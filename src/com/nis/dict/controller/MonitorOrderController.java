package com.nis.dict.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.f;
import com.nis.dict.entity.MonitorOrder;
import com.nis.dict.service.MonitorOrderService;
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
public class MonitorOrderController extends BaseController {
	private static final Logger c = Logger.getLogger(MonitorOrderController.class);
	@Autowired
	private MonitorOrderService qy;

	@RequestMapping({"/monitorOrder/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "dict/monitorOrderList";
	}

	@RequestMapping({"/monitorOrder/f_view/query"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Long year) {
		if (year == null) {
			year = Long.valueOf((long) f.getCurYear());
		}

		Object list = null;
		this.b(response, list);
	}

	@RequestMapping({"/monitorOrder/f_json/pageQuery"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, MonitorOrder monitorOrder) {
		MyPage page = this.qy.a(monitorOrder);
		this.b(response, page);
	}

	@RequestMapping({"/monitorOrder/f_view/toedit"})
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (StringUtils.isNotBlank(id)) {
			MonitorOrder monitorOrder = this.qy.get(id);
			modelMap.put("monitorOrder", monitorOrder);
		}

		return "dict/monitorOrderEdit";
	}

	@RequestMapping({"/monitorOrder/f_json/save"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, MonitorOrder monitorOrder) {
		Result result = null;

		try {
			result = new Result();
			if (this.qy.get(monitorOrder.getOrderCode()) == null) {
				this.qy.save(monitorOrder);
			} else {
				this.qy.update(monitorOrder);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/monitorOrder/f_json/delete"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.qy.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}