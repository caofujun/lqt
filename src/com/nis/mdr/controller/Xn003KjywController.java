package com.nis.mdr.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.ab;
import com.nis.mdr.entity.Xn003Kjyw;
import com.nis.mdr.service.Xn003KjywService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Xn003KjywController extends BaseController {
	private static final Logger c = Logger.getLogger(Xn003KjywController.class);
	@Autowired
	private Xn003KjywService tR;

	@RequestMapping({"/xn003Kjyw/f_view/query"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String bactGenusId, String q, int page,
			int size) {
		Xn003Kjyw xn003Kjyw = new Xn003Kjyw();
		xn003Kjyw.setSearchString(ab.aR(q));
		xn003Kjyw.setPage(Integer.valueOf(page));
		xn003Kjyw.setSize(Integer.valueOf(size));
		MyPage drugPage = this.tR.a(xn003Kjyw);
		this.b(response, drugPage.getRows());
	}

	@RequestMapping({"/xn003Kjyw/f_view/query/byt"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, String pathogenId, String q, int page,
			int size) {
		Xn003Kjyw xn003Kjyw = new Xn003Kjyw();
		xn003Kjyw.setSearchString(ab.aR(q));
		xn003Kjyw.setPathogenId(pathogenId);
		List drugList = this.tR.b(xn003Kjyw);
		this.b(response, drugList);
	}

	@RequestMapping({"/xn003Kjyw/f_json/getAll"})
	@ResponseBody
	public void w(HttpServletRequest request, HttpServletResponse response) {
		List xn003Kjyws = this.tR.getAll();
		this.a(response, xn003Kjyws);
	}
}