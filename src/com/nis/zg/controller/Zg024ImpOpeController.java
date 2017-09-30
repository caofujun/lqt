package com.nis.zg.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.dict.service.SysDictService;
import com.nis.zg.entity.Zg024ImpOpe;
import com.nis.zg.service.Zg024ImpOpeService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Zg024ImpOpeController extends BaseController {
	@Autowired
	private Zg024ImpOpeService yo;
	@Autowired
	private SysDictService p;

	@RequestMapping({"/zg024ImpOpe/f_view/operMacth"})
	public String az(HttpServletRequest request, ModelMap modelMap) {
		return "dict/operMacth";
	}

	@RequestMapping({"/zg024ImpOpe/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "手术匹配--重点手术列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Zg024ImpOpe zg024ImpOpe) {
		List page = this.p.u("operation_point", (String) null);
		this.b(response, page);
	}
}