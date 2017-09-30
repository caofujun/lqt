package com.nis.analysis.controller;

import com.nis.analysis.entity.Zg007Dict;
import com.nis.analysis.service.Zg007DictService;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Zg007DictController extends BaseController {
	private static final Logger c = Logger.getLogger(Zg007DictController.class);
	@Autowired
	private Zg007DictService X;

	@RequestMapping({"/zg007Dict/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "monitor/zg007DictList";
	}

	@RequestMapping({"/zg007Dict/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "预警关键字管理--关键字列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Zg007Dict zg007Dict) {
		MyPage page = this.X.a(zg007Dict);
		this.a(response, page);
	}

	@RequestMapping({"/zg007Dict/f_view/toadd"})
	public String a(HttpServletRequest request, ModelMap modelMap, Zg007Dict zg007Dict) {
		modelMap.put("zg007Dict", zg007Dict);
		return "monitor/zg007DictEdit";
	}

	@RequestMapping({"/zg007Dict/f_view/toedit"})
	@SqlLog(p = "预警关键字管理--关键字详情")
	public String e(HttpServletRequest request, ModelMap modelMap, String keyid, String elementId) {
		Zg007Dict zg007Dict;
		if (StringUtils.isEmpty(keyid)) {
			zg007Dict = this.X.getByElementId(elementId);
			modelMap.put("zg007Dict", zg007Dict);
		} else {
			zg007Dict = this.X.get(keyid);
			zg007Dict.decode();
			modelMap.put("zg007Dict", zg007Dict);
		}

		return "monitor/zg007DictEdit";
	}

	@RequestMapping({"/zg007Dict/f_json/save"})
	@ResponseBody
	@SqlLog(p = "预警关键字管理--保存关键字")
	public void b(HttpServletRequest request, HttpServletResponse response, Zg007Dict zg007Dict) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isEmpty(zg007Dict.getKeyid())) {
				if ("0".equals(zg007Dict.getItemClass())) {
					zg007Dict.setElementType("0");
					zg007Dict.setQyElement("1");
					zg007Dict.setAppElement("1");
					zg007Dict.setBhElement("1");
					zg007Dict.setFlag("1");
				}

				this.X.save(zg007Dict);
			} else {
				this.X.update(zg007Dict);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/zg007Dict/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "预警关键字管理--删除关键字")
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.X.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}