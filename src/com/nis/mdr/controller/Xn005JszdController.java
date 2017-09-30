package com.nis.mdr.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.ab;
import com.nis.mdr.entity.Xn005Jszd;
import com.nis.mdr.service.Xn005JszdService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Xn005JszdController extends BaseController {
	@Autowired
	private Xn005JszdService tS;

	@RequestMapping({"/xn005Jszd/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		modelMap.put("unitId", this.c(request));
		return "mdr/xn005JszdList";
	}

	@RequestMapping({"/xn005Jszd/f_json/pageQuery"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Xn005Jszd xn005Jszd) {
		MyPage page = this.tS.a(xn005Jszd);
		this.a(response, page);
	}

	@RequestMapping({"/xn005Jszd/f_view/query"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String bactGenusId, String q, int page,
			int size) {
		Xn005Jszd xn005Jszd = new Xn005Jszd();
		xn005Jszd.setSearchString(ab.aR(q));
		xn005Jszd.setPage(Integer.valueOf(page));
		xn005Jszd.setSize(Integer.valueOf(size));
		MyPage drugPage = this.tS.a(xn005Jszd);
		this.b(response, drugPage.getRows());
	}
}