package com.nis.questionnaire.controller;

import com.nis.access.entity.AcAccount;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.h;
import com.nis.comm.utils.EncryptUtils;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.ai;
import com.nis.dict.service.SysDictService;
import com.nis.organization.service.DepService;
import com.nis.param.service.SysParamService;
import com.nis.questionnaire.entity.QsQuestionnaire;
import com.nis.questionnaire.entity.QsTopic;
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
public class OpenQsQuestionnaireController extends BaseController {
	private static final Logger logger = Logger.getLogger(OpenQsQuestionnaireController.class);
	@Autowired
	private QsQuestionnaireService xi;
	@Autowired
	private QsTopicService xj;
	@Autowired
	private QsTopicOptionService xk;
	@Autowired
	private SysParamService j;
	@Autowired
	private DepService e;
	@Autowired
	private SysDictService p;

	@RequestMapping({"/qsQuestionnaire/f_view/questShare"})
	public String b(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String qId,
			String isHttps) {
		AcAccount ac = (AcAccount) this.b(request);
		String encryptQid = "#" + qId;
		encryptQid = EncryptUtils.ad(encryptQid);
		modelMap.put("qId", qId);
		modelMap.put("isHttps", isHttps);
		modelMap.put("pcLink", "/qsQuestionnaire/view/openWeb.shtml?qId=" + encryptQid);
		return "questionnaire/questShare";
	}

	@RequestMapping({"/qsQuestionnaire/f_view/getPcLink"})
	public void a(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Long qId) {
		Result result = null;
		if (qId == null) {
			result = new Result("error", "问卷id为空!");
			this.a(response, result);
		} else {
			String path = this.j.findByParamCode(Param.NIS_HTTP_URL, (String) null, (String) null, (String) null);
			String encryptQid = "#" + qId;
			encryptQid = EncryptUtils.ad(encryptQid);
			encryptQid = path + "qsQuestionnaire/view/openWeb.shtml?qId=" + encryptQid;
			result = new Result("success");
			result.setData(encryptQid);
			this.a(response, result);
		}
	}

	@RequestMapping({"/qsQuestionnaire/f_view/questQcCode"})
	public void b(HttpServletRequest request, HttpServletResponse response, Long qId, String depNo, String isHttps) {
		ServletOutputStream outPutStream = null;

		try {
			outPutStream = response.getOutputStream();
			String e = this.j.findByParamCode(Param.NIS_HTTP_URL, (String) null, (String) null, (String) null);
			if ("yes".equals(isHttps)) {
				e = e.replace("http", "https");
			}

			String encryptQid = "#" + qId;
			encryptQid = EncryptUtils.ad(encryptQid);
			String depUrl = ab.isEmpty(depNo) ? "" : "&depNo=" + depNo;
			ai.a(e + "qsQuestionnaire/view/openWap.shtml?qId=" + encryptQid + depUrl, outPutStream, "png",
					Integer.valueOf(300), Integer.valueOf(300), (String) null);
		} catch (IOException arg17) {
			logger.error("生成二维码错误！");
			arg17.printStackTrace();
		} finally {
			if (outPutStream != null) {
				try {
					outPutStream.flush();
					outPutStream.close();
				} catch (IOException arg16) {
					;
				}
			}

		}

	}

	@RequestMapping({"/qsQuestionnaire/f_view/downQcCode"})
	public void c(HttpServletRequest request, HttpServletResponse response, Long qId) {
		ServletOutputStream outPutStream = null;

		try {
			response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode("微信二维码", "UTF-8"));
			outPutStream = response.getOutputStream();
			String e = this.j.findByParamCode(Param.NIS_HTTP_URL, (String) null, (String) null, (String) null);
			String encryptQid = "#" + qId;
			encryptQid = EncryptUtils.ad(encryptQid);
			ai.a(e + "qsQuestionnaire/view/openWap.shtml?qId=" + encryptQid, outPutStream, "png", Integer.valueOf(300),
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

	@RequestMapping({"/qsQuestionnaire/f_view/viewQs"})
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

	@RequestMapping({"/qsQuestionnaire/view/openWeb"})
	public String D(HttpServletRequest request, ModelMap modelMap, String qId, String depNo) {
		Result result = this.cx(qId);
		if ("error".equals(result.getResult())) {
			modelMap.put("msg", result.getMsg());
			return "redirect:/404.jsp";
		} else {
			modelMap.put("questInfo", result.getData());
			modelMap.put("depNo", depNo);
			return "questionnaire/openWeb";
		}
	}

	@RequestMapping({"/qsQuestionnaire/view/openWap"})
	public String k(HttpServletRequest request, ModelMap modelMap, String qId, String depNo, String pid) {
		Result result = this.cx(qId);
		if ("error".equals(result.getResult())) {
			modelMap.put("msg", result.getMsg());
			return "redirect:/404.jsp";
		} else {
			modelMap.put("questInfo", result.getData());
			modelMap.put("depNo", depNo);
			modelMap.put("pid", pid);
			modelMap.put("nowQsType", request.getParameter("nowQsType"));
			modelMap.put("fid", request.getParameter("fid"));
			modelMap.put("nowStep", request.getParameter("nowStep"));
			return "questionnaire/openWap";
		}
	}

	private Result<QsQuestionnaire> cx(String qId) {
		Result result = new Result();
		if (ab.isEmpty(qId)) {
			result.setResult("error");
			result.setMsg("请求参数为空！");
			return result;
		} else {
			String trueQid = null;

			try {
				trueQid = this.cy(qId);
			} catch (Exception arg8) {
				;
			}

			if (ab.isEmpty(trueQid)) {
				result.setResult("error");
				result.setMsg("为攻击性请求！不予处理！或请联系管理员");
				return result;
			} else {
				QsQuestionnaire quest = this.xi.get(trueQid);
				if (quest == null) {
					result.setResult("error");
					result.setMsg("问卷不存在！");
					return result;
				} else {
					List topicList = this.xj.findByQid(trueQid);
					Iterator arg6 = topicList.iterator();

					while (arg6.hasNext()) {
						QsTopic topic = (QsTopic) arg6.next();
						List optionList = this.xk.findByTid(topic.getTid());
						topic.setOptions(optionList);
					}

					quest.setQsTopicList(topicList);
					result.setResult("success");
					result.setData(quest);
					return result;
				}
			}
		}
	}

	private String cy(String qId) {
		String trueQid = EncryptUtils.af(qId);
		trueQid = trueQid.substring(1);
		return trueQid;
	}

	@RequestMapping({"/qsQuestionnaire/view/getWenJuanInfo"})
	@ResponseBody
	public void a(HttpServletRequest request, String qId, HttpServletResponse response) {
		Result result = null;
		AcAccount ac = (AcAccount) this.b(request);

		try {
			result = new Result();
			if (qId != null) {
				QsQuestionnaire e = this.xi.get(qId);
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