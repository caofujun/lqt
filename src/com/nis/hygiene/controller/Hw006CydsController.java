package com.nis.hygiene.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.hygiene.entity.Hw006Cyds;
import com.nis.hygiene.service.Hw006CydsService;
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
public class Hw006CydsController extends BaseController {
	private static final Logger c = Logger.getLogger(Hw006CydsController.class);
	@Autowired
	private Hw006CydsService rs;

	@RequestMapping({"/hw006Cyds/f_json/query"})
	@SqlLog(p = "环境卫生监测--采样点数列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Hw006Cyds hw006Cyds) {
		List list = this.rs.queryList(hw006Cyds);
		this.b(response, list);
	}

	@RequestMapping({"/hw006Cyds/f_view/toList"})
	public String t(HttpServletRequest request, ModelMap modelMap) {
		return "hygiene/hw006CydsList";
	}

	@RequestMapping({"/hw006Cyds/f_json/findList"})
	@SqlLog(p = "环境卫生监测--采样点数列表")
	public void b(HttpServletRequest request, HttpServletResponse response, Hw006Cyds hw006Cyds) {
		List list = this.rs.findList(hw006Cyds);
		this.b(response, list);
	}

	@RequestMapping({"/hw006Cyds/f_view/toedit"})
	@SqlLog(p = "环境卫生监测--采样点数详情")
	public String c(HttpServletRequest request, ModelMap modelMap, String posId) {
		if (StringUtils.isNotBlank(posId)) {
			Hw006Cyds maxPosId = this.rs.get(posId);
			modelMap.put("hw006Cyds", maxPosId);
			modelMap.put("action", "edit");
		} else {
			String maxPosId1 = this.rs.findMaxPosId();
			Hw006Cyds hw006Cyds = new Hw006Cyds();
			hw006Cyds.setPosId(String.valueOf(Integer.parseInt(maxPosId1) + 1));
			modelMap.put("hw006Cyds", hw006Cyds);
		}

		return "hygiene/hw006CydsEdit";
	}

	@RequestMapping({"/hw006Cyds/f_json/save"})
	@ResponseBody
	@SqlLog(p = "环境卫生监测--保存采样点数")
	public void a(HttpServletRequest request, HttpServletResponse response, Hw006Cyds hw006Cyds, String action) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isNotBlank(hw006Cyds.getPosId())) {
				hw006Cyds.setPosId(hw006Cyds.getPosId().trim());
				if ("edit".equals(action)) {
					this.rs.update(hw006Cyds);
					result.setResult("success");
				} else if (this.rs.get(hw006Cyds.getPosId()) != null) {
					result = new Result("error", "点数编号已存在");
				} else {
					this.rs.save(hw006Cyds);
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

	@RequestMapping({"/hw006Cyds/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "环境卫生监测--删除采样点数")
	public void c(HttpServletRequest request, HttpServletResponse response, String posId) {
		Result result = null;

		try {
			result = new Result();
			this.rs.delete(posId);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/hw006Cyds/f_json/updFlag"})
	@ResponseBody
	@SqlLog(p = "环境卫生监测--更新采样点数状态")
	public void c(HttpServletRequest request, HttpServletResponse response, Hw006Cyds hw006Cyds) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isNotBlank(hw006Cyds.getPosId())) {
				if (hw006Cyds.getFlag() != null && hw006Cyds.getFlag().intValue() == 1) {
					hw006Cyds.setFlag(Integer.valueOf(0));
				} else {
					hw006Cyds.setFlag(Integer.valueOf(1));
				}

				this.rs.updFlag(hw006Cyds);
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