package com.nis.hygiene.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.hygiene.entity.Hw004Cybb;
import com.nis.hygiene.service.Hw004CybbService;
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
public class Hw004CybbController extends BaseController {
	private static final Logger c = Logger.getLogger(Hw004CybbController.class);
	@Autowired
	private Hw004CybbService ru;

	@RequestMapping({"/hw004Cybb/f_json/queryList"})
	@SqlLog(p = "环境卫生检测--采样标本列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Hw004Cybb hw004Cybb, String q,
			String defValue) {
		List list = this.ru.queryList(hw004Cybb);
		if (ab.isNotEmpty(q)) {
			hw004Cybb.setSearchString(q);
			list = this.ru.queryList(hw004Cybb);
		} else if (ab.isNotEmpty(defValue)) {
			hw004Cybb.setSampleId(defValue);
			List list2 = this.ru.queryList(hw004Cybb);
			list.addAll(list2);
		}

		this.b(response, list);
	}

	@RequestMapping({"/hw004Cybb/f_view/toList"})
	public String t(HttpServletRequest request, ModelMap modelMap) {
		return "hygiene/hw004CybbList";
	}

	@RequestMapping({"/hw004Cybb/f_json/findList"})
	@SqlLog(p = "环境卫生监测--采样标本列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Hw004Cybb hw004Cybb) {
		List list = this.ru.findList(hw004Cybb);
		this.b(response, list);
	}

	@RequestMapping({"/hw004Cybb/f_view/toedit"})
	@SqlLog(p = "环境卫生监测--采样标本詳情")
	public String c(HttpServletRequest request, ModelMap modelMap, String sampleId) {
		if (StringUtils.isNotBlank(sampleId)) {
			Hw004Cybb maxSampleId = this.ru.get(sampleId);
			modelMap.put("hw004Cybb", maxSampleId);
			modelMap.put("action", "edit");
		} else {
			String maxSampleId1 = this.ru.findMaxSampleId();
			Hw004Cybb hw004Cybb = new Hw004Cybb();
			hw004Cybb.setSampleId(String.valueOf(Integer.parseInt(maxSampleId1) + 1));
			modelMap.put("hw004Cybb", hw004Cybb);
		}

		return "hygiene/hw004CybbEdit";
	}

	@RequestMapping({"/hw004Cybb/f_json/save"})
	@ResponseBody
	@SqlLog(p = "环境卫生监测--保存采样标本")
	public void a(HttpServletRequest request, HttpServletResponse response, Hw004Cybb hw004Cybb, String action) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isNotBlank(hw004Cybb.getSampleId())) {
				hw004Cybb.setSampleId(hw004Cybb.getSampleId().trim());
				if ("edit".equals(action)) {
					this.ru.update(hw004Cybb);
					result.setResult("success");
				} else if (this.ru.get(hw004Cybb.getSampleId()) != null) {
					result = new Result("error", "标本ID已存在");
				} else {
					this.ru.save(hw004Cybb);
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

	@RequestMapping({"/hw004Cybb/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "环境卫生监测--删除采样标本")
	public void c(HttpServletRequest request, HttpServletResponse response, String sampleId) {
		Result result = null;

		try {
			result = new Result();
			this.ru.delete(sampleId);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/hw004Cybb/f_json/get"})
	@SqlLog(p = "环境卫生监测--采样标本详情")
	public void b(HttpServletRequest request, HttpServletResponse response, String sampleId) {
		Hw004Cybb hw004Cybb = null;
		if (StringUtils.isNotBlank(sampleId)) {
			hw004Cybb = this.ru.getHw004Cybb(sampleId);
		}

		this.b(response, hw004Cybb);
	}

	@RequestMapping({"/hw004Cybb/f_json/updFlag"})
	@ResponseBody
	@SqlLog(p = "环境卫生监测--更新采样标本状态")
	public void b(HttpServletRequest request, HttpServletResponse response, Hw004Cybb hw004Cybb) {
		Result result = null;

		try {
			result = new Result();
			if (StringUtils.isNotBlank(hw004Cybb.getSampleId())) {
				if (hw004Cybb.getFlag() != null && hw004Cybb.getFlag().intValue() == 1) {
					hw004Cybb.setFlag(Integer.valueOf(0));
				} else {
					hw004Cybb.setFlag(Integer.valueOf(1));
				}

				this.ru.updFlag(hw004Cybb);
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