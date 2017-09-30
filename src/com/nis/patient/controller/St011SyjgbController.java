package com.nis.patient.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.f;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.entity.St009Sjbb;
import com.nis.patient.entity.St011Syjgb;
import com.nis.patient.service.St001JbxxbService;
import com.nis.patient.service.St003CryxxbService;
import com.nis.patient.service.St009SjbbService;
import com.nis.patient.service.St011SyjgbService;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
public class St011SyjgbController extends BaseController {
	private static final Logger c = Logger.getLogger(St011SyjgbController.class);
	@Autowired
	private St011SyjgbService bH;
	@Autowired
	private St001JbxxbService dg;
	@Autowired
	private St009SjbbService bE;
	@Autowired
	private SysDictService p;
	@Autowired
	private St003CryxxbService bg;

	@RequestMapping({"/st011Syjgb/f_view/toSt011SyjgbList"})
	public String j(HttpServletRequest request, ModelMap modelMap, String testOrderNo, String zyid, String submiAt,
			String checkOutAt, String classCode) {
		modelMap.put("testOrderNo", testOrderNo);
		modelMap.put("zyid", zyid);
		modelMap.put("submiAt", submiAt);
		modelMap.put("checkOutAt", checkOutAt);
		modelMap.put("classCode", classCode);
		String resultHigh = "";
		List hilist = this.p.u("result_high", (String) null);

		SysDict resultLow;
		for (Iterator loList = hilist.iterator(); loList
				.hasNext(); resultHigh = resultHigh + resultLow.getDictCode() + ",") {
			resultLow = (SysDict) loList.next();
		}

		String resultLow1 = "";
		List loList1 = this.p.u("result_low", (String) null);

		SysDict dict;
		for (Iterator arg12 = loList1.iterator(); arg12.hasNext(); resultLow1 = resultLow1 + dict.getDictCode() + ",") {
			dict = (SysDict) arg12.next();
		}

		modelMap.put("resultHigh", resultHigh);
		modelMap.put("resultLow", resultLow1);
		return "patient/st011SyjgbList";
	}

	@RequestMapping({"/st011Syjgb/f_json/findSt011SyjgbListLeft"})
	@ResponseBody
	@SqlLog(p = "患者信息--检验单结果信息")
	public void a(HttpServletRequest request, HttpServletResponse response, St011Syjgb st011Syjgb) {
		St003Cryxxb st003Cryxxb = this.bg.get(st011Syjgb.getZyid());
		String startDate = "";
		String endDate = "";
		if (StringUtils.isBlank(startDate)) {
			if (st003Cryxxb.getInHospAt() != null) {
				startDate = f.formatDate(st003Cryxxb.getInHospAt());
			} else {
				startDate = f.formatDate(new Date());
			}
		}

		if (StringUtils.isBlank(endDate)) {
			if (st003Cryxxb.getOutAt() != null) {
				endDate = f.formatDate(f.a(st003Cryxxb.getOutAt(), 1));
			} else {
				endDate = f.formatDate(f.a(new Date(), 1));
			}
		}

		List patientViewList = this.dg.K(st011Syjgb.getZyid(), st011Syjgb.getClassCode());
		this.a(response, patientViewList);
	}

	@RequestMapping({"/st011Syjgb/f_json/findSt011SyjgbList"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, St011Syjgb st011Syjgb) {
		List st011SyjgbList = this.bH.findSt011SyjgbList(st011Syjgb);
		this.a(response, st011SyjgbList);
	}

	@RequestMapping({"/st011Syjgb/f_json/findSt011Syjgbqst"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, St011Syjgb st011Syjgb) {
		Result result = null;

		try {
			LinkedList e = new LinkedList();
			LinkedList xAxisData = new LinkedList();
			LinkedList legendData = new LinkedList();
			LinkedList series = new LinkedList();
			List list = this.bH.findSt011Syjgbqst(st011Syjgb);
			Iterator arg10 = list.iterator();

			while (arg10.hasNext()) {
				St011Syjgb resultMap = (St011Syjgb) arg10.next();
				xAxisData.add(f.X(resultMap.getResultDate()));
				e.add(resultMap.getTestResult());
			}

			series.add(e);
			HashMap resultMap1 = new HashMap();
			resultMap1.put("legendData", legendData);
			resultMap1.put("xAxisData", xAxisData);
			resultMap1.put("series", series);
			result = new Result("success");
			result.setData(resultMap1);
		} catch (Exception arg11) {
			result = new Result("error", arg11.getMessage());
			arg11.printStackTrace();
		}

		this.a(response, result);
	}

	@RequestMapping({"/st011Syjgb/f_json/getCheckResultTitle"})
	@ResponseBody
	public void d(HttpServletRequest request, HttpServletResponse response, St009Sjbb st009Sjbb) {
		St009Sjbb st009 = null;
		if (st009Sjbb != null && StringUtils.isNotBlank(st009Sjbb.getZyid())
				&& StringUtils.isNotBlank(st009Sjbb.getTestOrderNo())) {
			st009 = this.bE.getCheckResultTitle(st009Sjbb);
		}

		this.a(response, st009);
	}
}