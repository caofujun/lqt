package com.nis.patient.controller;

import com.nis.access.entity.AcAccount;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.bg;
import com.nis.comm.enums.e;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.l;
import com.nis.comm.utils.r;
import com.nis.comm.utils.z;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import com.nis.param.service.SysParamService;
import com.nis.patient.controller.St005SsxxbController.1;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.entity.St005Ssxxb;
import com.nis.patient.service.St003CryxxbService;
import com.nis.patient.service.St005SsxxbService;
import com.nis.zg.entity.Zg011Ss;
import com.nis.zg.service.Zg011SsService;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
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
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class St005SsxxbController extends BaseController {
	private static final Logger c = Logger.getLogger(St005SsxxbController.class);
	@Autowired
	private St005SsxxbService bO;
	@Autowired
	private St003CryxxbService bg;
	@Autowired
	private SysParamService j;
	@Autowired
	private SysDictService p;
	@Autowired
	private Zg011SsService wt;

	@RequestMapping({"/st005Ssxxb/f_view/toRecordList"})
	public String K(HttpServletRequest request, ModelMap modelMap, String zyid) {
		return "patient/operationList";
	}

	@RequestMapping({"/f_task/ssxxb/resetNNIS"})
   public void V(HttpServletRequest request, HttpServletResponse response, String archived, String nnis) {
      Result result = new Result();
      (new Thread(new 1(this, archived, nnis))).start();
      this.a(response, result);
   }

	@RequestMapping({"/st005Ssxxb/f_json/findRecordList"})
	@ResponseBody
	@SqlLog(p = "患者信息--手术列表")
	public void ac(HttpServletRequest request, HttpServletResponse response, String zyid) {
		List st005SsxxbList = null;
		if (ab.isNotEmpty(zyid)) {
			st005SsxxbList = this.bO.findListByZyid(zyid);
		}

		MyPage page = new MyPage(1, st005SsxxbList.size(), st005SsxxbList.size(), st005SsxxbList);
		this.a(response, page);
	}

	@RequestMapping({"/st005Ssxxb/f_view/toSelect"})
	public String U(HttpServletRequest request, ModelMap modelMap, String patientId) {
		return "patient/operationSelect";
	}

	@RequestMapping({"/st005Ssxxb/f_view/toSelectList"})
	public String m(HttpServletRequest request, ModelMap modelMap, String patientId) {
		return "patient/operationListSelect";
	}

	@RequestMapping({"/st005Ssxxb/f_view/addSimple"})
	public String A(HttpServletRequest request, ModelMap modelMap, String zyid, String patientId) {
		St003Cryxxb st003Cryxxb = this.bg.get(zyid);
		modelMap.put("st003Cryxxb", st003Cryxxb);
		return "patient/operationListAdd";
	}

	@RequestMapping({"/st005Ssxxb/f_json/findSelectList"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String patientId, Date operAt) {
		List st005SsxxbList = null;
		if (ab.isNotEmpty(patientId)) {
			st005SsxxbList = this.bO.findListByPatientId(patientId, operAt);
		}

		this.a(response, st005SsxxbList);
	}

	@RequestMapping({"/st005Ssxxb/f_view/toSurgeryList"})
	public String V(HttpServletRequest request, ModelMap modelMap, String patientId) {
		String riskRateType = this.j.findByParamCode(Param.NIS_OPERA_RISK_RATE);
		modelMap.put("riskRateType", riskRateType);
		Date currDate = f.getCurDate();
		modelMap.put("queryEndDate", f.c(currDate, "yyyy-MM-dd"));
		List list = this.p.u("gr_type", (String) null);
		LinkedList dateSectionList = new LinkedList();
		Iterator e = list.iterator();

		while (true) {
			SysDict dataSections;
			do {
				if (!e.hasNext()) {
					String dataSections1 = l.toString(dateSectionList);
					modelMap.put("dataSections", dataSections1);

					try {
						modelMap.put("queryStartDate", f.c(f.a(currDate, -30), "yyyy-MM-dd"));
					} catch (NumberFormatException arg10) {
						arg10.printStackTrace();
					}

					return "surgery/surgeryList";
				}

				dataSections = (SysDict) e.next();
			} while (!dataSections.getDictCode().equals("1") && !dataSections.getDictCode().equals("2"));

			HashMap map = new HashMap();
			map.put("value", dataSections.getDictCode());
			map.put("text", dataSections.getDictName() == null ? "" : dataSections.getDictName());
			dateSectionList.add(map);
		}
	}

	@RequestMapping({"/st005Ssxxb/f_view/toSurgeryDayList"})
	@SqlLog(p = "患者每日手术--手术数量统计")
	public String W(HttpServletRequest request, ModelMap modelMap, String date) {
		String riskRateType = this.j.findByParamCode(Param.NIS_OPERA_RISK_RATE);
		modelMap.put("riskRateType", riskRateType);
		if (ab.isEmpty(date)) {
			String list = this.j.findByParamCode(Param.NIS_MONITOR_DAY_DATETYPE);
			if (list.split(",").length > 1 && r.isNumber(list.split(",")[1])) {
				date = f.c(f.a(new Date(), Integer.parseInt(list.split(",")[1])), "yyyy-MM-dd");
			} else {
				date = f.c(f.a(new Date(), -1), "yyyy-MM-dd");
			}
		}

		modelMap.put("date", date);
		List list1 = this.p.u("gr_type", (String) null);
		LinkedList dateSectionList = new LinkedList();
		Iterator arg7 = list1.iterator();

		while (true) {
			SysDict dataSections;
			do {
				if (!arg7.hasNext()) {
					String dataSections1 = l.toString(dateSectionList);
					modelMap.put("dataSections", dataSections1);
					return "surgery/surgeryDayList";
				}

				dataSections = (SysDict) arg7.next();
			} while (!dataSections.getDictCode().equals("1") && !dataSections.getDictCode().equals("2"));

			HashMap map = new HashMap();
			map.put("value", dataSections.getDictCode());
			map.put("text", dataSections.getDictName() == null ? "" : dataSections.getDictName());
			dateSectionList.add(map);
		}
	}

	@RequestMapping({"/st005Ssxxb/f_json/findSurgeryDayList"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, St005Ssxxb st005Ssxxb) {
		if ("gx".equals(st005Ssxxb.getDeptType())) {
			LoginUser page = this.d(request);
			if (ab.isNotEmpty(page.getScopeInfo())) {
				String[] deptIds = page.getScopeInfo().split(",");
				st005Ssxxb.setDeptIdIn(Arrays.asList(deptIds));
			} else {
				st005Ssxxb.setDeptIdIn(Arrays.asList(new String[]{"0"}));
			}
		}

		List page1 = this.bO.findSurgeryDayList(st005Ssxxb);
		if (page1 != null && page1.size() > 0) {
			this.bO.M(page1);
		}

		this.a(response, page1);
	}

	@RequestMapping({"/st005Ssxxb/f_json/dayCount"})
	@ResponseBody
	@SqlLog(p = "患者每日手术--手术数量统计")
	public void b(HttpServletRequest request, HttpServletResponse response, St005Ssxxb st005Ssxxb) {
		if ("gx".equals(st005Ssxxb.getDeptType())) {
			LoginUser map = this.d(request);
			if (ab.isNotEmpty(map.getScopeInfo())) {
				String[] deptIds = map.getScopeInfo().split(",");
				st005Ssxxb.setDeptIdIn(Arrays.asList(deptIds));
			} else {
				st005Ssxxb.setDeptIdIn(Arrays.asList(new String[]{"0"}));
			}
		}

		Map map1 = this.bO.g(st005Ssxxb);
		this.a(response, map1);
	}

	@RequestMapping({"/st005Ssxxb/f_json/findSurgeryList"})
	@ResponseBody
	@SqlLog(p = "手术搜索--手术搜索列表")
	public void c(HttpServletRequest request, HttpServletResponse response, St005Ssxxb st005Ssxxb) {
		if (ab.isNotEmpty(st005Ssxxb.getGx())) {
			LoginUser ac = this.d(request);
			if (ab.isNotEmpty(ac.getScopeInfo())) {
				String[] page = ac.getScopeInfo().split(",");
				st005Ssxxb.setDeptIdIn(Arrays.asList(page));
			} else {
				st005Ssxxb.setDeptIdIn(Arrays.asList(new String[]{"0"}));
			}
		}

		AcAccount ac1 = (AcAccount) this.b(request);
		if (e.fE.getValue().equals(ac1.getRoleCur().getRoleType())) {
			String page1 = (String) this.b(request, "zg_dept");
			st005Ssxxb.setDeptId(page1);
		}

		if (st005Ssxxb.getOperName() != null && !"".equals(st005Ssxxb.getOperName())) {
			st005Ssxxb.setOperName(ab.aR(st005Ssxxb.getOperName()));
		}

		if (st005Ssxxb.getOperRoom() != null && !"".equals(st005Ssxxb.getOperRoom())) {
			st005Ssxxb.setOperRoom(ab.aR(st005Ssxxb.getOperRoom()));
		}

		MyPage page2 = this.bO.b(st005Ssxxb);
		if (page2.getRows() != null && page2.getRows().size() > 0) {
			this.bO.M(page2.getRows());
		}

		this.a(response, page2);
	}

	@RequestMapping({"/st005Ssxxb/f_json/exportExcelSsxx"})
	@ResponseBody
	@SqlLog(p = "手术搜索--手术搜索导出")
	public void d(HttpServletRequest request, HttpServletResponse response, St005Ssxxb st005Ssxxb)
			throws UnsupportedEncodingException {
		String name = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());

		try {
			response.setHeader("Content-disposition",
					"attachment; filename=" + ab.a("外科手术导出_", request) + name + ".xls");
			response.setContentType("application/msexcel;charset=UTF-8");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			if (ab.isNotEmpty(st005Ssxxb.getSearchString())) {
				st005Ssxxb.setSearchString(URLDecoder.decode(st005Ssxxb.getSearchString(), "utf-8"));
			}

			if (ab.isNotEmpty(st005Ssxxb.getOperName())) {
				st005Ssxxb.setOperName(URLDecoder.decode(st005Ssxxb.getOperName(), "utf-8"));
			}

			if (ab.isNotEmpty(st005Ssxxb.getIncisionGrade())) {
				st005Ssxxb.setIncisionGrade(URLDecoder.decode(st005Ssxxb.getIncisionGrade(), "utf-8"));
			}

			HSSFWorkbook e = this.bO.c(st005Ssxxb);
			ServletOutputStream os = response.getOutputStream();
			e.write(os);
			os.flush();
			os.close();
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
		}

	}

	@RequestMapping({"/st005Ssxxb/f_json/exportExcelSsxxDay"})
	@ResponseBody
	@SqlLog(p = "每日手术--每日手术导出")
	public void e(HttpServletRequest request, HttpServletResponse response, St005Ssxxb st005Ssxxb)
			throws UnsupportedEncodingException {
		String name = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());

		try {
			response.setHeader("Content-disposition",
					"attachment; filename=" + ab.a("每日手术导出_", request) + name + ".xls");
			response.setContentType("application/msexcel;charset=UTF-8");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			if (ab.isNotEmpty(st005Ssxxb.getIncisionGrade())) {
				st005Ssxxb.setIncisionGrade(URLDecoder.decode(st005Ssxxb.getIncisionGrade(), "utf-8"));
			}

			if ("gx".equals(st005Ssxxb.getDeptType())) {
				LoginUser e = this.d(request);
				if (ab.isNotEmpty(e.getScopeInfo())) {
					String[] os = e.getScopeInfo().split(",");
					st005Ssxxb.setDeptIdIn(Arrays.asList(os));
				} else {
					st005Ssxxb.setDeptIdIn(Arrays.asList(new String[]{"0"}));
				}
			}

			HSSFWorkbook e1 = this.bO.d(st005Ssxxb);
			ServletOutputStream os1 = response.getOutputStream();
			e1.write(os1);
			os1.flush();
			os1.close();
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
		}

	}

	@RequestMapping({"/st005Ssxxb/f_json/updViewFlag"})
	@ResponseBody
	@SqlLog(p = "手术搜索--更新查看状态")
	public void f(HttpServletRequest request, HttpServletResponse response, St005Ssxxb st005Ssxxb) {
		Result result = null;

		try {
			result = new Result();
			if (ab.isNotEmpty(st005Ssxxb.getId())) {
				this.bO.e(st005Ssxxb);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/st005Ssxxb/f_json/archive/status/update"})
	@ResponseBody
	@SqlLog(p = "手术搜索--更新归档状态")
	public void g(HttpServletRequest request, HttpServletResponse response, St005Ssxxb st005Ssxxb) {
		Result result = null;

		try {
			result = new Result();
			if (ab.isNotEmpty(st005Ssxxb.getId())) {
				if ("4".equals("" + st005Ssxxb.getStatus())) {
					AcAccount e = (AcAccount) this.b(request);
					st005Ssxxb.setLastLog(e.getUserId());
					st005Ssxxb.setLastModUserid(e.getRealname());
					st005Ssxxb.setLastModDate(f.getCurDate());
				}

				this.bO.f(st005Ssxxb);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/st005Ssxxb/f_view/toSurgeryInfoAdd"})
	@SqlLog(p = "新增手术")
	public String Z(HttpServletRequest request, ModelMap modelMap) {
		String inputRequired = this.j.findByParamCode(Param.NIS_OPERA_INPUT_REQUIRED);
		modelMap.put("inputRequired", inputRequired);
		List list = this.p.u("opera_before_time", (String) null);
		LinkedList dateSectionList = new LinkedList();
		Iterator list2 = list.iterator();

		while (list2.hasNext()) {
			SysDict dateSections = (SysDict) list2.next();
			HashMap dateSectionList2 = new HashMap();
			dateSectionList2.put("value", dateSections.getDictCode());
			dateSectionList2.put("text", dateSections.getDictName() == null ? "" : dateSections.getDictName());
			dateSectionList.add(dateSectionList2);
		}

		String dateSections1 = l.toString(dateSectionList);
		modelMap.put("dateSectionsBefore", dateSections1);
		List list21 = this.p.u("opera_after_time", (String) null);
		LinkedList dateSectionList21 = new LinkedList();
		Iterator list3 = list21.iterator();

		while (list3.hasNext()) {
			SysDict dateSections2 = (SysDict) list3.next();
			HashMap yymdList3 = new HashMap();
			yymdList3.put("value", dateSections2.getDictCode());
			yymdList3.put("text", dateSections2.getDictName() == null ? "" : dateSections2.getDictName());
			dateSectionList21.add(yymdList3);
		}

		String dateSections21 = l.toString(dateSectionList21);
		modelMap.put("dateSectionsAfter", dateSections21);
		List list31 = this.p.u("medication_purpose", (String) null);
		LinkedList yymdList31 = new LinkedList();
		Iterator list4 = list31.iterator();

		while (list4.hasNext()) {
			SysDict yymd3 = (SysDict) list4.next();
			HashMap szyzjyyewzjList4 = new HashMap();
			szyzjyyewzjList4.put("value", yymd3.getDictCode());
			szyzjyyewzjList4.put("text", yymd3.getDictName() == null ? "" : yymd3.getDictName());
			yymdList31.add(szyzjyyewzjList4);
		}

		String yymd31 = l.toString(yymdList31);
		modelMap.put("yymd", yymd31);
		List list41 = this.p.u("yes_or_no", (String) null);
		LinkedList szyzjyyewzjList41 = new LinkedList();
		Iterator arg15 = list41.iterator();

		while (arg15.hasNext()) {
			SysDict szyzjyyewzj = (SysDict) arg15.next();
			HashMap map = new HashMap();
			map.put("value", szyzjyyewzj.getDictCode());
			map.put("text", szyzjyyewzj.getDictName() == null ? "" : szyzjyyewzj.getDictName());
			szyzjyyewzjList41.add(map);
		}

		String szyzjyyewzj1 = l.toString(szyzjyyewzjList41);
		modelMap.put("szyzjyyewzj", szyzjyyewzj1);
		return "surgery/surgeryInfoAdd";
	}

	@RequestMapping({"/st005Ssxxb/f_view/toSurgeryInfoEdit"})
	@SqlLog(p = "手术编辑--手术详情")
	public String a(HttpServletRequest request, ModelMap modelMap, String id, Integer nnis) {
		String inputRequired = this.j.findByParamCode(Param.NIS_OPERA_INPUT_REQUIRED);
		modelMap.put("inputRequired", inputRequired);
		if (ab.isNotEmpty(id)) {
			St005Ssxxb st005Ssxxb = this.bO.get(id);
			List zg011SsList = null;
			if (st005Ssxxb != null) {
				zg011SsList = this.wt.getOperaInfo(st005Ssxxb.getOperName());
				if (zg011SsList.size() > 0) {
					if (ab.isNotEmpty(((Zg011Ss) zg011SsList.get(0)).getOpepartKindid())) {
						st005Ssxxb.setOpepartkindid(((Zg011Ss) zg011SsList.get(0)).getOpepartKindid());
					}

					if (ab.isNotEmpty(((Zg011Ss) zg011SsList.get(0)).getImpOpeId())) {
						st005Ssxxb.setImpOpeId(((Zg011Ss) zg011SsList.get(0)).getImpOpeId());
					}

					if (ab.isNotEmpty(((Zg011Ss) zg011SsList.get(0)).getImpOpeName())) {
						st005Ssxxb.setImpOpeName(((Zg011Ss) zg011SsList.get(0)).getImpOpeName());
					}
				}
			}

			this.bO.a(st005Ssxxb, true);
			modelMap.put("st005Ssxxb", st005Ssxxb);
			List list = this.p.u("opera_before_time", (String) null);
			LinkedList dateSectionList = new LinkedList();
			Iterator list2 = list.iterator();

			while (list2.hasNext()) {
				SysDict dateSections = (SysDict) list2.next();
				HashMap dateSectionList2 = new HashMap();
				dateSectionList2.put("value", dateSections.getDictCode());
				dateSectionList2.put("text", dateSections.getDictName() == null ? "" : dateSections.getDictName());
				dateSectionList.add(dateSectionList2);
			}

			String dateSections1 = l.toString(dateSectionList);
			modelMap.put("dateSectionsBefore", dateSections1);
			List list21 = this.p.u("opera_after_time", (String) null);
			LinkedList dateSectionList21 = new LinkedList();
			Iterator list3 = list21.iterator();

			while (list3.hasNext()) {
				SysDict dateSections2 = (SysDict) list3.next();
				HashMap yymdList3 = new HashMap();
				yymdList3.put("value", dateSections2.getDictCode());
				yymdList3.put("text", dateSections2.getDictName() == null ? "" : dateSections2.getDictName());
				dateSectionList21.add(yymdList3);
			}

			String dateSections21 = l.toString(dateSectionList21);
			modelMap.put("dateSectionsAfter", dateSections21);
			List list31 = this.p.u("medication_purpose", (String) null);
			LinkedList yymdList31 = new LinkedList();
			Iterator list4 = list31.iterator();

			while (list4.hasNext()) {
				SysDict yymd3 = (SysDict) list4.next();
				HashMap szyzjyyewzjList4 = new HashMap();
				szyzjyyewzjList4.put("value", yymd3.getDictCode());
				szyzjyyewzjList4.put("text", yymd3.getDictName() == null ? "" : yymd3.getDictName());
				yymdList31.add(szyzjyyewzjList4);
			}

			String yymd31 = l.toString(yymdList31);
			modelMap.put("yymd", yymd31);
			List list41 = this.p.u("yes_or_no", (String) null);
			LinkedList szyzjyyewzjList41 = new LinkedList();
			Iterator arg19 = list41.iterator();

			while (arg19.hasNext()) {
				SysDict szyzjyyewzj = (SysDict) arg19.next();
				HashMap map = new HashMap();
				map.put("value", szyzjyyewzj.getDictCode());
				map.put("text", szyzjyyewzj.getDictName() == null ? "" : szyzjyyewzj.getDictName());
				szyzjyyewzjList41.add(map);
			}

			String szyzjyyewzj1 = l.toString(szyzjyyewzjList41);
			modelMap.put("szyzjyyewzj", szyzjyyewzj1);
		}

		return "surgery/surgeryInfoEdit";
	}

	@RequestMapping({"/st005Ssxxb/f_view/surgeryInfoAdd"})
	@SqlLog(p = "添加手术")
	public void h(HttpServletRequest request, HttpServletResponse response, St005Ssxxb st005Ssxxb) {
		Result result = null;

		try {
			result = new Result();
			st005Ssxxb.setId(z.a(bg.mS));
			st005Ssxxb.setRelid(st005Ssxxb.getId());
			if ("4".equals("" + st005Ssxxb.getStatus())) {
				AcAccount e = (AcAccount) this.b(request);
				st005Ssxxb.setLastModUserid(e.getRealname());
				st005Ssxxb.setLastLog(e.getUserId());
				st005Ssxxb.setLastModDate(f.getCurDate());
			}

			this.bO.save(st005Ssxxb);
			result.setResult("success");
			result.setData("id=" + st005Ssxxb.getId());
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/st005Ssxxb/f_view/operationAdd"})
	public void i(HttpServletRequest request, HttpServletResponse response, St005Ssxxb st005Ssxxb) {
		Result result = null;

		try {
			result = new Result();
			st005Ssxxb.setId(z.a(bg.mS));
			st005Ssxxb.setRelid(st005Ssxxb.getId());
			st005Ssxxb.setStatus(Integer.valueOf(4));
			St003Cryxxb e = this.bg.get(st005Ssxxb.getZyid());
			st005Ssxxb.setPatientId(e.getPatientId());
			st005Ssxxb.setPatientName(e.getPatientName());
			st005Ssxxb.setSex(e.getSex());
			st005Ssxxb.setBedNo(e.getBedNo());
			st005Ssxxb.setAge(e.getAge());
			st005Ssxxb.setAgeUnit(e.getAgeUnit());
			st005Ssxxb.setDeptId(e.getDeptCode());
			st005Ssxxb.setDeptName(e.getDeptName());
			st005Ssxxb.setInHospAt(e.getInHospAt());
			st005Ssxxb.setOutAt(e.getOutAt());
			this.bO.a(st005Ssxxb, true);
			this.bO.save(st005Ssxxb);
			result.setResult("success");
			result.setData(st005Ssxxb);
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}