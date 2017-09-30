package com.nis.dict.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.h;
import com.nis.dict.entity.Zg005Yygrzd;
import com.nis.dict.service.Zg005YygrzdService;
import java.util.Date;
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
public class YygrzdController extends BaseController {
	private static final Logger c = Logger.getLogger(YygrzdController.class);
	@Autowired
	private Zg005YygrzdService E;

	@RequestMapping({"/yygrzd/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "dict/yygrzdList";
	}

	@RequestMapping({"/yygrzd/f_json/findYygrzdList"})
	@ResponseBody
	@SqlLog(p = "标准诊断树")
	public void a(HttpServletRequest request, HttpServletResponse response, Zg005Yygrzd yygrzd, String addNull) {
		if (StringUtils.isBlank(yygrzd.getPInfectCode())) {
			yygrzd.setPInfectCode("HAI");
		}

		List list = this.E.b(yygrzd);
		if (String.valueOf(h.fP.getCode()).equals(addNull)) {
			list.add(0, new Zg005Yygrzd());
		}

		this.b(response, list);
	}

	@RequestMapping({"/yygrzd/f_view/yygrzdSelect"})
	public String j(HttpServletRequest request, ModelMap modelMap, String pInfectCode, String ownership) {
		return "dict/yygrzdSelect";
	}

	@RequestMapping({"/yygrzd/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "标准感染诊断--诊断列表")
	public void b(HttpServletRequest request, HttpServletResponse response, Zg005Yygrzd yygrzd) {
		MyPage page = this.E.a(yygrzd);
		this.b(response, page);
	}

	@RequestMapping({"/yygrzd/f_view/toedit"})
	@SqlLog(p = "标准感染诊断--诊断详情")
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (StringUtils.isNotBlank(id)) {
			Zg005Yygrzd yygrzd = this.E.get(id);
			modelMap.put("yygrzd", yygrzd);
		}

		return "dict/yygrzdEdit";
	}

	@RequestMapping({"/yygrzd/f_json/save"})
	@ResponseBody
	@SqlLog(p = "标准感染诊断--保存诊断")
	public void c(HttpServletRequest request, HttpServletResponse response, Zg005Yygrzd yygrzd) {
		Result result = null;

		try {
			result = new Result();
			if (yygrzd.getInfectCode() != null) {
				yygrzd.setInfectCode(yygrzd.getInfectCode().trim());
			}

			if (this.E.get(yygrzd.getInfectCode()) == null) {
				this.E.save(yygrzd);
			} else {
				this.E.update(yygrzd);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/yygrzd/f_json/updFlag"})
	@ResponseBody
	@SqlLog(p = "标准感染诊断--更新诊断状态")
	public void a(HttpServletRequest request, HttpServletResponse response, String infectCode, Integer flag) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isNotBlank(infectCode)) {
				if (flag != null && flag.intValue() == 1) {
					flag = Integer.valueOf(0);
				} else {
					flag = Integer.valueOf(1);
				}

				this.E.updFlag(infectCode, flag, new Date());
				result.setResult("success");
			} else {
				result = new Result("error", "关键参数丢失");
			}
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}