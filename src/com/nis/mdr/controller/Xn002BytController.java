package com.nis.mdr.controller;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.mdr.entity.Xn002Byt;
import com.nis.mdr.service.Xn002BytService;
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
public class Xn002BytController extends BaseController {
	private static final Logger c = Logger.getLogger(Xn002BytController.class);
	@Autowired
	private Xn002BytService tQ;

	@RequestMapping({"/xn002Byt/f_view/query"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String bactGenusId, String q, int page,
			int size, String isByt) {
		Xn002Byt xn002Byt = new Xn002Byt();
		xn002Byt.setSearchString(ab.aR(q));
		xn002Byt.setPage(Integer.valueOf(page));
		xn002Byt.setSize(Integer.valueOf(size));
		xn002Byt.setIsByt(isByt);
		MyPage drugPage = this.tQ.a(xn002Byt);
		this.b(response, drugPage.getRows());
	}

	@RequestMapping({"/xn002Byt/f_view/xn002BytLists"})
	public String A(HttpServletRequest request, ModelMap modelMap) {
		return "mdr/xn002BytLists";
	}

	@RequestMapping({"/xn002Byt/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "mdr/xn002BytIndex";
	}

	@RequestMapping({"/xn002Byt/f_json/pageQuery"})
	@ResponseBody
	@SqlLog(p = "标准病原体--病原体列表")
	public void a(HttpServletRequest request, HttpServletResponse response, Xn002Byt xn002Byt) {
		if (xn002Byt.getSearchString() != null && !"".equals(xn002Byt.getSearchString())) {
			xn002Byt.setSearchString(ab.aR(xn002Byt.getSearchString()));
		}

		MyPage Xn002BytPage = this.tQ.c(xn002Byt);
		this.a(response, Xn002BytPage);
	}

	@RequestMapping({"/xn002Byt/f_view/edit"})
	@SqlLog(p = "标准病原体--病原体详情")
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (id != null && id != "") {
			Xn002Byt xn002Byt = new Xn002Byt();
			xn002Byt.setPathogenId(id);
			Xn002Byt Xn002Byttemp = this.tQ.findXn002BytEdit(xn002Byt);
			modelMap.put("Xn002Byt", Xn002Byttemp);
		}

		return "mdr/xn002BytEdit";
	}

	@RequestMapping({"/xn002Byt/f_json/delete"})
	@ResponseBody
	@SqlLog(p = "标准病原体--删除病原体")
	public void c(HttpServletRequest request, HttpServletResponse response, String id) {
		Result result = null;

		try {
			result = new Result();
			this.tQ.delete(id);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/xn002Byt/f_json/save"})
	@ResponseBody
	@SqlLog(p = "标准病原体--保存病原体")
	public void b(HttpServletRequest request, HttpServletResponse response, Xn002Byt xn002Byt) {
		Result result = null;

		try {
			result = new Result();
			if (xn002Byt.getPathogenId() != null) {
				xn002Byt.setPathogenId(xn002Byt.getPathogenId().trim());
			}

			if (this.tQ.get(xn002Byt.getPathogenId()) == null) {
				this.tQ.save(xn002Byt);
			} else {
				this.tQ.update(xn002Byt);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/xn002Byt/f_json/glsfl"})
	@ResponseBody
	public void I(HttpServletRequest request, HttpServletResponse response) {
		List listMap = this.tQ.getGlsfl();
		this.a(response, listMap);
	}

	@RequestMapping({"/xn002Byt/f_view/queryCount"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, String bactGenusId, String q,
			String startDate, String endDate, int page, int size) {
		Xn002Byt xn002Byt = new Xn002Byt();
		xn002Byt.setSearchString(ab.aR(q));
		xn002Byt.setPage(Integer.valueOf(page));
		xn002Byt.setSize(Integer.valueOf(size));
		xn002Byt.setStartDate(startDate);
		xn002Byt.setEndDate(endDate);
		MyPage drugPage = this.tQ.b(xn002Byt);
		this.b(response, drugPage.getRows());
	}

	@RequestMapping({"/xn002Byt/f_json/getAll"})
	@ResponseBody
	public void w(HttpServletRequest request, HttpServletResponse response) {
		List xn002Byts = this.tQ.getAll();
		this.a(response, xn002Byts);
	}
}