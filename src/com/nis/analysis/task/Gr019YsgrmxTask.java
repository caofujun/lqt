package com.nis.analysis.task;

import com.nis.analysis.entity.SysJudgeLog;
import com.nis.analysis.service.AnalysisModelService;
import com.nis.analysis.service.SysJudgeLogService;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.ae;
import com.nis.comm.enums.bg;
import com.nis.comm.service.NisTaskService;
import com.nis.comm.utils.z;
import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Gr019YsgrmxTask implements NisTaskService {
	private static final Logger logger = Logger.getLogger(Gr019YsgrmxTask.class);
	private static final String cI = "执行【根据感染因素分析感染诊断】定时任务";
	@Autowired
	private AnalysisModelService D;
	@Autowired
	private SysJudgeLogService J;

	public void execute(Result<?> result) {
		if (logger.isInfoEnabled()) {
			logger.info("执行【根据感染因素分析感染诊断】定时任务,开始");
		}

		try {
			String e = z.a(bg.my);
			SysJudgeLog sysJudgeLog = new SysJudgeLog();
			sysJudgeLog.setId(e);
			sysJudgeLog.setStartTime(new Date());
			sysJudgeLog.setJudgeCode(ae.it.getCode());
			sysJudgeLog.setStatus("0");
			this.J.save(sysJudgeLog);
			this.D.n(e);
		} catch (Exception arg3) {
			logger.error("执行【根据感染因素分析感染诊断】定时任务,异常，异常信息：" + arg3.getMessage());
			arg3.printStackTrace();
		}

		if (logger.isInfoEnabled()) {
			logger.info("执行【根据感染因素分析感染诊断】定时任务,结束");
		}

	}
}