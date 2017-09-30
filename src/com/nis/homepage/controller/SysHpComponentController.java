package com.nis.homepage.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.n;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.homepage.entity.SysHpComponent;
import com.nis.homepage.entity.SysHpStyle;
import com.nis.homepage.service.SysHpComponentService;
import com.nis.homepage.service.SysHpStyleService;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SysHpComponentController extends BaseController {
	private static final Logger c = Logger.getLogger(SysHpComponentController.class);
	@Autowired
	private SysHpComponentService rh;
	@Autowired
	private SysHpStyleService ri;

	@RequestMapping({"/sysHpComponent/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "homepage/sysHpComponentList";
	}

	@RequestMapping({"/sysHpComponent/f_json/pageQuery"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, SysHpComponent sysHpComponent) {
		MyPage page = this.rh.b(sysHpComponent);
		this.a(response, page);
	}

	@RequestMapping({"/sysHpComponent/f_json/find"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, SysHpComponent sysHpComponent) {
		LoginUser loginUser = this.d(request);
		SysHpStyle sysHpStyle = this.ri.a(loginUser.getDataScope().intValue(), loginUser.getUnitId(),
				loginUser.getDepNo(), loginUser.getUsername());
		sysHpComponent.setComponentCodes(sysHpStyle.getComponentCodes());
		if (sysHpComponent.getSearchString() != null && !"".equals(sysHpComponent.getSearchString())) {
			sysHpComponent.setSearchString(ab.aR(sysHpComponent.getSearchString()));
		}

		List page = this.rh.find(sysHpComponent);
		this.a(response, page);
	}

	@RequestMapping({"/sysHpComponent/f_view/toedit"})
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (id != null) {
			SysHpComponent sysHpComponent = this.rh.get(id);
			modelMap.put("sysHpComponent", sysHpComponent);
		}

		return "homepage/sysHpComponentEdit";
	}

	@RequestMapping({"/sysHpComponent/f_view/select"})
	public String o(HttpServletRequest request, ModelMap modelMap) {
		return "homepage/sysHpComponentSelect";
	}

	@RequestMapping({"/sysHpComponent/f_json/save"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, SysHpComponent sysHpComponent) {
		Result result = null;

		try {
			if (n.gm.getValue().toString().equals(sysHpComponent.getScopeLevel())) {
				sysHpComponent.setUnitId("0");
			}

			result = this.rh.a(sysHpComponent);
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/sysHpComponent/f_json/delete"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.rh.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/sysHpComponent/f_json/status"})
	@ResponseBody
	public void v(HttpServletRequest request, HttpServletResponse response, String id, String status) {
		Result result = null;

		try {
			result = new Result();
			this.rh.updateStatus(id, status);
			result.setResult("success");
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/sysHpComponent/f_json/date"})
	@ResponseBody
	public void v(HttpServletRequest request, HttpServletResponse response) {
		Result result = null;

		try {
			result = new Result();
			result.setData(f.c(new Date(), "yyyy-MM-dd"));
			result.setResult("success");
		} catch (Exception arg4) {
			c.error("获取信息异常!", arg4);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}