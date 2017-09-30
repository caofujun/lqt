package com.nis.icu.controller;

import com.nis.access.entity.AcAccount;
import com.nis.access.entity.AcMenu;
import com.nis.access.entity.AcRole;
import com.nis.analysis.service.SysJudgeLogService;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.entity.TreeEntity;
import com.nis.comm.enums.Param;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.dict.service.SysDictService;
import com.nis.icu.controller.Gm004JcmxController.1;
import com.nis.icu.entity.By001Bfgz;
import com.nis.icu.entity.CgPg;
import com.nis.icu.entity.Gm004Jcmx;
import com.nis.icu.entity.NicuCount;
import com.nis.icu.service.By001BfgzService;
import com.nis.icu.service.Gm004JcmxService;
import com.nis.organization.entity.Dep;
import com.nis.organization.service.DepService;
import com.nis.param.service.SysParamService;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.service.St003CryxxbService;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
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
public class Gm004JcmxController extends BaseController {
	private static final Logger c = Logger.getLogger(Gm004JcmxController.class);
	@Autowired
	private St003CryxxbService bg;
	@Autowired
	private Gm004JcmxService bP;
	@Autowired
	private DepService e;
	@Autowired
	private By001BfgzService sf;
	@Autowired
	private SysParamService j;
	@Autowired
	private SysDictService p;
	@Autowired
	private SysJudgeLogService J;

