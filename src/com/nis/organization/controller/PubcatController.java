package com.nis.organization.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.organization.entity.Pubcat;
import com.nis.organization.service.PubcatService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PubcatController extends BaseController {
	private static final Logger c = Logger.getLogger(PubcatController.class);
	@Autowired
	private PubcatService vU;

	@RequestMapping({"/pubcat/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "organization/pubcatList";
	}

	@RequestMapping({"/pubcat/f_json/pageQuery"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Pubcat pubcat) {
		MyPage page = this.vU.a(pubcat);
		this.b(response, page);
	}

	@RequestMapping({"/pubcat/f_view/toedit"})
	public String b(HttpServletRequest request, ModelMap modelMap, Long id) {
		if (id != null) {
			Pubcat pubcat = this.vU.get(id);
			modelMap.put("pubcat", pubcat);
		}

		return "organization/pubcatEdit";
	}

	@RequestMapping({"/pubcat/f_json/save"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, Pubcat pubcat) {
		Result result = null;

		try {
			result = new Result();
			if (pubcat.getCatid() == null) {
				this.vU.save(pubcat);
			} else {
				this.vU.update(pubcat);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/pubcat/f_json/delete"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, Long id) {
		Result result = null;

		try {
			result = new Result();
			this.vU.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/pubcat/f_json/getAll"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, Pubcat pubcat) {
		List page = this.vU.getAll();
		this.b(response, page);
	}
}