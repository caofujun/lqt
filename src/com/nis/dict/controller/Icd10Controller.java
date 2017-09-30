package com.nis.dict.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.dict.entity.Icd10;
import com.nis.dict.service.Icd10Service;
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
public class Icd10Controller extends BaseController {
	private static final Logger c = Logger.getLogger(Icd10Controller.class);
	@Autowired
	private Icd10Service qw;

	@RequestMapping({"/icd10/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "dict/icd10List";
	}

	@RequestMapping({"/icd10/f_view/query"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String drugTypeid, String q, int page,
			int size) {
		Icd10 icd10 = new Icd10();
		icd10.setSearchString(ab.aR(q));
		icd10.setPage(Integer.valueOf(page));
		icd10.setSize(Integer.valueOf(size));
		MyPage pageIcd10 = this.qw.a(icd10);
		this.b(response, pageIcd10.getRows());
	}

	@RequestMapping({"/icd10/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "ICD10字典--ICD字典列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Icd10 icd10) {
		if (icd10.getSearchString() != null && !"".equals(icd10.getSearchString())) {
			icd10.setSearchString(ab.aR(icd10.getSearchString()));
		}

		MyPage page = this.qw.a(icd10);
		this.b(response, page);
	}

	@RequestMapping({"/icd10/f_view/toedit"})
	@SqlLog(p = "ICD10字典--ICD字典详情")
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (StringUtils.isNotBlank(id)) {
			Icd10 icd10 = this.qw.get(id);
			modelMap.put("icd10", icd10);
		}

		return "dict/icd10Edit";
	}

	@RequestMapping({"/icd10/f_json/save"})
	@ResponseBody
	@SqlLog(p = "ICD10字典--保存ICD字典")
	public void b(HttpServletRequest request, HttpServletResponse response, Icd10 icd10) {
		Result result = null;

		try {
			result = new Result();
			if (icd10.getIcdId() != null) {
				icd10.setIcdId(icd10.getIcdId().trim());
			}

			if (this.qw.get(icd10.getIcdId()) == null) {
				this.qw.save(icd10);
			} else {
				this.qw.update(icd10);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/icd10/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "ICD10字典--删除ICD字典")
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.qw.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}