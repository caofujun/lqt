package com.nis.task.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.enums.h;
import com.nis.comm.utils.z;
import com.nis.task.dao.TaskProjectDao;
import com.nis.task.entity.TaskProject;
import com.nis.task.service.TaskJobService;
import com.nis.task.service.TaskProjectService;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskProjectServiceImpl implements TaskProjectService {
	@Autowired
	private TaskProjectDao xW;
	@Autowired
	private TaskJobService aU;

	public void save(TaskProject taskProject) {
		taskProject.setAddtime(new Date());
		taskProject.setId(z.a(bg.nx));
		if (taskProject.getIsrecemail() == null) {
			taskProject.setIsrecemail(h.fQ.getCode());
		}

		this.xW.save(taskProject);
	}

	public void delete(String id) throws SchedulerException {
		this.xW.delete(id);
		this.aU.cF(id);
	}

	public void update(TaskProject taskProject) {
		this.xW.update(taskProject);
	}

	public TaskProject get(String id) {
		return this.xW.get(id);
	}

	public MyPage<TaskProject> b(TaskProject taskProject) {
		int total = this.xW.findTaskProjectCount(taskProject);
		List data = null;
		if (total > 0) {
			data = this.xW.findTaskProject(taskProject);
			Iterator arg4 = data.iterator();

			while (arg4.hasNext()) {
				TaskProject project = (TaskProject) arg4.next();
				project.setIsrecemailname(h.b(project.getIsrecemail()));
			}
		}

		return new MyPage(taskProject.getPage().intValue(), taskProject.getSize().intValue(), total, data);
	}
}