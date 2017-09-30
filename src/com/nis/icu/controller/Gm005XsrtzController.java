package com.nis.icu.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.Result;
import com.nis.icu.entity.Gm005Xsrtz;
import com.nis.icu.service.Gm005XsrtzService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Gm005XsrtzController extends BaseController {
	private static final Logger c = Logger.getLogger(Gm005XsrtzController.class);
	@Autowired
	private Gm005XsrtzService sj;

	@RequestMapping({"/gm005Xsrtz/f_json/updWeight"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Gm005Xsrtz gm005Xsrtz) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isNotBlank(gm005Xsrtz.getZyid()) && gm005Xsrtz.getWeight() != null) {
				LoginUser e = this.d(request);
				this.sj.a(gm005Xsrtz, e);
				result.setResult("success");
			} else {
				result = new Result("error", "关键参数丢失");
			}
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}