package com.nis.task.core.base;

import com.nis.task.core.base.b;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class c extends QuartzJobBean {
	private b xN;

	public void setTask(b task) {
		this.xN = task;
	}

	public b getTask() {
		return this.xN;
	}

	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		this.xN.execute(context);
	}
}