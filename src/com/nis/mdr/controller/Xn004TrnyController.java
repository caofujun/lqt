package com.nis.mdr.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.mdr.entity.Xn004Trny;
import com.nis.mdr.service.Xn004TrnyService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Xn004TrnyController extends BaseController {
	private static final Logger c = Logger.getLogger(Xn004TrnyController.class);
	@Autowired
	private Xn004TrnyService ci;

	@RequestMapping({"/xn004Trny/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "mdr/xn004TrnyList";
	}

	@RequestMapping({"/xn004Trny/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "天然耐药字典--字典列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Xn004Trny xn004Trny) {
		if (xn004Trny.getSearchString() != null && !"".equals(xn004Trny.getSearchString())) {
			xn004Trny.setSearchString(ab.aR(xn004Trny.getSearchString()));
		}

		MyPage page = this.ci.a(xn004Trny);
		this.a(response, page);
	}

	@RequestMapping({"/xn004Trny/f_view/toedit"})
	@SqlLog(p = "天然耐药字典--字典详情")
	public String a(HttpServletRequest request, ModelMap modelMap, String pathogenId, String drugId, String isAdd) {
		if (ab.isNotEmpty(pathogenId) && ab.isNotEmpty(drugId)) {
			Xn004Trny xn004Trny = this.ci.get(pathogenId, drugId);
			modelMap.put("xn004Trny", xn004Trny);
		}

		modelMap.put("isAdd", isAdd);
		return "mdr/xn004TrnyEdit";
	}

	@RequestMapping({"/xn004Trny/f_json/save"})
	@ResponseBody
	@SqlLog(p = "天然耐药字典--保存字典")
	public void a(HttpServletRequest request, HttpServletResponse response, Xn004Trny xn004Trny, String isAdd) {
		Result result = null;

		try {
			result = new Result();
			if (isAdd.equals("1")) {
				Xn004Trny e = this.ci.get(xn004Trny.getPathogenId(), xn004Trny.getDrugId());
				if (e == null) {
					this.ci.save(xn004Trny);
					result.setResult("success");
				} else {
					result.setResult("error");
					result.setMsg("已存在相同项目！");
				}
			} else {
				this.ci.update(xn004Trny);
				result.setResult("success");
			}
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/xn004Trny/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "天然耐药字典--删除字典")
	public void f(HttpServletRequest request, HttpServletResponse response, String pathogenId, String drugId) {
		Result result = null;

		try {
			result = new Result();
			this.ci.delete(pathogenId, drugId);
			result.setResult("success");
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}