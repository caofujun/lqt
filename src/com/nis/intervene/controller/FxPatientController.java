package com.nis.intervene.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.follow.service.FoPatientService;
import com.nis.icu.entity.By001Bfgz;
import com.nis.icu.service.By001BfgzService;
import com.nis.intervene.entity.FxPatient;
import com.nis.intervene.entity.FxPatientZb;
import com.nis.intervene.service.FxPatientService;
import com.nis.intervene.service.FxPatientZbService;
import com.nis.organization.entity.Dep;
import com.nis.organization.service.DepService;
import com.nis.patient.entity.St001Jbxxb;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.service.St001JbxxbService;
import com.nis.patient.service.St002ZdxxbService;
import com.nis.patient.service.St003CryxxbService;
import com.nis.patient.service.St004YzxxbService;
import com.nis.patient.service.St005SsxxbService;
import com.nis.patient.service.St011SyjgbService;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
public class FxPatientController extends BaseController {
	private static final Logger c = Logger.getLogger(FxPatientController.class);
	@Autowired
	private FxPatientService sr;
	@Autowired
	private St001JbxxbService dg;
	@Autowired
	private St003CryxxbService bg;
	@Autowired
	private St002ZdxxbService bv;
	@Autowired
	private St004YzxxbService bu;
	@Autowired
	private St005SsxxbService bO;
	@Autowired
	private St011SyjgbService bH;
	@Autowired
	private FxPatientZbService ss;
	@Autowired
	private DepService e;
	@Autowired
	private By001BfgzService sf;
	@Autowired
	private FoPatientService qW;

	@RequestMapping({"/fxPatient/f_view/index"})
	public String o(HttpServletRequest request, ModelMap modelMap, String startDate, String endDate) {
		if (ab.isNotEmpty(startDate)) {
			modelMap.put("startDate", startDate);
		} else {
			modelMap.put("startDate", f.formatDate(f.a(new Date(), -30)));
		}

		if (ab.isNotEmpty(endDate)) {
			modelMap.put("endDate", endDate);
		} else {
			modelMap.put("endDate", f.formatDate(new Date()));
		}

		return "intervene/fxPatientIndex";
	}

