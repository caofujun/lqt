package com.nis.task.core.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class b implements JobListener {
	private String name;

	public b() {
	}

	public b(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void jobToBeExecuted(JobExecutionContext inContext) {
	}

	public void jobExecutionVetoed(JobExecutionContext inContext) {
	}

	public void jobWasExecuted(JobExecutionContext inContext, JobExecutionException inException) {
	}
}