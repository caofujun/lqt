package com.nis.task.core.exec.task;

import com.nis.comm.enums.ad;
import com.nis.task.core.base.a;
import com.nis.task.core.exec.JobService;
import com.nis.task.core.exec.task.TaskJobTask;
import com.nis.task.core.listener.b;
import com.nis.task.entity.TaskJob;
import com.nis.task.service.TaskJobService;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainTask extends a {
	private static final Logger c = Logger.getLogger(MainTask.class);
	@Autowired
	private JobService xO;
	@Autowired
	private TaskJobService aU;
	@Autowired
	private TaskJobTask xS;

	public void execute(JobExecutionContext context) {
		List jobs = this.aU.findByStatus(ad.ig.getCode());
		Iterator arg3 = jobs.iterator();

		while (arg3.hasNext()) {
			TaskJob taskJob = (TaskJob) arg3.next();

			try {
				this.xO.b(taskJob.getId().toString(), taskJob.getCron(), this.xS, new b(taskJob.getId().toString()));
				c.info("主线程添加任务 ID【" + taskJob.getId() + "】名称【" + taskJob.getName() + "】成功");
				this.aU.updateStatus(taskJob.getId(), ad.ie.getCode());
			} catch (SchedulerException arg5) {
				c.error(arg5.getMessage(), arg5);
				this.aU.updateStatus(taskJob.getId(), ad.ih.getCode());
			}
		}

	}
}