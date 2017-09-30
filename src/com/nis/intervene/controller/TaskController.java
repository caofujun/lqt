package com.nis.intervene.controller;

import com.nis.analysis.task.Gm004Task;
import com.nis.analysis.task.Gr018YsgrysKfzTask;
import com.nis.analysis.task.Gr018YsgrysPacsTask;
import com.nis.analysis.task.Gr018YsgrysTask;
import com.nis.analysis.task.Gr018YsgrysTwTask;
import com.nis.analysis.task.Gr019YsgrmxTask;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.comm.service.NisTaskService;
import com.nis.comm.utils.AppContextUtil;
import com.nis.comm.utils.f;
import com.nis.intervene.task.FxPatientTask;
import com.nis.mdr.task.Xn013LisbytTask;
import com.nis.zg.task.Zg027LisbbPpTask;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TaskController extends BaseController {
	private static final Logger logger = Logger.getLogger(TaskController.class);
	private static boolean sx = false;
	private static boolean sy = false;
	private static boolean sz = false;
	private static boolean sA = false;
	private static boolean sB = false;
	private static boolean sC = false;
	private static boolean sD = false;
	private static boolean sE = false;
	private static boolean sF = false;
	private static boolean sG = false;

	@RequestMapping({"/f_task/intervene"})
	public void x(HttpServletRequest request, HttpServletResponse response) {
		logger.info("风险分析进程处理开始!请求时间：" + f.f(new Date()) + ";请求ip:" + request.getRemoteAddr());
		Result result = null;
		if (sx) {
			result = new Result("error", "风险分析进程执行中，不支持重复执行");
			this.a(response, result);
			logger.info("风险分析进程处理结束!【结果】风险分析进程执行中，不支持重复执行");
		} else {
			try {
				sx = true;
				AppContextUtil e = AppContextUtil.getInstance();
				NisTaskService nisTask = (NisTaskService) e.getBean(FxPatientTask.class);
				nisTask.execute(result);
				sx = false;
			} catch (Exception arg8) {
				sx = false;
				result = new Result("error", "风险分析进程处理异常!");
				this.a(response, result);
				logger.error("风险分析进程处理异常!【异常信息】" + arg8.getMessage());
				arg8.printStackTrace();
			} finally {
				sx = false;
			}

			result = new Result("success");
			logger.info("风险分析进程处理结束!【结果】success");
			this.a(response, result);
		}
	}

	@RequestMapping({"/f_task/lisbbPp"})
	public void y(HttpServletRequest request, HttpServletResponse response) {
		logger.info("自动添加检验系统标本进程处理开始!请求时间：" + f.f(new Date()) + ";请求ip:" + request.getRemoteAddr());
		Result result = null;
		if (sy) {
			result = new Result("error", "自动添加检验系统标本进程执行中，不支持重复执行");
			this.a(response, result);
			logger.info("自动添加检验系统标本进程处理结束!【结果】自动添加检验系统标本进程执行中，不支持重复执行");
		} else {
			try {
				sy = true;
				AppContextUtil e = AppContextUtil.getInstance();
				NisTaskService nisTask = (NisTaskService) e.getBean(Zg027LisbbPpTask.class);
				nisTask.execute(result);
				sy = false;
			} catch (Exception arg8) {
				sy = false;
				result = new Result("error", "自动添加检验系统标本进程处理异常!");
				this.a(response, result);
				logger.error("自动添加检验系统标本进程处理异常!【异常信息】" + arg8.getMessage());
				arg8.printStackTrace();
			} finally {
				sy = false;
			}

			result = new Result("success");
			logger.info("自动添加检验系统标本进程处理结束!【结果】success");
			this.a(response, result);
		}
	}

	@RequestMapping({"/f_task/lisbyt"})
	public void z(HttpServletRequest request, HttpServletResponse response) {
		logger.info("自动添加检验系统病原体进程处理开始!请求时间：" + f.f(new Date()) + ";请求ip:" + request.getRemoteAddr());
		Result result = null;
		if (sz) {
			result = new Result("error", "自动添加检验系统病原体进程执行中，不支持重复执行");
			this.a(response, result);
			logger.info("自动添加检验系统病原体进程处理结束!【结果】自动添加检验系统病原体进程执行中，不支持重复执行");
		} else {
			try {
				sz = true;
				AppContextUtil e = AppContextUtil.getInstance();
				NisTaskService nisTask = (NisTaskService) e.getBean(Xn013LisbytTask.class);
				nisTask.execute(result);
				sz = false;
			} catch (Exception arg8) {
				sz = false;
				result = new Result("error", "自动添加检验系统病原体进程处理异常!");
				this.a(response, result);
				logger.error("自动添加检验系统病原体进程处理异常!【异常信息】" + arg8.getMessage());
				arg8.printStackTrace();
			} finally {
				sz = false;
			}

			result = new Result("success");
			logger.info("自动添加检验系统病原体进程处理结束!【结果】success");
			this.a(response, result);
		}
	}

	@RequestMapping({"/f_task/ysgrMx"})
	public void A(HttpServletRequest request, HttpServletResponse response) {
		logger.info("根据感染因素分析感染诊断进程处理开始!请求时间：" + f.f(new Date()) + ";请求ip:" + request.getRemoteAddr());
		Result result = null;
		if (sA) {
			result = new Result("error", "根据感染因素分析感染诊断进程执行中，不支持重复执行");
			this.a(response, result);
		} else {
			try {
				sA = true;
				AppContextUtil e = AppContextUtil.getInstance();
				NisTaskService nisTask = (NisTaskService) e.getBean(Gr019YsgrmxTask.class);
				nisTask.execute(result);
				sA = false;
			} catch (Exception arg8) {
				sA = false;
				result = new Result("error", "根据感染因素分析感染诊断进程处理异常!");
				this.a(response, result);
				logger.error("根据感染因素分析感染诊断进程处理异常!【异常信息】" + arg8.getMessage());
				arg8.printStackTrace();
			} finally {
				sA = false;
			}

			result = new Result("success");
			logger.info("根据感染因素分析感染诊断进程处理结束!【结果】success");
			this.a(response, result);
		}
	}

	@RequestMapping({"/f_task/ysgrys"})
	public void B(HttpServletRequest request, HttpServletResponse response) {
		logger.info("根据根据病程分析感染因素进程处理开始!请求时间：" + f.f(new Date()) + ";请求ip:" + request.getRemoteAddr());
		Result result = null;
		if (sB) {
			result = new Result("error", "根据根据病程分析感染因素进程执行中，不支持重复执行");
			this.a(response, result);
		} else {
			try {
				sB = true;
				AppContextUtil e = AppContextUtil.getInstance();
				NisTaskService nisTask = (NisTaskService) e.getBean(Gr018YsgrysTask.class);
				nisTask.execute(result);
				sB = false;
			} catch (Exception arg8) {
				sB = false;
				result = new Result("error", "根据根据病程分析感染因素进程处理异常!");
				this.a(response, result);
				logger.error("根据根据病程分析感染因素进程处理异常!【异常信息】" + arg8.getMessage());
				arg8.printStackTrace();
			} finally {
				sB = false;
			}

			result = new Result("success");
			logger.info("根据根据病程分析感染因素进程处理结束!【结果】success");
			this.a(response, result);
		}
	}

	@RequestMapping({"/f_task/ysgrysKfz"})
	public void C(HttpServletRequest request, HttpServletResponse response) {
		logger.info("康夫子-根据病程分析感染因素进程处理开始!请求时间：" + f.f(new Date()) + ";请求ip:" + request.getRemoteAddr());
		Result result = null;
		if (sC) {
			result = new Result("error", "康夫子-根据病程分析感染因素进程执行中，不支持重复执行");
			this.a(response, result);
		} else {
			try {
				sC = true;
				AppContextUtil e = AppContextUtil.getInstance();
				NisTaskService nisTask = (NisTaskService) e.getBean(Gr018YsgrysKfzTask.class);
				nisTask.execute(result);
				sC = false;
			} catch (Exception arg8) {
				sC = false;
				result = new Result("error", "康夫子-根据病程分析感染因素进程处理异常!");
				this.a(response, result);
				logger.error("康夫子-根据病程分析感染因素进程处理异常!【异常信息】" + arg8.getMessage());
				arg8.printStackTrace();
			} finally {
				sC = false;
			}

			result = new Result("success");
			logger.info("康夫子-根据病程分析感染因素进程处理结束!【结果】success");
			this.a(response, result);
		}
	}

	@RequestMapping({"/f_task/ysgrysPacs"})
	public void D(HttpServletRequest request, HttpServletResponse response) {
		logger.info("根据根据影像分析感染因素进程处理开始!请求时间：" + f.f(new Date()) + ";请求ip:" + request.getRemoteAddr());
		Result result = null;
		if (sD) {
			result = new Result("error", "根据根据影像分析感染因素进程执行中，不支持重复执行");
			this.a(response, result);
		} else {
			try {
				sD = true;
				AppContextUtil e = AppContextUtil.getInstance();
				NisTaskService nisTask = (NisTaskService) e.getBean(Gr018YsgrysPacsTask.class);
				nisTask.execute(result);
				sD = false;
			} catch (Exception arg8) {
				sD = false;
				result = new Result("error", "根据根据影像分析感染因素进程处理异常!");
				this.a(response, result);
				logger.error("根据根据影像分析感染因素进程处理异常!【异常信息】" + arg8.getMessage());
				arg8.printStackTrace();
			} finally {
				sD = false;
			}

			result = new Result("success");
			logger.info("根据根据影像分析感染因素进程处理结束!【结果】success");
			this.a(response, result);
		}
	}

	@RequestMapping({"/f_task/ysgrysTw"})
	public void E(HttpServletRequest request, HttpServletResponse response) {
		logger.info("根据根据体温分析感染因素进程处理开始!请求时间：" + f.f(new Date()) + ";请求ip:" + request.getRemoteAddr());
		Result result = null;
		if (sE) {
			result = new Result("error", "根据根据体温分析感染因素进程执行中，不支持重复执行");
			this.a(response, result);
		} else {
			try {
				sE = true;
				AppContextUtil e = AppContextUtil.getInstance();
				NisTaskService nisTask = (NisTaskService) e.getBean(Gr018YsgrysTwTask.class);
				nisTask.execute(result);
				sE = false;
			} catch (Exception arg8) {
				sE = false;
				result = new Result("error", "根据根据体温分析感染因素进程处理异常!");
				this.a(response, result);
				logger.error("根据根据体温分析感染因素进程处理异常!【异常信息】" + arg8.getMessage());
				arg8.printStackTrace();
			} finally {
				sE = false;
			}

			result = new Result("success");
			logger.info("根据根据体温分析感染因素进程处理结束!【结果】success");
			this.a(response, result);
		}
	}

	@RequestMapping({"/f_task/cdcDiagnosis"})
	public void F(HttpServletRequest request, HttpServletResponse response) {
		logger.info("传染病根据诊断分析感染因素进程处理开始!请求时间：" + f.f(new Date()) + ";请求ip:" + request.getRemoteAddr());
		Result result = null;
		if (sF) {
			result = new Result("error", "传染病根据诊断分析感染因素进程执行中，不支持重复执行");
			this.a(response, result);
		} else {
			try {
				sF = true;
				AppContextUtil e = AppContextUtil.getInstance();
				NisTaskService nisTask = (NisTaskService) e.getBean(Gr018YsgrysPacsTask.class);
				nisTask.execute(result);
				sF = false;
			} catch (Exception arg8) {
				sF = false;
				result = new Result("error", "传染病根据诊断分析感染因素进程处理异常!");
				this.a(response, result);
				logger.error("传染病根据诊断分析感染因素进程处理异常!【异常信息】" + arg8.getMessage());
				arg8.printStackTrace();
			} finally {
				sF = false;
			}

			result = new Result("success");
			logger.info("传染病根据诊断分析感染因素进程处理结束!【结果】success");
			this.a(response, result);
		}
	}

	@RequestMapping({"/f_task/jcmx"})
	public void G(HttpServletRequest request, HttpServletResponse response) {
		logger.info("自动计算三管和在院人数进程处理开始!请求时间：" + f.f(new Date()) + ";请求ip:" + request.getRemoteAddr());
		Result result = null;
		if (sG) {
			result = new Result("error", "自动计算三管和在院人数进程执行中，不支持重复执行");
			this.a(response, result);
			logger.info("自动计算三管和在院人数进程处理结束!【结果】自动计算三管和在院人数进程执行中，不支持重复执行");
		} else {
			try {
				sG = true;
				AppContextUtil e = AppContextUtil.getInstance();
				NisTaskService nisTask = (NisTaskService) e.getBean(Gm004Task.class);
				nisTask.execute(result);
				sG = false;
			} catch (Exception arg8) {
				sG = false;
				result = new Result("error", "自动计算三管和在院人数进程处理异常!");
				this.a(response, result);
				logger.error("自动计算三管和在院人数进程处理异常!【异常信息】" + arg8.getMessage());
				arg8.printStackTrace();
			} finally {
				sG = false;
			}

			result = new Result("success");
			logger.info("自动计算三管和在院人数进程处理结束!【结果】success");
			this.a(response, result);
		}
	}
}