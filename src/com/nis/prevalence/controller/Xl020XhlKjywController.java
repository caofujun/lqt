package com.nis.prevalence.controller;

import com.nis.comm.controller.BaseController;
import com.nis.prevalence.entity.Xl020XhlKjyw;
import com.nis.prevalence.service.Xl020XhlKjywService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Xl020XhlKjywController extends BaseController {
	private static final Logger c = Logger.getLogger(Xl020XhlKjywController.class);
	@Autowired
	private Xl020XhlKjywService wT;

	@RequestMapping({"/xl020XhlKjyw/f_json/query"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String drugTypeid, String q, int page,
			int size) {
		Xl020XhlKjyw xl020XhlKjyw = new Xl020XhlKjyw();
		xl020XhlKjyw.setSearchString(q);
		xl020XhlKjyw.setPage(Integer.valueOf(page));
		xl020XhlKjyw.setSize(Integer.valueOf(size));
		List list = this.wT.b(xl020XhlKjyw);
		this.b(response, list);
	}
}