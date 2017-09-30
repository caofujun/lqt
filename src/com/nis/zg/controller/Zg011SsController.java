package com.nis.zg.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import com.nis.zg.entity.Zg011Ss;
import com.nis.zg.service.Zg011SsService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Zg011SsController extends BaseController {
	@Autowired
	private Zg011SsService wt;
	@Autowired
	private SysDictService p;

	@RequestMapping({"/zg011Ss/f_view/operMacth"})
	public String az(HttpServletRequest request, ModelMap modelMap) {
		return "dict/operMacth";
	}

	@RequestMapping({"/zg011Ss/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "手术匹配--匹配手术列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Zg011Ss zg011Ss) {
		if (zg011Ss.getSearchString() != null && !"".equals(zg011Ss.getSearchString())) {
			zg011Ss.setSearchString(ab.aR(zg011Ss.getSearchString()));
		}

		MyPage page = this.wt.a(zg011Ss);
		this.b(response, page);
	}

	@RequestMapping({"/zg011Ss/f_view/toedit"})
	public String c(HttpServletRequest request, ModelMap modelMap, String operId) {
		if (ab.isNotEmpty(operId)) {
			Zg011Ss zg011Ss = this.wt.getByOperId(operId);
			modelMap.put("zg011Ss", zg011Ss);
		}

		return "dict/zg011SsEdit";
	}

	@RequestMapping({"/zg011Ss/f_view/zdssquery"})
	public String aA(HttpServletRequest request, ModelMap modelMap) {
		return "dict/operationPoint";
	}

	@RequestMapping({"/zg011Ss/f_json/zdssDetail"})
	@ResponseBody
	public void f(HttpServletRequest request, HttpServletResponse response, SysDict sysDict) {
		MyPage page = null;
		sysDict.setDictTypeCode("operation_point");
		sysDict.setPage(Integer.valueOf(1));
		sysDict.setSize(Integer.valueOf(10));
		sysDict.setRows(Integer.valueOf(10));
		page = this.p.a(sysDict);
		this.a(response, page);
	}

	@RequestMapping({"/zg011Ss/f_json/save"})
	@ResponseBody
	@SqlLog(p = "手术匹配--保存匹配手术关联")
	public void b(HttpServletRequest request, HttpServletResponse response, Zg011Ss zg011Ss) {
		Result result = null;

		try {
			result = new Result();
			String e = zg011Ss.getImpOpeId();
			zg011Ss = this.wt.getByOperId(zg011Ss.getOperId());
			zg011Ss.setImpOpeId(e);
			this.wt.update(zg011Ss);
			result.setResult("success");
		} catch (Exception arg5) {
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/zg011Ss/f_json/query"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String q, int page, int size) {
		Zg011Ss zg011Ss = new Zg011Ss();
		zg011Ss.setSearchString(q);
		zg011Ss.setPage(Integer.valueOf(page));
		zg011Ss.setSize(Integer.valueOf(size));
		List list = this.wt.query(zg011Ss);
		this.b(response, list);
	}

	@RequestMapping({"/zg011Ss/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "手术匹配--取消匹配手术关联")
	public void c(HttpServletRequest request, HttpServletResponse response, String operId) {
		Result result = null;

		try {
			result = new Result();
			Zg011Ss e = this.wt.getByOperId(operId);
			e.setImpOpeId((String) null);
			this.wt.update(e);
			result.setResult("success");
		} catch (Exception arg5) {
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}