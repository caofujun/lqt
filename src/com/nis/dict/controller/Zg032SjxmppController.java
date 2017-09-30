package com.nis.dict.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.dict.entity.Zg032Sjxmpp;
import com.nis.dict.service.Zg032SjxmppService;
import com.nis.zg.entity.Zg007Grys;
import com.nis.zg.service.Zg007GrysService;
import java.util.List;
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
public class Zg032SjxmppController extends BaseController {
	private static final Logger c = Logger.getLogger(Zg032SjxmppController.class);
	@Autowired
	private Zg032SjxmppService bG;
	@Autowired
	private Zg007GrysService qC;

	@RequestMapping({"/zg032Sjxmpp/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "dict/jyyjConfiguration";
	}

	@RequestMapping({"/zg032Sjxmpp/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "检验预警配置--送检规则列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Zg032Sjxmpp zg032Sjxmpp) {
		MyPage page = this.bG.a(zg032Sjxmpp);
		this.a(response, page);
	}

	@RequestMapping({"/zg032Sjxmpp/f_json/findByIdOrName"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Zg007Grys zg007Grys) {
		List zg007GrysList = this.qC.findByElementIdLike("JC");
		this.b(response, zg007GrysList);
	}

	@RequestMapping({"/zg032Sjxmpp/f_view/toedit"})
	@SqlLog(p = "检验预警配置--送检规则详情")
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (id != null) {
			Zg032Sjxmpp zg032Sjxmpp = this.bG.get(id);
			modelMap.put("zg032Sjxmpp", zg032Sjxmpp);
		}

		return "dict/jyyjConfigurationEdit";
	}

	@RequestMapping({"/zg032Sjxmpp/f_json/save"})
	@ResponseBody
	@SqlLog(p = "检验预警配置--保存送检规则")
	public void b(HttpServletRequest request, HttpServletResponse response, Zg032Sjxmpp zg032Sjxmpp) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isEmpty(zg032Sjxmpp.getId())) {
				this.bG.save(zg032Sjxmpp);
			} else {
				this.bG.update(zg032Sjxmpp);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/zg032Sjxmpp/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "检验预警配置--删除送检规则")
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.bG.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}