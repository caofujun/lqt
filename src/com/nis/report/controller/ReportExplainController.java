package com.nis.report.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import com.nis.report.entity.SysReportExplain;
import com.nis.report.service.SysReportExplainService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReportExplainController extends BaseController {
	@Autowired
	private SysReportExplainService xE;
	@Autowired
	private SysDictService p;

	@RequestMapping({"/reportExplain/f_view/index"})
	public String p(HttpServletRequest request, ModelMap modelMap, String reportName) {
		if (ab.isNotEmpty(reportName)) {
			reportName = reportName.substring(reportName.indexOf("/") + 1);
			List reportExplainList = this.xE.findByName(reportName);
			modelMap.put("reportExplainList", reportExplainList);
		}

		return "report/sysReportExplainIndex";
	}

	@RequestMapping({"/reportExplain/f_view/toedit"})
	@SqlLog(p = "报表说明管理--报表说明详情")
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (ab.isNotEmpty(id)) {
			SysReportExplain reportExplain = this.xE.get(id);
			if (reportExplain != null) {
				SysDict dict = this.p.j("report_type", reportExplain.getReportName(), (String) null);
				reportExplain.setReportType(dict.getParentCode());
			}

			modelMap.put("reportExplain", reportExplain);
		}

		return "report/sysReportExplainEdit";
	}

	@RequestMapping({"/reportExplain/f_view/List"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "report/sysReportExplainList";
	}

	@RequestMapping({"/reportExplain/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "报表说明管理--报表说明列表")
	public void a(HttpServletRequest request, HttpServletResponse response, SysReportExplain reportExplain) {
		MyPage page = this.xE.a(reportExplain);
		this.b(response, page);
	}

	@RequestMapping({"/reportExplain/f_json/save"})
	@ResponseBody
	@SqlLog(p = "报表说明管理--保存报表说明")
	public void b(HttpServletRequest request, HttpServletResponse response, SysReportExplain reportExplain) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isBlank(reportExplain.getReportId())) {
				this.xE.save(reportExplain);
			} else {
				this.xE.update(reportExplain);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			arg5.printStackTrace();
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/reportExplain/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "报表说明管理--删除报表说明")
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.xE.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}