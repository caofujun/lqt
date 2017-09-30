package com.nis.zb.controller;

import com.nis.access.entity.AcAccount;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.ah;
import com.nis.comm.utils.f;
import com.nis.dict.service.SysDictService;
import com.nis.log.service.SysLogService;
import com.nis.param.service.SysParamService;
import com.nis.prevalence.service.Xl001BrxxService;
import com.nis.zb.entity.ZbRecord;
import com.nis.zb.service.ZbRecordService;
import com.nis.zb.service.ZbXhlService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

@Controller
public class ZbRecordController extends BaseController {
	private static final Logger c = Logger.getLogger(ZbRecordController.class);
	@Autowired
	private ZbRecordService yj;
	@Autowired
	private SysParamService j;
	@Autowired
	private SysLogService aV;
	@Autowired
	private SysDictService p;
	@Autowired
	private ZbXhlService yk;
	@Autowired
	private FreeMarkerConfig rn;
	@Autowired
	private Xl001BrxxService wH;

	@RequestMapping({"/zbRecord/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		modelMap.put("years", f.getYears());
		modelMap.put("months", f.getMonths());
		modelMap.put("curYear", Integer.valueOf(f.getCurYear()));
		modelMap.put("curMonth", Integer.valueOf(f.getCurMonth()));
		modelMap.put("zbURL", this.j.findByParamCode(Param.NIS_SYS_ZB_URL));
		modelMap.put("channel", this.j.findByParamCode(Param.NIS_SYS_ZB_CHANNEL));
		return "zb/zbRecordList";
	}

	@RequestMapping({"/zbRecord/f_view/xhlList"})
	public String B(HttpServletRequest request, ModelMap modelMap) {
		modelMap.put("startDate", f.formatDate(f.getYearFirst(), "yyyy-MM"));
		modelMap.put("endDate", f.formatDate(f.getYearLast(), "yyyy-MM"));
		modelMap.put("xhlZbURL", this.j.findByParamCode(Param.NIS_XHL_ZB_URL));
		modelMap.put("channel", this.j.findByParamCode(Param.NIS_SYS_ZB_CHANNEL));
		List xl001BrxxList = this.wH.findVoteDateList();
		modelMap.put("xl001BrxxList", xl001BrxxList);
		return "zb/zbXhlList";
	}

	@RequestMapping({"/zbRecord/f_view/exportZbValidate"})
	@ResponseBody
	public void ab(HttpServletRequest request, HttpServletResponse response, String startDate, String endDate) {
		Date start = f.a(f.k(startDate, "yyyy-MM"), true);
		Date end = f.b(f.k(endDate, "yyyy-MM"), true);
		Result result = this.yk.g(start, end);
		result.setData((Object) null);
		this.a(response, result);
	}

	@RequestMapping({"/zbRecord/f_view/exportZbHtml"})
	@ResponseBody
	public void ac(HttpServletRequest request, HttpServletResponse response, String startDate, String endDate)
			throws IOException {
		String fileName = "现患率直报.html";
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain; charset=utf-8");
		response.setHeader("content-disposition",
				"attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO-8859-1"));
		Configuration configuration = this.rn.getConfiguration();
		configuration.setDefaultEncoding("UTF-8");
		Template t = null;
		BufferedWriter out = null;
		HashMap dataMap = new HashMap();
		Date start = f.a(f.k(startDate, "yyyy-MM"), true);
		Date end = f.b(f.k(endDate, "yyyy-MM"), true);
		Result result = this.yk.g(start, end);
		String[] data = (String[]) result.getData();

		try {
			t = configuration.getTemplate("homepage/template/zbModel.ftl");
			out = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			dataMap.put("xhlZbURL", this.j.findByParamCode(Param.NIS_XHL_ZB_URL));
			dataMap.put("channel", this.j.findByParamCode(Param.NIS_SYS_ZB_CHANNEL));
			dataMap.put("dicOffice", data[0]);
			dataMap.put("patientMain", data[1]);
			dataMap.put("infectInfo", data[2]);
			dataMap.put("pathoInfo", data[3]);
			dataMap.put("antibInfo", data[4]);
			t.process(dataMap, out);
		} catch (TemplateException arg24) {
			arg24.printStackTrace();
		} catch (IOException arg25) {
			arg25.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException arg23) {
				arg23.printStackTrace();
			}

		}

	}

	private void b(Map<String, Object> dataMap, String startDate, String endDate) {
		Date start = f.a(f.k(startDate, "yyyy-MM"), true);
		Date end = f.b(f.k(endDate, "yyyy-MM"), true);
		this.yk.g(start, end);
	}

	@RequestMapping({"/zbRecord/f_view/zbLogList"})
	public String a(HttpServletRequest request, ModelMap modelMap, ZbRecord zbRecord) {
		modelMap.put("itemCode", zbRecord.getItemCode());
		return "zb/zbLogList";
	}

	@RequestMapping({"/zbRecord/f_json/getRate"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, ZbRecord zbRecord) {
		try {
			zbRecord.setStartDate();
			zbRecord.setEndDate();
		} catch (ParseException arg4) {
			arg4.printStackTrace();
		}

		ZbRecord findZbRate = this.yj.e(zbRecord);
		this.a(response, findZbRate);
	}

	@RequestMapping({"/zbRecord/f_json/findLogList"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, ZbRecord zbRecord) {
		List findZbLogList = this.yj.b(zbRecord);
		this.a(response, findZbLogList);
	}

	@RequestMapping({"/zbRecord/f_view/jdtshow"})
	public String ay(HttpServletRequest request, ModelMap modelMap) {
		return "zb/jdtshow";
	}

	@RequestMapping({"/zbRecord/f_json/xhlPageQuery"})
	@ResponseBody
	public void H(HttpServletRequest request, HttpServletResponse response, String startDate, String endDate) {
		Date start = f.a(f.k(startDate, "yyyy-MM"), true);
		Date end = f.b(f.k(endDate, "yyyy-MM"), true);
		List zbList = this.yj.findZbxhlList(start, end);
		this.a(response, zbList);
	}

	@RequestMapping({"/zbRecord/f_json/pageQuery"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, ZbRecord zbRecord) {
		zbRecord.setSize(Integer.valueOf(100));
		if (zbRecord.getReportStartYear() == null) {
			zbRecord.setReportStartYear(new Long((long) f.getCurYear()));
			zbRecord.setReportStartMonth(new Long(1L));
			zbRecord.setReportEndYear(new Long((long) f.getCurYear()));
			zbRecord.setReportEndMonth(new Long((long) (f.getCurMonth() == 1 ? 1 : f.getCurMonth() - 1)));
		}

		MyPage page = this.yj.a(zbRecord);
		this.a(response, page);
	}

	@RequestMapping({"/zbRecord/f_json/findXhl"})
	@ResponseBody
	public void I(HttpServletRequest request, HttpServletResponse response, String startDate, String endDate) {
		Date start = f.a(f.k(startDate, "yyyy-MM"), true);
		Date end = f.b(f.k(endDate, "yyyy-MM"), true);
		Result result = this.yk.g(start, end);
		this.a(response, result);
	}

	@RequestMapping({"/zbRecord/f_json/findReportDataByItem"})
	@ResponseBody
	public void d(HttpServletRequest request, HttpServletResponse response, ZbRecord zbRecord) {
		String[] datas = new String[3];

		try {
			zbRecord.setStartDate();
			zbRecord.setEndDate();
			zbRecord.setOrclBegNum(Integer.valueOf(1));
			zbRecord.setOrclEndNum(Integer.valueOf(100));
			datas = this.yj.d(zbRecord);
			System.out.println(datas[1]);
		} catch (Exception arg5) {
			datas[0] = "";
			datas[1] = "";
		}

		this.a(response, datas);
	}

	@RequestMapping({"/zbRecord/f_json/zbResult"})
	@ResponseBody
	public void e(HttpServletRequest request, HttpServletResponse response, ZbRecord zbRecord) {
		try {
			zbRecord.setStartDate();
			zbRecord.setEndDate();
		} catch (ParseException arg8) {
			arg8.printStackTrace();
		}

		AcAccount account = (AcAccount) this.b(request);
		String itemName = this.p.k("zb_type", zbRecord.getItemCode(), (String) null);
		String[] split = zbRecord.getReportResult().split(",");
		this.aV.a(ah.iz, itemName, "wlzb" + zbRecord.getItemCode(), itemName + "直报了" + split.length + "条记录!",
				account.getUsername());

		try {
			if ("0".equals(zbRecord.getItemCode())) {
				this.yj.cX(zbRecord.getReportResult());
			}

			if ("1".equals(zbRecord.getItemCode())) {
				this.yj.cN(zbRecord.getReportResult());
				this.yj.cT(zbRecord.getReportResult());
				this.yj.dg(zbRecord.getReportResult());
				this.yj.dh(zbRecord.getReportResult());
			}

			if ("2".equals(zbRecord.getItemCode())) {
				this.yj.cM(zbRecord.getReportResult());
			}

			if ("5".equals(zbRecord.getItemCode())) {
				this.yj.cL(zbRecord.getReportResult());
				this.yj.cU(zbRecord.getReportResult());
			}

			if ("6".equals(zbRecord.getItemCode())) {
				this.yj.cO(zbRecord.getReportResult());
				this.yj.cV(zbRecord.getReportResult());
				this.yj.cW(zbRecord.getReportResult());
			}

			if ("7".equals(zbRecord.getItemCode())) {
				this.yj.cP(zbRecord.getReportResult());
				this.yj.cY(zbRecord.getReportResult());
				this.yj.cZ(zbRecord.getReportResult());
				this.yj.da(zbRecord.getReportResult());
				this.yj.b(zbRecord.getReportResult(), zbRecord.getStartDate(), zbRecord.getEndDate());
				this.yj.a(zbRecord.getReportResult(), zbRecord.getStartDate(), zbRecord.getEndDate());
			}

			if ("9".equals(zbRecord.getItemCode())) {
				this.yj.cQ(zbRecord.getReportResult());
				this.yj.db(zbRecord.getReportResult());
				this.yj.dc(zbRecord.getReportResult());
				this.yj.dd(zbRecord.getReportResult());
				this.yj.c(zbRecord.getReportResult(), zbRecord.getStartDate(), zbRecord.getEndDate());
			}

			if ("10".equals(zbRecord.getItemCode())) {
				this.yj.cR(zbRecord.getReportResult());
				this.yj.de(zbRecord.getReportResult());
			}

			if ("11".equals(zbRecord.getItemCode())) {
				this.yj.cS(zbRecord.getReportResult());
				this.yj.df(zbRecord.getReportResult());
			}

			if ("12".equals(zbRecord.getItemCode())) {
				this.yj.cS(zbRecord.getReportResult());
				this.yj.df(zbRecord.getReportResult());
			}

			if ("13".equals(zbRecord.getItemCode())) {
				this.yj.cS(zbRecord.getReportResult());
				this.yj.df(zbRecord.getReportResult());
			}
		} catch (Exception arg7) {
			arg7.printStackTrace();
		}

		itemName = itemName.replace("\"", "").replace("\"", "");
		this.a(response, itemName);
	}
}