package com.nis.dict.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.dict.entity.Zg033Jcxxpp;
import com.nis.dict.service.Zg033JcxxppService;
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
public class Zg033JcxxppController extends BaseController {
	private static final Logger c = Logger.getLogger(Zg033JcxxppController.class);
	@Autowired
	private Zg033JcxxppService qD;

	@RequestMapping({"/zg033Jcxxpp/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "zg/zg033JcxxppList";
	}

	@RequestMapping({"/zg033Jcxxpp/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "检验预警配置--检出项目规则列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Zg033Jcxxpp zg033Jcxxpp) {
		MyPage page = this.qD.a(zg033Jcxxpp);
		this.a(response, page);
	}

	@RequestMapping({"/zg033Jcxxpp/f_view/toedit"})
	@SqlLog(p = "检验预警配置--检出项目规则详情")
	public String e(HttpServletRequest request, ModelMap modelMap, String id, String sjId) {
		if (id != null) {
			Zg033Jcxxpp zg033Jcxxpp = this.qD.get(id);
			modelMap.put("zg033Jcxxpp", zg033Jcxxpp);
		}

		if (sjId != null) {
			modelMap.put("sjId", sjId);
		}

		return "dict/jyyjConfigurationEdit2";
	}

	@RequestMapping({"/zg033Jcxxpp/f_json/save"})
	@ResponseBody
	@SqlLog(p = "检验预警配置--保存检出项目规则")
	public void b(HttpServletRequest request, HttpServletResponse response, Zg033Jcxxpp zg033Jcxxpp) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isBlank(zg033Jcxxpp.getId())) {
				this.qD.save(zg033Jcxxpp);
			} else {
				this.qD.update(zg033Jcxxpp);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/zg033Jcxxpp/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "检验预警配置--删除检出项目规则")
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.qD.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}