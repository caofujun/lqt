package com.nis.task.core.exec.task;

import com.nis.analysis.service.SysJudgeLogService;
import com.nis.task.core.base.a;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JudgeAnalysisStatusTask extends a {
	private static final Logger c = Logger.getLogger(JudgeAnalysisStatusTask.class);
	@Autowired
	private SysJudgeLogService J;

	public void execute(JobExecutionContext context) {
		try {
			this.J.e();
		} catch (Exception arg2) {
			c.error("判断分析任务执行状态任务执行异常：", arg2);
			arg2.printStackTrace();
		}

	}
}