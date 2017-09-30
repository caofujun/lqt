package com.nis.hygiene.controller;

import com.nis.comm.controller.BaseController;
import com.nis.hygiene.entity.Hw007Xjlb;
import com.nis.hygiene.service.Hw007XjlbService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Hw007XjlbController extends BaseController {
	private static final Logger c = Logger.getLogger(Hw007XjlbController.class);
	@Autowired
	private Hw007XjlbService rw;

	@RequestMapping({"/hw007Xjlb/f_json/query"})
	public void a(HttpServletRequest request, HttpServletResponse response, Hw007Xjlb hw007Xjlb) {
		List list = this.rw.query(hw007Xjlb);
		this.b(response, list);
	}
}