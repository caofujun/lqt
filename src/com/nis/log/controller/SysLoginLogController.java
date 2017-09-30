package com.nis.log.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.log.entity.SysLoginLog;
import com.nis.log.service.SysLoginLogService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SysLoginLogController extends BaseController {
	private static final Logger c = Logger.getLogger(SysLoginLogController.class);
	@Autowired
	private SysLoginLogService tI;

	@RequestMapping({"/sysLoginLog/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "log/sysLoginLogList";
	}

	@RequestMapping({"/sysLoginLog/f_view/look"})
	public String p(HttpServletRequest request, ModelMap modelMap, String id) {
		SysLoginLog sysLoginLog = this.tI.get(id);
		modelMap.put("sysLoginLog", sysLoginLog);
		return "log/sysLoginLogLook";
	}

	@RequestMapping({"/sysLoginLog/f_json/pageQuery"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, SysLoginLog SysLoginLog) {
		String unitId = this.c(request);
		SysLoginLog.setUnitId(unitId);
		MyPage page = this.tI.a(SysLoginLog);
		this.a(response, page);
	}

	@RequestMapping({"/sysLoginLog/f_json/delete"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.tI.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}