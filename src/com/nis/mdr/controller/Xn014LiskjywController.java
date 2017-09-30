package com.nis.mdr.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.mdr.entity.Xn014Liskjyw;
import com.nis.mdr.service.Xn014LiskjywService;
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
public class Xn014LiskjywController extends BaseController {
	@Autowired
	private Xn014LiskjywService tV;

	@RequestMapping({"/xn014Liskjyw/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "mdr/xn014LiskjywIndex";
	}

	@RequestMapping({"/xn014Liskjyw/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "药敏药物匹配--标准药敏药物列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Xn014Liskjyw xn014Liskjyw) {
		if (xn014Liskjyw.getSearchString() != null && !"".equals(xn014Liskjyw.getSearchString())) {
			xn014Liskjyw.setSearchString(ab.aR(xn014Liskjyw.getSearchString()));
		}

		MyPage page = this.tV.a(xn014Liskjyw);
		this.a(response, page);
	}

	@RequestMapping({"/xn014Liskjyw/f_json/match"})
	@ResponseBody
	@SqlLog(p = "药敏药物匹配--标准药敏药物自动匹配")
	public void J(HttpServletRequest request, HttpServletResponse response) {
		Result result = null;

		try {
			result = new Result();
			Map e = this.tV.queryMatched();
			this.tV.match();
			result.setData(e);
			result.setResult("success");
		} catch (Exception arg4) {
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/xn014Liskjyw/f_view/list"})
	public String A(HttpServletRequest request, ModelMap modelMap, String searchString)
			throws UnsupportedEncodingException {
		if (ab.isNotEmpty(searchString)) {
			modelMap.put("searchString", URLDecoder.decode(searchString, "utf-8"));
		}

		return "mdr/xn014LiskjywList";
	}

	@RequestMapping({"/xn014Liskjyw/f_json/pageQueryList"})
	@ResponseBody
	@SqlLog(p = "药敏药物匹配--LIS药敏药物列表")
	public void b(HttpServletRequest request, HttpServletResponse response, Xn014Liskjyw xn014Liskjyw) {
		MyPage page = this.tV.b(xn014Liskjyw);
		this.a(response, page);
	}

	@RequestMapping({"/xn014Liskjyw/f_view/toedit"})
	@SqlLog(p = "药敏药物匹配--LIS药敏药物详情")
	public String c(HttpServletRequest request, ModelMap modelMap, String drugid) {
		if (ab.isNotEmpty(drugid)) {
			String decode = "";

			try {
				decode = URLDecoder.decode(drugid, "utf-8");
			} catch (UnsupportedEncodingException arg5) {
				arg5.printStackTrace();
			}

			Xn014Liskjyw xn014Liskjyw = this.tV.get(decode);
			modelMap.put("xn014Liskjyw", xn014Liskjyw);
		}

		return "mdr/xn014LiskjywEdit";
	}

	@RequestMapping({"/xn014Liskjyw/f_json/save"})
	@ResponseBody
	@SqlLog(p = "药敏药物匹配--保存LIS药敏药物对照")
	public void c(HttpServletRequest request, HttpServletResponse response, Xn014Liskjyw xn014Liskjyw) {
		Result result = null;

		try {
			result = new Result();
			if (ab.isEmpty(xn014Liskjyw.getDrugid())) {
				this.tV.save(xn014Liskjyw);
			} else {
				this.tV.update(xn014Liskjyw);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}