package com.nis.task.core.exec.task;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nis.comm.utils.f;
import com.nis.param.service.SysParamService;
import com.nis.task.core.base.a;
import com.nis.task.entity.TaskJob;
import com.nis.task.entity.TaskProject;
import com.nis.task.service.TaskJobLogService;
import com.nis.task.service.TaskJobService;
import com.nis.task.service.TaskProjectService;

@Component
public class TaskJobTask extends a {
	private static final Logger c = Logger.getLogger(TaskJobTask.class);
	@Autowired
	private TaskJobService aU;
	@Autowired
	private TaskProjectService xH;
	@Autowired
	private TaskJobLogService xI;
	@Autowired
	private SysParamService j;

	public void execute(JobExecutionContext context) {
      String time = f.c(new Date(), "yyyy-MM-dd HH:mm:ss");
      String id = (String)context.getJobDetail().getJobDataMap().get("taskId");
      TaskJob taskJob = this.aU.get(id);
      TaskProject taskProject = this.xH.get(taskJob.getProjectid());
      //TODO: (new Thread(new 1(this, taskJob, taskProject, time))).start();
   }
}