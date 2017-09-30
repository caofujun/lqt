package com.nis.cdc.controller;

import com.nis.access.entity.AcAccount;
import com.nis.cdc.entity.CtgBk006Tumour;
import com.nis.cdc.service.CtgBk006TumourService;
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
public class TumourController extends BaseController {
	@Autowired
	private SysDictService p;
	@Autowired
	private Zg012Icd10Service dG;
	@Autowired
	private SysParamService j;
	@Autowired
	private CtgBk006TumourService dq;
	@Autowired
	private St003CryxxbService df;
	@Autowired
	private St001JbxxbService dg;
	@Autowired
	private St020ClinicPatientsService bh;
	@Autowired
	private NyMessageDetailService cV;
	private static final Logger c = Logger.getLogger(TumourController.class);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat dz = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat dA = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	SimpleDateFormat dB = new SimpleDateFormat("yyMMddHHmmssSSS");

	@RequestMapping({"/cdc/f_view/tumourReport"})
	@SqlLog(p = "传染病上报--食肿瘤测报卡详情")
	public String i(HttpServletRequest request, ModelMap modelMap, String zyid, String mzid, String masterid,
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
			if (!ab.aM(zyid)) {
				modelMap.put("patientType", "zy");
			} else if (!ab.aM(mzid)) {
				modelMap.put("patientType", "mz");
			}

			CtgBk006Tumour education = this.dq.get(masterid);
			if (education != null) {
				modelMap.put("zlxx", education);
				modelMap.put("MSTID", education.getMasterid());
				modelMap.put("BKT", "zlbk");
				modelMap.put("flag", education.getFlag());
				String marital = education.getRegisterareacode();
				if (!ab.aM(marital)) {
					modelMap.put("regSheng", marital.substring(0, 2) + "000000");
					modelMap.put("regShi", marital.substring(0, 4) + "0000");
					modelMap.put("regXian", marital.substring(0, 6) + "00");
					modelMap.put("regXiang", marital);
				}

				String nation = education.getNowaddrareacode();
				if (!ab.aM(nation)) {
					modelMap.put("nowSheng", nation.substring(0, 2) + "000000");
					modelMap.put("nowShi", nation.substring(0, 4) + "0000");
					modelMap.put("nowXian", nation.substring(0, 6) + "00");
					modelMap.put("nowXiang", nation);
				}

				long profession = (long) education.getIsemptycard().intValue();
				if (profession > 0L) {
					modelMap.put("isEmptyCard", "Y");
				}
			}
		} else if (!ab.aM(zyid) && !"null".equals(zyid)) {
			St003Cryxxb education3 = this.df.get(zyid);
			if (education3 != null && ab.isNotEmpty(education3.getPatientId())) {
				St001Jbxxb marital2 = this.dg.get(education3.getPatientId());
				if (marital2 != null) {
					if (!ab.aM(marital2.getTel())) {
						education3.setTel(marital2.getTel());
					}

					if (!ab.aM(marital2.getNation())) {
						education3.setNation(marital2.getNation());
					}

					if (marital2.getBirthDate() != null && education3.getBirthDate() == null) {
						education3.setBirthDate(marital2.getBirthDate());
					}

					if (marital2.getIdCardId() != null) {
						education3.setIdCard(marital2.getIdCardId());
					}

					if (marital2.getAddress() != null) {
						education3.setAddress(marital2.getAddress());
					}
				}
			} else {
				modelMap.put("errMsg", "未找到病人信息！");
			}

			modelMap.put("BRXX", education3);
			modelMap.put("patientType", "zy");
		} else if (!ab.aM(mzid) && !"null".equals(mzid)) {
			St020ClinicPatients education2 = this.bh.get(mzid);
			if (education2 == null) {
				modelMap.put("errMsg", "未找到病人信息！");
			}

			modelMap.put("BRXX", education2);
			modelMap.put("patientType", "mz");
		} else if (!ab.aM(type)) {
			String education1 = this.j.findByParamCode(Param.NIS_CDC_EMPTY_CARD_GENERATE_INFO);
			St020ClinicPatients marital1 = new St020ClinicPatients();
			if ("1".equals(education1)) {
				Date nation1 = new Date();
				marital1.setMzid("MZ" + this.dA.format(nation1));
				marital1.setPatientId("EC" + this.dB.format(nation1));
				marital1.setVisitId("1");
			}

			marital1.setIsemptycard(1);
			modelMap.put("BRXX", marital1);
			modelMap.put("isEmptyCard", "Y");
			modelMap.put("patientType", "mz");
		}

		List education4 = this.p.u("tumour_education", "0");
		modelMap.put("education", education4);
		List marital3 = this.p.u("tumour_marital_status", "0");
		modelMap.put("marital", marital3);
		List nation2 = this.p.u("cdc_nation", "0");
		modelMap.put("nation", nation2);
		List profession1 = this.p.u("tumour_profession", "0");
		modelMap.put("profession", profession1);
		modelMap.put("RDFD", this.j.findByParamCode(Param.NIS_CDC_RD_VALUE_FROM_DOCTOR));
		return "cards/tumourbk";
	}

	@RequestMapping({"/cdc/f_json/saveTumourCard"})
	@ResponseBody
	@SqlLog(p = "传染病上报--保存食肿瘤测报卡")
	public void a(HttpServletRequest request, HttpServletResponse response, CtgBk006Tumour ctgBk006Tumour) {
		AcAccount acount = (AcAccount) this.b(request);
		Result r = this.dq.a(ctgBk006Tumour, acount);
		this.a(response, r);
	}
}