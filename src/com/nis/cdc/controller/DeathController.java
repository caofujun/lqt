package com.nis.cdc.controller;

import com.nis.access.entity.AcAccount;
import com.nis.cdc.entity.CtgBk002Sybk;
import com.nis.cdc.entity.CtgSys012Pathology;
import com.nis.cdc.service.CtgBk002SybkService;
import com.nis.cdc.service.CtgSys012PathologyService;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
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
import com.nis.zg.entity.Zg012Icd10;
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
public class DeathController extends BaseController {
	@Autowired
	private SysDictService p;
	@Autowired
	private Zg012Icd10Service dG;
	@Autowired
	private SysParamService j;
	@Autowired
   private CtgBk002SybkService doo;
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
	private static final Logger c = Logger.getLogger(DeathController.class);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat dz = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat dA = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	SimpleDateFormat dB = new SimpleDateFormat("yyMMddHHmmssSSS");

	@RequestMapping({"/cdc/f_view/deathReport"})
   @SqlLog(
      p = "传染病上报--居民死因监测报卡详情"
   )
   public String c(HttpServletRequest request, ModelMap modelMap, String zyid, String mzid, String masterid, String justLook, String type) {
      AcAccount account = (AcAccount)this.b(request);
      if(!"hospital".equals(account.getAcType())) {
         modelMap.put("acType", "doctor");
      } else {
         modelMap.put("acType", "hospital");
      }

      String gbcode = this.j.findByParamCode(Param.NIS_HOSPITAL_GBCODE);
      if(!ab.aM(gbcode)) {
         modelMap.put("HASheng", gbcode.substring(0, 2) + "000000");
         modelMap.put("HAShi", gbcode.substring(0, 4) + "0000");
         modelMap.put("HAXian", gbcode.substring(0, 6) + "00");
         modelMap.put("HAXiang", gbcode);
      }

      String canfillBD3;
      if(!ab.aM(masterid)) {
         CtgBk002Sybk canfillBD = this.doo.get(masterid);
         modelMap.put("syxx", canfillBD);
         if(canfillBD != null && ab.isNotEmpty(canfillBD.getRegistrationaddrcode())) {
            modelMap.put("curRegSheng", canfillBD.getRegistrationaddrcode().substring(0, 2) + "000000");
            modelMap.put("curRegShi", canfillBD.getRegistrationaddrcode().substring(0, 4) + "0000");
            modelMap.put("curRegXian", canfillBD.getRegistrationaddrcode().substring(0, 6) + "00");
            modelMap.put("curRegXiang", canfillBD.getRegistrationaddrcode());
         }

         if(canfillBD != null && ab.isNotEmpty(canfillBD.getNowaddrcode())) {
            modelMap.put("curSheng", canfillBD.getNowaddrcode().substring(0, 2) + "000000");
            modelMap.put("curShi", canfillBD.getNowaddrcode().substring(0, 4) + "0000");
            modelMap.put("curXian", canfillBD.getNowaddrcode().substring(0, 6) + "00");
            modelMap.put("curXiang", canfillBD.getNowaddrcode());
         }

         modelMap.put("MSTID", canfillBD.getMasterid());
         modelMap.put("BKT", "sybk");
         modelMap.put("flag", canfillBD.getFlag());
         if(!ab.aM(zyid)) {
            modelMap.put("patientType", "zy");
         } else if(!ab.aM(mzid)) {
            modelMap.put("patientType", "mz");
         }

         long isFIN = canfillBD.getIsemptycard().longValue();
         if(isFIN > 0L) {
            modelMap.put("isEmptyCard", "Y");
         }
      } else if(!ab.aM(zyid)) {
         St003Cryxxb canfillBD1 = this.df.get(zyid);
         if(canfillBD1 != null && ab.isNotEmpty(canfillBD1.getPatientId())) {
            St001Jbxxb isFIN1 = this.dg.get(canfillBD1.getPatientId());
            if(isFIN1 != null) {
               if(!ab.aM(isFIN1.getTel())) {
                  canfillBD1.setTel(isFIN1.getTel());
               }

               if(!ab.aM(isFIN1.getNation())) {
                  canfillBD1.setNation(isFIN1.getNation());
               }

               if(isFIN1.getBirthDate() != null && canfillBD1.getBirthDate() == null) {
                  canfillBD1.setBirthDate(isFIN1.getBirthDate());
               }

               if(isFIN1.getIdCardId() != null) {
                  canfillBD1.setIdCard(isFIN1.getIdCardId());
               }

               if(isFIN1.getAddress() != null) {
                  canfillBD1.setAddress(isFIN1.getAddress());
               }
            }
         } else {
            modelMap.put("errMsg", "未找到病人信息！");
         }

         modelMap.put("BRXX", canfillBD1);
         modelMap.put("patientType", "zy");
      } else if(!ab.aM(mzid)) {
         St020ClinicPatients canfillBD2 = this.bh.get(mzid);
         if(canfillBD2 == null) {
            modelMap.put("errMsg", "未找到病人信息！");
         }

         modelMap.put("BRXX", canfillBD2);
         modelMap.put("patientType", "mz");
      } else if(!ab.aM(type)) {
         canfillBD3 = this.j.findByParamCode(Param.NIS_CDC_EMPTY_CARD_GENERATE_INFO);
         St020ClinicPatients isFIN2 = new St020ClinicPatients();
         if("1".equals(canfillBD3)) {
            Date education = new Date();
            isFIN2.setMzid("MZ" + this.dA.format(education));
            isFIN2.setPatientId("EC" + this.dB.format(education));
            isFIN2.setVisitId("1");
         }

         isFIN2.setIsemptycard(1);
         modelMap.put("BRXX", isFIN2);
         modelMap.put("isEmptyCard", "Y");
         modelMap.put("patientType", "mz");
      }

      canfillBD3 = this.j.findByParamCode(Param.NIS_CDC_CAN_FILL_BASE_DEATH_CAUSE);
      modelMap.put("canfillBD", canfillBD3);
      String isFIN3 = this.j.findByParamCode(Param.NIS_CDC_DEATH_CARD_IS_FAMILY_INFO_NECESSARY);
      modelMap.put("isFIN", isFIN3);
      List education1 = this.p.u("death_whcd", "0");
      modelMap.put("EDUCATION", education1);
      List nation = this.p.u("cdc_nation", "0");
      modelMap.put("NATION", nation);
      List grsf = this.p.u("death_grsf", "0");
      modelMap.put("GRSF", grsf);
      List nxqk = this.p.u("death_nxqk", "0");
      modelMap.put("NXQK", nxqk);
      List hyzk = this.p.u("death_hyzk", "0");
      modelMap.put("HYZK", hyzk);
      List zjlx = this.p.u("death_zjlx", "0");
      modelMap.put("ZJLX", zjlx);
      List swdd = this.p.u("death_swdd", "0");
      modelMap.put("SWDD", swdd);
      List patientBelong = this.p.u("cdc_patient_belong", "0");
      modelMap.put("patientBelong", patientBelong);
      List zgzddw = this.p.u("death_zgzddw", "0");
      modelMap.put("ZGZDDW", zgzddw);
      List zgzdyj = this.p.u("death_zgzdyj", "0");
      modelMap.put("ZGZDYJ", zgzdyj);
      String isGDSN = this.j.findByParamCode(Param.NIS_CDC_GENERATE_DC_SERIAL_NUMBER);
      modelMap.put("isGDSN", isGDSN);
      modelMap.put("RDFD", this.j.findByParamCode(Param.NIS_CDC_RD_VALUE_FROM_DOCTOR));
      if(!ab.aM(justLook)) {
         modelMap.put("justLook", "Y");
      }

      return "cards/sybk";
   }

