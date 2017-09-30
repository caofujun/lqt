package com.nis.mdr.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.mdr.entity.Xn013Lisbyt;
import com.nis.mdr.service.Xn013LisbytService;
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
public class Xn013LisbytController extends BaseController {
	@Autowired
	private Xn013LisbytService tU;

	@RequestMapping({"/xn013Lisbyt/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "mdr/xn013LisbytIndex";
	}

	@RequestMapping({"/xn013Lisbyt/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "微生物匹配--标准微生物列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Xn013Lisbyt xn013Lisbyt) {
		if (xn013Lisbyt.getSearchString() != null && !"".equals(xn013Lisbyt.getSearchString())) {
			xn013Lisbyt.setSearchString(ab.aR(xn013Lisbyt.getSearchString()));
		}

		MyPage page = this.tU.a(xn013Lisbyt);
		this.a(response, page);
	}

	@RequestMapping({"/xn013Lisbyt/f_view/list"})
	public String A(HttpServletRequest request, ModelMap modelMap, String searchString)
			throws UnsupportedEncodingException {
		if (ab.isNotEmpty(searchString)) {
			modelMap.put("searchString", URLDecoder.decode(searchString, "utf-8"));
		}

		return "mdr/xn013LisbytList";
	}

	@RequestMapping({"/xn013Lisbyt/f_json/pageQueryList"})
	@ResponseBody
	@SqlLog(p = "微生物匹配--LIS病原体列表")
	public void b(HttpServletRequest request, HttpServletResponse response, Xn013Lisbyt xn013Lisbyt) {
		MyPage page = this.tU.b(xn013Lisbyt);
		this.a(response, page);
	}

	@RequestMapping({"/xn013Lisbyt/f_view/toedit"})
	@SqlLog(p = "微生物匹配--LIS病原体详情")
	public String c(HttpServletRequest request, ModelMap modelMap, String lisBytid) {
		if (ab.isNotEmpty(lisBytid)) {
			String decode = "";

			try {
				decode = URLDecoder.decode(lisBytid, "utf-8");
			} catch (UnsupportedEncodingException arg5) {
				arg5.printStackTrace();
			}

			Xn013Lisbyt xn013Lisbyt = this.tU.get(decode);
			modelMap.put("xn013Lisbyt", xn013Lisbyt);
		}

		return "mdr/xn013LisbytEdit";
	}

	@RequestMapping({"/xn013Lisbyt/f_json/match"})
	@ResponseBody
	@SqlLog(p = "微生物匹配--微生物自动匹配")
	public void J(HttpServletRequest request, HttpServletResponse response) {
		Result result = null;

		try {
			result = new Result();
			Map e = this.tU.queryMatched();
			this.tU.match();
			result.setData(e);
			result.setResult("success");
		} catch (Exception arg4) {
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/xn013Lisbyt/f_json/save"})
	@ResponseBody
	@SqlLog(p = "微生物匹配--新增LIS病原体")
	public void c(HttpServletRequest request, HttpServletResponse response, Xn013Lisbyt xn013Lisbyt) {
		Result result = null;

		try {
			result = new Result();
			if (ab.isEmpty(xn013Lisbyt.getLisBytid())) {
				this.tU.save(xn013Lisbyt);
			} else {
				this.tU.update(xn013Lisbyt);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}