package com.nis.questionnaire.controller;

import com.nis.access.entity.AcAccount;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.b;
import com.nis.comm.utils.f;
import com.nis.organization.entity.Dep;
import com.nis.organization.service.DepService;
import com.nis.patient.entity.St001Jbxxb;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.service.St001JbxxbService;
import com.nis.patient.service.St003CryxxbService;
import com.nis.questionnaire.controller.QsSurveyRecordController.1;
import com.nis.questionnaire.entity.QsQuestionnaire;
import com.nis.questionnaire.entity.QsReportTopic;
import com.nis.questionnaire.entity.QsSurveyRecord;
import com.nis.questionnaire.entity.QsSurveyRecordList;
import com.nis.questionnaire.entity.QsSurveyResult;
import com.nis.questionnaire.service.QsQuestionnaireService;
import com.nis.questionnaire.service.QsSurveyRecordService;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QsSurveyRecordController extends BaseController {
	private static final Logger c = Logger.getLogger(QsSurveyRecordController.class);
	@Autowired
	private QsSurveyRecordService sv;
	@Autowired
	private QsQuestionnaireService xi;
	@Autowired
	private DepService e;
	@Autowired
	private St001JbxxbService dg;
	@Autowired
	private St003CryxxbService bg;

	@RequestMapping({"/qsSurveyRecord/f_view/index"})
	public String n(HttpServletRequest request) {
		return "questionnaire/qsSurveyRecordList";
	}

	@RequestMapping({"/qsSurveyRecord/view/webEndPage"})
	public String o(HttpServletRequest request) {
		return "questionnaire/qsQuestionnaireWebEndPage";
	}

	@RequestMapping({"/qsSurveyRecord/view/endPage"})
	public String a(HttpServletRequest request, HttpServletResponse response, String qid, ModelMap modelMap) {
		if (qid != null) {
			QsQuestionnaire qs = this.xi.get(qid);
			modelMap.put("title", qs.getTitle());
		}

		return "questionnaire/qsQuestionnaireEndPage";
	}

	@RequestMapping({"/qsSurveyRecord/f_json/pageQuery"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, QsSurveyRecord qsSurveyRecord) {
		AcAccount account = (AcAccount) this.b(request);
		qsSurveyRecord.setUnitId(account.getUnitId());
		MyPage page = this.sv.a(qsSurveyRecord);
		this.b(response, page);
	}

	@RequestMapping({"/qsSurveyRecord/f_view/toedit"})
	public String a(HttpServletRequest request, ModelMap modelMap, String id, QsSurveyRecord qsSurveyRecord,
			String sourceType) throws Exception {
		if (ab.isNotEmpty(qsSurveyRecord.getPatientName())) {
			qsSurveyRecord.setPatientName(URLDecoder.decode(qsSurveyRecord.getPatientName(), "UTF-8"));
		}

		if (ab.isEmpty(qsSurveyRecord.getPatientId()) && ab.isNotEmpty(qsSurveyRecord.getZyid())) {
			St003Cryxxb qsQuestionnaire = this.bg.get(qsSurveyRecord.getZyid());
			qsSurveyRecord.setPatientName(qsQuestionnaire.getPatientName());
			qsSurveyRecord.setPatientId(qsQuestionnaire.getPatientId());
			qsSurveyRecord.setDepNo(qsQuestionnaire.getDeptCode());
		}

		if (ab.isNotEmpty(id)) {
			qsSurveyRecord = this.sv.get(id);
			if (ab.isEmpty(qsSurveyRecord.getDepType())) {
				Dep qsQuestionnaire1 = this.e.F(qsSurveyRecord.getUnitId(), qsSurveyRecord.getDepNo());
				if (qsQuestionnaire1 != null) {
					qsSurveyRecord.setDepType(qsQuestionnaire1.getDeptTypeId());
				}
			}
		} else {
			AcAccount qsQuestionnaire2 = (AcAccount) this.b(request);
			if (qsSurveyRecord.getSurveyTime() == null) {
				qsSurveyRecord.setSurveyTime(new Date());
			}

			if (ab.isNotEmpty(qsSurveyRecord.getDepNo())) {
				Dep dep = this.e.F(qsQuestionnaire2.getUnitId(), qsSurveyRecord.getDepNo());
				if (dep != null) {
					qsSurveyRecord.setDepType(dep.getDeptTypeId());
				}
			}
		}

		QsQuestionnaire qsQuestionnaire3 = this.xi.get(qsSurveyRecord.getQid());
		modelMap.put("qsQuestionnaire", qsQuestionnaire3);
		modelMap.put("qsSurveyRecord", qsSurveyRecord);
		modelMap.put("sourceType", sourceType);
		modelMap.put("unitId", this.c(request));
		return "questionnaire/qsSurveyRecordEdit";
	}

	@RequestMapping({"/qsSurveyRecord/f_view/toPgEdit"})
	@SqlLog(p = "插管评估--插管评估单详情")
	public String a(HttpServletRequest request, ModelMap modelMap, String id, String catId,
			QsSurveyRecord qsSurveyRecord, String sourceType) throws Exception {
		AcAccount account = (AcAccount) this.b(request);
		if (ab.isNotEmpty(qsSurveyRecord.getPatientName())) {
			qsSurveyRecord.setPatientName(URLDecoder.decode(qsSurveyRecord.getPatientName(), "UTF-8"));
		}

		if (ab.isEmpty(qsSurveyRecord.getPatientId()) && ab.isNotEmpty(qsSurveyRecord.getZyid())) {
			St003Cryxxb qsQuestionnaire = this.bg.get(qsSurveyRecord.getZyid());
			qsSurveyRecord.setPatientName(qsQuestionnaire.getPatientName());
			qsSurveyRecord.setPatientId(qsQuestionnaire.getPatientId());
			qsSurveyRecord.setDepNo(qsQuestionnaire.getDeptCode());
		}

		Dep qsQuestionnaire1;
		if (ab.isNotEmpty(id)) {
			qsSurveyRecord = this.sv.get(id);
			if (ab.isEmpty(qsSurveyRecord.getDepType())) {
				qsQuestionnaire1 = this.e.F(qsSurveyRecord.getUnitId(), qsSurveyRecord.getDepNo());
				if (qsQuestionnaire1 != null) {
					qsSurveyRecord.setDepType(qsQuestionnaire1.getDeptTypeId());
				}
			}
		} else if (ab.isNotEmpty(qsSurveyRecord.getDepNo())) {
			qsQuestionnaire1 = this.e.F(account.getUnitId(), qsSurveyRecord.getDepNo());
			if (qsQuestionnaire1 != null) {
				qsSurveyRecord.setDepType(qsQuestionnaire1.getDeptTypeId());
			}
		}

		QsQuestionnaire qsQuestionnaire2 = this.xi.getbyCatId(catId);
		if (qsQuestionnaire2 != null) {
			qsSurveyRecord.setQid(qsQuestionnaire2.getQid());
		}

		List surveyRecordList = this.sv.findByzyidAndQid(qsSurveyRecord.getZyid(), qsSurveyRecord.getQid());
		modelMap.put("surveyRecordList", surveyRecordList);
		modelMap.put("username", account.getUsername());
		modelMap.put("qsQuestionnaire", qsQuestionnaire2);
		modelMap.put("qsSurveyRecord", qsSurveyRecord);
		modelMap.put("sourceType", sourceType);
		modelMap.put("dateMonth", f.formatDate(new Date(), "MM月dd日"));
		modelMap.put("unitId", this.c(request));
		return "questionnaire/qsCgPgEdit";
	}

	@RequestMapping({"/qsSurveyRecord/f_view/toeditIframe"})
	public String ac(HttpServletRequest request, ModelMap modelMap, String id) {
		return "questionnaire/qsSurveyRecordEditIframe";
	}

	@RequestMapping({"/qsSurveyRecord/f_json/save"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, QsSurveyRecord qsSurveyRecord,
			String[] resultTid) {
		Result result = null;

		try {
			AcAccount e = (AcAccount) this.b(request);
			result = new Result();
			qsSurveyRecord.setUsername(e.getUsername());
			ArrayList results = new ArrayList();
			if (resultTid != null) {
				for (int i = 0; i < resultTid.length; ++i) {
					String tid = resultTid[i];
					String tktCont = request.getParameter("resultTkt" + tid);
					if (ab.isNotEmpty(tktCont)) {
						QsSurveyResult optIds = new QsSurveyResult();
						optIds.setQid(qsSurveyRecord.getQid());
						optIds.setTid(tid);
						optIds.setInputValue(tktCont);
						results.add(optIds);
					}

					String[] arg18 = request.getParameterValues("resultOptId" + tid);
					if (arg18 != null) {
						String[] arg14 = arg18;
						int arg13 = arg18.length;

						for (int arg12 = 0; arg12 < arg13; ++arg12) {
							String optId = arg14[arg12];
							if (ab.isNotEmpty(optId)) {
								QsSurveyResult surveyResult = new QsSurveyResult();
								String[] arr = optId.split("#@#");
								surveyResult.setQid(qsSurveyRecord.getQid());
								surveyResult.setTid(tid);
								surveyResult.setOptId(arr[1]);
								surveyResult.setInputValue(
										request.getParameter("resultOptIdInput" + surveyResult.getOptId()));
								results.add(surveyResult);
							}
						}
					}
				}
			}

			this.sv.a(qsSurveyRecord, results);
			result.setData(qsSurveyRecord);
			result.setResult("success");
		} catch (Exception arg17) {
			c.error("获取信息异常!", arg17);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/qsSurveyRecord/f_json/saveList"})
	@ResponseBody
	@SqlLog(p = "评估单--保存")
	public void a(HttpServletRequest request, HttpServletResponse response, QsSurveyRecordList qsSurveyRecordList) {
		Result result = null;

		try {
			List e = qsSurveyRecordList.getSurveyRecordList();

			for (int a = 0; a < e.size(); ++a) {
				QsSurveyRecord qsSurveyRecord = (QsSurveyRecord) e.get(a);
				String[] resultTid = qsSurveyRecord.getResultTid();
				result = new Result();
				ArrayList results = new ArrayList();
				if (resultTid != null) {
					for (int i = 0; i < resultTid.length; ++i) {
						String tid = resultTid[i];
						String tktCont = request.getParameter("surveyRecordList[" + a + "].resultTkt" + tid);
						if (ab.isNotEmpty(tktCont)) {
							QsSurveyResult optIds = new QsSurveyResult();
							optIds.setQid(qsSurveyRecord.getQid());
							optIds.setTid(tid);
							optIds.setInputValue(tktCont);
							results.add(optIds);
						}

						String[] arg20 = request.getParameterValues("surveyRecordList[" + a + "].resultOptId" + tid);
						if (arg20 != null) {
							String[] arg16 = arg20;
							int arg15 = arg20.length;

							for (int arg14 = 0; arg14 < arg15; ++arg14) {
								String optId = arg16[arg14];
								if (ab.isNotEmpty(optId)) {
									QsSurveyResult surveyResult = new QsSurveyResult();
									String[] arr = optId.split("#@#");
									surveyResult.setQid(qsSurveyRecord.getQid());
									surveyResult.setTid(tid);
									surveyResult.setOptId(arr[1]);
									surveyResult.setInputValue(request.getParameter(
											"surveyRecordList[" + a + "].resultOptIdInput" + surveyResult.getOptId()));
									results.add(surveyResult);
								}
							}
						}
					}
				}

				this.sv.a(qsSurveyRecord, results);
				result.setData(qsSurveyRecord);
			}

			result.setResult("success");
		} catch (Exception arg19) {
			c.error("获取信息异常!", arg19);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/qsSurveyRecord/json/openSave"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String[] tIdList, String qid, String unitId,
			String depNo, String patientPhone, String surveyType, String pid, String nowQsType) {
		Result result = null;

		try {
			result = new Result();
			QsSurveyRecord e = new QsSurveyRecord();
			e.setSurveyTime(new Date());
			e.setQid(qid);
			e.setPatientId(pid);
			e.setPatientPhone(patientPhone);
			if (pid != null) {
				St001Jbxxb results = this.dg.get(pid);
				e.setPatientName(results.getPatientName());
				if (ab.isEmpty(patientPhone)) {
					e.setPatientPhone(results.getTel());
				}
			}

			e.setUnitId(unitId);
			e.setSurveyType(surveyType);
			e.setDepNo(depNo);
			ArrayList arg24 = new ArrayList();
			if (tIdList != null) {
				for (int i = 0; i < tIdList.length; ++i) {
					String tid = tIdList[i];
					String tktCont = request.getParameter("resultTkt" + tid);
					if (ab.isNotEmpty(tktCont)) {
						QsSurveyResult optIds = new QsSurveyResult();
						optIds.setQid(e.getQid());
						optIds.setTid(tid);
						optIds.setInputValue(tktCont);
						arg24.add(optIds);
					}

					String[] arg25 = request.getParameterValues("resultOptId" + tid);
					if (arg25 != null) {
						String[] arg20 = arg25;
						int arg19 = arg25.length;

						for (int arg18 = 0; arg18 < arg19; ++arg18) {
							String optId = arg20[arg18];
							if (ab.isNotEmpty(optId)) {
								QsSurveyResult surveyResult = new QsSurveyResult();
								String[] arr = optId.split("#@#");
								surveyResult.setQid(e.getQid());
								surveyResult.setTid(tid);
								surveyResult.setOptId(arr[1]);
								surveyResult.setInputValue(
										request.getParameter("resultOptIdInput" + surveyResult.getOptId()));
								arg24.add(surveyResult);
							}
						}
					}
				}
			}

			this.sv.a(e, arg24);
			result.setData(e);
			result.setResult("success");
		} catch (Exception arg23) {
			c.error("获取信息异常!", arg23);
			result = new Result("error", "获取信息异常");
		}

		if (ab.isNotEmpty(nowQsType) && !nowQsType.equals("emergency") && !nowQsType.equals("zh_satisfaction")) {
			result.setExpandValue("/qsFlow/view/openFlow4Wap/" + pid + ".shtml?nowQsType=" + nowQsType + "&fid="
					+ request.getParameter("fid") + "&nowStep=" + request.getParameter("nowStep"));
		}

		this.a(response, result);
	}

	@RequestMapping({"/qsSurveyRecord/f_json/zhiBiaoCount"})
	@ResponseBody
	public void z(HttpServletRequest request, HttpServletResponse response, String queryStartDate, String queryEndDate,
			String type) {
		Result result = null;
		if (!ab.isEmpty(queryStartDate) && !ab.isEmpty(queryEndDate)) {
			QsReportTopic qsReportTopic = new QsReportTopic();
			qsReportTopic.setTtype(type);
			qsReportTopic.setQueryStartDate(f.l(queryStartDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
			qsReportTopic.setQueryEndDate(f.l(queryEndDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
			qsReportTopic.setUnitId(this.c(request));
			List list = this.sv.zhiBiaoCount(qsReportTopic);
			result = new Result("success");
			result.setData(list);
			this.b(response, result);
		} else {
			result = new Result("error", "查询指标统计，参数异常！时间区间不能为空");
			this.b(response, result);
		}
	}

	@RequestMapping({"/qsSurveyRecord/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "评估单--删除评估单")
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			QsSurveyRecord e = this.sv.get(id);
			this.sv.delete(id);
			int answerCount = this.sv.findByQid(e.getQid());
			QsQuestionnaire qsQuestionnaire = this.xi.get(e.getQid());
			qsQuestionnaire.setAnswerCount(Long.valueOf((long) answerCount));
			this.xi.updateAnswerCounts(e.getQid(), Long.valueOf((long) answerCount));
			result.setResult("success");
		} catch (Exception arg7) {
			c.error("获取信息异常!", arg7);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/qsSurveyRecord/f_json/smsMydTrendAnalyze"})
   @ResponseBody
   public void d(HttpServletRequest request, HttpServletResponse response, QsSurveyRecord qsSurveyRecord) {
      Result result = null;

      try {
         result = new Result();
         qsSurveyRecord.setUnitId(this.c(request));
         List e = this.sv.d(qsSurveyRecord);
         LinkedList standardList = new LinkedList();
         String[] days = f.r(new Date());
         String[] arg10 = days;
         int arg9 = days.length;

         for(int arg8 = 0; arg8 < arg9; ++arg8) {
            String day = arg10[arg8];
            Map map = (Map)b.a(day, e, new 1(this));
            HashMap result_map = new HashMap();
            result_map.put("DAY", day);
            result_map.put("MYD", map == null?Integer.valueOf(0):map.get("MYD"));
            standardList.add(result_map);
         }

         result.setData(standardList);
         result.setMsg(f.k(new Date()));
         result.setResult("success");
      } catch (Exception arg13) {
         c.error("获取信息异常!", arg13);
         result = new Result("error", "获取信息异常");
      }

      this.a(response, result);
   }

	@RequestMapping({"/qsSurveyRecord/f_json/smsMydAnalyzeDep"})
	@ResponseBody
	public void e(HttpServletRequest request, HttpServletResponse response, QsSurveyRecord qsSurveyRecord) {
		Result result = null;

		try {
			result = new Result();
			qsSurveyRecord.setUnitId(this.c(request));
			List e = this.sv.b(qsSurveyRecord);
			Iterator arg6 = e.iterator();

			while (arg6.hasNext()) {
				Map map = (Map) arg6.next();
				String depNo = ab.g(map.get("DEPNO"));
				if (ab.isNotEmpty(depNo)) {
					map.put("DEPNAME", this.e.H(this.c(request), depNo));
				} else {
					map.put("DEPNAME", "其它");
				}
			}

			result.setData(e);
			result.setResult("success");
		} catch (Exception arg8) {
			c.error("获取信息异常!", arg8);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/qsSurveyRecord/f_json/smsMydCompareAnalyze"})
	@ResponseBody
	public void f(HttpServletRequest request, HttpServletResponse response, QsSurveyRecord qsSurveyRecord) {
		Result result = null;

		try {
			result = new Result();
			ArrayList e = new ArrayList();
			HashMap result_map_1 = new HashMap();
			HashMap result_map_2 = new HashMap();
			qsSurveyRecord.setUnitId(this.c(request));
			qsSurveyRecord.setQueryStartDate(f.q(f.g(true)));
			qsSurveyRecord.setQueryEndDate(f.p(f.h(true)));
			String myd = this.sv.c(qsSurveyRecord);
			result_map_1.put("SMYD", myd);
			result_map_2.put("SMYD", myd);
			qsSurveyRecord.setQueryStartDate(f.q(f.a(f.b(new Date(), -1), true)));
			qsSurveyRecord.setQueryEndDate(f.p(f.b(f.b(new Date(), -1), true)));
			myd = this.sv.c(qsSurveyRecord);
			result_map_1.put("DATE", "环比" + f.k(f.b(new Date(), -1)));
			result_map_1.put("CMYD", myd);
			qsSurveyRecord.setQueryStartDate(f.q(f.a(f.c(new Date(), -1), true)));
			qsSurveyRecord.setQueryEndDate(f.p(f.b(f.c(new Date(), -1), true)));
			myd = this.sv.c(qsSurveyRecord);
			result_map_2.put("DATE", "同比" + f.k(f.c(new Date(), -1)));
			result_map_2.put("CMYD", myd);
			e.add(result_map_1);
			e.add(result_map_2);
			result.setData(e);
			result.setResult("success");
		} catch (Exception arg8) {
			c.error("获取信息异常!", arg8);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/qsSurveyRecord/json/getByPidQid"})
	@ResponseBody
	public void W(HttpServletRequest request, HttpServletResponse response, String qid, String patientId) {
		Result result = null;

		try {
			result = new Result();
			if (qid != null && patientId != null) {
				QsSurveyRecord e = this.sv.getByPidQid(patientId, qid);
				if (e != null) {
					result.setResult("success");
				}
			} else {
				result.setResult("error");
			}
		} catch (Exception arg6) {
			result = new Result("error", "获取信息异常");
			arg6.printStackTrace();
		}

		this.a(response, result);
	}
}