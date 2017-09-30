package com.nis.comm.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.utils.f;
import com.nis.param.service.SysParamService;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SystemTaskController extends BaseController {
	private static final Logger logger = Logger.getLogger(SystemTaskController.class);
	@Autowired
	private SysParamService j;

	@RequestMapping({"/f_task/system/logs/delete"})
	public void j(HttpServletRequest request, HttpServletResponse response) {
		Result result = new Result();
		logger.info("患者数据接口监控开始!请求时间：" + f.f(new Date()) + ";请求ip:" + request.getRemoteAddr());

		try {
			String e = request.getSession().getServletContext().getRealPath("/");
			int parentPathIndex = e.indexOf("wtpwebapps") > 0 ? e.indexOf("wtpwebapps") : e.indexOf("webapps");
			String logsNisPath = e.substring(0, parentPathIndex) + "/logs/nis/";
			this.a(logsNisPath, true);
			String logsTomcatPath = e.substring(0, parentPathIndex) + "/logs/";
			this.a(logsTomcatPath, false);
			logger.info("患者数据接口监控处理结束!【结果】success");
		} catch (Exception arg7) {
			logger.error("患者数据接口监控进程处理异常!【异常信息】" + arg7.getMessage());
			arg7.printStackTrace();
		}

		this.a(response, result);
	}

	private void a(String logsPath, boolean isDebug) {
		File logsDir = new File(logsPath);
		File logFile = null;
		if (logsDir.isDirectory()) {
			String logDays = this.j.findByParamCode(Param.NIS_SYSTEM_LOG_DAYS);
			String[] logFiles = logsDir.list();
			String[] arg9 = logFiles;
			int arg8 = logFiles.length;

			for (int arg7 = 0; arg7 < arg8; ++arg7) {
				String logFilePath = arg9[arg7];
				if (!isDebug || logFilePath.indexOf("debug") >= 0) {
					logFile = new File(logsPath + logFilePath);
					Calendar cd = Calendar.getInstance();
					cd.setTimeInMillis(logFile.lastModified());
					if (DateUtils.addDays(cd.getTime(), Integer.parseInt(logDays)).before(f.getCurDate())
							&& logFile.exists()) {
						logFile.delete();
					}
				}
			}
		}

	}
}