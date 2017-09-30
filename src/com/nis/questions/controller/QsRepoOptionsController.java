package com.nis.questions.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.questions.entity.QsRepoOptions;
import com.nis.questions.service.QsRepoOptionsService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QsRepoOptionsController extends BaseController {
	@Autowired
	private QsRepoOptionsService xy;

	@RequestMapping({"/qsRepoOptions/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "questions/qsRepoOptionsList";
	}

	@RequestMapping({"/qsRepoOptions/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "评估单--题目选项列表")
	public void a(HttpServletRequest request, HttpServletResponse response, QsRepoOptions qsRepoOptions) {
		MyPage page = this.xy.a(qsRepoOptions);
		this.a(response, page);
	}

	@RequestMapping({"/qsRepoOptions/f_view/toedit"})
	@SqlLog(p = "评估单--题目选项详情")
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (id != null) {
			QsRepoOptions qsRepoOptions = this.xy.get(id);
			modelMap.put("qsRepoOptions", qsRepoOptions);
		}

		return "questions/qsRepoOptionsEdit";
	}
}