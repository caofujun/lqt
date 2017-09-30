package com.nis.task.service;

import com.nis.comm.entity.MyPage;
import com.nis.task.entity.TaskProject;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Component;

@Component
public interface TaskProjectService {
	void save(TaskProject arg0);

	void delete(String arg0) throws SchedulerException;

	void update(TaskProject arg0);

	TaskProject get(String arg0);

	MyPage<TaskProject> b(TaskProject arg0);
}