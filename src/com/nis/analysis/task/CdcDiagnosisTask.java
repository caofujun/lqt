package com.nis.analysis.task;

import com.nis.analysis.service.AnalysisCdcZdService;
import com.nis.comm.entity.Result;
import com.nis.comm.service.NisTaskService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CdcDiagnosisTask implements NisTaskService {
	private static final Logger logger = Logger.getLogger(CdcDiagnosisTask.class);
	private static final String cI = "执行【传染病根据诊断分析感染因素】定时任务";
	@Autowired
	private AnalysisCdcZdService B;

	public void execute(Result<?> result) {
		if (logger.isInfoEnabled()) {
			logger.info("执行【传染病根据诊断分析感染因素】定时任务,开始");
		}

		try {
			this.B.d(true);
		} catch (Exception arg2) {
			logger.error("执行【传染病根据诊断分析感染因素】定时任务,异常，异常信息：" + arg2.getMessage());
			arg2.printStackTrace();
		}

		if (logger.isInfoEnabled()) {
			logger.info("执行【传染病根据诊断分析感染因素】定时任务,结束");
		}

	}
}