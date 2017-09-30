package com.nis.dict.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.dict.entity.Icd9;
import com.nis.dict.service.Icd9Service;
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
public class Icd9Controller extends BaseController {
	private static final Logger c = Logger.getLogger(Icd9Controller.class);
	@Autowired
	private Icd9Service qx;

	@RequestMapping({"/icd9/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "dict/icd9List";
	}

	@RequestMapping({"/icd9/f_view/query"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String drugTypeid, String q, int page,
			int size, String defValue) {
		Icd9 icd9 = new Icd9();
		if (ab.isNotEmpty(q)) {
			icd9.setSearchString(ab.aR(q));
		} else {
			icd9.setIcdId(defValue);
		}

		icd9.setPage(Integer.valueOf(page));
		icd9.setSize(Integer.valueOf(size));
		MyPage icd9Page = this.qx.a(icd9);
		this.b(response, icd9Page);
	}

	@RequestMapping({"/icd9/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "ICD9字典--ICD字典列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Icd9 icd9) {
		if (icd9.getSearchString() != null && !"".equals(icd9.getSearchString())) {
			icd9.setSearchString(ab.aR(icd9.getSearchString()));
		}

		MyPage page = this.qx.a(icd9);
		this.b(response, page);
	}

	@RequestMapping({"/icd9/f_view/toSelectList"})
	public String m(HttpServletRequest request, ModelMap modelMap, String patientId) {
		return "dict/icd9Select";
	}

	@RequestMapping({"/icd9/f_view/toedit"})
	@SqlLog(p = "ICD9字典--ICD字典详情")
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (StringUtils.isNotBlank(id)) {
			Icd9 icd9 = this.qx.get(id);
			modelMap.put("icd9", icd9);
		}

		return "dict/icd9Edit";
	}

	@RequestMapping({"/icd9/f_json/save"})
	@ResponseBody
	@SqlLog(p = "ICD9字典--保存ICD字典")
	public void b(HttpServletRequest request, HttpServletResponse response, Icd9 icd9) {
		Result result = null;

		try {
			result = new Result();
			if (icd9.getIcdId() != null) {
				icd9.setIcdId(icd9.getIcdId().trim());
			}

			if (this.qx.get(icd9.getIcdId()) == null) {
				this.qx.save(icd9);
			} else {
				this.qx.update(icd9);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/icd9/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "ICD9字典--删除ICD字典")
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.qx.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}