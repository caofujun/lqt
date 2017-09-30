package com.nis.task.dao;

import com.nis.task.entity.TaskProject;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskProjectDao {
	void save(TaskProject arg0);

	void update(TaskProject arg0);

	void delete(@Param("id") String arg0);

	TaskProject get(@Param("id") String arg0);

	List<TaskProject> findTaskProject(TaskProject arg0);

	int findTaskProjectCount(TaskProject arg0);
}