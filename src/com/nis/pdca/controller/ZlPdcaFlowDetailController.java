package com.nis.pdca.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.ab;
import com.nis.pdca.entity.ZlPdcaFlowDetail;
import com.nis.pdca.service.ZlPdcaFlowDetailService;
import java.util.Iterator;
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
public class ZlPdcaFlowDetailController extends BaseController {
	private static final Logger c = Logger.getLogger(ZlPdcaFlowDetailController.class);
	@Autowired
	private ZlPdcaFlowDetailService wA;

	@RequestMapping({"/zlPdcaFlowDetail/f_view/index"})
	public String p(HttpServletRequest request, ModelMap modelMap, String fuid) {
		modelMap.put("fuid", fuid);
		return "pdca/zlPdcaFlowDetailList";
	}

	@RequestMapping({"/zlPdcaFlowDetail/f_json/pageQuery"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, ZlPdcaFlowDetail zlPdcaFlowDetail) {
		MyPage page = this.wA.a(zlPdcaFlowDetail);
		this.b(response, page);
	}

	@RequestMapping({"/zlPdcaFlowDetail/f_json/list"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, ZlPdcaFlowDetail zlPdcaFlowDetail) {
		List page = this.wA.findZlPdcaFlowDetailList(zlPdcaFlowDetail);
		this.b(response, page);
	}

	@RequestMapping({"/zlPdcaFlowDetail/f_view/toedit"})
	public String e(HttpServletRequest request, ModelMap modelMap, String fdSubid, String fuid) {
		ZlPdcaFlowDetail zlPdcaFlowDetail;
		if (ab.isNotEmpty(fdSubid)) {
			zlPdcaFlowDetail = this.wA.get(fdSubid);
			modelMap.put("zlPdcaFlowDetail", zlPdcaFlowDetail);
		} else {
			zlPdcaFlowDetail = new ZlPdcaFlowDetail();
			zlPdcaFlowDetail.setFuid(fuid);
			modelMap.put("zlPdcaFlowDetail", zlPdcaFlowDetail);
		}

		return "pdca/zlPdcaFlowDetailEdit";
	}

	@RequestMapping({"/zlPdcaFlowDetail/f_json/save"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, ZlPdcaFlowDetail zlPdcaFlowDetail) {
		Result result = null;

		try {
			result = new Result();
			if (ab.isEmpty(zlPdcaFlowDetail.getFdSubid())) {
				this.wA.save(zlPdcaFlowDetail);
			} else {
				this.wA.update(zlPdcaFlowDetail);
			}

			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/zlPdcaFlowDetail/f_json/delete"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, String fdSubid) {
		Result result = null;

		try {
			result = new Result();
			this.wA.delete(fdSubid);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/zlPdcaFlowDetail/f_json/move"})
	@ResponseBody
	public void ay(HttpServletRequest request, HttpServletResponse response, String fdSubid) {
		Result result = null;

		try {
			result = new Result();
			ZlPdcaFlowDetail e = this.wA.get(fdSubid);
			if (e.getXh() != null) {
				e.setXh(Long.valueOf(e.getXh().longValue() - 1L));
				List pdcaFlowDetailList = this.wA.getByFuid(e.getFuid());
				Iterator arg7 = pdcaFlowDetailList.iterator();

				while (arg7.hasNext()) {
					ZlPdcaFlowDetail detail = (ZlPdcaFlowDetail) arg7.next();
					if (detail.getXh().equals(e.getXh())) {
						detail.setXh(Long.valueOf(detail.getXh().longValue() + 1L));
						this.wA.update(detail);
					}
				}
			}

			this.wA.update(e);
			result.setResult("success");
		} catch (Exception arg8) {
			c.error("获取信息异常!", arg8);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}
}