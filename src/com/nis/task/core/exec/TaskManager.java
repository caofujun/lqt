package com.nis.task.core.exec;

import com.nis.comm.enums.Param;
import com.nis.comm.enums.ad;
import com.nis.param.service.SysParamService;
import com.nis.task.core.exec.JobService;
import com.nis.task.core.exec.task.JudgeAnalysisStatusTask;
import com.nis.task.core.exec.task.MainTask;
import com.nis.task.core.exec.task.TaskJobLogCleanTask;
import com.nis.task.core.listener.a;
import com.nis.task.service.TaskJobService;
import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskManager {
	private static final Logger c = Logger.getLogger(TaskManager.class);
	@Autowired
	private JobService xO;
	@Autowired
	private TaskJobService aU;
	@Autowired
	private SysParamService j;
	@Autowired
	private MainTask xP;
	@Autowired
	private TaskJobLogCleanTask xQ;
	@Autowired
	private JudgeAnalysisStatusTask xR;

	public void init() {
		this.aU.r(ad.ig.getCode());

		try {
			this.xO.a("main", this.j.findByParamCode(Param.TASK_MAIN_CRON), this.xP, new a("main"));
		} catch (SchedulerException arg3) {
			c.error("添加系统的定时任务异常: " + arg3.getMessage(), arg3);
		}

		try {
			this.xO.a("taskJobLogClean", this.j.findByParamCode(Param.JOBLOG_CLEAN_CRON), this.xQ,
					new a("taskJobLogClean"));
		} catch (SchedulerException arg2) {
			c.error("添加清除任务日志的定时任务异常: " + arg2.getMessage(), arg2);
		}

		try {
			this.xO.a("judgeAnalysisStatusTask", "0 0/2 * * * ?", this.xR, new a("judgeAnalysisStatusTask"));
		} catch (SchedulerException arg1) {
			c.error("添加判断分析任务执行状态任务异常: " + arg1.getMessage(), arg1);
		}

	}
}