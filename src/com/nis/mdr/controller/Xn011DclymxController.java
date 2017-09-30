package com.nis.mdr.controller;

import com.nis.access.entity.AcAccount;
import com.nis.access.entity.AcRole;
import com.nis.access.service.AcAccountService;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.al;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.l;
import com.nis.comm.utils.r;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import com.nis.mdr.entity.ViewMdr;
import com.nis.mdr.entity.Xn011Dclymx;
import com.nis.mdr.service.Xn011DclymxService;
import com.nis.monitor.entity.Bk001Sbk;
import com.nis.monitor.service.Bk001SbkService;
import com.nis.msg.service.NyMessageDetailService;
import com.nis.param.service.SysParamService;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.service.St003CryxxbService;
import com.nis.patient.service.St011SyjgbService;
import com.nis.zg.entity.Zg003Yyzg;
import com.nis.zg.service.Zg003YyzgService;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
public class Xn011DclymxController extends BaseController {
	private static final Logger c = Logger.getLogger(Xn011DclymxController.class);
	@Autowired
	private Xn011DclymxService cn;
	@Autowired
	private St011SyjgbService bH;
	@Autowired
	private SysDictService p;
	@Autowired
	private NyMessageDetailService cV;
	@Autowired
	private Bk001SbkService bW;
	@Autowired
	private AcAccountService d;
	@Autowired
	private SysParamService j;
	@Autowired
	private Zg003YyzgService q;
	@Autowired
	private St003CryxxbService df;

	@RequestMapping({"/xn011Dclymx/f_view/mdr/type/list"})
	public String b(HttpServletRequest request, ModelMap modelMap, String zyid, String testOrderNo, String orderno,
			String surveyDeptId, String normItemId, String normOrderno) {
		modelMap.put("zyid", zyid);
		modelMap.put("testOrderNo", testOrderNo);
		modelMap.put("orderno", orderno);
		modelMap.put("surveyDeptId", surveyDeptId);
		modelMap.put("normItemId", normItemId);
		modelMap.put("normOrderno", normOrderno);
		List mdrTypeList = this.p.u("res_prop_code", (String) null);
		modelMap.put("mdrTypeList", mdrTypeList);
		return "mdr/mdrTypeList";
	}

