package com.nis.analysis.task;

import com.nis.analysis.entity.SysJudgeLog;
import com.nis.analysis.service.SysJudgeLogService;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.ae;
import com.nis.comm.enums.bg;
import com.nis.comm.service.NisTaskService;
import com.nis.comm.utils.f;
import com.nis.comm.utils.z;
import com.nis.icu.service.Gm004JcmxService;
import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Gm004Task implements NisTaskService {
	private static final Logger logger = Logger.getLogger(Gm004Task.class);
	private static final String cI = "执行【三管计算】定时任务";
	@Autowired
	private Gm004JcmxService bP;
	@Autowired
	private SysJudgeLogService J;

	public void execute(Result<?> result) {
		if (logger.isInfoEnabled()) {
			logger.info("执行【三管计算】定时任务,开始");
		}

		try {
			String e = f.formatDate(f.a(new Date(), -1));
			String endDate = f.formatDate(new Date());
			String id = z.a(bg.my);
			SysJudgeLog sysJudgeLog = new SysJudgeLog();
			sysJudgeLog.setId(id);
			sysJudgeLog.setStartTime(new Date());
			sysJudgeLog.setJudgeCode(ae.is.getCode());
			sysJudgeLog.setStatus("0");
			this.J.save(sysJudgeLog);
			this.bP.o(e, endDate, id);
		} catch (Exception arg5) {
			logger.error("执行【三管计算】定时任务,异常，异常信息：" + arg5.getMessage());
			arg5.printStackTrace();
		}

		if (logger.isInfoEnabled()) {
			logger.info("执行【三管计算】定时任务,结束");
		}

	}
}