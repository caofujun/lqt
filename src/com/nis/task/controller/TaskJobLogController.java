package com.nis.task.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.task.entity.TaskJobLog;
import com.nis.task.service.TaskJobLogService;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TaskJobLogController extends BaseController {
	private static final Logger logger = Logger.getLogger(TaskJobLogController.class);
	@Autowired
	private TaskJobLogService xI;

	@RequestMapping({"/taskJobLog/f_view/manager"})
	public String p(HttpServletRequest request) {
		return "task/jobLog_manager";
	}

	@RequestMapping({"/taskJobLog/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "定时器明细--定时器执行日志列表")
	public MyPage<TaskJobLog> a(HttpServletRequest request, TaskJobLog taskJobLog) {
		MyPage page = null;

		try {
			page = this.xI.a(taskJobLog);
		} catch (Exception arg4) {
			logger.error("分页获取信息异常: " + arg4.getMessage(), arg4);
		}

		return page;
	}

	@RequestMapping({"/taskJobLog/f_view/look"})
	@SqlLog(p = "定时器明细--定时器执行日志详情")
	public String ag(HttpServletRequest request, ModelMap modelMap, String id) {
		if (id != null) {
			modelMap.put("taskJobLog", this.xI.get(id));
		}

		return "task/jobLog_look";
	}

	@RequestMapping({"/taskJobLog/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "定时器明细--定时器执行日志详情")
	public ModelMap e(HttpServletRequest request, String id) {
		String result = null;

		try {
			this.xI.delete(id);
			result = "success";
		} catch (Exception arg4) {
			logger.error("删除异常: " + arg4.getMessage(), arg4);
			result = "error";
		}

		ModelMap modelMap = new ModelMap();
		modelMap.put("result", result);
		return modelMap;
	}
}