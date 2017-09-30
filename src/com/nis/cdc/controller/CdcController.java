package com.nis.cdc.controller;

import com.nis.access.entity.AcAccount;
import com.nis.cdc.entity.CtgBk001Crbdisease;
import com.nis.cdc.entity.CtgBk001Crbhbvcard;
import com.nis.cdc.entity.CtgBk001Crbmaster;
import com.nis.cdc.entity.CtgBk001Crbstdcard;
import com.nis.cdc.entity.CtgBk001Tb;
import com.nis.cdc.entity.CtgBk001Zzd;
import com.nis.cdc.entity.CtgBk002Sybk;
import com.nis.cdc.entity.CtgBk004Syycbk;
import com.nis.cdc.entity.CtgBk005Syjc;
import com.nis.cdc.entity.CtgBk006Tumour;
import com.nis.cdc.entity.CtgBk008Ccvd;
import com.nis.cdc.entity.CtgBk009Sunstroke;
import com.nis.cdc.entity.CtgBk010Pesticide;
import com.nis.cdc.entity.CtgSys008Markedcase;
import com.nis.cdc.service.CtgBk001CrbdiseaseService;
import com.nis.cdc.service.CtgBk001CrbhbvcardService;
import com.nis.cdc.service.CtgBk001CrbmasterService;
import com.nis.cdc.service.CtgBk001CrbstdcardService;
import com.nis.cdc.service.CtgBk001TbService;
import com.nis.cdc.service.CtgBk001ZzdService;
import com.nis.cdc.service.CtgBk002SybkService;
import com.nis.cdc.service.CtgBk004SyycbkService;
import com.nis.cdc.service.CtgBk005SyjcService;
import com.nis.cdc.service.CtgBk006TumourService;
import com.nis.cdc.service.CtgBk008CcvdService;
import com.nis.cdc.service.CtgBk009SunstrokeService;
import com.nis.cdc.service.CtgBk010PesticideService;
import com.nis.cdc.service.CtgSys004DictaddrareaService;
import com.nis.cdc.service.CtgSys005LisruleMasterService;
import com.nis.cdc.service.CtgSys007DictcontagionService;
import com.nis.cdc.service.CtgSys008MarkedcaseService;
import com.nis.cdc.service.CtgSys013SerialService;
import com.nis.comm.annotation.SqlLog;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.al;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.r;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import com.nis.msg.service.NyMessageDetailService;
import com.nis.param.service.SysParamService;
import com.nis.patient.entity.St001Jbxxb;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.entity.St020ClinicPatients;
import com.nis.patient.service.St001JbxxbService;
import com.nis.patient.service.St003CryxxbService;
import com.nis.patient.service.St020ClinicPatientsService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CdcController extends BaseController {
	@Autowired
	private St003CryxxbService df;
	@Autowired
	private St001JbxxbService dg;
	@Autowired
	private St020ClinicPatientsService bh;
	@Autowired
	private CtgSys004DictaddrareaService dh;
	@Autowired
	private CtgSys005LisruleMasterService di;
	@Autowired
	private CtgBk001CrbmasterService dj;
	@Autowired
	private CtgBk001CrbdiseaseService dk;
	@Autowired
	private CtgBk001CrbstdcardService dl;
	@Autowired
	private CtgBk001CrbhbvcardService dm;
	@Autowired
	private CtgSys007DictcontagionService bL;
	@Autowired
	private CtgSys008MarkedcaseService dn;
	@Autowired
	private SysDictService p;
	@Autowired
	private SysParamService j;
	@Autowired
	private NyMessageDetailService cV;
	@Autowired
   private CtgBk002SybkService doo;
   @Autowired
   private CtgBk005SyjcService dp;
	@Autowired
	private CtgBk006TumourService dq;
	@Autowired
	private CtgBk004SyycbkService dr;
	@Autowired
	private CtgBk001TbService ds;
	@Autowired
	private CtgBk008CcvdService du;
	@Autowired
	private CtgBk009SunstrokeService dv;
	@Autowired
	private CtgSys013SerialService dw;
	@Autowired
	private CtgBk001ZzdService dx;
	@Autowired
	private CtgBk010PesticideService dy;
	private static final Logger c = Logger.getLogger(CdcController.class);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat dz = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat dA = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	SimpleDateFormat dB = new SimpleDateFormat("yyMMddHHmmssSSS");

	@RequestMapping({"/cdc/f_view/index"})
	public String a(HttpServletRequest request, ModelMap modelMap) {
		List unAuditCards1 = this.dj.unAuditCards();
		ArrayList unAuditCards = new ArrayList();
		List cdcCardList = this.p.u("cdc_card_type", (String) null);
		Iterator arg6 = unAuditCards1.iterator();

		while (true) {
			while (arg6.hasNext()) {
				Map dayYJBL = (Map) arg6.next();
				Iterator arg8 = cdcCardList.iterator();

				while (arg8.hasNext()) {
					SysDict sysDict = (SysDict) arg8.next();
					if ((sysDict.getDictName() + "_" + sysDict.getDictCode()).equals(dayYJBL.get("name"))) {
						unAuditCards.add(dayYJBL);
						break;
					}
				}
			}

			modelMap.put("unAuditCards", unAuditCards);
			List dayYJBL1 = this.dj.curDayYJBL();
			modelMap.put("dayYJBL", dayYJBL1);
			return "cdc_index";
		}
	}

	@RequestMapping({"/cdc/f_view/main"})
	public String f(HttpServletRequest request, ModelMap modelMap) {
		modelMap.put("queryStartDate", this.sdf.format(Long.valueOf((new Date()).getTime() - 604800000L)));
		modelMap.put("queryEndDate", this.sdf.format(Long.valueOf((new Date()).getTime())));
		return "cdc_main";
	}

	@RequestMapping({"/cdc/f_view/pages"})
	public String f(HttpServletRequest request, ModelMap modelMap, String ct, String searchString) {
		AcAccount user = (AcAccount) this.b(request);
		if (user == null) {
			modelMap.put("errorMsg", "无法获取到当前用户信息，请重新登录");
			return "cdc/error";
		} else if ("mz".equals(ct)) {
			St020ClinicPatients st003Cryxxb1 = new St020ClinicPatients();
			st003Cryxxb1.setDeptId(user.getDepNo());
			st003Cryxxb1.setDiagnosisDt(new Date());
			if (!ab.aM(searchString)) {
				st003Cryxxb1.setSearchString(searchString);
			}

			MyPage list1 = this.bh.d(st003Cryxxb1);
			modelMap.put("MZBRS", list1.getRows());
			return "cdc/mzpl";
		} else if ("zy".equals(ct)) {
			St003Cryxxb st003Cryxxb = new St003Cryxxb();
			st003Cryxxb.setDeptCode(user.getDepNo());
			st003Cryxxb.setIsInHosp(Integer.valueOf(1));
			st003Cryxxb.setPage(Integer.valueOf(1));
			st003Cryxxb.setSize(Integer.valueOf(2000));
			List list = this.df.m(st003Cryxxb);
			modelMap.put("ZYBRS", list);
			return "cdc/zypl";
		} else if ("db".equals(ct)) {
			return "cdc/dbpl";
		} else {
			modelMap.put("errorMsg", "必要参数获取失败！操作失败！");
			return "cdc/error";
		}
	}

	@RequestMapping({"/cdc/f_view/mzpage"})
	public String g(HttpServletRequest request, ModelMap modelMap) {
		AcAccount user = (AcAccount) this.b(request);
		if (user == null) {
			modelMap.put("errorMsg", "无法获取到当前用户信息，请重新登录");
			return "cdc/error";
		} else {
			St020ClinicPatients st020ClinicPatients = new St020ClinicPatients();
			st020ClinicPatients.setDeptId(user.getDepNo());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			try {
				Date urm = sdf.parse(request.getParameter("queryStartDate"));
				st020ClinicPatients.setQueryStartDate(urm);
				Date list = sdf.parse(request.getParameter("queryEndDate"));
				st020ClinicPatients.setQueryEndDate(list);
			} catch (ParseException arg7) {
				arg7.printStackTrace();
			}

			String urm1 = request.getParameter("NRM");
			if (!ab.aM(urm1)) {
				st020ClinicPatients.setUnReadMsg(urm1);
			}

			MyPage list1 = this.bh.d(st020ClinicPatients);
			modelMap.put("MZBRS", list1.getRows());
			return "cdc/mzpl";
		}
	}

	@RequestMapping({"/cdc/f_json/jydata"})
	@SqlLog(p = "传染病--检验指标列表")
	public void m(HttpServletRequest request, HttpServletResponse response) {
		List list = this.di.getAll();
		this.a(response, list);
	}

	@RequestMapping({"/cdc/f_json/excludeRows"})
	@ResponseBody
	@SqlLog(p = "传染病--疑似病例批量处理")
	public void e(HttpServletRequest request, HttpServletResponse response, String dataType, String ids,
			String operId) {
		AcAccount user = (AcAccount) this.b(request);
		if (!ab.aM(dataType) && !ab.aM(ids) && !ab.aM(operId)) {
			String msg = "";
			String[] idArray = ids.split(",");
			ArrayList markedList = new ArrayList();
			String[] arg12 = idArray;
			int arg11 = idArray.length;

			for (int arg10 = 0; arg10 < arg11; ++arg10) {
				String e = arg12[arg10];
				CtgSys008Markedcase mc = new CtgSys008Markedcase();
				mc.setId(e);
				mc.setDataTypeid(dataType);
				mc.setUserId(user.getUserId());
				mc.setUserName(user.getUsername());
				mc.setRemarkTime(new Date());
				mc.setRemark(new Long(operId));
				markedList.add(mc);
			}

			try {
				this.dn.u(markedList);
				if ("1".equals(operId)) {
					msg = "确认成功！";
				} else {
					msg = "排除成功！";
				}

				this.a(response, "{\'state\':\'success\',\'msg\':\'" + msg + "\'}");
			} catch (Exception arg14) {
				arg14.printStackTrace();
				if ("1".equals(operId)) {
					msg = "确认失败！";
				} else {
					msg = "排除失败！";
				}

				c.error(msg, arg14);
				this.a(response, "{\'state\':\'error\',\'msg\':\'排除失败！\'}");
			}

		} else {
			this.a(response, "{\'state\':\'error\',\'msg\':\'必要参数获取为空，操作失败！\'}");
		}
	}

	@RequestMapping({"/cdc/f_view/reportCardMZ"})
	@SqlLog(p = "传染病上报--新增传染病报卡")
	public String a(HttpServletRequest request, ModelMap modelMap, String mzid, String masterid, String type,
			String justLook, String icds) {
		String needFKDiseasis = this.bL.getNeedFKDiseasis();
		modelMap.put("sexcards", needFKDiseasis);
		AcAccount account = (AcAccount) this.b(request);
		if (!"hospital".equals(account.getAcType())) {
			modelMap.put("acType", "doctor");
		} else {
			modelMap.put("acType", "hospital");
		}

		if (ab.aM(mzid) && !"empty".equals(type)) {
			modelMap.put("errMsg", "关键字获取为空");
		}

		List arg31;
		if (!ab.aM(masterid)) {
			CtgBk001Crbmaster typeCode = this.dj.get(masterid);
			if (typeCode != null) {
				modelMap.put("mz", typeCode);
				modelMap.put("MSTID", typeCode.getMasterid());
				modelMap.put("BKT", "crbbk");
				String patientBelong = typeCode.getAddrcode();
				if (!ab.aM(patientBelong)) {
					modelMap.put("curSheng", patientBelong.substring(0, 2) + "000000");
					modelMap.put("curShi", patientBelong.substring(0, 4) + "0000");
					modelMap.put("curXian", patientBelong.substring(0, 6) + "00");
					modelMap.put("curXiang", patientBelong);
				}
			}

			arg31 = this.dk.get(masterid);
			modelMap.put("AllDisease", arg31);
			Iterator blfl2 = arg31.iterator();

			while (blfl2.hasNext()) {
				CtgBk001Crbdisease blfl = (CtgBk001Crbdisease) blfl2.next();
				if (blfl != null) {
					modelMap.put("flag", blfl.getFlag());
					break;
				}
			}

			boolean arg33 = this.dl.t(masterid);
			if (arg33) {
				modelMap.put("HIVBK", "exist");
			}

			Iterator groupClassify = arg31.iterator();

			while (groupClassify.hasNext()) {
				CtgBk001Crbdisease arg36 = (CtgBk001Crbdisease) groupClassify.next();
				if (arg36 != null && "0302".equals(arg36.getDiseaseid())) {
					modelMap.put("YGBK", "exist");
				}
			}

			boolean arg37 = this.dx.v(masterid);
			if (arg37) {
				modelMap.put("TBBK", "exist");
			}

			int arg39 = typeCode.getIsemptycard();
			if (arg39 > 0) {
				modelMap.put("isEmptyCard", "Y");
			}
		} else if (ab.aM(type)) {
			St020ClinicPatients arg28 = this.bh.get(mzid);
			modelMap.put("BRXX", arg28);
		} else {
			String arg29 = this.j.findByParamCode(Param.NIS_CDC_EMPTY_CARD_GENERATE_INFO);
			St020ClinicPatients arg32 = new St020ClinicPatients();
			if ("1".equals(arg29)) {
				Date arg34 = new Date();
				arg32.setMzid("MZ" + this.dA.format(arg34));
				arg32.setPatientId("EC" + this.dB.format(arg34));
				arg32.setVisitId("1");
			}

			arg32.setIsemptycard(1);
			modelMap.put("BRXX", arg32);
			modelMap.put("isEmptyCard", "Y");
		}

		if (!ab.aM(justLook)) {
			modelMap.put("justLook", "Y");
		}

		List arg30 = this.p.u("cdc_idcard_type", "0");
		modelMap.put("cardType", arg30);
		arg31 = this.p.u("cdc_patient_belong", "0");
		modelMap.put("patientBelong", arg31);
		List arg35 = this.p.u("cdc_blfl", "0");
		modelMap.put("blfl", arg35);
		List arg38 = this.p.u("cdc_blfl2", "0");
		modelMap.put("blfl2", arg38);
		List arg40 = this.p.u("cdc_group_classify", "0");
		modelMap.put("groupClassify", arg40);
		List LABR = this.p.u("cdc_lab_result", "0");
		modelMap.put("LABR", LABR);
		String mdd = this.bL.getMDDiseaseId();
		modelMap.put("mdDiseasis", mdd);
		String diseasisList = this.bL.getNeedFKDiseasis();
		modelMap.put("fkDiseasis", diseasisList);
		String isShowFjhZzd = this.j.findByParamCode(Param.NIS_CRB_FJHZZD);
		String min_age;
		if ("0".equals(isShowFjhZzd)) {
			min_age = this.bL.getNeedFKDiseasisByParentId("2600");
			modelMap.put("fjhDiseasis", min_age);
		}

		modelMap.put("isShowFjhZzd", isShowFjhZzd);
		min_age = this.j.findByParamCode(Param.NIS_CDC_MIN_AGE);
		if (!"-1".equals(min_age)) {
			modelMap.put("NCMA", min_age);
		}

		String ifcheckBLFL2 = this.j.findByParamCode(Param.NIS_CDC_IFCHECK_BLFL2);
		modelMap.put("ifcheckBLFL2", ifcheckBLFL2);
		String ygbt = this.j.findByParamCode(Param.NIS_CDC_YG_BT);
		modelMap.put("YGBT", ygbt);
		String gbcode = this.j.findByParamCode(Param.NIS_HOSPITAL_GBCODE);
		if (!ab.aM(gbcode)) {
			modelMap.put("HASheng", gbcode.substring(0, 2) + "000000");
			modelMap.put("HAShi", gbcode.substring(0, 4) + "0000");
			modelMap.put("HAXian", gbcode.substring(0, 6) + "00");
			modelMap.put("HAXiang", gbcode);
		}

		modelMap.put("RDFD", this.j.findByParamCode(Param.NIS_CDC_RD_VALUE_FROM_DOCTOR));
		StringBuffer sbf = new StringBuffer("\'\'");
		if (ab.isNotEmpty(icds)) {
			String[] icdsz = icds.split("\\|");
			String[] arg27 = icdsz;
			int arg26 = icdsz.length;

			for (int arg25 = 0; arg25 < arg26; ++arg25) {
				String DL = arg27[arg25];
				sbf.append(",\'" + DL + "\'");
			}

			List arg41 = this.bL.getByIds(sbf.toString());
			modelMap.put("DL", arg41);
		}

		return "cards/crbbk_mz";
	}

	@RequestMapping({"/cdc/f_json/bkChoose"})
	@ResponseBody
	@SqlLog(p = "传染病上报--患者传染病报卡列表")
	public void i(HttpServletRequest request, HttpServletResponse response, String id, String cardType) {
		List allbk;
		if ("mz".equals(cardType)) {
			if (!ab.aM(id)) {
				allbk = this.dj.getAllBK(id, cardType);
				this.a(response, allbk);
			}
		} else if (!ab.aM(id)) {
			allbk = this.dj.getAllBK(id, cardType);
			this.a(response, allbk);
		}

	}

	@RequestMapping({"/cdc/f_view/reportCardZY"})
	@SqlLog(p = "传染病上报--新增传染病报卡")
	public String a(HttpServletRequest request, ModelMap modelMap, String zyid, String mzid, String masterid,
			String type, String justLook, String icds) {
		String needFKDiseasis = this.bL.getNeedFKDiseasis();
		modelMap.put("sexcards", needFKDiseasis);
		AcAccount account = (AcAccount) this.b(request);
		if (!"hospital".equals(account.getAcType())) {
			modelMap.put("acType", "doctor");
		} else {
			modelMap.put("acType", "hospital");
		}

		if (ab.aM(zyid) && "empty".equals(type)) {
			modelMap.put("tipMsg", "您在进行法定传染病空卡上报！");
		}

		List arg33;
		if (!ab.aM(masterid)) {
			CtgBk001Crbmaster typeCode = this.dj.get(masterid);
			if (typeCode != null) {
				modelMap.put("mz", typeCode);
				modelMap.put("MSTID", typeCode.getMasterid());
				modelMap.put("BKT", "crbbk");
				String patientBelong = typeCode.getAddrcode();
				if (!ab.aM(patientBelong)) {
					modelMap.put("curSheng", patientBelong.substring(0, 2) + "000000");
					modelMap.put("curShi", patientBelong.substring(0, 4) + "0000");
					modelMap.put("curXian", patientBelong.substring(0, 6) + "00");
					modelMap.put("curXiang", patientBelong);
				}
			}

			arg33 = this.dk.get(masterid);
			modelMap.put("AllDisease", arg33);
			Iterator blfl2 = arg33.iterator();

			while (blfl2.hasNext()) {
				CtgBk001Crbdisease blfl = (CtgBk001Crbdisease) blfl2.next();
				if (blfl != null) {
					modelMap.put("flag", blfl.getFlag());
					break;
				}
			}

			boolean arg35 = this.dl.t(masterid);
			if (arg35) {
				modelMap.put("HIVBK", "exist");
			}

			boolean arg37 = this.dx.v(masterid);
			if (arg37) {
				modelMap.put("TBBK", "exist");
			}

			Iterator LABR = arg33.iterator();

			while (LABR.hasNext()) {
				CtgBk001Crbdisease groupClassify = (CtgBk001Crbdisease) LABR.next();
				if (groupClassify != null && "0302".equals(groupClassify.getDiseaseid())) {
					modelMap.put("YGBK", "exist");
					break;
				}
			}

			int arg39 = typeCode.getIsemptycard();
			if (arg39 > 0) {
				modelMap.put("isEmptyCard", "Y");
			}
		} else if (!ab.aM(zyid)) {
			St003Cryxxb arg30 = this.df.get(zyid);
			if (arg30 != null && ab.isNotEmpty(arg30.getPatientId())) {
				St001Jbxxb arg34 = this.dg.get(arg30.getPatientId());
				if (arg34 != null) {
					if (!ab.aM(arg34.getTel())) {
						arg30.setTel(arg34.getTel());
					}

					if (!ab.aM(arg34.getNation())) {
						arg30.setNation(arg34.getNation());
					}

					if (arg34.getBirthDate() != null && arg30.getBirthDate() == null) {
						arg30.setBirthDate(arg34.getBirthDate());
					}

					if (arg34.getIdCardId() != null) {
						arg30.setIdCard(arg34.getIdCardId());
					}

					if (arg34.getAddress() != null) {
						arg30.setAddress(arg34.getAddress());
					}
				}
			} else {
				modelMap.put("errMsg", "未找到病人信息！");
			}

			modelMap.put("BRXX", arg30);
		} else if (!ab.aM(mzid)) {
			St020ClinicPatients arg31 = this.bh.get(mzid);
			if (arg31 == null) {
				modelMap.put("errMsg", "未找到病人信息！");
			}

			modelMap.put("BRXX", arg31);
		}

		if (!ab.aM(justLook)) {
			modelMap.put("justLook", "Y");
		}

		List arg32 = this.p.u("cdc_idcard_type", "0");
		modelMap.put("cardType", arg32);
		arg33 = this.p.u("cdc_patient_belong", "0");
		modelMap.put("patientBelong", arg33);
		List arg36 = this.p.u("cdc_blfl", "0");
		modelMap.put("blfl", arg36);
		List arg38 = this.p.u("cdc_blfl2", "0");
		modelMap.put("blfl2", arg38);
		List arg40 = this.p.u("cdc_group_classify", "0");
		modelMap.put("groupClassify", arg40);
		List arg41 = this.p.u("cdc_lab_result", "0");
		modelMap.put("LABR", arg41);
		String irprn = this.j.findByParamCode(Param.NIS_CDC_IS_RPR_NECESSARY);
		modelMap.put("IRPRN", irprn);
		String mdd = this.bL.getMDDiseaseId();
		modelMap.put("mdDiseasis", mdd);
		String diseasisList = this.bL.getNeedFKDiseasis();
		modelMap.put("fkDiseasis", diseasisList);
		String isShowFjhZzd = this.j.findByParamCode(Param.NIS_CRB_FJHZZD);
		String min_age;
		if ("0".equals(isShowFjhZzd)) {
			min_age = this.bL.getNeedFKDiseasisByParentId("2600");
			modelMap.put("fjhDiseasis", min_age);
		}

		modelMap.put("isShowFjhZzd", isShowFjhZzd);
		min_age = this.j.findByParamCode(Param.NIS_CDC_MIN_AGE);
		if (!"-1".equals(min_age)) {
			modelMap.put("NCMA", min_age);
		}

		String ifcheckBLFL2 = this.j.findByParamCode(Param.NIS_CDC_IFCHECK_BLFL2);
		modelMap.put("ifcheckBLFL2", ifcheckBLFL2);
		String ygbt = this.j.findByParamCode(Param.NIS_CDC_YG_BT);
		modelMap.put("YGBT", ygbt);
		String gbcode = this.j.findByParamCode(Param.NIS_HOSPITAL_GBCODE);
		if (!ab.aM(gbcode)) {
			modelMap.put("HASheng", gbcode.substring(0, 2) + "000000");
			modelMap.put("HAShi", gbcode.substring(0, 4) + "0000");
			modelMap.put("HAXian", gbcode.substring(0, 6) + "00");
			modelMap.put("HAXiang", gbcode);
		}

		modelMap.put("RDFD", this.j.findByParamCode(Param.NIS_CDC_RD_VALUE_FROM_DOCTOR));
		StringBuffer sbf = new StringBuffer("\'\'");
		if (ab.isNotEmpty(icds)) {
			String[] icdsz = icds.split("|");
			String[] arg29 = icdsz;
			int arg28 = icdsz.length;

			for (int arg27 = 0; arg27 < arg28; ++arg27) {
				String DL = arg29[arg27];
				sbf.append(",\'" + DL + "\'");
			}

			List arg42 = this.bL.getByIds(sbf.toString());
			modelMap.put("DL", arg42);
		}

		return !ab.aM(zyid) ? "cards/crbbk_zy" : "cards/crbbk_mz";
	}

	@RequestMapping({"/cdc/f_json/chooseDisease"})
	@ResponseBody
	public void j(HttpServletRequest request, HttpServletResponse response, String keyword, String q) {
		if (keyword != null && !"".equals(keyword)) {
			keyword = ab.aR(keyword);
		}

		List search = this.bL.search(keyword);
		this.a(response, search);
	}

	@RequestMapping({"/cdc/f_json/chooseDiseaseForControl"})
	@ResponseBody
	public void k(HttpServletRequest request, HttpServletResponse response, String keyword, String q) {
		List search = this.bL.search(ab.aR(q));
		this.a(response, search);
	}

	@RequestMapping({"/cdc/f_view/ygfk"})
	public String f(HttpServletRequest request, ModelMap modelMap, String masterid) {
		if (!ab.aM(masterid)) {
			CtgBk001Crbhbvcard ygbt = this.dm.getByMasterId(masterid);
			if (ygbt != null) {
				modelMap.put("YGFK", ygbt);
			}
		}

		String ygbt1 = this.j.findByParamCode(Param.NIS_CDC_YG_BT);
		modelMap.put("YGBT", ygbt1);
		return "cards/additionCards/ygfk";
	}

	@RequestMapping({"/cdc/f_view/afpfk"})
	public String j(HttpServletRequest request, HttpServletResponse response, String masterid) {
		return "cards/additionCards/afpfk";
	}

	@RequestMapping({"/cdc/f_view/xbfk"})
	public String g(HttpServletRequest request, ModelMap modelMap, String masterid, String mzid) {
		modelMap.put("currTime", new Date());
		if (!ab.aM(mzid)) {
			St020ClinicPatients jcs = this.bh.get(mzid);
			if (jcs != null) {
				modelMap.put("regaddress", jcs.getRegisteraddr());
			}
		}

		List jcs1 = this.p.u("cdc_jcs", "0");
		modelMap.put("jcs", jcs1);
		List infectWay = this.p.u("cdc_infect_way", "0");
		modelMap.put("infectWay", infectWay);
		List maritalStatus = this.p.u("cdc_marital_status", "0");
		modelMap.put("maritalStatus", maritalStatus);
		List patientBelong = this.p.u("cdc_patient_belong", "0");
		modelMap.put("patientBelong", patientBelong);
		List sampleFrom = this.p.u("cdc_sample_from", "0");
		modelMap.put("sampleFrom", sampleFrom);
		List labConclusion = this.p.u("cdc_lab_conclusion", "0");
		modelMap.put("labConclusion", labConclusion);
		List education = this.p.u("cdc_education", "0");
		modelMap.put("education", education);
		List nation = this.p.u("cdc_nation", "0");
		modelMap.put("nation", nation);
		String gbcode = this.j.findByParamCode(Param.NIS_HOSPITAL_GBCODE);
		if (!ab.aM(gbcode)) {
			modelMap.put("HASheng", gbcode.substring(0, 2) + "000000");
			modelMap.put("HAShi", gbcode.substring(0, 4) + "0000");
			modelMap.put("HAXian", gbcode.substring(0, 6) + "00");
			modelMap.put("HAXiang", gbcode);
		}

		if (!ab.aM(masterid)) {
			System.err.println("查询数据");
			CtgBk001Crbstdcard crbstdcard = this.dl.get(masterid);
			if (crbstdcard != null) {
				modelMap.put("XBFK", crbstdcard);
				String addrcode = crbstdcard.getRegaddrcode();
				modelMap.put("curRegSheng", addrcode.substring(0, 2) + "000000");
				modelMap.put("curRegShi", addrcode.substring(0, 4) + "0000");
				modelMap.put("curRegXian", addrcode.substring(0, 6) + "00");
				modelMap.put("curRegXiang", addrcode);
			}
		}

		return "cards/additionCards/xbfk";
	}

	@RequestMapping({"/cdc/f_view/fjhfk"})
	public String g(HttpServletRequest request, ModelMap modelMap, String masterid) {
		List patientSourceList = this.p.u("cdc_patient_source", "0");
		modelMap.put("patientSourceList", patientSourceList);
		if (!ab.aM(masterid)) {
			System.err.println("查询数据");
			CtgBk001Tb bk001Tb = this.ds.get(masterid);
			if (bk001Tb != null) {
				modelMap.put("FJHFK", bk001Tb);
			}
		}

		return "cards/additionCards/fjhfk";
	}

	@RequestMapping({"/cdc/f_view/fjhzzdfk"})
	public String h(HttpServletRequest request, ModelMap modelMap, String masterid) {
		if (!ab.aM(masterid)) {
			CtgBk001Zzd bk001Zzd = this.dx.getByMasterid(masterid);
			if (bk001Zzd != null) {
				modelMap.put("fjh", bk001Zzd);
			}
		}

		return "cards/additionCards/fjhzzdfk";
	}

	@RequestMapping({"/cdc/f_json/saveCards"})
	@ResponseBody
	public void a(HttpServletRequest request, HttpServletResponse response, CtgBk001Crbmaster ctgBk001Crbmaster) {
		AcAccount acount = (AcAccount) this.b(request);
		Result r = this.dj.a(ctgBk001Crbmaster, acount);
		this.a(response, r);
	}

	@RequestMapping({"/cdc/f_json/getSheng"})
	@ResponseBody
	public void n(HttpServletRequest request, HttpServletResponse response) {
		List sheng = this.dh.getSheng();
		this.a(response, sheng);
	}

	@RequestMapping({"/cdc/f_json/getOther"})
	@ResponseBody
	public void k(HttpServletRequest request, HttpServletResponse response, String areacode) {
		List other = this.dh.getOther(areacode);
		this.a(response, other);
	}

	@RequestMapping({"/cdc/f_view/cardsAdmin"})
	@SqlLog(p = "传染病上报审核--上报卡审核查询条件")
	public String b(HttpServletRequest request, ModelMap modelMap, String queryStartDate, String queryEndDate,
			String cardType) {
		if (StringUtils.isBlank(queryStartDate)) {
			Calendar reportUrl = Calendar.getInstance();
			reportUrl.set(5, 1);
			queryStartDate = this.sdf.format(reportUrl.getTime());
		}

		if (StringUtils.isBlank(queryEndDate)) {
			queryEndDate = this.sdf.format(Long.valueOf((new Date()).getTime()));
		}

		modelMap.put("cardType", cardType);
		modelMap.put("queryStartDate", queryStartDate);
		modelMap.put("queryEndDate", queryEndDate);
		String reportUrl1 = this.j.findByParamCode(Param.NIS_REPORT_URL);
		modelMap.put("reportUrl", reportUrl1);
		List allowBKs = this.p.u("cdc_card_type", (String) null);
		modelMap.put("allowCards", allowBKs);
		String needFKDiseasis = this.bL.getNeedFKDiseasis();
		modelMap.put("sexcards", needFKDiseasis);
		String crbZb = this.j.findByParamCode(Param.NIS_CRB_ZB);
		modelMap.put("crbZb", crbZb);
		String isShowFjhZzd = this.j.findByParamCode(Param.NIS_CRB_FJHZZD);
		if ("0".equals(isShowFjhZzd)) {
			String fjhDiseasisList = this.bL.getNeedFKDiseasisByParentId("2600");
			modelMap.put("fjhDiseasis", fjhDiseasisList);
		}

		modelMap.put("isShowFjhZzd", isShowFjhZzd);
		return "cardsAdmin";
	}

	@RequestMapping({"/cdc/f_view/getAllCards"})
   @SqlLog(
      p = "传染病上报审核--上报卡列表"
   )
   public String a(HttpServletRequest request, ModelMap modelMap, CtgBk001Crbmaster ctgBk001Crbmaster) {
      AcAccount user = (AcAccount)this.b(request);
      String isShowPrintTag = this.j.findByParamCode(Param.NIS_CDC_IS_SHOW_PRINT_TAG);
      modelMap.put("isShowPrintTag", isShowPrintTag);
      if(ctgBk001Crbmaster.getSearchString() != null && !"".equals(ctgBk001Crbmaster.getSearchString())) {
         ctgBk001Crbmaster.setSearchString(ab.aR(ctgBk001Crbmaster.getSearchString()));
      }

      List cardsForAdmin;
      if(!"crbbk".equals(ctgBk001Crbmaster.getCardType()) && !ab.aM(ctgBk001Crbmaster.getCardType())) {
         if("sybk".equals(ctgBk001Crbmaster.getCardType())) {
            ctgBk001Crbmaster.setUserid(user.getUserId() == null?"":user.getUserId());
            cardsForAdmin = this.doo.findCardsForAdmin(ctgBk001Crbmaster);
            modelMap.put("Cards", cardsForAdmin);
            return "cdc/syCardsTable";
         } else if("syjcbk".equals(ctgBk001Crbmaster.getCardType())) {
            ctgBk001Crbmaster.setUserid(user.getUserId() == null?"":user.getUserId());
            cardsForAdmin = this.dp.findCardsForAdmin(ctgBk001Crbmaster);
            modelMap.put("Cards", cardsForAdmin);
            return "cdc/syjcCardsTable";
         } else if("zlbk".equals(ctgBk001Crbmaster.getCardType())) {
            ctgBk001Crbmaster.setUserid(user.getUserId() == null?"":user.getUserId());
            cardsForAdmin = this.dq.findCardsForAdmin(ctgBk001Crbmaster);
            modelMap.put("Cards", cardsForAdmin);
            return "cdc/tumourCardsTable";
         } else if("syycbk".equals(ctgBk001Crbmaster.getCardType())) {
            ctgBk001Crbmaster.setUserid(user.getUserId() == null?"":user.getUserId());
            cardsForAdmin = this.dr.findCardsForAdmin(ctgBk001Crbmaster);
            modelMap.put("Cards", cardsForAdmin);
            return "cdc/syycCardsTable";
         } else if("xnxgbk".equals(ctgBk001Crbmaster.getCardType())) {
            ctgBk001Crbmaster.setUserid(user.getUserId() == null?"":user.getUserId());
            cardsForAdmin = this.du.findCardsForAdmin(ctgBk001Crbmaster);
            modelMap.put("Cards", cardsForAdmin);
            return "cdc/xnxgCardsTable";
         } else if("gwzsbk".equals(ctgBk001Crbmaster.getCardType())) {
            ctgBk001Crbmaster.setUserid(user.getUserId() == null?"":user.getUserId());
            cardsForAdmin = this.dv.findCardsForAdmin(ctgBk001Crbmaster);
            modelMap.put("Cards", cardsForAdmin);
            return "cdc/gwzsCardsTable";
         } else if("nyzdbk".equals(ctgBk001Crbmaster.getCardType())) {
            ctgBk001Crbmaster.setUserid(user.getUserId() == null?"":user.getUserId());
            cardsForAdmin = this.dy.findCardsForAdmin(ctgBk001Crbmaster);
            modelMap.put("Cards", cardsForAdmin);
            return "cdc/nyzdCardsTable";
         } else {
            return "cdc/cardsTable";
         }
      } else {
         ctgBk001Crbmaster.setUserid(user.getUserId() == null?"":user.getUserId());
         cardsForAdmin = this.dj.findCardsForAdmin(ctgBk001Crbmaster);
         modelMap.put("Cards", cardsForAdmin);
         return "cdc/cardsTable";
      }
   }

	@RequestMapping({"/cdc/f_json/auditCards"})
   @ResponseBody
   @Transactional
   @SqlLog(
      p = "传染病上报审核--审核上报卡"
   )
   public void l(HttpServletRequest request, HttpServletResponse response, String masterid, String bktype) {
      Result r = new Result();
      AcAccount user = (AcAccount)this.b(request);
      if(!ab.aM(masterid) && !ab.aM(bktype) && user != null) {
         try {
            if("crbbk".equals(bktype)) {
               CtgBk001Crbdisease e = new CtgBk001Crbdisease();
               e.setAuditdate(new Date());
               e.setAuditor(user.getUsername());
               e.setAuditorname(user.getRealname());
               e.setMasterid(masterid);
               this.dk.audit(e);
               CtgBk001Crbmaster nyzdbk = this.dj.get(masterid);
               StringBuffer content = new StringBuffer();
               List diseaseList = this.dk.get(masterid);
               if(diseaseList != null) {
                  Iterator arg11 = diseaseList.iterator();

                  while(arg11.hasNext()) {
                     CtgBk001Crbdisease content1 = (CtgBk001Crbdisease)arg11.next();
                     if(content1.getDiseaseid() != null) {
                        content.append(" " + content1.getDiseasename());
                     }
                  }
               }

               String content3 = "您上报的传染病报卡（" + content + "）审核通过";
               this.cV.a(nyzdbk.getZyid(), nyzdbk.getMzid(), user.getUsername(), user.getRealname(), content3, new String[]{nyzdbk.getReportdoctorid()}, (String[])null, al.jP.getValue(), masterid);
               r.setResult("success");
               r.setMsg("审核成功！");
            } else {
               String content2;
               if("sybk".equals(bktype)) {
                  CtgBk002Sybk e1 = new CtgBk002Sybk();
                  e1.setAuditdate(new Date());
                  e1.setAuditor(user.getUsername());
                  e1.setAuditorname(user.getRealname());
                  e1.setMasterid(masterid);
                  this.doo.audit(e1);
                  CtgBk002Sybk nyzdbk1 = this.doo.get(masterid);
                  content2 = "您上报的 居民死因报卡 审核通过";
                  this.cV.a(nyzdbk1.getZyid(), nyzdbk1.getMzid(), user.getUsername(), user.getRealname(), content2, new String[]{nyzdbk1.getReportdoctorid()}, (String[])null, al.jQ.getValue(), masterid);
                  r.setResult("success");
                  r.setMsg("审核成功！");
               } else if("syjcbk".equals(bktype)) {
                  CtgBk005Syjc e2 = new CtgBk005Syjc();
                  e2.setValidDate(new Date());
                  e2.setValidperson(user.getUsername());
                  e2.setValidpersonname(user.getRealname());
                  e2.setMasterid(masterid);
                  this.dp.audit(e2);
                  CtgBk005Syjc nyzdbk2 = this.dp.get(masterid);
                  content2 = "您上报的 食源监测报卡 审核通过";
                  this.cV.a(nyzdbk2.getZyid(), nyzdbk2.getMzid(), user.getUsername(), user.getRealname(), content2, new String[]{nyzdbk2.getReportdoctorid()}, (String[])null, al.jS.getValue(), masterid);
                  r.setResult("success");
                  r.setMsg("审核成功！");
               } else if("zlbk".equals(bktype)) {
                  CtgBk006Tumour e3 = new CtgBk006Tumour();
                  e3.setValiddt(new Date());
                  e3.setValidpersonid(user.getUsername());
                  e3.setValidpersonname(user.getRealname());
                  e3.setMasterid(masterid);
                  this.dq.audit(e3);
                  CtgBk006Tumour nyzdbk3 = this.dq.get(masterid);
                  content2 = "您上报的 肿瘤病例报卡 审核通过";
                  this.cV.a(nyzdbk3.getZyid(), nyzdbk3.getMzid(), user.getUsername(), user.getRealname(), content2, new String[]{nyzdbk3.getReportdoctorid()}, (String[])null, al.jT.getValue(), masterid);
                  r.setResult("success");
                  r.setMsg("审核成功！");
               } else if("syycbk".equals(bktype)) {
                  CtgBk004Syycbk e4 = new CtgBk004Syycbk();
                  e4.setValiddt(new Date());
                  e4.setValidpersonid(user.getUsername());
                  e4.setValidpersonname(user.getRealname());
                  e4.setMasterid(masterid);
                  this.dr.audit(e4);
                  CtgBk004Syycbk nyzdbk4 = this.dr.get(masterid);
                  content2 = "您上报的 食源异常报卡 审核通过";
                  this.cV.a(nyzdbk4.getZyid(), nyzdbk4.getMzid(), user.getUsername(), user.getRealname(), content2, new String[]{nyzdbk4.getReportdoctorid()}, (String[])null, al.jR.getValue(), masterid);
                  r.setResult("success");
                  r.setMsg("审核成功！");
               } else if("xnxgbk".equals(bktype)) {
                  CtgBk008Ccvd e5 = new CtgBk008Ccvd();
                  e5.setValiddt(new Date());
                  e5.setValidpersonid(user.getUsername());
                  e5.setValidpersonname(user.getRealname());
                  e5.setMasterid(masterid);
                  this.du.audit(e5);
                  CtgBk008Ccvd nyzdbk5 = this.du.get(masterid);
                  content2 = "您上报的 心脑血管事件报卡 审核通过";
                  this.cV.a(nyzdbk5.getZyid(), nyzdbk5.getMzid(), user.getUsername(), user.getRealname(), content2, new String[]{nyzdbk5.getReportdoctorid()}, (String[])null, al.jU.getValue(), masterid);
                  r.setResult("success");
                  r.setMsg("审核成功！");
               } else if("gwzsbk".equals(bktype)) {
                  CtgBk009Sunstroke e6 = new CtgBk009Sunstroke();
                  e6.setValiddt(new Date());
                  e6.setValidpersonid(user.getUsername());
                  e6.setValidpersonname(user.getRealname());
                  e6.setMasterid(masterid);
                  this.dv.audit(e6);
                  CtgBk009Sunstroke nyzdbk6 = this.dv.get(masterid);
                  content2 = "您上报的 高温中暑病例报卡 审核通过";
                  this.cV.a(nyzdbk6.getZyid(), nyzdbk6.getMzid(), user.getUsername(), user.getRealname(), content2, new String[]{nyzdbk6.getReportdoctorid()}, (String[])null, al.jV.getValue(), nyzdbk6.getMasterid());
                  r.setResult("success");
                  r.setMsg("审核成功！");
               } else if("nyzdbk".equals(bktype)) {
                  CtgBk010Pesticide e7 = new CtgBk010Pesticide();
                  e7.setValiddt(new Date());
                  e7.setValidpersonid(user.getUsername());
                  e7.setValidpersonname(user.getRealname());
                  e7.setMasterid(masterid);
                  this.dy.audit(e7);
                  CtgBk010Pesticide nyzdbk7 = this.dy.get(masterid);
                  content2 = "您上报的 农药中毒报卡 审核通过";
                  this.cV.a(nyzdbk7.getZyid(), nyzdbk7.getMzid(), user.getUsername(), user.getRealname(), content2, new String[]{nyzdbk7.getReportdoctorid()}, (String[])null, al.ka.getValue(), nyzdbk7.getMasterid());
                  r.setResult("success");
                  r.setMsg("审核成功！");
               }
            }
         } catch (Exception arg12) {
            arg12.printStackTrace();
            c.error("审核出错了！", arg12);
            r.setResult("error");
            r.setMsg("出错了！操作失败！错误信息：" + arg12.getMessage());
         }
      } else {
         r.setResult("error");
         r.setMsg("必要字段获取为空！审核失败！");
      }

      this.a(response, r);
   }

	@RequestMapping({"/cdc/f_json/cancelCards"})
   @ResponseBody
   @Transactional
   @SqlLog(
      p = "传染病上报审核--取消上报卡"
   )
   public void m(HttpServletRequest request, HttpServletResponse response, String masterid, String bktype) {
      Result r = new Result();
      AcAccount user = (AcAccount)this.b(request);
      if(!ab.aM(masterid) && !ab.aM(bktype) && user != null) {
         try {
            if("crbbk".equals(bktype)) {
               CtgBk001Crbdisease e = new CtgBk001Crbdisease();
               e.setAuditdate(new Date());
               e.setAuditor(user.getUsername());
               e.setAuditorname(user.getRealname());
               e.setMasterid(masterid);
               this.dk.cancel(e);
               CtgBk001Crbmaster cm = new CtgBk001Crbmaster();
               cm.setMasterid(masterid);
               cm.setDelreason("");
               this.dj.updateDelReason(cm);
               r.setResult("success");
               r.setMsg("取消操作成功！");
            } else if("sybk".equals(bktype)) {
               CtgBk002Sybk e1 = new CtgBk002Sybk();
               e1.setAuditdate(new Date());
               e1.setAuditor(user.getUsername());
               e1.setAuditorname(user.getRealname());
               e1.setMasterid(masterid);
               e1.setDelreason("");
               this.doo.cancel(e1);
               r.setResult("success");
               r.setMsg("取消操作成功！");
            } else if("syjcbk".equals(bktype)) {
               CtgBk005Syjc e2 = new CtgBk005Syjc();
               e2.setValidDate(new Date());
               e2.setValidperson(user.getUsername());
               e2.setValidpersonname(user.getRealname());
               e2.setMasterid(masterid);
               e2.setRejectReason("");
               this.dp.cancel(e2);
               r.setResult("success");
               r.setMsg("取消操作成功！");
            } else if("zlbk".equals(bktype)) {
               CtgBk006Tumour e3 = new CtgBk006Tumour();
               e3.setValiddt(new Date());
               e3.setValidpersonid(user.getUsername());
               e3.setValidpersonname(user.getRealname());
               e3.setMasterid(masterid);
               e3.setDelreason("");
               this.dq.cancel(e3);
               r.setResult("success");
               r.setMsg("取消操作成功！");
            } else if("syycbk".equals(bktype)) {
               CtgBk004Syycbk e4 = new CtgBk004Syycbk();
               e4.setValiddt(new Date());
               e4.setValidpersonid(user.getUsername());
               e4.setValidpersonname(user.getRealname());
               e4.setMasterid(masterid);
               e4.setRejectReason("");
               this.dr.cancel(e4);
               r.setResult("success");
               r.setMsg("取消操作成功！");
            } else if("xnxgbk".equals(bktype)) {
               CtgBk008Ccvd e5 = new CtgBk008Ccvd();
               e5.setValiddt(new Date());
               e5.setValidpersonid(user.getUsername());
               e5.setValidpersonname(user.getRealname());
               e5.setMasterid(masterid);
               e5.setDelreason("");
               this.du.cancel(e5);
               r.setResult("success");
               r.setMsg("取消操作成功！");
            } else if("gwzsbk".equals(bktype)) {
               CtgBk009Sunstroke e6 = new CtgBk009Sunstroke();
               e6.setValiddt(new Date());
               e6.setValidpersonid(user.getUsername());
               e6.setValidpersonname(user.getRealname());
               e6.setMasterid(masterid);
               e6.setDelreason("");
               this.dv.cancel(e6);
               r.setResult("success");
               r.setMsg("取消操作成功！");
            } else if("nyzdbk".equals(bktype)) {
               CtgBk010Pesticide e7 = new CtgBk010Pesticide();
               e7.setValiddt(new Date());
               e7.setValidpersonid(user.getUsername());
               e7.setValidpersonname(user.getRealname());
               e7.setMasterid(masterid);
               e7.setRejectReason("");
               this.dy.cancel(e7);
               r.setResult("success");
               r.setMsg("取消操作成功！");
            }
         } catch (Exception arg8) {
            arg8.printStackTrace();
            c.error("取消操作失败", arg8);
            r.setResult("error");
            r.setMsg("出错了！操作失败！错误信息：" + arg8.getMessage());
         }
      } else {
         r.setResult("error");
         r.setMsg("必要字段获取为空！取消操作失败！");
      }

      this.a(response, r);
   }

	@RequestMapping({"/cdc/f_json/retreatCards"})
   @ResponseBody
   @Transactional
   @SqlLog(
      p = "传染病上报审核--退回上报卡"
   )
   public void f(HttpServletRequest request, HttpServletResponse response, String masterid, String bktype, String delreason) {
      Result r = new Result();
      AcAccount user = (AcAccount)this.b(request);
      if(!ab.aM(masterid) && !ab.aM(bktype) && user != null && !ab.aM(delreason)) {
         try {
            if("crbbk".equals(bktype)) {
               CtgBk001Crbdisease e = new CtgBk001Crbdisease();
               e.setAuditdate(new Date());
               e.setAuditor(user.getUsername());
               e.setAuditorname(user.getRealname());
               e.setMasterid(masterid);
               this.dk.retreat(e);
               CtgBk001Crbmaster nyzdbk = new CtgBk001Crbmaster();
               nyzdbk.setMasterid(masterid);
               nyzdbk.setDelreason(delreason);
               this.dj.updateDelReason(nyzdbk);
               CtgBk001Crbmaster content = this.dj.get(masterid);
               StringBuffer allDN = new StringBuffer();
               List diseaseList = this.dk.get(masterid);
               if(diseaseList != null) {
                  Iterator arg13 = diseaseList.iterator();

                  while(arg13.hasNext()) {
                     CtgBk001Crbdisease content1 = (CtgBk001Crbdisease)arg13.next();
                     if(content1.getDiseaseid() != null) {
                        allDN.append(" " + content1.getDiseasename());
                     }
                  }
               }

               String content3 = "您上报的传染病报卡（" + allDN + "）被退卡";
               this.cV.a(content.getZyid(), content.getMzid(), user.getUsername(), user.getRealname(), content3, new String[]{content.getReportdoctorid()}, (String[])null, al.jB.getValue(), masterid);
               r.setResult("success");
               r.setMsg("退回操作成功！");
            } else {
               String content2;
               if("sybk".equals(bktype)) {
                  CtgBk002Sybk e1 = new CtgBk002Sybk();
                  e1.setAuditdate(new Date());
                  e1.setAuditor(user.getUsername());
                  e1.setAuditorname(user.getRealname());
                  e1.setMasterid(masterid);
                  e1.setDelreason(delreason);
                  this.doo.retreat(e1);
                  CtgBk002Sybk nyzdbk1 = this.doo.get(masterid);
                  content2 = "您上报的 居民死因报卡 被退卡";
                  this.cV.a(nyzdbk1.getZyid(), nyzdbk1.getMzid(), user.getUsername(), user.getRealname(), content2, new String[]{nyzdbk1.getReportdoctorid()}, (String[])null, al.jC.getValue(), masterid);
                  r.setResult("success");
                  r.setMsg("退回操作成功！");
               } else if("syjcbk".equals(bktype)) {
                  CtgBk005Syjc e2 = new CtgBk005Syjc();
                  e2.setValidDate(new Date());
                  e2.setValidperson(user.getUsername());
                  e2.setValidpersonname(user.getRealname());
                  e2.setMasterid(masterid);
                  e2.setRejectReason(delreason);
                  this.dp.retreat(e2);
                  CtgBk005Syjc nyzdbk2 = this.dp.get(masterid);
                  content2 = "您上报的 食源监测报卡 被退卡";
                  this.cV.a(nyzdbk2.getZyid(), nyzdbk2.getMzid(), user.getUsername(), user.getRealname(), content2, new String[]{nyzdbk2.getReportdoctorid()}, (String[])null, al.jE.getValue(), masterid);
                  r.setResult("success");
                  r.setMsg("退回操作成功！");
               } else if("zlbk".equals(bktype)) {
                  CtgBk006Tumour e3 = new CtgBk006Tumour();
                  e3.setValiddt(new Date());
                  e3.setValidpersonid(user.getUsername());
                  e3.setValidpersonname(user.getRealname());
                  e3.setMasterid(masterid);
                  e3.setDelreason(delreason);
                  this.dq.retreat(e3);
                  CtgBk006Tumour nyzdbk3 = this.dq.get(masterid);
                  content2 = "您上报的 肿瘤病例报卡 被退卡";
                  this.cV.a(nyzdbk3.getZyid(), nyzdbk3.getMzid(), user.getUsername(), user.getRealname(), content2, new String[]{nyzdbk3.getReportdoctorid()}, (String[])null, al.jF.getValue(), masterid);
                  r.setResult("success");
                  r.setMsg("退回操作成功！");
               } else if("syycbk".equals(bktype)) {
                  CtgBk004Syycbk e4 = new CtgBk004Syycbk();
                  e4.setValiddt(new Date());
                  e4.setValidpersonid(user.getUsername());
                  e4.setValidpersonname(user.getRealname());
                  e4.setMasterid(masterid);
                  e4.setRejectReason(delreason);
                  this.dr.retreat(e4);
                  CtgBk004Syycbk nyzdbk4 = this.dr.get(masterid);
                  content2 = "您上报的 食源异常报卡 被退卡";
                  this.cV.a(nyzdbk4.getZyid(), nyzdbk4.getMzid(), user.getUsername(), user.getRealname(), content2, new String[]{nyzdbk4.getReportdoctorid()}, (String[])null, al.jD.getValue(), masterid);
                  r.setResult("success");
                  r.setMsg("退回操作成功！");
               } else if("xnxgbk".equals(bktype)) {
                  CtgBk008Ccvd e5 = new CtgBk008Ccvd();
                  e5.setValiddt(new Date());
                  e5.setValidpersonid(user.getUsername());
                  e5.setValidpersonname(user.getRealname());
                  e5.setMasterid(masterid);
                  e5.setDelreason(delreason);
                  this.du.retreat(e5);
                  CtgBk008Ccvd nyzdbk5 = this.du.get(masterid);
                  content2 = "您上报的 心脑血管事件报卡 被退卡";
                  this.cV.a(nyzdbk5.getZyid(), nyzdbk5.getMzid(), user.getUsername(), user.getRealname(), content2, new String[]{nyzdbk5.getReportdoctorid()}, (String[])null, al.jG.getValue(), masterid);
                  r.setResult("success");
                  r.setMsg("退回操作成功！");
               } else if("gwzsbk".equals(bktype)) {
                  CtgBk009Sunstroke e6 = new CtgBk009Sunstroke();
                  e6.setValiddt(new Date());
                  e6.setValidpersonid(user.getUsername());
                  e6.setValidpersonname(user.getRealname());
                  e6.setMasterid(masterid);
                  e6.setDelreason(delreason);
                  this.dv.retreat(e6);
                  CtgBk009Sunstroke nyzdbk6 = this.dv.get(masterid);
                  content2 = "您上报的 高温中暑病例报卡 被退卡";
                  this.cV.a(nyzdbk6.getZyid(), nyzdbk6.getMzid(), user.getUsername(), user.getRealname(), content2, new String[]{nyzdbk6.getReportdoctorid()}, (String[])null, al.jH.getValue(), nyzdbk6.getMasterid());
                  r.setResult("success");
                  r.setMsg("退回操作成功！");
               } else if("nyzdbk".equals(bktype)) {
                  CtgBk010Pesticide e7 = new CtgBk010Pesticide();
                  e7.setValiddt(new Date());
                  e7.setValidpersonid(user.getUsername());
                  e7.setValidpersonname(user.getRealname());
                  e7.setMasterid(masterid);
                  e7.setRejectReason(delreason);
                  this.dy.retreat(e7);
                  CtgBk010Pesticide nyzdbk7 = this.dy.get(masterid);
                  content2 = "您上报的 农药中毒报卡 被退卡";
                  this.cV.a(nyzdbk7.getZyid(), nyzdbk7.getMzid(), user.getUsername(), user.getRealname(), content2, new String[]{nyzdbk7.getReportdoctorid()}, (String[])null, al.jY.getValue(), nyzdbk7.getMasterid());
                  r.setResult("success");
                  r.setMsg("退回操作成功！");
               }
            }
         } catch (Exception arg14) {
            arg14.printStackTrace();
            c.error("退回操作出错！", arg14);
            r.setResult("error");
            r.setMsg("出错了！操作失败！错误信息：" + arg14.getMessage());
         }
      } else {
         r.setResult("error");
         r.setMsg("必要字段获取为空！退回操作失败！");
      }

      this.a(response, r);
   }

	@RequestMapping({"/cdc/f_json/removeCards"})
   @ResponseBody
   @Transactional
   @SqlLog(
      p = "传染病上报审核--删除上报卡"
   )
   public void g(HttpServletRequest request, HttpServletResponse response, String masterid, String bktype, String delreason) {
      Result r = new Result();
      AcAccount user = (AcAccount)this.b(request);
      if(!ab.aM(masterid) && !ab.aM(bktype) && user != null && !ab.aM(delreason)) {
         try {
            if("crbbk".equals(bktype)) {
               CtgBk001Crbdisease e = new CtgBk001Crbdisease();
               e.setAuditdate(new Date());
               e.setAuditor(user.getUsername());
               e.setAuditorname(user.getRealname());
               e.setMasterid(masterid);
               this.dk.remove(e);
               CtgBk001Crbmaster gwzsbk = new CtgBk001Crbmaster();
               gwzsbk.setMasterid(masterid);
               gwzsbk.setDelreason(delreason);
               this.dj.updateDelReason(gwzsbk);
               CtgBk001Crbmaster crbmaster = this.dj.get(masterid);
               if(crbmaster.getIsemptycard() != 1) {
                  StringBuffer allDN = new StringBuffer();
                  List diseaseList = this.dk.get(masterid);
                  if(diseaseList != null) {
                     Iterator arg13 = diseaseList.iterator();

                     while(arg13.hasNext()) {
                        CtgBk001Crbdisease d = (CtgBk001Crbdisease)arg13.next();
                        if(d.getDiseaseid() != null) {
                           allDN.append(" " + d.getDiseasename());
                        }
                     }
                  }
               }

               r.setResult("success");
               r.setMsg("删卡操作成功！");
            } else if("sybk".equals(bktype)) {
               CtgBk002Sybk e1 = new CtgBk002Sybk();
               e1.setAuditdate(new Date());
               e1.setAuditor(user.getUsername());
               e1.setAuditorname(user.getRealname());
               e1.setMasterid(masterid);
               e1.setDelreason(delreason);
               this.doo.remove(e1);
               CtgBk002Sybk gwzsbk1 = this.doo.get(masterid);
               gwzsbk1.getIsemptycard().longValue();
               r.setResult("success");
               r.setMsg("删卡操作成功！");
            } else if("syjcbk".equals(bktype)) {
               CtgBk005Syjc e2 = new CtgBk005Syjc();
               e2.setValidDate(new Date());
               e2.setValidperson(user.getUsername());
               e2.setValidpersonname(user.getRealname());
               e2.setMasterid(masterid);
               e2.setRejectReason(delreason);
               this.dp.remove(e2);
               CtgBk005Syjc gwzsbk2 = this.dp.get(masterid);
               gwzsbk2.getIsemptycard().intValue();
               r.setResult("success");
               r.setMsg("删卡操作成功！");
            } else if("zlbk".equals(bktype)) {
               CtgBk006Tumour e3 = new CtgBk006Tumour();
               e3.setValiddt(new Date());
               e3.setValidpersonid(user.getUsername());
               e3.setValidpersonname(user.getRealname());
               e3.setMasterid(masterid);
               e3.setDelreason(delreason);
               this.dq.remove(e3);
               CtgBk006Tumour gwzsbk3 = this.dq.get(masterid);
               gwzsbk3.getIsemptycard().intValue();
               r.setResult("success");
               r.setMsg("删卡操作成功！");
            } else if("syycbk".equals(bktype)) {
               CtgBk004Syycbk e4 = new CtgBk004Syycbk();
               e4.setValiddt(new Date());
               e4.setValidpersonid(user.getUsername());
               e4.setValidpersonname(user.getRealname());
               e4.setMasterid(masterid);
               e4.setRejectReason(delreason);
               this.dr.remove(e4);
               CtgBk004Syycbk gwzsbk4 = this.dr.get(masterid);
               gwzsbk4.getIsemptycard().intValue();
               r.setResult("success");
               r.setMsg("删卡操作成功！");
            } else if("xnxgbk".equals(bktype)) {
               CtgBk008Ccvd e5 = new CtgBk008Ccvd();
               e5.setValiddt(new Date());
               e5.setValidpersonid(user.getUsername());
               e5.setValidpersonname(user.getRealname());
               e5.setMasterid(masterid);
               e5.setDelreason(delreason);
               this.du.remove(e5);
               CtgBk008Ccvd gwzsbk5 = this.du.get(masterid);
               if(gwzsbk5.getIsemptycard() != null) {
                  gwzsbk5.getIsemptycard().longValue();
               }

               r.setResult("success");
               r.setMsg("删卡操作成功！");
            } else if("gwzsbk".equals(bktype)) {
               CtgBk009Sunstroke e6 = new CtgBk009Sunstroke();
               e6.setValiddt(new Date());
               e6.setValidpersonid(user.getUsername());
               e6.setValidpersonname(user.getRealname());
               e6.setMasterid(masterid);
               e6.setDelreason(delreason);
               this.dv.remove(e6);
               CtgBk009Sunstroke gwzsbk6 = this.dv.get(masterid);
               if(gwzsbk6 != null) {
                  gwzsbk6.getIsemptycard();
               }

               r.setResult("success");
               r.setMsg("删卡操作成功！");
            } else if("nyzdbk".equals(bktype)) {
               CtgBk010Pesticide e7 = new CtgBk010Pesticide();
               e7.setValiddt(new Date());
               e7.setValidpersonid(user.getUsername());
               e7.setValidpersonname(user.getRealname());
               e7.setMasterid(masterid);
               e7.setRejectReason(delreason);
               this.dy.remove(e7);
               r.setResult("success");
               r.setMsg("删卡操作成功！");
            }
         } catch (Exception arg14) {
            arg14.printStackTrace();
            c.error("删卡操作出错！", arg14);
            r.setResult("error");
            r.setMsg("出错了！操作失败！错误信息：" + arg14.getMessage());
         }
      } else {
         r.setResult("error");
         r.setMsg("必要字段获取为空！删卡操作失败！");
      }

      this.a(response, r);
   }

	@RequestMapping({"/cdc/f_json/writeNote"})
   @ResponseBody
   @SqlLog(
      p = "传染病上报--添加上报卡备注"
   )
   public void h(HttpServletRequest request, HttpServletResponse response, String bktype, String subid, String note) {
      Result r = new Result();
      if(ab.aM(subid)) {
         r.setResult("error");
         r.setMsg("必要字段获取为空！添加备注失败！");
      } else {
         try {
            if("crbbk".equals(bktype)) {
               this.dk.updateDiseaseNotes(subid, note);
            } else if("sybk".equals(bktype)) {
               this.doo.updateNotes(subid, note);
            } else if("syjcbk".equals(bktype)) {
               this.dp.updateNotes(subid, note);
            } else if("syycbk".equals(bktype)) {
               this.dr.updateNotes(subid, note);
            } else if("zlbk".equals(bktype)) {
               this.dq.updateNotes(subid, note);
            } else if("xnxgbk".equals(bktype)) {
               this.du.updateNotes(subid, note);
            } else if("gwzsbk".equals(bktype)) {
               this.dv.updateNotes(subid, note);
            }

            r.setResult("success");
            r.setMsg("添加备注成功！");
         } catch (Exception arg7) {
            arg7.printStackTrace();
            c.error("添加备注操作出错！", arg7);
            r.setResult("error");
            r.setMsg("出错了！操作失败！错误信息：" + arg7.getMessage());
         }
      }

      this.a(response, r);
   }

	@RequestMapping({"/cdc/f_json/batchAudit"})
   @ResponseBody
   @SqlLog(
      p = "传染病上报--批量审核上报卡"
   )
   public void n(HttpServletRequest request, HttpServletResponse response, String bktype, String masterids) {
      Result r = new Result();
      AcAccount user = (AcAccount)this.b(request);
      if(ab.aM(masterids)) {
         r.setResult("error");
         r.setMsg("必要参数获取失败！无法进行批量审核操作！");
      } else {
         try {
            if("crbbk".equals(bktype)) {
               CtgBk001Crbdisease e = new CtgBk001Crbdisease();
               e.setAuditdate(new Date());
               e.setAuditor(user.getUsername());
               e.setAuditorname(user.getRealname());
               e.setMasterid(masterids);
               this.dk.batchAudit(e);
            } else if("sybk".equals(bktype)) {
               CtgBk002Sybk e1 = new CtgBk002Sybk();
               e1.setAuditdate(new Date());
               e1.setAuditor(user.getUsername());
               e1.setAuditorname(user.getRealname());
               e1.setMasterid(masterids);
               this.doo.batchAudit(e1);
            } else if("syjcbk".equals(bktype)) {
               CtgBk005Syjc e2 = new CtgBk005Syjc();
               e2.setValidDate(new Date());
               e2.setValidperson(user.getUsername());
               e2.setValidpersonname(user.getRealname());
               e2.setMasterid(masterids);
               this.dp.batchAudit(e2);
            } else if("zlbk".equals(bktype)) {
               CtgBk006Tumour e3 = new CtgBk006Tumour();
               e3.setValiddt(new Date());
               e3.setValidpersonid(user.getUsername());
               e3.setValidpersonname(user.getRealname());
               e3.setMasterid(masterids);
               this.dq.batchAudit(e3);
            } else if("syycbk".equals(bktype)) {
               CtgBk004Syycbk e4 = new CtgBk004Syycbk();
               e4.setValiddt(new Date());
               e4.setValidpersonid(user.getUsername());
               e4.setValidpersonname(user.getRealname());
               e4.setMasterid(masterids);
               this.dr.batchAudit(e4);
            } else if("xnxgbk".equals(bktype)) {
               CtgBk008Ccvd e5 = new CtgBk008Ccvd();
               e5.setValiddt(new Date());
               e5.setValidpersonid(user.getUsername());
               e5.setValidpersonname(user.getRealname());
               e5.setMasterid(masterids);
               this.du.batchAudit(e5);
            } else if("gwzsbk".equals(bktype)) {
               CtgBk009Sunstroke e6 = new CtgBk009Sunstroke();
               e6.setValiddt(new Date());
               e6.setValidpersonid(user.getUsername());
               e6.setValidpersonname(user.getRealname());
               e6.setMasterid(masterids);
               this.dv.batchAudit(e6);
            } else if("nyzdbk".equals(bktype)) {
               CtgBk010Pesticide e7 = new CtgBk010Pesticide();
               e7.setValiddt(new Date());
               e7.setValidpersonid(user.getUsername());
               e7.setValidpersonname(user.getRealname());
               e7.setMasterid(masterids);
               this.dy.batchAudit(e7);
            }

            r.setResult("success");
            r.setMsg("审核成功！");
         } catch (Exception arg7) {
            arg7.printStackTrace();
            c.error("审核操作出错！", arg7);
            r.setResult("error");
            r.setMsg("出错了！操作失败！错误信息：" + arg7.getMessage());
         }
      }

      this.a(response, r);
   }

	@RequestMapping({"/cdc/f_view/reportHistory"})
	@SqlLog(p = "传染病上报--上报卡历史记录")
	public String h(HttpServletRequest request, ModelMap modelMap) {
		modelMap.put("queryStartDate", this.sdf.format(Long.valueOf((new Date()).getTime() - 604800000L)));
		modelMap.put("queryEndDate", this.sdf.format(Long.valueOf((new Date()).getTime())));
		String reportUrl = this.j.findByParamCode(Param.NIS_REPORT_URL);
		modelMap.put("reportUrl", reportUrl);
		AcAccount account = (AcAccount) this.b(request);
		if (!"hospital".equals(account.getAcType())) {
			modelMap.put("acType", "doctor");
		} else {
			modelMap.put("acType", "hospital");
		}

		List allowBKs = this.p.u("cdc_card_type", (String) null);
		modelMap.put("allowCards", allowBKs);
		String needFKDiseasis = this.bL.getNeedFKDiseasis();
		modelMap.put("sexcards", needFKDiseasis);
		String isShowFjhZzd = this.j.findByParamCode(Param.NIS_CRB_FJHZZD);
		if ("0".equals(isShowFjhZzd)) {
			String fjhDiseasisList = this.bL.getNeedFKDiseasisByParentId("2600");
			modelMap.put("fjhDiseasis", fjhDiseasisList);
		}

		modelMap.put("isShowFjhZzd", isShowFjhZzd);
		return "reportHistory";
	}

	@RequestMapping({"/cdc/f_view/reportHistoryQuery"})
	@ResponseBody
	@SqlLog(p = "传染病上报--上报卡历史记录")
	public void b(HttpServletRequest request, HttpServletResponse response, CtgBk001Crbmaster ctgBk001Crbmaster) {
		AcAccount account = (AcAccount) this.b(request);
		ctgBk001Crbmaster.setUserid(account.getUserId());
		if (ctgBk001Crbmaster.getSearchString() != null && !"".equals(ctgBk001Crbmaster.getSearchString())) {
			ctgBk001Crbmaster.setSearchString(ab.aR(ctgBk001Crbmaster.getSearchString()));
		}

		MyPage myPage = this.dj.c(ctgBk001Crbmaster);
		this.a(response, myPage);
	}

	@RequestMapping({"/cdc/f_json/getPatientFromHIS"})
	@ResponseBody
	@SqlLog(p = "传染病上报--获取患者信息")
	public void i(HttpServletRequest request, HttpServletResponse response, String searchKey, String startTime,
			String endTime) {
		Result result = new Result();
		if (!ab.aM(searchKey)) {
			result = this.dj.c(searchKey, startTime, endTime);
		} else {
			result.setResult("error");
			result.setExtraValue("病人参数获取为空，无法进行提取！");
		}

		this.a(response, result);
	}

	@RequestMapping({"/cdc/f_json/getZYPatientFromHIS"})
	@ResponseBody
	@SqlLog(p = "传染病上报--获取患者信息")
	public void j(HttpServletRequest request, HttpServletResponse response, String searchKey, String startTime,
			String endTime) {
		Result result = new Result();
		if (!ab.aM(searchKey)) {
			result = this.dj.d(searchKey, startTime, endTime);
		} else {
			result.setResult("error");
			result.setExtraValue("病人参数获取为空，无法进行提取！");
		}

		this.a(response, result);
	}

	@RequestMapping({"/cdc/tips"})
	public String h(HttpServletRequest request, ModelMap modelMap, String type, String msg) {
		modelMap.put("tips", msg);
		return "tips";
	}

	@RequestMapping({"/cdc/f_json/report/file/get"})
	@ResponseBody
	public void l(HttpServletRequest request, HttpServletResponse response, String bktype) {
		Result result = new Result();
		SysDict sysDict = this.p.j("cdc_card_type", bktype, (String) null);
		if (sysDict != null && !ab.isEmpty(sysDict.getExtParam2())) {
			result.setData(sysDict.getExtParam2());
		} else if ("crbbk".equals(bktype)) {
			result.setData("cdc/crb.cpt");
		} else if ("sybk".equals(bktype)) {
			result.setData("cdc/DeathCard.cpt");
		} else if ("syjcbk".equals(bktype)) {
			result.setData("cdc/SYJCCard.cpt");
		} else if ("zlbk".equals(bktype)) {
			result.setData("cdc/cdc.print.tumour.cpt");
		} else if ("syycbk".equals(bktype)) {
			result.setData("cdc/cdc.print.SYYC.cpt");
		} else if ("xnxgbk".equals(bktype)) {
			result.setData("cdc/cdc.print.CCVD.cpt");
		} else if (!"gwzsbk".equals(bktype)) {
			"nyzdbk".equals(bktype);
		}

		this.a(response, result);
	}

	@RequestMapping({"/cdc/f_json/updataPrintFlag"})
   @ResponseBody
   @SqlLog(
      p = "传染病上报--更新报卡打印状态"
   )
   public void o(HttpServletRequest request, HttpServletResponse response, String bktype, String msid) {
      Result r = new Result();
      boolean issucc = true;
      if(ab.isNotEmpty(bktype) && ab.isNotEmpty(msid)) {
         String[] msids = msid.split("\\|");

         for(int i = 0; i < msids.length; ++i) {
            if("crbbk".equals(bktype)) {
               this.dj.updatePrintFlag(msids[i]);
            } else if("sybk".equals(bktype)) {
               this.doo.updatePrintFlag(msids[i]);
            } else if("syjcbk".equals(bktype)) {
               this.dp.updatePrintFlag(msids[i]);
            } else if("zlbk".equals(bktype)) {
               this.dq.updatePrintFlag(msids[i]);
            } else if("syycbk".equals(bktype)) {
               this.dr.updatePrintFlag(msids[i]);
            } else if("xnxgbk".equals(bktype)) {
               this.du.updatePrintFlag(msids[i]);
            } else if("gwzsbk".equals(bktype)) {
               this.dv.updatePrintFlag(msids[i]);
            } else {
               if(!"nyzdbk".equals(bktype)) {
                  issucc = false;
                  break;
               }

               this.dy.updatePrintFlag(msids[i]);
            }
         }

         if(issucc) {
            r.setResult("success");
            r.setMsg("更新打印标识成功！");
         } else {
            r.setResult("error");
            r.setMsg("未知报卡类型！");
         }
      } else {
         r.setResult("error");
         r.setMsg("必要参数为空！");
      }

      this.a(response, r);
   }

	@RequestMapping({"/cdc/f_json/export/file/get"})
	@ResponseBody
	public void m(HttpServletRequest request, HttpServletResponse response, String bktype) {
		Result result = new Result();
		SysDict sysDict = this.p.j("cdc_card_type", bktype, (String) null);
		if (sysDict != null && !ab.isEmpty(sysDict.getExtParam1())) {
			result.setData(sysDict.getExtParam1());
		} else if ("crbbk".equals(bktype)) {
			result.setData("cdc/cdc.export.crb.cpt");
		} else if ("sybk".equals(bktype)) {
			result.setData("cdc/cdc.export.sybk.cpt");
		} else if ("syjcbk".equals(bktype)) {
			result.setData("cdc/cdc.export.syjc.cpt");
		} else if ("zlbk".equals(bktype)) {
			result.setData("cdc/cdc.export.tumour.cpt");
		} else if ("syycbk".equals(bktype)) {
			result.setData("cdc/cdc.export.SYYC.cpt");
		} else if ("xnxgbk".equals(bktype)) {
			result.setData("cdc/cdc.export.CCVD.cpt");
		} else if ("gwzsbk".equals(bktype)) {
			result.setData("cdc/cdc.export.sunstroke.cpt");
		}

		this.a(response, result);
	}

	@RequestMapping({"/cdc/f_json/export/file/get2"})
	@ResponseBody
	public void c(HttpServletRequest request, HttpServletResponse response, CtgBk001Crbmaster ctgBk001Crbmaster) {
		this.dj.a(response, ctgBk001Crbmaster);
	}

	@RequestMapping({"/cdc/f_json/classesDataForChart"})
	@ResponseBody
	public void o(HttpServletRequest request, HttpServletResponse response) {
		Result result = null;

		try {
			List e = this.dj.classesDataForChart(this.sdf.format(new Date()));
			ArrayList classesData = new ArrayList();
			List cdcCardList = this.p.u("cdc_card_type", (String) null);
			Iterator arg7 = e.iterator();

			while (true) {
				while (arg7.hasNext()) {
					Map classesMap = (Map) arg7.next();
					Iterator arg9 = cdcCardList.iterator();

					while (arg9.hasNext()) {
						SysDict sysDict = (SysDict) arg9.next();
						if (sysDict.getDictName().equals(classesMap.get("name"))) {
							classesData.add(classesMap);
							break;
						}
					}
				}

				result = new Result("success");
				result.setData(classesData);
				break;
			}
		} catch (Exception arg10) {
			arg10.printStackTrace();
			c.error("图表数据获取出错！", arg10);
			result = new Result("error", arg10.getMessage());
		}

		this.b(response, result);
	}

	@RequestMapping({"/cdc/f_json/diseaseTypeDataForChart"})
	@ResponseBody
	public void p(HttpServletRequest request, HttpServletResponse response) {
		Result result = null;

		try {
			List e = this.dj.diseaseTypeDataForChart(this.sdf.format(new Date()));
			result = new Result("success");
			result.setData(e);
		} catch (Exception arg4) {
			arg4.printStackTrace();
			c.error("图表数据获取出错！", arg4);
			result = new Result("error", arg4.getMessage());
		}

		this.b(response, result);
	}

	@RequestMapping({"/cdc/f_json/areaDataForChart"})
	@ResponseBody
	public void q(HttpServletRequest request, HttpServletResponse response) {
		Result result = null;

		try {
			List e = this.dj.areaDataForChart(this.sdf.format(new Date()));
			result = new Result("success");
			result.setData(e);
		} catch (Exception arg4) {
			arg4.printStackTrace();
			c.error("图表数据获取出错！", arg4);
			result = new Result("error", arg4.getMessage());
		}

		this.b(response, result);
	}

	@RequestMapping({"/cdc/f_json/reportDataForChart"})
	@ResponseBody
	public void r(HttpServletRequest request, HttpServletResponse response) {
		Result result = null;

		try {
			LinkedList e = new LinkedList();
			LinkedList xAxisData = new LinkedList();
			LinkedList legendData = new LinkedList();
			LinkedList series = new LinkedList();
			List classesData = this.dj.reportDataForChart(this.sdf.format(new Date()));
			Iterator arg9 = classesData.iterator();

			while (arg9.hasNext()) {
				Map resultMap = (Map) arg9.next();
				xAxisData.add(f.formatDate((Date) resultMap.get("name")));
				e.add(Integer.valueOf(r.d(resultMap.get("value"))));
			}

			series.add(e);
			legendData.add("法定传染病");
			HashMap resultMap1 = new HashMap();
			resultMap1.put("legendData", legendData);
			resultMap1.put("xAxisData", xAxisData);
			resultMap1.put("series", series);
			result = new Result("success");
			result.setData(resultMap1);
		} catch (Exception arg10) {
			arg10.printStackTrace();
			c.error("图表数据获取出错！", arg10);
			result = new Result("error", arg10.getMessage());
		}

		this.b(response, result);
	}

	@RequestMapping({"/cdc/f_json/yearDataForChart"})
	@ResponseBody
	public void s(HttpServletRequest request, HttpServletResponse response) {
		Result result = null;

		try {
			Calendar e = Calendar.getInstance();
			String startMonth = e.get(1) + "-01";
			String endMonth = e.get(1) + "-12";
			LinkedList count = new LinkedList();
			LinkedList xAxisData = new LinkedList();
			LinkedList legendData = new LinkedList();
			LinkedList series = new LinkedList();
			List classesData = this.dj.yearDataForChart(startMonth, endMonth);
			Iterator arg12 = classesData.iterator();

			while (arg12.hasNext()) {
				Map resultMap = (Map) arg12.next();
				xAxisData.add(Integer.parseInt((String) resultMap.get("name")) + "月");
				count.add(Integer.valueOf(r.d(resultMap.get("value"))));
			}

			series.add(count);
			HashMap resultMap1 = new HashMap();
			resultMap1.put("legendData", legendData);
			resultMap1.put("xAxisData", xAxisData);
			resultMap1.put("series", series);
			result = new Result("success");
			result.setData(resultMap1);
		} catch (Exception arg13) {
			arg13.printStackTrace();
			c.error("图表数据获取出错！", arg13);
			result = new Result("error", arg13.getMessage());
		}

		this.b(response, result);
	}

	@RequestMapping({"/cdc/f_view/toChooseCard"})
	@SqlLog(p = "传染病上报--传染病报卡类型列表")
	public String c(HttpServletRequest request, ModelMap modelMap, String zyid, String mzid, String isShowCards) {
		List allowBKs = this.p.u("cdc_card_type", (String) null);
		modelMap.put("AllBK", allowBKs);
		modelMap.put("curZyid", zyid);
		modelMap.put("curMzid", mzid);
		modelMap.put("isShowCards", isShowCards);
		return "cards/includepages/bkChoose";
	}
}