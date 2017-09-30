package com.nis.yj.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.ab;
import com.nis.yj.entity.Yj003Standard;
import com.nis.yj.service.Yj003StandardService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Yj003StandardController extends BaseController {
	@Autowired
	private Yj003StandardService yd;

	@RequestMapping({"/yj003Standard/f_view/query"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String bactGenusId, String q, int page,
			int size) {
		Yj003Standard yj003Standard = new Yj003Standard();
		yj003Standard.setSearchString(ab.aR(q));
		yj003Standard.setPage(Integer.valueOf(page));
		yj003Standard.setSize(Integer.valueOf(size));
		MyPage standardPage = this.yd.a(yj003Standard);
		this.b(response, standardPage.getRows());
	}
}