package com.nis.outbreak.controller;

import com.nis.access.entity.AcAccount;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.outbreak.entity.By007Bfjl;
import com.nis.outbreak.service.By007BfjlService;
import com.nis.outbreak.service.By007ShowService;
import com.nis.param.service.SysParamService;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class By007BfjlController extends BaseController {
	private static final Logger c = Logger.getLogger(By007BfjlController.class);
	@Autowired
	private By007BfjlService we;
	@Autowired
	private By007ShowService wf;
	@Autowired
	private SysParamService j;

	@RequestMapping({"/by007Bfjl/f_view/toList"})
	public String a(HttpServletRequest request, ModelMap modelMap, Date startDate, Date endDate) {
		if (endDate == null) {
			endDate = new Date();
		}

		modelMap.put("queryEndDate", endDate);
		if (startDate == null) {
			String itemList = this.j.findByParamCode(Param.NIS_OUT_BREAK_DAYS);
			startDate = f.a(endDate, -NumberUtils.toInt(itemList));
		}

		modelMap.put("queryStartDate", startDate);
		List itemList1 = this.wf.getAll();
		modelMap.put("itemList", itemList1);
		return "outbreak/outbreakList";
	}

	@RequestMapping({"/by007Bfjl/f_view/toMemo"})
	public String N(HttpServletRequest request, ModelMap modelMap, String id) {
		By007Bfjl by007Bfjl = this.we.get(id);
		modelMap.put("by007Bfjl", by007Bfjl);
		return "outbreak/outbreakMemo";
	}

	@RequestMapping({"/by007Bfjl/f_json/findList"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, By007Bfjl by007Bfjl) {
		if (StringUtils.isNotBlank(by007Bfjl.getId()) && by007Bfjl.getId().split(",").length > 0) {
			String[] page = by007Bfjl.getId().split(",");
			ArrayList idList = new ArrayList();
			String[] arg8 = page;
			int arg7 = page.length;

			for (int arg6 = 0; arg6 < arg7; ++arg6) {
				String id = arg8[arg6];
				idList.add(id);
			}

			by007Bfjl.setIdList(idList);
			by007Bfjl.setId((String) null);
		}

		MyPage arg9 = this.we.b(by007Bfjl);
		this.a(response, arg9);
	}

	@RequestMapping({"/by007Bfjl/f_json/findListByDept"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, By007Bfjl by007Bfjl) {
		int count;
		if (StringUtils.isNotBlank(by007Bfjl.getAuditFlagStr()) && by007Bfjl.getAuditFlagStr().split(",").length > 0) {
			String[] by007BfjlList = by007Bfjl.getAuditFlagStr().split(",");
			ArrayList page = new ArrayList();
			String[] by007Bfjl2 = by007BfjlList;
			count = by007BfjlList.length;

			for (int by007BfjlFooter = 0; by007BfjlFooter < count; ++by007BfjlFooter) {
				String by007BfjlFooterList = by007Bfjl2[by007BfjlFooter];
				page.add(by007BfjlFooterList);
			}

			by007Bfjl.setAuditFlagList(page);
			by007Bfjl.setAuditFlag((Integer) null);
		} else if (StringUtils.isNotBlank(by007Bfjl.getAuditFlagStr())) {
			by007Bfjl.setAuditFlag(Integer.valueOf(by007Bfjl.getAuditFlagStr()));
		}

		List arg10 = this.we.findListByDept(by007Bfjl);
		MyPage arg11 = new MyPage(1, arg10.size(), arg10.size(), arg10);
		ArrayList arg12 = new ArrayList();
		By007Bfjl arg13 = new By007Bfjl();
		count = 0;

		By007Bfjl arg14;
		for (Iterator arg9 = arg10.iterator(); arg9.hasNext(); count += arg14.getDeptCount().intValue()) {
			arg14 = (By007Bfjl) arg9.next();
		}

		arg13.setDeptName("合计");
		arg13.setDeptCount(Integer.valueOf(count));
		arg12.add(arg13);
		arg11.setFooter(arg12);
		this.a(response, arg11);
	}

	@RequestMapping({"/by007Bfjl/f_json/updReadFlag"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, By007Bfjl by007Bfjl) {
		Result result = null;

		try {
			if (StringUtils.isNotBlank(by007Bfjl.getId())) {
				AcAccount e = (AcAccount) this.b(request);
				this.we.a(by007Bfjl, e);
				result = new Result("success");
				result.setData(by007Bfjl);
			} else {
				result = new Result("error", "关键参数丢失");
			}
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/by007Bfjl/f_json/auditflag/set"})
	@ResponseBody
	public void d(HttpServletRequest request, HttpServletResponse response, By007Bfjl by007Bfjl) {
		Result result = null;

		try {
			if (StringUtils.isNotBlank(by007Bfjl.getId())) {
				AcAccount e = (AcAccount) this.b(request);
				by007Bfjl.setAuditDate(f.getCurDate());
				by007Bfjl.setAuditId(e.getUserId());
				by007Bfjl.setAuditName(e.getRealname());
				this.we.updAuditFlag(by007Bfjl);
				result = new Result("success");
				result.setData(by007Bfjl);
			} else {
				result = new Result("error", "关键参数丢失");
			}
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/by007Bfjl/f_json/memo/set"})
	@ResponseBody
	public void e(HttpServletRequest request, HttpServletResponse response, By007Bfjl by007Bfjl) {
		Result result = null;

		try {
			if (StringUtils.isNotBlank(by007Bfjl.getId())) {
				this.we.updMemo(by007Bfjl);
				result = new Result("success");
				result.setData(by007Bfjl);
			} else {
				result = new Result("error", "关键参数丢失");
			}
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/by007Show/f_json/exportOutbreakExcel"})
	@ResponseBody
	public void f(HttpServletRequest request, HttpServletResponse response, By007Bfjl by007Bfjl)
			throws UnsupportedEncodingException {
		String name = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());

		try {
			response.setHeader("Content-disposition",
					"attachment; filename=" + ab.a("暴发预警记录导出_", request) + name + ".xls");
			response.setContentType("application/msexcel;charset=UTF-8");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			HSSFWorkbook e = this.we.c(by007Bfjl);
			ServletOutputStream os = response.getOutputStream();
			e.write(os);
			os.flush();
			os.close();
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
		}

	}
}