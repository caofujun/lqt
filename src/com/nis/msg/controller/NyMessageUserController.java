package com.nis.msg.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.msg.controller.NyMessageThemeController;
import com.nis.msg.entity.NyMessageUser;
import com.nis.msg.service.NyMessageUserService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NyMessageUserController extends BaseController {
	private static final Logger c = Logger.getLogger(NyMessageThemeController.class);
	@Autowired
	private NyMessageUserService uT;

	@RequestMapping({"/nyMessageUser/f_json/getByThemeId"})
	@ResponseBody
	public void ai(HttpServletRequest request, HttpServletResponse response, String themeId) {
		List userList = this.uT.getByThemeId(themeId);
		this.a(response, userList);
	}

	@RequestMapping({"/nyMessageUser/f_json/save"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, NyMessageUser nyMessageUser) {
		Result result = null;

		try {
			result = new Result();
			if (ab.isEmpty(nyMessageUser.getMsgUserId())) {
				this.uT.a(nyMessageUser);
			} else {
				this.uT.update(nyMessageUser);
			}

			result.setData(nyMessageUser.getMsgUserId());
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/nyMessageUser/f_json/delete"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, String msgUserId) {
		Result result = null;

		try {
			result = new Result();
			this.uT.delete(msgUserId);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}