package com.nis.prevalence.controller;

import com.nis.comm.controller.BaseController;
import com.nis.prevalence.entity.Xl011DicPatho;
import com.nis.prevalence.service.Xl011DicPathoService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Xl011DicPathoController extends BaseController {
	private static final Logger c = Logger.getLogger(Xl011DicPathoController.class);
	@Autowired
	private Xl011DicPathoService wO;

	@RequestMapping({"/xl011DicPatho/f_json/query"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String drugTypeid, String q, int page,
			int size) {
		Xl011DicPatho xl011DicPatho = new Xl011DicPatho();
		xl011DicPatho.setPage(Integer.valueOf(page));
		xl011DicPatho.setSize(Integer.valueOf(size));
		xl011DicPatho.setIfdisp(Integer.valueOf(1));
		List list = this.wO.b(xl011DicPatho);
		this.b(response, list);
	}
}