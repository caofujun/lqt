package com.nis.task.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.ac;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.f;
import com.nis.comm.utils.z;
import com.nis.task.dao.TaskJobLogDao;
import com.nis.task.entity.TaskJobLog;
import com.nis.task.service.TaskJobLogService;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskJobLogServiceImpl implements TaskJobLogService {
	@Autowired
	private TaskJobLogDao xU;

	public void save(TaskJobLog taskJobLog) {
		taskJobLog.setId(z.a(bg.nw));
		String result = taskJobLog.getResult();
		if (result.length() >= 4000) {
			result.substring(0, 4000);
		}

		taskJobLog.setResult(result);
		this.xU.save(taskJobLog);
	}

	public void delete(String id) {
		this.xU.delete(id);
	}

	public void update(TaskJobLog taskJobLog) {
		this.xU.update(taskJobLog);
	}

	public TaskJobLog get(String id) {
		TaskJobLog jobLog = this.xU.get(id);
		jobLog.setStatusname(ac.b(jobLog.getStatus()));
		return jobLog;
	}

	public MyPage<TaskJobLog> a(TaskJobLog taskJobLog) {
		int total = this.xU.findTaskJobLogCount(taskJobLog);
		List data = null;
		if (total > 0) {
			data = this.xU.findTaskJobLog(taskJobLog);
			Iterator arg4 = data.iterator();

			while (arg4.hasNext()) {
				TaskJobLog jobLog = (TaskJobLog) arg4.next();
				jobLog.setStatusname(ac.b(jobLog.getStatus()));
			}
		}

		return new MyPage(taskJobLog.getPage().intValue(), taskJobLog.getSize().intValue(), total, data);
	}

	public void B(Date date) {
		this.xU.deleteLtDate(f.c(date, "yyyy-MM-dd HH:mm:ss"));
	}
}