package com.nis.analysis.controller;

import com.nis.analysis.entity.NyUnanalyzeBbByt;
import com.nis.analysis.service.NyUnanalyzeBbBytService;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NyUnanalyzeBbBytController extends BaseController {
	private static final Logger c = Logger.getLogger(NyUnanalyzeBbBytController.class);
	@Autowired
	private NyUnanalyzeBbBytService V;

	@RequestMapping({"/nyUnanalyzeBbByt/f_view/nyUnanalyzeBbTab"})
	public String d(HttpServletRequest request, ModelMap modelMap) {
		return "analysis/nyUnanalyzeBbTab";
	}

	@RequestMapping({"/nyUnanalyzeBbByt/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "analysis/nyUnanalyzeBbBytList";
	}

	@RequestMapping({"/nyUnanalyzeBbByt/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "不分析病原体管理--不分析病原体列表")
	public void a(HttpServletRequest request, HttpServletResponse response, NyUnanalyzeBbByt nyUnanalyzeBbByt) {
		MyPage page = this.V.a(nyUnanalyzeBbByt);
		this.a(response, page);
	}

	@RequestMapping({"/nyUnanalyzeBbByt/f_view/toedit"})
	@SqlLog(p = "不分析病原体管理--不分析病原体详情")
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (ab.isNotEmpty(id)) {
			NyUnanalyzeBbByt nyUnanalyzeBbByt = this.V.get(id);
			modelMap.put("nyUnanalyzeBbByt", nyUnanalyzeBbByt);
		}

		return "analysis/nyUnanalyzeBbBytEdit";
	}

	@RequestMapping({"/nyUnanalyzeBbByt/f_json/save"})
	@ResponseBody
	@SqlLog(p = "不分析病原体管理--保存不分析病原体")
	public void b(HttpServletRequest request, HttpServletResponse response, NyUnanalyzeBbByt nyUnanalyzeBbByt) {
		Result result = null;

		try {
			result = new Result();
			if (ab.isEmpty(nyUnanalyzeBbByt.getBytid())) {
				this.V.save(nyUnanalyzeBbByt);
			} else {
				this.V.update(nyUnanalyzeBbByt);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/nyUnanalyzeBbByt/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "不分析病原体管理--删除不分析病原体")
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.V.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}