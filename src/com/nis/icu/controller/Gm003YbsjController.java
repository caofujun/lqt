package com.nis.icu.controller;

import com.nis.access.entity.AcAccount;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.r;
import com.nis.icu.entity.Gm003Ybsj;
import com.nis.icu.entity.IcuCount;
import com.nis.icu.service.Gm003YbsjService;
import com.nis.icu.service.Gm004JcmxService;
import com.nis.organization.entity.Dep;
import com.nis.organization.service.DepService;
import com.nis.organization.service.UnitService;
import com.nis.param.service.SysParamService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
public class Gm003YbsjController extends BaseController {
	private static final Logger c = Logger.getLogger(Gm003YbsjController.class);
	@Autowired
	private Gm003YbsjService sd;
	@Autowired
	private DepService e;
	@Autowired
	private Gm004JcmxService bP;
	@Autowired
	private SysParamService j;
	@Autowired
	private UnitService se;

	@RequestMapping({"/gm003Ybsj/f_view/index"})
	public String o(HttpServletRequest request, ModelMap modelMap, String deptId, String monthDate) {
		if (ab.isNotEmpty(monthDate)) {
			modelMap.put("monthDate", monthDate);
		} else {
			modelMap.put("monthDate", f.c(f.a(new Date(), -1), "yyyy-MM"));
		}

		Dep dep = new Dep();
		dep.setIficu(Long.valueOf(1L));
		dep.setPage(Integer.valueOf(1));
		dep.setSize(Integer.valueOf(200));
		MyPage icuDepPage = this.e.b(dep);
		dep = new Dep();
		dep.setIfbedicu(Long.valueOf(1L));
		dep.setPage(Integer.valueOf(1));
		dep.setSize(Integer.valueOf(200));
		MyPage bedIcuDepPage = this.e.b(dep);
		icuDepPage.getRows().addAll(bedIcuDepPage.getRows());
		if (ab.isEmpty(deptId) && icuDepPage.getRows().size() > 0) {
			deptId = ((Dep) icuDepPage.getRows().get(0)).getDeptId();
		}

		modelMap.put("bedicu", Integer.valueOf(1));
		modelMap.put("deptId", deptId);
		modelMap.put("icuList", icuDepPage.getRows());
		return "icu/gm003YbsjList";
	}

	@RequestMapping({"/gm003Ybsj/f_json/findMainFx"})
	@ResponseBody
	public void G(HttpServletRequest request, HttpServletResponse response, String dataRange) {
		Result result = null;

		try {
			if (ab.isEmpty(dataRange)) {
				dataRange = "-6";
			}

			Date e = f.p(f.a(f.getCurDate(), -1));
			Date startDate = f.q(f.a(e, Integer.parseInt(dataRange)));
			LinkedList ywFx = new LinkedList();
			LinkedList bhFx = new LinkedList();
			LinkedList xAxisData = new LinkedList();
			LinkedList legendData = new LinkedList();
			LinkedList series = new LinkedList();
			List list = this.sd.findMainFx(startDate, e);
			Iterator arg13 = list.iterator();

			while (arg13.hasNext()) {
				Map resultMap = (Map) arg13.next();
				xAxisData.add(f.formatDate((Date) resultMap.get("datetime")));
				ywFx.add(Integer.valueOf(r.d(resultMap.get("ywFx"))));
				bhFx.add(Integer.valueOf(r.d(resultMap.get("bhFx"))));
			}

			series.add(ywFx);
			series.add(bhFx);
			legendData.add("医务人员腹泻");
			legendData.add("病患腹泻");
			HashMap resultMap1 = new HashMap();
			resultMap1.put("legendData", legendData);
			resultMap1.put("xAxisData", xAxisData);
			resultMap1.put("series", series);
			result = new Result("success");
			result.setData(resultMap1);
		} catch (Exception arg14) {
			result = new Result("error", arg14.getMessage());
			arg14.printStackTrace();
		}

		this.a(response, result);
	}

