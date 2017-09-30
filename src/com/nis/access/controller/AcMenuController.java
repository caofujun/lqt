package com.nis.access.controller;

import com.nis.access.entity.AcAccount;
import com.nis.access.entity.AcMenu;
import com.nis.access.entity.AcPrivilege;
import com.nis.access.service.AcMenuService;
import com.nis.access.service.AcPrivilegeService;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.entity.TreeEntity;
import com.nis.comm.enums.ar;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.af;
import com.nis.comm.utils.l;
import com.nis.dict.entity.Zg005Yygrzd;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AcMenuController extends BaseController {
	private static final Logger c = Logger.getLogger(AcMenuController.class);
	@Autowired
	private AcMenuService k;
	@Autowired
	private AcPrivilegeService i;

	@RequestMapping({"/acMenu/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		modelMap.put("ownership", request.getParameter("ownership"));
		return "access/acMenuList";
	}

	@RequestMapping({"/acMenu/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "菜单管理--菜单列表")
	public void a(HttpServletRequest request, HttpServletResponse response, AcMenu acMenu) {
		MyPage page = this.k.b(acMenu);
		this.b(response, page);
	}

	@RequestMapping({"/acMenu/f_json/findTree"})
	@ResponseBody
	@SqlLog(p = "菜单管理--菜单树")
	public void a(HttpServletRequest request, HttpServletResponse response, Zg005Yygrzd yygrzd) {
		List list = this.k.a();
		this.b(response, list);
	}

	@RequestMapping({"/acMenu/f_view/toedit"})
	@SqlLog(p = "菜单管理--菜单详情")
	public String a(HttpServletRequest request, ModelMap modelMap, String id, String ownership, String parentMenuNo) {
		modelMap.put("ownerList", ar.getList());
		if (id != null) {
			AcMenu acMenu = this.k.get(id);
			modelMap.put("acMenu", acMenu);
		}

		return "access/acMenuEdit";
	}

	@RequestMapping({"/acMenu/f_json/save"})
	@ResponseBody
	@SqlLog(p = "菜单管理--保存菜单信息")
	public void b(HttpServletRequest request, HttpServletResponse response, AcMenu acMenu) {
		Result result = new Result();

		try {
			if (StringUtils.isBlank(acMenu.getUnitId())) {
				acMenu.setUnitId(this.c(request));
			}

			String e = acMenu.getDestUrl();
			if (acMenu.getMenuId() == null && e.contains("$hospital$")) {
				String parentId = null;
				if (ab.isNotEmpty(acMenu.getParentMenuNo())) {
					AcMenu am = this.k.e(acMenu.getParentMenuNo());
					parentId = am.getCode91160();
				}

				if (ab.isEmpty(parentId)) {
					parentId = "0";
				}

				e = e.substring(e.indexOf("?"));
			} else {
				result = this.k.a(acMenu);
			}
		} catch (Exception arg7) {
			c.error("获取信息异常!", arg7);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/acMenu/f_json/check"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, String menuNo, String ownership) {
		Result result = null;

		try {
			List e = this.k.getAllPatentMenu(ownership);
			ArrayList list = new ArrayList();
			this.a((List) e, (List) list, (String) menuNo);
			int count = list.size();
			if (count > 3) {
				result = new Result("error", "获取信息异常");
				this.a(response, result);
			} else {
				result = new Result("success");
				this.a(response, result);
			}
		} catch (Exception arg8) {
			c.error("获取信息异常!", arg8);
			result = new Result("error", "获取信息异常");
			this.a(response, result);
		}
	}

	private void a(List<AcMenu> dataList, List<AcMenu> list, String parentNo) {
		Iterator arg4 = dataList.iterator();

		while (arg4.hasNext()) {
			AcMenu menu = (AcMenu) arg4.next();
			if (menu.getMenuNo().equals(parentNo)) {
				list.add(menu);
				this.a(dataList, list, menu.getParentMenuNo());
			}
		}

	}

	@RequestMapping({"/acMenu/f_json/getAllPatentMenu"})
	@ResponseBody
	@SqlLog(p = "菜单管理--所有顶层菜单列表")
	public void c(HttpServletRequest request, HttpServletResponse response, AcMenu acMenu) {
		List result = this.k.getAllPatentMenu(acMenu.getOwnership());
		this.b(response, result);
	}

	@RequestMapping({"/acMenu/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "菜单管理--刪除菜单信息")
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.k.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/acMenu/f_json/getAllMenu"})
	@ResponseBody
	@SqlLog(p = "菜单管理--所有菜单列表")
	public void d(HttpServletRequest request, HttpServletResponse response, AcMenu acMenu) {
		List result = this.k.getAllMenu(acMenu);
		this.b(response, result);
	}

	@RequestMapping({"/acMenu/f_view/acMenuIndex"})
	public String a(HttpServletRequest request, ModelMap modelMap, String menuNo) {
		AcAccount account = (AcAccount) this.b(request);
		List menuList = this.k.getMenuByParentNo(menuNo, account.getRoleCur().getRoleId());
		modelMap.put("menuList", menuList);
		return "access/acMenuIndex";
	}

	@RequestMapping({"/acMenu/f_view/acReportAuth"})
	public String b(HttpServletRequest request, ModelMap modelMap, String menuNo) {
		modelMap.put("menuNo", menuNo);
		return "access/acReportAuth";
	}

	@RequestMapping({"/acMenu/f_json/reportAuth"})
	@ResponseBody
	@SqlLog(p = "菜单管理--报表菜单授权")
	public void e(HttpServletRequest request, HttpServletResponse response, String menuNo) {
		List menuList = this.k.f(menuNo);
		this.b(response, menuList);
	}

	@RequestMapping({"/acMenu/f_json/saveJoinReport"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String[] havegrants, String menuNo) {
		Result result = null;

		try {
			AcMenu e = this.k.e(menuNo);
			e.setJoinReport(ab.g(havegrants));
			this.k.update(e);
			result = new Result("success");
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/acMenu/f_json/findTreeForEasyUI"})
	@ResponseBody
	public void f(HttpServletRequest request, HttpServletResponse response, String mid) {
		List list = this.k.g(mid);
		this.b(response, list);
	}

	@RequestMapping({"/acMenu/f_view/toReportAuth"})
	public String c(HttpServletRequest request, ModelMap modelMap, String roleId, String ownership) {
		modelMap.put("roleId", roleId);
		return "access/toReportAuthTree";
	}

	@RequestMapping({"/acMenu/f_view/toReportAuthTree"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, String roleId, String ownership) {
		TreeEntity te = new TreeEntity();
		te.setId("0");
		te.setText("报表");
		te.setState("true");
		te.setIsfather("1");
		if (StringUtils.isNotBlank(roleId)) {
			List reportTree = this.k.h(roleId);
			te.setChildren(reportTree);
		}

		this.a(response, te);
	}

	@RequestMapping({"/acMenu/f_view/toReportAuthGridTree"})
	@ResponseBody
	@SqlLog(p = "菜单管理--报表菜单列表")
	public void d(HttpServletRequest request, HttpServletResponse response, String roleId, String ownership) {
		if (StringUtils.isNotBlank(roleId)) {
			List reportTree = this.k.h(roleId);
			this.a(response, reportTree);
		}

	}

	@RequestMapping({"/acMenu/f_json/saveReportAuth"})
	@ResponseBody
	@SqlLog(p = "菜单管理--保存报表菜单权限")
	public void e(HttpServletRequest request, HttpServletResponse response, String aps, String roleId) {
		Result result = null;

		try {
			List e = null;
			if (ab.isNotEmpty(aps)) {
				e = (List) l.a(aps, AcPrivilege.class);
			}

			for (int i = 0; i < e.size(); ++i) {
				((AcPrivilege) e.get(i)).setId(af.getUUID32());
			}

			this.i.saveList(e, roleId);
			result = new Result("success");
		} catch (Exception arg7) {
			c.error("获取信息异常!", arg7);
			result = new Result("error", "获取信息异常");
			result.setExtraValue(arg7.getMessage());
		}

		this.a(response, result);
	}
}