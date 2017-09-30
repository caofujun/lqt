package com.nis.analysis.task;

import com.nis.analysis.entity.SysJudgeLog;
import com.nis.analysis.service.AnalysisBcKfzService;
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
public class Gr018YsgrysKfzTask implements NisTaskService {
	private static final Logger logger = Logger.getLogger(Gr018YsgrysKfzTask.class);
	private static final String cI = "执行【根据病程分析感染因素】定时任务";
	@Autowired
	private AnalysisBcKfzService cJ;

	public void execute(Result<?> result) {
		if (logger.isInfoEnabled()) {
			logger.info("执行【根据病程分析感染因素】定时任务,开始");
		}

		try {
			String e = z.a(bg.my);
			SysJudgeLog sysJudgeLog = new SysJudgeLog();
			sysJudgeLog.setId(e);
			sysJudgeLog.setStartTime(new Date());
			sysJudgeLog.setJudgeCode(ae.im.getCode());
			sysJudgeLog.setStatus("0");
			this.cJ.a(true, e);
		} catch (Exception arg3) {
			logger.error("执行【根据病程分析感染因素】定时任务,异常，异常信息：" + arg3.getMessage());
			arg3.printStackTrace();
		}

		if (logger.isInfoEnabled()) {
			logger.info("执行【根据病程分析感染因素】定时任务,结束");
		}

	}
}