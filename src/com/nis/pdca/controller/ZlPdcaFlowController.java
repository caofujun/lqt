package com.nis.pdca.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.pdca.entity.ZlPdcaFlow;
import com.nis.pdca.service.ZlPdcaFlowDetailService;
import com.nis.pdca.service.ZlPdcaFlowService;
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
public class ZlPdcaFlowController extends BaseController {
	private static final Logger c = Logger.getLogger(ZlPdcaFlowController.class);
	@Autowired
	private ZlPdcaFlowService wz;
	@Autowired
	private ZlPdcaFlowDetailService wA;

	@RequestMapping({"/zlPdcaFlow/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		return "pdca/zlPdcaFlowList";
	}

	@RequestMapping({"/zlPdcaFlow/f_json/pageQuery"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, ZlPdcaFlow zlPdcaFlow) {
		if (zlPdcaFlow.getStartDate() != null && !"".equals(zlPdcaFlow.getStartDate())) {
			zlPdcaFlow.setStartDate(zlPdcaFlow.getStartDate() + " 00:00");
		}

		if (zlPdcaFlow.getEndDate() != null && !"".equals(zlPdcaFlow.getEndDate())) {
			zlPdcaFlow.setEndDate(zlPdcaFlow.getEndDate() + " 23:59");
		}

		MyPage page = this.wz.a(zlPdcaFlow);
		this.b(response, page);
	}

	@RequestMapping({"/zlPdcaFlow/f_json/list"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, ZlPdcaFlow zlPdcaFlow) {
		if (zlPdcaFlow.getStartDate() != null && !"".equals(zlPdcaFlow.getStartDate())) {
			zlPdcaFlow.setStartDate(zlPdcaFlow.getStartDate() + " 00:00");
		}

		if (zlPdcaFlow.getEndDate() != null && !"".equals(zlPdcaFlow.getEndDate())) {
			zlPdcaFlow.setEndDate(zlPdcaFlow.getEndDate() + " 23:59");
		}

		if (zlPdcaFlow.getSearchString() != null && !"".equals(zlPdcaFlow.getSearchString())) {
			zlPdcaFlow.setSearchString(ab.aR(zlPdcaFlow.getSearchString()));
		}

		List list = this.wz.findZlPdcaFlowList(zlPdcaFlow);
		this.b(response, list);
	}

	@RequestMapping({"/zlPdcaFlow/f_view/toedit"})
	public String c(HttpServletRequest request, ModelMap modelMap, String fuid) {
		if (ab.isNotEmpty(fuid)) {
			ZlPdcaFlow zlPdcaFlow = this.wz.get(fuid);
			modelMap.put("zlPdcaFlow", zlPdcaFlow);
		}

		return "pdca/zlPdcaFlowEdit";
	}

	@RequestMapping({"/zlPdcaFlow/f_json/save"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, ZlPdcaFlow zlPdcaFlow) {
		Result result = null;

		try {
			result = new Result();
			if (ab.isEmpty(zlPdcaFlow.getFuid())) {
				zlPdcaFlow.setFlowCreatetime(new Date());
				zlPdcaFlow.setCreateId(this.d(request).getUsername());
				this.wz.save(zlPdcaFlow);
			} else {
				this.wz.update(zlPdcaFlow);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/zlPdcaFlow/f_json/stop"})
	@ResponseBody
	public void av(HttpServletRequest request, HttpServletResponse response, String fuid) {
		Result result = null;

		try {
			result = new Result();
			ZlPdcaFlow e = this.wz.get(fuid);
			e.setStatus(Long.valueOf(0L));
			this.wz.update(e);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/zlPdcaFlow/f_json/start"})
	@ResponseBody
	public void aw(HttpServletRequest request, HttpServletResponse response, String fuid) {
		Result result = null;

		try {
			result = new Result();
			ZlPdcaFlow e = this.wz.get(fuid);
			e.setStatus(Long.valueOf(1L));
			this.wz.update(e);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/zlPdcaFlow/f_json/delete"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, String fuid) {
		Result result = null;

		try {
			result = new Result();
			this.wz.delete(fuid);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/zlPdcaFlow/f_json/getAll"})
	@ResponseBody
	public void ax(HttpServletRequest request, HttpServletResponse response, String q) {
		ZlPdcaFlow zlPdcaFlow = new ZlPdcaFlow();
		zlPdcaFlow.setSearchString(q);
		List page = this.wz.getAll(zlPdcaFlow);
		this.b(response, page);
	}

	@RequestMapping({"/zlPdcaFlow/f_json/get"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, String fuid) {
		if (ab.isNotEmpty(fuid)) {
			ZlPdcaFlow zlPdcaFlow = this.wz.get(fuid);
			List zlPdcaFlowDetailList = this.wA.getByFuid(fuid);
			zlPdcaFlow.setZlPdcaFlowDetailList(zlPdcaFlowDetailList);
			this.b(response, zlPdcaFlow);
		}

	}
}