	@SqlLog(p = "风险分析--风险分析列表")
	@RequestMapping({"/fxPatient/f_json/FxPatientIndex"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, FxPatient fxPatient) {
		List page = this.sr.c(fxPatient);
		this.b(response, page);
	}

	@SqlLog(p = "风险分析--风险分析列表导出")
	@RequestMapping({"/fxPatient/f_json/exportDeptFx"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, FxPatient fxPatient) throws IOException {
		List dataList = this.sr.c(fxPatient);
		String fileName = "风险干预" + (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
		response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
		response.setContentType("application/msexcel;charset=UTF-8");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		HSSFWorkbook workBook = this.sr.D(dataList);
		ServletOutputStream os = response.getOutputStream();
		workBook.write(os);
		os.flush();
		os.close();
	}

	@RequestMapping({"/fxPatient/f_view/fxPatientList"})
	public String a(HttpServletRequest request, ModelMap modelMap, FxPatient fxPatient) {
		modelMap.put("fxPatient", fxPatient);
		return "intervene/fxPatientList";
	}

	@RequestMapping({"/fxPatient/f_json/pageQuery"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, FxPatient fxPatient) {
		List fxList = this.sr.findFxPatientList(fxPatient);
		MyPage page = new MyPage(1, fxList.size(), fxList.size(), fxList);
		int yjgrSum = 0;
		int qrgrSum = 0;
		Iterator fxFooterList = fxList.iterator();

		FxPatient fx;
		while (fxFooterList.hasNext()) {
			fx = (FxPatient) fxFooterList.next();
			if (fx.getYjgrCount() != null) {
				yjgrSum += Integer.parseInt(fx.getYjgrCount());
			}

			if (fx.getQrgrCount() != null) {
				qrgrSum += Integer.parseInt(fx.getQrgrCount());
			}
		}

		fx = new FxPatient();
		fx.setPatientName("合计");
		fx.setYjgrCount(String.valueOf(yjgrSum));
		fx.setQrgrCount(String.valueOf(qrgrSum));
		ArrayList fxFooterList1 = new ArrayList();
		fxFooterList1.add(fx);
		page.setFooter(fxFooterList1);
		this.b(response, page);
	}

	@RequestMapping({"/fxPatient/f_view/fxPatientListIcu"})
	public String b(HttpServletRequest request, ModelMap modelMap, String deptid, String typeid, String neonatebw,
			String strDate) {
		Dep dep = this.e.get(deptid);
		By001Bfgz by001Bfgz = this.sf.get(typeid);
		if (dep != null) {
			modelMap.put("deptName", dep.getDeptName());
		}

		modelMap.put("typeName", by001Bfgz.getOutbreakTypeName());
		modelMap.put("deptid", deptid);
		modelMap.put("typeid", typeid);
		modelMap.put("typeid", typeid);
		modelMap.put("neonatebw", neonatebw);
		return "intervene/fxPatientListIcu";
	}

	@RequestMapping({"/fxPatient/f_json/pageQueryIcu"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, String unitId, String deptid, String typeid,
			String neonatebw, String startDate, String endDate) {
		Timestamp startdate = f.Y(startDate);
		Timestamp enddate = f.Y(endDate);
		List cryxxbList = this.bg.b(unitId, typeid, deptid, neonatebw, startdate, enddate);
		List fxList = this.sr.findFxPatientByList(cryxxbList);
		MyPage myPage = new MyPage(1, fxList.size(), fxList.size(), fxList);
		int yjgrSum = 0;
		int qrgrSum = 0;
		Iterator fxFooterList = fxList.iterator();

		FxPatient fx;
		while (fxFooterList.hasNext()) {
			fx = (FxPatient) fxFooterList.next();
			if (fx.getYjgrCount() != null) {
				yjgrSum += Integer.parseInt(fx.getYjgrCount());
			}

			if (fx.getQrgrCount() != null) {
				qrgrSum += Integer.parseInt(fx.getQrgrCount());
			}
		}

		fx = new FxPatient();
		fx.setPatientName("合计");
		fx.setYjgrCount(String.valueOf(yjgrSum));
		fx.setQrgrCount(String.valueOf(qrgrSum));
		ArrayList fxFooterList1 = new ArrayList();
		fxFooterList1.add(fx);
		myPage.setFooter(fxFooterList1);
		this.b(response, myPage);
	}

	@RequestMapping({"/fxPatient/f_view/detail"})
	public String x(HttpServletRequest request, ModelMap modelMap, String zyId) {
		St003Cryxxb st003Cryxxb = this.bg.get(zyId);
		List foPatientList = this.qW.getByPatientId(st003Cryxxb.getPatientId());
		if (foPatientList.size() > 0) {
			modelMap.put("follow", "1");
		}

		St001Jbxxb patient = this.dg.get(st003Cryxxb.getPatientId());
		List patientZbList = this.ss.findByzyid(zyId);
		modelMap.put("haPatient", patient);
		modelMap.put("zyxx", st003Cryxxb);
		modelMap.put("patientZbList", patientZbList);
		return "intervene/fxPatientDetail";
	}

	@RequestMapping({"/fxPatient/f_view/getzyId"})
	public void a(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String zyId) {
		Result result = new Result();
		List patientZbList = this.ss.findByzyid(zyId);
		ArrayList mapList = new ArrayList();

		for (int a = 0; a < patientZbList.size(); ++a) {
			FxPatientZb zbs = (FxPatientZb) patientZbList.get(a);
			HashMap map = new HashMap();
			ArrayList mpList = new ArrayList();
			map.put("charDate", zbs.getCharDate());

			for (int i = 0; i < zbs.getPzList().size(); ++i) {
				FxPatientZb zb = (FxPatientZb) zbs.getPzList().get(i);
				HashMap mp = new HashMap();
				mp.put("pzId", zb.getPzId());
				mp.put("pzStatus", zb.getPzStatus());
				mp.put("zbName", zb.getZbName());
				mp.put("pdcaStatus", zb.getPdcaStatus());
				mp.put("qsStatus", zb.getQsStatus());
				mp.put("fxColor", zb.getFxColor());
				mpList.add(mp);
			}

			map.put("pz" + a, mpList);
			mapList.add(map);
		}

		result.setData(mapList);
		result.setResult("success");
		this.a(response, result);
	}

	@RequestMapping({"/fxPatient/f_json/report"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, String queryStartDate, String queryEndDate,
			String deptCode, String dgsType, String dateType) {
		Result result = null;
		if (ab.isNotEmpty(queryStartDate) && ab.isNotEmpty(queryEndDate)) {
			if ("day".equals(dateType)) {
				queryStartDate = f.formatDate(f.a(f.k(queryEndDate, "yyyy-MM-dd"), -15));
			} else if ("month".equals(dateType)) {
				queryStartDate = f.formatDate(f.c(f.k(queryEndDate, "yyyy-MM-dd"), -1));
			}

			List mapList = this.sr.a(f.k(queryStartDate, "yyyy-MM-dd"), f.k(queryEndDate, "yyyy-MM-dd"), deptCode,
					dgsType, dateType);
			result = new Result("success");
			result.setData(mapList);
			this.b(response, result);
		} else {
			result = new Result("error", "查询指标统计，参数异常！时间区间不能为空");
			this.b(response, result);
		}
	}
}