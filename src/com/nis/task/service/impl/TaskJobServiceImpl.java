package com.nis.task.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.ad;
import com.nis.comm.enums.bg;
import com.nis.comm.enums.h;
import com.nis.comm.utils.z;
import com.nis.task.core.exec.JobService;
import com.nis.task.dao.TaskJobDao;
import com.nis.task.entity.TaskJob;
import com.nis.task.service.TaskJobService;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskJobServiceImpl implements TaskJobService {
	@Autowired
	private TaskJobDao xV;
	@Autowired
	private JobService xO;

	public void save(TaskJob taskJob) {
      if(taskJob.getStatus().intValue() != com.nis.comm.enums.ad.iff.getCode().intValue()) {
         taskJob.setStatus(ad.ig.getCode());
      }

      if(taskJob.getIsfailmail() == null) {
         taskJob.setIsfailmail(h.fQ.getCode());
      }

      taskJob.setId(z.a(bg.nv));
      taskJob.setAddtime(new Date());
      this.xV.save(taskJob);
   }

	public void delete(String id) throws SchedulerException {
		this.xO.cD(id);
		this.xV.delete(id);
	}

	public void cF(String projectid) throws SchedulerException {
		List list = this.findByProjectid(projectid);
		Iterator arg3 = list.iterator();

		while (arg3.hasNext()) {
			TaskJob taskJob = (TaskJob) arg3.next();
			this.delete(taskJob.getId());
		}

	}

	private List<TaskJob> findByProjectid(String projectid) {
		return this.xV.findByProjectid(projectid);
	}

	public void update(TaskJob taskJob) throws SchedulerException {
      if(taskJob.getStatus().intValue() == com.nis.comm.enums.ad.iff.getCode().intValue()) {
         this.xO.cD(taskJob.getId().toString());
      } else {
         taskJob.setStatus(ad.ig.getCode());
      }

      this.xV.update(taskJob);
   }

	public void updateStatus(String id, Integer status) {
		this.xV.updateStatus(id, status);
	}

	public TaskJob get(String id) {
		return this.xV.get(id);
	}

	public MyPage<TaskJob> a(TaskJob taskJob) {
		int total = this.xV.findTaskJobCount(taskJob);
		List data = null;
		if (total > 0) {
			data = this.xV.findTaskJob(taskJob);
			Iterator arg4 = data.iterator();

			while (arg4.hasNext()) {
				TaskJob job = (TaskJob) arg4.next();
				job.setStatusname(ad.b(job.getStatus()));
				job.setIsfailmailname(h.b(job.getIsfailmail()));
			}
		}

		return new MyPage(taskJob.getPage().intValue(), taskJob.getSize().intValue(), total, data);
	}

	public void r(Integer status) {
      this.xV.updateWait(com.nis.comm.enums.ad.iff.getCode(), status);
   }

	public List<TaskJob> findByStatus(Integer status) {
		return this.xV.findByStatus(status);
	}

	public TaskJob findByLink(String link) {
		return this.xV.findByLink(link);
	}
}