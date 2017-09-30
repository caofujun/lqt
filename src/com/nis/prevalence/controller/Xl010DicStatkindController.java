package com.nis.prevalence.controller;

import com.nis.comm.controller.BaseController;
import com.nis.prevalence.entity.Xl010DicStatkind;
import com.nis.prevalence.service.Xl010DicStatkindService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Xl010DicStatkindController extends BaseController {
	private static final Logger c = Logger.getLogger(Xl010DicStatkindController.class);
	@Autowired
	private Xl010DicStatkindService wN;

	@RequestMapping({"/xl010DicStatkind/f_json/query"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String drugTypeid, String q, int page,
			int size) {
		Xl010DicStatkind xl010DicStatkind = new Xl010DicStatkind();
		xl010DicStatkind.setPage(Integer.valueOf(page));
		xl010DicStatkind.setSize(Integer.valueOf(size));
		List list = this.wN.b(xl010DicStatkind);
		this.b(response, list);
	}
}