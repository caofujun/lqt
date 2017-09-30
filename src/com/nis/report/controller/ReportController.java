package com.nis.report.controller;

import com.nis.access.entity.AcAccount;
import com.nis.access.entity.AcAccountConfig;
import com.nis.access.service.AcAccountConfigService;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.TreeEntity;
import com.nis.comm.enums.Param;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.s;
import com.nis.mdr.entity.Xn002Byt;
import com.nis.mdr.service.Xn002BytService;
import com.nis.organization.service.UnitService;
import com.nis.param.service.SysParamService;
import com.nis.zg.service.Zg002ByksService;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReportController extends BaseController {
	@Autowired
	private SysParamService j;
	@Autowired
	private AcAccountConfigService b;
	@Autowired
	private Zg002ByksService cZ;
	@Autowired
	private Xn002BytService tQ;
	@Autowired
	private UnitService se;

	@RequestMapping({"/report/f_view/fineReportFrame"})
	public String ad(HttpServletRequest request, ModelMap modelMap, String reportFile) {
		Map formData = s.k(request);
		formData.remove("reportFile");
		modelMap.put("formData", formData);
		modelMap.put("reportFile", reportFile);
		modelMap.put("reportUrl", this.j.findByParamCode(Param.NIS_REPORT_URL));
		modelMap.put("nisUrl", this.j.findByParamCode(Param.NIS_HTTP_URL));
		modelMap.put("unitId", this.c(request));
		modelMap.put("unitFlag", this.se.getAll().size() > 1 ? "1" : "0");
		AcAccount acAccount = (AcAccount) this.b(request);
		modelMap.put("account", acAccount);
		modelMap.put("roleType", acAccount.getRoleCur().getRoleType());
		modelMap.put("curDeptId", (String) this.b(request, "zg_dept"));
		modelMap.put("deptFlag", this.j.findByParamCode(Param.NIS_CLINIC_REPORT_DEPT_SELECT));
		AcAccountConfig acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportStartDate");
		modelMap.put("startDate", acAccountConfig == null ? "" : acAccountConfig.getConfigValue());
		acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportEndDate");
		modelMap.put("endDate", acAccountConfig == null ? "" : acAccountConfig.getConfigValue());
		return "report/fineReportFrame";
	}

	@RequestMapping({"/report/f_view/fineReportAdvancedFrame"})
	public String d(HttpServletRequest request, ModelMap modelMap, String reportFile, String __bypagesize__,
			String statType, String deptType) {
		modelMap.put("reportFile", reportFile);
		modelMap.put("__bypagesize__", __bypagesize__);
		modelMap.put("statType", statType);
		modelMap.put("deptType", deptType);
		modelMap.put("reportUrl", this.j.findByParamCode(Param.NIS_REPORT_URL));
		modelMap.put("nisUrl", this.j.findByParamCode(Param.NIS_HTTP_URL));
		modelMap.put("unitId", this.c(request));
		modelMap.put("unitFlag", this.se.getAll().size() > 1 ? "1" : "0");
		AcAccount acAccount = (AcAccount) this.b(request);
		modelMap.put("account", acAccount);
		modelMap.put("roleType", acAccount.getRoleCur().getRoleType());
		modelMap.put("curDeptId", (String) this.b(request, "zg_dept"));
		modelMap.put("deptFlag", this.j.findByParamCode(Param.NIS_CLINIC_REPORT_DEPT_SELECT));
		AcAccountConfig acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportStartDate");
		modelMap.put("startDate", acAccountConfig == null ? "" : acAccountConfig.getConfigValue());
		acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportEndDate");
		modelMap.put("endDate", acAccountConfig == null ? "" : acAccountConfig.getConfigValue());
		return "report/fineReportAdvancedFrame";
	}

	@RequestMapping({"/report/f_view/fineReportTargetFrame"})
	public String af(HttpServletRequest request, ModelMap modelMap) {
		return "report/fineReportTargetFrame";
	}

	@RequestMapping({"/report/f_view/fineReportCustomFrame"})
	public String l(HttpServletRequest request, ModelMap modelMap, String reportFile, String __bypagesize__,
			String searchTerms) {
		modelMap.put("reportFile", reportFile);
		modelMap.put("__bypagesize__", __bypagesize__);
		modelMap.put("searchTerms", searchTerms);
		modelMap.put("reportUrl", this.j.findByParamCode(Param.NIS_REPORT_URL));
		modelMap.put("nisUrl", this.j.findByParamCode(Param.NIS_HTTP_URL));
		modelMap.put("unitId", this.c(request));
		modelMap.put("unitFlag", this.se.getAll().size() > 1 ? "1" : "0");
		AcAccount acAccount = (AcAccount) this.b(request);
		modelMap.put("account", acAccount);
		modelMap.put("roleType", acAccount.getRoleCur().getRoleType());
		modelMap.put("curDeptId", (String) this.b(request, "zg_dept"));
		modelMap.put("deptFlag", this.j.findByParamCode(Param.NIS_CLINIC_REPORT_DEPT_SELECT));
		AcAccountConfig acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportStartDate");
		modelMap.put("startDate", acAccountConfig == null ? "" : acAccountConfig.getConfigValue());
		acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportEndDate");
		modelMap.put("endDate", acAccountConfig == null ? "" : acAccountConfig.getConfigValue());
		return "report/fineReportCustomFrame";
	}

	@RequestMapping({"/report/f_view/fineReportGrFrame"})
	public String b(HttpServletRequest request, ModelMap modelMap, String reportFile, String __bypagesize__,
			String statType, String id, String code, String startDate, String endDate) {
		modelMap.put("reportFile", reportFile);
		modelMap.put("__bypagesize__", __bypagesize__);
		modelMap.put("statType", statType);
		modelMap.put("id", id);
		modelMap.put("code", code);
		modelMap.put("reportUrl", this.j.findByParamCode(Param.NIS_REPORT_URL));
		modelMap.put("nisUrl", this.j.findByParamCode(Param.NIS_HTTP_URL));
		modelMap.put("unitId", this.c(request));
		modelMap.put("unitFlag", this.se.getAll().size() > 1 ? "1" : "0");
		AcAccount acAccount = (AcAccount) this.b(request);
		modelMap.put("account", acAccount);
		modelMap.put("roleType", acAccount.getRoleCur().getRoleType());
		modelMap.put("curDeptId", (String) this.b(request, "zg_dept"));
		modelMap.put("deptFlag", this.j.findByParamCode(Param.NIS_CLINIC_REPORT_DEPT_SELECT));
		modelMap.put("years", f.getYears());
		AcAccountConfig acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportStartDate");
		if (ab.isEmpty(startDate)) {
			startDate = acAccountConfig == null ? "" : acAccountConfig.getConfigValue();
		}

		modelMap.put("startDate", startDate);
		if (ab.isEmpty(endDate)) {
			acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportEndDate");
			endDate = acAccountConfig == null ? "" : acAccountConfig.getConfigValue();
		}

		modelMap.put("endDate", endDate);
		return "report/fineReportGrFrame";
	}

	@RequestMapping({"/report/f_view/fineReportGr"})
	public String ae(HttpServletRequest request, ModelMap modelMap, String code) {
		modelMap.put("code", code);
		return "report/fineReportGr";
	}

	@RequestMapping({"/report/f_view/fineReportZyblrykstjFrame"})
	public String l(HttpServletRequest request, ModelMap modelMap, String reportFile, String __bypagesize__, String id,
			String startDate, String endDate) {
		modelMap.put("reportFile", reportFile);
		modelMap.put("__bypagesize__", __bypagesize__);
		modelMap.put("id", id);
		modelMap.put("reportUrl", this.j.findByParamCode(Param.NIS_REPORT_URL));
		modelMap.put("nisUrl", this.j.findByParamCode(Param.NIS_HTTP_URL));
		modelMap.put("unitId", this.c(request));
		modelMap.put("unitFlag", this.se.getAll().size() > 1 ? "1" : "0");
		AcAccount acAccount = (AcAccount) this.b(request);
		modelMap.put("account", acAccount);
		modelMap.put("roleType", acAccount.getRoleCur().getRoleType());
		modelMap.put("curDeptId", (String) this.b(request, "zg_dept"));
		modelMap.put("deptFlag", this.j.findByParamCode(Param.NIS_CLINIC_REPORT_DEPT_SELECT));
		AcAccountConfig acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportStartDate");
		if (ab.isEmpty(startDate)) {
			startDate = acAccountConfig == null ? "" : acAccountConfig.getConfigValue();
		}

		modelMap.put("startDate", startDate);
		if (ab.isEmpty(endDate)) {
			acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportEndDate");
			endDate = acAccountConfig == null ? "" : acAccountConfig.getConfigValue();
		}

		modelMap.put("endDate", endDate);
		return "report/fineReportZyblrykstjFrame";
	}

	@RequestMapping({"/report/f_view/fineReportZyblrytj"})
	public String ag(HttpServletRequest request, ModelMap modelMap) {
		return "report/fineReportZyblrytj";
	}

	@RequestMapping({"/report/f_view/fineReportZyblrytjFrame"})
	public String m(HttpServletRequest request, ModelMap modelMap, String reportFile, String __bypagesize__, String id,
			String startDate, String endDate) {
		modelMap.put("reportFile", reportFile);
		modelMap.put("__bypagesize__", __bypagesize__);
		modelMap.put("id", id);
		modelMap.put("reportUrl", this.j.findByParamCode(Param.NIS_REPORT_URL));
		modelMap.put("nisUrl", this.j.findByParamCode(Param.NIS_HTTP_URL));
		modelMap.put("unitId", this.c(request));
		modelMap.put("unitFlag", this.se.getAll().size() > 1 ? "1" : "0");
		AcAccount acAccount = (AcAccount) this.b(request);
		modelMap.put("account", acAccount);
		modelMap.put("roleType", acAccount.getRoleCur().getRoleType());
		modelMap.put("curDeptId", (String) this.b(request, "zg_dept"));
		modelMap.put("deptFlag", this.j.findByParamCode(Param.NIS_CLINIC_REPORT_DEPT_SELECT));
		AcAccountConfig acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportStartDate");
		if (ab.isEmpty(startDate)) {
			startDate = acAccountConfig == null ? "" : acAccountConfig.getConfigValue();
		}

		modelMap.put("startDate", startDate);
		if (ab.isEmpty(endDate)) {
			acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportEndDate");
			endDate = acAccountConfig == null ? "" : acAccountConfig.getConfigValue();
		}

		modelMap.put("endDate", endDate);
		return "report/fineReportZyblrytjFrame";
	}

	@RequestMapping({"/report/f_view/fineReportZyblrykstj"})
	public String ah(HttpServletRequest request, ModelMap modelMap) {
		return "report/fineReportZyblrykstj";
	}

	@RequestMapping({"/report/f_view/fineReportXj"})
	public String af(HttpServletRequest request, ModelMap modelMap, String code) {
		modelMap.put("code", code);
		return "report/fineReportXj";
	}

	@RequestMapping({"/report/f_view/fineReportZb"})
	public String ai(HttpServletRequest request, ModelMap modelMap) {
		return "report/fineReportZb";
	}

	@RequestMapping({"/report/f_view/fineReportYw"})
	public String aj(HttpServletRequest request, ModelMap modelMap) {
		return "report/fineReportYw";
	}

	@RequestMapping({"/report/f_view/fineReportSw"})
	public String ak(HttpServletRequest request, ModelMap modelMap) {
		return "report/fineReportSw";
	}

	@RequestMapping({"/report/f_view/fineReportSws"})
	public String al(HttpServletRequest request, ModelMap modelMap) {
		return "report/fineReportSws";
	}

	@RequestMapping({"/report/f_view/fineReportSs"})
	public String am(HttpServletRequest request, ModelMap modelMap) {
		return "report/fineReportSs";
	}

	@RequestMapping({"/report/f_view/fineReportHdm"})
	public String an(HttpServletRequest request, ModelMap modelMap) {
		return "report/fineReportHdm";
	}

	@RequestMapping({"/report/f_view/fineReportSsyy"})
	public String ao(HttpServletRequest request, ModelMap modelMap) {
		return "report/fineReportSsyy";
	}

	@RequestMapping({"/report/f_view/fineReportHw"})
	public String ap(HttpServletRequest request, ModelMap modelMap) {
		return "report/fineReportHw";
	}

	@RequestMapping({"/report/f_view/fineReportHwFrame"})
	public String d(HttpServletRequest request, ModelMap modelMap, String reportFile, String __bypagesize__,
			String statType, String id, String startDate, String endDate) {
		modelMap.put("reportFile", reportFile);
		modelMap.put("__bypagesize__", __bypagesize__);
		modelMap.put("statType", statType);
		modelMap.put("id", id);
		modelMap.put("reportUrl", this.j.findByParamCode(Param.NIS_REPORT_URL));
		modelMap.put("nisUrl", this.j.findByParamCode(Param.NIS_HTTP_URL));
		modelMap.put("unitId", this.c(request));
		modelMap.put("unitFlag", this.se.getAll().size() > 1 ? "1" : "0");
		AcAccount acAccount = (AcAccount) this.b(request);
		modelMap.put("account", acAccount);
		modelMap.put("roleType", acAccount.getRoleCur().getRoleType());
		modelMap.put("curDeptId", (String) this.b(request, "zg_dept"));
		modelMap.put("deptFlag", this.j.findByParamCode(Param.NIS_CLINIC_REPORT_DEPT_SELECT));
		AcAccountConfig acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportStartDate");
		if (ab.isEmpty(startDate)) {
			startDate = acAccountConfig == null ? "" : acAccountConfig.getConfigValue();
		}

		modelMap.put("startDate", startDate);
		if (ab.isEmpty(endDate)) {
			acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportEndDate");
			endDate = acAccountConfig == null ? "" : acAccountConfig.getConfigValue();
		}

		modelMap.put("endDate", endDate);
		return "report/fineReportHwFrame";
	}

	@RequestMapping({"/report/f_view/fineReportXjFrame"})
	public String c(HttpServletRequest request, ModelMap modelMap, String reportFile, String __bypagesize__,
			String statType, String id, String startDate, String endDate, String code) {
		modelMap.put("reportFile", reportFile);
		modelMap.put("__bypagesize__", __bypagesize__);
		modelMap.put("statType", statType);
		modelMap.put("id", id);
		modelMap.put("reportUrl", this.j.findByParamCode(Param.NIS_REPORT_URL));
		modelMap.put("nisUrl", this.j.findByParamCode(Param.NIS_HTTP_URL));
		modelMap.put("unitId", this.c(request));
		modelMap.put("unitFlag", this.se.getAll().size() > 1 ? "1" : "0");
		AcAccount acAccount = (AcAccount) this.b(request);
		modelMap.put("account", acAccount);
		modelMap.put("roleType", acAccount.getRoleCur().getRoleType());
		modelMap.put("curDeptId", (String) this.b(request, "zg_dept"));
		modelMap.put("deptFlag", this.j.findByParamCode(Param.NIS_CLINIC_REPORT_DEPT_SELECT));
		modelMap.put("code", code);
		AcAccountConfig acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportStartDate");
		if (ab.isEmpty(startDate)) {
			startDate = acAccountConfig == null ? "" : acAccountConfig.getConfigValue();
		}

		modelMap.put("startDate", startDate);
		if (ab.isEmpty(endDate)) {
			acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportEndDate");
			endDate = acAccountConfig == null ? "" : acAccountConfig.getConfigValue();
		}

		Xn002Byt xn002Byt = new Xn002Byt();
		xn002Byt.setPage(Integer.valueOf(1));
		xn002Byt.setSize(Integer.valueOf(200));
		xn002Byt.setStartDate(startDate);
		xn002Byt.setEndDate(endDate);
		MyPage drugPage = this.tQ.b(xn002Byt);
		List rows = drugPage.getRows();
		if (rows.size() > 0) {
			String pathogenId = ((Xn002Byt) rows.get(0)).getPathogenId();
			modelMap.put("pathogenId", pathogenId);
		}

		modelMap.put("endDate", endDate);
		return "report/fineReportXjFrame";
	}

	@RequestMapping({"/report/f_view/fineReportKsFrame"})
	public String e(HttpServletRequest request, ModelMap modelMap, String reportFile, String __bypagesize__,
			String statType, String id, String startDate, String endDate) {
		modelMap.put("reportFile", reportFile);
		modelMap.put("__bypagesize__", __bypagesize__);
		modelMap.put("statType", statType);
		modelMap.put("id", id);
		modelMap.put("reportUrl", this.j.findByParamCode(Param.NIS_REPORT_URL));
		modelMap.put("nisUrl", this.j.findByParamCode(Param.NIS_HTTP_URL));
		modelMap.put("unitId", this.c(request));
		modelMap.put("unitFlag", this.se.getAll().size() > 1 ? "1" : "0");
		AcAccount acAccount = (AcAccount) this.b(request);
		modelMap.put("account", acAccount);
		modelMap.put("roleType", acAccount.getRoleCur().getRoleType());
		modelMap.put("curDeptId", (String) this.b(request, "zg_dept"));
		modelMap.put("deptFlag", this.j.findByParamCode(Param.NIS_CLINIC_REPORT_DEPT_SELECT));
		AcAccountConfig acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportStartDate");
		if (ab.isEmpty(startDate)) {
			startDate = acAccountConfig == null ? "" : acAccountConfig.getConfigValue();
		}

		modelMap.put("startDate", startDate);
		if (ab.isEmpty(endDate)) {
			acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportEndDate");
			endDate = acAccountConfig == null ? "" : acAccountConfig.getConfigValue();
		}

		modelMap.put("endDate", endDate);
		return "report/fineReportKsFrame";
	}

	@RequestMapping({"/report/f_view/fineReportSwFrame"})
	public String e(HttpServletRequest request, ModelMap modelMap, String reportFile, String __bypagesize__,
			String statType, String id) {
		modelMap.put("reportFile", reportFile);
		modelMap.put("__bypagesize__", __bypagesize__);
		modelMap.put("statType", statType);
		modelMap.put("id", id);
		modelMap.put("reportUrl", this.j.findByParamCode(Param.NIS_REPORT_URL));
		modelMap.put("nisUrl", this.j.findByParamCode(Param.NIS_HTTP_URL));
		modelMap.put("unitId", this.c(request));
		modelMap.put("unitFlag", this.se.getAll().size() > 1 ? "1" : "0");
		AcAccount acAccount = (AcAccount) this.b(request);
		modelMap.put("account", acAccount);
		modelMap.put("roleType", acAccount.getRoleCur().getRoleType());
		modelMap.put("curDeptId", (String) this.b(request, "zg_dept"));
		modelMap.put("deptFlag", this.j.findByParamCode(Param.NIS_CLINIC_REPORT_DEPT_SELECT));
		AcAccountConfig acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportStartDate");
		modelMap.put("startDate", acAccountConfig == null ? "" : acAccountConfig.getConfigValue());
		acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportEndDate");
		modelMap.put("endDate", acAccountConfig == null ? "" : acAccountConfig.getConfigValue());
		return "report/fineReportSwFrame";
	}

	@RequestMapping({"/report/f_view/fineReportSwsFrame"})
	public String f(HttpServletRequest request, ModelMap modelMap, String reportFile, String __bypagesize__,
			String statType, String id) {
		modelMap.put("reportFile", reportFile);
		modelMap.put("__bypagesize__", __bypagesize__);
		modelMap.put("statType", statType);
		modelMap.put("id", id);
		modelMap.put("reportUrl", this.j.findByParamCode(Param.NIS_REPORT_URL));
		modelMap.put("nisUrl", this.j.findByParamCode(Param.NIS_HTTP_URL));
		modelMap.put("unitId", this.c(request));
		modelMap.put("unitFlag", this.se.getAll().size() > 1 ? "1" : "0");
		AcAccount acAccount = (AcAccount) this.b(request);
		modelMap.put("account", acAccount);
		modelMap.put("roleType", acAccount.getRoleCur().getRoleType());
		modelMap.put("curDeptId", (String) this.b(request, "zg_dept"));
		modelMap.put("deptFlag", this.j.findByParamCode(Param.NIS_CLINIC_REPORT_DEPT_SELECT));
		AcAccountConfig acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportStartDate");
		modelMap.put("startDate", acAccountConfig == null ? "" : acAccountConfig.getConfigValue());
		acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportEndDate");
		modelMap.put("endDate", acAccountConfig == null ? "" : acAccountConfig.getConfigValue());
		return "report/fineReportSwsFrame";
	}

	@RequestMapping({"/report/f_view/fineReportYwFrame"})
	public String g(HttpServletRequest request, ModelMap modelMap, String reportFile, String __bypagesize__,
			String statType, String id) {
		modelMap.put("reportFile", reportFile);
		modelMap.put("__bypagesize__", __bypagesize__);
		modelMap.put("statType", statType);
		modelMap.put("id", id);
		modelMap.put("reportUrl", this.j.findByParamCode(Param.NIS_REPORT_URL));
		modelMap.put("nisUrl", this.j.findByParamCode(Param.NIS_HTTP_URL));
		modelMap.put("unitId", this.c(request));
		modelMap.put("unitFlag", this.se.getAll().size() > 1 ? "1" : "0");
		AcAccount acAccount = (AcAccount) this.b(request);
		modelMap.put("account", acAccount);
		modelMap.put("roleType", acAccount.getRoleCur().getRoleType());
		modelMap.put("curDeptId", (String) this.b(request, "zg_dept"));
		modelMap.put("deptFlag", this.j.findByParamCode(Param.NIS_CLINIC_REPORT_DEPT_SELECT));
		AcAccountConfig acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportStartDate");
		modelMap.put("startDate", acAccountConfig == null ? "" : acAccountConfig.getConfigValue());
		acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportEndDate");
		modelMap.put("endDate", acAccountConfig == null ? "" : acAccountConfig.getConfigValue());
		return "report/fineReportYwFrame";
	}

	@RequestMapping({"/report/f_view/fineReportSsFrame"})
	public String f(HttpServletRequest request, ModelMap modelMap, String reportFile, String __bypagesize__,
			String statType, String id, String startDate, String endDate) {
		modelMap.put("reportFile", reportFile);
		modelMap.put("__bypagesize__", __bypagesize__);
		modelMap.put("statType", statType);
		modelMap.put("id", id);
		modelMap.put("reportUrl", this.j.findByParamCode(Param.NIS_REPORT_URL));
		modelMap.put("nisUrl", this.j.findByParamCode(Param.NIS_HTTP_URL));
		modelMap.put("unitId", this.c(request));
		modelMap.put("unitFlag", this.se.getAll().size() > 1 ? "1" : "0");
		AcAccount acAccount = (AcAccount) this.b(request);
		modelMap.put("account", acAccount);
		modelMap.put("roleType", acAccount.getRoleCur().getRoleType());
		modelMap.put("curDeptId", (String) this.b(request, "zg_dept"));
		modelMap.put("deptFlag", this.j.findByParamCode(Param.NIS_CLINIC_REPORT_DEPT_SELECT));
		AcAccountConfig acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportStartDate");
		if (ab.isEmpty(startDate)) {
			startDate = acAccountConfig == null ? "" : acAccountConfig.getConfigValue();
		}

		modelMap.put("startDate", startDate);
		if (ab.isEmpty(endDate)) {
			acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportEndDate");
			endDate = acAccountConfig == null ? "" : acAccountConfig.getConfigValue();
		}

		modelMap.put("endDate", endDate);
		return "report/fineReportSsFrame";
	}

	@RequestMapping({"/report/f_view/fineReportHdmFrame"})
	public String g(HttpServletRequest request, ModelMap modelMap, String reportFile, String __bypagesize__,
			String statType, String id, String startDate, String endDate) {
		modelMap.put("reportFile", reportFile);
		modelMap.put("__bypagesize__", __bypagesize__);
		modelMap.put("statType", statType);
		modelMap.put("id", id);
		modelMap.put("reportUrl", this.j.findByParamCode(Param.NIS_REPORT_URL));
		modelMap.put("nisUrl", this.j.findByParamCode(Param.NIS_HTTP_URL));
		modelMap.put("unitId", this.c(request));
		modelMap.put("unitFlag", this.se.getAll().size() > 1 ? "1" : "0");
		AcAccount acAccount = (AcAccount) this.b(request);
		modelMap.put("account", acAccount);
		modelMap.put("roleType", acAccount.getRoleCur().getRoleType());
		modelMap.put("curDeptId", (String) this.b(request, "zg_dept"));
		modelMap.put("deptFlag", this.j.findByParamCode(Param.NIS_CLINIC_REPORT_DEPT_SELECT));
		AcAccountConfig acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportStartDate");
		if (ab.isEmpty(startDate)) {
			startDate = acAccountConfig == null ? "" : acAccountConfig.getConfigValue();
		}

		modelMap.put("startDate", startDate);
		if (ab.isEmpty(endDate)) {
			acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportEndDate");
			endDate = acAccountConfig == null ? "" : acAccountConfig.getConfigValue();
		}

		modelMap.put("endDate", endDate);
		return "report/fineReportHdmFrame";
	}

	@RequestMapping({"/report/f_view/fineReportSsyyFrame"})
	public String h(HttpServletRequest request, ModelMap modelMap, String reportFile, String __bypagesize__,
			String statType, String id, String startDate, String endDate) {
		modelMap.put("reportFile", reportFile);
		modelMap.put("__bypagesize__", __bypagesize__);
		modelMap.put("statType", statType);
		modelMap.put("id", id);
		modelMap.put("reportUrl", this.j.findByParamCode(Param.NIS_REPORT_URL));
		modelMap.put("nisUrl", this.j.findByParamCode(Param.NIS_HTTP_URL));
		modelMap.put("unitId", this.c(request));
		modelMap.put("unitFlag", this.se.getAll().size() > 1 ? "1" : "0");
		AcAccount acAccount = (AcAccount) this.b(request);
		modelMap.put("account", acAccount);
		modelMap.put("roleType", acAccount.getRoleCur().getRoleType());
		modelMap.put("curDeptId", (String) this.b(request, "zg_dept"));
		modelMap.put("deptFlag", this.j.findByParamCode(Param.NIS_CLINIC_REPORT_DEPT_SELECT));
		AcAccountConfig acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportStartDate");
		if (ab.isEmpty(startDate)) {
			startDate = acAccountConfig == null ? "" : acAccountConfig.getConfigValue();
		}

		modelMap.put("startDate", startDate);
		if (ab.isEmpty(endDate)) {
			acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportEndDate");
			endDate = acAccountConfig == null ? "" : acAccountConfig.getConfigValue();
		}

		modelMap.put("endDate", endDate);
		return "report/fineReportSsyyFrame";
	}

	@RequestMapping({"/report/f_view/fineReportGrlFrame"})
	public String h(HttpServletRequest request, ModelMap modelMap, String reportFile, String __bypagesize__,
			String statType, String id) {
		modelMap.put("reportFile", reportFile);
		modelMap.put("__bypagesize__", __bypagesize__);
		modelMap.put("statType", statType);
		modelMap.put("id", id);
		modelMap.put("reportUrl", this.j.findByParamCode(Param.NIS_REPORT_URL));
		modelMap.put("nisUrl", this.j.findByParamCode(Param.NIS_HTTP_URL));
		modelMap.put("unitId", this.c(request));
		modelMap.put("unitFlag", this.se.getAll().size() > 1 ? "1" : "0");
		AcAccount acAccount = (AcAccount) this.b(request);
		modelMap.put("account", acAccount);
		modelMap.put("roleType", acAccount.getRoleCur().getRoleType());
		modelMap.put("curDeptId", (String) this.b(request, "zg_dept"));
		modelMap.put("deptFlag", this.j.findByParamCode(Param.NIS_CLINIC_REPORT_DEPT_SELECT));
		modelMap.put("years", f.getYears());
		return "report/fineReportGrlFrame";
	}

	@RequestMapping({"/report/f_view/fineReportGrl"})
	public String aq(HttpServletRequest request, ModelMap modelMap) {
		return "report/fineReportGrl";
	}

	@RequestMapping({"/report/json/searchvalue/save"})
	@ResponseBody
	public void T(HttpServletRequest request, HttpServletResponse response, String startDate, String endDate) {
		this.a(request, "report_startDate", startDate);
		this.a(request, "report_endDate", endDate);
	}

	@RequestMapping({"/report/json/getTreeRoot"})
	@ResponseBody
	public void aF(HttpServletRequest request, HttpServletResponse response, String nol) {
		TreeEntity te = new TreeEntity();
		te.setId("0");
		te.setText("全院");
		te.setState("closed");
		te.setIconCls("icon-house");
		List list = this.cZ.getRoot(nol);
		te.setChildren(list);
		this.a(response, te);
	}

	@RequestMapping({"/report/f_view/fineReportGrtjFrame"})
	public String i(HttpServletRequest request, ModelMap modelMap, String reportFile, String __bypagesize__,
			String statType, String id, String startDate, String endDate) {
		modelMap.put("reportFile", reportFile);
		modelMap.put("__bypagesize__", __bypagesize__);
		modelMap.put("statType", statType);
		modelMap.put("id", id);
		modelMap.put("reportUrl", this.j.findByParamCode(Param.NIS_REPORT_URL));
		modelMap.put("nisUrl", this.j.findByParamCode(Param.NIS_HTTP_URL));
		modelMap.put("unitId", this.c(request));
		modelMap.put("unitFlag", this.se.getAll().size() > 1 ? "1" : "0");
		AcAccount acAccount = (AcAccount) this.b(request);
		modelMap.put("account", acAccount);
		modelMap.put("roleType", acAccount.getRoleCur().getRoleType());
		modelMap.put("curDeptId", (String) this.b(request, "zg_dept"));
		modelMap.put("deptFlag", this.j.findByParamCode(Param.NIS_CLINIC_REPORT_DEPT_SELECT));
		AcAccountConfig acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportStartDate");
		if (ab.isEmpty(startDate)) {
			startDate = acAccountConfig == null ? "" : acAccountConfig.getConfigValue();
		}

		modelMap.put("startDate", startDate);
		if (ab.isEmpty(endDate)) {
			acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportEndDate");
			endDate = acAccountConfig == null ? "" : acAccountConfig.getConfigValue();
		}

		modelMap.put("endDate", endDate);
		return "report/fineReportGrtjFrame";
	}

	@RequestMapping({"/report/f_view/fineReportGrtj"})
	public String ar(HttpServletRequest request, ModelMap modelMap) {
		return "report/fineReportGrtj";
	}

	@RequestMapping({"/report/f_view/fineReportCdcFrame"})
	public String j(HttpServletRequest request, ModelMap modelMap, String reportFile, String __bypagesize__,
			String statType, String id, String startDate, String endDate) {
		modelMap.put("reportFile", reportFile);
		modelMap.put("__bypagesize__", __bypagesize__);
		modelMap.put("statType", statType);
		modelMap.put("id", id);
		modelMap.put("reportUrl", this.j.findByParamCode(Param.NIS_REPORT_URL));
		modelMap.put("nisUrl", this.j.findByParamCode(Param.NIS_HTTP_URL));
		modelMap.put("unitId", this.c(request));
		modelMap.put("unitFlag", this.se.getAll().size() > 1 ? "1" : "0");
		AcAccount acAccount = (AcAccount) this.b(request);
		modelMap.put("account", acAccount);
		modelMap.put("roleType", acAccount.getRoleCur().getRoleType());
		modelMap.put("curDeptId", (String) this.b(request, "zg_dept"));
		modelMap.put("deptFlag", this.j.findByParamCode(Param.NIS_CLINIC_REPORT_DEPT_SELECT));
		AcAccountConfig acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportStartDate");
		if (ab.isEmpty(startDate)) {
			startDate = acAccountConfig == null ? "" : acAccountConfig.getConfigValue();
		}

		modelMap.put("startDate", startDate);
		if (ab.isEmpty(endDate)) {
			acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportEndDate");
			endDate = acAccountConfig == null ? "" : acAccountConfig.getConfigValue();
		}

		modelMap.put("endDate", endDate);
		return "report/fineReportCdcFrame";
	}

	@RequestMapping({"/report/f_view/fineReportCdc"})
	public String as(HttpServletRequest request, ModelMap modelMap) {
		return "report/fineReportCdc";
	}

	@RequestMapping({"/report/f_view/fineReportKJYWSYZLJTSFrame"})
	public String i(HttpServletRequest request, ModelMap modelMap, String reportFile, String __bypagesize__,
			String statType, String id) {
		modelMap.put("reportFile", reportFile);
		modelMap.put("__bypagesize__", __bypagesize__);
		modelMap.put("statType", statType);
		modelMap.put("id", id);
		modelMap.put("reportUrl", this.j.findByParamCode(Param.NIS_REPORT_URL));
		modelMap.put("nisUrl", this.j.findByParamCode(Param.NIS_HTTP_URL));
		modelMap.put("unitId", this.c(request));
		modelMap.put("unitFlag", this.se.getAll().size() > 1 ? "1" : "0");
		AcAccount acAccount = (AcAccount) this.b(request);
		modelMap.put("account", acAccount);
		modelMap.put("roleType", acAccount.getRoleCur().getRoleType());
		modelMap.put("curDeptId", (String) this.b(request, "zg_dept"));
		modelMap.put("deptFlag", this.j.findByParamCode(Param.NIS_CLINIC_REPORT_DEPT_SELECT));
		AcAccountConfig acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportStartDate");
		modelMap.put("startDate", acAccountConfig == null ? "" : acAccountConfig.getConfigValue());
		acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportEndDate");
		modelMap.put("endDate", acAccountConfig == null ? "" : acAccountConfig.getConfigValue());
		return "report/fineReportKJYWSYZLJTSFrame";
	}

	@RequestMapping({"/report/f_view/fineReportKJYWSYZLJTS"})
	public String at(HttpServletRequest request, ModelMap modelMap) {
		return "report/fineReportKJYWSYZLJTS";
	}

	@RequestMapping({"/report/f_view/fineReportSYKJYWSJLFrame"})
	public String j(HttpServletRequest request, ModelMap modelMap, String reportFile, String __bypagesize__,
			String statType, String id) {
		modelMap.put("reportFile", reportFile);
		modelMap.put("__bypagesize__", __bypagesize__);
		modelMap.put("statType", statType);
		modelMap.put("id", id);
		modelMap.put("reportUrl", this.j.findByParamCode(Param.NIS_REPORT_URL));
		modelMap.put("nisUrl", this.j.findByParamCode(Param.NIS_HTTP_URL));
		modelMap.put("unitId", this.c(request));
		modelMap.put("unitFlag", this.se.getAll().size() > 1 ? "1" : "0");
		AcAccount acAccount = (AcAccount) this.b(request);
		modelMap.put("account", acAccount);
		modelMap.put("roleType", acAccount.getRoleCur().getRoleType());
		modelMap.put("curDeptId", (String) this.b(request, "zg_dept"));
		modelMap.put("deptFlag", this.j.findByParamCode(Param.NIS_CLINIC_REPORT_DEPT_SELECT));
		AcAccountConfig acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportStartDate");
		modelMap.put("startDate", acAccountConfig == null ? "" : acAccountConfig.getConfigValue());
		acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportEndDate");
		modelMap.put("endDate", acAccountConfig == null ? "" : acAccountConfig.getConfigValue());
		return "report/fineReportSYKJYWSJLFrame";
	}

	@RequestMapping({"/report/f_view/fineReportSYKJYWSJL"})
	public String au(HttpServletRequest request, ModelMap modelMap) {
		return "report/fineReportSYKJYWSJL";
	}

	@RequestMapping({"/report/f_view/fineReportHivFrame"})
	public String k(HttpServletRequest request, ModelMap modelMap, String reportFile, String __bypagesize__,
			String statType, String id) {
		modelMap.put("reportFile", reportFile);
		modelMap.put("__bypagesize__", __bypagesize__);
		modelMap.put("statType", statType);
		modelMap.put("id", id);
		modelMap.put("reportUrl", this.j.findByParamCode(Param.NIS_REPORT_URL));
		modelMap.put("nisUrl", this.j.findByParamCode(Param.NIS_HTTP_URL));
		modelMap.put("unitId", this.c(request));
		modelMap.put("unitFlag", this.se.getAll().size() > 1 ? "1" : "0");
		AcAccount acAccount = (AcAccount) this.b(request);
		modelMap.put("account", acAccount);
		modelMap.put("roleType", acAccount.getRoleCur().getRoleType());
		modelMap.put("curDeptId", (String) this.b(request, "zg_dept"));
		modelMap.put("deptFlag", this.j.findByParamCode(Param.NIS_CLINIC_REPORT_DEPT_SELECT));
		AcAccountConfig acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportStartDate");
		modelMap.put("startDate", acAccountConfig == null ? "" : acAccountConfig.getConfigValue());
		acAccountConfig = this.b.getByKey(acAccount.getUserId(), "reportEndDate");
		modelMap.put("endDate", acAccountConfig == null ? "" : acAccountConfig.getConfigValue());
		return "report/fineReportHivFrame";
	}
}