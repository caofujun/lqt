package com.nis.intervene.controller;

import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.bo;
import com.nis.comm.utils.f;
import com.nis.intervene.entity.FxPatientZb;
import com.nis.intervene.service.FxPatientZbService;
import com.nis.intervene.service.FxZhibiaoService;
import com.nis.patient.entity.St002Zdxxb;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.entity.St004Yzxxb;
import com.nis.patient.entity.St005Ssxxb;
import com.nis.patient.entity.St011Syjgb;
import com.nis.patient.service.St002ZdxxbService;
import com.nis.patient.service.St003CryxxbService;
import com.nis.patient.service.St004YzxxbService;
import com.nis.patient.service.St005SsxxbService;
import com.nis.patient.service.St009SjbbService;
import com.nis.patient.service.St011SyjgbService;
import com.nis.pdca.entity.ZlPdcaPlans;
import com.nis.pdca.service.ZlPdcaPlansService;
import com.nis.questionnaire.entity.QsSurveyRecord;
import com.nis.questionnaire.service.QsSurveyRecordService;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FxPatientZbController extends BaseController {
	@Autowired
	private FxPatientZbService ss;
	@Autowired
	private FxZhibiaoService st;
	@Autowired
	private St002ZdxxbService bv;
	@Autowired
	private St003CryxxbService bg;
	@Autowired
	private St004YzxxbService bu;
	@Autowired
	private St011SyjgbService bH;
	@Autowired
	private St005SsxxbService bO;
	@Autowired
	private St009SjbbService bE;
	@Autowired
	private ZlPdcaPlansService su;
	@Autowired
	private QsSurveyRecordService sv;

	@RequestMapping({"/fxPatientZb/f_view/edit"})
	public String y(HttpServletRequest request, ModelMap modelMap, String pzId) {
		FxPatientZb fxPatientZb = this.ss.get(pzId);
		St003Cryxxb cryxxb = this.bg.get(fxPatientZb.getZyId());
		List zlPdcaPlansList = this.su.findByPzId(pzId);
		List surveyRecordList = this.sv.findByzyId(fxPatientZb.getZyId(), f.formatDate(fxPatientZb.getStartDate()));
		modelMap.put("fxPatientZb", fxPatientZb);
		modelMap.put("cryxxb", cryxxb);
		modelMap.put("zlPdcaPlansList", zlPdcaPlansList);
		modelMap.put("surveyRecordList", surveyRecordList);
		if (bo.oI.getValue().equals(fxPatientZb.getZbType())) {
			St002Zdxxb st005Ssxxb3 = this.bv.get(fxPatientZb.getCaseId());
			modelMap.put("st002Zdxxb", st005Ssxxb3);
			return "intervene/zhenduan";
		} else if (bo.oJ.getValue().equals(fxPatientZb.getZbType())) {
			St004Yzxxb st005Ssxxb2 = this.bu.get(fxPatientZb.getCaseId());
			modelMap.put("st004Yzxx", st005Ssxxb2);
			return "intervene/yizhu";
		} else if (bo.oK.getValue().equals(fxPatientZb.getZbType())) {
			St011Syjgb st005Ssxxb1 = this.bH.get(fxPatientZb.getCaseId());
			List st009SjbbList = this.bE.findSjbbListBytestNo(st005Ssxxb1.getTestOrderNo());
			if (st009SjbbList.size() > 0) {
				modelMap.put("st009Sjbb", st009SjbbList.get(0));
			}

			modelMap.put("st011Syjgb", st005Ssxxb1);
			return "intervene/jianyan";
		} else if (bo.oL.getValue().equals(fxPatientZb.getZbType())) {
			St005Ssxxb st005Ssxxb = this.bO.get(fxPatientZb.getCaseId());
			modelMap.put("st005Ssxxb", st005Ssxxb);
			return "intervene/shoushu";
		} else {
			return "intervene/zhenduan";
		}
	}

	@RequestMapping({"/fxPatientZb/f_view/getRid"})
	public String z(HttpServletRequest request, ModelMap modelMap, String rid) {
		QsSurveyRecord qsSurveyRecord = this.sv.get(rid);
		modelMap.put("qsSurveyRecord", qsSurveyRecord);
		return "redirect:/qsSurveyRecord/f_view/toeditIframe.shtml?id=" + qsSurveyRecord.getRid();
	}

	@RequestMapping({"/fxPatientZb/f_view/getPdcaBypzId"})
	public void b(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String pzId) {
		Result result = new Result();
		String pdcaNames = "【PDCA:";
		List zlPdcaPlansList = this.su.findByPzId(pzId);

		ZlPdcaPlans pdcaPlans;
		for (Iterator arg8 = zlPdcaPlansList.iterator(); arg8
				.hasNext(); pdcaNames = pdcaNames + pdcaPlans.getPlanName() + ",") {
			pdcaPlans = (ZlPdcaPlans) arg8.next();
		}

		pdcaNames = pdcaNames + "】";
		result.setMsg(pdcaNames);
		result.setResult("success");
		this.a(response, result);
	}

	@RequestMapping({"/fxPatientZb/f_view/getQsBypzId"})
	public void c(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String pzId) {
		Result result = new Result();
		String qsNames = "";
		FxPatientZb fxPatientZb = this.ss.get(pzId);
		St003Cryxxb cryxxb = this.bg.get(fxPatientZb.getZyId());
		List surveyRecordList = this.sv.findByzyId(fxPatientZb.getZyId(), f.formatDate(fxPatientZb.getStartDate()));

		QsSurveyRecord qs;
		for (Iterator arg10 = surveyRecordList.iterator(); arg10.hasNext(); qsNames = qsNames + qs.getTitle() + ",") {
			qs = (QsSurveyRecord) arg10.next();
		}

		result.setMsg(qsNames);
		result.setResult("success");
		this.a(response, result);
	}
}