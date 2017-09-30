package com.nis.task.dao;

import com.nis.task.entity.TaskJob;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskJobDao {
	void save(TaskJob arg0);

	void update(TaskJob arg0);

	void updateWait(@Param("stopStatus") Integer arg0, @Param("status") Integer arg1);

	void updateStatus(@Param("id") String arg0, @Param("status") Integer arg1);

	void delete(@Param("id") String arg0);

	TaskJob get(@Param("id") String arg0);

	List<TaskJob> findTaskJob(TaskJob arg0);

	int findTaskJobCount(TaskJob arg0);

	TaskJob findByLink(@Param("link") String arg0);

	List<TaskJob> findByStatus(@Param("status") Integer arg0);

	List<TaskJob> findByProjectid(@Param("projectid") String arg0);
}