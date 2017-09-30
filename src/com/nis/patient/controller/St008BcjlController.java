package com.nis.patient.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.Param;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.v;
import com.nis.dict.service.SysDictService;
import com.nis.param.service.SysParamService;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.entity.St008Bcjl;
import com.nis.patient.entity.St020ClinicPatients;
import com.nis.patient.service.St003CryxxbService;
import com.nis.patient.service.St008BcjlService;
import com.nis.patient.service.St020ClinicPatientsService;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class St008BcjlController extends BaseController {
	private static final Logger c = Logger.getLogger(St008BcjlController.class);
	@Autowired
	private St008BcjlService aP;
	@Autowired
	private SysParamService j;
	@Autowired
	private St003CryxxbService bg;
	@Autowired
	private St020ClinicPatientsService bh;
	@Autowired
	private SysDictService p;

	@RequestMapping({"/st008Bcjl/f_view/toDisAnalysisList"})
	public String a(HttpServletRequest request, ModelMap modelMap, String zyid, String mzid, Date dataDate) {
		String days;
		int e;
		if (ab.isNotEmpty(zyid)) {
			days = this.j.findByParamCode(Param.NIS_PATIENT_DIS_DAYS);
			String st020ClinicPatients = this.j.findByParamCode(Param.NIS_YSBC_URL);
			St003Cryxxb queryEndTime = this.bg.get(zyid);
			String queryStartDate = queryEndTime.getPatientId();
			e = queryEndTime.getVisitId().intValue();
			st020ClinicPatients = st020ClinicPatients.replace("{patientId}", queryStartDate);
			st020ClinicPatients = st020ClinicPatients.replace("{zyid}", zyid);
			st020ClinicPatients = st020ClinicPatients.replace("{visitId}", String.valueOf(e));
			Date queryEndTime1 = new Date();
			Date queryStartDate1 = null;
			if (dataDate == null) {
				if (queryEndTime.getOutAt() != null) {
					queryEndTime1 = f.a(queryEndTime.getOutAt(), 3);
				}

				try {
					int e1 = Integer.parseInt(days);
					queryStartDate1 = f.a(queryEndTime1, -e1);
				} catch (NumberFormatException arg14) {
					arg14.printStackTrace();
				}
			} else {
				queryEndTime1 = f.a(dataDate, 6);
				queryStartDate1 = f.a(dataDate, -6);
			}

			modelMap.put("queryEndTime", queryEndTime1);
			modelMap.put("queryStartDate", queryStartDate1);
			modelMap.put("ysbcurl", st020ClinicPatients);
		} else if (ab.isNotEmpty(mzid)) {
			days = this.j.findByParamCode(Param.NIS_PATIENT_DIS_DAYS);
			St020ClinicPatients st020ClinicPatients1 = this.bh.getByMzid(mzid, (String) null);
			Date queryEndTime2 = new Date();
			Date queryStartDate2 = null;
			if (dataDate == null) {
				if (st020ClinicPatients1.getDiagnosisDt() != null) {
					queryEndTime2 = f.a(st020ClinicPatients1.getDiagnosisDt(), 3);
				}

				try {
					e = Integer.parseInt(days);
					queryStartDate2 = f.a(queryEndTime2, -e);
				} catch (NumberFormatException arg13) {
					arg13.printStackTrace();
				}
			} else {
				queryEndTime2 = f.a(dataDate, 6);
				queryStartDate2 = f.a(dataDate, -6);
			}

			modelMap.put("queryEndTime", queryEndTime2);
			modelMap.put("queryStartDate", queryStartDate2);
		}

		return "patient/disAnalysisList";
	}

	@RequestMapping({"/st008Bcjl/f_json/findDisAnalysisList"})
	@ResponseBody
	@SqlLog(p = "患者信息--病程列表")
	public void a(HttpServletRequest request, HttpServletResponse response, St008Bcjl st008Bcjl) {
		String orderBy = this.j.findByParamCode(Param.NIS_hzda_YZORDERBY);
		st008Bcjl.setOrderBy(orderBy);
		List st008BcjlList = this.aP.findDisAnalysisList(st008Bcjl);
		MyPage page = new MyPage(1, st008BcjlList.size(), st008BcjlList.size(), st008BcjlList);
		this.a(response, page);
	}

	@RequestMapping({"/st008Bcjl/f_json/getDisCourseContent"})
	@ResponseBody
	@SqlLog(p = "患者信息--病程详情")
	public void at(HttpServletRequest request, HttpServletResponse response, String id) {
		St008Bcjl st008Bcjl = null;
		if (ab.isNotEmpty(id)) {
			st008Bcjl = this.aP.get(id);
			if (st008Bcjl != null && "1".equals(st008Bcjl.getContentType())) {
				String courseContent = st008Bcjl.getCourseContent();
				if (ab.isNotEmpty(courseContent)) {
					try {
						courseContent = v.aI(courseContent);
					} catch (Exception arg6) {
						c.debug("解析RTF原始病程失败", arg6);
						arg6.printStackTrace();
					}
				}

				st008Bcjl.setCourseContent(courseContent);
			}
		}

		this.a(response, st008Bcjl);
	}
}