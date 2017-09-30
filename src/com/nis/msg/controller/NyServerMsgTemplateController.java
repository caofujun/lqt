package com.nis.msg.controller;

import com.nis.access.entity.AcAccount;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.f;
import com.nis.msg.entity.NyServerMsgTemplate;
import com.nis.msg.service.NyServerMsgTemplateService;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NyServerMsgTemplateController extends BaseController {
	private static final Logger c = Logger.getLogger(NyServerMsgTemplateController.class);
	@Autowired
	private NyServerMsgTemplateService uV;

	@RequestMapping({"/msgTemplate/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "msg/nyServerMsgTemplateList";
	}

	@RequestMapping({"/msgTemplate/f_view/msgTemplateDetailList"})
	public String M(HttpServletRequest request, ModelMap modelMap) {
		return "msg/msgTemplateDetailList";
	}

	@RequestMapping({"/msgTemplate/f_view/toDetail"})
	public String n(HttpServletRequest request, ModelMap modelMap, String id) {
		if (StringUtils.isNotBlank(id)) {
			NyServerMsgTemplate msgTemplate = this.uV.get(id);
			modelMap.put("msgTemplate", msgTemplate);
		}

		return "msg/nyServerMsgTemplateDetail";
	}

	@RequestMapping({"/msgTemplate/f_view/query"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Long year) {
		if (year == null) {
			year = Long.valueOf((long) f.getCurYear());
		}

		Object list = null;
		this.b(response, list);
	}

	@RequestMapping({"/msgTemplate/f_json/pageQuery"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, NyServerMsgTemplate msgTemplate) {
		MyPage page = this.uV.a(msgTemplate);
		this.b(response, page);
	}

	@RequestMapping({"/msgTemplate/f_view/toedit"})
	public String a(HttpServletRequest request, ModelMap modelMap, String id, String title, String content)
			throws UnsupportedEncodingException {
		NyServerMsgTemplate msgTemplate;
		if (StringUtils.isNotBlank(id)) {
			msgTemplate = this.uV.get(id);
			modelMap.put("msgTemplate", msgTemplate);
		} else {
			msgTemplate = new NyServerMsgTemplate();
			msgTemplate.setContentStr(URLDecoder.decode(content, "UTF-8"));
			msgTemplate.setTitle(URLDecoder.decode(title, "UTF-8"));
			modelMap.put("msgTemplate", msgTemplate);
		}

		return "msg/nyServerMsgTemplateEdit";
	}

	@RequestMapping({"/msgTemplate/f_view/select"})
	public String o(HttpServletRequest request, ModelMap modelMap) {
		return "msg/msgTemplateList";
	}

	@RequestMapping({"/msgTemplate/f_view/details"})
	public String o(HttpServletRequest request, ModelMap modelMap, String id) {
		if (StringUtils.isNotBlank(id)) {
			NyServerMsgTemplate msgTemplate = this.uV.get(id);
			modelMap.put("msgTemplate", msgTemplate);
		}

		return "msg/msgTemplateDetails";
	}

	@RequestMapping({"/msgTemplate/f_json/save"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, NyServerMsgTemplate msgTemplate) {
		Result result = null;

		try {
			result = new Result();
			if (this.uV.get(msgTemplate.getId()) == null) {
				if (StringUtils.isBlank(msgTemplate.getCreateOper())) {
					AcAccount e = (AcAccount) this.b(request);
					msgTemplate.setCreateOper(e.getUserId());
				}

				this.uV.save(msgTemplate);
			} else {
				this.uV.update(msgTemplate);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/msgTemplate/f_json/delete"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.uV.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}