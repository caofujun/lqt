package com.nis.intervene.task;

import com.nis.comm.entity.Result;
import com.nis.comm.service.NisTaskService;
import com.nis.intervene.service.FxPatientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FxPatientTask implements NisTaskService {
	private static final Logger logger = Logger.getLogger(FxPatientTask.class);
	private static final String cI = "执行【风险分析】定时任务";
	@Autowired
	private FxPatientService sr;

	public void execute(Result<?> result) {
		if (logger.isInfoEnabled()) {
			logger.info("执行【风险分析】定时任务,开始");
		}

		try {
			this.sr.F();
		} catch (Exception arg2) {
			logger.error("执行【风险分析】定时任务,异常，异常信息：" + arg2.getMessage());
			arg2.printStackTrace();
		}

		if (logger.isInfoEnabled()) {
			logger.info("执行【风险分析】定时任务,结束");
		}

	}
}