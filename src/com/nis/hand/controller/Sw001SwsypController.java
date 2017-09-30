package com.nis.hand.controller;

import com.nis.access.entity.AcAccount;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.v;
import com.nis.comm.utils.l;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import com.nis.hand.entity.Sw001Swsyp;
import com.nis.hand.service.Sw001SwsypService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
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
public class Sw001SwsypController extends BaseController {
	private static final Logger c = Logger.getLogger(Sw001SwsypController.class);
	@Autowired
	private Sw001SwsypService qY;
	@Autowired
	private SysDictService p;

	@RequestMapping({"/sw001Swsyp/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String queryEndDate = df.format(new Date());
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		gc.add(2, -2);
		String queryStartDate = (new SimpleDateFormat("yyyy-MM-dd")).format(gc.getTime());
		modelMap.put("queryStartDate", queryStartDate);
		modelMap.put("queryEndDate", queryEndDate);
		return "hand/handHygieneList";
	}

	@RequestMapping({"/sw001Swsyp/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "手卫生用品--手卫生用品列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Sw001Swsyp sw001Swsyp) {
		MyPage page = this.qY.a(sw001Swsyp);
		this.a(response, page);
	}

	@RequestMapping({"/sw001Swsyp/f_view/toadds"})
	@SqlLog(p = "手卫生用品--添加手卫生用品")
	public String p(HttpServletRequest request, ModelMap modelMap) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date time = null;

		try {
			time = format.parse(format.format(new Date()));
		} catch (ParseException arg8) {
			arg8.printStackTrace();
		}

		Sw001Swsyp sw001Swsyp = new Sw001Swsyp();
		sw001Swsyp.setReportDate(time);
		AcAccount acAccount = (AcAccount) this.b(request);
		List swsTypeList = this.p.u("swsType", acAccount.getUnitId());
		sw001Swsyp.setReportUserId(acAccount.getUsername());
		sw001Swsyp.setReportDeptId(acAccount.getDepNo());
		sw001Swsyp.setType(v.hc.getValue());
		if (swsTypeList != null && swsTypeList.size() > 0) {
			SysDict swsType = (SysDict) swsTypeList.get(0);
			sw001Swsyp.setTypeName(swsType.getDictName());
			sw001Swsyp.setSpecificaUnit(swsType.getExtParam2() + "/" + swsType.getExtParam1());
			sw001Swsyp.setInventoryUnit(swsType.getExtParam2());
			sw001Swsyp.setUsedUnit(swsType.getExtParam1());
			sw001Swsyp.setInventoryUnit(swsType.getExtParam2());
			modelMap.put("sw001Swsyp", sw001Swsyp);
			modelMap.put("swsTypeList", swsTypeList);
		}

		return "hand/handHygieneAdds";
	}

	@RequestMapping({"/sw001Swsyp/f_json/chooseHandUseType"})
	@ResponseBody
	@SqlLog(p = "手卫生用品--手卫生用品类型")
	public void w(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;
		AcAccount acAccount = (AcAccount) this.b(request);

		try {
			if (StringUtils.isNotBlank(id)) {
				Sw001Swsyp e = new Sw001Swsyp();
				SysDict swsType = this.p.j("swsType", id, acAccount.getUnitId());
				e.setTypeName(swsType.getDictName());
				e.setSpecificaUnit(swsType.getExtParam2() + "/" + swsType.getExtParam1());
				e.setInventoryUnit(swsType.getExtParam2());
				e.setUsedUnit(swsType.getExtParam1());
				e.setInventoryUnit(swsType.getExtParam2());
				result = new Result("success");
				result.setData(e);
			} else {
				result = new Result("error", "参数异常");
			}
		} catch (Exception arg7) {
			c.error("获取信息异常!", arg7);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/sw001Swsyp/f_view/toadd"})
	@SqlLog(p = "手卫生用品--添加手卫生用品")
	public String q(HttpServletRequest request, ModelMap modelMap) {
		LoginUser user = this.d(request);
		List disinfe = this.p.findByDictTypeCode("hand_disinfectant", user.getUnitId(), user.getDepNo());
		ArrayList disinfeList = new ArrayList();
		Iterator ordinaryList = disinfe.iterator();

		while (ordinaryList.hasNext()) {
			SysDict ordinary = (SysDict) ordinaryList.next();
			Sw001Swsyp antibacte = new Sw001Swsyp();
			antibacte.setType(v.hb.getValue());
			antibacte.setSpecification(Integer.valueOf(NumberUtils.toInt(ordinary.getDictCode())));
			disinfeList.add(antibacte);
		}

		modelMap.put("disinfeList", disinfeList);
		List ordinary1 = this.p.findByDictTypeCode("ordinary_hand_sanitizer", user.getUnitId(), user.getDepNo());
		ArrayList ordinaryList1 = new ArrayList();
		Iterator antibacteList = ordinary1.iterator();

		while (antibacteList.hasNext()) {
			SysDict antibacte1 = (SysDict) antibacteList.next();
			Sw001Swsyp dryPaper = new Sw001Swsyp();
			dryPaper.setType(v.hc.getValue());
			dryPaper.setSpecification(Integer.valueOf(NumberUtils.toInt(antibacte1.getDictCode())));
			ordinaryList1.add(dryPaper);
		}

		modelMap.put("ordinaryList", ordinaryList1);
		List antibacte2 = this.p.findByDictTypeCode("antibacterial_hand_sanitizer", user.getUnitId(), user.getDepNo());
		ArrayList antibacteList1 = new ArrayList();
		Iterator dryPaperList = antibacte2.iterator();

		Sw001Swsyp sw001Swsyp;
		while (dryPaperList.hasNext()) {
			SysDict dryPaper1 = (SysDict) dryPaperList.next();
			sw001Swsyp = new Sw001Swsyp();
			sw001Swsyp.setType(v.hd.getValue());
			sw001Swsyp.setSpecification(Integer.valueOf(NumberUtils.toInt(dryPaper1.getDictCode())));
			antibacteList1.add(sw001Swsyp);
		}

		modelMap.put("antibacteList", antibacteList1);
		List dryPaper2 = this.p.findByDictTypeCode("dry_paper", user.getUnitId(), user.getDepNo());
		ArrayList dryPaperList1 = new ArrayList();
		Iterator arg12 = dryPaper2.iterator();

		while (arg12.hasNext()) {
			SysDict sw001Swsyp2 = (SysDict) arg12.next();
			Sw001Swsyp sw001Swsyp1 = new Sw001Swsyp();
			sw001Swsyp1.setType(v.he.getValue());
			sw001Swsyp1.setSpecification(Integer.valueOf(NumberUtils.toInt(sw001Swsyp2.getDictCode())));
			dryPaperList1.add(sw001Swsyp1);
		}

		modelMap.put("dryPaperList", dryPaperList1);
		modelMap.put("typeMap", l.toString(v.s()));
		sw001Swsyp = new Sw001Swsyp();
		sw001Swsyp.setReportDate(new Date());
		sw001Swsyp.setReportDeptId(user.getDepNo());
		sw001Swsyp.setReportUserId(user.getUsername());
		sw001Swsyp.setReportUserName(user.getRealname());
		modelMap.put("sw001Swsyp", sw001Swsyp);
		return "hand/handHygieneAdd";
	}

	@RequestMapping({"/sw001Swsyp/f_json/add"})
	@ResponseBody
	@SqlLog(p = "手卫生用品--添加手卫生用品")
	public void b(HttpServletRequest request, HttpServletResponse response, Sw001Swsyp sw001Swsyp) {
		Result result = null;

		try {
			if (sw001Swsyp.getSw001List() != null) {
				this.qY.b(sw001Swsyp);
				result = new Result("success");
			} else {
				result = new Result("error", "参数异常");
			}
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/sw001Swsyp/f_view/toedit"})
	@SqlLog(p = "手卫生用品--手卫生用品详情")
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (id != null) {
			Sw001Swsyp sw001Swsyp = this.qY.get(id);
			modelMap.put("sw001Swsyp", sw001Swsyp);
		}

		return "hand/handHygieneEdit";
	}

	@RequestMapping({"/sw001Swsyp/f_json/saves"})
	@ResponseBody
	@SqlLog(p = "手卫生用品--保存手卫生用品")
	public void c(HttpServletRequest request, HttpServletResponse response, Sw001Swsyp sw001Swsyp) {
		Result result = null;

		try {
			if (StringUtils.isEmpty(sw001Swsyp.getId())) {
				this.qY.save(sw001Swsyp);
				result = new Result("success");
			} else {
				this.qY.c(sw001Swsyp);
				result = new Result("error", "参数异常");
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/sw001Swsyp/f_json/save"})
	@ResponseBody
	@SqlLog(p = "手卫生用品--保存手卫生用品")
	public void d(HttpServletRequest request, HttpServletResponse response, Sw001Swsyp sw001Swsyp) {
		Result result = null;

		try {
			if (StringUtils.isNotBlank(sw001Swsyp.getId())) {
				this.qY.c(sw001Swsyp);
				result = new Result("success");
			} else {
				result = new Result("error", "参数异常");
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/sw001Swsyp/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "手卫生用品--删除手卫生用品")
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			if (StringUtils.isNotBlank(id)) {
				this.qY.delete(id);
				result = new Result("success");
			} else {
				result = new Result("error", "参数异常");
			}
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}