package com.nis.cdc.controller;

import com.nis.access.entity.AcAccount;
import com.nis.cdc.entity.CtgBk008Ccvd;
import com.nis.cdc.service.CtgBk008CcvdService;
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
public class HCVesselController extends BaseController {
	@Autowired
	private SysDictService p;
	@Autowired
	private Zg012Icd10Service dG;
	@Autowired
	private SysParamService j;
	@Autowired
	private CtgBk008CcvdService du;
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
	private static final Logger c = Logger.getLogger(HCVesselController.class);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat dz = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat dA = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	SimpleDateFormat dB = new SimpleDateFormat("yyMMddHHmmssSSS");

	@RequestMapping({"/cdc/f_view/hcvReport"})
	@SqlLog(p = "传染病上报--心脑血管监测报卡详情")
	public String f(HttpServletRequest request, ModelMap modelMap, String zyid, String mzid, String masterid,
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
			CtgBk008Ccvd nation = this.du.get(masterid);
			modelMap.put("ccvd", nation);
			if (nation != null && ab.isNotEmpty(nation.getRegisterareacode())) {
				modelMap.put("curRegSheng", nation.getRegisterareacode().substring(0, 2) + "000000");
				modelMap.put("curRegShi", nation.getRegisterareacode().substring(0, 4) + "0000");
				modelMap.put("curRegXian", nation.getRegisterareacode().substring(0, 6) + "00");
				modelMap.put("curRegXiang", nation.getRegisterareacode());
			}

			if (nation != null && ab.isNotEmpty(nation.getNowaddrareacode())) {
				modelMap.put("curSheng", nation.getNowaddrareacode().substring(0, 2) + "000000");
				modelMap.put("curShi", nation.getNowaddrareacode().substring(0, 4) + "0000");
				modelMap.put("curXian", nation.getNowaddrareacode().substring(0, 6) + "00");
				modelMap.put("curXiang", nation.getNowaddrareacode());
			}

			modelMap.put("MSTID", nation.getMasterid());
			modelMap.put("BKT", "xnxgbk");
			modelMap.put("flag", nation.getFlag());
			if (!ab.aM(zyid)) {
				modelMap.put("patientType", "zy");
			} else if (!ab.aM(mzid)) {
				modelMap.put("patientType", "mz");
			}

			long clinicPatients = nation.getIsemptycard().longValue();
			if (clinicPatients > 0L) {
				modelMap.put("isEmptyCard", "Y");
			}
		} else if (!ab.aM(zyid)) {
			St003Cryxxb nation1 = this.df.get(zyid);
			if (nation1 != null && ab.isNotEmpty(nation1.getPatientId())) {
				St001Jbxxb clinicPatients1 = this.dg.get(nation1.getPatientId());
				if (clinicPatients1 != null) {
					if (!ab.aM(clinicPatients1.getTel())) {
						nation1.setTel(clinicPatients1.getTel());
					}

					if (!ab.aM(clinicPatients1.getNation())) {
						nation1.setNation(clinicPatients1.getNation());
					}

					if (clinicPatients1.getBirthDate() != null && nation1.getBirthDate() == null) {
						nation1.setBirthDate(clinicPatients1.getBirthDate());
					}

					if (clinicPatients1.getIdCardId() != null) {
						nation1.setIdCard(clinicPatients1.getIdCardId());
					}

					if (clinicPatients1.getAddress() != null) {
						nation1.setAddress(clinicPatients1.getAddress());
					}
				}
			} else {
				modelMap.put("errMsg", "未找到病人信息！");
			}

			modelMap.put("BRXX", nation1);
			modelMap.put("patientType", "zy");
		} else if (!ab.aM(mzid)) {
			St020ClinicPatients nation2 = this.bh.get(mzid);
			if (nation2 == null) {
				modelMap.put("errMsg", "未找到病人信息！");
			} else if (nation2.getIsemptycard() == 1) {
				modelMap.put("isEmptyCard", "Y");
			}

			modelMap.put("BRXX", nation2);
			modelMap.put("patientType", "mz");
		} else if (!ab.aM(type)) {
			String nation3 = this.j.findByParamCode(Param.NIS_CDC_EMPTY_CARD_GENERATE_INFO);
			St020ClinicPatients clinicPatients2 = new St020ClinicPatients();
			if ("1".equals(nation3)) {
				Date d = new Date();
				clinicPatients2.setMzid("MZ" + this.dA.format(d));
				clinicPatients2.setPatientId("EC" + this.dB.format(d));
				clinicPatients2.setVisitId("1");
			}

			clinicPatients2.setIsemptycard(1);
			modelMap.put("BRXX", clinicPatients2);
			modelMap.put("isEmptyCard", "Y");
			modelMap.put("patientType", "mz");
		}

		List nation4 = this.p.u("cdc_nation", "0");
		modelMap.put("NATION", nation4);
		modelMap.put("RDFD", this.j.findByParamCode(Param.NIS_CDC_RD_VALUE_FROM_DOCTOR));
		if (!ab.aM(justLook)) {
			modelMap.put("justLook", "Y");
		}

		return "cards/xnxgbk";
	}

	@RequestMapping({"/cdc/f_json/saveXNXGCard"})
	@ResponseBody
	@SqlLog(p = "传染病上报--保存心脑血管监测报卡")
	public void a(HttpServletRequest request, HttpServletResponse response, CtgBk008Ccvd ctgBk008Ccvd) {
		AcAccount account = (AcAccount) this.b(request);
		Result result = this.du.a(ctgBk008Ccvd, account);
		this.a(response, result);
	}
}