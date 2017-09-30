package com.nis.zg.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.zg.entity.Zg027LisbbPp;
import com.nis.zg.service.Zg027LisbbPpService;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Zg027LisbbPpController extends BaseController {
	@Autowired
	private Zg027LisbbPpService bC;

	@RequestMapping({"/zg027LisbbPp/f_view/dataMacth"})
	public String aB(HttpServletRequest request, ModelMap modelMap) {
		return "dict/dataMacth";
	}

	@RequestMapping({"/zg027LisbbPp/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "dict/zg027LisbbPpIndex";
	}

	@RequestMapping({"/zg027LisbbPp/f_json/match"})
	@ResponseBody
	@SqlLog(p = "标本匹配--标本自动匹配")
	public void J(HttpServletRequest request, HttpServletResponse response) {
		Result result = null;

		try {
			result = new Result();
			Map e = this.bC.queryMatched();
			this.bC.match();
			result.setData(e);
			result.setResult("success");
		} catch (Exception arg4) {
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/zg027LisbbPp/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "标本匹配--标准标本列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Zg027LisbbPp zg027LisbbPp) {
		if (zg027LisbbPp.getSearchString() != null && !"".equals(zg027LisbbPp.getSearchString())) {
			zg027LisbbPp.setSearchString(ab.aR(zg027LisbbPp.getSearchString()));
		}

		MyPage page = this.bC.a(zg027LisbbPp);
		this.b(response, page);
	}

	@RequestMapping({"/zg027LisbbPp/f_view/list"})
	public String A(HttpServletRequest request, ModelMap modelMap, String searchString)
			throws UnsupportedEncodingException {
		if (ab.isNotEmpty(searchString)) {
			modelMap.put("searchString", URLDecoder.decode(searchString, "utf-8"));
		}

		return "dict/zg027LisbbPpList";
	}

	@RequestMapping({"/zg027LisbbPp/f_json/pageQueryList"})
	@ResponseBody
	@SqlLog(p = "标本匹配--LIS标本列表")
	public void b(HttpServletRequest request, HttpServletResponse response, Zg027LisbbPp zg027LisbbPp) {
		MyPage page = this.bC.b(zg027LisbbPp);
		this.b(response, page);
	}

	@RequestMapping({"/zg027LisbbPp/f_view/toedit"})
	public String c(HttpServletRequest request, ModelMap modelMap, String bbppId) throws UnsupportedEncodingException {
		if (ab.isNotEmpty(bbppId)) {
			bbppId = URLDecoder.decode(bbppId, "utf-8");
			Zg027LisbbPp zg027LisbbPp = this.bC.get(bbppId);
			modelMap.put("zg027LisbbPp", zg027LisbbPp);
		}

		return "dict/zg027LisbbPpEdit";
	}

	@RequestMapping({"/zg027LisbbPp/f_json/save"})
	@ResponseBody
	@SqlLog(p = "标本匹配--保存：LIS标本匹配信息")
	public void c(HttpServletRequest request, HttpServletResponse response, Zg027LisbbPp zg027LisbbPp) {
		Result result = null;

		try {
			result = new Result();
			if (ab.isEmpty(zg027LisbbPp.getBbppId())) {
				this.bC.save(zg027LisbbPp);
			} else {
				this.bC.update(zg027LisbbPp);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}