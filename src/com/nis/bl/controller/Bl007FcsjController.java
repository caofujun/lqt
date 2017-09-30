package com.nis.bl.controller;

import com.nis.bl.entity.Bl007Fcsj;
import com.nis.bl.service.Bl007FcsjService;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.param.service.SysParamService;
import java.util.Date;
import java.util.List;
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
public class Bl007FcsjController extends BaseController {
	private static final Logger c = Logger.getLogger(Bl007FcsjController.class);
	@Autowired
	private Bl007FcsjService cX;
	@Autowired
	private SysParamService j;

	@RequestMapping({"/bl007Fcsj/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "bl/bl007FcsjList";
	}

	@RequestMapping({"/bl007Fcsj/f_view/query"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Bl007Fcsj bl007Fcsj) {
		MyPage drugPage = this.cX.a(bl007Fcsj);
		this.b(response, drugPage.getRows());
	}

	@RequestMapping({"/bl007Fcsj/f_view/list"})
	@ResponseBody
	@SqlLog(p = "职业暴露--近7天复查人员列表")
	public void b(HttpServletRequest request, HttpServletResponse response, Bl007Fcsj bl007Fcsj) {
		if (bl007Fcsj.getDays() == null) {
			bl007Fcsj.setDays(Integer.valueOf(this.j.findByParamCode(Param.NIS_BL_FC_DAYS)));
		}

		List bl007FcsjList = this.cX.getFcList(bl007Fcsj.getDays());
		this.a(response, bl007FcsjList);
	}

	@RequestMapping({"/bl007Fcsj/f_json/pageQuery"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, Bl007Fcsj bl007Fcsj) {
		MyPage page = this.cX.a(bl007Fcsj);
		this.b(response, page);
	}

	@RequestMapping({"/bl007Fcsj/f_view/toedit"})
	public String c(HttpServletRequest request, ModelMap modelMap, String id) {
		if (StringUtils.isNotBlank(id)) {
			Bl007Fcsj bl007Fcsj = this.cX.get(id);
			modelMap.put("bl007Fcsj", bl007Fcsj);
		}

		return "bl/bl007FcsjEdit";
	}

	@RequestMapping({"/bl007Fcsj/f_view/tonote"})
	public String d(HttpServletRequest request, ModelMap modelMap, String id) {
		return "bl/bl007FcsjNote";
	}

	@RequestMapping({"/bl007Fcsj/f_json/save"})
	@ResponseBody
	public void d(HttpServletRequest request, HttpServletResponse response, Bl007Fcsj bl007Fcsj) {
		Result result = null;

		try {
			result = new Result();
			if (this.cX.get(bl007Fcsj.getBlId()) == null) {
				this.cX.save(bl007Fcsj);
			} else {
				this.cX.update(bl007Fcsj);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/bl007Fcsj/f_json/delete"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, String blId) {
		Result result = null;

		try {
			result = new Result();
			this.cX.delete(blId);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/bl007Fcsj/f_json/findByTime"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Integer day, String startDate,
			String endDate) {
		if (ab.isEmpty(startDate)) {
			startDate = f.formatDate(new Date());
			endDate = f.formatDate(f.a(new Date(), day.intValue()));
		}

		List JyjgList = this.cX.findByTime(startDate, endDate);
		this.b(response, JyjgList);
	}

	@RequestMapping({"/bl007Fcsj/f_adapter/sendFcMsg"})
	@ResponseBody
	public void l(HttpServletRequest request, HttpServletResponse response) {
		Result result = null;

		try {
			result = new Result();
			this.cX.o();
			result.setResult("success");
		} catch (Exception arg4) {
			c.error("获取信息异常!", arg4);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}