	@RequestMapping({"/gm003Ybsj/f_json/findMainKs"})
	@ResponseBody
	public void H(HttpServletRequest request, HttpServletResponse response, String dataRange) {
		Result result = null;

		try {
			if (ab.isEmpty(dataRange)) {
				dataRange = "-6";
			}

			Date e = f.p(f.a(f.getCurDate(), -1));
			Date startDate = f.q(f.a(e, Integer.parseInt(dataRange)));
			LinkedList ywks = new LinkedList();
			LinkedList bhks = new LinkedList();
			LinkedList xAxisData = new LinkedList();
			LinkedList legendData = new LinkedList();
			LinkedList series = new LinkedList();
			List list = this.sd.findMainKs(startDate, e);
			Iterator arg13 = list.iterator();

			while (arg13.hasNext()) {
				Map resultMap = (Map) arg13.next();
				xAxisData.add(f.formatDate((Date) resultMap.get("datetime")));
				ywks.add(Integer.valueOf(r.d(resultMap.get("ywks"))));
				bhks.add(Integer.valueOf(r.d(resultMap.get("bhks"))));
			}

			series.add(ywks);
			series.add(bhks);
			legendData.add("医务人员咳嗽");
			legendData.add("病患咳嗽");
			HashMap resultMap1 = new HashMap();
			resultMap1.put("legendData", legendData);
			resultMap1.put("xAxisData", xAxisData);
			resultMap1.put("series", series);
			result = new Result("success");
			result.setData(resultMap1);
		} catch (Exception arg14) {
			result = new Result("error", arg14.getMessage());
			arg14.printStackTrace();
		}

		this.a(response, result);
	}

	@RequestMapping({"/gm003Ybsj/f_view/jcList"})
	public String p(HttpServletRequest request, ModelMap modelMap, String deptId, String dayDate) {
		modelMap.put("deptId", deptId);
		modelMap.put("dayDate", dayDate);
		return "icu/jcList";
	}

	@RequestMapping({"/gm003Ybsj/f_view/kfjcList"})
	public String q(HttpServletRequest request, ModelMap modelMap, String startDate, String endDate) {
		if (StringUtils.isBlank(startDate) && StringUtils.isBlank(endDate)) {
			Date ac = f.getCurDate();
			endDate = f.formatDate(ac);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(ac);
			c.add(2, -1);
			Date m = c.getTime();
			startDate = sdf.format(m);
		}

		modelMap.put("queryEndTime", endDate);
		modelMap.put("queryStartDate", startDate);
		AcAccount ac1 = (AcAccount) this.b(request);
		modelMap.put("userId", ac1.getUsername());
		modelMap.put("deptId", ac1.getDepNo());
		return "icu/gm003YbsjKfjc";
	}

