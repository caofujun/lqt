package com.nis.pdca.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.as;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.intervene.entity.FxPatientZb;
import com.nis.intervene.service.FxPatientZbService;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.service.St003CryxxbService;
import com.nis.pdca.entity.ZlPdcaPlans;
import com.nis.pdca.entity.ZlPdcaPlansDetail;
import com.nis.pdca.service.ZlPdcaFlowService;
import com.nis.pdca.service.ZlPdcaPlansDetailService;
import com.nis.pdca.service.ZlPdcaPlansService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

@Controller
public class ZlPdcaPlansController extends BaseController {
	private static final Logger c = Logger.getLogger(ZlPdcaPlansController.class);
	@Autowired
	private ZlPdcaPlansService su;
	@Autowired
	private ZlPdcaPlansDetailService wB;
	@Autowired
	private ZlPdcaFlowService wz;
	@Autowired
	private FreeMarkerConfig rn;
	@Autowired
	private St003CryxxbService bg;
	@Autowired
	private FxPatientZbService ss;

	@RequestMapping({"/zlPdcaPlans/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		modelMap.put("unitId", this.c(request));
		return "pdca/zlPdcaPlansList";
	}

	@RequestMapping({"/zlPdcaPlans/f_json/pageQuery"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, ZlPdcaPlans zlPdcaPlans) {
		if (zlPdcaPlans.getStartDate() != null && !"".equals(zlPdcaPlans.getStartDate())) {
			zlPdcaPlans.setStartDate(zlPdcaPlans.getStartDate() + " 00:00");
		}

		if (zlPdcaPlans.getEndDate() != null && !"".equals(zlPdcaPlans.getEndDate())) {
			zlPdcaPlans.setEndDate(zlPdcaPlans.getEndDate() + " 23:59");
		}

		if (zlPdcaPlans.getSearchString() != null && !"".equals(zlPdcaPlans.getSearchString())) {
			zlPdcaPlans.setSearchString(ab.aR(zlPdcaPlans.getSearchString()));
		}

		MyPage page = this.su.b(zlPdcaPlans);
		this.b(response, page);
	}

	@RequestMapping({"/zlPdcaPlans/f_view/toedit"})
	public String e(HttpServletRequest request, ModelMap modelMap, String puid, String pzId) {
		if (ab.isNotEmpty(puid)) {
			ZlPdcaPlans zlPdcaPlans = this.su.get(puid);
			List zlPdcaPlansDetailList = this.wB.getByPuid(puid);
			modelMap.put("zlPdcaPlans", zlPdcaPlans);
			modelMap.put("zlPdcaPlansDetailList", zlPdcaPlansDetailList);
		}

		modelMap.put("unitId", this.c(request));
		modelMap.put("pzId", pzId);
		return "pdca/zlPdcaPlansEdit";
	}

	@RequestMapping({"/zlPdcaPlans/f_view/toeditIframe"})
	public String C(HttpServletRequest request, ModelMap modelMap, String puid, String pzId) {
		modelMap.put("puid", puid);
		modelMap.put("pzId", pzId);
		return "pdca/zlPdcaPlansEditIframe";
	}

	@RequestMapping({"/zlPdcaPlans/f_json/save"})
	@ResponseBody
	public void b(HttpServletRequest request, HttpServletResponse response, ZlPdcaPlans zlPdcaPlans) {
		Result result = null;

		try {
			result = new Result();
			if ("0".equals(zlPdcaPlans.getSaveOrAs())) {
				if (ab.isEmpty(zlPdcaPlans.getPuid())) {
					zlPdcaPlans.setCreaterId(this.d(request).getUsername());
					zlPdcaPlans.setCreateDate(new Date());
					zlPdcaPlans.setCreaterName(this.d(request).getRealname());
					FxPatientZb e = this.ss.get(zlPdcaPlans.getPzId());
					if (e != null) {
						St003Cryxxb st003Cryxxb = this.bg.get(e.getZyId());
						st003Cryxxb.setPdcaStatus(as.kX.getValue());
						this.bg.update(st003Cryxxb);
					}

					this.su.save(zlPdcaPlans);
				} else {
					this.su.update(zlPdcaPlans);
				}
			} else {
				zlPdcaPlans.setCreaterId(this.d(request).getUsername());
				this.wz.a(zlPdcaPlans);
			}

			result.setResult("success");
		} catch (Exception arg6) {
			c.error("获取信息异常!", arg6);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/zlPdcaPlans/f_json/delete"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, String puid) {
		Result result = null;

		try {
			result = new Result();
			this.su.delete(puid);
			result.setResult("success");
		} catch (Exception arg5) {
			c.error("获取信息异常!", arg5);
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	@RequestMapping({"/zlPdcaPlans/f_json/exportWord"})
	@ResponseBody
	public void az(HttpServletRequest request, HttpServletResponse response, String puid) throws IOException {
		String fileName = "导出的文档.doc";
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/vnd.ms-word");
		response.setHeader("content-disposition",
				"attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO-8859-1"));
		Configuration configuration = this.rn.getConfiguration();
		configuration.setDefaultEncoding("UTF-8");
		Template t = null;
		BufferedWriter out = null;
		HashMap dataMap = new HashMap();
		this.l(dataMap, puid);

		try {
			t = configuration.getTemplate("homepage/template/wordModel.ftl");
			out = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "utf-8"));
			t.process(dataMap, out);
		} catch (TemplateException arg19) {
			arg19.printStackTrace();
		} catch (IOException arg20) {
			arg20.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException arg18) {
				arg18.printStackTrace();
			}

		}

	}

	private void l(Map<String, Object> dataMap, String puid) {
		ZlPdcaPlans zlPdcaPlans = this.su.get(puid);
		List zlPdcaPlansDetailList = this.wB.getByPuid(puid);
		dataMap.put("title", zlPdcaPlans.getPlanName());
		dataMap.put("data", f.formatDate(zlPdcaPlans.getCreateDate()));
		dataMap.put("deptname", zlPdcaPlans.getDeptName());
		dataMap.put("weave", zlPdcaPlans.getCreaterName());
		ArrayList list = new ArrayList();

		HashMap map;
		for (Iterator arg6 = zlPdcaPlansDetailList.iterator(); arg6.hasNext(); list.add(map)) {
			ZlPdcaPlansDetail plansDetail = (ZlPdcaPlansDetail) arg6.next();
			map = new HashMap();
			map.put("num", ab.aP(plansDetail.getStepName()));
			map.put("content", ab.aP(plansDetail.getStepContent()));
			if (plansDetail.getStatus() == null) {
				map.put("statusName", "未完成");
			} else if (plansDetail.getStatus().equals("0")) {
				map.put("statusName", "未完成");
			} else if (plansDetail.getStatus().equals("1")) {
				map.put("statusName", "已完成");
			} else {
				map.put("statusName", "未完成");
			}
		}

		dataMap.put("list", list);
	}
}