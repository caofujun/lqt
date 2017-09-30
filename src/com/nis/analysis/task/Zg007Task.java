package com.nis.analysis.task;

import com.nis.analysis.service.Zg007DictService;
import com.nis.comm.entity.Result;
import com.nis.comm.service.NisTaskService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Zg007Task implements NisTaskService {
	private static final Logger logger = Logger.getLogger(Zg007Task.class);
	private static final String cI = "执行【传染病根据诊断分析感染因素】定时任务";
	@Autowired
	private Zg007DictService X;

	public void execute(Result<?> result) {
		if (logger.isInfoEnabled()) {
			logger.info("执行【传染病根据诊断分析感染因素】定时任务,开始");
		}

		try {
			this.X.i();
		} catch (Exception arg2) {
			logger.error("执行【传染病根据诊断分析感染因素】定时任务,异常，异常信息：" + arg2.getMessage());
			arg2.printStackTrace();
		}

		if (logger.isInfoEnabled()) {
			logger.info("执行【传染病根据诊断分析感染因素】定时任务,结束");
		}

	}
}