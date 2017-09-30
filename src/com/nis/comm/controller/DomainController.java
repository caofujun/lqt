package com.nis.comm.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.enums.Param;
import com.nis.param.service.SysParamService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DomainController extends BaseController {
	@Autowired
	private SysParamService j;

	@RequestMapping({"/domain/f_view/toDomainDis"})
	public String k(HttpServletRequest request, ModelMap modelMap, String urlPrefix) {
		modelMap.put("urlPrefix", urlPrefix);
		return "comm/domain_dis";
	}

	@RequestMapping({"/domain/f_view/toReportDomainDis"})
	public String l(HttpServletRequest request, ModelMap modelMap, String prefixType) {
		String reportUrl;
		if ("self".equals(prefixType)) {
			reportUrl = this.j.findByParamCode(Param.NIS_HTTP_URL);
			modelMap.put("urlPrefix", reportUrl);
		} else {
			reportUrl = this.j.findByParamCode(Param.NIS_REPORT_URL);
			reportUrl.replaceAll("~-~", "?");
			modelMap.put("urlPrefix", reportUrl);
		}

		return "comm/domain_dis";
	}
}