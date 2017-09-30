package com.nis.intervene.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.intervene.entity.JkDicAll;
import com.nis.intervene.service.JkDicAllService;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JkDicAllController extends BaseController {
	private static final Logger c = Logger.getLogger(JkDicAllController.class);
	@Autowired
	private JkDicAllService sw;

	@RequestMapping({"/jkDicAll/f_view/index"})
	public String p(HttpServletRequest request, ModelMap modelMap, String classCode) {
		modelMap.put("classCode", classCode);
		return "intervene/jkDicAllIndex";
	}

	@RequestMapping({"/jkDicAll/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "监测项目管理--监测项目分类列表")
	public void w(HttpServletRequest request, HttpServletResponse response) {
		List page = this.sw.findDictType();
		this.b(response, page);
	}

	@RequestMapping({"/jkDicAll/f_view/list"})
	@SqlLog(p = "监测项目管理--监测项目明细列表")
	public String A(HttpServletRequest request, ModelMap modelMap, String classCode) {
		modelMap.put("classCode", classCode);
		return "intervene/jkDicAllList";
	}

	@RequestMapping({"/jkDicAll/f_json/pageQueryList"})
	@ResponseBody
	@SqlLog(p = "监测项目管理--监测项目列表")
	public void a(HttpServletRequest request, HttpServletResponse response, JkDicAll jkDicAll) {
		if (jkDicAll.getSearchString() != null && !"".equals(jkDicAll.getSearchString())) {
			jkDicAll.setSearchString(ab.aR(jkDicAll.getSearchString()));
		}

		List page = this.sw.findDict(jkDicAll);
		this.b(response, page);
	}

	@RequestMapping({"/jkDicAll/f_view/toedit"})
	@SqlLog(p = "监测项目管理--监测项目明细")
	public String e(HttpServletRequest request, ModelMap modelMap, String classCode, String orderCode) {
		JkDicAll jkDicAll = new JkDicAll();
		List jkDicAllList;
		if (ab.isNotEmpty(orderCode)) {
			jkDicAll = this.sw.get(orderCode);
		} else {
			jkDicAll.setClassCode(classCode);
			jkDicAllList = this.sw.getByClassCode(classCode);
			if (!jkDicAllList.isEmpty()) {
				jkDicAll.setClassName(((JkDicAll) jkDicAllList.get(0)).getClassName());
			}
		}

		modelMap.put("jkDicAll", jkDicAll);
		jkDicAllList = this.sw.findDictType();
		modelMap.put("jkDicAllList", jkDicAllList);
		return "intervene/jkDicAllEdit";
	}

	@RequestMapping({"/jkDicAll/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "监测项目管理--删除监测项目")
	public void c(HttpServletRequest request, HttpServletResponse response, String orderCode) {
		Result result = null;

		try {
			result = new Result();
			this.sw.delete(orderCode);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/jkDicAll/f_json/save"})
	@ResponseBody
	@SqlLog(p = "监测项目管理--保存监测项目")
	public void b(HttpServletRequest request, HttpServletResponse response, JkDicAll jkDicAll) {
		Result result = null;

		try {
			result = new Result();
			if (ab.isEmpty(jkDicAll.getOrderCode())) {
				jkDicAll.setUpdTime(new Date());
				jkDicAll.setUpdateTime(new Date());
				jkDicAll.setFlag(Long.valueOf(1L));
				jkDicAll.setOrderUse("1");
				jkDicAll.setOrderVisible("1");
				jkDicAll.setUpdateUserId(this.d(request).getUsername());
				this.sw.save(jkDicAll);
			} else {
				jkDicAll.setUpdTime(new Date());
				jkDicAll.setUpdateTime(new Date());
				jkDicAll.setUpdateUserId(this.d(request).getUsername());
				this.sw.update(jkDicAll);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}