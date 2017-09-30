package com.nis.yj.controller;

import com.nis.access.entity.AcAccount;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.yj.entity.Yj003Synonym;
import com.nis.yj.service.Yj003SynonymService;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Yj003SynonymController extends BaseController {
	@Autowired
	private Yj003SynonymService ye;

	@RequestMapping({"/yj003Synonym/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "yj/yj003SynonymIndex";
	}

	@RequestMapping({"/yj003Synonym/f_json/pageQuery"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Yj003Synonym yj003Synonym) {
		MyPage page = this.ye.a(yj003Synonym);
		this.b(response, page);
	}

	@RequestMapping({"/yj003Synonym/f_json/match"})
	@ResponseBody
	public void J(HttpServletRequest request, HttpServletResponse response) {
		Result result = null;

		try {
			result = new Result();
			Map e = this.ye.queryMatched();
			this.ye.match();
			result.setData(e);
			result.setResult("success");
		} catch (Exception arg4) {
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/yj003Synonym/f_view/list"})
	public String A(HttpServletRequest request, ModelMap modelMap, String searchString)
			throws UnsupportedEncodingException {
		if (ab.isNotEmpty(searchString)) {
			modelMap.put("searchString", URLDecoder.decode(searchString, "utf-8"));
		}

		return "yj/yj003SynonymList";
	}

	@RequestMapping({"/yj003Synonym/f_json/pageQueryList"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, Yj003Synonym yj003Synonym) {
		MyPage page = this.ye.b(yj003Synonym);
		this.b(response, page);
	}

	@RequestMapping({"/yj003Synonym/f_view/toedit"})
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (ab.isNotEmpty(id)) {
			Yj003Synonym yj003Synonym = this.ye.get(id);
			modelMap.put("yj003Synonym", yj003Synonym);
		}

		return "yj/yj003SynonymEdit";
	}

	@RequestMapping({"/yj003Synonym/f_json/save"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, Yj003Synonym yj003Synonym) {
		Result result = null;

		try {
			result = new Result();
			AcAccount e = (AcAccount) this.b(request);
			yj003Synonym.setCompareTime(new Date());
			yj003Synonym.setCompareUserId(e.getRealname());
			if (ab.isEmpty(yj003Synonym.getId())) {
				this.ye.save(yj003Synonym);
			} else {
				this.ye.update(yj003Synonym);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}