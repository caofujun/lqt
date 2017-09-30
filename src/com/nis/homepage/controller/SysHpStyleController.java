package com.nis.homepage.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.n;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.l;
import com.nis.homepage.entity.SysHpStyle;
import com.nis.homepage.service.SysHpComponentService;
import com.nis.homepage.service.SysHpLayoutService;
import com.nis.homepage.service.SysHpStyleService;
import com.nis.param.service.SysParamService;
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
public class SysHpStyleController extends BaseController {
	private static final Logger c = Logger.getLogger(SysHpStyleController.class);
	@Autowired
	private SysHpStyleService ri;
	@Autowired
	private SysHpLayoutService rj;
	@Autowired
	private SysHpComponentService rh;
	@Autowired
	private SysParamService j;

	@RequestMapping({"/sysHpStyle/f_json/layout"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String layoutCode,
			String componentCodes, String sourceType) {
		LoginUser loginUser = this.d(request);
		String photoPath = loginUser.getPhotoPath();
		photoPath = StringUtils.isEmpty(photoPath) ? null : photoPath.replaceAll("\\\\", "/");
		loginUser.setPhotoPath(photoPath);
		List menus = (List) l.toObject((String) this.b(request, "user_menus_json"), List.class);
		String string = this.ri.a(layoutCode, componentCodes, loginUser, sourceType, menus);
		this.a(response, string);
	}

	@RequestMapping({"/sysHpStyle/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "homepage/sysHpStyleList";
	}

	@RequestMapping({"/sysHpStyle/f_view/set"})
	public String s(HttpServletRequest request, ModelMap modelMap) {
		LoginUser loginUser = this.d(request);
		SysHpStyle sysHpStyle = this.ri.a(loginUser.getDataScope().intValue(), loginUser.getUnitId(),
				loginUser.getDepNo(), loginUser.getUsername());
		modelMap.put("sysHpStyle", sysHpStyle);
		return "homepage/sysHpStyleSet";
	}

	@RequestMapping({"/sysHpStyle/f_json/load"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Long scopeLevel, String unitId,
			String depNo) {
		SysHpStyle sysHpStyle = this.ri.a(scopeLevel, unitId, depNo, (String) null);
		this.a(response, sysHpStyle);
	}

	@RequestMapping({"/sysHpStyle/f_json/pageQuery"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, SysHpStyle sysHpStyle) {
		MyPage page = this.ri.a(sysHpStyle);
		this.a(response, page);
	}

	@RequestMapping({"/sysHpStyle/f_view/toedit"})
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (id != null) {
			SysHpStyle sysHpStyle = this.ri.get(id);
			modelMap.put("sysHpStyle", sysHpStyle);
		}

		return "homepage/sysHpStyleEdit";
	}

	@RequestMapping({"/sysHpStyle/f_json/save"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, SysHpStyle sysHpStyle) {
		Result result = null;

		try {
			result = new Result();
			if (!StringUtils.isBlank(sysHpStyle.getId())) {
				this.ri.update(sysHpStyle);
			} else {
				if (StringUtils.isBlank(sysHpStyle.getScopeLevel())) {
					if (ab.isNotEmpty(sysHpStyle.getUserName())) {
						sysHpStyle.setScopeLevel(n.gh.getValue().toString());
					} else if (ab.isNotEmpty(sysHpStyle.getDepNo())) {
						sysHpStyle.setScopeLevel(n.gi.getValue().toString());
					} else if (sysHpStyle.getUnitId() != null && !"0".equals(sysHpStyle.getUnitId())) {
						sysHpStyle.setScopeLevel(n.gk.getValue().toString());
					} else {
						sysHpStyle.setScopeLevel(n.gm.getValue().toString());
					}
				}

				this.ri.save(sysHpStyle);
			}

			result.setData(sysHpStyle);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/sysHpStyle/f_json/delete"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.ri.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}