package com.nis.hygiene.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.hygiene.entity.Hw005Cyff;
import com.nis.hygiene.service.Hw005CyffService;
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
public class Hw005CyffController extends BaseController {
	private static final Logger c = Logger.getLogger(Hw005CyffController.class);
	@Autowired
	private Hw005CyffService rv;

	@RequestMapping({"/hw005Cyff/f_json/query"})
	@SqlLog(p = "环境卫生检测--采样方法列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Hw005Cyff hw005Cyff) {
		List list = this.rv.queryList(hw005Cyff);
		this.b(response, list);
	}

	@RequestMapping({"/hw005Cyff/f_view/toList"})
	public String t(HttpServletRequest request, ModelMap modelMap) {
		return "hygiene/hw005CyffList";
	}

	@RequestMapping({"/hw005Cyff/f_json/findList"})
	@SqlLog(p = "环境卫生检测--采样方法列表")
	public void b(HttpServletRequest request, HttpServletResponse response, Hw005Cyff hw005Cyff) {
		List list = this.rv.findList(hw005Cyff);
		this.b(response, list);
	}

	@RequestMapping({"/hw005Cyff/f_view/toedit"})
	@SqlLog(p = "环境卫生检测--采样方法详情")
	public String c(HttpServletRequest request, ModelMap modelMap, String takeModeId) {
		if (StringUtils.isNotBlank(takeModeId)) {
			Hw005Cyff maxTakeModeId = this.rv.get(takeModeId);
			modelMap.put("hw005Cyff", maxTakeModeId);
			modelMap.put("action", "edit");
		} else {
			String maxTakeModeId1 = this.rv.findMaxTakeModeId();
			Hw005Cyff hw005Cyff = new Hw005Cyff();
			hw005Cyff.setTakeModeId(String.valueOf(Integer.parseInt(maxTakeModeId1) + 1));
			modelMap.put("hw005Cyff", hw005Cyff);
		}

		return "hygiene/hw005CyffEdit";
	}

	@RequestMapping({"/hw005Cyff/f_json/save"})
	@ResponseBody
	@SqlLog(p = "环境卫生检测--保存采样方法")
	public void a(HttpServletRequest request, HttpServletResponse response, Hw005Cyff hw005Cyff, String action) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isNotBlank(hw005Cyff.getTakeModeId())) {
				hw005Cyff.setTakeModeId(hw005Cyff.getTakeModeId().trim());
				if ("edit".equals(action)) {
					this.rv.update(hw005Cyff);
					result.setResult("success");
				} else if (this.rv.get(hw005Cyff.getTakeModeId()) != null) {
					result = new Result("error", "方法编号已存在");
				} else {
					this.rv.save(hw005Cyff);
					result.setResult("success");
				}
			} else {
				result = new Result("error", "关键参数丢失");
			}
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/hw005Cyff/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "环境卫生检测--删除采样方法")
	public void c(HttpServletRequest request, HttpServletResponse response, String takeModeId) {
		Result result = null;

		try {
			result = new Result();
			this.rv.delete(takeModeId);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/hw005Cyff/f_json/updFlag"})
	@ResponseBody
	@SqlLog(p = "环境卫生检测--更新方法方法状态")
	public void c(HttpServletRequest request, HttpServletResponse response, Hw005Cyff hw005Cyff) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isNotBlank(hw005Cyff.getTakeModeId())) {
				if (hw005Cyff.getFlag() != null && hw005Cyff.getFlag().intValue() == 1) {
					hw005Cyff.setFlag(Integer.valueOf(0));
				} else {
					hw005Cyff.setFlag(Integer.valueOf(1));
				}

				this.rv.updFlag(hw005Cyff);
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