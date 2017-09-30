package com.nis.questionnaire.controller;

import com.nis.access.entity.AcAccount;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.KvEntity;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.h;
import com.nis.comm.enums.n;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.questionnaire.entity.QsAccountQues;
import com.nis.questionnaire.entity.QsQuestionnaire;
import com.nis.questionnaire.entity.QsSurveyRecord;
import com.nis.questionnaire.service.QsAccountQuesService;
import com.nis.questionnaire.service.QsQuestionnaireService;
import com.nis.questionnaire.service.QsSurveyRecordService;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QsQuestionnaireController extends BaseController {
	private static final Logger c = Logger.getLogger(QsQuestionnaireController.class);
	@Autowired
	private QsQuestionnaireService xi;
	@Autowired
	private QsSurveyRecordService sv;
	@Autowired
	private QsAccountQuesService xm;

	@RequestMapping({"/qsQuestionnaire/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		try {
			this.xi.deleteCache();
		} catch (Exception arg3) {
			;
		}

		AcAccount acAccount = (AcAccount) this.b(request);
		modelMap.put("username", acAccount.getUsername());
		return "questionnaire/qsQuestionnaireList";
	}

	@RequestMapping({"/qsQuestionnaire/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "评估单--评估单列表")
	public void a(HttpServletRequest request, HttpServletResponse response, QsQuestionnaire qsQuestionnaire) {
		AcAccount account = (AcAccount) this.b(request);
		qsQuestionnaire.setQlevel(account.getDataScope().toString());
		qsQuestionnaire.setUnitId(account.getUnitId());
		qsQuestionnaire.setDepNo(account.getDepNo());
		if (account.getDataScope() == n.gj.getValue()) {
			qsQuestionnaire.setQlevel(n.gk.getValue().toString());
		} else if (account.getDataScope() == n.gh.getValue()) {
			qsQuestionnaire.setCreateUser(account.getUsername());
		} else if (account.getDataScope() == n.gm.getValue() && ab.isNotEmpty(account.getUnitId())) {
			qsQuestionnaire.setQlevel(n.gk.getValue().toString());
		}

		if (qsQuestionnaire.getTitle() != null && !"".equals(qsQuestionnaire.getTitle())) {
			qsQuestionnaire.setTitle(ab.aR(qsQuestionnaire.getTitle()));
		}

		MyPage page = this.xi.a(qsQuestionnaire);
		this.b(response, page);
	}

	@RequestMapping({"/qsQuestionnaire/f_json/queryQsEasyUi"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, QsQuestionnaire qsQuestionnaire,
			String defalultValue) throws UnsupportedEncodingException {
		AcAccount account = (AcAccount) this.b(request);
		qsQuestionnaire.setQlevel(account.getDataScope().toString());
		qsQuestionnaire.setUnitId(account.getUnitId());
		qsQuestionnaire.setDepNo(account.getDepNo());
		if (account.getDataScope() == n.gj.getValue()) {
			qsQuestionnaire.setQlevel(n.gk.getValue().toString());
		} else if (account.getDataScope() == n.gh.getValue()) {
			qsQuestionnaire.setCreateUser(account.getUsername());
		} else if (account.getDataScope() == n.gm.getValue() && ab.isNotEmpty(account.getUnitId())) {
			qsQuestionnaire.setQlevel(n.gk.getValue().toString());
		}

		List list = this.xi.findQsQuestionnaireAll(qsQuestionnaire);
		ArrayList kvList = new ArrayList();
		if (ab.isNotEmpty(defalultValue)) {
			KvEntity qs = new KvEntity("", URLDecoder.decode(defalultValue, "utf-8"));
			if (ab.isEmpty(qsQuestionnaire.getQid())) {
				qs.setSelected(true);
			}

			kvList.add(qs);
		}

		Iterator arg8 = list.iterator();

		while (true) {
			while (arg8.hasNext()) {
				QsQuestionnaire qs1 = (QsQuestionnaire) arg8.next();
				if (qs1.getQid() != null && qs1.getQid().equals(qsQuestionnaire.getQid())) {
					KvEntity kv = new KvEntity(qs1.getQid(), qs1.getTitle());
					kv.setSelected(true);
					kvList.add(kv);
				} else {
					kvList.add(new KvEntity(qs1.getQid(), qs1.getTitle()));
				}
			}

			this.b(response, kvList);
			return;
		}
	}

	@RequestMapping({"/qsQuestionnaire/f_view/toadd"})
	@SqlLog(p = "评估单--添加评估单")
	public String r(HttpServletRequest request, ModelMap modelMap) {
		AcAccount account = (AcAccount) this.b(request);
		QsQuestionnaire qsQuestionnaire = new QsQuestionnaire();
		qsQuestionnaire.setIsMod(h.fP.getCode().toString());
		qsQuestionnaire.setIsQz(h.fP.getCode().toString());
		qsQuestionnaire.setQlevel(account.getDataScope().toString());
		qsQuestionnaire.setUnitId(account.getUnitId());
		qsQuestionnaire.setDepNo(account.getDepNo());
		if (account.getDataScope() == n.gj.getValue()) {
			qsQuestionnaire.setQlevel(n.gk.getValue().toString());
		} else if (account.getDataScope() == n.gh.getValue()) {
			qsQuestionnaire.setCreateUser(account.getUsername());
		} else if (account.getDataScope() == n.gm.getValue() && ab.isNotEmpty(account.getUnitId())) {
			qsQuestionnaire.setQlevel(n.gk.getValue().toString());
		}

		List list = this.xi.findByIsMod(qsQuestionnaire);
		modelMap.put("list", list);
		return "questionnaire/qsQuestionnaireAdd";
	}

	@RequestMapping({"/qsQuestionnaire/f_view/toedit"})
	@SqlLog(p = "评估单--评估单详情")
	public String a(HttpServletRequest request, ModelMap modelMap, String id, String quesId, String isCache) {
		QsQuestionnaire qsQuestionnaire = null;
		if (id != null) {
			qsQuestionnaire = this.xi.get(id);
		} else {
			AcAccount account = (AcAccount) this.b(request);
			if (ab.isNotEmpty(quesId)) {
				qsQuestionnaire = this.xi.a(quesId, account, isCache);
			} else {
				qsQuestionnaire = new QsQuestionnaire();
				qsQuestionnaire.setTitle("问卷标题（未设定）");
				qsQuestionnaire.setUpdTime(f.f(new Date()));
				qsQuestionnaire.setUpdUser(account.getUsername());
				qsQuestionnaire.setCreateUser(account.getUsername());
				qsQuestionnaire.setUnitId(account.getUnitId());
				qsQuestionnaire.setDepNo(account.getDepNo());
				qsQuestionnaire.setExt1(isCache);
				qsQuestionnaire.setQlevel(account.getDataScope().toString());
				if (account.getDataScope() == n.gj.getValue()) {
					qsQuestionnaire.setQlevel(n.gk.getValue().toString());
				} else if (account.getDataScope() == n.gm.getValue() && ab.isNotEmpty(account.getUnitId())) {
					qsQuestionnaire.setQlevel(n.gk.getValue().toString());
				}

				this.xi.b(qsQuestionnaire);
			}

			if (n.gi.getValue().intValue() == account.getDataScope().intValue()) {
				qsQuestionnaire.setDepNo(account.getDepNo());
			}
		}

		modelMap.put("qsQuestionnaire", qsQuestionnaire);
		return "questionnaire/qsQuestionnaireEdit";
	}

	@RequestMapping({"/qsQuestionnaire/f_json/update"})
	@ResponseBody
	@SqlLog(p = "评估单--更新评估单")
	public void b(HttpServletRequest request, HttpServletResponse response, QsQuestionnaire qsQuestionnaire) {
		Result result = null;

		try {
			result = new Result();
			QsQuestionnaire e = this.xi.get(qsQuestionnaire.getQid());
			e.setFeedbackValue(qsQuestionnaire.getFeedbackValue());
			this.xi.b(e);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/qsQuestionnaire/f_json/save"})
	@ResponseBody
	@SqlLog(p = "评估单--新增评估单")
	public void c(HttpServletRequest request, HttpServletResponse response, QsQuestionnaire qsQuestionnaire) {
		Result result = null;

		try {
			AcAccount e = (AcAccount) this.b(request);
			result = new Result();
			qsQuestionnaire.setUpdTime(f.f(new Date()));
			qsQuestionnaire.setUpdUser(e.getUsername());
			if (ab.isEmpty(qsQuestionnaire.getQid())) {
				qsQuestionnaire.setQlevel(e.getDataScope().toString());
				qsQuestionnaire.setDepNo(e.getDepNo());
				if (e.getDataScope() == n.gj.getValue()) {
					qsQuestionnaire.setQlevel(n.gk.getValue().toString());
				} else if (e.getDataScope() == n.gm.getValue() && ab.isNotEmpty(e.getUnitId())) {
					qsQuestionnaire.setQlevel(n.gk.getValue().toString());
				}

				qsQuestionnaire.setCreateUser(e.getUsername());
			} else {
				QsQuestionnaire temp = this.xi.get(qsQuestionnaire.getQid());
				if (temp != null && !e.getUsername().equals(temp.getCreateUser())) {
					c.error("没有修改此问卷的权限!");
					result = new Result("fail", "没有修改此问卷的权限");
					this.a(response, result);
					return;
				}
			}

			this.xi.b(qsQuestionnaire);
			result.setResult("success");
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/qsQuestionnaire/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "评估单--删除评估单")
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			AcAccount e = (AcAccount) this.b(request);
			QsQuestionnaire temp = this.xi.get(id);
			if (!e.getUsername().equals(temp.getCreateUser())) {
				c.error("没有删除此问卷的权限!");
				result = new Result("fail", "没有删除此问卷的权限");
				this.a(response, result);
				return;
			}

			this.xi.delete(id);
			result.setResult("success");
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/qsQuestionnaire/f_json/issue"})
	@ResponseBody
	@SqlLog(p = "评估单--发布评估单")
	public void c(HttpServletRequest request, HttpServletResponse response, String id, Integer status) {
		Result result = null;

		try {
			result = new Result();
			AcAccount e = (AcAccount) this.b(request);
			QsQuestionnaire temp = this.xi.get(id);
			if (!e.getUsername().equals(temp.getCreateUser())) {
				c.error("没有发布此问卷的权限!");
				result = new Result("fail", "没有发布此问卷的权限");
				this.a(response, result);
				return;
			}

			this.xi.updateStatus(id, status);
			result.setResult("success");
		} catch (Exception arg7) {
			c.error("获取信息异常!", arg7);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/qsQuestionnaire/f_json/publish"})
	@ResponseBody
	public void d(HttpServletRequest request, HttpServletResponse response, String id, Integer publish) {
		Result result = null;

		try {
			result = new Result();
			this.xi.updatePublish(id, publish);
			result.setResult("success");
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/qsQuestionnaire/f_view/input"})
	public String aa(HttpServletRequest request, ModelMap modelMap) {
		modelMap.put("type", "input");
		return "questionnaire/qsQuestionnaireSurvey";
	}

	@RequestMapping({"/qsQuestionnaire/f_view/phone"})
	public String ab(HttpServletRequest request, ModelMap modelMap) {
		modelMap.put("type", "phone");
		return "questionnaire/qsQuestionnaireSurvey";
	}

	@RequestMapping({"/qsQuestionnaire/f_view/internet"})
	public String ac(HttpServletRequest request, ModelMap modelMap) {
		modelMap.put("type", "internet");
		return "questionnaire/qsQuestionnaireSurvey";
	}

	@RequestMapping({"/qsQuestionnaire/f_view/feedback"})
	public String ad(HttpServletRequest request, ModelMap modelMap) {
		return "questionnaire/qsQuestionnaireFeedback";
	}

	@RequestMapping({"/qsQuestionnaire/f_view/feedbackList"})
	public String aa(HttpServletRequest request, ModelMap modelMap, String qid) {
		QsQuestionnaire qsQuestionnaire = this.xi.get(qid);
		qsQuestionnaire.setCatId((String) null);
		AcAccount account = (AcAccount) this.b(request);
		qsQuestionnaire.setQlevel(account.getDataScope().toString());
		qsQuestionnaire.setUnitId(account.getUnitId());
		qsQuestionnaire.setDepNo(account.getDepNo());
		if (account.getDataScope() == n.gj.getValue()) {
			qsQuestionnaire.setQlevel(n.gk.getValue().toString());
		} else if (account.getDataScope() == n.gh.getValue()) {
			qsQuestionnaire.setCreateUser(account.getUsername());
		} else if (account.getDataScope() == n.gm.getValue() && ab.isNotEmpty(account.getUnitId())) {
			qsQuestionnaire.setQlevel(n.gk.getValue().toString());
		}

		List qsQuestionnaireList = this.xi.findCanEnter(qsQuestionnaire);
		this.c(request);
		QsAccountQues qsAccountQues = this.xm.getByQidAndUserId(qid, (String) null);
		if (qsAccountQues != null) {
			qsQuestionnaire.setDcValue(qsAccountQues.getDcValue());
		}

		modelMap.put("endTime", f.c(new Date(), "yyyy-MM-dd"));
		modelMap.put("qsQuestionnaire", qsQuestionnaire);
		modelMap.put("qsQuestionnaireList", qsQuestionnaireList);
		return "questionnaire/qsQuestionnaireFeedbackList";
	}

	@RequestMapping({"/qsQuestionnaire/f_json/pageQueryFeedback"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, QsSurveyRecord qsSurveyRecord) {
		this.c(request);
		QsAccountQues qsAccountQues = this.xm.getByQidAndUserId(qsSurveyRecord.getQid(), (String) null);
		if (qsSurveyRecord.getDcValue() != null) {
			if (qsAccountQues == null) {
				qsAccountQues = new QsAccountQues();
				qsAccountQues.setUserId(this.c(request));
				qsAccountQues.setQid(qsSurveyRecord.getQid());
				qsAccountQues.setDcValue(qsSurveyRecord.getDcValue());
				this.xm.save(qsAccountQues);
			} else {
				qsAccountQues.setUserId(this.c(request));
				qsAccountQues.setQid(qsSurveyRecord.getQid());
				qsAccountQues.setDcValue(qsSurveyRecord.getDcValue());
				this.xm.update(qsAccountQues);
			}
		}

		MyPage page = this.sv.e(qsSurveyRecord);
		this.b(response, page);
	}

	@RequestMapping({"/qsQuestionnaire/f_view/qsFeedbackValue"})
	public String ab(HttpServletRequest request, ModelMap modelMap, String qid) {
		QsQuestionnaire qsQuestionnaire = this.xi.get(qid);
		if (qsQuestionnaire.getFeedbackValue() == null) {
			this.c(request);
			QsAccountQues qsAccountQues = this.xm.getByQidAndUserId(qid, (String) null);
			if (qsAccountQues != null) {
				qsQuestionnaire.setFeedbackValue(qsAccountQues.getDcValue());
			}
		}

		modelMap.put("qsQuestionnaire", qsQuestionnaire);
		return "questionnaire/qsFeedbackValue";
	}

	@RequestMapping({"/qsQuestionnaire/f_json/exportFeedback"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, QsSurveyRecord qsSurveyRecord)
			throws IOException {
		QsQuestionnaire qsQuestionnaire = this.xi.get(qsSurveyRecord.getQid());
		List dataList = this.sv.f(qsSurveyRecord);
		String fileName = qsQuestionnaire.getTitle() + (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
		String finalFileName = "";
		String userAgent = request.getHeader("USER-AGENT");
		if (StringUtils.contains(userAgent, "MSIE")) {
			finalFileName = URLEncoder.encode(fileName, "UTF-8");
		} else if (StringUtils.contains(userAgent, "Chrome")) {
			finalFileName = new String(fileName.getBytes(), "ISO8859-1");
		} else if (StringUtils.contains(userAgent, "Firefox")) {
			finalFileName = new String(fileName.getBytes(), "ISO8859-1");
		} else {
			finalFileName = URLEncoder.encode(fileName, "UTF-8");
		}

		response.setHeader("Content-disposition", "attachment; filename=" + finalFileName + ".xls");
		response.setContentType("application/msexcel;charset=UTF-8");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		HSSFWorkbook workBook = this.sv.D(dataList);
		ServletOutputStream os = response.getOutputStream();
		workBook.write(os);
		os.flush();
		os.close();
	}

	@RequestMapping({"/qsQuestionnaire/f_json/findCanEnter"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, QsQuestionnaire qsQuestionnaire,
			String type) {
		AcAccount account = (AcAccount) this.b(request);
		qsQuestionnaire.setCatId((String) null);
		qsQuestionnaire.setQlevel(account.getDataScope().toString());
		qsQuestionnaire.setUnitId(account.getUnitId());
		qsQuestionnaire.setDepNo(account.getDepNo());
		if ("internet".equals(type)) {
			qsQuestionnaire.setCatId(type);
		}

		if (account.getDataScope() == n.gj.getValue()) {
			qsQuestionnaire.setQlevel(n.gk.getValue().toString());
		} else if (account.getDataScope() == n.gh.getValue()) {
			qsQuestionnaire.setCreateUser(account.getUsername());
		} else if (account.getDataScope() == n.gm.getValue() && ab.isNotEmpty(account.getUnitId())) {
			qsQuestionnaire.setQlevel(n.gk.getValue().toString());
		}

		MyPage page = this.xi.c(qsQuestionnaire);
		this.b(response, page);
	}
}