package com.nis.hand.controller;

import com.nis.access.entity.AcAccount;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.utils.ab;
import com.nis.dict.service.SysDictService;
import com.nis.hand.entity.Sw002Ycxdc;
import com.nis.hand.service.Sw002YcxdcService;
import com.nis.hand.service.Sw003YcxsjService;
import com.nis.hand.service.Sw004YcxsjService;
import com.nis.param.service.SysParamService;
import java.util.Calendar;
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
public class Sw002YcxdcController extends BaseController {
	private static final Logger c = Logger.getLogger(Sw002YcxdcController.class);
	@Autowired
	private Sw002YcxdcService qZ;
	@Autowired
	private Sw003YcxsjService rb;
	@Autowired
	private Sw004YcxsjService rc;
	@Autowired
	private SysDictService p;
	@Autowired
	private SysParamService j;

	@RequestMapping({"/sw002Ycxdc/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		AcAccount user = (AcAccount) this.b(request);
		List personalType = this.p.u("personnel_type", user.getUnitId());
		modelMap.put("personalType", personalType);
		modelMap.put("userType", user.getRoleCur().getRoleType());
		modelMap.put("curDeptId", (String) this.b(request, "zg_dept"));
		Calendar c = Calendar.getInstance();
		modelMap.put("queryEndDate", c.get(1) + "-12-31");
		modelMap.put("queryStartDate", c.get(1) + "-01-01");
		String isqx = this.j.findByParamCode(Param.NIS_HHC_IS_QX);
		modelMap.put("isqx", isqx);
		return "hand/handsHygieneComplianceList";
	}

	@RequestMapping({"/sw002Ycxdc/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "手卫生调查--手卫生调查列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Sw002Ycxdc sw002Ycxdc) {
		MyPage page = this.qZ.a(sw002Ycxdc);
		this.a(response, page);
	}

	@RequestMapping({"/sw002Ycxdc/f_view/toadd"})
	@SqlLog(p = "手卫生调查--手卫生调查界面")
	public String r(HttpServletRequest request, ModelMap modelMap) {
		AcAccount user = (AcAccount) this.b(request);
		modelMap.put("userType", user.getRoleCur().getRoleType());
		List personalType = this.p.u("personnel_type", user.getUnitId());
		modelMap.put("personalType", personalType);
		List indication = this.p.u("hhc_indication", user.getUnitId());
		modelMap.put("indication", indication);
		List handsDeficiency = this.p.u("hands_deficiency", user.getUnitId());
		modelMap.put("handsDeficiency", handsDeficiency);
		List hhc_behavior = this.p.u("hhc_behavior", user.getUnitId());
		modelMap.put("behavior", hhc_behavior);
		List isright = this.p.u("boolean", user.getUnitId());
		modelMap.put("isright", isright);
		String paramCode = this.j.findByParamCode(Param.NIS_HHC_IS_NEED_INPUT_ERR_REASON);
		modelMap.put("INIER", paramCode);
		List errreason = this.p.u("hhc_errorreason", user.getUnitId());
		modelMap.put("errreason", errreason);
		String IERN = this.j.findByParamCode(Param.NIS_HHC_IS_ERR_REASON_NECESSARY);
		modelMap.put("IERN", IERN);
		return "hand/hhcTable";
	}

	@RequestMapping({"/sw002Ycxdc/f_view/toedit"})
	@SqlLog(p = "手卫生调查--手卫生调查界面")
	public String q(HttpServletRequest request, ModelMap modelMap, String dcId) {
		AcAccount user = (AcAccount) this.b(request);
		modelMap.put("userType", user.getRoleCur().getRoleType());
		List personalType = this.p.u("personnel_type", user.getUnitId());
		modelMap.put("personalType", personalType);
		List indication = this.p.u("hhc_indication", user.getUnitId());
		modelMap.put("indication", indication);
		List handsDeficiency = this.p.u("hands_deficiency", user.getUnitId());
		modelMap.put("handsDeficiency", handsDeficiency);
		List hhc_behavior = this.p.u("hhc_behavior", user.getUnitId());
		modelMap.put("behavior", hhc_behavior);
		List isright = this.p.u("boolean", user.getUnitId());
		modelMap.put("isright", isright);
		String paramCode = this.j.findByParamCode(Param.NIS_HHC_IS_NEED_INPUT_ERR_REASON);
		modelMap.put("INIER", paramCode);
		String IERN = this.j.findByParamCode(Param.NIS_HHC_IS_ERR_REASON_NECESSARY);
		modelMap.put("IERN", IERN);
		List errreason = this.p.u("hhc_errorreason", user.getUnitId());
		modelMap.put("errreason", errreason);
		if (ab.isNotEmpty(dcId)) {
			Sw002Ycxdc sw002Ycxdc = this.qZ.get(dcId);
			modelMap.put("sw002", sw002Ycxdc);
			List sw004 = this.rc.getByDcid(dcId);
			modelMap.put("AllWatchList", sw004);
		} else {
			modelMap.put("msg", "未获取到必要参数");
		}

		return "hand/hhcTable";
	}

	@RequestMapping({"/sw002Ycxdc/f_json/saveData"})
	@ResponseBody
	@SqlLog(p = "手卫生调查--保存手卫生调查")
	public void b(HttpServletRequest request, HttpServletResponse response, Sw002Ycxdc sw002Ycxdc) {
		AcAccount acount = (AcAccount) this.b(request);
		Result result = this.qZ.a(sw002Ycxdc, acount);
		this.a(response, result);
	}

	@RequestMapping({"/sw002Ycxdc/f_json/delData"})
	@ResponseBody
	@SqlLog(p = "手卫生调查--删除手卫生调查")
	public void x(HttpServletRequest request, HttpServletResponse response, String dcId) {
		AcAccount acount = (AcAccount) this.b(request);
		Result result = this.qZ.i(dcId);
		this.a(response, result);
	}
}