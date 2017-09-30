package com.nis.zg.task;

import com.nis.comm.entity.Result;
import com.nis.comm.service.NisTaskService;
import com.nis.zg.service.Zg027LisbbPpService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Zg027LisbbPpTask implements NisTaskService {
	private static final Logger logger = Logger.getLogger(Zg027LisbbPpTask.class);
	private static final String cI = "执行【自动添加检验系统标本】定时任务";
	@Autowired
	private Zg027LisbbPpService bC;

	public void execute(Result<?> result) {
		if (logger.isInfoEnabled()) {
			logger.info("执行【自动添加检验系统标本】定时任务,开始");
		}

		try {
			this.bC.aB();
		} catch (Exception arg2) {
			logger.error("执行【自动添加检验系统标本】定时任务,异常，异常信息：" + arg2.getMessage());
			arg2.printStackTrace();
		}

		if (logger.isInfoEnabled()) {
			logger.info("执行【自动添加检验系统标本】定时任务,结束");
		}

	}
}