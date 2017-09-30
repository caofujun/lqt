package com.nis.dict.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.ab;
import com.nis.dict.entity.NyBbDict;
import com.nis.dict.service.NyBbDictService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NyBbDictController extends BaseController {
	@Autowired
	private NyBbDictService bB;

	@RequestMapping({"/nyBbDict/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "dict/zg027LisbbPpIndex";
	}

	@RequestMapping({"/nyBbDict/f_json/pageQuery"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, NyBbDict nyBbDict) {
		MyPage page = this.bB.a(nyBbDict);
		this.b(response, page);
	}

	@RequestMapping({"/nyBbDict/f_json/query"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String bactGenusId, String q, int page,
			int size) {
		NyBbDict nyBbDict = new NyBbDict();
		nyBbDict.setSearchString(ab.aR(q));
		nyBbDict.setPage(Integer.valueOf(page));
		nyBbDict.setSize(Integer.valueOf(size));
		MyPage nyBbDictPage = this.bB.a(nyBbDict);
		this.b(response, nyBbDictPage.getRows());
	}

	@RequestMapping({"/nyBbDict/f_json/queryList"})
	@ResponseBody
	public void r(HttpServletRequest request, HttpServletResponse response, String bactGenusId) {
		NyBbDict nyBbDict = new NyBbDict();
		nyBbDict.setPage(Integer.valueOf(1));
		nyBbDict.setSize(Integer.valueOf(200));
		MyPage nyBbDictPage = this.bB.a(nyBbDict);
		this.b(response, nyBbDictPage.getRows());
	}
}