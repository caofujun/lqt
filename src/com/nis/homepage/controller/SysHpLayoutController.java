package com.nis.homepage.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.homepage.entity.SysHpLayout;
import com.nis.homepage.service.SysHpLayoutService;
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
public class SysHpLayoutController extends BaseController {
	private static final Logger c = Logger.getLogger(SysHpLayoutController.class);
	@Autowired
	private SysHpLayoutService rj;

	@RequestMapping({"/sysHpLayout/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "homepage/sysHpLayoutList";
	}

	@RequestMapping({"/sysHpLayout/f_json/pageQuery"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, SysHpLayout sysHpLayout) {
		MyPage page = this.rj.b(sysHpLayout);
		this.a(response, page);
	}

	@RequestMapping({"/sysHpLayout/f_json/find"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, SysHpLayout sysHpLayout) {
		List list = this.rj.find(sysHpLayout);
		this.a(response, list);
	}

	@RequestMapping({"/sysHpLayout/f_view/toedit"})
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (id != null) {
			SysHpLayout sysHpLayout = this.rj.get(id);
			modelMap.put("sysHpLayout", sysHpLayout);
		}

		return "homepage/sysHpLayoutEdit";
	}

	@RequestMapping({"/sysHpLayout/f_view/select"})
	public String o(HttpServletRequest request, ModelMap modelMap) {
		return "homepage/sysHpLayoutSelect";
	}

	@RequestMapping({"/sysHpLayout/f_json/save"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, SysHpLayout sysHpLayout) {
		Result result = null;

		try {
			result = this.rj.a(sysHpLayout);
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/sysHpLayout/f_json/delete"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.rj.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/sysHpLayout/f_json/status"})
	@ResponseBody
	public void v(HttpServletRequest request, HttpServletResponse response, String id, String status) {
		Result result = null;

		try {
			result = new Result();
			this.rj.updateStatus(id, status);
			result.setResult("success");
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}