	@RequestMapping({"/gm003Ybsj/f_json/kfjcPageQuery"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Gm003Ybsj gm003Ybsj) {
		MyPage myPage = this.sd.b(gm003Ybsj);
		this.a(response, myPage);
	}

	@RequestMapping({"/gm003Ybsj/f_view/gm003YbsjIndex"})
	public String v(HttpServletRequest request, ModelMap modelMap) {
		return "icu/gm003YbsjIndex";
	}

	@RequestMapping({"/gm003Ybsj/f_view/kfjcEdit"})
	public String a(HttpServletRequest request, ModelMap modelMap, Gm003Ybsj gm003Ybsj) {
		modelMap.put("deptId", gm003Ybsj.getDeptId());
		if (gm003Ybsj.getCreationdate() != null) {
			List list = this.sd.findkfjcByCreationdate(gm003Ybsj);
			if (list.size() > 0) {
				Long bhksCount = ((Gm003Ybsj) list.get(0)).getBhksCount();
				Long bhfxCount = ((Gm003Ybsj) list.get(0)).getBhfxCount();
				Long ywryksCount = ((Gm003Ybsj) list.get(0)).getYwryksCount();
				Long ywryfxCount = ((Gm003Ybsj) list.get(0)).getYwryfxCount();
				Long ywryfrCount = ((Gm003Ybsj) list.get(0)).getYwryfrCount();
				Date creationdate = ((Gm003Ybsj) list.get(0)).getCreationdate();
				String deptId = ((Gm003Ybsj) list.get(0)).getDeptId();
				Gm003Ybsj gm003YbsjTemp = new Gm003Ybsj();
				gm003YbsjTemp.setBhfxCount(bhfxCount);
				gm003YbsjTemp.setBhksCount(bhksCount);
				gm003YbsjTemp.setYwryfxCount(ywryfxCount);
				gm003YbsjTemp.setYwryfrCount(ywryfrCount);
				gm003YbsjTemp.setYwryksCount(ywryksCount);
				gm003YbsjTemp.setCreationdate(creationdate);
				gm003YbsjTemp.setDeptId(deptId);
				modelMap.put("gm003Ybsj", gm003YbsjTemp);
			}
		}

		return "icu/gm003YbsjKfjcEdit";
	}

	@RequestMapping({"/gm003Ybsj/f_json/kfjcDelete"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Gm003Ybsj gm003Ybsj) {
		Result result = null;

		try {
			result = new Result();
			this.sd.kfjcDelete(gm003Ybsj);
			result.setResult("success");
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "删除失败!");
		}

		this.a(response, result);
	}

	@RequestMapping({"/gm003Ybsj/f_json/kfjcSave"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Gm003Ybsj gm003Ybsj) {
		Result result = null;

		try {
			result = new Result();
			if (gm003Ybsj.getYwryksCount() != null) {
				gm003Ybsj.setSurveyCount(gm003Ybsj.getYwryksCount());
				gm003Ybsj.setTypeId("13");
				this.sd.kfjcDelete(gm003Ybsj);
				this.sd.save(gm003Ybsj);
			}

			if (gm003Ybsj.getYwryfxCount() != null) {
				gm003Ybsj.setSurveyCount(gm003Ybsj.getYwryfxCount());
				gm003Ybsj.setTypeId("14");
				this.sd.kfjcDelete(gm003Ybsj);
				this.sd.save(gm003Ybsj);
			}

			if (gm003Ybsj.getBhksCount() != null) {
				gm003Ybsj.setSurveyCount(gm003Ybsj.getBhksCount());
				gm003Ybsj.setTypeId("15");
				this.sd.kfjcDelete(gm003Ybsj);
				this.sd.save(gm003Ybsj);
			}

			if (gm003Ybsj.getBhfxCount() != null) {
				gm003Ybsj.setSurveyCount(gm003Ybsj.getBhfxCount());
				gm003Ybsj.setTypeId("16");
				this.sd.kfjcDelete(gm003Ybsj);
				this.sd.save(gm003Ybsj);
			}

			if (gm003Ybsj.getYwryfrCount() != null) {
				gm003Ybsj.setSurveyCount(gm003Ybsj.getYwryfrCount());
				gm003Ybsj.setTypeId("17");
				this.sd.kfjcDelete(gm003Ybsj);
				this.sd.save(gm003Ybsj);
			}

			result.setResult("success");
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "保存异常!该科室的咳嗽、腹泻类型已经统计过!");
		}

		this.a(response, result);
	}

	@RequestMapping({"/gm003Ybsj/f_json/pageQueryIndex"})
	@ResponseBody
	public void a(HttpServletRequest request, ModelMap modelMap, HttpServletResponse response, String deptId,
			String dayDate) {
	}

	@RequestMapping({"/gm003Ybsj/f_view/icuList"})
	public String e(HttpServletRequest request, ModelMap modelMap, String unitId, String deptId, String monthDate) {
		modelMap.put("deptId", deptId);
		if (ab.isNotEmpty(monthDate)) {
			modelMap.put("monthDate", monthDate);
		} else {
			modelMap.put("monthDate", f.c(f.a(new Date(), -1), "yyyy-MM"));
		}

		String isKfjc = this.j.findByParamCode(Param.NIS_JCRB_IS_KFJC);
		String isTsy = this.j.findByParamCode(Param.NIS_JCRB_IS_TSY);
		modelMap.put("ISKFJC", isKfjc);
		modelMap.put("ISTSY", isTsy);
		modelMap.put("unitId", unitId);
		modelMap.put("unitFlag", this.se.getAll().size() > 1 ? "1" : "0");
		return "icu/icuList";
	}

	@RequestMapping({"/gm003Ybsj/f_view/icuListbyMonth"})
	public String r(HttpServletRequest request, ModelMap modelMap, String deptId, String unitId) {
		modelMap.put("deptId", deptId);
		modelMap.put("startMonthDate", f.getCurYear() + "-01");
		modelMap.put("endMonthDate", f.c(f.a(new Date(), -1), "yyyy-MM"));
		modelMap.put("unitId", unitId);
		modelMap.put("unitFlag", this.se.getAll().size() > 1 ? "1" : "0");
		return "icu/icuListbyMonth";
	}

	@RequestMapping({"/gm003Ybsj/f_view/dayIndex"})
	public String f(HttpServletRequest request, ModelMap modelMap, String unitId, String deptId, String dayDate) {
		if (ab.isEmpty(dayDate)) {
			modelMap.put("dayDate", f.c(f.a(new Date(), -1), "yyyy-MM-dd"));
		} else {
			modelMap.put("dayDate", dayDate);
		}

		String isKfjc = this.j.findByParamCode(Param.NIS_JCRB_IS_KFJC);
		String istsy = this.j.findByParamCode(Param.NIS_JCRB_IS_TSY);
		modelMap.put("unitId", unitId);
		modelMap.put("unitFlag", this.se.getAll().size() > 1 ? "1" : "0");
		modelMap.put("ISKFJC", isKfjc);
		modelMap.put("ISTSY", istsy);
		return "icu/gm003YbsjDayList";
	}

	@RequestMapping({"/gm003Ybsj/f_json/pageQueryDay"})
	@ResponseBody
	@SqlLog(p = "全院三管监测--监测日报列表")
	public void a(HttpServletRequest request, HttpServletResponse response, String deptId, String startDate,
			String endDate, String deptType, String unitId) {
		List icuCountList = this.sd.c(deptId, startDate, endDate, deptType, unitId);
		MyPage page = new MyPage(1, icuCountList.size(), icuCountList.size(), icuCountList);
		int newCountSum = 0;
		int inCountSum = 0;
		int outCountSum = 0;
		int frCountSum = 0;
		int hxjCountSum = 0;
		int mndCountSum = 0;
		int zxjmCountSum = 0;
		int ywryksCountSum = 0;
		int ywryfxCountSum = 0;
		int ywryfrCountSum = 0;
		int bhksCountSum = 0;
		int bhfxCountSum = 0;
		int tsyCountSum = 0;

		IcuCount icu;
		for (Iterator icuFooterList = icuCountList.iterator(); icuFooterList
				.hasNext(); tsyCountSum += icu.getTsyCount().intValue()) {
			icu = (IcuCount) icuFooterList.next();
			newCountSum += icu.getNewCount().intValue();
			inCountSum += icu.getInCount().intValue();
			outCountSum += icu.getOutCount().intValue();
			frCountSum += icu.getFrCount().intValue();
			hxjCountSum += icu.getHxjCount().intValue();
			mndCountSum += icu.getMndCount().intValue();
			zxjmCountSum += icu.getZxjmCount().intValue();
			ywryksCountSum += icu.getYwryksCount().intValue();
			ywryfxCountSum += icu.getYwryfxCount().intValue();
			ywryfrCountSum += icu.getYwryfrCount().intValue();
			bhksCountSum += icu.getBhksCount().intValue();
			bhfxCountSum += icu.getBhfxCount().intValue();
		}

		icu = new IcuCount();
		icu.setDeptName("合计");
		icu.setNewCount(Integer.valueOf(newCountSum));
		icu.setInCount(Integer.valueOf(inCountSum));
		icu.setOutCount(Integer.valueOf(outCountSum));
		icu.setFrCount(Integer.valueOf(frCountSum));
		icu.setHxjCount(Integer.valueOf(hxjCountSum));
		icu.setMndCount(Integer.valueOf(mndCountSum));
		icu.setZxjmCount(Integer.valueOf(zxjmCountSum));
		icu.setYwryksCount(Integer.valueOf(ywryksCountSum));
		icu.setYwryfxCount(Integer.valueOf(ywryfxCountSum));
		icu.setYwryfrCount(Integer.valueOf(ywryfrCountSum));
		icu.setBhksCount(Integer.valueOf(bhksCountSum));
		icu.setBhfxCount(Integer.valueOf(bhfxCountSum));
		icu.setTsyCount(Integer.valueOf(tsyCountSum));
		ArrayList icuFooterList1 = new ArrayList();
		icuFooterList1.add(icu);
		page.setFooter(icuFooterList1);
		this.a(response, page);
	}

	@RequestMapping({"/gm003Ybsj/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "全院三管监测--监测月报列表")
	public void e(HttpServletRequest request, HttpServletResponse response, String deptId, String bedicu,
			String monthDate, String unitId) {
		String startDate = f.formatDate(f.a(f.k(monthDate, "yyyy-MM"), true));
		String endDate = f.formatDate(f.b(f.k(monthDate, "yyyy-MM"), true));
		new ArrayList();
		new ArrayList();
		List icuCountList;
		List icuCountListSum;
		if ("1".equals(bedicu)) {
			Dep page = this.e.get(deptId);
			if (page.getIfbedicu() != null && page.getIfbedicu().longValue() == 1L) {
				icuCountList = this.bP.i(deptId, startDate, endDate, unitId);
				icuCountListSum = this.sd.e(deptId, startDate, endDate, unitId);
			} else {
				icuCountList = this.sd.c(deptId, startDate, endDate, unitId);
				icuCountListSum = this.sd.d(deptId, startDate, endDate, unitId);
			}
		} else {
			icuCountList = this.sd.c(deptId, startDate, endDate, unitId);
			icuCountListSum = this.sd.d(deptId, startDate, endDate, unitId);
		}

		MyPage page1 = new MyPage(1, icuCountList.size(), icuCountList.size(), icuCountList);
		if (icuCountListSum.size() > 0) {
			IcuCount icu = (IcuCount) icuCountListSum.get(0);
			icu.setStrDate("合计");
			ArrayList icuFooterList = new ArrayList();
			icuFooterList.add(icu);
			page1.setFooter(icuFooterList);
		}

		this.a(response, page1);
	}

	@RequestMapping({"/gm003Ybsj/f_json/pageQuerybyMonth"})
	@ResponseBody
	@SqlLog(p = "全院三管监测--监测年报列表")
	public void f(HttpServletRequest request, HttpServletResponse response, String deptId, String startMonthDate,
			String endMonthDate, String unitId) {
		String startDate = f.formatDate(f.a(f.k(startMonthDate, "yyyy-MM"), true));
		String endDate = f.formatDate(f.b(f.k(endMonthDate, "yyyy-MM"), true));
		IcuCount icu = this.sd.f(deptId, startDate, endDate, unitId);
		icu.setStrDate("合计");
		ArrayList icuFooterList = new ArrayList();
		icuFooterList.add(icu);
		List icuCountList = this.sd.d(deptId, startDate, endDate, unitId);
		MyPage page = new MyPage(1, icuCountList.size(), icuCountList.size(), icuCountList);
		page.setFooter(icuFooterList);
		this.a(response, page);
	}

	@RequestMapping({"/gm003Ybsj/f_json/exportExcelIcu"})
	@ResponseBody
	@SqlLog(p = "全院三管监测--监测月报导出")
	public void l(HttpServletRequest request, HttpServletResponse response, String deptId, String monthDate,
			String unitId) {
		String startDate = f.formatDate(f.a(f.k(monthDate, "yyyy-MM"), true));
		String endDate = f.formatDate(f.b(f.k(monthDate, "yyyy-MM"), true));
		String name = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());

		try {
			response.setHeader("Content-disposition", "attachment; filename=" + ab.a("监测年报_", request) + name + ".xls");
			response.setContentType("application/msexcel;charset=UTF-8");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			HSSFWorkbook e = this.sd.g(deptId, startDate, endDate, unitId);
			ServletOutputStream os = response.getOutputStream();
			e.write(os);
			os.flush();
			os.close();
		} catch (Exception arg10) {
			c.error("获取信息异常!", arg10);
		}

	}

	@RequestMapping({"/gm003Ybsj/f_json/exportExcelIcuByMonth"})
	@ResponseBody
	@SqlLog(p = "全院三管监测--监测年报导出")
	public void g(HttpServletRequest request, HttpServletResponse response, String deptId, String startMonthDate,
			String endMonthDate, String unitId) {
		String startDate = f.formatDate(f.a(f.k(startMonthDate, "yyyy-MM"), true));
		String endDate = f.formatDate(f.b(f.k(endMonthDate, "yyyy-MM"), true));
		String name = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());

		try {
			response.setHeader("Content-disposition", "attachment; filename=" + ab.a("监测年报_", request) + name + ".xls");
			response.setContentType("application/msexcel;charset=UTF-8");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			HSSFWorkbook e = this.sd.h(deptId, startDate, endDate, unitId);
			ServletOutputStream os = response.getOutputStream();
			e.write(os);
			os.flush();
			os.close();
		} catch (Exception arg11) {
			c.error("获取信息异常!", arg11);
		}

	}

	@RequestMapping({"/gm003Ybsj/f_json/exportExcelDay"})
	@ResponseBody
	@SqlLog(p = "全院三管监测--监测日报导出")
	public void b(HttpServletRequest request, HttpServletResponse response, String deptId, String startDate,
			String endDate, String deptType, String unitId) {
		String name = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());

		try {
			response.setHeader("Content-disposition", "attachment; filename=" + ab.a("监测日报_", request) + name + ".xls");
			response.setContentType("application/msexcel;charset=UTF-8");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			HSSFWorkbook e = this.sd.d(deptId, startDate, endDate, deptType, unitId);
			ServletOutputStream os = response.getOutputStream();
			e.write(os);
			os.flush();
			os.close();
		} catch (Exception arg10) {
			c.error("获取信息异常!", arg10);
		}

	}
}