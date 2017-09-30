package com.nis.pdca.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.pdca.entity.ZlPdcaPlans;
import com.nis.pdca.entity.ZlPdcaPlansDetail;
import com.nis.pdca.service.ZlPdcaPlansDetailService;
import com.nis.pdca.service.ZlPdcaPlansService;
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
public class ZlPdcaPlansDetailController extends BaseController {
	private static final Logger c = Logger.getLogger(ZlPdcaPlansDetailController.class);
	@Autowired
	private ZlPdcaPlansDetailService wB;
	@Autowired
	private ZlPdcaPlansService su;

	@RequestMapping({"/zlPdcaPlansDetail/f_view/index"})
	public String p(HttpServletRequest request, ModelMap modelMap, String puid) {
		modelMap.put("puid", puid);
		ZlPdcaPlans zlPdcaPlans = this.su.get(puid);
		List zlPdcaPlansDetailList = this.wB.getByPuid(puid);
		modelMap.put("zlPdcaPlans", zlPdcaPlans);
		modelMap.put("zlPdcaPlansDetailList", zlPdcaPlansDetailList);
		return "pdca/zlPdcaPlansDetailList";
	}

	@RequestMapping({"/zlPdcaPlansDetail/f_json/pageQuery"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, ZlPdcaPlansDetail zlPdcaPlansDetail) {
		MyPage page = this.wB.a(zlPdcaPlansDetail);
		this.b(response, page);
	}

	@RequestMapping({"/zlPdcaPlansDetail/f_view/toedit"})
	public String e(HttpServletRequest request, ModelMap modelMap, String pdSubid, String puid) {
		ZlPdcaPlansDetail zlPdcaPlansDetail;
		if (ab.isNotEmpty(pdSubid)) {
			zlPdcaPlansDetail = this.wB.get(pdSubid);
			modelMap.put("zlPdcaPlansDetail", zlPdcaPlansDetail);
		} else {
			zlPdcaPlansDetail = new ZlPdcaPlansDetail();
			zlPdcaPlansDetail.setPuid(puid);
			modelMap.put("zlPdcaPlansDetail", zlPdcaPlansDetail);
		}

		return "pdca/zlPdcaPlansDetailEdit";
	}

	@RequestMapping({"/zlPdcaPlansDetail/f_json/save"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, ZlPdcaPlansDetail zlPdcaPlansDetail) {
		Result result = null;

		try {
			result = new Result();
			if (ab.isEmpty(zlPdcaPlansDetail.getPdSubid())) {
				this.wB.save(zlPdcaPlansDetail);
			} else {
				this.wB.update(zlPdcaPlansDetail);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/zlPdcaPlansDetail/f_json/delete"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, String pdSubid) {
		Result result = null;

		try {
			result = new Result();
			this.wB.delete(pdSubid);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}