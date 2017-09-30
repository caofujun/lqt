package com.nis.hygiene.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.hygiene.entity.Hw002Jsbz;
import com.nis.hygiene.entity.Hw006Cyds;
import com.nis.hygiene.service.Hw002JsbzService;
import com.nis.hygiene.service.Hw006CydsService;
import java.util.ArrayList;
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
public class Hw002JsbzController extends BaseController {
	private static final Logger c = Logger.getLogger(Hw002JsbzController.class);
	@Autowired
	private Hw002JsbzService rr;
	@Autowired
	private Hw006CydsService rs;

	@RequestMapping({"/hw002Jsbz/f_json/findListByClassId"})
	@SqlLog(p = "环境卫生监测--监测样本数据")
	public void w(HttpServletRequest request, HttpServletResponse response, String classId, String posId) {
		List list = this.rr.findListByClassId(classId);
		Hw006Cyds cyds = this.rs.get(posId);
		ArrayList allList = new ArrayList();
		if (cyds != null && ab.isNotEmpty(cyds.getPosValue())) {
			Integer posValue = Integer.valueOf(Integer.parseInt(cyds.getPosValue()));

			for (int i = 0; i < posValue.intValue(); ++i) {
				allList.addAll(list);
			}
		}

		this.b(response, allList);
	}

	@RequestMapping({"/hw002Jsbz/f_view/toList"})
	public String t(HttpServletRequest request, ModelMap modelMap) {
		return "hygiene/hw002JsbzList";
	}

	@RequestMapping({"/hw002Jsbz/f_json/findList"})
	@SqlLog(p = "环境卫生监测--监测技术标准列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Hw002Jsbz hw002Jsbz) {
		List list = this.rr.findList(hw002Jsbz);
		this.b(response, list);
	}

	@RequestMapping({"/hw002Jsbz/f_view/toedit"})
	@SqlLog(p = "环境卫生监测--监测技术标准详情")
	public String c(HttpServletRequest request, ModelMap modelMap, String itemId) {
		if (StringUtils.isNotBlank(itemId)) {
			Hw002Jsbz maxItemId = this.rr.get(itemId);
			modelMap.put("hw002Jsbz", maxItemId);
			modelMap.put("action", "edit");
		} else {
			String maxItemId1 = this.rr.findMaxItemId();
			Hw002Jsbz hw002Jsbz = new Hw002Jsbz();
			hw002Jsbz.setItemId(String.valueOf(Integer.parseInt(maxItemId1) + 1));
			modelMap.put("hw002Jsbz", hw002Jsbz);
		}

		return "hygiene/hw002JsbzEdit";
	}

	@RequestMapping({"/hw002Jsbz/f_json/save"})
	@ResponseBody
	@SqlLog(p = "环境卫生监测--保存监测技术标准")
	public void a(HttpServletRequest request, HttpServletResponse response, Hw002Jsbz hw002Jsbz, String action) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isNotBlank(hw002Jsbz.getItemId())) {
				hw002Jsbz.setItemId(hw002Jsbz.getItemId().trim());
				if ("edit".equals(action)) {
					this.rr.update(hw002Jsbz);
					result.setResult("success");
				} else if (this.rr.get(hw002Jsbz.getItemId()) != null) {
					result = new Result("error", "类别ID已存在");
				} else {
					this.rr.save(hw002Jsbz);
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

	@RequestMapping({"/hw002Jsbz/f_json/get"})
	@SqlLog(p = "环境卫生监测--监测技术标准详情")
	public void b(HttpServletRequest request, HttpServletResponse response, String itemId) {
		Hw002Jsbz hw002Jsbz = null;
		if (StringUtils.isNotBlank(itemId)) {
			hw002Jsbz = this.rr.getHw002Jsbz(itemId);
		}

		this.b(response, hw002Jsbz);
	}

	@RequestMapping({"/hw002Jsbz/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "环境卫生监测--删除监测技术标准")
	public void c(HttpServletRequest request, HttpServletResponse response, String itemId) {
		Result result = null;

		try {
			result = new Result();
			this.rr.delete(itemId);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}