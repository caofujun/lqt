package com.nis.questionnaire.controller;

import com.nis.access.entity.AcAccount;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.h;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.ai;
import com.nis.param.service.SysParamService;
import com.nis.questionnaire.entity.QsFlow;
import com.nis.questionnaire.entity.QsQuestionnaire;
import com.nis.questionnaire.entity.QsTopic;
import com.nis.questionnaire.service.QsFlowService;
import com.nis.questionnaire.service.QsQuestionnaireService;
import com.nis.questionnaire.service.QsTopicOptionService;
import com.nis.questionnaire.service.QsTopicService;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OpenQsFlowController extends BaseController {
	private static final Logger logger = Logger.getLogger(OpenQsFlowController.class);
	@Autowired
	private QsQuestionnaireService xi;
	@Autowired
	private QsTopicService xj;
	@Autowired
	private QsTopicOptionService xk;
	@Autowired
	private SysParamService j;
	@Autowired
	private QsFlowService xl;

	@RequestMapping({"/qsFlow/f_view/getPcLink"})
	public void a(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Long fId) {
		Result result = null;
		if (fId == null) {
			result = new Result("error", "流程问卷id为空!");
			this.a(response, result);
		} else {
			String path = this.j.findByParamCode(Param.NIS_HTTP_URL, (String) null, (String) null, (String) null);
			path = path + "qsFlow/view/phoneConfirm.shtml?fId=" + fId;
			result = new Result("success");
			result.setData(path);
			this.a(response, result);
		}
	}

	@RequestMapping({"/qsFlow/view/phoneConfirm"})
	public String Y(HttpServletRequest request, ModelMap modelMap, String fId) {
		modelMap.put("fId", fId);
		return "questionnaire/phoneConfirm";
	}

	@RequestMapping({"/qsFlow/f_view/questQcCode"})
	public void c(HttpServletRequest request, HttpServletResponse response, Long fId, String isHttps) {
		ServletOutputStream outPutStream = null;

		try {
			outPutStream = response.getOutputStream();
			String e = this.j.findByParamCode(Param.NIS_HTTP_URL, (String) null, (String) null, (String) null);
			if ("yes".equals(isHttps)) {
				e = e.replace("http", "https");
			}

			ai.a(e + "qsFlow/view/phoneConfirm.shtml?fId=" + fId, outPutStream, "png", Integer.valueOf(300),
					Integer.valueOf(300), (String) null);
		} catch (IOException arg14) {
			logger.error("生成二维码错误！");
			arg14.printStackTrace();
		} finally {
			if (outPutStream != null) {
				try {
					outPutStream.flush();
					outPutStream.close();
				} catch (IOException arg13) {
					;
				}
			}

		}

	}

	@RequestMapping({"/qsFlow/f_view/qsFlowShare"})
	public String a(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String fId,
			String isHttps) {
		QsFlow qs = this.xl.get(fId);
		modelMap.put("fId", fId);
		modelMap.put("isHttps", isHttps);
		modelMap.put("ext1", qs.getExt1());
		modelMap.put("pcLink", "/qsFlow/view/phoneConfirm.shtml?fId=" + fId);
		return "questionnaire/qsFlowShare";
	}

	@RequestMapping({"/qsFlow/f_view/viewQs"})
	public String Z(HttpServletRequest request, ModelMap modelMap, String qId) {
		if (ab.isEmpty(qId)) {
			modelMap.put("msg", "请求参数为空！");
			return "redirect:/404.jsp";
		} else {
			QsQuestionnaire quest = this.xi.get(qId);
			if (quest == null) {
				modelMap.put("msg", "问卷不存在！");
				return "redirect:/404.jsp";
			} else {
				List topicList = this.xj.findByQid(qId);
				Iterator arg6 = topicList.iterator();

				while (arg6.hasNext()) {
					QsTopic topic = (QsTopic) arg6.next();
					List optionList = this.xk.findByTid(topic.getTid());
					topic.setOptions(optionList);
				}

				quest.setQsTopicList(topicList);
				modelMap.put("questInfo", quest);
				modelMap.put("isView", h.fP.getCode());
				return "questionnaire/openWeb";
			}
		}
	}

	@RequestMapping({"/qsFlow/f_view/downQcCode"})
	public void c(HttpServletRequest request, HttpServletResponse response, Long fId) {
		ServletOutputStream outPutStream = null;

		try {
			response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode("微信二维码", "UTF-8"));
			outPutStream = response.getOutputStream();
			String e = this.j.findByParamCode(Param.NIS_HTTP_URL, (String) null, (String) null, (String) null);
			ai.a(e + "qsFlow/view/phoneConfirm.shtml?fId=" + fId, outPutStream, "png", Integer.valueOf(300),
					Integer.valueOf(300), (String) null);
		} catch (IOException arg13) {
			logger.error("生成二维码错误！");
			arg13.printStackTrace();
		} finally {
			if (outPutStream != null) {
				try {
					outPutStream.flush();
					outPutStream.close();
				} catch (IOException arg12) {
					;
				}
			}

		}

	}

	@RequestMapping({"/qsFlow/view/getWenJuanInfo"})
	@ResponseBody
	public void a(HttpServletRequest request, String fId, HttpServletResponse response) {
		Result result = null;
		AcAccount ac = (AcAccount) this.b(request);

		try {
			result = new Result();
			if (fId != null) {
				QsFlow e = this.xl.get(fId);
				result.setResult("success");
				result.setData(e);
				result.setExpandValue(ac.getUnitName());
			}
		} catch (Exception arg6) {
			result = new Result("error", "获取信息异常");
			arg6.printStackTrace();
		}

		this.a(response, result);
	}
}