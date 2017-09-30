package com.nis.mdr.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.ab;
import com.nis.mdr.entity.Xn006Kjywfl;
import com.nis.mdr.service.Xn006KjywflService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Xn006KjywflController extends BaseController {
	@Autowired
	private Xn006KjywflService tT;

	@RequestMapping({"/xn006Kjywfl/f_view/query"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String q, int page, int size) {
		Xn006Kjywfl xn006Kjywfl = new Xn006Kjywfl();
		xn006Kjywfl.setSearchString(ab.aR(q));
		xn006Kjywfl.setPage(Integer.valueOf(page));
		xn006Kjywfl.setSize(Integer.valueOf(size));
		MyPage drugPage = this.tT.a(xn006Kjywfl);
		this.b(response, drugPage.getRows());
	}
}