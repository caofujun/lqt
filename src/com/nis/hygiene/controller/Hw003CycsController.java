package com.nis.hygiene.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.hygiene.entity.Hw003Cycs;
import com.nis.hygiene.service.Hw003CycsService;
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
public class Hw003CycsController extends BaseController {
	private static final Logger c = Logger.getLogger(Hw003CycsController.class);
	@Autowired
	private Hw003CycsService rt;

	@RequestMapping({"/hw003Cycs/f_json/queryList"})
	@SqlLog(p = "环境卫生监测--监测场所列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Hw003Cycs hw003Cycs, String q,
			String defValue) {
		List list = this.rt.queryList(hw003Cycs);
		if (ab.isNotEmpty(q)) {
			hw003Cycs.setSearchString(ab.aR(q));
			list = this.rt.queryList(hw003Cycs);
		} else if (ab.isNotEmpty(defValue)) {
			hw003Cycs.setPlaceId(defValue);
			List list2 = this.rt.queryList(hw003Cycs);
			list.addAll(list2);
		}

		this.b(response, list);
	}

	@RequestMapping({"/hw003Cycs/f_view/toList"})
	public String t(HttpServletRequest request, ModelMap modelMap) {
		return "hygiene/hw003CycsList";
	}

	@RequestMapping({"/hw003Cycs/f_json/findList"})
	@SqlLog(p = "环境卫生监测--采样场所列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Hw003Cycs hw003Cycs) {
		if (hw003Cycs.getSearchString() != null && !"".equals(hw003Cycs.getSearchString())) {
			hw003Cycs.setSearchString(ab.aR(hw003Cycs.getSearchString()));
		}

		List list = this.rt.findList(hw003Cycs);
		this.b(response, list);
	}

	@RequestMapping({"/hw003Cycs/f_view/toedit"})
	@SqlLog(p = "环境卫生监测--采样场所详情")
	public String c(HttpServletRequest request, ModelMap modelMap, String placeId) {
		if (StringUtils.isNotBlank(placeId)) {
			Hw003Cycs maxPlaceId = this.rt.get(placeId);
			modelMap.put("hw003Cycs", maxPlaceId);
			modelMap.put("action", "edit");
		} else {
			String maxPlaceId1 = this.rt.findMaxPlaceId();
			Hw003Cycs hw003Cycs = new Hw003Cycs();
			hw003Cycs.setPlaceId(String.valueOf(Integer.parseInt(maxPlaceId1) + 1));
			modelMap.put("hw003Cycs", hw003Cycs);
		}

		return "hygiene/hw003CycsEdit";
	}

	@RequestMapping({"/hw003Cycs/f_json/save"})
	@ResponseBody
	@SqlLog(p = "环境卫生监测--保存采样场所")
	public void a(HttpServletRequest request, HttpServletResponse response, Hw003Cycs hw003Cycs, String action) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isNotBlank(hw003Cycs.getPlaceId())) {
				hw003Cycs.setPlaceId(hw003Cycs.getPlaceId().trim());
				if ("edit".equals(action)) {
					this.rt.update(hw003Cycs);
					result.setResult("success");
				} else if (this.rt.get(hw003Cycs.getPlaceId()) != null) {
					result = new Result("error", "场所编号已存在");
				} else {
					this.rt.save(hw003Cycs);
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

	@RequestMapping({"/hw003Cycs/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "环境卫生监测--删除采样场所")
	public void c(HttpServletRequest request, HttpServletResponse response, String placeId) {
		Result result = null;

		try {
			result = new Result();
			this.rt.delete(placeId);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/hw003Cycs/f_json/get"})
	public void b(HttpServletRequest request, HttpServletResponse response, String placeId) {
		Hw003Cycs hw003Cycs = null;
		if (StringUtils.isNotBlank(placeId)) {
			hw003Cycs = this.rt.get(placeId);
		}

		this.b(response, hw003Cycs);
	}

	@RequestMapping({"/hw003Cycs/f_json/updFlag"})
	@ResponseBody
	@SqlLog(p = "环境卫生监测--更新采样场所状态")
	public void b(HttpServletRequest request, HttpServletResponse response, Hw003Cycs hw003Cycs) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isNotBlank(hw003Cycs.getPlaceId())) {
				if (hw003Cycs.getFlag() != null && hw003Cycs.getFlag().intValue() == 1) {
					hw003Cycs.setFlag(Integer.valueOf(0));
				} else {
					hw003Cycs.setFlag(Integer.valueOf(1));
				}

				this.rt.updFlag(hw003Cycs);
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