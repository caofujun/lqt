package com.nis.patient.controller;

import com.nis.access.entity.AcAccount;
import com.nis.access.entity.AcMenu;
import com.nis.access.entity.AcRole;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.s;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import com.nis.follow.service.FoPatientService;
import com.nis.mdr.service.Xn011DclymxService;
import com.nis.param.service.SysParamService;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.entity.St004Yzxxb;
import com.nis.patient.entity.St020ClinicPatients;
import com.nis.patient.service.St001JbxxbService;
import com.nis.patient.service.St003CryxxbService;
import com.nis.patient.service.St004YzxxbService;
import com.nis.patient.service.St005SsxxbService;
import com.nis.patient.service.St006TwxxService;
import com.nis.patient.service.St020ClinicPatientsService;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class St003CryxxbController extends BaseController {
	private static final Logger c = Logger.getLogger(St003CryxxbController.class);
	@Autowired
	private St003CryxxbService bg;
	@Autowired
	private St001JbxxbService dg;
	@Autowired
	private FoPatientService qW;
	@Autowired
	private St020ClinicPatientsService bh;
	@Autowired
	private St004YzxxbService bu;
	@Autowired
	private Xn011DclymxService cn;
	@Autowired
	private St005SsxxbService bO;
	@Autowired
	private St006TwxxService bN;
	@Autowired
	private SysParamService j;
	@Autowired
	private SysDictService p;

	@RequestMapping({"/st003Cryxxb/f_view/toPatientRecords"})
	public String O(HttpServletRequest request, ModelMap modelMap, String zyid) {
		if (ab.isNotEmpty(zyid)) {
			AcAccount acc = (AcAccount) this.b(request);
			AcRole role = acc.getRoleCur();
			String ownership = role.getRoleType();
			modelMap.put("ownership", ownership);
			St003Cryxxb st003Cryxxb = this.bg.getPatientRecords(zyid);
			if (st003Cryxxb != null) {
				List foPatientList = this.qW.getByPatientId(st003Cryxxb.getPatientId());
				if (foPatientList.size() > 0) {
					modelMap.put("follow", "1");
				}

				modelMap.put("st003Cryxxb", st003Cryxxb);
			}
		}

		return "patient/patientRecords";
	}

	@RequestMapping({"/st003Cryxxb/f_view/toPatientRecordsMz"})
	public String P(HttpServletRequest request, ModelMap modelMap, String mzid) {
		if (ab.isNotEmpty(mzid)) {
			this.c(request);
			St020ClinicPatients st020ClinicPatients = this.bh.getByMzid(mzid, (String) null);
			List foPatientList = this.qW.getByPatientId(st020ClinicPatients.getPatientId());
			if (foPatientList.size() > 0) {
				modelMap.put("follow", "1");
			}

			String mzbc = this.j.findByParamCode(Param.NIS_MZBJ_OPEN_SCOPE);
			modelMap.put("mzbc", mzbc);
			modelMap.put("st020ClinicPatients", st020ClinicPatients);
		}

		return "patient/patientRecordsMz";
	}

	@RequestMapping({"/st003Cryxxb/f_view/mzDetail"})
	public String Q(HttpServletRequest request, ModelMap modelMap, String mzid) {
		if (ab.isNotEmpty(mzid)) {
			this.c(request);
			St020ClinicPatients st020ClinicPatients = this.bh.getByMzid(mzid, (String) null);
			modelMap.put("st020ClinicPatients", st020ClinicPatients);
		}

		return "patient/patientDetailMz";
	}

	@RequestMapping({"/st003Cryxxb/f_json/findByid"})
	@ResponseBody
	public void ap(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			if (ab.isNotEmpty(id)) {
				St003Cryxxb e = this.bg.get(id);
				result.setData(e);
				result.setResult("success");
			} else {
				result.setResult("error");
			}
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/st003Cryxxb/f_view/detail"})
	@SqlLog(p = "患者信息--基本信息")
	public String x(HttpServletRequest request, ModelMap modelMap, String zyid) {
		if (ab.isNotEmpty(zyid)) {
			St003Cryxxb st003Cryxxb = this.bg.getPatientRecords(zyid);
			modelMap.put("st003Cryxxb", st003Cryxxb);
		}

		return "patient/patientDetail";
	}

	@RequestMapping({"/st003Cryxxb/f_view/view"})
	@SqlLog(p = "患者信息--综合视图")
	public String j(HttpServletRequest request, ModelMap modelMap, String zyid, String startDate, String endDate) {
		St003Cryxxb st003Cryxxb = this.bg.get(zyid);
		String minDate = f.formatDate(st003Cryxxb.getInHospAt());
		String maxDate = f.formatDate(f.a(new Date(), 1));
		if (st003Cryxxb.getOutAt() != null) {
			maxDate = f.formatDate(f.a(st003Cryxxb.getOutAt(), 1));
		}

		if (StringUtils.isBlank(startDate)) {
			if (st003Cryxxb.getInHospAt() != null) {
				startDate = f.formatDate(st003Cryxxb.getInHospAt());
			} else {
				startDate = f.formatDate(new Date());
			}
		}

		if (StringUtils.isBlank(endDate)) {
			endDate = f.formatDate(f.a(f.Z(startDate), 30));
		}

		AcAccount account = (AcAccount) this.b(request);
		String resultHigh = "";
		List hilist = this.p.u("result_high", account.getUnitId());

		SysDict resultLow;
		for (Iterator loList = hilist.iterator(); loList
				.hasNext(); resultHigh = resultHigh + resultLow.getDictCode() + ",") {
			resultLow = (SysDict) loList.next();
		}

		String resultLow1 = "";
		List loList1 = this.p.u("result_low", account.getUnitId());

		SysDict patientViewList;
		for (Iterator patientViewAllList = loList1.iterator(); patientViewAllList
				.hasNext(); resultLow1 = resultLow1 + patientViewList.getDictCode() + ",") {
			patientViewList = (SysDict) patientViewAllList.next();
		}

		modelMap.put("resultHigh", resultHigh);
		modelMap.put("resultLow", resultLow1);
		List patientViewList1 = this.dg.findbyZyid(zyid, f.formatDate(st003Cryxxb.getInHospAt()), startDate, endDate);
		List patientViewAllList1 = this.dg.findbyZyid(zyid, f.formatDate(st003Cryxxb.getInHospAt()), minDate, maxDate);
		modelMap.put("startDate", startDate);
		modelMap.put("endDate", endDate);
		modelMap.put("previous", f.a(f.Y(startDate), st003Cryxxb.getInHospAt()) > 1 ? "" : "disabled");
		modelMap.put("next",
				f.a(st003Cryxxb.getOutAt() == null ? f.getCurDate() : st003Cryxxb.getOutAt(), f.Y(endDate)) > 0
						? ""
						: "disabled");
		modelMap.put("patientViewList", patientViewList1);
		modelMap.put("patientViewAllList", patientViewAllList1);
		return "patient/patientView";
	}

	@RequestMapping({"/st003Cryxxb/f_view/viewframe"})
	public String X(HttpServletRequest request, ModelMap modelMap) {
		Map formData = s.k(request);
		formData.remove("src");
		modelMap.put("formData", formData);
		return "patient/patientViewFrame";
	}

	@RequestMapping({"/st003Cryxxb/f_view/toriskFacto"})
	@SqlLog(p = "患者信息--重点风险因素")
	public String R(HttpServletRequest request, ModelMap modelMap, String zyid) {
		String time = this.j.findByParamCode(Param.NIS_CGPG_TIME);
		if (ab.isNotEmpty(zyid)) {
			St004Yzxxb st004Yzxxb = new St004Yzxxb();
			st004Yzxxb.setZyid(zyid);
			st004Yzxxb.setIsKjyw(Integer.valueOf(1));
			st004Yzxxb.setDays(time);
			List kjSt004List = this.bu.findByZyid(st004Yzxxb);
			modelMap.put("kjSt004List", kjSt004List);
			st004Yzxxb.setIsKjyw((Integer) null);
			st004Yzxxb.setFlagJr(Integer.valueOf(1));
			st004Yzxxb.setDays(time);
			List qxSt004List = this.bu.findByZyid(st004Yzxxb);
			modelMap.put("qxSt004List", qxSt004List);
			List viewMdrList = this.cn.findCheckOutbacteria(zyid);
			modelMap.put("viewMdrList", viewMdrList);
			List st005List = this.bO.findListByZyid(zyid);
			modelMap.put("st005List", st005List);
			List st006List = this.bN.findAbnormalByZyid(zyid);
			modelMap.put("st006List", st006List);
		}

		return "patient/riskFacto";
	}

	@RequestMapping({"/st003Cryxxb/f_json/findCryxxList"})
	@ResponseBody
	@SqlLog(p = "患者信息--就诊记录")
	public void ac(HttpServletRequest request, HttpServletResponse response, String patientId) {
		List st003CryxxbList = null;
		MyPage page = null;
		if (ab.isNotEmpty(patientId)) {
			st003CryxxbList = this.bg.findCryxxByPatientId(patientId);
			page = new MyPage(1, st003CryxxbList.size(), st003CryxxbList.size(), st003CryxxbList);
		}

		this.a(response, page);
	}

	@RequestMapping({"/st003Cryxxb/f_json/findMzList"})
	@ResponseBody
	public void aq(HttpServletRequest request, HttpServletResponse response, String patientId) {
		List st020ClinicPatientsList = null;
		MyPage page = null;
		if (ab.isNotEmpty(patientId)) {
			st020ClinicPatientsList = this.bh.getByPatientId(patientId);
			page = new MyPage(1, st020ClinicPatientsList.size(), st020ClinicPatientsList.size(),
					st020ClinicPatientsList);
		}

		this.a(response, page);
	}

	@RequestMapping({"/st003Cryxxb/f_json/query"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String q, int page, int size) {
		St003Cryxxb st003Cryxxb = new St003Cryxxb();
		st003Cryxxb.setSearchString(q);
		st003Cryxxb.setPage(Integer.valueOf(page));
		st003Cryxxb.setSize(Integer.valueOf(size));
		List list = this.bg.m(st003Cryxxb);
		this.b(response, list);
	}

	@RequestMapping({"/st003Cryxxb/f_view/toList"})
	public String z(HttpServletRequest request, ModelMap modelMap, String searchString, String patientType)
			throws UnsupportedEncodingException {
		List allowBKs = this.p.u("cdc_card_type", (String) null);
		if (ab.isNotEmpty(searchString)) {
			modelMap.put("searchString", URLDecoder.decode(searchString, "utf-8"));
		}

		modelMap.put("AllBK", allowBKs);
		modelMap.put("cgpg", this.j.findByParamCode(Param.NIS_HXJ_CGPGSHOW));
		List menus = (List) this.b(request, "user_menus");
		Iterator menu = menus.iterator();

		while (menu.hasNext()) {
			AcMenu isShowGw = (AcMenu) menu.next();
			if (isShowGw.getMenuNo().equals("C0202")) {
				modelMap.put("isByShow", Integer.valueOf(1));
			}
		}

		byte isShowGw1 = 0;
		Iterator arg8 = menus.iterator();

		while (arg8.hasNext()) {
			AcMenu menu1 = (AcMenu) arg8.next();
			if (menu1.getMenuNo().indexOf("K") > -1) {
				isShowGw1 = 1;
			}
		}

		modelMap.put("isShowGw", Integer.valueOf(isShowGw1));
		if (ab.isNotEmpty(patientType)) {
			modelMap.put("patientType", patientType);
		}

		return "patient/patientInHospList";
	}

	@RequestMapping({"/st003Cryxxb/f_json/findPatientList"})
	@ResponseBody
	@SqlLog(p = "患者信息查询--患者信息列表")
	public void a(HttpServletRequest request, HttpServletResponse response, St003Cryxxb st003Cryxxb) {
		MyPage page = this.bg.n(st003Cryxxb);
		this.a(response, page);
	}

	@RequestMapping({"/st003Cryxxb/f_view/toHighRiskNewbornList"})
	public String Y(HttpServletRequest request, ModelMap modelMap) {
		Date currDate = f.getCurDate();
		modelMap.put("queryEndDate", f.c(currDate, "yyyy-MM-dd"));
		modelMap.put("queryStartDate", f.c(f.a(currDate, -7), "yyyy-MM-dd"));
		return "patient/highRiskNewbornList";
	}

	@RequestMapping({"/st003Cryxxb/f_json/findNewbornList"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, St003Cryxxb st003Cryxxb) {
		MyPage page = this.bg.o(st003Cryxxb);
		this.a(response, page);
	}

	@RequestMapping({"/st003Cryxxb/f_view/patientMzList"})
	public String S(HttpServletRequest request, ModelMap modelMap, String patientType) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.c(request);
		List allowBKs = this.p.u("cdc_card_type", (String) null);
		modelMap.put("AllBK", allowBKs);
		modelMap.put("queryStartDate", sdf.format(Long.valueOf((new Date()).getTime() - 604800000L)));
		modelMap.put("queryEndDate", sdf.format(Long.valueOf((new Date()).getTime())));
		if (ab.isNotEmpty(patientType)) {
			modelMap.put("patientType", patientType);
		}

		return "patient/patientMzList";
	}
}