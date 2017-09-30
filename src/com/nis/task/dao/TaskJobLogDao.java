package com.nis.task.dao;

import com.nis.task.entity.TaskJobLog;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskJobLogDao {
	void save(TaskJobLog arg0);

	void update(TaskJobLog arg0);

	void delete(@Param("id") String arg0);

	TaskJobLog get(@Param("id") String arg0);

	List<TaskJobLog> findTaskJobLog(TaskJobLog arg0);

	int findTaskJobLogCount(TaskJobLog arg0);

	void deleteLtDate(@Param("date") String arg0);
}