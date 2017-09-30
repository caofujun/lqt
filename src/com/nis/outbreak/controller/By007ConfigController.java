package com.nis.outbreak.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.outbreak.service.By007ConfigService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class By007ConfigController extends BaseController {
	private static final Logger c = Logger.getLogger(By007ConfigController.class);
	@Autowired
	private By007ConfigService wg;

	@RequestMapping({"/by007Config/f_view/toGrade"})
	public String N(HttpServletRequest request, ModelMap modelMap) {
		return "outbreak/outbreakGrade";
	}

	@RequestMapping({"/by007Config/f_json/findList"})
	@ResponseBody
	public void R(HttpServletRequest request, HttpServletResponse response) {
		List list = this.wg.getAll();
		MyPage page = new MyPage(1, 100, list.size(), list);
		this.b(response, page);
	}

	@RequestMapping({"by007Config/f_json/saveGrade"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String[] typeId) {
		Result result = new Result();

		try {
			ArrayList e = new ArrayList();
			String[] arg8 = typeId;
			int arg7 = typeId.length;

			for (int arg6 = 0; arg6 < arg7; ++arg6) {
				String tmpId = arg8[arg6];
				e.add(tmpId);
			}

			this.wg.L(e);
			result.setResult("success");
		} catch (Exception arg9) {
			c.error("保存授权信息异常!", arg9);
			result = new Result("error", "保存授权信息异常");
		}

		this.a(response, result);
	}
}