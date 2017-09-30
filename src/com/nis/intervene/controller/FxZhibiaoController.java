package com.nis.intervene.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.intervene.entity.FxZhibiao;
import com.nis.intervene.service.FxZhibiaoService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FxZhibiaoController extends BaseController {
	@Autowired
	private FxZhibiaoService st;

	@RequestMapping({"/fxZhibiao/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "intervene/fxZhibiaoList";
	}

	@RequestMapping({"/fxZhibiao/f_json/pageQuery"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, FxZhibiao fxZhibiao) {
		MyPage page = this.st.a(fxZhibiao);
		this.b(response, page);
	}

	@RequestMapping({"/fxZhibiao/f_view/toedit"})
	public String c(HttpServletRequest request, ModelMap modelMap, String zbId) {
		if (ab.isNotEmpty(zbId)) {
			FxZhibiao fxZhibiao = this.st.get(zbId);
			modelMap.put("fxZhibiao", fxZhibiao);
		}

		return "intervene/fxZhibiaoEdit";
	}

	@RequestMapping({"/fxZhibiao/f_json/save"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, FxZhibiao fxZhibiao) {
		Result result = null;

		try {
			result = new Result();
			if (ab.isEmpty(fxZhibiao.getZbId())) {
				this.st.save(fxZhibiao);
			} else {
				this.st.update(fxZhibiao);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}