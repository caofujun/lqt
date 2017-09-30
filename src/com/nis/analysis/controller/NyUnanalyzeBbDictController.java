package com.nis.analysis.controller;

import com.nis.analysis.entity.NyUnanalyzeBbDict;
import com.nis.analysis.service.NyUnanalyzeBbDictService;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NyUnanalyzeBbDictController extends BaseController {
	private static final Logger c = Logger.getLogger(NyUnanalyzeBbDictController.class);
	@Autowired
	private NyUnanalyzeBbDictService W;

	@RequestMapping({"/nyUnanalyzeBbDict/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "analysis/nyUnanalyzeBbDictList";
	}

	@RequestMapping({"/nyUnanalyzeBbDict/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "不分析标本管理--不分析标本列表")
	public void a(HttpServletRequest request, HttpServletResponse response, NyUnanalyzeBbDict nyUnanalyzeBbDict) {
		if (nyUnanalyzeBbDict.getSearchString() != null && !"".equals(nyUnanalyzeBbDict.getSearchString())) {
			nyUnanalyzeBbDict.setSearchString(ab.aR(nyUnanalyzeBbDict.getSearchString()));
		}

		MyPage page = this.W.a(nyUnanalyzeBbDict);
		this.a(response, page);
	}

	@RequestMapping({"/nyUnanalyzeBbDict/f_view/toedit"})
	@SqlLog(p = "不分析标本管理--不分析标本详情")
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (ab.isNotEmpty(id)) {
			NyUnanalyzeBbDict nyUnanalyzeBbDict = this.W.get(id);
			modelMap.put("nyUnanalyzeBbDict", nyUnanalyzeBbDict);
			modelMap.put("flag", "1");
		}

		return "analysis/nyUnanalyzeBbDictEdit";
	}

	@RequestMapping({"/nyUnanalyzeBbDict/f_json/save"})
	@ResponseBody
	@SqlLog(p = "不分析标本管理--保存不分析标本")
	public void a(HttpServletRequest request, HttpServletResponse response, NyUnanalyzeBbDict nyUnanalyzeBbDict,
			String flag) {
		Result result = null;

		try {
			result = new Result();
			if (ab.isEmpty(flag)) {
				this.W.save(nyUnanalyzeBbDict);
			} else {
				this.W.update(nyUnanalyzeBbDict);
			}

			result.setResult("success");
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/nyUnanalyzeBbDict/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "不分析标本管理--删除不分析标本")
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			if (ab.isNotEmpty(id)) {
				id = URLDecoder.decode(id, "utf-8");
				result = new Result();
				this.W.delete(id);
				result.setResult("success");
			}
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}