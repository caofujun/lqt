package com.nis.dict.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.dict.entity.Sop;
import com.nis.dict.service.SopService;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
public class SopController extends BaseController {
	private static final Logger c = Logger.getLogger(SopController.class);
	@Autowired
	private SopService qz;

	@RequestMapping({"/sop/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "dict/sopList";
	}

	@RequestMapping({"/sop/f_view/sopDetailList"})
	public String n(HttpServletRequest request, ModelMap modelMap) {
		return "dict/sopDetailList";
	}

	@RequestMapping({"/sop/f_view/toDetail"})
	@SqlLog(p = "SOP字典--SOP字典详情")
	public String n(HttpServletRequest request, ModelMap modelMap, String id) {
		if (StringUtils.isNotBlank(id)) {
			Sop sop = this.qz.get(id);
			if (ab.isNotEmpty(sop.getShowFileData())) {
				String showFileData = sop.getShowFileData();
				showFileData = showFileData.replaceAll("[\\t\\n\\r]", "");
				showFileData = showFileData.replaceAll("\'", "\"");
				sop.setShowFileData(showFileData);
			}

			modelMap.put("sop", sop);
		}

		return "dict/sopDetail";
	}

	@RequestMapping({"/sop/f_view/query"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Long year) {
		if (year == null) {
			year = Long.valueOf((long) f.getCurYear());
		}

		Object list = null;
		this.b(response, list);
	}

	@RequestMapping({"/sop/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "SOP字典--SOP字典列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Sop sop) {
		if (sop.getSearchString() != null && !"".equals(sop.getSearchString())) {
			sop.setSearchString(ab.aR(sop.getSearchString()));
		}

		MyPage page = this.qz.a(sop);
		this.b(response, page);
	}

	@RequestMapping({"/sop/f_view/toedit"})
	@SqlLog(p = "SOP字典--SOP字典详情")
	public String a(HttpServletRequest request, ModelMap modelMap, String id, String content, String pFileId)
			throws UnsupportedEncodingException {
		Sop sop;
		if (StringUtils.isNotBlank(id)) {
			sop = this.qz.get(id);
			modelMap.put("sop", sop);
		} else {
			sop = new Sop();
			if (ab.isNotEmpty(content)) {
				sop.setShowFileData(URLDecoder.decode(content, "UTF-8"));
			}

			sop.setpFileId(pFileId);
			modelMap.put("sop", sop);
		}

		return "dict/sopEdit";
	}

	@RequestMapping({"/sop/f_view/select"})
	public String o(HttpServletRequest request, ModelMap modelMap) {
		return "dict/sopList";
	}

	@RequestMapping({"/sop/f_view/details"})
	@SqlLog(p = "SOP字典--SOP字典详情")
	public String o(HttpServletRequest request, ModelMap modelMap, String id) {
		if (StringUtils.isNotBlank(id)) {
			Sop sop = this.qz.get(id);
			modelMap.put("sop", sop);
		}

		return "dict/sopDetails";
	}

	@RequestMapping({"/sop/f_json/save"})
	@ResponseBody
	@SqlLog(p = "SOP字典--保存SOP字典")
	public void b(HttpServletRequest request, HttpServletResponse response, Sop sop) {
		Result result = null;

		try {
			result = new Result();
			if (this.qz.get(sop.getFileId()) == null) {
				this.qz.save(sop);
			} else {
				this.qz.update(sop);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/sop/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "SOP字典--删除SOP字典")
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.qz.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}