package com.nis.cdc.controller;

import com.nis.access.entity.AcAccount;
import com.nis.cdc.entity.CtgBk010Pesticide;
import com.nis.cdc.service.CtgBk010PesticideService;
import com.nis.cdc.service.CtgSys012PathologyService;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.utils.ab;
import com.nis.dict.service.SysDictService;
import com.nis.msg.service.NyMessageDetailService;
import com.nis.param.service.SysParamService;
import com.nis.patient.entity.St001Jbxxb;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.entity.St020ClinicPatients;
import com.nis.patient.service.St001JbxxbService;
import com.nis.patient.service.St003CryxxbService;
import com.nis.patient.service.St020ClinicPatientsService;
import com.nis.zg.service.Zg012Icd10Service;
import java.text.SimpleDateFormat;
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
public class PesticideController extends BaseController {
	@Autowired
	private SysDictService p;
	@Autowired
	private Zg012Icd10Service dG;
	@Autowired
	private SysParamService j;
	@Autowired
	private CtgBk010PesticideService dy;
	@Autowired
	private St003CryxxbService df;
	@Autowired
	private St001JbxxbService dg;
	@Autowired
	private St020ClinicPatientsService bh;
	@Autowired
	private NyMessageDetailService cV;
	@Autowired
	private CtgSys012PathologyService dC;
	private static final Logger c = Logger.getLogger(PesticideController.class);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat dz = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat dA = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	SimpleDateFormat dB = new SimpleDateFormat("yyMMddHHmmssSSS");

	@RequestMapping({"/cdc/f_view/nyzdReport"})
	@SqlLog(p = "传染病上报--农药中毒监测报卡详情")
	public String c(HttpServletRequest request, ModelMap modelMap, String zyid, String mzid, String masterid,
			String justLook, String type) {
		AcAccount account = (AcAccount) this.b(request);
		if (!"hospital".equals(account.getAcType())) {
			modelMap.put("acType", "doctor");
		} else {
			modelMap.put("acType", "hospital");
		}

		String gbcode = this.j.findByParamCode(Param.NIS_HOSPITAL_GBCODE);
		if (!ab.aM(gbcode)) {
			modelMap.put("HASheng", gbcode.substring(0, 2) + "000000");
			modelMap.put("HAShi", gbcode.substring(0, 4) + "0000");
			modelMap.put("HAXian", gbcode.substring(0, 6) + "00");
			modelMap.put("HAXiang", gbcode);
		}

		if (!ab.aM(masterid)) {
			CtgBk010Pesticide outcome = this.dy.get(masterid);
			modelMap.put("zdxx", outcome);
			if (outcome != null && ab.isNotEmpty(outcome.getNowaddrareacode())) {
				modelMap.put("curSheng", outcome.getNowaddrareacode().substring(0, 2) + "000000");
				modelMap.put("curShi", outcome.getNowaddrareacode().substring(0, 4) + "0000");
				modelMap.put("curXian", outcome.getNowaddrareacode().substring(0, 6) + "00");
				modelMap.put("curXiang", outcome.getNowaddrareacode());
			}

			modelMap.put("MSTID", outcome.getMasterid());
			modelMap.put("BKT", "nyzdbk");
			modelMap.put("flag", outcome.getFlag());
			if (!ab.aM(zyid)) {
				modelMap.put("patientType", "zy");
			} else if (!ab.aM(mzid)) {
				modelMap.put("patientType", "mz");
			}

			long poisoningReason = (long) outcome.getIsemptycard().intValue();
			if (poisoningReason > 0L) {
				modelMap.put("isEmptyCard", "Y");
			}
		} else if (!ab.aM(zyid)) {
			St003Cryxxb outcome1 = this.df.get(zyid);
			if (outcome1 != null && ab.isNotEmpty(outcome1.getPatientId())) {
				St001Jbxxb poisoningReason1 = this.dg.get(outcome1.getPatientId());
				if (poisoningReason1 != null) {
					if (!ab.aM(poisoningReason1.getTel())) {
						outcome1.setTel(poisoningReason1.getTel());
					}

					if (!ab.aM(poisoningReason1.getNation())) {
						outcome1.setNation(poisoningReason1.getNation());
					}

					if (poisoningReason1.getBirthDate() != null && outcome1.getBirthDate() == null) {
						outcome1.setBirthDate(poisoningReason1.getBirthDate());
					}

					if (poisoningReason1.getIdCardId() != null) {
						outcome1.setIdCard(poisoningReason1.getIdCardId());
					}

					if (poisoningReason1.getAddress() != null) {
						outcome1.setAddress(poisoningReason1.getAddress());
					}
				}
			} else {
				modelMap.put("errMsg", "未找到病人信息！");
			}

			modelMap.put("BRXX", outcome1);
			modelMap.put("patientType", "zy");
		} else if (!ab.aM(mzid)) {
			St020ClinicPatients outcome2 = this.bh.get(mzid);
			if (outcome2 == null) {
				modelMap.put("errMsg", "未找到病人信息！");
			}

			modelMap.put("BRXX", outcome2);
			modelMap.put("patientType", "mz");
		} else if (!ab.aM(type)) {
			String outcome3 = this.j.findByParamCode(Param.NIS_CDC_EMPTY_CARD_GENERATE_INFO);
			St020ClinicPatients poisoningReason2 = new St020ClinicPatients();
			if ("1".equals(outcome3)) {
				Date pesticideKind = new Date();
				poisoningReason2.setMzid("MZ" + this.dA.format(pesticideKind));
				poisoningReason2.setPatientId("EC" + this.dB.format(pesticideKind));
				poisoningReason2.setVisitId("1");
			}

			poisoningReason2.setIsemptycard(1);
			modelMap.put("BRXX", poisoningReason2);
			modelMap.put("isEmptyCard", "Y");
			modelMap.put("patientType", "mz");
		}

		List outcome4 = this.p.u("lapse_to", "0");
		modelMap.put("outcome", outcome4);
		List poisoningReason3 = this.p.u("pp_poisoningReason", "0");
		modelMap.put("poisoningReason", poisoningReason3);
		List pesticideKind1 = this.p.u("pp_pesticideKind", "0");
		modelMap.put("pesticideKind", pesticideKind1);
		List poisoningNum = this.p.u("pp_poisoningNum", "0");
		modelMap.put("poisoningNum", poisoningNum);
		List riskBehavior = this.p.u("pp_riskBehavior", "0");
		modelMap.put("riskBehavior", riskBehavior);
		List patientBelong = this.p.u("cdc_patient_belong", "0");
		modelMap.put("patientBelong", patientBelong);
		List drugWay = this.p.u("pp_drugWay", "0");
		modelMap.put("drugWay", drugWay);
		List train = this.p.u("pp_train", "0");
		modelMap.put("train", train);
		modelMap.put("RDFD", this.j.findByParamCode(Param.NIS_CDC_RD_VALUE_FROM_DOCTOR));
		if (!ab.aM(justLook)) {
			modelMap.put("justLook", "Y");
		}

		return "cards/nyzdbk";
	}

	@RequestMapping({"/cdc/f_json/savePesticideCard"})
	@ResponseBody
	@SqlLog(p = "传染病上报--保存农药中毒监测报卡")
	public void a(HttpServletRequest request, HttpServletResponse response, CtgBk010Pesticide ctgBk010Pesticide) {
		AcAccount account = (AcAccount) this.b(request);
		Result result = this.dy.a(ctgBk010Pesticide, account);
		this.a(response, result);
	}
}