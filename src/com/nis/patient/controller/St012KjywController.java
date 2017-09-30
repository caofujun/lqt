package com.nis.patient.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.patient.entity.St012Kjyw;
import com.nis.patient.service.St012KjywService;
import java.util.Map;
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
public class St012KjywController extends BaseController {
	private static final Logger c = Logger.getLogger(St012KjywController.class);
	@Autowired
	private St012KjywService bM;

	@RequestMapping({"/st012Kjyw/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "预警抗菌药物管理--预警抗菌药物列表")
	public void a(HttpServletRequest request, HttpServletResponse response, St012Kjyw st012Kjyw) {
		if (st012Kjyw.getSearchString() != null && !"".equals(st012Kjyw.getSearchString())) {
			st012Kjyw.setSearchString(ab.aR(st012Kjyw.getSearchString()));
		}

		MyPage page = this.bM.a(st012Kjyw);
		this.a(response, page);
	}

	@RequestMapping({"/st012Kjyw/f_json/save"})
	@ResponseBody
	@SqlLog(p = "预警抗菌药物管理--保存预警抗菌药物")
	public void b(HttpServletRequest request, HttpServletResponse response, St012Kjyw st012Kjyw) {
		Result result = null;

		try {
			result = new Result();
			String e = st012Kjyw.getSyDrugId();
			st012Kjyw = this.bM.get(st012Kjyw.getId());
			st012Kjyw.setSyDrugId(e);
			this.bM.update(st012Kjyw);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/st012Kjyw/f_json/saveSt012Kjyw"})
	@ResponseBody
	@SqlLog(p = "预警抗菌药物管理--保存抗菌药物")
	public void c(HttpServletRequest request, HttpServletResponse response, St012Kjyw st012Kjyw) {
		Result result = null;

		try {
			result = new Result();
			if (this.bM.get(st012Kjyw.getId()) == null) {
				this.bM.save(st012Kjyw);
			} else {
				this.bM.update(st012Kjyw);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/st012Kjyw/f_json/match"})
	@ResponseBody
	public void d(HttpServletRequest request, HttpServletResponse response, St012Kjyw st012Kjyw) {
		Result result = null;

		try {
			result = new Result();
			Map e = this.bM.queryMatched();
			this.bM.match();
			result.setData(e);
			result.setResult("success");
		} catch (Exception arg5) {
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/st012Kjyw/f_view/toedit"})
	@SqlLog(p = "预警抗菌药物管理--预警抗菌药物详情")
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (ab.isNotEmpty(id)) {
			St012Kjyw st012Kjyw = this.bM.get(id);
			modelMap.put("st012Kjyw", st012Kjyw);
		}

		return "dict/st012KjywEdit";
	}

	@RequestMapping({"/st012Kjyw/f_view/toeditSt012Kjyw"})
	public String X(HttpServletRequest request, ModelMap modelMap, String id) {
		if (ab.isNotEmpty(id)) {
			St012Kjyw st012Kjyw = this.bM.get(id);
			modelMap.put("st012Kjyw", st012Kjyw);
		}

		return "dict/st012KjywEditNew";
	}

	@RequestMapping({"/st012Kjyw/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "预警抗菌药物管理--删除预警抗菌药物对照")
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			St012Kjyw e = this.bM.get(id);
			e.setSyDrugId((String) null);
			this.bM.update(e);
			result.setResult("success");
		} catch (Exception arg5) {
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/st012Kjyw/f_json/deleteSt012Kjyw"})
	@ResponseBody
	@SqlLog(p = "预警抗菌药物管理--删除预警抗菌药物")
	public void au(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.bM.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/st012Kjyw/f_json/updDrugLine"})
	@ResponseBody
	@SqlLog(p = "预警抗菌药物管理--更新预警抗菌药物级别")
	public void b(HttpServletRequest request, HttpServletResponse response, String id, Integer drugLine) {
		Result result = null;

		try {
			if (StringUtils.isBlank(id)) {
				result = new Result("error", "异常操作！");
			} else {
				this.bM.c(id, drugLine);
				result = new Result("success");
			}
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}