	@RequestMapping({"/cdc/f_json/chooseICD"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, Zg012Icd10 zg012Icd10) {
		MyPage page = this.dG.a(zg012Icd10);
		this.a(response, page);
	}

	@RequestMapping({"/cdc/f_json/chooseICD0"})
	@ResponseBody
	@SqlLog(p = "ICD0-3字典--ICD字典列表")
	public void b(HttpServletRequest request, HttpServletResponse response, CtgSys012Pathology ctgSys012Pathology) {
		if (ctgSys012Pathology.getSearchString() != null && !"".equals(ctgSys012Pathology.getSearchString())) {
			ctgSys012Pathology.setSearchString(ab.aR(ctgSys012Pathology.getSearchString()));
		}

		MyPage pageQuery = this.dC.a(ctgSys012Pathology);
		this.a(response, pageQuery);
	}

	@RequestMapping({"/cdc/f_json/saveSYCard"})
   @ResponseBody
   @SqlLog(
      p = "传染病上报--保存居民死因监测报卡"
   )
   public void a(HttpServletRequest request, HttpServletResponse response, CtgBk002Sybk ctgBk002Sybk) {
      AcAccount account = (AcAccount)this.b(request);
      Result result = this.doo.a(ctgBk002Sybk, account);
      this.a(response, result);
   }
}