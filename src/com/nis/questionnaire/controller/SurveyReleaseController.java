package com.nis.questionnaire.controller;

import com.nis.access.entity.AcAccount;
import com.nis.access.entity.AcRole;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.LoginUser;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.ax;
import com.nis.comm.utils.EncryptUtils;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.ai;
import com.nis.organization.entity.Dep;
import com.nis.organization.service.DepService;
import com.nis.param.service.SysParamService;
import com.nis.questionnaire.entity.QsQuestionnaire;
import com.nis.questionnaire.service.QsQuestionnaireService;
import com.nis.user.service.UserService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SurveyReleaseController extends BaseController {
	private static final Logger logger = Logger.getLogger(SurveyReleaseController.class);
	@Autowired
	private SysParamService j;
	@Autowired
	private UserService cU;
	@Autowired
	private DepService e;
	@Autowired
	private QsQuestionnaireService xi;

	@RequestMapping({"/surveyRelease/f_view/index"})
	public String b(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		AcAccount ac = (AcAccount) this.b(request);
		QsQuestionnaire qsQuestionnaire = new QsQuestionnaire();
		qsQuestionnaire.setUnitId(ac.getUnitId());
		qsQuestionnaire.setStatus(ax.lq.getCode());

		try {
			List e = this.xi.findQsQuestionnaireAll(qsQuestionnaire);
			if (e.size() < 1) {
				modelMap.put("urlDemo", "javascript:void();");
				modelMap.put("urlDemoInfo", "当前尚未添加问卷，请联系管理员！");
				return "surveyRelease/surveyReleaseIndex";
			}

			String qid = null;
			String url = "/qsQuestionnaire/f_view/viewQs.shtml";
			qid = ((QsQuestionnaire) e.get(0)).getQid();
			url = url + "?qId=" + qid;
			modelMap.put("urlDemo", url);
		} catch (Exception arg8) {
			arg8.printStackTrace();
		}

		return "surveyRelease/surveyReleaseIndex";
	}

	@RequestMapping({"/surveyRelease/f_view/loginQcCode"})
	public void U(HttpServletRequest request, HttpServletResponse response) {
		ServletOutputStream outPutStream = null;

		try {
			outPutStream = response.getOutputStream();
			String e = this.j.findByParamCode(Param.NIS_HTTP_URL, (String) null, (String) null, (String) null);
			ai.a(e + "surveyRelease/view/userLogin4Wap.shtml", outPutStream, "png", Integer.valueOf(300),
					Integer.valueOf(300), (String) null);
		} catch (IOException arg12) {
			logger.error("生成二维码错误！");
			arg12.printStackTrace();
		} finally {
			if (outPutStream != null) {
				try {
					outPutStream.flush();
					outPutStream.close();
				} catch (IOException arg11) {
					;
				}
			}

		}

	}

	@RequestMapping({"/surveyRelease/f_view/questQcCode"})
	public void a(HttpServletRequest request, HttpServletResponse response, Long qid, String depNo, Long pid) {
		ServletOutputStream outPutStream = null;

		try {
			outPutStream = response.getOutputStream();
			String e = this.j.findByParamCode(Param.NIS_HTTP_URL, (String) null, (String) null, (String) null);
			String encryptQid = "#" + qid;
			encryptQid = EncryptUtils.ad(encryptQid);
			String depUrl = ab.isEmpty(depNo) ? "" : "&depNo=" + depNo;
			ai.a(e + "qsQuestionnaire/view/openWap.shtml?qId=" + encryptQid + depUrl + "&pid=" + pid, outPutStream,
					"png", Integer.valueOf(300), Integer.valueOf(300), (String) null);
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

	@RequestMapping({"/surveyRelease/f_view/selectPatient4Wap"})
	public String b(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Long qid) {
		AcAccount ac = (AcAccount) this.b(request);
		Dep dep = new Dep();

		try {
			dep.setHospId(ac.getUnitId());
			List encryptQid = this.e.getDepList(dep);
			modelMap.put("depList", encryptQid);
		} catch (Exception arg7) {
			arg7.printStackTrace();
		}

		String encryptQid1 = "#" + qid;
		encryptQid1 = EncryptUtils.ad(encryptQid1);
		modelMap.put("qid", qid);
		modelMap.put("encryptQid", encryptQid1);
		modelMap.put("unitId", ac.getUnitId());
		return "surveyRelease/selectPatient4Wap";
	}

	@RequestMapping({"/surveyRelease/f_view/selectQnaire4Wap"})
	public String c(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		try {
			AcAccount e = (AcAccount) this.b(request);
			QsQuestionnaire qsQuestionnaire = new QsQuestionnaire();
			qsQuestionnaire.setUnitId(e.getUnitId());
			qsQuestionnaire.setStatus(ax.lq.getCode());
			List list = this.xi.findQsQuestionnaireAll(qsQuestionnaire);
			modelMap.put("qsQuestionnaireList", list);
		} catch (Exception arg6) {
			arg6.printStackTrace();
		}

		return "surveyRelease/selectQnaire4Wap";
	}

	@RequestMapping({"/surveyRelease/view/userLogin4Wap"})
	public String d(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		return "surveyRelease/userLogin4Wap";
	}

	public LoginUser d(AcAccount account) {
		LoginUser loginUser = new LoginUser();
		loginUser.setUserId(account.getUserId());
		loginUser.setUsername(account.getUsername());
		loginUser.setUnitId(account.getUnitId());
		loginUser.setUnitName(account.getUnitName());
		loginUser.setDocNo(account.getDocNo());
		loginUser.setDepNo(account.getDepNo());
		loginUser.setRealname(account.getRealname());
		loginUser.setDataScope(account.getDataScope());
		loginUser.setPhotoPath(account.getPhotoPath());
		AcRole role = account.getRoleCur();
		if (role != null) {
			loginUser.setRoleId(role.getRoleId());
			loginUser.setRoleName(role.getName());
		}

		return loginUser;
	}
}