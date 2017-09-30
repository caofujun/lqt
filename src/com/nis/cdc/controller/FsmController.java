package com.nis.cdc.controller;

import com.nis.access.entity.AcAccount;
import com.nis.cdc.entity.CtgBk005Blxx;
import com.nis.cdc.entity.CtgBk005Syjc;
import com.nis.cdc.service.CtgBk005BlxxService;
import com.nis.cdc.service.CtgBk005CyxxService;
import com.nis.cdc.service.CtgBk005SyjcService;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FsmController extends BaseController {
	@Autowired
	private SysDictService p;
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
	private CtgBk005CyxxService dH;
	@Autowired
	private CtgBk005SyjcService dp;
	@Autowired
	private CtgBk005BlxxService dI;
	private static final Logger c = Logger.getLogger(FsmController.class);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat dz = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat dA = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	SimpleDateFormat dB = new SimpleDateFormat("yyMMddHHmmssSSS");

	@SqlLog(p = "传染病上报--食源监测报卡详情")
	@RequestMapping({"/cdc/f_view/fsmReport"})
	public String e(HttpServletRequest request, ModelMap modelMap, String zyid, String mzid, String masterid,
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

		String min_age = this.j.findByParamCode(Param.NIS_CDC_MIN_AGE);
		if (!"-1".equals(min_age)) {
			modelMap.put("NCMA", min_age);
		}

		List groupClassify;
		List BBLX;
		if (!ab.aM(masterid)) {
			CtgBk005Syjc patientBelong = this.dp.get(masterid);
			modelMap.put("syjcxx", patientBelong);
			modelMap.put("MSTID", patientBelong.getMasterid());
			modelMap.put("BKT", "syjcbk");
			modelMap.put("flag", patientBelong.getFlag());
			groupClassify = this.dI.getByMastertid(masterid);
			modelMap.put("AllBLXX", groupClassify);
			BBLX = this.dH.getByMastertid(masterid);
			modelMap.put("AllCYXX", BBLX);
			if (!ab.aM(zyid)) {
				modelMap.put("patientType", "zy");
			} else if (!ab.aM(mzid)) {
				modelMap.put("patientType", "mz");
			}

			long FXXZ = (long) patientBelong.getIsemptycard().intValue();
			if (FXXZ > 0L) {
				modelMap.put("isEmptyCard", "Y");
			}
		} else if (!ab.aM(zyid) && !"null".equals(zyid)) {
			St003Cryxxb patientBelong3 = this.df.get(zyid);
			if (patientBelong3 != null && ab.isNotEmpty(patientBelong3.getPatientId())) {
				St001Jbxxb groupClassify2 = this.dg.get(patientBelong3.getPatientId());
				if (groupClassify2 != null) {
					if (!ab.aM(groupClassify2.getTel())) {
						patientBelong3.setTel(groupClassify2.getTel());
					}

					if (!ab.aM(groupClassify2.getNation())) {
						patientBelong3.setNation(groupClassify2.getNation());
					}

					if (groupClassify2.getBirthDate() != null && patientBelong3.getBirthDate() == null) {
						patientBelong3.setBirthDate(groupClassify2.getBirthDate());
					}

					if (groupClassify2.getIdCardId() != null) {
						patientBelong3.setIdCard(groupClassify2.getIdCardId());
					}

					if (groupClassify2.getAddress() != null) {
						patientBelong3.setAddress(groupClassify2.getAddress());
					}
				}
			} else {
				modelMap.put("errMsg", "未找到病人信息！");
			}

			modelMap.put("BRXX", patientBelong3);
			modelMap.put("patientType", "zy");
		} else if (!ab.aM(mzid) && !"null".equals(zyid)) {
			St020ClinicPatients patientBelong2 = this.bh.get(mzid);
			if (patientBelong2 == null) {
				modelMap.put("errMsg", "未找到病人信息！");
			}

			modelMap.put("BRXX", patientBelong2);
			modelMap.put("patientType", "mz");
		} else if (!ab.aM(type)) {
			String patientBelong1 = this.j.findByParamCode(Param.NIS_CDC_EMPTY_CARD_GENERATE_INFO);
			St020ClinicPatients groupClassify1 = new St020ClinicPatients();
			if ("1".equals(patientBelong1)) {
				Date BBLX1 = new Date();
				groupClassify1.setMzid("MZ" + this.dA.format(BBLX1));
				groupClassify1.setPatientId("EC" + this.dB.format(BBLX1));
				groupClassify1.setVisitId("1");
			}

			groupClassify1.setIsemptycard(1);
			modelMap.put("BRXX", groupClassify1);
			modelMap.put("isEmptyCard", "Y");
			modelMap.put("patientType", "mz");
		}

		List patientBelong4 = this.p.u("cdc_patient_belong", "0");
		modelMap.put("patientBelong", patientBelong4);
		groupClassify = this.p.u("cdc_group_classify", "0");
		modelMap.put("groupClassify", groupClassify);
		BBLX = this.p.u("fsm_bblx", "0");
		modelMap.put("BBLX", BBLX);
		List FXXZ1 = this.p.u("fsm_fxxz", "0");
		modelMap.put("FXXZ", FXXZ1);
		List TKYC = this.p.u("fsm_tkyc", "0");
		modelMap.put("TKYC", TKYC);
		modelMap.put("RDFD", this.j.findByParamCode(Param.NIS_CDC_RD_VALUE_FROM_DOCTOR));
		return "cards/syjcbk";
	}

	@RequestMapping({"/cdc/f_view/getBlxxTable"})
	public String a(HttpServletRequest request, ModelMap modelMap, CtgBk005Blxx ctgBk005Blxx) {
		String gbcode = this.j.findByParamCode(Param.NIS_HOSPITAL_GBCODE);
		if (!ab.aM(gbcode)) {
			modelMap.put("HASheng", gbcode.substring(0, 2) + "000000");
			modelMap.put("HAShi", gbcode.substring(0, 4) + "0000");
			modelMap.put("HAXian", gbcode.substring(0, 6) + "00");
			modelMap.put("HAXiang", gbcode);
		}

		if (!ab.aM(ctgBk005Blxx.getFoodname())) {
			modelMap.put("BLXX", ctgBk005Blxx);
			String FOODCLASSIFY = ctgBk005Blxx.getEatplacecode();
			if (!ab.aM(FOODCLASSIFY)) {
				modelMap.put("eatSheng", FOODCLASSIFY.substring(0, 2) + "000000");
				modelMap.put("eatShi", FOODCLASSIFY.substring(0, 4) + "0000");
				modelMap.put("eatXian", FOODCLASSIFY.substring(0, 6) + "00");
			}

			String PACKAGEWAY = ctgBk005Blxx.getPurcplacecode();
			if (!ab.aM(PACKAGEWAY)) {
				modelMap.put("buySheng", PACKAGEWAY.substring(0, 2) + "000000");
				modelMap.put("buyShi", PACKAGEWAY.substring(0, 4) + "0000");
				modelMap.put("buyXian", PACKAGEWAY.substring(0, 6) + "00");
			}
		}

		List FOODCLASSIFY1 = this.p.u("fsm_spfl", "0");
		modelMap.put("FOODCLASSIFY", FOODCLASSIFY1);
		List PACKAGEWAY1 = this.p.u("fsm_bzfs", "0");
		modelMap.put("PACKAGEWAY", PACKAGEWAY1);
		List EATPLACE = this.p.u("fsm_jscs", "0");
		modelMap.put("EATPLACE", EATPLACE);
		return "cards/includepages/syjc/blxxDialog";
	}

	@RequestMapping({"/cdc/f_json/saveSYJCCard"})
	@ResponseBody
	@SqlLog(p = "传染病上报--保存食源监测报卡")
	public void a(HttpServletRequest request, HttpServletResponse response, CtgBk005Syjc ctgBk005Syjc) {
		AcAccount account = (AcAccount) this.b(request);
		Result result = this.dp.a(ctgBk005Syjc, account);
		this.a(response, result);
	}

	@RequestMapping({"/cdc/export/file/toExcel"})
	@ResponseBody
	public void p(HttpServletRequest request, HttpServletResponse response, String masterid) {
		if (ab.isNotEmpty(masterid)) {
			String name = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());

			try {
				response.setHeader("Content-disposition",
						"attachment; filename=" + ab.a("食源性疾病病例监测导入模版_", request) + name + ".xls");
				response.setContentType("application/msexcel;charset=UTF-8");
				response.setHeader("Content-Transfer-Encoding", "binary");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				HSSFWorkbook e = this.dp.a(request, masterid);
				ServletOutputStream os = response.getOutputStream();
				e.write(os);
				os.flush();
				os.close();
			} catch (Exception arg6) {
				c.error("获取信息异常!", arg6);
			}
		}

	}
}