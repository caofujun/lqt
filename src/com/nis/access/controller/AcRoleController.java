package com.nis.access.controller;

import com.nis.access.entity.AcAccount;
import com.nis.access.entity.AcMenu;
import com.nis.access.entity.AcPrivilege;
import com.nis.access.entity.AcRole;
import com.nis.access.service.AcMenuService;
import com.nis.access.service.AcPrivilegeService;
import com.nis.access.service.AcRoleService;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.constants.b;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.c;
import com.nis.comm.enums.h;
import com.nis.comm.utils.ab;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AcRoleController extends BaseController {
	private static final Logger c = Logger.getLogger(AcRoleController.class);
	@Autowired
	private AcRoleService g;
	@Autowired
	private AcPrivilegeService i;
	@Autowired
	private AcMenuService k;

	@RequestMapping({"/acRole/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "access/acRoleList";
	}

	@RequestMapping({"/acRole/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "账号管理--角色列表")
	public void a(HttpServletRequest request, HttpServletResponse response, AcRole acRole, String ownership) {
		acRole.setRoleType(ownership);
		if (acRole.getName() != null && !"".equals(acRole.getName())) {
			acRole.setName(ab.aR(acRole.getName()));
		}

		MyPage page = this.g.a(acRole);
		this.b(response, page);
	}

	@RequestMapping({"/acRole/f_view/toedit"})
	@SqlLog(p = "账号管理--角色详情")
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (id != null) {
			AcRole acRole = this.g.get(id);
			modelMap.put("acRole", acRole);
		}

		return "access/acRoleEdit";
	}

	@RequestMapping({"/acRole/f_json/save"})
	@ResponseBody
	@SqlLog(p = "账号管理--保存角色信息")
	public void b(HttpServletRequest request, HttpServletResponse response, AcRole acRole, String ownership) {
		Result result = null;

		try {
			AcAccount e = (AcAccount) this.b(request);
			result = new Result();
			acRole.setRoleType(ownership);
			if (StringUtils.isBlank(acRole.getRoleId())) {
				acRole.setCreateUser(e.getUsername());
				acRole.setUnitId(e.getUnitId());
				this.g.save(acRole);
				result.setResult("success");
			} else {
				this.g.update(acRole);
				result.setResult("success");
			}
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/acRole/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "账号管理--删除角色信息")
	public void f(HttpServletRequest request, HttpServletResponse response, String id, String ownership) {
		Result result = null;

		try {
			result = this.g.i(id);
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/acRole/f_view/toauth"})
	@SqlLog(p = "账号管理--角色授权列表")
	public String d(HttpServletRequest request, ModelMap modelMap, String id, String ownership) {
		if (StringUtils.isNotBlank(id)) {
			Integer isHavegrant = Integer.valueOf(0);
			AcRole acRole = this.g.get(id);
			modelMap.put("acRole", acRole);
			AcAccount account = (AcAccount) this.b(request);
			if (!b.eB.equals(account.getUsername()) && !b.eC.equals(account.getUsername())) {
				List curRolePrivileges = this.i.findByRoleid(account.getRoleCur().getRoleId());
				Iterator arg9 = curRolePrivileges.iterator();

				while (arg9.hasNext()) {
					AcPrivilege ap = (AcPrivilege) arg9.next();
					if (h.fP.getCode().toString().equals(ap.getHavegrant())) {
						isHavegrant = Integer.valueOf(1);
						break;
					}
				}
			} else {
				isHavegrant = Integer.valueOf(1);
			}

			modelMap.put("isHavegrant", isHavegrant);
		}

		return "access/acRoleAuth";
	}

	@RequestMapping({"/acRole/f_json/findMyMenu"})
	@ResponseBody
	@SqlLog(p = "账号管理--角色授权列表")
	public void a(HttpServletRequest request, HttpServletResponse response, String roleId, String ownership) {
		AcAccount account = (AcAccount) this.b(request);
		List list = this.g.a(account, ownership);
		List curRolePrivileges = this.i.findByRoleid(roleId);
		Iterator arg8 = list.iterator();

		while (arg8.hasNext()) {
			AcMenu page = (AcMenu) arg8.next();
			Iterator arg10 = curRolePrivileges.iterator();

			while (arg10.hasNext()) {
				AcPrivilege ap = (AcPrivilege) arg10.next();
				if (ap.getAccessId().equals(page.getMenuId())) {
					page.setIsHavegrant(ap.getHavegrant());
					String isFungrant = null;
					if (com.nis.comm.enums.c.fx.getCode().toString().equals(ap.getOperation())) {
						isFungrant = h.fP.getCode().toString();
					} else if (com.nis.comm.enums.c.fy.getCode().toString().equals(ap.getOperation())) {
						isFungrant = h.fQ.getCode().toString();
					}

					page.setIsFungrant(isFungrant);
				}
			}
		}

		MyPage page1 = new MyPage(1, 10, list.size(), list);
		this.b(response, page1);
	}

	@RequestMapping({"/acRole/f_json/saveAuth"})
	@ResponseBody
	@SqlLog(p = "账号管理--保存角色权限")
	public void a(HttpServletRequest request, HttpServletResponse response, AcRole acRole, String[] fungrants,
			String[] havegrants) {
		Result result = new Result();

		try {
			this.i.a(acRole.getRoleId(), fungrants, havegrants);
			result.setResult("success");
		} catch (Exception arg7) {
			c.error("保存授权信息异常!", arg7);
			result = new Result("error", "保存授权信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/acRole/f_json/findMyMenu2"})
	@ResponseBody
	public void g(HttpServletRequest request, HttpServletResponse response, String roleId, String ownership) {
		AcAccount account = (AcAccount) this.b(request);
		List treeMenu = this.k.a(account, ownership, roleId);
		this.b(response, treeMenu);
	}
}