	@RequestMapping({"/gm004Jcmx/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap, String bizType, String unitId, String deptid,
			String typeid, String startDate, String endDate, String neonatebw, String bedicu) {
		Dep dep = this.e.get(deptid);
		By001Bfgz by001Bfgz = this.sf.get(typeid);
		if (dep != null) {
			modelMap.put("deptName", dep.getDeptName());
		}

		modelMap.put("typeName", by001Bfgz.getOutbreakTypeName());
		modelMap.put("bizType", bizType);
		modelMap.put("deptid", deptid);
		modelMap.put("unitId", unitId);
		modelMap.put("typeid", typeid);
		modelMap.put("startDate", startDate);
		modelMap.put("bedicu", bedicu);
		modelMap.put("endDate", endDate);
		modelMap.put("neonatebw", neonatebw);
		return "icu/gm004JcmxList";
	}

	@RequestMapping({"/gm004Jcmx/f_json/deleteHxj"})
	@ResponseBody
	@SqlLog(p = "全院三管监测--删除患者插管")
	public void a(HttpServletRequest request, HttpServletResponse response, Gm004Jcmx gm004Jcmx) {
		Result result = null;

		try {
			result = new Result();
			this.bP.deleteHxj(gm004Jcmx);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/gm004Jcmx/f_view/gm004JcmxIndex"})
	@SqlLog(p = "插管评估")
	public String a(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		AcAccount acc = (AcAccount) this.b(request);
		AcRole role = acc.getRoleCur();
		String deptId = "";
		if (role != null && "clinical".equals(role.getRoleType())) {
			deptId = (String) this.b(request, "zg_dept");
			modelMap.put("deptId", deptId);
		}

		String time = this.j.findByParamCode(Param.NIS_CGPG_TIME);
		TreeEntity treeEntity = new TreeEntity();
		List listMap = this.bP.getDeptPatient(time, deptId);
		ArrayList listDeptId = new ArrayList();
		Iterator queryDate = listMap.iterator();

		while (queryDate.hasNext()) {
			Map sdf = (Map) queryDate.next();
			treeEntity.setId(sdf.get("DEPT_CODE").toString());
			treeEntity.setText(sdf.get("DEPT_NAME").toString());
			String countChild = sdf.get("COUNT_CHILD").toString();
			treeEntity.setCldCount(Integer.valueOf(Integer.parseInt(countChild)));
			listDeptId.add(treeEntity);
		}

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String queryDate1 = sdf1.format(new Date());
		modelMap.put("queryDate", queryDate1);
		modelMap.put("listMap", listDeptId);
		return "icu/gm004JcmxIndex";
	}

	@RequestMapping({"/gm004Jcmx/f_json/gm004JcmxCountPatient"})
	@ResponseBody
	@SqlLog(p = "插管评估--科室列表合计")
	public void m(HttpServletRequest request, HttpServletResponse response, String isInHosp, String queryDate,
			String deptId) {
		String time = this.j.findByParamCode(Param.NIS_CGPG_TIME);
		String countPatient;
		Result result;
		if ("1".equals(isInHosp)) {
			countPatient = this.bP.getPatientNum(time, deptId);
			result = new Result();
			result.setData(countPatient);
			result.setResult("success");
			this.a(response, result);
		} else {
			countPatient = this.bP.getPatientNum2(time, queryDate, deptId);
			result = new Result();
			result.setData(countPatient);
			result.setResult("success");
			this.a(response, result);
		}

	}

	@RequestMapping({"/gm004Jcmx/f_json/gm004JcmxIndexMenu"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String isInHosp,
			String queryDate, String deptId) {
		String time = this.j.findByParamCode(Param.NIS_CGPG_TIME);
		List listMap;
		ArrayList listDeptId;
		Map map;
		Iterator arg10;
		TreeEntity treeEntity;
		String countChild;
		if ("1".equals(isInHosp)) {
			listMap = this.bP.getDeptPatient(time, deptId);
			listDeptId = new ArrayList();
			arg10 = listMap.iterator();

			while (arg10.hasNext()) {
				map = (Map) arg10.next();
				treeEntity = new TreeEntity();
				treeEntity.setId(map.get("DEPT_CODE").toString());
				countChild = map.get("COUNT_CHILD").toString();
				treeEntity.setText(map.get("DEPT_NAME").toString() + "【" + countChild + "】");
				treeEntity.setCldCount(Integer.valueOf(Integer.parseInt(countChild)));
				treeEntity.setState("closed");
				listDeptId.add(treeEntity);
			}

			this.a(response, listDeptId);
		} else {
			listMap = this.bP.getDeptPatient2(time, queryDate, deptId);
			listDeptId = new ArrayList();
			arg10 = listMap.iterator();

			while (arg10.hasNext()) {
				map = (Map) arg10.next();
				treeEntity = new TreeEntity();
				treeEntity.setId(map.get("DEPT_CODE").toString());
				countChild = map.get("COUNT_CHILD").toString();
				treeEntity.setText(map.get("DEPT_NAME").toString() + "【" + countChild + "】");
				treeEntity.setCldCount(Integer.valueOf(Integer.parseInt(countChild)));
				treeEntity.setState("closed");
				listDeptId.add(treeEntity);
			}

			this.a(response, listDeptId);
		}

	}

	@RequestMapping({"/gm004Jcmx/f_json/gm004JcmxIndexMenuNode"})
	@ResponseBody
	public void n(HttpServletRequest request, HttpServletResponse response, String deptCode, String isInHosp,
			String queryDate) {
		String time = this.j.findByParamCode(Param.NIS_CGPG_TIME);
		List listTree;
		if ("1".equals(isInHosp)) {
			listTree = this.bP.getDeptPatientMenuNode(deptCode, time);
			this.a(response, listTree);
		} else {
			listTree = this.bP.getDeptPatientMenuNode2(deptCode, queryDate, time);
			this.a(response, listTree);
		}

	}

	@RequestMapping({"/gm004Jcmx/f_json/gm004JcmxGetpatientList"})
	@ResponseBody
	@SqlLog(p = "插管评估--患者列表")
	public void I(HttpServletRequest request, HttpServletResponse response, String id) {
		String time = this.j.findByParamCode(Param.NIS_CGPG_TIME);
		List list = this.bP.getpatientList(id, time);
		this.a(response, list);
	}

	@RequestMapping({"/gm004Jcmx/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "全院三管监测--患者明细列表")
	public void a(HttpServletRequest request, HttpServletResponse response, String unitId, String deptid, String typeid,
			String startDate, String endDate, Integer page, Integer rows, String neonatebw, String bedicu) {
		Timestamp startdate = f.Y(startDate);
		Timestamp enddate = f.k(endDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
		if (rows == null) {
			rows = Integer.valueOf(10);
		}

		MyPage myPage = this.bg.a(unitId, typeid, deptid, neonatebw, startdate, enddate, page, rows);
		if ("1".equals(bedicu)) {
			Dep dep = this.e.get(deptid);
			if (dep.getIfbedicu() != null && dep.getIfbedicu().longValue() == 1L) {
				myPage = this.bg.b(unitId, typeid, deptid, neonatebw, startdate, enddate, page, rows);
			}
		}

		this.a(response, myPage);
	}

	@RequestMapping({"/gm004Jcmx/f_view/findByZyidUrl"})
	public String v(HttpServletRequest request, ModelMap modelMap, String zyid) {
		modelMap.put("zyid", zyid);
		modelMap.put("cgpg", this.j.findByParamCode(Param.NIS_HXJ_CGPGSHOW));
		modelMap.put("time", this.j.findByParamCode(Param.NIS_CGPG_TIME));
		return "icu/gm004JcmxDetail";
	}

	@RequestMapping({"/gm004Jcmx/f_json/findByZyid"})
	@ResponseBody
	public void J(HttpServletRequest request, HttpServletResponse response, String zyid) {
		String time = this.j.findByParamCode(Param.NIS_CGPG_TIME);
		List list = this.bP.findByZyid(zyid, time);
		this.a(response, list);
	}

	@RequestMapping({"/gm004Jcmx/f_json/exportExcelPatient"})
	@ResponseBody
	@SqlLog(p = "全院三管监测--患者列表导出")
	public void a(HttpServletRequest request, HttpServletResponse response, String bizType, String unitId,
			String deptid, String neonatebw, String typeid, String startDate, String endDate) {
		Timestamp startdate = f.Y(startDate);
		Timestamp enddate = f.k(endDate + " 23:59", "yyyy-MM-dd HH:ss");
		String name = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());

		try {
			response.setHeader("Content-disposition", "attachment; filename=" + ab.a("患者列表_", request) + name + ".xls");
			response.setContentType("application/msexcel;charset=UTF-8");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			HSSFWorkbook e = this.bP.a(unitId, typeid, deptid, neonatebw, startdate, enddate);
			ServletOutputStream os = response.getOutputStream();
			e.write(os);
			os.flush();
			os.close();
		} catch (Exception arg14) {
			c.error("获取信息异常!", arg14);
		}

	}

	@RequestMapping({"/gm004Jcmx/f_json/qrxczxgxgr"})
	@ResponseBody
	public void h(HttpServletRequest request, HttpServletResponse response, String dateType, String ificu, String date,
			String orderType) {
		String queryStartDate = null;
		String queryEndDate = null;
		Object myDate = new Date();
		if (ab.isNotEmpty(date)) {
			myDate = f.k(date, "yyyy/MM/dd");
		}

		if (dateType.equals("month")) {
			if ("back".equals(orderType)) {
				myDate = f.b((Date) myDate, -1);
			}

			if ("next".equals(orderType)) {
				myDate = f.b((Date) myDate, 1);
			}

			queryStartDate = f.formatDate(f.a((Date) myDate, true));
			queryEndDate = f.formatDate(f.b((Date) myDate, true));
		} else if (dateType.equals("week")) {
			if ("back".equals(orderType)) {
				myDate = f.a((Date) myDate, 0);
			}

			if ("next".equals(orderType)) {
				myDate = f.a((Date) myDate, 16);
			}

			queryStartDate = f.formatDate(f.a((Date) myDate, -8));
			queryEndDate = f.formatDate(f.a((Date) myDate, -1));
		}

		Result result = null;
		List mapList = this.bP.findqrxczxgxgr(queryStartDate, queryEndDate, ificu);
		result = new Result("success");
		result.setData(mapList);
		result.setMsg(f.c(f.k(queryStartDate, "yyyy-MM-dd"), "yyyy/MM/dd") + "~"
				+ f.c(f.k(queryEndDate, "yyyy-MM-dd"), "MM/dd"));
		result.setExpandValue(queryStartDate + "~" + queryEndDate);
		this.b(response, result);
	}

	@RequestMapping({"/gm004Jcmx/f_json/dcl"})
	@ResponseBody
	public void K(HttpServletRequest request, HttpServletResponse response, String dateType) {
		String queryStartDate = null;
		String queryEndDate = null;
		if (dateType.equals("month")) {
			queryStartDate = f.formatDate(f.a(new Date(), true));
			queryEndDate = f.formatDate(f.b(new Date(), true));
		} else if (dateType.equals("week")) {
			queryStartDate = f.formatDate(f.a(new Date(), -8));
			queryEndDate = f.formatDate(f.a(new Date(), -1));
		} else if (dateType.equals("day")) {
			queryStartDate = f.formatDate(f.a(new Date(), -1));
			queryEndDate = f.formatDate(f.a(new Date(), -1));
		}

		Result result = null;

		try {
			result = new Result();
			LoginUser e = this.d(request);
			List deptIdIn = null;
			if (ab.isNotEmpty(e.getScopeInfo())) {
				String[] data = e.getScopeInfo().split(",");
				deptIdIn = Arrays.asList(data);
			}

			Map data1 = this.bP.a(queryStartDate, queryEndDate, deptIdIn);
			result.setData(data1);
			result.setMsg(f.c(f.k(queryStartDate, "yyyy-MM-dd"), "yyyy/MM/dd") + "~"
					+ f.c(f.k(queryEndDate, "yyyy-MM-dd"), "MM/dd"));
			result.setExpandValue(queryStartDate + "~" + queryEndDate);
			result.setResult("success");
		} catch (Exception arg9) {
			c.error("获取信息异常!", arg9);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/gm004Jcmx/f_view/main_info_new"})
	public String w(HttpServletRequest request, ModelMap modelMap, String isHttps) {
		AcAccount acAccount = (AcAccount) this.b(request);
		if (acAccount.getRoleCur() != null) {
			String roleScope = acAccount.getRoleCur().getRoleScope();
			if (StringUtils.isBlank(roleScope) || "crb".equals(roleScope) || "yg".equals(roleScope)) {
				Date currDate = f.getCurDate();
				Date endDate = f.p(f.a(currDate, -1));
				Date startDate = f.q(endDate);
				Map floorData = this.bP.A(f.g(startDate), f.g(endDate));
				String tw = this.j.findByParamCode(Param.NIS_TW_FR);
				modelMap.put("floorData", floorData);
				List allowBKs = this.p.u("cdc_card_type", (String) null);
				modelMap.put("allowCards", allowBKs);
				Date toEndDate = f.p(currDate);
				Date toStartDate = f.q(f.a(toEndDate, -29));
				List menus = (List) this.b(request, "user_menus");
				Iterator time = menus.iterator();

				while (time.hasNext()) {
					AcMenu isShowGw = (AcMenu) time.next();
					if (isShowGw.getMenuNo().equals("C0202")) {
						modelMap.put("isByShow", Integer.valueOf(1));
					}
				}

				byte isShowGw1 = 0;
				Iterator toData = menus.iterator();

				while (toData.hasNext()) {
					AcMenu time1 = (AcMenu) toData.next();
					if (time1.getMenuNo().indexOf("K") > -1) {
						isShowGw1 = 1;
					}
				}

				modelMap.put("isShowGw", Integer.valueOf(isShowGw1));
				String time2 = this.j.findByParamCode(Param.NIS_CGPG_TIME);
				Map toData1 = this.bP.a(toStartDate, toEndDate, time2);
				String cgpg = this.j.findByParamCode(Param.NIS_HXJ_CGPGSHOW);
				String kfjc = this.j.findByParamCode(Param.NIS_JCRB_IS_KFJC);
				modelMap.put("cgpg", cgpg);
				modelMap.put("kfjc", kfjc);
				modelMap.put("toData", toData1);
				modelMap.put("tw", tw);
				modelMap.put("date", f.c(startDate, "yyyy/MM/dd"));
				modelMap.put("startDate", f.formatDate(startDate));
				modelMap.put("endDate", f.formatDate(endDate));
				modelMap.put("toStartDate", f.formatDate(toStartDate));
				modelMap.put("toEndDate", f.formatDate(toEndDate));
				modelMap.put("startDateMonth", f.formatDate(f.a(f.b(f.getCurDate(), -12), true)));
				modelMap.put("endDateMonth", f.formatDate(f.h(true)));
				modelMap.put("startDateYear", f.formatDate(f.getYearFirst()));
				modelMap.put("endDateYear", f.formatDate(f.getYearLast()));
				modelMap.put("startDateWeek", f.formatDate(f.a(endDate, -6)));
				modelMap.put("year", Integer.valueOf(f.getCurYear()));
				if ("true".equals(isHttps)) {
					request.getSession().setAttribute("isHttps", "true");
				} else {
					request.getSession().setAttribute("isHttps", "false");
				}

				return "/main_info_new";
			}
		}

		return "/welcome";
	}

	@RequestMapping({"/gm004Jcmx/f_view/nicuCount"})
	public String s(HttpServletRequest request, ModelMap modelMap, String deptId, String monthDate) {
		modelMap.put("deptId", deptId);
		AcAccount acAccount = (AcAccount) this.b(request);
		Dep dep = new Dep();
		dep.setIfchildoffice(Long.valueOf(1L));
		dep.setHospId(acAccount.getUnitId());
		dep.setPage(Integer.valueOf(1));
		dep.setSize(Integer.valueOf(200));
		MyPage nicuDepPage = this.e.b(dep);
		if (nicuDepPage.getRows().size() > 0) {
			modelMap.put("deptId", ((Dep) nicuDepPage.getRows().get(0)).getDeptId());
		}

		modelMap.put("nicuList", nicuDepPage.getRows());
		if (ab.isNotEmpty(monthDate)) {
			modelMap.put("monthDate", monthDate);
		} else {
			modelMap.put("monthDate", f.c(new Date(), "yyyy-MM"));
		}

		modelMap.put("unitId", this.c(request));
		return "icu/nicuList";
	}

	@RequestMapping({"/gm004Jcmx/f_json/findNicuCount"})
	@ResponseBody
	public void E(HttpServletRequest request, HttpServletResponse response, String deptId, String monthDate) {
		String startDate = f.formatDate(f.a(f.k(monthDate, "yyyy-MM"), true));
		String endDate = f.formatDate(f.b(f.k(monthDate, "yyyy-MM"), true));
		List nicuList = this.bP.findNicuCount(deptId, startDate, endDate);
		MyPage page = new MyPage(1, nicuList.size(), nicuList.size(), nicuList);
		int newCount1Sum = 0;
		int inCount1Sum = 0;
		int hxjCount1Sum = 0;
		int zxjmCount1Sum = 0;
		int newCount2Sum = 0;
		int inCount2Sum = 0;
		int hxjCount2Sum = 0;
		int zxjmCount2Sum = 0;
		int newCount3Sum = 0;
		int inCount3Sum = 0;
		int hxjCount3Sum = 0;
		int zxjmCount3Sum = 0;
		int newCount4Sum = 0;
		int inCount4Sum = 0;
		int hxjCount4Sum = 0;
		int zxjmCount4Sum = 0;

		NicuCount nicu;
		for (Iterator nicuFooterList = nicuList.iterator(); nicuFooterList
				.hasNext(); zxjmCount4Sum += nicu.getZxjmCount4().intValue()) {
			nicu = (NicuCount) nicuFooterList.next();
			newCount1Sum += nicu.getNewCount1().intValue();
			inCount1Sum += nicu.getInCount1().intValue();
			hxjCount1Sum += nicu.getHxjCount1().intValue();
			zxjmCount1Sum += nicu.getZxjmCount1().intValue();
			newCount2Sum += nicu.getNewCount2().intValue();
			inCount2Sum += nicu.getInCount2().intValue();
			hxjCount2Sum += nicu.getHxjCount2().intValue();
			zxjmCount2Sum += nicu.getZxjmCount2().intValue();
			newCount3Sum += nicu.getNewCount3().intValue();
			inCount3Sum += nicu.getInCount3().intValue();
			hxjCount3Sum += nicu.getHxjCount3().intValue();
			zxjmCount3Sum += nicu.getZxjmCount3().intValue();
			newCount4Sum += nicu.getNewCount4().intValue();
			inCount4Sum += nicu.getInCount4().intValue();
			hxjCount4Sum += nicu.getHxjCount4().intValue();
		}

		nicu = new NicuCount();
		nicu.setStrDate("合计");
		nicu.setNewCount1(Integer.valueOf(newCount1Sum));
		nicu.setInCount1(Integer.valueOf(inCount1Sum));
		nicu.setZxjmCount1(Integer.valueOf(zxjmCount1Sum));
		nicu.setHxjCount1(Integer.valueOf(hxjCount1Sum));
		nicu.setNewCount2(Integer.valueOf(newCount2Sum));
		nicu.setInCount2(Integer.valueOf(inCount2Sum));
		nicu.setZxjmCount2(Integer.valueOf(zxjmCount2Sum));
		nicu.setHxjCount2(Integer.valueOf(hxjCount2Sum));
		nicu.setNewCount3(Integer.valueOf(newCount3Sum));
		nicu.setInCount3(Integer.valueOf(inCount3Sum));
		nicu.setZxjmCount3(Integer.valueOf(zxjmCount3Sum));
		nicu.setHxjCount3(Integer.valueOf(hxjCount3Sum));
		nicu.setNewCount4(Integer.valueOf(newCount4Sum));
		nicu.setInCount4(Integer.valueOf(inCount4Sum));
		nicu.setZxjmCount4(Integer.valueOf(zxjmCount4Sum));
		nicu.setHxjCount4(Integer.valueOf(hxjCount4Sum));
		ArrayList nicuFooterList1 = new ArrayList();
		nicuFooterList1.add(nicu);
		page.setFooter(nicuFooterList1);
		this.a(response, page);
	}

	@RequestMapping({"/gm004Jcmx/f_json/exportExcelNicu"})
	@ResponseBody
	public void D(HttpServletRequest request, HttpServletResponse response, String deptId, String monthDate) {
		String startDate = f.formatDate(f.a(f.k(monthDate, "yyyy-MM"), true));
		String endDate = f.formatDate(f.b(f.k(monthDate, "yyyy-MM"), true));
		String name = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());

		try {
			response.setHeader("Content-disposition",
					"attachment; filename=" + ab.a("ICU日志_", request) + name + ".xls");
			response.setContentType("application/msexcel;charset=UTF-8");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			HSSFWorkbook e = this.bP.n(deptId, startDate, endDate);
			ServletOutputStream os = response.getOutputStream();
			e.write(os);
			os.flush();
			os.close();
		} catch (Exception arg9) {
			c.error("获取信息异常!", arg9);
		}

	}

	@RequestMapping({"/gm004Jcmx/f_view/cgPgList"})
	@SqlLog(p = "插管评估--患者插管列表")
	public String g(HttpServletRequest request, ModelMap modelMap, String zyid, String dateMonth, String showName) {
		Object nowDate = f.getCurDate();
		if (ab.isNotEmpty(dateMonth)) {
			nowDate = f.k(dateMonth, "yyyy/MM");
		}

		St003Cryxxb cryxxb = this.bg.get(zyid);
		String time = this.j.findByParamCode(Param.NIS_CGPG_TIME);
		List cgPgList = this.bP.findCgByZyid(zyid, f.q(f.a((Date) nowDate, false)), f.p(f.b((Date) nowDate, false)),
				time);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime((Date) nowDate);
		calendar.set(5, 1);
		int first_day = calendar.get(7);
		ArrayList dayList = new ArrayList();

		int i;
		for (i = 1; i < first_day; ++i) {
			CgPg day = new CgPg();
			day.setDay(Integer.valueOf(0));
			dayList.add(day);
		}

		for (i = 0; i < cgPgList.size(); ++i) {
			if (f.formatDate(((CgPg) cgPgList.get(i)).getDtime()).equals(f.formatDate(f.getCurDate()))) {
				((CgPg) cgPgList.get(i)).setToDay("1");
			}

			((CgPg) cgPgList.get(i)).setDay(Integer.valueOf(i + 1));
			long arg16 = ((new Date()).getTime() - ((CgPg) cgPgList.get(i)).getDtime().getTime()) / 3600000L;
			if (arg16 < Long.parseLong(time)) {
				int arg15 = ((CgPg) cgPgList.get(i)).getMnd().intValue();
			}
		}

		dayList.addAll(cgPgList);
		modelMap.put("showName", showName);
		modelMap.put("dateMonth", f.formatDate((Date) nowDate, "yyyy年MM月"));
		modelMap.put("nowDate", f.formatDate((Date) nowDate, "yyyy/MM/dd"));
		modelMap.put("today", f.formatDate(f.getCurDate(), "yyyy/MM"));
		modelMap.put("dayList", dayList);
		modelMap.put("cryxxb", cryxxb);
		return "icu/cgPgList";
	}

	@RequestMapping({"/gm004Jcmx/f_view/judgeJcmxList"})
	public String w(HttpServletRequest request, ModelMap modelMap) {
		return "icu/judgeJcmxList";
	}

	@RequestMapping({"/gm004Jcmx/f_json/judgeJcmx"})
   @ResponseBody
   public void F(HttpServletRequest request, HttpServletResponse response, String startDate, String endDate) {
      Result result = new Result();
      result.setResult("success");
      (new Thread(new 1(this, startDate, endDate))).start();
      this.a(response, result);
   }
}