	@RequestMapping({"/xn011Dclymx/f_view/mdr/type/update"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String zyid, String testOrderNo,
			Long orderno, String surveyDeptId, Integer normItemId, Integer normOrderno, Integer resProp) {
		Result result;
		try {
			result = new Result();
			Xn011Dclymx e = new Xn011Dclymx();
			e.setZyid(zyid);
			e.setTestOrderNo(testOrderNo);
			e.setOrderno(orderno);
			e.setSurveyDeptId(surveyDeptId);
			e.setNormItemId(normItemId);
			e.setNormOrderno(normOrderno);
			e.setResProp(resProp);
			this.cn.updateMdrType(e);
			result.setResult("success");
		} catch (Exception arg11) {
			c.error("获取信息异常!", arg11);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/xn011Dclymx/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap, String resProp, String ownership, String startDate,
			String endDate, String testOrderNo, String code, String name) {
		try {
			if (name != null && !"".equals(name)) {
				name = URLDecoder.decode(name, "UTF-8");
				modelMap.put("name", name);
			}
		} catch (UnsupportedEncodingException arg16) {
			arg16.printStackTrace();
		}

		if (ab.isEmpty(startDate)) {
			if (ownership.equals("clinical")) {
				startDate = f.c(f.a(new Date(), -7), "yyyy-MM-dd");
			} else {
				startDate = f.c(f.b(new Date(), -1), "yyyy-MM-dd");
			}
		}

		if (ab.isEmpty(endDate)) {
			endDate = f.c(new Date(), "yyyy-MM-dd");
		}

		AcAccount acc = (AcAccount) this.b(request);
		AcRole role = acc.getRoleCur();
		if (role != null && "clinical".equals(role.getRoleType())) {
			String list = (String) this.b(request, "zg_dept");
			modelMap.put("deptId", list);
		}

		modelMap.put("acType", role.getRoleType());
		if (ab.isEmpty(resProp)) {
			resProp = "1,2,3";
		}

		List list1 = this.p.u("gr_type", (String) null);
		LinkedList dateSectionList = new LinkedList();
		Iterator isSh = list1.iterator();

		while (isSh.hasNext()) {
			SysDict dataSections = (SysDict) isSh.next();
			HashMap map = new HashMap();
			map.put("value", dataSections.getDictCode());
			map.put("text", dataSections.getDictName() == null ? "" : dataSections.getDictName());
			dateSectionList.add(map);
		}

		String dataSections1 = l.toString(dateSectionList);
		String isSh1 = this.j.findByParamCode(Param.NIS_JCJ_JCJSH);
		modelMap.put("isSh", isSh1);
		modelMap.put("dataSections", dataSections1);
		modelMap.put("unitId", this.c(request));
		modelMap.put("resProp", resProp);
		modelMap.put("startDate", startDate);
		modelMap.put("endDate", endDate);
		modelMap.put("testOrderNo", testOrderNo);
		modelMap.put("code", code);
		if (role != null && "clinical".equals(role.getRoleType())) {
			return "mdr/mdrClinicalList";
		} else {
			return "mdr/mdrList";
		}
	}

	@RequestMapping({"/xn011Dclymx/f_view/toWarningPatientMDRo"})
	public String a(HttpServletRequest request, ModelMap modelMap, String resProp, String ownership, String zyid,
			String bedNo, String patientId, Integer visitId, String zhong) {
		modelMap.put("unitId", this.c(request));
		modelMap.put("resProp", resProp);
		if (ab.isNotEmpty(zyid)) {
			modelMap.put("zyid", zyid);
		} else {
			List acc = this.df.findByPatientIdAndVisitId(patientId, visitId);
			if (acc.size() > 0) {
				modelMap.put("zyid", ((St003Cryxxb) acc.get(0)).getZyid());
			}
		}

		modelMap.put("bedNo", bedNo);
		AcAccount acc1 = (AcAccount) this.b(request);
		AcRole role = acc1.getRoleCur();
		String ownership1 = role.getRoleType();
		modelMap.put("ownership", ownership1);
		modelMap.put("zhong", zhong);
		return "mdr/MDRoList";
	}

	@RequestMapping({"/xn011Dclymx/f_json/pageQueryMDRo"})
	@ResponseBody
	@SqlLog(p = "患者检出菌列表")
	public void a(HttpServletRequest request, HttpServletResponse response, ViewMdr viewMdr) {
		MyPage page = this.cn.e(viewMdr);

		for (int i = 0; i < page.getRows().size(); ++i) {
			String testOrderNo = ((ViewMdr) page.getRows().get(i)).getTestOrderNo();
			String pathoCode = ((ViewMdr) page.getRows().get(i)).getPathogenSn();
			List bk001Sbk = this.bW.D(testOrderNo, pathoCode);
			if (bk001Sbk.size() > 0) {
				String reportDrName = ((Bk001Sbk) bk001Sbk.get(0)).getReportDrName();
				((ViewMdr) page.getRows().get(i)).setReportDrName(reportDrName);
			}
		}

		this.a(response, page);
	}

	@RequestMapping({"/xn011Dclymx/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "检出菌搜索--检出菌列表")
	public void b(HttpServletRequest request, HttpServletResponse response, ViewMdr viewMdr) {
		if (ab.isNotEmpty(viewMdr.getResProp())) {
			viewMdr.setResPropList(viewMdr.getResProp().split(","));
		}

		if (ab.isNotEmpty(viewMdr.getInfectTypeId())) {
			viewMdr.setInfectTypeIdList(viewMdr.getInfectTypeId().split(","));
		}

		if (ab.isNotEmpty(viewMdr.getEsbl())) {
			viewMdr.setEsblList(viewMdr.getEsbl().split(","));
		}

		if (ab.isNotEmpty(viewMdr.getGx())) {
			LoginUser page = this.d(request);
			if (ab.isNotEmpty(page.getScopeInfo())) {
				String[] deptIds = page.getScopeInfo().split(",");
				viewMdr.setDeptIdIn(Arrays.asList(deptIds));
			} else {
				viewMdr.setDeptIdIn(Arrays.asList(new String[]{"0"}));
			}
		}

		if (viewMdr.getSearchString() != null && !"".equals(viewMdr.getSearchString())) {
			viewMdr.setSearchString(ab.aR(viewMdr.getSearchString()));
		}

		if (viewMdr.getLispathoName() != null && !"".equals(viewMdr.getLispathoName())) {
			viewMdr.setLispathoName(ab.aR(viewMdr.getLispathoName()));
		}

		MyPage page1 = this.cn.a(viewMdr);
		this.a(response, page1);
	}

	@RequestMapping({"/xn011Dclymx/f_view/mdrCheck"})
	public String a(HttpServletRequest request, ModelMap modelMap, ViewMdr viewMdr) {
		try {
			viewMdr.setResPropName(URLDecoder.decode(viewMdr.getResPropName(), "UTF-8"));
		} catch (UnsupportedEncodingException arg4) {
			arg4.printStackTrace();
		}

		modelMap.put("viewMdr", viewMdr);
		return "mdr/mdrCheck";
	}

	@RequestMapping({"/xn011Dclymx/f_json/updateMdr"})
	@ResponseBody
	@SqlLog(p = "检出菌--更新检出菌多耐类型")
	public void a(HttpServletRequest request, HttpServletResponse response, Xn011Dclymx xn011Dclymx) {
		Result result = null;

		try {
			result = new Result();
			AcAccount e = (AcAccount) this.b(request);
			String userName = e.getUsername();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String authDate = sdf.format(new Date());
			xn011Dclymx.setAuthDate(authDate);
			xn011Dclymx.setAuthUser(userName);
			xn011Dclymx.setAuthStatus("1");
			this.cn.updateMdrCheck(xn011Dclymx);
			result.setResult("success");
		} catch (Exception arg8) {
			c.error("获取信息异常!", arg8);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/xn011Dclymx/f_view/dayIndex"})
	public String f(HttpServletRequest request, ModelMap modelMap, String specType, String date, String infectTypeId) {
		if (ab.isEmpty(date)) {
			String acc = this.j.findByParamCode(Param.NIS_MONITOR_DAY_DATETYPE);
			if (acc.split(",").length > 0 && r.isNumber(acc.split(",")[0])) {
				date = f.c(f.a(new Date(), Integer.parseInt(acc.split(",")[0])), "yyyy-MM-dd");
			} else {
				date = f.c(f.a(new Date(), -1), "yyyy-MM-dd");
			}
		}

		AcAccount acc1 = (AcAccount) this.b(request);
		AcRole role = acc1.getRoleCur();
		if (role != null && "clinical".equals(role.getRoleType())) {
			String list = (String) this.b(request, "zg_dept");
			modelMap.put("deptId", list);
		}

		modelMap.put("acType", role.getRoleType());
		if (ab.isEmpty(specType)) {
			specType = "z";
		}

		List list1 = this.p.u("gr_type", (String) null);
		LinkedList dateSectionList = new LinkedList();
		Iterator isSh = list1.iterator();

		while (isSh.hasNext()) {
			SysDict dataSections = (SysDict) isSh.next();
			HashMap map = new HashMap();
			map.put("value", dataSections.getDictCode());
			map.put("text", dataSections.getDictName() == null ? "" : dataSections.getDictName());
			dateSectionList.add(map);
		}

		String dataSections1 = l.toString(dateSectionList);
		modelMap.put("dataSections", dataSections1);
		modelMap.put("unitId", this.c(request));
		modelMap.put("specType", specType);
		modelMap.put("infectTypeId", infectTypeId);
		modelMap.put("date", date);
		String isSh1 = this.j.findByParamCode(Param.NIS_JCJ_JCJSH);
		modelMap.put("isSh", isSh1);
		return "mdr/mdrListDay";
	}

	@RequestMapping({"/xn011Dclymx/f_json/pageQueryDay"})
	@ResponseBody
	@SqlLog(p = "每日检出菌--检出菌列表")
	public void c(HttpServletRequest request, HttpServletResponse response, ViewMdr viewMdr) {
		if ("gx".equals(viewMdr.getDeptType())) {
			LoginUser page = this.d(request);
			if (ab.isNotEmpty(page.getScopeInfo())) {
				String[] deptIds = page.getScopeInfo().split(",");
				viewMdr.setDeptIdIn(Arrays.asList(deptIds));
			} else {
				viewMdr.setDeptIdIn(Arrays.asList(new String[]{"0"}));
			}
		}

		List page1 = this.cn.c(viewMdr);
		this.a(response, page1);
	}

	@RequestMapping({"/xn011Dclymx/f_json/dayCount"})
	@ResponseBody
	public void o(HttpServletRequest request, HttpServletResponse response, String specType, String date,
			String deptType) {
		Object deptIdIn = new ArrayList();
		if ("gx".equals(deptType)) {
			LoginUser map = this.d(request);
			if (ab.isNotEmpty(map.getScopeInfo())) {
				String[] deptIds = map.getScopeInfo().split(",");
				deptIdIn = Arrays.asList(deptIds);
			} else {
				deptIdIn = Arrays.asList(new String[]{"0"});
			}
		}

		Map map1 = this.cn.b(date, specType, (List) deptIdIn);
		this.a(response, map1);
	}

	@RequestMapping({"/xn011Dclymx/f_json/exportExcelMdrDay"})
	@ResponseBody
	@SqlLog(p = "检出菌--每日检出菌导出")
	public void d(HttpServletRequest request, HttpServletResponse response, ViewMdr viewMdr) {
		String name = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
		if ("gx".equals(viewMdr.getDeptType())) {
			LoginUser e = this.d(request);
			if (ab.isNotEmpty(e.getScopeInfo())) {
				String[] os = e.getScopeInfo().split(",");
				viewMdr.setDeptIdIn(Arrays.asList(os));
			} else {
				viewMdr.setDeptIdIn(Arrays.asList(new String[]{"0"}));
			}
		}

		try {
			response.setHeader("Content-disposition",
					"attachment; filename=" + ab.a("每日检出菌_", request) + name + ".xls");
			response.setContentType("application/msexcel;charset=UTF-8");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			HSSFWorkbook e1 = this.cn.d(viewMdr);
			ServletOutputStream os1 = response.getOutputStream();
			e1.write(os1);
			os1.flush();
			os1.close();
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
		}

	}

	@RequestMapping({"/xn011Dclymx/f_json/exportExcelMdr"})
	@ResponseBody
	public void e(HttpServletRequest request, HttpServletResponse response, ViewMdr viewMdr)
			throws UnsupportedEncodingException {
		if (ab.isNotEmpty(viewMdr.getResProp()) && !"null".equals(viewMdr.getResProp())) {
			viewMdr.setResPropList(viewMdr.getResProp().split(","));
		}

		if (ab.isNotEmpty(viewMdr.getRsId()) && !"null".equals(viewMdr.getRsId())) {
			viewMdr.setRsId(viewMdr.getRsId().replace(" ", "+"));
		}

		if (ab.isNotEmpty(viewMdr.getSpecDescribes()) && !"null".equals(viewMdr.getSpecDescribes())) {
			viewMdr.setSpecDescribesList(viewMdr.getSpecDescribes().split(","));
		}

		if (ab.isNotEmpty(viewMdr.getInfectTypeId()) && !"null".equals(viewMdr.getInfectTypeId())) {
			viewMdr.setInfectTypeIdList(viewMdr.getInfectTypeId().split(","));
		}

		if (ab.isNotEmpty(viewMdr.getEsbl()) && !"null".equals(viewMdr.getEsbl())) {
			viewMdr.setEsblList(viewMdr.getEsbl().split(","));
		}

		if (ab.isNotEmpty(viewMdr.getSearchString())) {
			viewMdr.setSearchString(URLDecoder.decode(viewMdr.getSearchString(), "utf-8"));
		}

		if (ab.isNotEmpty(viewMdr.getGx())) {
			LoginUser name = this.d(request);
			if (ab.isNotEmpty(name.getScopeInfo())) {
				String[] e = name.getScopeInfo().split(",");
				viewMdr.setDeptIdIn(Arrays.asList(e));
			} else {
				viewMdr.setDeptIdIn(Arrays.asList(new String[]{"0"}));
			}
		}

		String name1 = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());

		try {
			response.setHeader("Content-disposition",
					"attachment; filename=" + ab.a("检出细菌_", request) + name1 + ".xls");
			response.setContentType("application/msexcel;charset=UTF-8");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			HSSFWorkbook e1 = this.cn.b(viewMdr);
			ServletOutputStream os = response.getOutputStream();
			e1.write(os);
			os.flush();
			os.close();
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
		}

	}

	@RequestMapping({"/xn011Dclymx/f_view/mdrDetail"})
	public String t(HttpServletRequest request, ModelMap modelMap, String testOrderNo, String pathogenSn) {
		modelMap.put("testOrderNo", testOrderNo);
		modelMap.put("pathogenSn", pathogenSn);
		return "mdr/mdrDetail";
	}

	@RequestMapping({"/xn011Dclymx/f_view/mdrTsny"})
	public String C(HttpServletRequest request, ModelMap modelMap, String specDescribes) {
		modelMap.put("specDescribe", specDescribes);
		return "mdr/mdrTsny";
	}

	@RequestMapping({"/xn011Dclymx/f_json/pageQueryMdrDetail"})
	@ResponseBody
	@SqlLog(p = "检出菌--检出结果详情")
	public void G(HttpServletRequest request, HttpServletResponse response, String testOrderNo, String pathogenSn) {
		List page = this.bH.findByOrderNoAndSn(testOrderNo, pathogenSn);
		this.a(response, page);
	}

	@RequestMapping({"/xn011Dclymx/f_json/update"})
	@ResponseBody
	@SqlLog(p = "更新细菌状态")
	public void b(HttpServletRequest request, HttpServletResponse response, Xn011Dclymx xn011Dclymx) {
		Result result = null;

		try {
			result = new Result();
			xn011Dclymx.setAffirmDt(new Date());
			xn011Dclymx.setChangeDt(new Date());
			xn011Dclymx.setAffirmUserid(this.d(request).getUsername());
			xn011Dclymx.setChangeUserid(this.d(request).getUsername());
			if (xn011Dclymx.getInfectTypeId() != null) {
				xn011Dclymx.setInfectTypeName(
						this.p.k("gr_type", xn011Dclymx.getInfectTypeId().toString(), (String) null));
			}

			this.cn.update(xn011Dclymx);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/xn011Dclymx/f_json/findwswbbfb"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, String dateType, String ificu, String date,
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
		} else if (dateType.equals("day")) {
			if ("back".equals(orderType)) {
				myDate = f.a((Date) myDate, 0);
			}

			if ("next".equals(orderType)) {
				myDate = f.a((Date) myDate, 2);
			}

			queryStartDate = f.formatDate(f.a((Date) myDate, -1));
			queryEndDate = f.formatDate(f.a((Date) myDate, -1));
		}

		Result result = null;
		List mapList = this.cn.findwswbbfb(queryStartDate, queryEndDate, ificu);
		result = new Result("success");
		result.setData(mapList);
		result.setMsg(f.c(f.k(queryStartDate, "yyyy-MM-dd"), "yyyy/MM/dd") + "~"
				+ f.c(f.k(queryEndDate, "yyyy-MM-dd"), "MM/dd"));
		result.setExpandValue(queryStartDate + "~" + queryEndDate);
		this.b(response, result);
	}

	@RequestMapping({"/xn011Dclymx/f_json/findwswjcqk"})
	@ResponseBody
	public void i(HttpServletRequest request, HttpServletResponse response, String dateType, String ificu, String date,
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
		} else if (dateType.equals("day")) {
			if ("back".equals(orderType)) {
				myDate = f.a((Date) myDate, 0);
			}

			if ("next".equals(orderType)) {
				myDate = f.a((Date) myDate, 2);
			}

			queryStartDate = f.formatDate(f.a((Date) myDate, -1));
			queryEndDate = f.formatDate(f.a((Date) myDate, -1));
		}

		Result result = null;
		List mapList = this.cn.findwswjcqk(queryStartDate, queryEndDate, ificu);
		result = new Result("success");
		result.setData(mapList);
		result.setMsg(f.c(f.k(queryStartDate, "yyyy-MM-dd"), "yyyy/MM/dd") + "~"
				+ f.c(f.k(queryEndDate, "yyyy-MM-dd"), "MM/dd"));
		result.setExpandValue(queryStartDate + "~" + queryEndDate);
		this.b(response, result);
	}

	@RequestMapping({"/xn011Dclymx/f_json/dcnyfbt"})
	@ResponseBody
	public void j(HttpServletRequest request, HttpServletResponse response, String dateType, String ificu, String date,
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
		} else if (dateType.equals("day")) {
			if ("back".equals(orderType)) {
				myDate = f.a((Date) myDate, 0);
			}

			if ("next".equals(orderType)) {
				myDate = f.a((Date) myDate, 2);
			}

			queryStartDate = f.formatDate(f.a((Date) myDate, -1));
			queryEndDate = f.formatDate(f.a((Date) myDate, -1));
		}

		Result result = null;
		List mapList = this.cn.dcnyfbt(queryStartDate, queryEndDate, ificu);
		result = new Result("success");
		result.setData(mapList);
		result.setMsg(f.c(f.k(queryStartDate, "yyyy-MM-dd"), "yyyy/MM/dd") + "~"
				+ f.c(f.k(queryEndDate, "yyyy-MM-dd"), "MM/dd"));
		result.setExpandValue(queryStartDate + "~" + queryEndDate);
		this.b(response, result);
	}

	@RequestMapping({"/xn011Dclymx/f_json/grblfbt"})
	@ResponseBody
	public void k(HttpServletRequest request, HttpServletResponse response, String dateType, String ificu, String date,
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
		} else if (dateType.equals("day")) {
			if ("back".equals(orderType)) {
				myDate = f.a((Date) myDate, 0);
			}

			if ("next".equals(orderType)) {
				myDate = f.a((Date) myDate, 2);
			}

			queryStartDate = f.formatDate(f.a((Date) myDate, -1));
			queryEndDate = f.formatDate(f.a((Date) myDate, -1));
		}

		Result result = null;
		List mapList = this.cn.grblfbt(queryStartDate, queryEndDate, ificu);
		result = new Result("success");
		result.setData(mapList);
		result.setMsg(f.c(f.k(queryStartDate, "yyyy-MM-dd"), "yyyy/MM/dd") + "~"
				+ f.c(f.k(queryEndDate, "yyyy-MM-dd"), "MM/dd"));
		result.setExpandValue(queryStartDate + "~" + queryEndDate);
		this.b(response, result);
	}

	@RequestMapping({"/xn011Dclymx/f_json/findMainSamples"})
	@ResponseBody
	public void R(HttpServletRequest request, HttpServletResponse response, String dataRange) {
		Result result = null;

		try {
			Date e = f.getYearLast();
			if (StringUtils.isEmpty(dataRange)) {
				dataRange = "month";
			}

			Date startDate;
			if ("month".equals(dataRange)) {
				startDate = f.a(e, false);
			} else if ("quarter".equals(dataRange)) {
				startDate = f.u(e);
			} else {
				startDate = f.getYearFirst();
			}

			List list = this.cn.findMainSamples(startDate, e);
			result = new Result("success");
			result.setData(list);
		} catch (Exception arg7) {
			result = new Result("error", arg7.getMessage());
			arg7.printStackTrace();
		}

		this.b(response, result);
	}

	@RequestMapping({"/xn011Dclymx/f_json/findMainFocusBacteria"})
	@ResponseBody
	public void S(HttpServletRequest request, HttpServletResponse response, String dataRange) {
		Result result = null;

		try {
			Date e = f.p(new Date());
			if (StringUtils.isEmpty(dataRange)) {
				dataRange = "month";
			}

			Date startDate;
			if ("month".equals(dataRange)) {
				startDate = f.a(e, false);
			} else if ("quarter".equals(dataRange)) {
				startDate = f.u(e);
			} else {
				startDate = f.getYearFirst();
			}

			List list = this.cn.findMainFocusBacteria(startDate, e);
			result = new Result("success");
			result.setData(list);
		} catch (Exception arg7) {
			result = new Result("error", arg7.getMessage());
			arg7.printStackTrace();
		}

		this.b(response, result);
	}

	@RequestMapping({"/xn011Dclymx/f_json/findMainMoreResistant"})
	@ResponseBody
	public void T(HttpServletRequest request, HttpServletResponse response, String dataRange) {
		Result result = null;

		try {
			Date e = f.p(f.a(f.getCurDate(), -1));
			Date startDate = f.q(f.a(e, -6));
			LinkedList value = new LinkedList();
			LinkedList xAxisData = new LinkedList();
			LinkedList legendData = new LinkedList();
			LinkedList series = new LinkedList();
			List list = this.cn.findMainMoreResistant(startDate, e);
			Iterator arg12 = list.iterator();

			while (arg12.hasNext()) {
				Map resultMap = (Map) arg12.next();
				xAxisData.add(f.formatDate((Date) resultMap.get("datetime")));
				value.add(Integer.valueOf(r.d(resultMap.get("value"))));
			}

			series.add(value);
			HashMap resultMap1 = new HashMap();
			resultMap1.put("legendData", legendData);
			resultMap1.put("xAxisData", xAxisData);
			resultMap1.put("series", series);
			result = new Result("success");
			result.setData(resultMap1);
		} catch (Exception arg13) {
			result = new Result("error", arg13.getMessage());
			arg13.printStackTrace();
		}

		this.a(response, result);
	}

	@RequestMapping({"/xn011Dclymx/f_json/updateGrlx"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Xn011Dclymx xn011Dclymx,
			Integer infectTypeId, String infectTypeName, String remindClinic, String messageToClinic) {
		Result result = new Result();
		Xn011Dclymx xn011Dclymx2 = new Xn011Dclymx();
		if (xn011Dclymx != null && infectTypeId != null) {
			xn011Dclymx2.setDt(xn011Dclymx.getDt());
			xn011Dclymx2.setSurveyDeptId(xn011Dclymx.getSurveyDeptId());
			xn011Dclymx2.setOrderno(xn011Dclymx.getOrderno());
			xn011Dclymx2.setZyid(xn011Dclymx.getZyid());
			if (infectTypeId.intValue() == 0) {
				xn011Dclymx2.setInfectTypeId((Integer) null);
				xn011Dclymx2.setInfectTypeName("");
			} else {
				xn011Dclymx2.setInfectTypeId(infectTypeId);
				xn011Dclymx2.setInfectTypeName(infectTypeName);
			}

			xn011Dclymx2.setChangeDt(new Date());
			xn011Dclymx2.setChangeUserid(this.d(request).getUsername());

			try {
				this.cn.updateIT(xn011Dclymx2);
				if ("YES".equals(remindClinic)) {
					Xn011Dclymx e = this.cn.getByPrimaryKeys(xn011Dclymx2);
					if (e != null) {
						AcAccount user = (AcAccount) this.b(request);
						System.err.println("------=消息信息=-------  ：  " + e.getZyid() + " - " + user.getUsername() + " - "
								+ e.getDeptId() + " - " + messageToClinic);
						this.cV.a(e.getZyid(), (String) null, user.getUsername(), user.getRealname(), messageToClinic,
								(String[]) null, new String[]{e.getDeptId()}, al.jm.getValue(), (String) null);
					}
				}

				result.setResult("success");
				result.setMsg("操作成功！");
			} catch (Exception arg12) {
				arg12.printStackTrace();
				c.error("更新感染类型出错！", arg12);
				result.setResult("error");
				result.setMsg("保存出错，操作失败！");
			}
		} else {
			result.setResult("error");
			result.setMsg("必要参数缺失，操作失败！");
		}

		this.a(response, result);
	}

	@RequestMapping({"/xn011Dclymx/f_view/mdr/type/optsDetail"})
	@SqlLog(p = "检出菌搜索--检出菌操作日志")
	public String a(HttpServletRequest request, ModelMap modelMap, Xn011Dclymx xn011Dclymx) {
		if (xn011Dclymx != null) {
			Xn011Dclymx xn = this.cn.getByPrimaryKeys(xn011Dclymx);
			if (xn != null && xn.getInfectTypeId() != null) {
				AcAccount bk001Sbk = this.d.b(xn.getChangeUserid());
				if (bk001Sbk != null) {
					xn.setChangeUserid(bk001Sbk.getRealname());
				} else {
					Zg003Yyzg zg003Yyzg = this.q.get(xn.getChangeUserid());
					if (zg003Yyzg != null) {
						xn.setChangeUserid(zg003Yyzg.getEmployeeName());
					}
				}

				modelMap.put("settingInfo", xn);
			}

			if (ab.isNotEmpty(xn011Dclymx.getTestOrderNo()) && ab.isNotEmpty(xn011Dclymx.getPathoCode())) {
				List bk001Sbk1 = this.bW.getByTestNoAndPathoId(xn011Dclymx.getTestOrderNo(),
						xn011Dclymx.getPathoCode());
				if (bk001Sbk1 != null && !bk001Sbk1.isEmpty()) {
					modelMap.put("reportInfo", bk001Sbk1.get(0));
				}
			}
		}

		return "mdr/mdrOptsDetail";
	}
}