package com.nis.task.core.exec;

import com.nis.comm.utils.AppContextUtil;
import com.nis.task.core.base.b;
import com.nis.task.core.base.c;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.stereotype.Component;

@Component
public class JobService {
	public void a(String id, String cron, b execTask, JobListener jobListener) throws SchedulerException {
		Scheduler scheduler = (Scheduler) AppContextUtil.getInstance().getBean(Scheduler.class);
		String name = "task_" + id;
		String group = "group_" + id;
		TriggerKey triggerKey = TriggerKey.triggerKey(name, group);
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		JobDetail jobDetail = JobBuilder.newJob(c.class).withIdentity(name, group).build();
		jobDetail.getJobDataMap().put("task", execTask);
		jobDetail.getJobDataMap().put("taskId", id);
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
		trigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(scheduleBuilder)
				.build();
		scheduler.scheduleJob(jobDetail, trigger);
		scheduler.getListenerManager().addJobListener(jobListener);
	}

	public void O(String id, String cron) throws SchedulerException {
		Scheduler scheduler = (Scheduler) AppContextUtil.getInstance().getBean(Scheduler.class);
		String name = "task_" + id;
		String group = "group_" + id;
		TriggerKey triggerKey = TriggerKey.triggerKey(name, group);
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
		trigger = (CronTrigger) trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder)
				.build();
		scheduler.rescheduleJob(triggerKey, trigger);
	}

	public void b(String id, String cron, b execTask, JobListener jobListener) throws SchedulerException {
		Scheduler scheduler = (Scheduler) AppContextUtil.getInstance().getBean(Scheduler.class);
		String name = "task_" + id;
		String group = "group_" + id;
		TriggerKey triggerKey = TriggerKey.triggerKey(name, group);
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		if (trigger == null) {
			JobDetail scheduleBuilder = JobBuilder.newJob(c.class).withIdentity(name, group).build();
			scheduleBuilder.getJobDataMap().put("task", execTask);
			scheduleBuilder.getJobDataMap().put("taskId", id);
			CronScheduleBuilder scheduleBuilder1 = CronScheduleBuilder.cronSchedule(cron);
			trigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(scheduleBuilder1)
					.build();
			scheduler.scheduleJob(scheduleBuilder, trigger);
			scheduler.getListenerManager().addJobListener(jobListener);
		} else {
			CronScheduleBuilder scheduleBuilder2 = CronScheduleBuilder.cronSchedule(cron);
			trigger = (CronTrigger) trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder2)
					.build();
			scheduler.rescheduleJob(triggerKey, trigger);
		}

	}

	public void cB(String id) throws SchedulerException {
		String name = "task_" + id;
		String group = "group_" + id;
		Scheduler scheduler = (Scheduler) AppContextUtil.getInstance().getBean(Scheduler.class);
		JobKey jobKey = JobKey.jobKey(name, group);
		scheduler.pauseJob(jobKey);
	}

	public void cC(String id) throws SchedulerException {
		String name = "task_" + id;
		String group = "group_" + id;
		Scheduler scheduler = (Scheduler) AppContextUtil.getInstance().getBean(Scheduler.class);
		JobKey jobKey = JobKey.jobKey(name, group);
		scheduler.resumeJob(jobKey);
	}

	public void cD(String id) throws SchedulerException {
		String name = "task_" + id;
		String group = "group_" + id;
		Scheduler scheduler = (Scheduler) AppContextUtil.getInstance().getBean(Scheduler.class);
		JobKey jobKey = JobKey.jobKey(name, group);
		scheduler.deleteJob(jobKey);
	}

	public void cE(String id) throws SchedulerException {
		String name = "task_" + id;
		String group = "group_" + id;
		Scheduler scheduler = (Scheduler) AppContextUtil.getInstance().getBean(Scheduler.class);
		JobKey jobKey = JobKey.jobKey(name, group);
		scheduler.triggerJob(jobKey);
	}
}