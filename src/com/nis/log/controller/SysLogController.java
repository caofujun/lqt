package com.nis.log.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.log.entity.SysLog;
import com.nis.log.service.SysLogService;
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
public class SysLogController extends BaseController {
	private static final Logger c = Logger.getLogger(SysLogController.class);
	@Autowired
	private SysLogService aV;

	@RequestMapping({"/sysLog/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "log/sysLogList";
	}

	@RequestMapping({"/sysLog/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "SQL日志--SQL日志列表")
	public void a(HttpServletRequest request, HttpServletResponse response, SysLog sysLog) {
		MyPage page = this.aV.a(sysLog);
		this.a(response, page);
	}

	@RequestMapping({"/sysLog/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "SQL日志--删除SQL日志")
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.aV.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/sysLog/f_view/look"})
	@SqlLog(p = "SQL日志--SQL日志详情")
	public String p(HttpServletRequest request, ModelMap modelMap, String id) {
		SysLog sysLog = this.aV.get(id);
		modelMap.put("sysLog", sysLog);
		return "log/sysLogLook";
	}

	@RequestMapping({"/sysLog/f_json/findbyBusinessId"})
	public void Q(HttpServletRequest request, HttpServletResponse response, String businessId) {
		List page = this.aV.findbyBusinessId(businessId);
		this.a(response, page);
	}

	@RequestMapping({"/sysLog/f_view/sqlLog"})
	public String z(HttpServletRequest request, ModelMap modelMap) {
		return "log/sqlLogList";
	}

	@RequestMapping({"/sysLog/f_json/delSqlLog"})
	@ResponseBody
	@SqlLog(p = "SQL日志--清理SQL日志")
	public void H(HttpServletRequest request, HttpServletResponse response) {
		Result result = null;

		try {
			result = new Result();
			this.aV.delSqlLog();
			result.setResult("success");
		} catch (Exception arg4) {
			c.error("获取信息异常!", arg4);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}