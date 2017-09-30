package com.nis.task.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.ad;
import com.nis.comm.enums.ae;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.param.service.SysParamService;
import com.nis.task.controller.TaskJobController.1;
import com.nis.task.entity.TaskJob;
import com.nis.task.entity.TaskProject;
import com.nis.task.service.TaskJobLogService;
import com.nis.task.service.TaskJobService;
import com.nis.task.service.TaskProjectService;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TaskJobController extends BaseController {
	private static final Logger logger = Logger.getLogger(TaskJobController.class);
	@Autowired
	private TaskJobService aU;
	@Autowired
	private TaskProjectService xH;
	@Autowired
	private TaskJobLogService xI;
	@Autowired
	private SysParamService j;

	@RequestMapping({"/taskJob/f_view/manager"})
	public String p(HttpServletRequest request) {
		return "task/job_manager";
	}

	@RequestMapping({"/taskJob/f_view/jobcron"})
	public String q(HttpServletRequest request) {
		return "task/job_cron";
	}

	@RequestMapping({"/taskJob/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "定时器明细--定时器明细列表")
	public MyPage<TaskJob> a(HttpServletRequest request, TaskJob taskJob) {
		MyPage page = null;

		try {
			if (taskJob.getSearchString() != null && !"".equals(taskJob.getSearchString())) {
				taskJob.setSearchString(ab.aR(taskJob.getSearchString()));
			}

			page = this.aU.a(taskJob);
		} catch (Exception arg4) {
			logger.error("分页获取信息异常: " + arg4.getMessage(), arg4);
		}

		return page;
	}

	@RequestMapping({"/taskJob/f_view/edit"})
	@SqlLog(p = "定时器明细--定时器明细详情")
	public String e(HttpServletRequest request, ModelMap modelMap, String id, String projectid) {
		if (ab.isNotEmpty(id)) {
			modelMap.put("taskJob", this.aU.get(id));
		} else {
			TaskJob taskJob = new TaskJob();
			taskJob.setStatus(ad.ig.getCode());
			modelMap.put("taskJob", taskJob);
			modelMap.put("taskProject", this.xH.get(projectid));
		}

		return "task/job_edit";
	}

	@RequestMapping({"/taskJob/f_view/CronGenerator"})
	public String av(HttpServletRequest request, ModelMap modelMap) {
		return "task/Cron_Generator";
	}

	@RequestMapping({"/taskJob/f_json/save"})
	@ResponseBody
	@SqlLog(p = "定时器明细--保存定时器明细")
	public void a(HttpServletRequest request, HttpServletResponse response, TaskJob taskJob) {
		Result result = null;
		LoginUser loginUser = this.d(request);

		try {
			result = new Result();
			if (ab.isEmpty(taskJob.getId())) {
				taskJob.setAdduser(loginUser.getUserId());
				this.aU.save(taskJob);
			} else {
				this.aU.update(taskJob);
			}

			result.setResult("success");
		} catch (Exception arg6) {
			logger.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/taskJob/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "定时器明细--删除定时器明细")
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.aU.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			logger.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/taskJob/f_json/run"})
   @ResponseBody
   @SqlLog(
      p = "定时器明细--执行定时器"
   )
   public ModelMap d(HttpServletRequest request, String id) {
      String result = null;

      try {
         String modelMap = f.c(new Date(), "yyyy-MM-dd HH:mm:ss");
         TaskJob taskJob = this.aU.get(id);
         TaskProject taskProject = this.xH.get(taskJob.getProjectid());
         (new Thread(new 1(this, taskJob, taskProject, modelMap))).start();
         result = "success";
      } catch (Exception arg6) {
         logger.error("修改任务状态异常: " + arg6.getMessage(), arg6);
         result = "error";
      }

      ModelMap modelMap1 = new ModelMap();
      modelMap1.put("result", result);
      return modelMap1;
   }

	@RequestMapping({"/taskJob/f_json/updateStatus"})
	@ResponseBody
	@SqlLog(p = "定时器明细--更新定时器明细任务状态")
	public ModelMap a(HttpServletRequest request, String id, Integer status, String judgeCode) {
		String result = null;

		try {
			TaskJob modelMap;
			if (ab.isNotEmpty(judgeCode)) {
				modelMap = this.aU.findByLink(ae.D(judgeCode).getUrl());
				modelMap.setStatus(status);
				this.aU.update(modelMap);
			} else if (ab.isNotEmpty(id)) {
				modelMap = this.aU.get(id);
				modelMap.setStatus(status);
				this.aU.update(modelMap);
			}

			result = "success";
		} catch (Exception arg6) {
			logger.error("修改任务状态异常: " + arg6.getMessage(), arg6);
			result = "error";
		}

		ModelMap modelMap1 = new ModelMap();
		modelMap1.put("result", result);
		return modelMap1;
	}
}