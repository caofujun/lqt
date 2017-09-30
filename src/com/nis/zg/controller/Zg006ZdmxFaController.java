package com.nis.zg.controller;

import com.nis.access.entity.AcAccount;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.zg.service.Zg006ZdmxFaService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Zg006ZdmxFaController extends BaseController {
	private static final Logger c = Logger.getLogger(Zg006ZdmxFaController.class);
	@Autowired
	private Zg006ZdmxFaService cb;

	@RequestMapping({"/zg006ZdmxFa/f_view/toedit"})
	@SqlLog(p = "预警权重配置--预警权重列表")
	public String av(HttpServletRequest request, ModelMap modelMap) {
		List zdmxFaList = this.cb.getAll();
		modelMap.put("zdmxFaList", zdmxFaList);
		return "zg/zg006ZdmxFaEdit";
	}

	@RequestMapping({"/zg006ZdmxFa/f_json/start"})
	@ResponseBody
	@SqlLog(p = "预警权重配置--启用预警权重")
	public void aw(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isNotBlank(id)) {
				this.cb.start(id);
				result.setResult("success");
			} else {
				result.setResult("error");
				result.setMsg("参数异常");
			}
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/zg006ZdmxFa/f_json/adjust"})
	@ResponseBody
	@SqlLog(p = "预警权重配置--调节预警权重")
	public void a(HttpServletRequest request, HttpServletResponse response, String id, Integer weight, String type) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isNotBlank(id)) {
				AcAccount e = (AcAccount) this.b(request);
				this.cb.a(id, weight, type, e.getUserId());
				result.setResult("success");
			} else {
				result.setResult("error");
				result.setMsg("参数异常");
			}
		} catch (Exception arg7) {
			c.error("获取信息异常!", arg7);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/zg006ZdmxFa/f_json/saveDescribe"})
	@ResponseBody
	@SqlLog(p = "预警权重配置--保存预警权重信息")
	public void ad(HttpServletRequest request, HttpServletResponse response, String id, String faDescribe) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isNotBlank(id)) {
				AcAccount e = (AcAccount) this.b(request);
				this.cb.v(id, faDescribe, e.getUserId());
				result.setResult("success");
			} else {
				result.setResult("error");
				result.setMsg("参数异常");
			}
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}