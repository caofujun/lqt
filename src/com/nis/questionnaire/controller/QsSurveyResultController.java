package com.nis.questionnaire.controller;

import com.nis.access.entity.AcAccount;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.organization.service.DepService;
import com.nis.param.service.SysParamService;
import com.nis.questionnaire.entity.QsReportTopic;
import com.nis.questionnaire.entity.QsSurveyResult;
import com.nis.questionnaire.service.QsSurveyResultService;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QsSurveyResultController extends BaseController {
	@Autowired
	private QsSurveyResultService xo;
	@Autowired
	private SysParamService j;
	@Autowired
	private DepService e;

	@RequestMapping({"/qsSurveyResult/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "questionnaire/qsSurveyResultList";
	}

	@RequestMapping({"/qsSurveyResult/f_json/pageQuery"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, QsSurveyResult qsSurveyResult) {
		MyPage page = this.xo.a(qsSurveyResult);
		this.b(response, page);
	}

	@RequestMapping({"/qsSurveyResult/f_view/toedit"})
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (id != null) {
			QsSurveyResult qsSurveyResult = this.xo.get(id);
			modelMap.put("qsSurveyResult", qsSurveyResult);
		}

		return "questionnaire/qsSurveyResultEdit";
	}

	@RequestMapping({"/qsSurveyResult/f_view/qsReport"})
	public String ae(HttpServletRequest request, ModelMap modelMap) {
		AcAccount account = (AcAccount) this.b(request);
		modelMap.put("account", account);
		modelMap.put("reportUrl",
				this.j.findByParamCode(Param.NIS_HTTP_URL, (String) null, (String) null, (String) null));
		return "questionnaire/qsReport";
	}

	@RequestMapping({"/qsSurveyResult/f_view/defalutReportInfo"})
	public String k(HttpServletRequest request, ModelMap modelMap, String qId, String queryStartDate,
			String queryEndDate, String depNo, String dateType) {
		if (qId == null) {
			modelMap.put("msg", "参数异常！");
			return "redirect:/404.jsp";
		} else {
			if (!"date".equals(dateType) && ab.isNotEmpty(dateType)) {
				queryStartDate = this.i(queryStartDate, queryEndDate, depNo, dateType, "start");
				queryEndDate = this.i(queryStartDate, queryEndDate, depNo, dateType, "end");
			}

			QsReportTopic qsReportTopic = new QsReportTopic();
			qsReportTopic.setqId(qId);
			if (ab.isNotEmpty(queryStartDate)) {
				qsReportTopic.setQueryStartDate(f.l(queryStartDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
			}

			if (ab.isNotEmpty(queryEndDate)) {
				qsReportTopic.setQueryEndDate(f.l(queryEndDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
			}

			if (ab.isNotEmpty(depNo)) {
				qsReportTopic.setDepNo(ab.aV(depNo));
			}

			List reportList = this.xo.findQsReportDefault(qsReportTopic);
			modelMap.put("reportList", reportList);
			return "questionnaire/defalutReportInfo";
		}
	}

	private String a(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(1, year);
		cal.set(2, month - 1);
		int lastDay = cal.getActualMaximum(5);
		cal.set(5, lastDay);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastDayOfMonth = sdf.format(cal.getTime());
		return lastDayOfMonth;
	}

	private String i(String queryStartDate, String queryEndDate, String depNo, String dateType, String endOrStart) {
		if ("month".equals(dateType)) {
			queryStartDate = queryEndDate + "-01";
			queryEndDate = this.a(Integer.valueOf(queryEndDate.split("-")[0]).intValue(),
					Integer.valueOf(queryEndDate.split("-")[1]).intValue());
		} else if ("year".equals(dateType)) {
			String quarter = queryEndDate + "-12";
			queryStartDate = queryEndDate + "-01-01";
			queryEndDate = this.a(Integer.valueOf(quarter.split("-")[0]).intValue(),
					Integer.valueOf(quarter.split("-")[1]).intValue());
		} else if ("quarter".equals(dateType)) {
			int quarter1 = Integer.valueOf(queryEndDate.split("-")[1]).intValue();
			String time;
			if (quarter1 == 1) {
				time = queryEndDate.split("-")[0];
				queryStartDate = time + "-01-01";
				queryEndDate = this.a(Integer.valueOf(time).intValue(), 3);
			} else if (quarter1 == 2) {
				time = queryEndDate.split("-")[0];
				queryStartDate = time + "-04-01";
				queryEndDate = this.a(Integer.valueOf(time).intValue(), 6);
			} else if (quarter1 == 3) {
				time = queryEndDate.split("-")[0];
				queryStartDate = time + "-07-01";
				queryEndDate = this.a(Integer.valueOf(time).intValue(), 9);
			} else if (quarter1 == 4) {
				time = queryEndDate.split("-")[0];
				queryStartDate = time + "-10-01";
				queryEndDate = this.a(Integer.valueOf(time).intValue(), 12);
			}
		}

		return endOrStart.equals("end") ? queryEndDate : (endOrStart.equals("start") ? queryStartDate : null);
	}

	@RequestMapping({"/qsSurveyResult/f_view/classifyReportInfo"})
	public String c(HttpServletRequest request, ModelMap modelMap, String qId, String optId, String queryStartDate,
			String queryEndDate, String depNo, String dateType) {
		if (qId == null) {
			modelMap.put("msg", "参数异常！问卷id为空");
			return "redirect:/404.jsp";
		} else if (optId == null) {
			modelMap.put("msg", "参数异常！问卷项id为空");
			return "redirect:/404.jsp";
		} else {
			if (!"date".equals(dateType) && ab.isNotEmpty(dateType)) {
				queryStartDate = this.i(queryStartDate, queryEndDate, depNo, dateType, "start");
				queryEndDate = this.i(queryStartDate, queryEndDate, depNo, dateType, "end");
			}

			QsReportTopic qsReportTopic = new QsReportTopic();
			qsReportTopic.setqId(qId);
			qsReportTopic.setOptId(optId);
			if (ab.isNotEmpty(queryStartDate)) {
				qsReportTopic.setQueryStartDate(f.l(queryStartDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
			}

			if (ab.isNotEmpty(queryEndDate)) {
				qsReportTopic.setQueryEndDate(f.l(queryEndDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
			}

			if (ab.isNotEmpty(depNo)) {
				qsReportTopic.setDepNo(ab.aV(depNo));
			}

			List reportList = this.xo.findQsReportClassify(qsReportTopic);
			modelMap.put("reportList", reportList);
			return "questionnaire/defalutReportInfo";
		}
	}

	@RequestMapping({"/qsSurveyResult/f_json/classifyReportCount"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, String qId, String optId,
			String queryStartDate, String queryEndDate, String depNo, String dateType) {
		Result result = new Result();
		if (qId == null) {
			result.setResult("error");
			result.setMsg("分类统计，参数异常！问卷id为空");
			this.b(response, result);
		} else if (optId == null) {
			result.setResult("error");
			result.setMsg("分类统计，参数异常！问卷项id为空");
			this.b(response, result);
		} else {
			if (ab.isNotEmpty(dateType) && !"date".equals(dateType)) {
				queryStartDate = this.i(queryStartDate, queryEndDate, depNo, dateType, "start");
				queryEndDate = this.i(queryStartDate, queryEndDate, depNo, dateType, "end");
			}

			QsReportTopic qsReportTopic = new QsReportTopic();
			qsReportTopic.setqId(qId);
			qsReportTopic.setOptId(optId);
			if (ab.isNotEmpty(queryStartDate)) {
				qsReportTopic.setQueryStartDate(f.l(queryStartDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
			}

			if (ab.isNotEmpty(queryEndDate)) {
				qsReportTopic.setQueryEndDate(f.l(queryEndDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
			}

			if (ab.isNotEmpty(depNo)) {
				qsReportTopic.setDepNo(ab.aV(depNo));
			}

			QsReportTopic topic = this.xo.findQsReportClassifyCount(qsReportTopic);
			result.setResult("success");
			result.setData(topic);
			this.b(response, result);
		}
	}
}