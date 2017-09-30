package com.nis.monitor.controller;

import com.nis.access.entity.AcAccount;
import com.nis.cdc.service.CtgBk001CrbmasterService;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.al;
import com.nis.comm.enums.bg;
import com.nis.comm.enums.e;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.l;
import com.nis.comm.utils.s;
import com.nis.comm.utils.z;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import com.nis.dict.service.Zg005YygrzdService;
import com.nis.monitor.entity.Bk001Sbk;
import com.nis.monitor.entity.Bk002Grzd;
import com.nis.monitor.entity.Bk003Ygys;
import com.nis.monitor.entity.Bk004Sjbb;
import com.nis.monitor.entity.Gr002YsgrMx;
import com.nis.monitor.entity.Gr016BkKjyw;
import com.nis.monitor.entity.Gr016SsbwKjyw;
import com.nis.monitor.entity.St004BkCgxx;
import com.nis.monitor.service.Bk001SbkService;
import com.nis.monitor.service.Bk002GrzdService;
import com.nis.monitor.service.Bk003YgysService;
import com.nis.monitor.service.Bk004SjbbService;
import com.nis.monitor.service.Gr002YsgrMxService;
import com.nis.monitor.service.Gr016BkKjywService;
import com.nis.monitor.service.St004BkCgxxService;
import com.nis.msg.service.NyMessageDetailService;
import com.nis.organization.entity.Dep;
import com.nis.organization.service.DepService;
import com.nis.organization.service.UnitService;
import com.nis.param.service.SysParamService;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.entity.St004Yzxxb;
import com.nis.patient.entity.St020ClinicPatients;
import com.nis.patient.service.St003CryxxbService;
import com.nis.patient.service.St004YzxxbService;
import com.nis.patient.service.St020ClinicPatientsService;
import com.nis.zg.entity.Zg002Byks;
import com.nis.zg.service.Zg002ByksService;
import com.nis.zg.service.Zg031SqksService;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Bk001SbkController extends BaseController {
	private static final Logger c = Logger.getLogger(Bk001SbkController.class);
	@Autowired
	private Bk001SbkService bW;
	@Autowired
	private Bk004SjbbService co;
	@Autowired
	private Bk002GrzdService us;
	@Autowired
	private Bk003YgysService ut;
	@Autowired
	private Zg005YygrzdService E;
	@Autowired
	private St003CryxxbService bg;
	@Autowired
	private Gr002YsgrMxService bX;
	@Autowired
	private Zg002ByksService cZ;
	@Autowired
	private Zg031SqksService dF;
	@Autowired
	private DepService e;
	@Autowired
	private St020ClinicPatientsService bh;
	@Autowired
	private St003CryxxbService df;
	@Autowired
	private SysDictService p;
	@Autowired
	private CtgBk001CrbmasterService dj;
	@Autowired
	private SysParamService j;
	@Autowired
	private St004YzxxbService bu;
	@Autowired
	private Gr016BkKjywService uu;
	@Autowired
	private NyMessageDetailService cV;
	@Autowired
	private St004BkCgxxService uv;
	@Autowired
	private UnitService se;

	@RequestMapping({"/bk001Sbk/f_view/toInfectionsQuery"})
	public String a(HttpServletRequest request, ModelMap modelMap, String bkState, String queryStartDate,
			String queryEndDate, Integer infectTypeId) {
		AcAccount ac = (AcAccount) this.b(request);
		Date currDate = f.getCurDate();
		if (ab.isNotEmpty(queryEndDate)) {
			modelMap.put("queryEndDate", queryEndDate);
		} else {
			modelMap.put("queryEndDate", f.c(currDate, "yyyy-MM-dd"));
		}

		try {
			if (ab.isNotEmpty(queryStartDate)) {
				modelMap.put("queryStartDate", queryStartDate);
			} else {
				modelMap.put("queryStartDate", f.c(f.a(currDate, -30), "yyyy-MM-dd"));
			}
		} catch (NumberFormatException arg9) {
			arg9.printStackTrace();
		}

		if (e.fE.getValue().equals(ac.getRoleCur().getRoleType())) {
			modelMap.put("clinical", "1");
			modelMap.put("reportDeptId", this.b(request, "zg_dept"));
		}

		modelMap.put("bkState", bkState);
		modelMap.put("unitFlag", this.se.getAll().size() > 1 ? "1" : "0");
		modelMap.put("infectTypeId", Integer.valueOf(infectTypeId == null ? 1 : infectTypeId.intValue()));
		return "monitor/infectionsQuery";
	}

	@RequestMapping({"/bk001Sbk/f_json/findInfectionsCards"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Bk001Sbk bk001Sbk, String relation,
			Integer reportStates) {
		String dataField = bk001Sbk.getDateField();
		if (ab.isNotEmpty(dataField)) {
			if (dataField.equals("2")) {
				bk001Sbk.setDateField("infect_date");
			} else if (dataField.equals("3")) {
				bk001Sbk.setDateField("st3.in_hosp_at");
			} else if (dataField.equals("4")) {
				bk001Sbk.setDateField("st3.out_at");
			} else if (dataField.equals("5")) {
				bk001Sbk.setDateField("bk2.auth_at");
			} else {
				bk001Sbk.setDateField("bk1.report_at");
			}
		}

		if (ab.isNotEmpty(relation)) {
			bk001Sbk.setRelationIn(new ArrayList(Arrays.asList(relation.split(","))));
		}

		AcAccount ac = (AcAccount) this.b(request);
		if (e.fE.getValue().equals(ac.getRoleCur().getRoleType())) {
			if (dataField.equals("2")) {
				bk001Sbk.setInfectDeptId("" + this.b(request, "zg_dept"));
			} else {
				bk001Sbk.setReportDeptId("" + this.b(request, "zg_dept"));
			}
		}

		if (bk001Sbk.getSearchString() != null && !"".equals(bk001Sbk.getSearchString())) {
			bk001Sbk.setSearchString(ab.aR(bk001Sbk.getSearchString()));
		}

		MyPage page = this.bW.b(bk001Sbk);
		Bk001Sbk bk001Footer = new Bk001Sbk();
		bk001Footer.setPatientId("合计：");
		bk001Footer.setPatientName(String.valueOf(page.getRows().size()));
		int reportNum = 0;

		Bk001Sbk bk001FooterList;
		for (Iterator arg11 = page.getRows().iterator(); arg11
				.hasNext(); reportNum += bk001FooterList.getReportNum().intValue()) {
			bk001FooterList = (Bk001Sbk) arg11.next();
		}

		bk001Footer.setReportNum(Integer.valueOf(reportNum));
		ArrayList bk001FooterList1 = new ArrayList();
		bk001FooterList1.add(bk001Footer);
		page.setFooter(bk001FooterList1);
		this.a(response, page);
	}

	@RequestMapping({"/bk001Sbk/f_view/sqgrCard"})
	public String D(HttpServletRequest request, ModelMap modelMap, String regId) {
		if (ab.isNotEmpty(regId)) {
			Gr002YsgrMx gr002YsgrMx = this.bX.getGr002YsgrMx(regId);
			gr002YsgrMx.setConfDate(new Date());
			Bk001Sbk bk001Sbk = new Bk001Sbk();
			AcAccount account = (AcAccount) this.b(request);
			bk001Sbk.setReportDrId(account.getUsername());
			bk001Sbk.setReportDrName(account.getRealname());
			Zg002Byks zg002Byks = this.cZ.getByDeptId(account.getDepNo());
			if (zg002Byks != null) {
				bk001Sbk.setReportDeptName(zg002Byks.getDeptName());
			}

			bk001Sbk.setReportDeptId(account.getDepNo());
			modelMap.put("bk001Sbk", bk001Sbk);
			int num = this.E.findNumByInfectCode("SSI", gr002YsgrMx.getInfectCode());
			modelMap.put("canSelOprat", Boolean.valueOf(num > 0));
			St003Cryxxb st003Cryxxb = this.bg.getSt003Cryxxb(gr002YsgrMx.getZyid());
			if (st003Cryxxb != null) {
				gr002YsgrMx.setInfectDeptId(st003Cryxxb.getDeptCode());
			}

			modelMap.put("st003Cryxxb", st003Cryxxb);
			modelMap.put("gr002YsgrMx", gr002YsgrMx);
		}

		modelMap.put("relid", z.a(bg.mU));
		return "monitor/sqgrCard";
	}

	@RequestMapping({"/bk001Sbk/f_view/toReportCards"})
	@SqlLog(p = "报卡--报卡详情")
	public String h(HttpServletRequest request, ModelMap modelMap, String relid, String bk2Relid, String action) {
		if (ab.isNotEmpty(relid) || ab.isNotEmpty(bk2Relid)) {
			AcAccount account = (AcAccount) this.b(request);
			if (ab.isNotEmpty(bk2Relid) && ab.isEmpty(relid)) {
				relid = this.us.get(bk2Relid).getRefid();
			}

			Bk001Sbk bk001Sbk = this.bW.findCardInfo(relid);
			modelMap.put("bk001Sbk", bk001Sbk);
			List LastBk002Grzd = this.us.getLastReport(bk001Sbk.getZyid());
			String lastRefid = "";
			if (LastBk002Grzd.size() > 0) {
				lastRefid = ((Bk002Grzd) LastBk002Grzd.get(0)).getRefid();
			}

			modelMap.put("lastRefid", lastRefid);
			String nbisr = this.j.findByParamCode(Param.NIS_BK_IS_SSMC_REQUIRED);
			modelMap.put("NBISR", nbisr);
			String isOpen = this.j.findByParamCode(Param.NIS_BK_IS_SSMC_OPEN);
			modelMap.put("ISOPEN", isOpen);
			String isCgsy = this.j.findByParamCode(Param.NIS_BK_IS_CGSY_OPEN);
			modelMap.put("ISCGSY", isCgsy);
			String ysbcurl = this.j.findByParamCode(Param.NIS_YSBC_URL);
			St003Cryxxb st003Cryxxb = this.bg.get(bk001Sbk.getZyid());
			bk001Sbk.setOutAt(st003Cryxxb.getOutAt());
			String patientId = st003Cryxxb.getPatientId();
			int visitId = st003Cryxxb.getVisitId().intValue();
			ysbcurl = ysbcurl.replace("{patientId}", patientId);
			ysbcurl = ysbcurl.replace("{zyid}", bk001Sbk.getZyid());
			ysbcurl = ysbcurl.replace("{visitId}", String.valueOf(visitId));
			modelMap.put("ysbcurl", ysbcurl);
			List infectList = this.us.getReportInfect(bk001Sbk.getRelid(), (String) null);
			modelMap.put("infectList", infectList);
			int num = this.us.findUnAuditCount(relid);
			modelMap.put("canAudit",
					Boolean.valueOf(num > 0 && e.fD.getValue().equals(account.getRoleCur().getRoleType())));
			int num1 = this.us.getReportInfect(relid, "1").size();
			modelMap.put("canDelete",
					Boolean.valueOf(num1 > 0 && e.fD.getValue().equals(account.getRoleCur().getRoleType())));
			boolean canEdit = bk001Sbk.getIsOk() == com.nis.comm.enums.z.hA.getValue()
					&& e.fD.getValue().equals(account.getRoleCur().getRoleType())
					|| bk001Sbk.getIsOk() == com.nis.comm.enums.z.hz.getValue();
			modelMap.put("canEdit", Boolean.valueOf(canEdit));
			AcAccount ac = (AcAccount) this.b(request);
			if (e.fE.getValue().equals(ac.getRoleCur().getRoleType())) {
				modelMap.put("clinical", "1");
			}

			Bk002Grzd bk002Grzd = this.us.getOneInfectByRefid(relid);
			modelMap.put("bk002Grzd", bk002Grzd);
			Bk003Ygys bk003 = new Bk003Ygys();
			bk003.setRefid(bk002Grzd.getRelid());
			List bk003List = this.ut.findSusceptFactors(bk003);
			modelMap.put("bk003List", bk003List);
		}

		return "edit".equals(action) ? "monitor/reportCardsInfoEditN" : "monitor/reportCardsInfoN";
	}

	@SqlLog(p = "报卡--编辑报卡详情")
	@RequestMapping({"/bk001Sbk/f_view/toReportCardsEdit"})
	public String E(HttpServletRequest request, ModelMap modelMap, String relid) {
		String isOpen;
		if (ab.isNotEmpty(relid)) {
			AcAccount nbisr = (AcAccount) this.b(request);
			Bk001Sbk isSb = this.bW.findCardInfo(relid);
			modelMap.put("bk001Sbk", isSb);
			isOpen = this.j.findByParamCode(Param.NIS_YSBC_URL);
			St003Cryxxb isCgsy = this.bg.get(isSb.getZyid());
			isSb.setOutAt(isCgsy.getOutAt());
			String patientId = isCgsy.getPatientId();
			int visitId = isCgsy.getVisitId().intValue();
			isOpen = isOpen.replace("{patientId}", patientId);
			isOpen = isOpen.replace("{zyid}", isSb.getZyid());
			isOpen = isOpen.replace("{visitId}", String.valueOf(visitId));
			modelMap.put("ysbcurl", isOpen);
			List infectList = this.us.getReportInfect(isSb.getRelid(), (String) null);
			modelMap.put("infectList", infectList);
			int num = this.us.findUnAuditCount(relid);
			modelMap.put("canAudit",
					Boolean.valueOf(num > 0 && e.fD.getValue().equals(nbisr.getRoleCur().getRoleType())));
			int num1 = this.us.getReportInfect(relid, "1").size();
			modelMap.put("canDelete", Boolean.valueOf(num1 > 0));
			Bk002Grzd bk002Grzd = this.us.getOneInfectByRefid(relid);
			modelMap.put("bk002Grzd", bk002Grzd);
			int onum = this.E.findNumByInfectCode("SSI", bk002Grzd.getInfectDiagnId());
			modelMap.put("canSelOprat", Boolean.valueOf(onum > 0));
			Bk003Ygys bk003 = new Bk003Ygys();
			bk003.setRefid(bk002Grzd.getRelid());
			List bk003List = this.ut.findSusceptFactors(bk003);
			modelMap.put("bk003List", bk003List);
			modelMap.put("refid", relid);
			List list3 = this.p.u("medication_purpose", (String) null);
			LinkedList yymdList3 = new LinkedList();
			Iterator arg19 = list3.iterator();

			while (arg19.hasNext()) {
				SysDict yymd3 = (SysDict) arg19.next();
				HashMap map = new HashMap();
				map.put("value", yymd3.getDictCode());
				map.put("text", yymd3.getDictName() == null ? "" : yymd3.getDictName());
				yymdList3.add(map);
			}

			String yymd31 = l.toString(yymdList3);
			modelMap.put("yymd", yymd31);
		}

		String nbisr1 = this.j.findByParamCode(Param.NIS_BK_IS_SSMC_REQUIRED);
		modelMap.put("NBISR", nbisr1);
		String isSb1 = this.j.findByParamCode(Param.NIS_BK_GRZDSB);
		modelMap.put("isSb", isSb1);
		isOpen = this.j.findByParamCode(Param.NIS_BK_IS_SSMC_OPEN);
		modelMap.put("ISOPEN", isOpen);
		String isCgsy1 = this.j.findByParamCode(Param.NIS_BK_IS_CGSY_OPEN);
		modelMap.put("ISCGSY", isCgsy1);
		modelMap.put("isYgys", this.j.findByParamCode(Param.NIS_BK_IS_YGYS_REQUIRED));
		modelMap.put("isIncision", this.j.findByParamCode(Param.NIS_BK_IS_INCISION_REQUIRED));
		return "monitor/reportCardsInfoEditN";
	}

	@RequestMapping({"/gr016BkKjyw/f_json/queryGr016BkKjyw"})
	@ResponseBody
	@SqlLog(p = "报卡--患者报卡抗菌药物列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Gr016BkKjyw gr016BkKjyw) {
		List gr016BkKjywList = null;
		String yzIdStr = "";
		if (gr016BkKjyw != null && ab.isNotEmpty(gr016BkKjyw.getRefid())) {
			gr016BkKjywList = this.uu.query(gr016BkKjyw);
			Iterator arg6 = gr016BkKjywList.iterator();

			while (arg6.hasNext()) {
				Gr016BkKjyw i = (Gr016BkKjyw) arg6.next();
				Date orderAt = i.getOrderAt();
				Date stopAt = i.getStopAt();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				cal.setTime(orderAt);
				long time1 = cal.getTimeInMillis();
				long time2 = 0L;
				if (stopAt != null) {
					cal.setTime(stopAt);
					time2 = cal.getTimeInMillis();
				} else {
					try {
						cal.setTime(df.parse(df.format(new Date())));
						time2 = cal.getTimeInMillis();
					} catch (ParseException arg18) {
						arg18.printStackTrace();
					}
				}

				long between_days = (time2 - time1) / 86400000L;
				int days = Integer.parseInt(String.valueOf(between_days));
				i.setDays(String.valueOf(days));
			}

			for (int arg19 = 0; arg19 < gr016BkKjywList.size(); ++arg19) {
				if (arg19 == gr016BkKjywList.size() - 1) {
					yzIdStr = yzIdStr + ((Gr016BkKjyw) gr016BkKjywList.get(arg19)).getYzId();
				} else {
					yzIdStr = yzIdStr + ((Gr016BkKjyw) gr016BkKjywList.get(arg19)).getYzId() + ",";
				}
			}
		}

		this.a(response, gr016BkKjywList);
	}

	@RequestMapping({"/gr016BkKjyw/f_json/query"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, St004Yzxxb st004Yzxxb) {
		List findDrugbyZyid = null;
		if (st004Yzxxb != null) {
			findDrugbyZyid = this.bu.findDrugbyZyid(st004Yzxxb);
			Iterator arg5 = findDrugbyZyid.iterator();

			while (arg5.hasNext()) {
				St004Yzxxb St004YzxxbTemp = (St004Yzxxb) arg5.next();
				Date orderAt = St004YzxxbTemp.getOrderAt();
				Date stopAt = St004YzxxbTemp.getStopAt();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				cal.setTime(orderAt);
				long time1 = cal.getTimeInMillis();
				long time2 = 0L;
				if (stopAt != null) {
					cal.setTime(stopAt);
					time2 = cal.getTimeInMillis();
				} else {
					try {
						cal.setTime(df.parse(df.format(new Date())));
						time2 = cal.getTimeInMillis();
					} catch (ParseException arg17) {
						arg17.printStackTrace();
					}
				}

				long between_days = (time2 - time1) / 86400000L;
				int days = Integer.parseInt(String.valueOf(between_days));
				St004YzxxbTemp.setDays(String.valueOf(days));
			}
		}

		this.a(response, findDrugbyZyid);
	}

	@RequestMapping({"/gr016BkKjyw/f_json/queryAad"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, St004Yzxxb st004Yzxxb) {
		List findDrugbyZyid = null;
		if (st004Yzxxb != null) {
			findDrugbyZyid = this.bu.findDrugAddbyZyid(st004Yzxxb);
			Iterator arg5 = findDrugbyZyid.iterator();

			while (arg5.hasNext()) {
				St004Yzxxb St004YzxxbTemp = (St004Yzxxb) arg5.next();
				Date orderAt = St004YzxxbTemp.getOrderAt();
				Date stopAt = St004YzxxbTemp.getStopAt();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				cal.setTime(orderAt);
				long time1 = cal.getTimeInMillis();
				long time2 = 0L;
				if (stopAt != null) {
					cal.setTime(stopAt);
					time2 = cal.getTimeInMillis();
				} else {
					try {
						cal.setTime(df.parse(df.format(new Date())));
						time2 = cal.getTimeInMillis();
					} catch (ParseException arg17) {
						arg17.printStackTrace();
					}
				}

				long between_days = (time2 - time1) / 86400000L;
				int days = Integer.parseInt(String.valueOf(between_days));
				St004YzxxbTemp.setDays(String.valueOf(days));
			}
		}

		this.a(response, findDrugbyZyid);
	}

	@RequestMapping({"/st004BkCgxx/f_json/querycgsy"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, St004BkCgxx st004BkCgxx) {
		List findDrugbyZyid = null;
		if (st004BkCgxx != null) {
			findDrugbyZyid = this.uv.findCgsybyZyid(st004BkCgxx);
		}

		this.a(response, findDrugbyZyid);
	}

	@RequestMapping({"/bk001Sbk/f_json/findCgsyInfo"})
	@ResponseBody
	@SqlLog(p = "报卡--患者报卡插管信息")
	public void b(HttpServletRequest request, HttpServletResponse response, St004BkCgxx st004BkCgxx) {
		List findDrugbyZyid = null;
		if (st004BkCgxx != null) {
			findDrugbyZyid = this.uv.findCgsyInfo(st004BkCgxx);
		}

		this.a(response, findDrugbyZyid);
	}

	@RequestMapping({"/st004BkCgxx/f_json/findcgsyByRefid"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, St004BkCgxx st004BkCgxx) {
		List findDrugbyZyid = null;
		if (st004BkCgxx != null) {
			findDrugbyZyid = this.uv.findcgsyByRefid(st004BkCgxx);
		}

		this.a(response, findDrugbyZyid);
	}

	@RequestMapping({"/gr016BkKjyw/f_json/delete"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, String relid) {
		Result result = null;

		try {
			result = new Result();
			this.uu.delete(relid);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/bk001Sbk/f_json/findPathogenDetection"})
	@ResponseBody
	@SqlLog(p = "报卡--报卡病原体")
	public void a(HttpServletRequest request, HttpServletResponse response, Bk004Sjbb bk004Sjbb) {
		List bk004List = null;
		if (bk004Sjbb != null && ab.isNotEmpty(bk004Sjbb.getRefid())) {
			bk004List = this.co.findPathogenDetection(bk004Sjbb);
		}

		this.a(response, bk004List);
	}

	@RequestMapping({"/bk001Sbk/f_json/findReportCards"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, Bk001Sbk bk001Sbk, String relation,
			Integer reportStates) {
		String dataField = bk001Sbk.getDateField();
		if (ab.isNotEmpty(dataField)) {
			if (dataField.equals("2")) {
				bk001Sbk.setDateField("infect_date");
			} else if (dataField.equals("3")) {
				bk001Sbk.setDateField("st3.in_hosp_at");
			} else if (dataField.equals("4")) {
				bk001Sbk.setDateField("st3.out_at");
			} else if (dataField.equals("5")) {
				bk001Sbk.setDateField("bk2.auth_at");
			} else {
				bk001Sbk.setDateField("bk1.report_at");
			}
		}

		if (ab.isNotEmpty(relation)) {
			bk001Sbk.setRelationIn(new ArrayList(Arrays.asList(relation.split(","))));
		}

		List data = this.bW.findReportCards(bk001Sbk);
		this.a(response, data);
	}

	@RequestMapping({"/bk001Sbk/f_view/toAuditCards"})
	public String F(HttpServletRequest request, ModelMap modelMap, String relid) {
		if (ab.isNotEmpty(relid)) {
			List bk002List = this.us.getReportInfect(relid, "0");
			modelMap.put("bk002List", bk002List);
		}

		return "monitor/auditReportCard";
	}

	@RequestMapping({"/bk001Sbk/f_json/toReturnCards"})
	public void U(HttpServletRequest request, HttpServletResponse response, String relid) {
		Result r = new Result();
		AcAccount account = (AcAccount) this.b(request);
		if (ab.isNotEmpty(relid)) {
			this.bW.b(relid, account);
			r.setResult("success");
		} else {
			r.setResult("error");
			r.setMsg("参数无效！");
		}

		this.a(response, r);
	}

	@RequestMapping({"/bk001Sbk/f_view/toOutcome"})
	public String G(HttpServletRequest request, ModelMap modelMap, String relid) {
		Bk002Grzd bk002Grzd = new Bk002Grzd();
		if (ab.isNotEmpty(relid)) {
			bk002Grzd = this.us.get(relid);
		}

		modelMap.put("bk002Grzd", bk002Grzd);
		return "monitor/outcome";
	}

	@RequestMapping({"/bk001Sbk/f_json/auditReportCard"})
	@ResponseBody
	@SqlLog(p = "报卡--报卡审核")
	public void a(HttpServletRequest request, HttpServletResponse response, String[] relids, Integer authStatus,
			String refid) {
		Result result = null;

		try {
			if (relids != null && relids.length > 0 && authStatus != null && ab.isNotEmpty(refid)) {
				Bk001Sbk e = this.bW.get(refid);
				if (e == null) {
					result = new Result("error", "参数异常");
				} else {
					result = new Result();
					ArrayList bk002List = new ArrayList();
					LoginUser user = this.d(request);
					Map params = s.k(request);
					String[] arg13 = relids;
					int arg12 = relids.length;

					for (int arg11 = 0; arg11 < arg12; ++arg11) {
						String relid = arg13[arg11];
						Bk002Grzd bk002Grzd = new Bk002Grzd();
						bk002Grzd.setRelid(relid);
						bk002Grzd.setAuthStatus(authStatus);
						if (authStatus.intValue() == 2) {
							bk002Grzd.setReturnReason((String) params.get("reason_" + relid));
						}

						bk002Grzd.setAuthAt(new Date());
						bk002Grzd.setAuthUserid(user.getUsername());
						bk002Grzd.setAuthUsername(user.getRealname());
						bk002List.add(bk002Grzd);
					}

					this.us.a(bk002List, authStatus, e, user);
					result.setResult("success");
				}
			} else {
				result = new Result("error", "参数丢失");
			}
		} catch (Exception arg15) {
			c.error("获取信息异常!", arg15);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/bk001Sbk/f_view/toAddReportCards"})
	@SqlLog(p = "报卡--新卡上报")
	public String a(HttpServletRequest request, ModelMap modelMap, String regId, String zyid, String patientId,
			Integer visitId, Integer infectTypeId, String testOrderNo, String infectDiagnId, String confirmDt,
			String infectDiagnName, String operName, String incisionGrade) throws UnsupportedEncodingException {
		String nbisr = this.j.findByParamCode(Param.NIS_BK_IS_SSMC_REQUIRED);
		modelMap.put("NBISR", nbisr);
		String isOpen = this.j.findByParamCode(Param.NIS_BK_IS_SSMC_OPEN);
		modelMap.put("ISOPEN", isOpen);
		String isCgsy = this.j.findByParamCode(Param.NIS_BK_IS_CGSY_OPEN);
		modelMap.put("ISCGSY", isCgsy);
		String kjywts = this.j.findByParamCode(Param.NIS_SB_KJYW_TS);
		modelMap.put("KJYWTS", kjywts);
		AcAccount list31;
		List st003Cryxxb1;
		if (ab.isNotEmpty(regId)) {
			Gr002YsgrMx isSb = this.bX.getGr002YsgrMx(regId);
			modelMap.put("gr002YsgrMx", isSb);
			int map;
			if (StringUtils.isNotBlank(isSb.getGr2Relid())) {
				list31 = (AcAccount) this.b(request);
				String yymdList31 = this.us.get(isSb.getGr2Relid()).getRefid();
				Bk001Sbk yymd31 = this.bW.findCardInfo(yymdList31);
				modelMap.put("bk001Sbk", yymd31);
				st003Cryxxb1 = this.us.getReportInfect(yymd31.getRelid(), (String) null);
				modelMap.put("infectList", st003Cryxxb1);
				map = this.us.findUnAuditCount(yymdList31);
				modelMap.put("canAudit",
						Boolean.valueOf(map > 0 && e.fD.getValue().equals(list31.getRoleCur().getRoleType())));
				int dep = this.us.getReportInfect(yymdList31, "1").size();
				modelMap.put("canDelete", Boolean.valueOf(dep > 0));
				Bk002Grzd bk002Grzd = this.us.getOneInfectByRefid(yymdList31);
				modelMap.put("bk002Grzd", bk002Grzd);
				int onum = this.E.findNumByInfectCode("SSI", bk002Grzd.getInfectDiagnId());
				modelMap.put("canSelOprat", Boolean.valueOf(onum > 0));
				Bk003Ygys bk003 = new Bk003Ygys();
				bk003.setRefid(bk002Grzd.getRelid());
				List bk003List = this.ut.findSusceptFactors(bk003);
				modelMap.put("bk003List", bk003List);
				return "monitor/reportCardsInfoEditN";
			}

			Bk001Sbk list3 = new Bk001Sbk();
			St003Cryxxb yymdList3 = this.bg.getSt003Cryxxb(isSb.getZyid());
			AcAccount yymd3 = (AcAccount) this.b(request);
			list3.setReportDrId(yymd3.getUsername());
			list3.setReportDrName(yymd3.getRealname());
			Zg002Byks st003Cryxxb = this.cZ.getByDeptId(yymdList3.getDeptCode());
			if (st003Cryxxb != null) {
				list3.setReportDeptName(st003Cryxxb.getDeptName());
			}

			list3.setReportDeptId(yymdList3.getDeptCode());
			if (infectTypeId != null) {
				isSb.setInfectTypeId(infectTypeId);
			}

			if (ab.isNotEmpty(confirmDt)) {
				isSb.setConfDate(f.k(confirmDt, "yyyy-MM-dd"));
			}

			if (isSb.getInfectDeptName() == null) {
				isSb.setInfectDeptId(st003Cryxxb.getDeptId());
				isSb.setInfectDeptName(st003Cryxxb.getDeptName());
			}

			modelMap.put("bk001Sbk", list3);
			modelMap.put("gr002YsgrMx", isSb);
			map = this.E.findNumByInfectCode("SSI", isSb.getInfectCode());
			modelMap.put("canSelOprat", Boolean.valueOf(map > 0));
			modelMap.put("st003Cryxxb", yymdList3);
		} else {
			Bk001Sbk isSb1 = new Bk001Sbk();
			list31 = (AcAccount) this.b(request);
			isSb1.setReportDrId(list31.getUsername());
			isSb1.setReportDrName(list31.getRealname());
			modelMap.put("bk001Sbk", isSb1);
			Gr002YsgrMx yymdList32 = new Gr002YsgrMx();
			Date yymd32 = f.getCurDate();
			if (ab.isNotEmpty(zyid)) {
				yymdList32.setZyid(zyid);
			} else {
				st003Cryxxb1 = this.df.findByPatientIdAndVisitId(patientId, visitId);
				if (st003Cryxxb1.size() > 0) {
					yymdList32.setZyid(((St003Cryxxb) st003Cryxxb1.get(0)).getZyid());
				}
			}

			yymdList32.setRegId(regId);
			yymdList32.setInfectCode(infectDiagnId);
			yymdList32.setInfectTypeId(infectTypeId);
			if (ab.isNotEmpty(infectDiagnName)) {
				yymdList32.setInfectName(URLDecoder.decode(infectDiagnName, "UTF-8"));
			}

			yymdList32.setStartAt(yymd32);
			yymdList32.setConfDate(yymd32);
			St003Cryxxb st003Cryxxb2 = this.bg.getSt003Cryxxb(yymdList32.getZyid());
			Zg002Byks map1 = this.cZ.getByDeptId(st003Cryxxb2.getDeptCode());
			if (map1 != null) {
				isSb1.setReportDeptName(map1.getDeptName());
			}

			yymdList32.setInfectDeptId(st003Cryxxb2.getDeptCode());
			Dep dep1 = this.e.F(list31.getUnitId(), st003Cryxxb2.getDeptCode());
			if (dep1 != null) {
				yymdList32.setInfectDeptName(dep1.getDeptName());
			}

			isSb1.setReportDeptId(st003Cryxxb2.getDeptCode());
			modelMap.put("gr002YsgrMx", yymdList32);
			modelMap.put("st003Cryxxb", st003Cryxxb2);
		}

		modelMap.put("testOrderNo", testOrderNo);
		if (ab.isNotEmpty(operName)) {
			modelMap.put("operName", URLDecoder.decode(operName, "utf-8"));
		}

		if (ab.isNotEmpty(incisionGrade)) {
			modelMap.put("incisionGrade", URLDecoder.decode(incisionGrade, "utf-8"));
		}

		modelMap.put("relid", z.a(bg.mU));
		String isSb2 = this.j.findByParamCode(Param.NIS_BK_GRZDSB);
		modelMap.put("isSb", isSb2);
		List list32 = this.p.u("medication_purpose", (String) null);
		LinkedList yymdList33 = new LinkedList();
		Iterator st003Cryxxb3 = list32.iterator();

		while (st003Cryxxb3.hasNext()) {
			SysDict yymd33 = (SysDict) st003Cryxxb3.next();
			HashMap map2 = new HashMap();
			map2.put("value", yymd33.getDictCode());
			map2.put("text", yymd33.getDictName() == null ? "" : yymd33.getDictName());
			yymdList33.add(map2);
		}

		String yymd34 = l.toString(yymdList33);
		modelMap.put("yymd", yymd34);
		modelMap.put("isYgys", this.j.findByParamCode(Param.NIS_BK_IS_YGYS_REQUIRED));
		modelMap.put("isIncision", this.j.findByParamCode(Param.NIS_BK_IS_INCISION_REQUIRED));
		return "monitor/reportCardsInfoAddN";
	}

	@RequestMapping({"/bk001Sbk/f_json/addReportCard"})
	@ResponseBody
	@SqlLog(p = "报卡--新增报卡")
	public void a(HttpServletRequest request, HttpServletResponse response, Bk002Grzd bk002Grzd, String chargeDrId,
			String jbzd, String jbzdCode, Bk001Sbk bk001Sbk, String isAuth, Gr016BkKjyw gr016BkKjyw,
			St004BkCgxx st004BkCgxx) {
		Result result = new Result();
		LoginUser user = this.d(request);

		try {
			String e;
			if (StringUtils.isNotBlank(bk002Grzd.getInfectDiagnId())) {
				e = this.j.findByParamCode(Param.NIS_BK_GRZDSB);
				if (e.equals("1")) {
					List num = this.bW.isReportBefore(bk002Grzd.getZyid(), bk002Grzd.getInfectDiagnId(),
							"" + bk002Grzd.getInfectType());
					if (num != null && num.size() > 0) {
						result.setResult("error");
						result.setMsg("此感染之前已报过！不允许再次上报！");
						this.a(response, result);
						return;
					}
				}
			}

			e = this.j.findByParamCode(Param.NIS_BK_IS_YGYS_REQUIRED);
			if ("1".equals(e) && "1".equals(bk002Grzd.getInfectType().toString())
					&& (bk002Grzd.getFactorIds() == null || bk002Grzd.getFactorIds().length == 0)) {
				result.setResult("error");
				result.setMsg("请选择易感因素！");
				this.a(response, result);
				return;
			}

			int arg25 = this.E.findNumByInfectCode("SSI", bk002Grzd.getInfectDiagnId());
			String isIncision = this.j.findByParamCode(Param.NIS_BK_IS_INCISION_REQUIRED);
			if ("1".equals(isIncision) && arg25 > 0 && StringUtils.isBlank(bk002Grzd.getMemo())) {
				result.setResult("error");
				result.setMsg("手术类感染切口必填！");
				this.a(response, result);
				return;
			}

			String nbisr = this.j.findByParamCode(Param.NIS_BK_IS_SSMC_REQUIRED);
			if ("1".equals(nbisr) && (arg25 > 0 || "POP".equals(bk002Grzd.getInfectDiagnId()))
					&& StringUtils.isBlank(bk002Grzd.getOpeName())) {
				result.setResult("error");
				result.setMsg("该感染诊断属于手术部位感染，未选择手术，无法上报！");
				this.a(response, result);
				return;
			}

			Gr002YsgrMx listRefid;
			if (StringUtils.isNotBlank(bk002Grzd.getRegId())) {
				listRefid = this.bX.getGr002YsgrMx(bk002Grzd.getRegId());
				if (listRefid != null) {
					this.bW.a(bk002Grzd, user, chargeDrId, jbzd, jbzdCode, listRefid, bk001Sbk);
					String yzIds = bk002Grzd.getRefid();
					result.setResult("success");
					result.setData(yzIds);
				} else {
					result = new Result("error", "参数异常");
				}
			} else {
				listRefid = new Gr002YsgrMx();
				listRefid.setZyid(bk002Grzd.getZyid());
				this.bW.a(bk002Grzd, user, chargeDrId, jbzd, jbzdCode, listRefid, bk001Sbk);
				result.setResult("success");
			}

			String arg26 = bk002Grzd.getRefid();
			String[] arg27 = gr016BkKjyw.getYzIds();
			this.uu.deleteByRefid(arg26);
			if (arg27 != null && arg27.length > 0) {
				for (int yzIdList = 0; yzIdList < arg27.length; ++yzIdList) {
					Gr016BkKjyw content = new Gr016BkKjyw();
					St004Yzxxb st004BkCgxxTemp = this.bu.get(arg27[yzIdList]);
					if (st004BkCgxxTemp != null) {
						List sdf = gr016BkKjyw.getGr16List();
						Iterator arg23 = sdf.iterator();

						while (arg23.hasNext()) {
							Gr016SsbwKjyw format = (Gr016SsbwKjyw) arg23.next();
							if (format.getYzId().equals(arg27[yzIdList])) {
								content.setPreYymd(format.getPreYymd());
							}
						}

						content.setYzId(arg27[yzIdList]);
						content.setRefid(arg26);
						content.setOrderType(st004BkCgxxTemp.getOrderTypeName());
						content.setOrderName(st004BkCgxxTemp.getOrderName());
						content.setOrderAt(st004BkCgxxTemp.getOrderAt());
						content.setStopAt(st004BkCgxxTemp.getStopAt());
						content.setDose(st004BkCgxxTemp.getDosage());
						content.setDosageUnit(st004BkCgxxTemp.getDosageUnit());
						content.setAdminRouteName(st004BkCgxxTemp.getAdminRouteName());
						content.setSypc(st004BkCgxxTemp.getSypc());
						content.setDateSection(st004BkCgxxTemp.getDateSection());
						content.setIsselect(1);
						this.uu.save(content);
					}
				}
			}

			String[] arg28 = st004BkCgxx.getYzIdList();
			this.uv.deleteByRefid(arg26);
			if (arg28 != null && arg28.length > 0) {
				for (int arg29 = 0; arg29 < arg28.length; ++arg29) {
					St004BkCgxx arg31 = new St004BkCgxx();
					arg31.setYzId(arg28[arg29]);
					SimpleDateFormat arg33 = new SimpleDateFormat("yyyy-MM-dd");
					String arg34 = arg33.format(new Date());
					arg31.setCreateDate(arg33.parse(arg34));
					arg31.setRefid(arg26);
					this.uv.save(arg31);
				}
			}

			result.setData(arg26);
			if ("1".equals(isAuth)) {
				ArrayList arg30 = new ArrayList();
				bk002Grzd.setAuthStatus(Integer.valueOf(1));
				bk002Grzd.setAuthAt(new Date());
				bk002Grzd.setAuthUserid(user.getUsername());
				bk002Grzd.setAuthUsername(user.getRealname());
				arg30.add(bk002Grzd);
				this.us.a(arg30, Integer.valueOf(1), bk001Sbk, user);
			}

			if (bk001Sbk.getReportDrId() != null && !bk001Sbk.getReportDrId().equals(bk002Grzd.getAuthUserid())) {
				String arg32 = bk001Sbk.getReportDrName() + " ( " + bk001Sbk.getReportDeptName() + " ) 上报了 "
						+ bk002Grzd.getInfectDiagnName() + " 报卡";
				this.cV.a(bk001Sbk.getZyid(), (String) null, bk001Sbk.getReportDrId(), bk001Sbk.getReportDrName(),
						arg32, (String[]) null, new String[]{this.j.findByParamCode(Param.NIS_GK_DEPTID)},
						al.jn.getValue(), bk001Sbk.getRelid());
			}
		} catch (Exception arg24) {
			c.error("获取信息异常!", arg24);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/bk001Sbk/f_view/index"})
	@SqlLog(p = "临床端患者--列表初始化")
	public String a(HttpServletRequest request, ModelMap modelMap) {
		String deptId = (String) this.b(request, "zg_dept");
		String iszd = this.j.findByParamCode(Param.NIS_MDRO_ZHONG);
		modelMap.put("iszd", iszd);
		String hospId = this.c(request);
		Dep dep = this.e.F(hospId, deptId);
		modelMap.put("cgpg", this.j.findByParamCode(Param.NIS_HXJ_CGPGSHOW));
		modelMap.put("startDate", f.formatDate(f.a(new Date(), -7)));
		modelMap.put("endDate", f.formatDate(new Date()));
		modelMap.put("startTime", f.formatDate(f.a(new Date(), -3)));
		modelMap.put("endTime", f.formatDate(new Date()));
		List allowBKs = this.p.u("cdc_card_type", (String) null);
		modelMap.put("AllBK", allowBKs);
		modelMap.put("EWLOGO", this.j.findByParamCode(Param.NIS_IS_SHOW_CDC_EWLOGO));
		modelMap.put("ISDB", this.j.findByParamCode(Param.NIS_CDC_INTERFACE_IS_RBA_ENABLED));
		return "2".equals(dep.getDeptTypeId()) ? "monitor/bk001MzList" : "monitor/bk001List";
	}

	@RequestMapping({"/bk001Sbk/f_view/findBkListByZyid"})
	public String H(HttpServletRequest request, ModelMap modelMap, String zyid) {
		return "monitor/bkListByZyid";
	}

	@RequestMapping({"/bk001Sbk/f_json/pageQueryByZyid"})
	@ResponseBody
	public void V(HttpServletRequest request, HttpServletResponse response, String zyid) {
		List bkList = this.bW.findByZyid(zyid);
		this.b(response, bkList);
	}

	@RequestMapping({"/bk001Sbk/f_view/main"})
	public String f(HttpServletRequest request, ModelMap modelMap) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String deptId = (String) this.b(request, "zg_dept");
		String hospId = this.c(request);
		Dep dep = this.e.F(hospId, deptId);
		List allowBKs = this.p.u("cdc_card_type", (String) null);
		modelMap.put("AllBK", allowBKs);
		if ("2".equals(dep.getDeptTypeId())) {
			modelMap.put("queryStartDate", sdf.format(Long.valueOf((new Date()).getTime() - 604800000L)));
			modelMap.put("queryEndDate", sdf.format(Long.valueOf((new Date()).getTime())));
			return "monitor/mainMZ";
		} else {
			return "monitor/main";
		}
	}

	@RequestMapping({"/bk001Sbk/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "临床端--住院患者列表")
	public void a(HttpServletRequest request, HttpServletResponse response, String startDate, String endDate,
			String searchString, String isInHosp, String patientType, String patientRange, String ygyj, String ygqr,
			String dnlx, String dbhz, String ydnlx) {
		AcAccount user = (AcAccount) this.b(request);
		String deptId = (String) this.b(request, "zg_dept");
		String showCrb = this.j.findByParamCode(Param.NIS_CRB_BK);
		String key = this.j.findByParamCode(Param.NIS_MDRO_ZHONG);
		List hzxx;
		if ("1".equals(isInHosp)) {
			hzxx = this.bg.a(deptId, searchString, showCrb, patientRange, user, ygyj, ygqr, dnlx, ydnlx, dbhz, key);
			this.b(response, hzxx);
		} else {
			hzxx = this.bg.a(deptId, searchString, startDate, endDate, showCrb, patientRange, user, ygyj, ygqr, dnlx,
					ydnlx, key);
			this.b(response, hzxx);
		}

	}

	@RequestMapping({"/bk001Sbk/f_json/pageQueryMz"})
	@ResponseBody
	@SqlLog(p = "临床端--门诊患者列表")
	public void a(HttpServletRequest request, HttpServletResponse response, String chargeFlag, String startDate,
			String endDate, St020ClinicPatients st020ClinicPatients, String patientType) {
		AcAccount acAccount = (AcAccount) this.b(request);
		if ("1".equals(chargeFlag)) {
			st020ClinicPatients.setDoctorId(acAccount.getDocNo());
		}

		String showCrb;
		if (ab.isEmpty(patientType)) {
			showCrb = (String) this.b(request, "zg_dept");
			st020ClinicPatients.setDeptId(showCrb);
		}

		showCrb = this.j.findByParamCode(Param.NIS_CRB_BK);
		st020ClinicPatients.setShowCrb(showCrb);
		MyPage mzHzxx = this.bh.d(st020ClinicPatients);
		this.b(response, mzHzxx);
	}

	@RequestMapping({"/bk001Sbk/f_view/getHzxx"})
	public String a(HttpServletRequest request, ModelMap modelMap, St020ClinicPatients st020ClinicPatients) {
		String unitId = this.c(request);
		String deptId = (String) this.b(request, "zg_dept");

		try {
			if (!ab.isEmpty(deptId)) {
				Dep e = this.e.F(unitId, deptId);
				List allowBKs = this.p.u("cdc_card_type", (String) null);
				String showCrb = this.j.findByParamCode(Param.NIS_CRB_BK);
				modelMap.put("AllBK", allowBKs);
				if (e != null && "2".equals(e.getDeptTypeId())) {
					st020ClinicPatients.setDeptId(deptId);
					st020ClinicPatients.setShowCrb(showCrb);
					MyPage searchString1 = this.bh.d(st020ClinicPatients);
					if (searchString1.getRows().isEmpty()) {
						modelMap.put("tipMsg", "没有相关病人信息！");
					} else {
						modelMap.put("MZHZXX", searchString1);
					}

					return "monitor/allMzHzxx";
				}

				String searchString = request.getParameter("searchString");
				String orderBy = request.getParameter("orderBy");
				return "monitor/allHzxx";
			}

			modelMap.put("errorMsg", "未获取到科室编号，查询失败！");
		} catch (Exception arg10) {
			arg10.printStackTrace();
			modelMap.put("errorMsg", "查询出现了问题，请在后台查看出错信息！");
		}

		return "monitor/allHzxx";
	}

	@RequestMapping({"/bk001Sbk/f_view/reportCardRecord"})
	public String a(HttpServletRequest request, ModelMap modelMap, String id, String patientId, Integer visitId,
			String ptype, String ctype, String employeeId, String icds) {
		try {
			List e;
			if ("mz".equals(ptype)) {
				if (ab.aM(id)) {
					if (ab.aM(patientId)) {
						modelMap.put("errMsg", "病人标识(ID)不符合调用规则！");
						return "forward:/cdc/tips.shtml?type=interface";
					}

					if (visitId == null) {
						visitId = Integer.valueOf(1);
					}

					e = this.bh.findByPatientIdAndVisitId(patientId, visitId);
					if (e.size() > 0) {
						id = ((St020ClinicPatients) e.get(0)).getMzid();
					}
				}

				St020ClinicPatients e2 = this.bh.get(id);
				String allowBKs1;
				if (e2 == null) {
					System.err.println("接口调用未找到ID与传入ID一致的病人，开始在HIS中查询...");

					try {
						allowBKs1 = f.formatDate(f.a(new Date(), -3));
						String BRXX1 = f.formatDate(new Date());
						Result clinicPatients = this.dj.c(id, allowBKs1, BRXX1);
						System.err.println("存储返回的错误信息：" + clinicPatients.getMsg());
						if ("error".equals(clinicPatients.getResult())) {
							modelMap.put("msg", "获取病人数据失败");
							return "forward:/cdc/tips.shtml?type=interface";
						}

						if (!ab.aM(clinicPatients.getMsg())) {
							modelMap.put("msg", clinicPatients.getMsg());
							return "forward:/cdc/tips.shtml?type=interface";
						}
					} catch (Exception arg13) {
						arg13.printStackTrace();
						modelMap.put("msg", "接口调用出错！");
						return "forward:/cdc/tips.shtml?type=interface";
					}
				}

				allowBKs1 = this.j.findByParamCode(Param.NIS_CDC_INTERFACE_IS_RBA_ENABLED);
				if ("crb".equals(ctype)) {
					if ("1".equals(allowBKs1)) {
						modelMap.put("url", "/cdc/f_view/reportCardMZ.shtml?mzid=" + id
								+ (ab.isNotEmpty(icds) ? "&icds=" + icds : ""));
						modelMap.put("mzzyid", id);
						modelMap.put("patientType", "mz");
						return "cdc/reportTransferPageForInterface";
					} else {
						return "redirect:/cdc/f_view/reportCardMZ.shtml?mzid=" + id
								+ (ab.isNotEmpty(icds) ? "&icds=" + icds : "");
					}
				} else if ("sy".equals(ctype)) {
					return "redirect:/cdc/f_view/deathReport.shtml?mzid=" + id;
				} else if ("syjc".equals(ctype)) {
					return "redirect:/cdc/f_view/fsmReport.shtml?mzid=" + id;
				} else if ("syyc".equals(ctype)) {
					return "redirect:/cdc/f_view/fsaReport.shtml?mzid=" + id;
				} else if ("tumour".equals(ctype)) {
					return "redirect:/cdc/f_view/tumourReport.shtml?mzid=" + id;
				} else if ("xnxgbk".equals(ctype)) {
					return "redirect:/cdc/f_view/hcvReport.shtml?mzid=" + id;
				} else if ("gwzsbk".equals(ctype)) {
					return "redirect:/cdc/f_view/sunstrokeReport.shtml?mzid=" + id;
				} else if ("choose".equals(ctype)) {
					List BRXX2 = this.p.u("cdc_card_type", (String) null);
					modelMap.put("AllBK", BRXX2);
					St020ClinicPatients clinicPatients1 = this.bh.get(id);
					modelMap.put("MZBRXX", clinicPatients1);
					return "monitor/reportCardRecord";
				} else {
					modelMap.put("errMsg", "报卡类型（ctype）参数有误！");
					return "forward:/cdc/tips.shtml?type=interface";
				}
			} else if ("zy".equals(ptype)) {
				if (ab.aM(id)) {
					if (ab.aM(patientId)) {
						modelMap.put("errMsg", "病人标识（id/patientId/visitId）不符合调用规则！");
						return "forward:/cdc/tips.shtml?type=interface";
					}

					if (visitId == null) {
						visitId = Integer.valueOf(1);
					}

					e = this.df.findByPatientIdAndVisitId(patientId, visitId);
					if (e.size() > 0) {
						id = ((St003Cryxxb) e.get(0)).getZyid();
					}
				}

				if (ab.isEmpty(id)) {
					modelMap.put("errMsg", "未获取到病人信息！");
					return "forward:/cdc/tips.shtml?type=interface";
				} else {
					String e1 = this.j.findByParamCode(Param.NIS_CDC_INTERFACE_IS_RBA_ENABLED);
					if ("crb".equals(ctype)) {
						if ("1".equals(e1)) {
							modelMap.put("url", "/cdc/f_view/reportCardZY.shtml?zyid=" + id
									+ (ab.isNotEmpty(icds) ? "&icds=" + icds : ""));
							modelMap.put("mzzyid", id);
							modelMap.put("patientType", "zy");
							return "cdc/reportTransferPageForInterface";
						} else {
							return "redirect:/cdc/f_view/reportCardZY.shtml?zyid=" + id + "&icds=" + icds;
						}
					} else if ("sy".equals(ctype)) {
						return "redirect:/cdc/f_view/deathReport.shtml?zyid=" + id;
					} else if ("syjc".equals(ctype)) {
						return "redirect:/cdc/f_view/fsmReport.shtml?zyid=" + id;
					} else if ("syyc".equals(ctype)) {
						return "redirect:/cdc/f_view/fsaReport.shtml?zyid=" + id;
					} else if ("tumour".equals(ctype)) {
						return "redirect:/cdc/f_view/tumourReport.shtml?zyid=" + id;
					} else if ("xnxgbk".equals(ctype)) {
						return "redirect:/cdc/f_view/hcvReport.shtml?zyid=" + id;
					} else if ("gwzsbk".equals(ctype)) {
						return "redirect:/cdc/f_view/sunstrokeReport.shtml?zyid=" + id;
					} else if ("choose".equals(ctype)) {
						List allowBKs = this.p.u("cdc_card_type", (String) null);
						modelMap.put("AllBK", allowBKs);
						St003Cryxxb BRXX = this.df.get(id);
						modelMap.put("ZYBRXX", BRXX);
						return "monitor/reportCardRecord";
					} else {
						modelMap.put("errMsg", "报卡类型（ctype）参数有误！");
						return "forward:/cdc/tips.shtml?type=interface";
					}
				}
			} else {
				modelMap.put("errMsg", "患者类型（ptype）参数获取失败！");
				return "forward:/cdc/tips.shtml?type=interface";
			}
		} catch (Exception arg14) {
			arg14.printStackTrace();
			modelMap.put("errMsg", "抱歉，发生错误！");
			return "forward:/cdc/tips.shtml?type=interface";
		}
	}

	@RequestMapping({"/bk001Sbk/f_json/isReportBefore"})
	@ResponseBody
	public void p(HttpServletRequest request, HttpServletResponse response, String zyid, String idi, String ift) {
		if (ab.isNotEmpty(idi) && ab.isNotEmpty(zyid) && ab.isNotEmpty(ift)) {
			List list = this.bW.isReportBefore(zyid, idi, ift);
			if (list != null && list.size() > 0) {
				this.b(response,
						"{\'count\':" + list.size() + ",\'bk2relid\':\'" + ((Bk001Sbk) list.get(0)).getRelid() + "\'}");
			} else {
				this.b(response, "{\'count\':0}");
			}
		} else {
			this.b(response, "{\'count\':-1}");
		}

	}

	@RequestMapping({"/bk001Sbk/f_view/List"})
	public String u(HttpServletRequest request, ModelMap modelMap, String employeeId, String deptId) {
		modelMap.put("employeeId", employeeId);
		modelMap.put("deptId", deptId);
		modelMap.put("cgpg", this.j.findByParamCode(Param.NIS_HXJ_CGPGSHOW));
		return "monitor/ewListForInterface";
	}

	@RequestMapping({"/bk001Sbk/f_view/ewList"})
	@ResponseBody
	public void J(HttpServletRequest request, HttpServletResponse response, String employeeId, String deptId) {
		St003Cryxxb st003Cryxxb = new St003Cryxxb();
		if (ab.isEmpty(deptId)) {
			LoginUser showCrb = this.d(request);
			if (ab.isNotEmpty(showCrb.getScopeInfo())) {
				String[] ewList = showCrb.getScopeInfo().split(",");
				st003Cryxxb.setDeptIdIn(Arrays.asList(ewList));
			} else {
				st003Cryxxb.setDeptIdIn(Arrays.asList(new String[]{"0"}));
			}
		} else {
			String[] showCrb1 = deptId.split(",");
			st003Cryxxb.setDeptIdIn(Arrays.asList(showCrb1));
		}

		st003Cryxxb.setInfectTypeId(Integer.valueOf(1));
		st003Cryxxb.setIsInHosp(Integer.valueOf(1));
		String showCrb2 = this.j.findByParamCode(Param.NIS_CRB_BK);
		st003Cryxxb.setShowCrb(showCrb2);
		List ewList1 = this.bg.ewListForInterface(st003Cryxxb);
		this.a(response, ewList1);
	}

	@RequestMapping({"/bk001Sbk/f_json/getReportInfectListByZyid"})
	@ResponseBody
	@SqlLog(p = "报卡--患者历史报卡明细")
	public void W(HttpServletRequest request, HttpServletResponse response, String zyid) {
		Result result = new Result();
		List bk001SbkList = null;

		try {
			if (ab.isNotEmpty(zyid)) {
				bk001SbkList = this.bW.getReportHistoryDetailsByZyid(zyid);
				Bk003Ygys e = new Bk003Ygys();
				Bk004Sjbb bk004Sjbb = new Bk004Sjbb();
				String ygysArr = "";
				String zbjArr = "";

				for (Iterator arg10 = bk001SbkList.iterator(); arg10.hasNext(); zbjArr = "") {
					Bk001Sbk bk001Sbk = (Bk001Sbk) arg10.next();
					if (bk001Sbk.getOpeName() == null) {
						bk001Sbk.setOpeName("");
					}

					if (bk001Sbk.getInfectTypeName() == null) {
						bk001Sbk.setInfectTypeName("");
					}

					if (bk001Sbk.getInfectDeptName() == null) {
						bk001Sbk.setInfectDeptName("");
					}

					if (bk001Sbk.getInfectName() == null) {
						bk001Sbk.setInfectName("");
					}

					if (bk001Sbk.getReportDrName() == null) {
						bk001Sbk.setReportDrName("");
					}

					if (bk001Sbk.getReportDeptName() == null) {
						bk001Sbk.setReportDeptName("");
					}

					e.setRefid(bk001Sbk.getBk2Relid());
					List bk003YgysList = this.ut.findBk003YgysList(e);
					bk001Sbk.setYgysCount(bk003YgysList.size());

					Bk003Ygys bk004SjbbList;
					for (Iterator bk004 = bk003YgysList.iterator(); bk004.hasNext(); ygysArr = ygysArr
							+ (bk004SjbbList.getFactorName() != null ? bk004SjbbList.getFactorName() + " " : "")) {
						bk004SjbbList = (Bk003Ygys) bk004.next();
					}

					bk001Sbk.setYgysArr(ygysArr);
					ygysArr = "";
					bk004Sjbb.setRefid(bk001Sbk.getBk2Relid());
					List bk004SjbbList1 = this.co.findPathogenDetection(bk004Sjbb);
					bk001Sbk.setZbjCount(bk004SjbbList1.size());

					Bk004Sjbb bk0041;
					for (Iterator arg14 = bk004SjbbList1.iterator(); arg14.hasNext(); zbjArr = zbjArr
							+ (bk0041.getPathoName() != null ? bk0041.getPathoName() + " " : "")) {
						bk0041 = (Bk004Sjbb) arg14.next();
					}

					bk001Sbk.setZbjArr(zbjArr);
				}

				result.setResult("success");
			} else {
				new Result("error", "参数异常");
			}
		} catch (Exception arg15) {
			c.error("获取信息异常!", arg15);
			new Result("error", "获取信息异常");
		}

		this.a(response, bk001SbkList);
	}
}