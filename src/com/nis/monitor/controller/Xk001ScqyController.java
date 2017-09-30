package com.nis.monitor.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.dict.service.SysDictService;
import com.nis.monitor.entity.Xk001Scqy;
import com.nis.monitor.service.Xk001ScqyService;
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
public class Xk001ScqyController extends BaseController {
	private static final Logger c = Logger.getLogger(Xk001ScqyController.class);
	@Autowired
	private Xk001ScqyService uz;
	@Autowired
	private SysDictService p;

	@RequestMapping({"/mdmm/f_view/mdmmIndex"})
	public String K(HttpServletRequest request, ModelMap modelMap) {
		return "monitor/mdmmIndex";
	}

	@RequestMapping({"/mdmm/f_json/query"})
	@ResponseBody
	@SqlLog(p = "企业信息管理--企业信息列表")
	public void a(HttpServletRequest request, HttpServletResponse response, String searchString) {
		if (searchString != null && !"".equals(searchString)) {
			searchString = ab.aR(searchString);
		}

		List all = this.uz.getAll(searchString);
		this.a(response, all);
	}

	@RequestMapping({"/mdmm/f_view/toAdd"})
	@SqlLog(p = "企业信息管理--新增企业信息")
	public String L(HttpServletRequest request, ModelMap modelMap, String qyNo) {
		if (ab.isNotEmpty(qyNo)) {
			Xk001Scqy ET = this.uz.get(qyNo);
			modelMap.put("XK001", ET);
		}

		List ET1 = this.p.u("enterpriseType", "0");
		modelMap.put("ET", ET1);
		List ES = this.p.u("enable_status", "0");
		modelMap.put("ES", ES);
		return "monitor/mdmmAdd";
	}

	@RequestMapping({"/mdmm/f_json/save"})
	@ResponseBody
	@SqlLog(p = "企业信息管理--保存企业信息")
	public void a(HttpServletRequest request, HttpServletResponse response, Xk001Scqy xk001Scqy) {
		Result result = new Result();

		try {
			this.uz.save(xk001Scqy);
			result.setResult("success");
		} catch (Exception arg5) {
			arg5.printStackTrace();
			c.error("企业信息管理保存出错", arg5);
			result.setResult("error");
			result.setMsg("企业信息管理保存出错！错误信息：" + arg5.getMessage());
		}

		this.a(response, result);
	}

	@RequestMapping({"/mdmm/f_json/update"})
	@ResponseBody
	@SqlLog(p = "企业信息管理--更新企业信息")
	public void b(HttpServletRequest request, HttpServletResponse response, Xk001Scqy xk001Scqy) {
		Result result = new Result();

		try {
			this.uz.update(xk001Scqy);
			result.setResult("success");
		} catch (Exception arg5) {
			arg5.printStackTrace();
			c.error("企业信息管理修改出错", arg5);
			result.setResult("error");
			result.setMsg("企业信息管理修改出错！错误信息：" + arg5.getMessage());
		}

		this.a(response, result);
	}

	@RequestMapping({"/mdmm/f_json/isNoExist"})
	@ResponseBody
	public void o(HttpServletRequest request, HttpServletResponse response, String qyNo) {
		Result result = new Result();
		Xk001Scqy xk001Scqy = this.uz.get(qyNo);
		if (xk001Scqy != null && ab.isNotEmpty(xk001Scqy.getQyNo())) {
			result.setResult("success");
		} else {
			result.setResult("error");
		}

		this.a(response, result);
	}

	@RequestMapping({"/mdmm/f_json/setStatus"})
	@ResponseBody
	@SqlLog(p = "企业信息管理--设置企业信息状态")
	public void N(HttpServletRequest request, HttpServletResponse response, String qyNo, String useFlag) {
		Result result = new Result();

		try {
			this.uz.setStatus(qyNo, useFlag);
			result.setResult("success");
		} catch (Exception arg6) {
			arg6.printStackTrace();
			c.error("企业信息管理修改状态出错！错误信息：" + arg6.getMessage());
			result.setResult("error");
		}

		this.a(response, result);
	}

	@RequestMapping({"/mdmm/f_json/exportScqyList"})
	@ResponseBody
	@SqlLog(p = "企业信息管理--导出企业信息列表")
	public void ad(HttpServletRequest request, HttpServletResponse response, String searchString) {
		this.uz.a(response, searchString);
	}
}