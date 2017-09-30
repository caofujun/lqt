package com.nis.outbreak.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.outbreak.entity.By007Config;
import com.nis.outbreak.entity.By007Show;
import com.nis.outbreak.service.By007ConfigService;
import com.nis.outbreak.service.By007ShowService;
import com.nis.param.service.SysParamService;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class By007ShowController extends BaseController {
	private static final Logger c = Logger.getLogger(By007ShowController.class);
	@Autowired
	private By007ShowService wf;
	@Autowired
	private By007ConfigService wg;
	@Autowired
	private SysParamService j;

	@RequestMapping({"/by007Show/f_view/toOutbreakTab"})
	public String i(HttpServletRequest request, ModelMap modelMap, String flag, String startDate, String endDate) {
		modelMap.put("flag", flag);
		if (ab.isNotEmpty(startDate)) {
			modelMap.put("startDate", startDate);
		} else {
			modelMap.put("startDate", f.l(f.a(f.getCurDate(), -30)));
		}

		if (ab.isNotEmpty(endDate)) {
			modelMap.put("endDate", endDate);
		} else {
			modelMap.put("endDate", f.l(f.getCurDate()));
		}

		return "outbreak/outbreakTab";
	}

	@RequestMapping({"/by007Show/f_view/toOutbreakTrends"})
	public String O(HttpServletRequest request, ModelMap modelMap) {
		modelMap.put("startDate", f.l(new Date()));
		List configList = this.wg.getAll();
		modelMap.put("configList", configList);
		return "outbreak/outbreakTrends";
	}

	@RequestMapping({"/by007Show/f_view/toPathogenPatientList"})
	public String P(HttpServletRequest request, ModelMap modelMap) {
		modelMap.put("startDate", f.l(new Date()));
		List configList = this.wg.getAll();
		modelMap.put("configList", configList);
		return "outbreak/pathogenPatientList";
	}

	@RequestMapping({"/by007Show/f_view/toInfectionSiteList"})
	public String Q(HttpServletRequest request, ModelMap modelMap) {
		modelMap.put("startDate", f.l(new Date()));
		List configList = this.wg.getAll();
		modelMap.put("configList", configList);
		return "outbreak/infectionSiteList";
	}

	@RequestMapping({"/by007Show/f_view/toOutbreakContrast"})
	public String R(HttpServletRequest request, ModelMap modelMap) {
		modelMap.put("startDate", f.l(new Date()));
		List configList = this.wg.getAll();
		modelMap.put("configList", configList);
		return "outbreak/outbreakContrast";
	}

	@RequestMapping({"/by007Show/f_json/findTrendFieldsAndWarn"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String startDate, By007Config by007Config) {
		Result result = null;

		try {
			if (by007Config != null) {
				this.wg.batchUpdAbsolute(by007Config.getConfigList());
			}

			HashMap e = new HashMap();
			List showList = this.wf.findShowFields(Integer.valueOf(2));
			e.put("showFields", showList);
			List warmList = this.wf.cm(startDate);
			e.put("warmList", warmList);
			result = new Result("success");
			result.setData(e);
		} catch (Exception arg8) {
			result = new Result("error");
			arg8.printStackTrace();
		}

		this.b(response, result);
	}

	@RequestMapping({"/by007Show/f_json/findTrendAnalysis"})
	@ResponseBody
	public void am(HttpServletRequest request, HttpServletResponse response, String startDate) {
		List list = this.wf.cn(startDate);
		this.a(response, list);
	}

	@RequestMapping({"/by007Show/f_json/findTrendGraphData"})
	@ResponseBody
	public void x(HttpServletRequest request, HttpServletResponse response, String endDate, String id, String deptId) {
		Result result = null;

		try {
			String e = this.j.findByParamCode(Param.NIS_OUT_BREAK_DAYS);
			String startDate = f.formatDate(f.a(f.Z(endDate), -NumberUtils.toInt(e)));
			HashMap resultMap = new HashMap();
			List list = this.wf.o(startDate, endDate, id, deptId);
			resultMap.put("list", list);
			By007Config by007Config = this.wg.getByShowId(id);
			resultMap.put("absolutePeople", by007Config.getAbsolutePeople());
			resultMap.put("startDate", startDate);
			result = new Result("success");
			result.setData(resultMap);
		} catch (Exception arg11) {
			result = new Result("error");
			arg11.printStackTrace();
		}

		this.b(response, result);
	}

	@RequestMapping({"/by007Show/f_json/findContrastFieldsAndWarn"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, String startDate, By007Config by007Config) {
		Result result = null;

		try {
			if (by007Config != null) {
				this.wg.batchUpdRelative(by007Config.getConfigList());
			}

			HashMap e = new HashMap();
			List showList = this.wf.findShowFields(Integer.valueOf(3));
			e.put("showFields", showList);
			List warmList = this.wf.co(startDate);
			e.put("warmList", warmList);
			result = new Result("success");
			result.setData(e);
		} catch (Exception arg8) {
			result = new Result("error");
			arg8.printStackTrace();
		}

		this.b(response, result);
	}

	@RequestMapping({"/by007Show/f_json/findContrastAnalysis"})
	@ResponseBody
	public void an(HttpServletRequest request, HttpServletResponse response, String startDate) {
		List list = this.wf.cp(startDate);
		this.a(response, list);
	}

	@RequestMapping({"/by007Show/f_json/findContrastGraphData"})
	@ResponseBody
	public void y(HttpServletRequest request, HttpServletResponse response, String endDate, String id, String deptId) {
		Result result = null;

		try {
			String e = this.j.findByParamCode(Param.NIS_OUT_BREAK_DAYS);
			String startDate = f.formatDate(f.a(f.Z(endDate), -NumberUtils.toInt(e)));
			HashMap resultMap = new HashMap();
			List list = this.wf.p(startDate, endDate, id, deptId);
			resultMap.put("list", list);
			By007Config by007Config = this.wg.getByShowId(id);
			resultMap.put("relativePeople", by007Config.getRelativePeople());
			resultMap.put("relativeProbability", by007Config.getRelativeProbability());
			resultMap.put("relativeAll", by007Config.getRelativeAll());
			resultMap.put("startDate", startDate);
			result = new Result("success");
			result.setData(resultMap);
		} catch (Exception arg11) {
			result = new Result("error");
			arg11.printStackTrace();
		}

		this.b(response, result);
	}

	@RequestMapping({"/by007Show/f_json/getByShowId"})
	@ResponseBody
	public void ao(HttpServletRequest request, HttpServletResponse response, String id) {
		By007Config by007Config = this.wg.getByShowId(id);
		this.a(response, by007Config);
	}

	@RequestMapping({"/by007Show/f_view/toPatientList"})
	public String S(HttpServletRequest request, ModelMap modelMap) {
		return "outbreak/breakPatientList";
	}

	@RequestMapping({"/by007Show/f_view/toBloodCultureList"})
	public String T(HttpServletRequest request, ModelMap modelMap) {
		return "outbreak/bloodCultureList";
	}

	@RequestMapping({"/by007Show/f_view/toDrugResistantList"})
	public String U(HttpServletRequest request, ModelMap modelMap) {
		return "outbreak/drugResistantList";
	}

	@RequestMapping({"/by007Show/f_json/findPatientInfo"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, By007Show by007Show) {
		List list = this.wf.findPatientInfo(by007Show);
		this.a(response, list);
	}

	@RequestMapping({"/by007Show/f_json/findBloodCulturePatient"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, By007Show by007Show) {
		List list = this.wf.findBloodCulturePatient(by007Show);
		this.a(response, list);
	}

	@RequestMapping({"/by007Show/f_json/findPathogenPatient"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, By007Show by007Show) {
		List list = this.wf.findPathogenPatient(by007Show);
		this.a(response, list);
	}

	@RequestMapping({"/by007Show/f_json/findInfectionSiteList"})
	@ResponseBody
	public void d(HttpServletRequest request, HttpServletResponse response, By007Show by007Show) {
		List list = this.wf.findInfectionSiteList(by007Show);
		this.a(response, list);
	}

	@RequestMapping({"/by007Show/f_json/findSameDrugResistant"})
	@ResponseBody
	@SqlLog(p = "暴发预警--相同耐药谱患者列表")
	public void e(HttpServletRequest request, HttpServletResponse response, By007Show by007Show) {
		List list = this.wf.b(by007Show);
		this.a(response, list);
	}

	@RequestMapping({"/by007Show/f_json/exportPatientExcel"})
	@ResponseBody
	public void f(HttpServletRequest request, HttpServletResponse response, By007Show by007Show)
			throws UnsupportedEncodingException {
		String name = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());

		try {
			response.setHeader("Content-disposition",
					"attachment; filename=" + ab.a("暴发患者信息导出_", request) + name + ".xls");
			response.setContentType("application/msexcel;charset=UTF-8");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			HSSFWorkbook e = this.wf.c(by007Show);
			ServletOutputStream os = response.getOutputStream();
			e.write(os);
			os.flush();
			os.close();
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
		}

	}

	@RequestMapping({"/by007Show/f_json/exportBloodCultureExcel"})
	@ResponseBody
	public void g(HttpServletRequest request, HttpServletResponse response, By007Show by007Show)
			throws UnsupportedEncodingException {
		String name = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());

		try {
			response.setHeader("Content-disposition",
					"attachment; filename=" + ab.a("暴发血常规患者信息导出_", request) + name + ".xls");
			response.setContentType("application/msexcel;charset=UTF-8");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			HSSFWorkbook e = this.wf.d(by007Show);
			ServletOutputStream os = response.getOutputStream();
			e.write(os);
			os.flush();
			os.close();
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
		}

	}

	@RequestMapping({"/by007Show/f_json/exportDrugResistantExcel"})
	@ResponseBody
	@SqlLog(p = "暴发预警--相同耐药谱患者导出")
	public void h(HttpServletRequest request, HttpServletResponse response, By007Show by007Show)
			throws UnsupportedEncodingException {
		String name = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());

		try {
			response.setHeader("Content-disposition",
					"attachment; filename=" + ab.a("暴发相同耐药谱信息导出_", request) + name + ".xls");
			response.setContentType("application/msexcel;charset=UTF-8");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			HSSFWorkbook e = this.wf.e(by007Show);
			ServletOutputStream os = response.getOutputStream();
			e.write(os);
			os.flush();
			os.close();
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
		}

	}

	@RequestMapping({"/by007Show/f_json/exportBreakCount"})
	@ResponseBody
	public void U(HttpServletRequest request, HttpServletResponse response, String dataType, String startDate)
			throws UnsupportedEncodingException {
		String name = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());

		try {
			response.setHeader("Content-disposition", "attachment; filename="
					+ ab.a("暴发流行" + ("contrast".equals(dataType) ? "对比" : "趋势") + "分析导出_", request) + name + ".xls");
			response.setContentType("application/msexcel;charset=UTF-8");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			HSSFWorkbook e = this.wf.J(dataType, startDate);
			ServletOutputStream os = response.getOutputStream();
			e.write(os);
			os.flush();
			os.close();
		} catch (Exception arg7) {
			c.error("获取信息异常!", arg7);
		}

	}

	@RequestMapping({"/by007Show/f_view/toOutbreakList"})
	public String V(HttpServletRequest request, ModelMap modelMap) {
		Date currDate = f.getCurDate();
		modelMap.put("queryStartDate", f.a(currDate, -30));
		modelMap.put("queryEndDate", currDate);
		List list = this.wg.getAll();
		modelMap.put("itemList", list);
		return "outbreak/outbreakList";
	}
}