package com.nis.dict.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.f;
import com.nis.dict.entity.TrocheKind;
import com.nis.dict.service.TrocheKindService;
import java.util.List;
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
public class TrocheKindController extends BaseController {
	private static final Logger c = Logger.getLogger(TrocheKindController.class);
	@Autowired
	private TrocheKindService qB;

	@RequestMapping({"/trocheKind/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "organization/trocheKindList";
	}

	@RequestMapping({"/trocheKind/f_view/query"})
	@ResponseBody
	@SqlLog(p = "抗菌药物--抗菌药物分类列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Long year, String q) {
		if (year == null) {
			year = Long.valueOf((long) f.getCurYear());
		}

		List list = this.qB.a(year, q);
		this.b(response, list);
	}

	@RequestMapping({"/trocheKind/f_json/pageQuery"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, TrocheKind trocheKind) {
		MyPage page = this.qB.a(trocheKind);
		this.b(response, page);
	}

	@RequestMapping({"/trocheKind/f_view/toedit"})
	public String a(HttpServletRequest request, ModelMap modelMap, Long year, String trocheKindId) {
		if (year != null && StringUtils.isNotBlank(trocheKindId)) {
			TrocheKind trocheKind = this.qB.get(year, trocheKindId);
			modelMap.put("trocheKind", trocheKind);
		}

		return "organization/trocheKindEdit";
	}

	@RequestMapping({"/trocheKind/f_json/save"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, TrocheKind trocheKind) {
		Result result = null;

		try {
			result = new Result();
			if (this.qB.get(trocheKind.getYear(), trocheKind.getThocheKindId()) == null) {
				this.qB.save(trocheKind);
			} else {
				this.qB.update(trocheKind);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/trocheKind/f_json/delete"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, Long year, String trocheKind) {
		Result result = null;

		try {
			result = new Result();
			this.qB.delete(year, trocheKind);
			result.setResult("success");
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}