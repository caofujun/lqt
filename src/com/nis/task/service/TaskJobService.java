package com.nis.task.service;

import com.nis.comm.entity.MyPage;
import com.nis.task.entity.TaskJob;
import java.util.List;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Component;

@Component
public interface TaskJobService {
	void save(TaskJob arg0);

	void delete(String arg0) throws SchedulerException;

	void cF(String arg0) throws SchedulerException;

	void update(TaskJob arg0) throws SchedulerException;

	void updateStatus(String arg0, Integer arg1);

	TaskJob get(String arg0);

	TaskJob findByLink(String arg0);

	MyPage<TaskJob> a(TaskJob arg0);

	void r(Integer arg0);

	List<TaskJob> findByStatus(Integer arg0);
}