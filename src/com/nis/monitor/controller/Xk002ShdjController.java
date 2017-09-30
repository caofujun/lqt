package com.nis.monitor.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.dict.service.SysDictService;
import com.nis.monitor.entity.Xk002Shdj;
import com.nis.monitor.service.Xk001ScqyService;
import com.nis.monitor.service.Xk002ShdjService;
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
public class Xk002ShdjController extends BaseController {
	private static final Logger c = Logger.getLogger(Xk002ShdjController.class);
	@Autowired
	private Xk002ShdjService uA;
	@Autowired
	private Xk001ScqyService uz;
	@Autowired
	private SysDictService p;

	@RequestMapping({"/aim/f_view/aimIndex"})
	public String K(HttpServletRequest request, ModelMap modelMap) {
		return "monitor/aimIndex";
	}

	@RequestMapping({"/aim/f_json/query"})
	@ResponseBody
	@SqlLog(p = "证照信息管理--证照信息列表")
	public void a(HttpServletRequest request, HttpServletResponse response, String searchString) {
		if (searchString != null && !"".equals(searchString)) {
			searchString = ab.aR(searchString);
		}

		List all = this.uA.getAll(searchString);
		this.a(response, all);
	}

	@RequestMapping({"/aim/f_view/toAdd"})
	@SqlLog(p = "证照信息管理--证照信息详情")
	public String L(HttpServletRequest request, ModelMap modelMap, String shNo) {
		if (ab.isNotEmpty(shNo)) {
			Xk002Shdj AAT = this.uA.get(shNo);
			modelMap.put("XK002", AAT);
		}

		List AAT1 = this.p.u("aim_audit_type", "0");
		modelMap.put("AAT", AAT1);
		List ES = this.p.u("enable_status", "0");
		modelMap.put("ES", ES);
		return "monitor/aimAdd";
	}

	@RequestMapping({"/aim/f_json/save"})
	@ResponseBody
	@SqlLog(p = "证照信息管理--保存证照信息")
	public void a(HttpServletRequest request, HttpServletResponse response, Xk002Shdj xk002Shdj) {
		Result result = new Result();

		try {
			xk002Shdj.setShDate(new Date());
			this.uA.save(xk002Shdj);
			result.setResult("success");
		} catch (Exception arg5) {
			arg5.printStackTrace();
			c.error("审核信息管理保存出错", arg5);
			result.setResult("error");
			result.setMsg("审核信息管理保存出错！错误信息：" + arg5.getMessage());
		}

		this.a(response, result);
	}

	@RequestMapping({"/aim/f_json/update"})
	@ResponseBody
	@SqlLog(p = "证照信息管理--修改证照信息")
	public void b(HttpServletRequest request, HttpServletResponse response, Xk002Shdj xk002Shdj) {
		Result result = new Result();

		try {
			this.uA.update(xk002Shdj);
			result.setResult("success");
		} catch (Exception arg5) {
			arg5.printStackTrace();
			c.error("企业信息管理修改出错", arg5);
			result.setResult("error");
			result.setMsg("企业信息管理修改出错！错误信息：" + arg5.getMessage());
		}

		this.a(response, result);
	}

	@RequestMapping({"/aim/f_json/isNoExist"})
	@ResponseBody
	@SqlLog(p = "证照信息管理--检查证照信息是否存在")
	public void o(HttpServletRequest request, HttpServletResponse response, String shNo) {
		Result result = new Result();
		Xk002Shdj xk002Shdj = this.uA.get(shNo);
		if (xk002Shdj != null && ab.isNotEmpty(xk002Shdj.getShNo())) {
			result.setResult("success");
		} else {
			result.setResult("error");
		}

		this.a(response, result);
	}

	@RequestMapping({"/aim/f_json/setStatus"})
	@ResponseBody
	@SqlLog(p = "证照信息管理--设置证照信息状态")
	public void N(HttpServletRequest request, HttpServletResponse response, String shNo, String useFlag) {
		Result result = new Result();

		try {
			this.uA.setStatus(shNo, useFlag);
			result.setResult("success");
		} catch (Exception arg6) {
			arg6.printStackTrace();
			c.error("企业信息管理修改状态出错！错误信息：" + arg6.getMessage());
			result.setResult("error");
		}

		this.a(response, result);
	}

	@RequestMapping({"/aim/f_json/queryQyList"})
	@ResponseBody
	@SqlLog(p = "证照信息管理--证照信息列表")
	public void ae(HttpServletRequest request, HttpServletResponse response, String qyType) {
		List queryQyList = this.uz.queryQyList(qyType);
		this.a(response, queryQyList);
	}

	@RequestMapping({"/aim/f_json/exportShdjList"})
	@ResponseBody
	@SqlLog(p = "证照信息管理--导出证照信息列表")
	public void af(HttpServletRequest request, HttpServletResponse response, String searchString) {
		this.uA.b(response, searchString);
	}
}