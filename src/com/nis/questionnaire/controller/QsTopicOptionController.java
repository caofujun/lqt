package com.nis.questionnaire.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.questionnaire.entity.QsTopicOption;
import com.nis.questionnaire.service.QsTopicOptionService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QsTopicOptionController extends BaseController {
	private static final Logger c = Logger.getLogger(QsTopicOptionController.class);
	@Autowired
	private QsTopicOptionService xk;

	@RequestMapping({"/qsTopicOption/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "questionnaire/qsTopicOptionList";
	}

	@RequestMapping({"/qsTopicOption/f_json/pageQuery"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, QsTopicOption qsTopicOption) {
		MyPage page = this.xk.a(qsTopicOption);
		this.b(response, page);
	}

	@RequestMapping({"/qsTopicOption/f_view/toedit"})
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (id != null) {
			QsTopicOption qsTopicOption = this.xk.get(id);
			modelMap.put("qsTopicOption", qsTopicOption);
		}

		return "questionnaire/qsTopicOptionEdit";
	}

	@RequestMapping({"/qsTopicOption/f_json/save"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, QsTopicOption qsTopicOption) {
		Result result = null;

		try {
			result = new Result();
			if (ab.isEmpty(qsTopicOption.getOptId())) {
				this.xk.save(qsTopicOption);
			} else {
				this.xk.update(qsTopicOption);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/qsTopicOption/f_json/delete"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.xk.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}