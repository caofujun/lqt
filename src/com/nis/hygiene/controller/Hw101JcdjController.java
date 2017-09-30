package com.nis.hygiene.controller;

import com.nis.access.entity.AcAccount;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.e;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.hygiene.entity.Hw101Jcdj;
import com.nis.hygiene.entity.Hw102Jcdmx;
import com.nis.hygiene.service.Hw008XmsqService;
import com.nis.hygiene.service.Hw009KssqService;
import com.nis.hygiene.service.Hw101JcdjService;
import com.nis.hygiene.service.Hw102JcdmxService;
import com.nis.hygiene.service.Hw103JcdjgService;
import com.nis.hygiene.service.Hw104JcdjgXjService;
import com.nis.param.service.SysParamService;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
public class Hw101JcdjController extends BaseController {
	private static final Logger c = Logger.getLogger(Hw101JcdjController.class);
	@Autowired
	private Hw101JcdjService rD;
	@Autowired
	private SysParamService j;
	@Autowired
	private Hw102JcdmxService rE;
	@Autowired
	private Hw104JcdjgXjService rF;
	@Autowired
	private Hw103JcdjgService rG;
	@Autowired
	private Hw009KssqService ry;
	@Autowired
	private Hw008XmsqService rx;

	@RequestMapping({"/hw101Jcdj/f_view/toHygieneIndex"})
	public String r(HttpServletRequest request, ModelMap modelMap, String type) {
		return "hygiene/hygieneIndex";
	}

	@RequestMapping({"/hw101Jcdj/f_view/toHygieneDict"})
	public String s(HttpServletRequest request, ModelMap modelMap, String type) {
		return "hygiene/hygieneDict";
	}

	@RequestMapping({"/hw101Jcdj/f_view/toHygieneList"})
	public String t(HttpServletRequest request, ModelMap modelMap, String type) {
		String days = this.j.findByParamCode(Param.NIS_HYGIENE_QUERY_DAYS);
		Date currDate = f.getCurDate();
		modelMap.put("queryEndDate", f.c(currDate, "yyyy-MM-dd"));
		modelMap.put("type", type);
		AcAccount ac = (AcAccount) this.b(request);
		if (e.fD.getValue().equals(ac.getRoleCur().getRoleType())) {
			String afterDays = this.j.findByParamCode(Param.NIS_HYGIENE_ISENABLE_AUDIT);
			modelMap.put("isAudit", afterDays);
		}

		int afterDays1 = NumberUtils.toInt(days);
		modelMap.put("queryStartDate", f.formatDate(f.a(currDate, -afterDays1)));
		if (e.fE.getValue().equals(ac.getRoleCur().getRoleType())) {
			modelMap.put("clinical", "1");
		}

		modelMap.put("userId", ac.getUsername());
		modelMap.put("deptId", ac.getDepNo());
		return "hygiene/hygieneList";
	}

	@RequestMapping({"/hw101Jcdj/f_json/findHygieneList"})
	@ResponseBody
	@SqlLog(p = "环境卫生监测--环境卫生监测列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Hw102Jcdmx hw102Jcdmx, String dateType) {
		if ("2".equals(dateType)) {
			hw102Jcdmx.setDateField("test_at");
		} else {
			hw102Jcdmx.setDateField("report_at");
		}

		AcAccount ac = (AcAccount) this.b(request);
		hw102Jcdmx.setUserId(ac.getUsername());
		if (hw102Jcdmx.getDjId() != null && !"".equals(hw102Jcdmx.getDjId())) {
			hw102Jcdmx.setDjId(ab.aR(hw102Jcdmx.getDjId()));
		}

		if (hw102Jcdmx.getResult() != null && !"".equals(hw102Jcdmx.getResult())) {
			hw102Jcdmx.setResult(ab.aR(hw102Jcdmx.getResult()));
		}

		MyPage page = this.rD.a(hw102Jcdmx);
		List hs102List = page.getRows();
		LinkedList reHs102List = new LinkedList();
		Iterator arg9 = hs102List.iterator();

		while (true) {
			while (arg9.hasNext()) {
				Hw102Jcdmx hs102 = (Hw102Jcdmx) arg9.next();
				hw102Jcdmx.setDjId(hs102.getDjId());
				hw102Jcdmx.setDjDeptId(hs102.getDeptId());
				List hw102List = this.rE.findHw102JcdmxByDjId(hw102Jcdmx);
				if (hw102List.size() > 0) {
					Iterator arg12 = hw102List.iterator();

					while (arg12.hasNext()) {
						Hw102Jcdmx hw102 = (Hw102Jcdmx) arg12.next();
						hw102.setDeptName(hs102.getDeptName());
						hw102.setDeptId(hs102.getDeptId());
						hw102.setReportByName(hs102.getReportByName());
						hw102.setReportAt(hs102.getReportAt());
						hw102.setIsprint(hs102.getIsprint());
						reHs102List.add(hw102);
					}
				} else {
					reHs102List.add(hs102);
				}
			}

			page.setRows(reHs102List);
			this.a(response, page);
			return;
		}
	}

