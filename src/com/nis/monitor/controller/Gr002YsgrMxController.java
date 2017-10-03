package com.nis.monitor.controller;

import com.nis.access.entity.AcAccount;
import com.nis.access.entity.AcAccountConfig;
import com.nis.access.service.AcAccountConfigService;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.e;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.follow.service.FoPatientService;
import com.nis.mdr.entity.Xn011Dclymx;
import com.nis.mdr.service.Xn011DclymxService;
import com.nis.monitor.entity.Gr002YsgrMx;
import com.nis.monitor.service.Bk002GrzdService;
import com.nis.monitor.service.Gr002YsgrMxService;
import com.nis.organization.entity.Dep;
import com.nis.organization.service.DepService;
import com.nis.param.service.SysParamService;
import com.nis.patient.entity.St002Zdxxb;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.service.St002ZdxxbService;
import com.nis.patient.service.St003CryxxbService;
import com.nis.patient.service.St009SjbbService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Gr002YsgrMxController extends BaseController {
	private static final Logger c = Logger.getLogger(Gr002YsgrMxController.class);
	@Autowired
	private Gr002YsgrMxService bX;
	@Autowired
	private SysParamService j;
	@Autowired
	private Bk002GrzdService us;
	@Autowired
	private St003CryxxbService bg;
	@Autowired
	private St009SjbbService bE;
	@Autowired
	private St002ZdxxbService bv;
	@Autowired
	private Xn011DclymxService cn;
	@Autowired
	private FoPatientService qW;
	@Autowired
	private AcAccountConfigService b;
	@Autowired
	private DepService e;

	@RequestMapping({"/gr002YsgrMx/f_view/toWarningResults"})
	public String I(HttpServletRequest request, ModelMap modelMap, String zyid) {
		return "monitor/warningResults";
	}

	@RequestMapping({"/gr002YsgrMx/f_view/toSsbbList"})
	public String C(HttpServletRequest request, ModelMap modelMap) {
		return "monitor/ssbbList";
	}

	@RequestMapping({"/gr002YsgrMx/f_json/findWarningResults"})
	@ResponseBody
	public void q(HttpServletRequest request, HttpServletResponse response, String zyid, String regId,
			String gr2Relid) {
		List gr002YsgrMxList = null;
		LinkedList regr002YsgrMxList = new LinkedList();
		if (ab.isNotEmpty(zyid)) {
			gr002YsgrMxList = this.bX.a(zyid, regId, gr2Relid, (Integer) null);
			Iterator arg8 = gr002YsgrMxList.iterator();

			label27 : while (true) {
				while (true) {
					if (!arg8.hasNext()) {
						break label27;
					}

					Gr002YsgrMx gr002YsgrMx = (Gr002YsgrMx) arg8.next();
					List list = this.bX.findWarningResults2(gr002YsgrMx.getZyid(), gr002YsgrMx.getInfectCode(),
							gr002YsgrMx.getRegId(), (Integer) null);
					if (list.size() > 0) {
						Iterator arg11 = list.iterator();

						while (arg11.hasNext()) {
							Gr002YsgrMx gr002 = (Gr002YsgrMx) arg11.next();
							gr002.setInfectTypeId(gr002YsgrMx.getInfectTypeId());
							gr002.setStartAt(gr002YsgrMx.getStartAt());
							gr002.setReportType(gr002YsgrMx.getReportType());
							gr002.setState(gr002YsgrMx.getState());
							gr002.setItemName(gr002YsgrMx.getItemName());
							gr002.setPatientName(gr002YsgrMx.getPatientName());
							gr002.setRegId(gr002YsgrMx.getRegId());
							gr002.setGr2Relid(gr002YsgrMx.getGr2Relid());
							regr002YsgrMxList.add(gr002);
						}
					} else {
						regr002YsgrMxList.add(gr002YsgrMx);
					}
				}
			}
		}

		this.a(response, regr002YsgrMxList);
	}

	@RequestMapping({"/gr002YsgrMx/f_view/toInfectionMonitor"})
	public String v(HttpServletRequest request, ModelMap modelMap, String startDate, String endDate) {
		if (StringUtils.isBlank(startDate) && StringUtils.isBlank(endDate)) {
			String account = this.j.findByParamCode(Param.NIS_MONITOR_INFECT_DAYS);
			Date currDate = f.getCurDate();
			endDate = f.formatDate(currDate);
			startDate = f.formatDate(f.a(currDate, -NumberUtils.toInt(account)));
		}

		modelMap.put("queryEndTime", endDate);
		modelMap.put("queryStartDate", startDate);
		AcAccount account1 = (AcAccount) this.b(request);
		if (StringUtils.isBlank(account1.getScopeInfo())) {
			modelMap.put("isAll", "1");
		}

		return "monitor/infectionMonitor";
	}

	@RequestMapping({"/gr002YsgrMx/f_json/findInfectionCounts"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Gr002YsgrMx gr002YsgrMx,
			Integer dataRange) {
		List gr002YsgrMxList = null;
		if (gr002YsgrMx != null) {
			if (dataRange != null && dataRange.intValue() == 1) {
				LoginUser page = this.d(request);
				if (ab.isNotEmpty(page.getScopeInfo())) {
					String[] conformNum = page.getScopeInfo().split(",");
					gr002YsgrMx.setDeptIdIn(Arrays.asList(conformNum));
				} else {
					gr002YsgrMx.setDeptIdIn(Arrays.asList(new String[]{"0"}));
				}
			}

			gr002YsgrMxList = this.bX.findInfectionCounts(gr002YsgrMx);
		}

		MyPage page1 = new MyPage(1, gr002YsgrMxList.size(), gr002YsgrMxList.size(), gr002YsgrMxList);
		int conformNum1 = 0;
		int reportNum = 0;
		int veryLikelyNum = 0;
		int followNum = 0;

		Gr002YsgrMx gr002YsgrMxFooter;
		for (Iterator gr002YsgrMxFooterList = gr002YsgrMxList.iterator(); gr002YsgrMxFooterList
				.hasNext(); followNum += gr002YsgrMxFooter.getFollowNum().intValue()) {
			gr002YsgrMxFooter = (Gr002YsgrMx) gr002YsgrMxFooterList.next();
			conformNum1 += gr002YsgrMxFooter.getConformNum().intValue();
			reportNum += gr002YsgrMxFooter.getReportNum().intValue();
			veryLikelyNum += gr002YsgrMxFooter.getVeryLikelyNum().intValue();
		}

		gr002YsgrMxFooter = new Gr002YsgrMx();
		gr002YsgrMxFooter.setDeptName("合计");
		gr002YsgrMxFooter.setConformNum(Integer.valueOf(conformNum1));
		gr002YsgrMxFooter.setReportNum(Integer.valueOf(reportNum));
		gr002YsgrMxFooter.setVeryLikelyNum(Integer.valueOf(veryLikelyNum));
		gr002YsgrMxFooter.setFollowNum(Integer.valueOf(followNum));
		ArrayList gr002YsgrMxFooterList1 = new ArrayList();
		gr002YsgrMxFooterList1.add(gr002YsgrMxFooter);
		page1.setFooter(gr002YsgrMxFooterList1);
		this.a(response, page1);
	}

	@RequestMapping({"/gr002YsgrMx/f_view/toWarningTree"})
	public String w(HttpServletRequest request, ModelMap modelMap, String type, String isAll) {
		if (StringUtils.isBlank(type)) {
			type = "wait";
		}

		modelMap.put("type", type);
		Gr002YsgrMx gr002YsgrMx = new Gr002YsgrMx();
		AcAccount account = (AcAccount) this.b(request);
		if ("1".equals(isAll)) {
			modelMap.put("isAll", "1");
			gr002YsgrMx.setDeptIdIn(Arrays.asList(new String[]{"0"}));
		} else if (StringUtils.isNotEmpty(account.getScopeInfo())) {
			modelMap.put("isAll", "0");
			String[] method = account.getScopeInfo().split(",");
			gr002YsgrMx.setDeptIdIn(Arrays.asList(method));
		} else {
			gr002YsgrMx.setDeptIdIn(Arrays.asList(new String[]{"0"}));
		}

		String method1 = "findAccordInfection";
		gr002YsgrMx.setState(Integer.valueOf(1));
		if ("favorit".equals(type)) {
			method1 = "findFavorit";
		} else if ("all".equals(type)) {
			gr002YsgrMx.setState((Integer) null);
			method1 = "findAllInfection";
		}

		modelMap.put("method", method1);
		Date currDate = new Date();
		String queryDate = f.m(currDate);
		modelMap.put("queryDate", queryDate);
		gr002YsgrMx.setIsInHosp(Integer.valueOf(1));
		gr002YsgrMx.setInfectTypeId(Integer.valueOf(1));
		gr002YsgrMx = this.bX.b(gr002YsgrMx, type);
		modelMap.put("inNum", gr002YsgrMx.getInNum());
		modelMap.put("outNum", gr002YsgrMx.getOutNum());
		return "monitor/warningTree";
	}

	@RequestMapping({"/gr002YsgrMx/f_view/toLiveInfect"})
	public String D(HttpServletRequest request, ModelMap modelMap) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		Date endDate = null;
		date.set(5, date.get(5) - 1);

		try {
			endDate = sdf.parse(sdf.format(date.getTime()));
		} catch (ParseException arg7) {
			arg7.printStackTrace();
		}

		String day = sdf.format(endDate);
		modelMap.put("day", day);
		modelMap.put("reportUrl", this.j.findByParamCode(Param.NIS_REPORT_URL));
		return "monitor/liveInfect";
	}

	@RequestMapping({"/gr002YsgrMx/f_json/findLiveInfect"})
	@ResponseBody
	@SqlLog(p = "患者信息--实时现患率列表")
	public void a(HttpServletRequest request, HttpServletResponse response, String startDate, String endDate,
			String[] list, String listStr) {
		List listMap = this.bX.findLiveInfect(startDate, endDate);
		this.a(response, listMap);
	}

	@RequestMapping({"/gr002YsgrMx/view/toLiveInfectJz"})
	public String x(HttpServletRequest request, ModelMap modelMap, String startDate, String endDate) {
		modelMap.put("startDate", startDate);
		return "monitor/liveInfectJz";
	}

	@RequestMapping({"/gr002YsgrMx/f_json/findLiveInfectJz"})
	@ResponseBody
	public void L(HttpServletRequest request, HttpServletResponse response, String startDate, String endDate) {
		List listMap = this.bX.findLiveInfect(startDate, endDate);
		this.a(response, listMap);
	}

	@RequestMapping({"/gr002YsgrMx/f_json/saveLiveInfectJz"})
	@ResponseBody
	@SqlLog(p = "患者信息--保存科室现患率基准数")
	public void a(HttpServletRequest request, HttpServletResponse response, String startDate, String endDate, Dep dep) {
		Result result = null;
		List deptList = dep.getDeptList();

		Dep e;
		try {
			for (Iterator arg8 = deptList.iterator(); arg8.hasNext(); result = this.e.a(e)) {
				e = (Dep) arg8.next();
			}
		} catch (Exception arg9) {
			c.error("获取信息异常!", arg9);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/gr002YsgrMx/f_json/findInAndOutCount"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Gr002YsgrMx gr002YsgrMx, Integer dataRange,
			Date queryDate, String type) {
		if (gr002YsgrMx != null) {
			LoginUser user = this.d(request);
			if (ab.isNotEmpty(user.getScopeInfo())) {
				String[] deptIds = user.getScopeInfo().split(",");
				gr002YsgrMx.setDeptIdIn(Arrays.asList(deptIds));
			} else {
				gr002YsgrMx.setDeptIdIn(Arrays.asList(new String[]{"0"}));
			}

			if (2 == gr002YsgrMx.getIsInHosp().intValue() && queryDate != null) {
				gr002YsgrMx.setQueryStartDate(f.a(queryDate, false));
				gr002YsgrMx.setQueryEndDate(f.b(queryDate, false));
			}

			if ("wait".equals(type)) {
				gr002YsgrMx.setState(Integer.valueOf(1));
			}

			gr002YsgrMx = this.bX.b(gr002YsgrMx, type);
		}

		this.a(response, gr002YsgrMx);
	}

	@RequestMapping({"/gr002YsgrMx/f_view/toCasesWarning"})
	public String E(HttpServletRequest request, ModelMap modelMap) {
		return "monitor/casesWarning";
	}

	@RequestMapping({"/gr002YsgrMx/f_json/findAccordInfection"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Gr002YsgrMx gr002YsgrMx, Integer dataRange,
			String id, Date queryDate) {
		List list = null;
		if (gr002YsgrMx != null) {
			if (dataRange != null && dataRange.intValue() == 1) {
				LoginUser user = this.d(request);
				if (ab.isNotEmpty(user.getScopeInfo())) {
					String[] deptIds = user.getScopeInfo().split(",");
					gr002YsgrMx.setDeptIdIn(Arrays.asList(deptIds));
				} else {
					gr002YsgrMx.setDeptIdIn(Arrays.asList(new String[]{"0"}));
				}
			}

			if (2 == gr002YsgrMx.getIsInHosp().intValue() && queryDate != null) {
				gr002YsgrMx.setQueryStartDate(f.a(queryDate, false));
				gr002YsgrMx.setQueryEndDate(f.b(queryDate, false));
			}

			gr002YsgrMx.setState(Integer.valueOf(1));
			list = this.bX.a(gr002YsgrMx, id);
		}

		this.a(response, list);
	}

	@RequestMapping({"/gr002YsgrMx/f_json/findFavorit"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, Gr002YsgrMx gr002YsgrMx, Integer dataRange,
			String id, Date queryDate) {
		List list = null;
		if (gr002YsgrMx != null) {
			if (dataRange != null && dataRange.intValue() == 1) {
				LoginUser user = this.d(request);
				if (ab.isNotEmpty(user.getScopeInfo())) {
					String[] deptIds = user.getScopeInfo().split(",");
					gr002YsgrMx.setDeptIdIn(Arrays.asList(deptIds));
				} else {
					gr002YsgrMx.setDeptIdIn(Arrays.asList(new String[]{"0"}));
				}
			}

			if (2 == gr002YsgrMx.getIsInHosp().intValue() && queryDate != null) {
				gr002YsgrMx.setQueryStartDate(f.a(queryDate, false));
				gr002YsgrMx.setQueryEndDate(f.b(queryDate, false));
			}

			list = this.bX.c(gr002YsgrMx, id);
		}

		this.a(response, list);
	}

	@RequestMapping({"/gr002YsgrMx/f_json/findAllInfection"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, Gr002YsgrMx gr002YsgrMx, Integer dataRange,
			String id, Date queryDate) {
		List list = null;
		if (gr002YsgrMx != null) {
			if (dataRange != null && dataRange.intValue() == 1) {
				LoginUser user = this.d(request);
				if (ab.isNotEmpty(user.getScopeInfo())) {
					String[] deptIds = user.getScopeInfo().split(",");
					gr002YsgrMx.setDeptIdIn(Arrays.asList(deptIds));
				} else {
					gr002YsgrMx.setDeptIdIn(Arrays.asList(new String[]{"0"}));
				}
			}

			if (2 == gr002YsgrMx.getIsInHosp().intValue() && queryDate != null) {
				gr002YsgrMx.setQueryStartDate(f.a(queryDate, false));
				gr002YsgrMx.setQueryEndDate(f.b(queryDate, false));
			}

			list = this.bX.a(gr002YsgrMx, id);
		}

		this.a(response, list);
	}

	@SqlLog(p = "感染预警--患者感染预警详情")
	@RequestMapping({"/gr002YsgrMx/f_view/toWarningPatient"})
	public String b(HttpServletRequest request, ModelMap modelMap, String zyid, String regId, String gr2Relid,
			Integer infectTypeId) {
		if (ab.isNotEmpty(zyid)) {
			AcAccount ac = (AcAccount) this.b(request);
			if (com.nis.comm.enums.e.fE.getValue().equals(ac.getRoleCur().getRoleType())) {
				modelMap.put("clinical", Integer.valueOf(1));
			}

			St003Cryxxb st003Cryxxb = this.bg.get(zyid);
			modelMap.put("st003Cryxxb", st003Cryxxb);
			Map focusMap = this.bE.ct(zyid);
			modelMap.put("focusMap", focusMap);
			List st002List = this.bv.findByZyid(zyid);
			ArrayList inZdxx = new ArrayList();
			ArrayList outZdxx = new ArrayList();
			Iterator foPatientList = st002List.iterator();

			while (foPatientList.hasNext()) {
				St002Zdxxb gr002YsgrMxList = (St002Zdxxb) foPatientList.next();
				if ("1".equals(gr002YsgrMxList.getDiagnosisType())) {
					inZdxx.add(gr002YsgrMxList);
				} else if ("2".equals(gr002YsgrMxList.getDiagnosisType())) {
					outZdxx.add(gr002YsgrMxList);
				}
			}

			modelMap.put("inZdxx", inZdxx);
			modelMap.put("outZdxx", outZdxx);
			List gr002YsgrMxList1 = this.bX.a(zyid, regId, gr2Relid, infectTypeId);
			Iterator arg14 = gr002YsgrMxList1.iterator();

			while (arg14.hasNext()) {
				Gr002YsgrMx foPatientList1 = (Gr002YsgrMx) arg14.next();
				List list = this.bX.findWarningResults2(foPatientList1.getZyid(), foPatientList1.getInfectCode(),
						foPatientList1.getRegId(), infectTypeId);
				foPatientList1.setGr002YsgrMxList(list);
			}

			if (st003Cryxxb != null) {
				List foPatientList2 = this.qW.getByPatientId(st003Cryxxb.getPatientId());
				if (foPatientList2.size() > 0) {
					modelMap.put("follow", "1");
				}
			}

			modelMap.put("gr002YsgrMxList", gr002YsgrMxList1);
		}

		return "monitor/warningPatientNew";
	}

	@RequestMapping({"/gr002YsgrMx/f_json/findInfectionDetail"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, Gr002YsgrMx gr002YsgrMx,
			Integer dataRange) {
		List gr002YsgrMxList = null;
		if (gr002YsgrMx != null) {
			gr002YsgrMxList = this.bX.findInfectionDetail(gr002YsgrMx);
		}

		this.a(response, gr002YsgrMxList);
	}

	@RequestMapping({"/gr002YsgrMx/f_view/toConfirmInfect"})
	public String F(HttpServletRequest request, ModelMap modelMap) {
		Date currDate = f.getCurDate();
		modelMap.put("queryEndTime", f.formatDate(currDate));

		try {
			modelMap.put("queryStartDate", f.formatDate(f.a(currDate, -30)));
		} catch (NumberFormatException arg4) {
			arg4.printStackTrace();
		}

		return "monitor/confirmInfect";
	}

	@RequestMapping({"/gr002YsgrMx/f_json/findConfirmInfect"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Gr002YsgrMx gr002YsgrMx) {
		MyPage data = null;
		if (gr002YsgrMx != null) {
			data = this.bX.b(gr002YsgrMx);
		}

		this.a(response, data);
	}

	@RequestMapping({"/gr002YsgrMx/f_view/toDelDiagnosis"})
	public String J(HttpServletRequest request, ModelMap modelMap, String relid) {
		if (ab.isNotEmpty(relid)) {
			List bk002List = this.us.getReportInfect(relid, "1");
			modelMap.put("bk002List", bk002List);
		}

		return "monitor/delInfectCases";
	}

	@RequestMapping({"/gr002YsgrMx/f_json/delDiagnosis"})
	@ResponseBody
	@SqlLog(p = "报卡--删除报卡")
	public void a(HttpServletRequest request, HttpServletResponse response, Gr002YsgrMx gr002YsgrMx, String delReason,
			String[] relids) {
		Result result = null;
		List dataList = null;

		try {
			result = new Result();
			LoginUser e = this.d(request);
			if (relids != null && relids.length > 0) {
				dataList = Arrays.asList(relids);
				if (ab.isNotEmpty(gr002YsgrMx.getRelid()) && ab.isNotEmpty(gr002YsgrMx.getZyid())
						&& dataList.size() > 0) {
					this.bX.a(gr002YsgrMx, delReason, dataList, e);
					result.setResult("success");
				} else {
					result = new Result("error", "关键参数缺失");
				}
			} else if (ab.isNotEmpty(gr002YsgrMx.getRelid()) && ab.isNotEmpty(gr002YsgrMx.getZyid())) {
				this.bX.a(gr002YsgrMx, delReason, e);
				result.setResult("success");
			} else {
				result = new Result("error", "关键参数缺失");
			}

			if (ab.isNotEmpty(gr002YsgrMx.getRelid())) {
				this.cn.clearInfectTypeByCardId(gr002YsgrMx.getRelid());
			}
		} catch (Exception arg8) {
			c.error("获取信息异常!", arg8);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/gr002YsgrMx/f_view/toHasRuleOutList"})
	public String G(HttpServletRequest request, ModelMap modelMap) {
		Date currDate = f.getCurDate();
		modelMap.put("queryEndTime", f.formatDate(currDate));

		try {
			modelMap.put("queryStartDate", f.formatDate(f.a(currDate, -30)));
		} catch (NumberFormatException arg4) {
			arg4.printStackTrace();
		}

		return "monitor/hasRuleOutList";
	}

	@RequestMapping({"/gr002YsgrMx/f_json/findHasRuleOut"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, Gr002YsgrMx gr002YsgrMx) {
		MyPage page = this.bX.c(gr002YsgrMx);
		this.a(response, page);
	}

	@RequestMapping({"/gr002YsgrMx/f_view/toRuleOutInfect"})
	public String H(HttpServletRequest request, ModelMap modelMap) {
		return "monitor/ruleOutInfect";
	}

	@RequestMapping({"/gr002YsgrMx/f_json/dingzhi"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, Gr002YsgrMx gr002YsgrMx) {
		Result result = null;

		try {
			result = new Result();
			Integer e = Integer.valueOf(1);
			String[] testOrderNo = gr002YsgrMx.getTestOrderNo();
			String testOrderNos = "";
			if (testOrderNo != null) {
				for (int i = 0; i < testOrderNo.length; ++i) {
					if (testOrderNos.indexOf(testOrderNo[i]) <= -1) {
						testOrderNos = testOrderNos + testOrderNo[i];
						if (i + 1 != testOrderNo.length) {
							testOrderNos = testOrderNos + ",";
						}
					}

					Xn011Dclymx xn011Dclymx = new Xn011Dclymx();
					xn011Dclymx.setTestOrderNo(testOrderNo[i]);
					xn011Dclymx.setInfectTypeId(Integer.valueOf(3));
					this.cn.updateByTestOrderNo(xn011Dclymx);
				}

				gr002YsgrMx.setTestOrderNos(testOrderNos);
			}

			gr002YsgrMx.setOperator(this.d(request).getUsername());
			this.bX.a(gr002YsgrMx, e);
			result.setResult("success");
		} catch (Exception arg9) {
			c.error("获取信息异常!", arg9);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/gr002YsgrMx/f_json/ruleOutInfection"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, Gr002YsgrMx gr002YsgrMx,
			Integer excludeType) {
		Result result = null;

		try {
			if (gr002YsgrMx != null) {
				result = new Result();
				gr002YsgrMx.setOperator(this.d(request).getUsername());
				this.bX.a(gr002YsgrMx, excludeType);
				result.setResult("success");
			} else {
				result = new Result("error", "参数丢失");
			}
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/gr002YsgrMx/f_view/toCasesReported"})
	public String I(HttpServletRequest request, ModelMap modelMap) {
		return "monitor/casesReported";
	}

	@RequestMapping({"/gr002YsgrMx/f_json/findCasesReported"})
	@ResponseBody
	@SqlLog(p = "患者信息--患者报卡列表")
	public void ab(HttpServletRequest request, HttpServletResponse response, String zyid) {
		List data = null;
		if (ab.isNotEmpty(zyid)) {
			data = this.bX.findCasesReported(zyid);
		}

		this.a(response, data);
	}

	@RequestMapping({"/gr002YsgrMx/f_view/workloadStatistics"})
	public String J(HttpServletRequest request, ModelMap modelMap) {
		String startDate = null;
		String endDate = null;
		AcAccount acAccount = (AcAccount) this.b(request);
		AcAccountConfig acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportStartDate");
		if (ab.isEmpty(startDate)) {
			startDate = acAccountConfig == null ? "" : acAccountConfig.getConfigValue();
		}

		modelMap.put("startDate", startDate);
		if (ab.isEmpty(endDate)) {
			acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportEndDate");
			endDate = acAccountConfig == null ? "" : acAccountConfig.getConfigValue();
		}

		modelMap.put("queryStartDate", startDate);
		modelMap.put("queryEndDate", endDate);
		return "monitor/workloadStatistics";
	}

	@RequestMapping({"/gr002YsgrMx/f_view/workloadStatisticsTable"})
	@SqlLog(p = "预警处理工作量统计")
	public String a(HttpServletRequest request, ModelMap modelMap, Gr002YsgrMx gr002YsgrMx) {
		modelMap.put("qsd", gr002YsgrMx.getQueryStartDate());
		modelMap.put("qed", gr002YsgrMx.getQueryEndDate());
		List workloadStatistics = this.bX.workloadStatistics(gr002YsgrMx);
		modelMap.put("wsl", workloadStatistics);
		return "monitor/workloadStatisticsTable";
	}

	@RequestMapping({"/gr002YsgrMx/f_view/workloadDetail"})
	@ResponseBody
	@SqlLog(p = "预警处理统计详情")
	public void d(HttpServletRequest request, HttpServletResponse response, Gr002YsgrMx gr002YsgrMx) {
		List workloadDetail = this.bX.workloadDetail(gr002YsgrMx);
		this.a(response, workloadDetail);
	}

	@RequestMapping({"/gr002YsgrMx/f_view/exportWST"})
	@ResponseBody
	@SqlLog(p = "预警处理工作量统计导出")
	public void e(HttpServletRequest request, HttpServletResponse response, Gr002YsgrMx gr002YsgrMx) {
		this.bX.a(response, gr002YsgrMx);
	}

	@RequestMapping({"/gr002YsgrMx/f_json/warnCount"})
	@ResponseBody
	public void M(HttpServletRequest request, HttpServletResponse response, String employeeId, String deptId) {
		Result r = new Result();
		Gr002YsgrMx gr002YsgrMx = new Gr002YsgrMx();

		try {
			if (ab.isEmpty(deptId)) {
				LoginUser e = this.d(request);
				if (ab.isNotEmpty(e.getScopeInfo())) {
					String[] deptIds = e.getScopeInfo().split(",");
					gr002YsgrMx.setDeptIdIn(Arrays.asList(deptIds));
				} else {
					gr002YsgrMx.setDeptIdIn(Arrays.asList(new String[]{"0"}));
				}
			} else {
				String[] e1 = deptId.split(",");
				gr002YsgrMx.setDeptIdIn(Arrays.asList(e1));
			}

			gr002YsgrMx.setInfectTypeId(Integer.valueOf(1));
			gr002YsgrMx.setState(Integer.valueOf(1));
			gr002YsgrMx.setIsInHosp(Integer.valueOf(1));
			gr002YsgrMx = this.bX.b(gr002YsgrMx, "1");
			r.setData("" + gr002YsgrMx.getInNum());
			r.setResult("success");
		} catch (Exception arg8) {
			r.setData("");
			r.setResult("error");
		}

		this.a(response, r);
	}
}