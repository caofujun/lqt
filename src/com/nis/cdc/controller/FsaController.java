package com.nis.cdc.controller;

import com.nis.access.entity.AcAccount;
import com.nis.cdc.entity.CtgBk004Syycbk;
import com.nis.cdc.service.CtgBk004SyycbkService;
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
public class FsaController extends BaseController {
	@Autowired
	private SysDictService p;
	@Autowired
	private Zg012Icd10Service dG;
	@Autowired
	private SysParamService j;
	@Autowired
	private St003CryxxbService df;
	@Autowired
	private St001JbxxbService dg;
	@Autowired
	private St020ClinicPatientsService bh;
	@Autowired
	private NyMessageDetailService cV;
	@Autowired
	private CtgBk004SyycbkService dr;
	private static final Logger c = Logger.getLogger(FsaController.class);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat dz = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat dA = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	SimpleDateFormat dB = new SimpleDateFormat("yyMMddHHmmssSSS");

	@RequestMapping({"/cdc/f_view/fsaReport"})
	@SqlLog(p = "传染病上报--食源异常监测报卡详情")
	public String d(HttpServletRequest request, ModelMap modelMap, String zyid, String mzid, String masterid,
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

			CtgBk004Syycbk typeCode = this.dr.get(masterid);
			if (typeCode != null) {
				modelMap.put("syycxx", typeCode);
				modelMap.put("MSTID", typeCode.getMasterid());
				modelMap.put("BKT", "syycbk");
				modelMap.put("flag", typeCode.getFlag());
				String clinicPatients = typeCode.getRegisterareacode();
				if (!ab.aM(clinicPatients)) {
					modelMap.put("regsheng", clinicPatients.substring(0, 2) + "000000");
					modelMap.put("regshi", clinicPatients.substring(0, 4) + "0000");
					modelMap.put("regxian", clinicPatients.substring(0, 6) + "00");
					modelMap.put("regxiang", clinicPatients);
				}

				String d = typeCode.getAddrcode();
				if (!ab.aM(d)) {
					modelMap.put("nowsheng", d.substring(0, 2) + "000000");
					modelMap.put("nowshi", d.substring(0, 4) + "0000");
					modelMap.put("nowxian", d.substring(0, 6) + "00");
					modelMap.put("nowxiang", d);
				}
			}

			long clinicPatients1 = (long) typeCode.getIsemptycard().intValue();
			if (clinicPatients1 > 0L) {
				modelMap.put("isEmptyCard", "Y");
			}
		} else if (!ab.aM(zyid) && !"null".equals(zyid)) {
			St003Cryxxb typeCode3 = this.df.get(zyid);
			if (typeCode3 != null && ab.isNotEmpty(typeCode3.getPatientId())) {
				St001Jbxxb clinicPatients3 = this.dg.get(typeCode3.getPatientId());
				if (clinicPatients3 != null) {
					if (!ab.aM(clinicPatients3.getTel())) {
						typeCode3.setTel(clinicPatients3.getTel());
					}

					if (!ab.aM(clinicPatients3.getNation())) {
						typeCode3.setNation(clinicPatients3.getNation());
					}

					if (clinicPatients3.getBirthDate() != null && typeCode3.getBirthDate() == null) {
						typeCode3.setBirthDate(clinicPatients3.getBirthDate());
					}

					if (clinicPatients3.getIdCardId() != null) {
						typeCode3.setIdCard(clinicPatients3.getIdCardId());
					}

					if (clinicPatients3.getAddress() != null) {
						typeCode3.setAddress(clinicPatients3.getAddress());
					}
				}
			} else {
				modelMap.put("errMsg", "未找到病人信息！");
			}

			modelMap.put("BRXX", typeCode3);
			modelMap.put("patientType", "zy");
		} else if (!ab.aM(mzid) && !"null".equals(zyid)) {
			St020ClinicPatients typeCode2 = this.bh.get(mzid);
			if (typeCode2 == null) {
				modelMap.put("errMsg", "未找到病人信息！");
			}

			modelMap.put("BRXX", typeCode2);
			modelMap.put("patientType", "mz");
		} else if (!ab.aM(type)) {
			String typeCode1 = this.j.findByParamCode(Param.NIS_CDC_EMPTY_CARD_GENERATE_INFO);
			St020ClinicPatients clinicPatients2 = new St020ClinicPatients();
			if ("1".equals(typeCode1)) {
				Date d1 = new Date();
				clinicPatients2.setMzid("MZ" + this.dA.format(d1));
				clinicPatients2.setPatientId("EC" + this.dB.format(d1));
				clinicPatients2.setVisitId("1");
			}

			clinicPatients2.setIsemptycard(1);
			modelMap.put("BRXX", clinicPatients2);
			modelMap.put("isEmptyCard", "Y");
			modelMap.put("patientType", "mz");
		}

		List typeCode4 = this.p.u("cdc_idcard_type", "0");
		modelMap.put("cardType", typeCode4);
		modelMap.put("RDFD", this.j.findByParamCode(Param.NIS_CDC_RD_VALUE_FROM_DOCTOR));
		return "cards/syycbk";
	}

	@RequestMapping({"/cdc/f_json/saveSyycCard"})
	@ResponseBody
	@SqlLog(p = "传染病上报--保存食源异常监测报卡")
	public void a(HttpServletRequest request, HttpServletResponse response, CtgBk004Syycbk ctgBk004Syycbk) {
		AcAccount acount = (AcAccount) this.b(request);
		Result r = this.dr.a(ctgBk004Syycbk, acount);
		this.a(response, r);
	}
}