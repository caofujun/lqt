package com.nis.log.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.ap;
import com.nis.log.entity.SysOperateLog;
import com.nis.log.service.SysOperateLogService;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SysOperateLogController extends BaseController {
	private static final Logger c = Logger.getLogger(SysOperateLogController.class);
	@Autowired
	private SysOperateLogService tJ;

	@RequestMapping({"/sysOperateLog/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		Map operateAction = ap.getMap();
		modelMap.put("operateAction", operateAction);
		return "log/sysOperateLogList";
	}

	@RequestMapping({"/sysOperateLog/f_json/pageQuery"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, SysOperateLog sysOperateLog) {
		String unitId = this.c(request);
		sysOperateLog.setUnitId(unitId);
		MyPage page = this.tJ.a(sysOperateLog);
		this.a(response, page);
	}

	@RequestMapping({"/sysOperateLog/f_view/look"})
	public String p(HttpServletRequest request, ModelMap modelMap, String id) {
		SysOperateLog sysOperateLog = this.tJ.get(id);
		sysOperateLog.setActionName(ap.b(sysOperateLog.getAction()));
		modelMap.put("sysOperateLog", sysOperateLog);
		return "log/sysOperateLogLook";
	}

	@RequestMapping({"/sysOperateLog/f_json/delete"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.tJ.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}