	@RequestMapping({"/hw101Jcdj/f_json/findHw102JcdmxByDjId"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Hw102Jcdmx hw102Jcdmx) {
		LoginUser user = this.d(request);
		hw102Jcdmx.setUserId(user.getUsername());
		hw102Jcdmx.setDeptId(user.getDepNo());
		List hw102List = this.rE.findHw102JcdmxByDjId(hw102Jcdmx);
		this.a(response, hw102List);
	}

	@RequestMapping({"/hw101Jcdj/f_view/toHygieneEdit"})
	@SqlLog(p = "环境卫生监测--编辑环境卫生监测单")
	public String m(HttpServletRequest request, ModelMap modelMap, String djId, String type) {
		LoginUser user = this.d(request);
		if (StringUtils.isBlank(djId)) {
			Date hw101Jcdj = new Date();
			Hw101Jcdj isAudit = new Hw101Jcdj();
			isAudit.setDjId(f.c(hw101Jcdj, "yyyyMMddHHmmss"));
			isAudit.setDeptId(user.getDepNo());
			isAudit.setReportBy(user.getUsername());
			isAudit.setCreateBy(user.getUsername());
			isAudit.setReportAt(hw101Jcdj);
			modelMap.put("hw101Jcdj", isAudit);
			Hw102Jcdmx hw102Jcdmx = new Hw102Jcdmx();
			hw102Jcdmx.setTakeBy(user.getUsername());
			hw102Jcdmx.setTakeAt(hw101Jcdj);
			hw102Jcdmx.setTakeType("3");
			hw102Jcdmx.setTestBy(user.getUsername());
			hw102Jcdmx.setTestAt(hw101Jcdj);
			modelMap.put("hw102Jcdmx", hw102Jcdmx);
		} else {
			Hw101Jcdj hw101Jcdj1 = this.rD.get(djId);
			modelMap.put("hw101Jcdj", hw101Jcdj1);
			modelMap.put("action", "edit");
			String isAudit1 = this.j.findByParamCode(Param.NIS_HYGIENE_ISENABLE_AUDIT);
			modelMap.put("isAudit", isAudit1);
		}

		modelMap.put("type", type);
		return "hygiene/hygieneEdit";
	}

	@RequestMapping({"/hw101Jcdj/f_json/getHw102Jcdmx"})
	@ResponseBody
	@SqlLog(p = "环境卫生监测--采样数据")
	public void z(HttpServletRequest request, HttpServletResponse response, String id, String djDeptId) {
		Hw102Jcdmx hw102Jcdmx = this.rE.get(id);
		AcAccount ac = (AcAccount) this.b(request);
		String classId = hw102Jcdmx.getClassId();
		if (ab.isNotEmpty(classId)) {
			classId = classId.substring(0, 2);
		}

		int num = this.rx.judgeResultsPermissions(ac.getUsername(), ac.getDepNo(), djDeptId, classId);
		if (num > 0) {
			if (hw102Jcdmx != null && hw102Jcdmx.getTestAt() == null) {
				hw102Jcdmx.setTestAt(new Date());
			}

			if (hw102Jcdmx != null && hw102Jcdmx.getTestBy() == null) {
				hw102Jcdmx.setTestBy(ac.getUsername());
			}
		}

		List pathoIdList = this.rF.findPathoIdByHw102Id(id);
		hw102Jcdmx.setCheckOutBacteria(pathoIdList);
		this.a(response, hw102Jcdmx);
	}

	@RequestMapping({"/hw101Jcdj/f_json/saveHygiene"})
	@ResponseBody
	@SqlLog(p = "环境卫生监测--保存环境卫生监测单")
	public void a(HttpServletRequest request, HttpServletResponse response, Hw101Jcdj hw101Jcdj, Hw102Jcdmx hw102Jcdmx,
			String action) {
		Result result = null;

		try {
			result = new Result();
			if ("edit".equals(action)) {
				AcAccount e = (AcAccount) this.b(request);
				if (e.fE.getValue().equals(e.getRoleCur().getRoleType())) {
					hw102Jcdmx.setClinical("1");
				}

				this.rD.a(hw101Jcdj, hw102Jcdmx, e);
			} else {
				this.rD.a(hw101Jcdj, hw102Jcdmx);
			}

			String e1 = "";
			result.setResult("success");
			result.setData(hw102Jcdmx.getId() + "##" + hw102Jcdmx.getResultFlagName());
		} catch (Exception arg7) {
			c.error("获取信息异常!", arg7);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/hw101Jcdj/f_json/delHygiene"})
	@ResponseBody
	@SqlLog(p = "环境卫生监测--删除环境卫生监测单")
	public void B(HttpServletRequest request, HttpServletResponse response, String djId) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isNotBlank(djId)) {
				this.rD.bv(djId);
				result.setResult("success");
			} else {
				result = new Result("error", "关键参数丢失");
			}
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/hw101Jcdj/f_view/toShowSample"})
	@SqlLog(p = "环境卫生监测--从模板中新建")
	public String u(HttpServletRequest request, ModelMap modelMap, String id) {
		Hw102Jcdmx hw102Jcdmx = this.rE.getShowSample(id);
		List pathoNameList = this.rF.findPathoNameByHw102Id(id);
		if (pathoNameList.size() > 0) {
			hw102Jcdmx.setCheckOutStr(StringUtils.join(pathoNameList, ","));
		}

		modelMap.put("hw102Jcdmx", hw102Jcdmx);
		List hw103List = this.rG.findListByHw102Id(id, (String) null);
		modelMap.put("hw103List", hw103List);
		return "hygiene/showSample";
	}

	@RequestMapping({"/hw101Jcdj/f_json/auditHygiene"})
	@ResponseBody
	@SqlLog(p = "环境卫生监测--审核环境卫生监测单")
	public void b(HttpServletRequest request, HttpServletResponse response, Hw102Jcdmx hw102Jcdmx) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isNotBlank(hw102Jcdmx.getDjId())) {
				LoginUser e = this.d(request);
				hw102Jcdmx.setCheckBy(e.getUsername());
				hw102Jcdmx.setCheckName(e.getRealname());
				this.rD.b(hw102Jcdmx);
				result.setResult("success");
			} else {
				result = new Result("error", "关键参数丢失");
			}
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/hw101Jcdj/f_view/toHygieneQuery"})
	public String n(HttpServletRequest request, ModelMap modelMap, String startDate, String endDate) {
		String isAudit;
		if (StringUtils.isBlank(startDate) && StringUtils.isBlank(endDate)) {
			isAudit = this.j.findByParamCode(Param.NIS_HYGIENE_QUERY_DAYS);
			Date ac = f.getCurDate();
			endDate = f.formatDate(ac);
			startDate = f.formatDate(f.a(ac, -NumberUtils.toInt(isAudit)));
		}

		modelMap.put("queryEndTime", endDate);
		modelMap.put("queryStartDate", startDate);
		isAudit = this.j.findByParamCode(Param.NIS_HYGIENE_ISENABLE_AUDIT);
		modelMap.put("isAudit", isAudit);
		AcAccount ac1 = (AcAccount) this.b(request);
		modelMap.put("userId", ac1.getUsername());
		modelMap.put("deptId", ac1.getDepNo());
		return "hygiene/hygieneQuery";
	}

	@RequestMapping({"/hw101Jcdj/f_json/updatePrintFlag"})
	@ResponseBody
	@SqlLog(p = "环境卫生监测--更新环境卫生监测单打印状态")
	public void C(HttpServletRequest request, HttpServletResponse response, String djId) {
		Result r = new Result();

		try {
			if (!StringUtils.isBlank(djId)) {
				this.rD.updatePrintFlag(djId);
				r.setResult("success");
			} else {
				r.setResult("error");
				r.setMsg("必要参数（djId）获取为空！");
			}
		} catch (Exception arg5) {
			r.setResult("error");
			r.setMsg("抱歉！更新打印标识失败！");
		}

		this.a(response, r);
	}
}