package com.nis.task.core.exec.task;

import com.nis.comm.enums.Param;
import com.nis.comm.enums.ah;
import com.nis.comm.utils.f;
import com.nis.log.service.SysLogService;
import com.nis.param.service.SysParamService;
import com.nis.task.core.base.a;
import com.nis.task.service.TaskJobLogService;
import java.util.Date;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskJobLogCleanTask extends a {
	private static final Logger c = Logger.getLogger(TaskJobLogCleanTask.class);
	@Autowired
	private TaskJobLogService xI;
	@Autowired
	private SysParamService j;
	@Autowired
	private SysLogService aV;

	public void execute(JobExecutionContext context) {
		if (c.isInfoEnabled()) {
			c.info("清空小于指定日期日志的定时任务");
		}

		String value = this.j.findByParamCode(Param.JOBLOG_SAVE_DAY);
		Date date = f.a(new Date(), -Integer.valueOf(value).intValue());
		this.xI.B(date);
		value = this.j.findByParamCode(Param.JOBLOG_SQL_SAVE_DAY);
		date = f.a(new Date(), -Integer.valueOf(value).intValue());
		this.aV.deleteSqlLtDate(ah.iI.getValue(), date);
	}
}