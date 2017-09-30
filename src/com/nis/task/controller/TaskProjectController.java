package com.nis.task.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.at;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.l;
import com.nis.task.entity.TaskProject;
import com.nis.task.service.TaskProjectService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TaskProjectController extends BaseController {
	private static final Logger logger = Logger.getLogger(TaskProjectController.class);
	@Autowired
	private TaskProjectService xH;

	@RequestMapping({"/taskProject/f_view/manager"})
	public String p(HttpServletRequest request) {
		return "task/project_manager";
	}

	@RequestMapping({"/taskProject/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "定时器项目--定时器项目列表")
	public MyPage<TaskProject> a(HttpServletRequest request, TaskProject taskProject) {
		MyPage page = null;

		try {
			if (taskProject.getSearchString() != null && !"".equals(taskProject.getSearchString())) {
				taskProject.setSearchString(ab.aR(taskProject.getSearchString()));
			}

			page = this.xH.b(taskProject);
		} catch (Exception arg4) {
			logger.error("分页获取信息异常: " + arg4.getMessage(), arg4);
		}

		return page;
	}

	@RequestMapping({"/taskProject/f_view/edit"})
	@SqlLog(p = "定时器项目--定时器项目详情")
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (id != null) {
			modelMap.put("taskProject", this.xH.get(id));
		}

		modelMap.put("projectSigns", l.toString(at.getList()));
		return "task/project_edit";
	}

	@RequestMapping({"/taskProject/f_json/save"})
	@ResponseBody
	@SqlLog(p = "定时器项目--保存定时器项目信息")
	public void a(HttpServletRequest request, HttpServletResponse response, TaskProject taskProject) {
		Result result = null;
		LoginUser loginUser = this.d(request);

		try {
			result = new Result();
			if (StringUtils.isBlank(taskProject.getId())) {
				taskProject.setAdduser(loginUser.getUserId());
				this.xH.save(taskProject);
			} else {
				this.xH.update(taskProject);
			}

			result.setResult("success");
		} catch (Exception arg6) {
			logger.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/taskProject/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "定时器项目--删除定时器项目信息")
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.xH.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			logger.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}