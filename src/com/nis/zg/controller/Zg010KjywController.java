package com.nis.zg.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.ab;
import com.nis.zg.entity.Zg010Kjyw;
import com.nis.zg.service.Zg010KjywService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Zg010KjywController extends BaseController {
	@Autowired
	private Zg010KjywService yn;

	@RequestMapping({"/zg010Kjyw/f_view/query"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String q, int page, int size) {
		Zg010Kjyw zg010Kjyw = new Zg010Kjyw();
		zg010Kjyw.setSearchString(ab.aR(q));
		zg010Kjyw.setPage(Integer.valueOf(page));
		zg010Kjyw.setSize(Integer.valueOf(size));
		MyPage drugPage = this.yn.a(zg010Kjyw);
		this.b(response, drugPage.getRows());
	}

	@RequestMapping({"/zg010Kjyw/f_view/kjywMacth"})
	public String az(HttpServletRequest request, ModelMap modelMap) {
		return "dict/kjywMacth";
	}

	@RequestMapping({"/zg010Kjyw/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "抗菌药物匹配--标准药敏药物列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Zg010Kjyw zg010Kjyw) {
		if (zg010Kjyw.getSearchString() != null && !"".equals(zg010Kjyw.getSearchString())) {
			zg010Kjyw.setSearchString(ab.aR(zg010Kjyw.getSearchString()));
		}

		MyPage page = this.yn.a(zg010Kjyw);
		this.b(response, page);
	}
}