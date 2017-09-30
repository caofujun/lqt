package com.nis.organization.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.organization.entity.StandDept;
import com.nis.organization.service.StandDeptService;
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
public class StandDeptController extends BaseController {
	private static final Logger c = Logger.getLogger(StandDeptController.class);
	@Autowired
	private StandDeptService vV;

	@RequestMapping({"/standDept/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "organization/standDeptList";
	}

	@RequestMapping({"/standDept/f_view/query"})
	@ResponseBody
	public void Q(HttpServletRequest request, HttpServletResponse response) {
		List list = this.vV.getAll();
		this.b(response, list);
	}

	@RequestMapping({"/standDept/f_view/queryWithCdt"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, StandDept standDept) {
		List list = this.vV.getStandDept(standDept);
		this.b(response, list);
	}

	@RequestMapping({"/standDept/f_json/pageQuery"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, StandDept standDept) {
		MyPage page = this.vV.a(standDept);
		this.b(response, page);
	}

	@RequestMapping({"/standDept/f_view/toedit"})
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (StringUtils.isNotBlank(id)) {
			StandDept standDept = this.vV.get(id);
			modelMap.put("standDept", standDept);
		}

		return "organization/standDeptEdit";
	}

	@RequestMapping({"/standDept/f_json/save"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, StandDept standDept) {
		Result result = null;

		try {
			result = new Result();
			if (this.vV.get(standDept.getDeptId()) == null) {
				this.vV.save(standDept);
			} else {
				this.vV.update(standDept);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/standDept/f_json/delete"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.vV.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}