package com.nis.task.service;

import com.nis.comm.entity.MyPage;
import com.nis.task.entity.TaskJobLog;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public interface TaskJobLogService {
	void save(TaskJobLog arg0);

	void delete(String arg0);

	void update(TaskJobLog arg0);

	TaskJobLog get(String arg0);

	MyPage<TaskJobLog> a(TaskJobLog arg0);

	void B(Date arg0);
}