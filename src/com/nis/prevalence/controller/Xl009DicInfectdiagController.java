package com.nis.prevalence.controller;

import com.nis.comm.controller.BaseController;
import com.nis.prevalence.entity.Xl009DicInfectdiag;
import com.nis.prevalence.service.Xl009DicInfectdiagService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Xl009DicInfectdiagController extends BaseController {
	private static final Logger c = Logger.getLogger(Xl009DicInfectdiagController.class);
	@Autowired
	private Xl009DicInfectdiagService wM;

	@RequestMapping({"/xl009DicInfectdiag/f_json/query"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String drugTypeid, String q, int page,
			int size) {
		Xl009DicInfectdiag xl009DicInfectdiag = new Xl009DicInfectdiag();
		xl009DicInfectdiag.setPage(Integer.valueOf(page));
		xl009DicInfectdiag.setSize(Integer.valueOf(size));
		List list = this.wM.b(xl009DicInfectdiag);
		this.b(response, list);
	}

	@RequestMapping({"/xl009DicInfectdiag/f_json/querytree"})
	@ResponseBody
	public void T(HttpServletRequest request, HttpServletResponse response) {
		List list = this.wM.queryTree();
		this.b(response, list);
	}
}