package com.nis.questions.controller;

import com.nis.access.entity.AcAccount;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.KvEntity;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.au;
import com.nis.comm.enums.aw;
import com.nis.comm.enums.ax;
import com.nis.comm.enums.ay;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.EncryptUtils;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.z;
import com.nis.param.service.SysParamService;
import com.nis.questionnaire.entity.QsFlow;
import com.nis.questionnaire.entity.QsQuestionnaire;
import com.nis.questionnaire.service.QsFlowService;
import com.nis.questionnaire.service.QsQuestionnaireService;
import com.nis.questionnaire.service.QsTopicOptionService;
import com.nis.questionnaire.service.QsTopicService;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QsFlowController extends BaseController {
	private static final Logger c = Logger.getLogger(QsFlowController.class);
	@Autowired
	private QsFlowService xl;
	@Autowired
	private QsQuestionnaireService xi;
	@Autowired
	private QsTopicOptionService xz;
	@Autowired
	private QsTopicService xj;
	@Autowired
	private SysParamService j;

	@RequestMapping({"/qsQuestionnaire/f_view/flow"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		AcAccount acAccount = (AcAccount) this.b(request);
		modelMap.put("username", acAccount.getUsername());
		return "questionnaire/qsFlowList";
	}

	@RequestMapping({"/qsFlow/f_json/queryQsFlowEasyUi"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, QsFlow qsFlow, String defalultValue)
			throws UnsupportedEncodingException {
		AcAccount account = (AcAccount) this.b(request);
		qsFlow.setStatus(ax.lq.getCode().toString());
		qsFlow.setUnitId(account.getUnitId());
		qsFlow.setPublish(au.lg.getCode().toString());
		List list = this.xl.findQsFlowAll(qsFlow);
		ArrayList kvList = new ArrayList();
		if (ab.isNotEmpty(defalultValue)) {
			KvEntity qs = new KvEntity("", URLDecoder.decode(defalultValue, "utf-8"));
			if (ab.isEmpty(qsFlow.getFid())) {
				qs.setSelected(true);
			}

			kvList.add(qs);
		}

		Iterator arg8 = list.iterator();

		while (true) {
			while (arg8.hasNext()) {
				QsFlow qs1 = (QsFlow) arg8.next();
				if (qs1.getFid() != null && qs1.getFid().equals(qsFlow.getFid())) {
					KvEntity kv = new KvEntity(qs1.getFid(), qs1.getfName());
					kv.setSelected(true);
					kvList.add(kv);
				} else {
					kvList.add(new KvEntity(qs1.getFid(), qs1.getfName()));
				}
			}

			this.b(response, kvList);
			return;
		}
	}

	@RequestMapping({"/qsFlow/f_view/toadd"})
	public String q(HttpServletRequest request, ModelMap modelMap) {
		AcAccount acAccount = (AcAccount) this.b(request);
		QsQuestionnaire qsQuestionnaire = new QsQuestionnaire();

		try {
			qsQuestionnaire.setUnitId(acAccount.getUnitId());
			qsQuestionnaire.setStatus(ax.lq.getCode());
			qsQuestionnaire.setCatId(ay.lB.getValue());
			modelMap.put("emergencyList", this.xi.findQsQuestionnaireAll(qsQuestionnaire));
			qsQuestionnaire.setCatId(ay.lC.getValue());
			modelMap.put("guideList", this.xi.findQsQuestionnaireAll(qsQuestionnaire));
			qsQuestionnaire.setCatId(ay.lD.getValue());
			modelMap.put("triageList", this.xi.findQsQuestionnaireAll(qsQuestionnaire));
			qsQuestionnaire.setCatId(ay.lE.getValue());
			modelMap.put("satisfactionList", this.xi.findQsQuestionnaireAll(qsQuestionnaire));
		} catch (Exception arg5) {
			arg5.printStackTrace();
			c.error("获取信息异常!", arg5);
		}

		return "questionnaire/qsFlowAddOrEdit";
	}

	@RequestMapping({"/qsFlow/f_view/toedit"})
	public String c(HttpServletRequest request, ModelMap modelMap, String fid) {
		AcAccount acAccount = (AcAccount) this.b(request);
		QsQuestionnaire qsQuestionnaire = new QsQuestionnaire();

		try {
			qsQuestionnaire.setUnitId(acAccount.getUnitId());
			qsQuestionnaire.setStatus(ax.lq.getCode());
			qsQuestionnaire.setCatId(ay.lB.getValue());
			modelMap.put("emergencyList", this.xi.findQsQuestionnaireAll(qsQuestionnaire));
			qsQuestionnaire.setCatId(ay.lC.getValue());
			modelMap.put("guideList", this.xi.findQsQuestionnaireAll(qsQuestionnaire));
			qsQuestionnaire.setCatId(ay.lD.getValue());
			modelMap.put("triageList", this.xi.findQsQuestionnaireAll(qsQuestionnaire));
			qsQuestionnaire.setCatId(ay.lE.getValue());
			modelMap.put("satisfactionList", this.xi.findQsQuestionnaireAll(qsQuestionnaire));
			QsFlow e = this.xl.get(fid);
			String ids = e.getExt1();
			if (ab.isNotEmpty(ids)) {
				modelMap.put("ids", ids);
			}

			modelMap.put("qsFlow", e);
			modelMap.put("endTime", f.formatDate(e.getEndDate()));
		} catch (Exception arg7) {
			arg7.printStackTrace();
			c.error("获取信息异常!", arg7);
		}

		return "questionnaire/qsFlowAddOrEdit";
	}

	@RequestMapping({"/qsFlow/f_json/pageQuery"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, QsFlow qsFlow) {
		AcAccount account = (AcAccount) this.b(request);
		MyPage page = null;

		try {
			String e = qsFlow.getfName();
			if (e != null) {
				qsFlow.setfName(e.trim());
			}

			qsFlow.setUnitId(account.getUnitId());
			qsFlow.setStatus(aw.ln.getCode().toString());
			page = this.xl.a(qsFlow);
		} catch (Exception arg6) {
			arg6.printStackTrace();
		}

		this.a(response, page);
	}

	@RequestMapping({"/qsFlow/f_json/save"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, QsFlow qsFlow, Long emergency, Long guide,
			Long triage, Long zh_satisfaction, Long fid) {
		AcAccount account = (AcAccount) this.b(request);
		Result result = new Result();

		try {
			String e = emergency + "#" + guide + "#" + triage + "#" + zh_satisfaction;
			if (fid == null) {
				qsFlow.setFid(z.a(bg.nG));
				qsFlow.setCreateUser(account.getUsername());
				qsFlow.setCreateTime(new Date());
				qsFlow.setUnitId(account.getUnitId());
				qsFlow.setPublish(au.lh.getCode().toString());
				qsFlow.setStatus(ax.lq.getCode().toString());
				qsFlow.setExt1(e);
				this.xl.save(qsFlow);
			} else {
				qsFlow.setExt1(e);
				this.xl.update(qsFlow);
			}

			result.setMsg("操作成功！");
			result.setResult("success");
		} catch (Exception arg11) {
			arg11.printStackTrace();
		}

		this.a(response, result);
	}

	@RequestMapping({"/qsFlow/f_json/delete"})
	@ResponseBody
	public void aD(HttpServletRequest request, HttpServletResponse response, String fid) {
		Result result = new Result();

		try {
			if (fid != null) {
				this.xl.delete(fid);
				result.setResult("success");
			} else {
				result.setMsg("参数错误！");
			}
		} catch (Exception arg5) {
			arg5.printStackTrace();
		}

		this.a(response, result);
	}

	@RequestMapping({"/qsFlow/f_json/publish"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, QsFlow qsFlow) {
		Result result = new Result();

		try {
			this.xl.update(qsFlow);
			result.setResult("success");
		} catch (Exception arg5) {
			arg5.printStackTrace();
			result.setMsg("参数错误！");
		}

		this.a(response, result);
	}

	@RequestMapping({"/qsFlow/view/openFlow4Wap/{pid}"})
	public String c(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String fid,
			@PathVariable("pid") String pid, String nowStep) {
		try {
			String e = this.xl.get(fid).getExt1();
			String[] ids = null;
			if (!ab.isNotEmpty(e)) {
				return "error";
			}

			ids = e.split("#");
			modelMap.put("fid", fid);
			String path = this.j.findByParamCode(Param.NIS_HTTP_URL, (String) null, (String) null, (String) null);
			if (ab.isEmpty(nowStep)) {
				modelMap.put("title", "您是急诊患者还是门诊患者?");
				modelMap.put("answer1", "急诊");
				modelMap.put("answer1url",
						path + "qsQuestionnaire/view/openWap.shtml?qId=" + EncryptUtils.ad("#" + ids[0])
								+ "&nowQsType=emergency&pid=" + pid + "&fid=" + fid + "&nowStep=step1");
				modelMap.put("answer2", "门诊");
				modelMap.put("answer2url",
						path + "qsFlow/view/openFlow4Wap/" + pid + ".shtml?nowStep=step1&fid=" + fid);
			}

			if (ab.isNotEmpty(nowStep) && nowStep.equals("step1")) {
				modelMap.put("title", "您是否经过导诊?");
				modelMap.put("answer1", "是");
				modelMap.put("answer1url",
						path + "qsQuestionnaire/view/openWap.shtml?qId=" + EncryptUtils.ad("#" + ids[1])
								+ "&nowQsType=guide&pid=" + pid + "&fid=" + fid + "&nowStep=step2");
				modelMap.put("answer2", "否");
				modelMap.put("answer2url",
						path + "qsFlow/view/openFlow4Wap/" + pid + ".shtml?nowStep=step2&fid=" + fid);
			}

			if (ab.isNotEmpty(nowStep) && nowStep.equals("step2")) {
				modelMap.put("title", "您是否经过分诊?");
				modelMap.put("answer1", "是");
				modelMap.put("answer1url",
						path + "qsQuestionnaire/view/openWap.shtml?qId=" + EncryptUtils.ad("#" + ids[2])
								+ "&nowQsType=triage&pid=" + pid + "&fid=" + fid + "&nowStep=step3");
				modelMap.put("answer2", "否");
				modelMap.put("answer2url",
						path + "qsFlow/view/openFlow4Wap/" + pid + ".shtml?nowStep=step3&fid=" + fid);
			}

			if (ab.isNotEmpty(nowStep) && nowStep.equals("step3")) {
				response.sendRedirect(path + "qsQuestionnaire/view/openWap.shtml?qId=" + EncryptUtils.ad("#" + ids[3])
						+ "&nowQsType=zh_satisfaction&pid=" + pid + "&fid=" + fid);
			}
		} catch (Exception arg9) {
			arg9.printStackTrace();
			c.error("获取信息异常!", arg9);
		}

		return "questionnaire/openFlow4Wap";
	}
}