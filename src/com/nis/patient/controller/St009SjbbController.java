package com.nis.patient.controller;

import com.nis.access.entity.AcAccount;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.Param;
import com.nis.comm.utils.ab;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import com.nis.monitor.service.Bk002GrzdService;
import com.nis.param.service.SysParamService;
import com.nis.patient.entity.St009Sjbb;
import com.nis.patient.service.St009SjbbService;
import java.util.ArrayList;
import java.util.Iterator;
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
public class St009SjbbController extends BaseController {
	private static final Logger c = Logger.getLogger(St009SjbbController.class);
	@Autowired
	private St009SjbbService bE;
	@Autowired
	private SysDictService p;
	@Autowired
	private SysParamService j;
	@Autowired
	private Bk002GrzdService us;

	@RequestMapping({"/st009Sjbb/f_view/toCheckInfoList"})
	@SqlLog(p = "患者信息--检验值异常字典")
	public String B(HttpServletRequest request, ModelMap modelMap, String zyid, String mzid) {
		AcAccount account = (AcAccount) this.b(request);
		String resultHigh = "";
		List hilist = this.p.u("result_high", account.getUnitId());

		SysDict resultLow;
		for (Iterator loList = hilist.iterator(); loList
				.hasNext(); resultHigh = resultHigh + resultLow.getDictCode() + ",") {
			resultLow = (SysDict) loList.next();
		}

		String resultLow1 = "";
		List loList1 = this.p.u("result_low", account.getUnitId());

		SysDict dict;
		for (Iterator nydict = loList1.iterator(); nydict
				.hasNext(); resultLow1 = resultLow1 + dict.getDictCode() + ",") {
			dict = (SysDict) nydict.next();
		}

		dict = this.p.j("ymjg", "R", (String) null);
		String nydict1 = dict.getExtParam1();
		modelMap.put("nydict", nydict1);
		modelMap.put("resultHigh", resultHigh);
		modelMap.put("resultLow", resultLow1);
		return "patient/checkInfoList";
	}

	@RequestMapping({"/st009Sjbb/f_view/jyDetail"})
	public String c(HttpServletRequest request, ModelMap modelMap, String zyid, String mzid, String testOrderNo,
			String itemType) {
		modelMap.put("mzid", mzid);
		modelMap.put("zyid", zyid);
		modelMap.put("testOrderNo", testOrderNo);
		modelMap.put("itemType", itemType);
		AcAccount account = (AcAccount) this.b(request);
		String resultHigh = "";
		List hilist = this.p.u("result_high", account.getUnitId());

		SysDict resultLow;
		for (Iterator loList = hilist.iterator(); loList
				.hasNext(); resultHigh = resultHigh + resultLow.getDictCode() + ",") {
			resultLow = (SysDict) loList.next();
		}

		String arg16 = "";
		List arg17 = this.p.u("result_low", account.getUnitId());

		SysDict sysDicts;
		for (Iterator strNyDict = arg17.iterator(); strNyDict.hasNext(); arg16 = arg16 + sysDicts.getDictCode() + ",") {
			sysDicts = (SysDict) strNyDict.next();
		}

		List arg18 = this.p.u("ymjg", (String) null);
		String arg19 = "";
		int count = 1;

		for (Iterator arg15 = arg18.iterator(); arg15.hasNext(); ++count) {
			SysDict sysDict = (SysDict) arg15.next();
			arg19 = arg19 + sysDict.getExtParam1();
			if (arg18.size() > count) {
				arg19 = arg19 + ",";
			}
		}

		modelMap.put("strNyDict", arg19);
		modelMap.put("resultHigh", resultHigh);
		modelMap.put("resultLow", arg16);
		if (itemType.equals("1")) {
			return "patient/xjDetail";
		} else {
			return "patient/cgDetail";
		}
	}

	@RequestMapping({"/st009Sjbb/f_json/findSt009SjbbList"})
	@ResponseBody
	@SqlLog(p = "患者信息--检验记录列表")
	public void a(HttpServletRequest request, HttpServletResponse response, St009Sjbb st009Sjbb) {
		String orderBy = this.j.findByParamCode(Param.NIS_hzda_YZORDERBY);
		st009Sjbb.setOrderBy(orderBy);
		MyPage page = this.bE.a(st009Sjbb);
		this.a(response, page);
	}

	@RequestMapping({"/st009Sjbb/f_json/findSjbbForInfectList"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, St009Sjbb st009Sjbb) {
		List st009SjbbList = null;
		if (st009Sjbb != null && ab.isNotEmpty(st009Sjbb.getZyid())) {
			st009SjbbList = this.bE.b(st009Sjbb);
			Iterator arg5 = st009SjbbList.iterator();

			while (arg5.hasNext()) {
				St009Sjbb sjbb = (St009Sjbb) arg5.next();
				if (sjbb.getTestOrderNo().equals(st009Sjbb.getTestOrderNo())) {
					sjbb.setRefid("1");
				}
			}
		}

		this.a(response, st009SjbbList);
	}

	@RequestMapping({"/st009Sjbb/f_json/findBacteriaList"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, St009Sjbb st009Sjbb) {
		Object st009SjbbList = null;
		if (st009Sjbb != null && StringUtils.isNotBlank(st009Sjbb.getZyid()) && st009Sjbb.getSubmiAt() != null) {
			st009SjbbList = this.bE.findBacteriaList(st009Sjbb);
		} else {
			st009SjbbList = new ArrayList();
		}

		this.a(response, st009SjbbList);
	}
}