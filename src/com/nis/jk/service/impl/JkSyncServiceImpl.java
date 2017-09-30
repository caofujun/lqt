package com.nis.jk.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.Param;
import com.nis.comm.utils.EncryptUtils;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.m;
import com.nis.comm.utils.r;
import com.nis.comm.utils.t;
import com.nis.jk.dao.JkDicDiseaseDao;
import com.nis.jk.dao.JkDicDoctorDao;
import com.nis.jk.dao.JkDicDrugDao;
import com.nis.jk.dao.JkDicOfficeDao;
import com.nis.jk.dao.JkMessageDao;
import com.nis.jk.dao.JkPatientBcDao;
import com.nis.jk.dao.JkPatientClinicDao;
import com.nis.jk.dao.JkPatientDiagnoseDao;
import com.nis.jk.dao.JkPatientLabAntiDao;
import com.nis.jk.dao.JkPatientLabItemsDao;
import com.nis.jk.dao.JkPatientLabPathoDao;
import com.nis.jk.dao.JkPatientOperationDao;
import com.nis.jk.dao.JkPatientRoutineDao;
import com.nis.jk.dao.JkPatientTempratureDao;
import com.nis.jk.dao.JkPatientVisitDao;
import com.nis.jk.dao.JkPatientYxDao;
import com.nis.jk.dao.JkPatientYzDao;
import com.nis.jk.dao.JkPatientZkmxDao;
import com.nis.jk.entity.JkDicDisease;
import com.nis.jk.entity.JkDicDoctor;
import com.nis.jk.entity.JkDicDrug;
import com.nis.jk.entity.JkDicOffice;
import com.nis.jk.entity.JkPatientBc;
import com.nis.jk.entity.JkPatientClinic;
import com.nis.jk.entity.JkPatientDiagnose;
import com.nis.jk.entity.JkPatientLabAnti;
import com.nis.jk.entity.JkPatientLabItems;
import com.nis.jk.entity.JkPatientLabPatho;
import com.nis.jk.entity.JkPatientOperation;
import com.nis.jk.entity.JkPatientRoutine;
import com.nis.jk.entity.JkPatientTemprature;
import com.nis.jk.entity.JkPatientVisit;
import com.nis.jk.entity.JkPatientYx;
import com.nis.jk.entity.JkPatientYz;
import com.nis.jk.entity.JkPatientZkmx;
import com.nis.jk.entity.JkSyncLog;
import com.nis.jk.service.JkMessageService;
import com.nis.jk.service.JkSyncService;
import com.nis.jk.service.impl.JkSyncServiceImpl.1;
import com.nis.jk.service.impl.JkSyncServiceImpl.10;
import com.nis.jk.service.impl.JkSyncServiceImpl.11;
import com.nis.jk.service.impl.JkSyncServiceImpl.12;
import com.nis.jk.service.impl.JkSyncServiceImpl.13;
import com.nis.jk.service.impl.JkSyncServiceImpl.14;
import com.nis.jk.service.impl.JkSyncServiceImpl.15;
import com.nis.jk.service.impl.JkSyncServiceImpl.16;
import com.nis.jk.service.impl.JkSyncServiceImpl.2;
import com.nis.jk.service.impl.JkSyncServiceImpl.3;
import com.nis.jk.service.impl.JkSyncServiceImpl.4;
import com.nis.jk.service.impl.JkSyncServiceImpl.5;
import com.nis.jk.service.impl.JkSyncServiceImpl.6;
import com.nis.jk.service.impl.JkSyncServiceImpl.7;
import com.nis.jk.service.impl.JkSyncServiceImpl.8;
import com.nis.jk.service.impl.JkSyncServiceImpl.9;
import com.nis.param.service.SysParamService;
import com.nis.patient.controller.PatientTaskController;
import com.nis.patient.dao.St001JbxxbDao;
import com.nis.patient.dao.St002ZdxxbDao;
import com.nis.patient.dao.St003CryxxbDao;
import com.nis.patient.dao.St004YzxxbDao;
import com.nis.patient.dao.St005SsxxbDao;
import com.nis.patient.dao.St006TwxxDao;
import com.nis.patient.dao.St008BcjlDao;
import com.nis.patient.dao.St009SjbbDao;
import com.nis.patient.dao.St010JcbytDao;
import com.nis.patient.dao.St011SyjgbDao;
import com.nis.patient.dao.St012KjywDao;
import com.nis.patient.dao.St012ZkjlDao;
import com.nis.patient.dao.St014PacsDao;
import com.nis.patient.dao.St020ClinicPatientsDao;
import com.nis.patient.dao.St021PatientRoutineDao;
import com.nis.patient.entity.St001Jbxxb;
import com.nis.patient.entity.St002Zdxxb;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.entity.St004Yzxxb;
import com.nis.patient.entity.St005Ssxxb;
import com.nis.patient.entity.St006Twxx;
import com.nis.patient.entity.St008Bcjl;
import com.nis.patient.entity.St009Sjbb;
import com.nis.patient.entity.St010Jcbyt;
import com.nis.patient.entity.St011Syjgb;
import com.nis.patient.entity.St012Kjyw;
import com.nis.patient.entity.St012Zkjl;
import com.nis.patient.entity.St014Pacs;
import com.nis.patient.entity.St020ClinicPatients;
import com.nis.patient.entity.St021PatientRoutine;
import com.nis.patient.service.St005SsxxbService;
import com.nis.patient.service.St012KjywService;
import com.nis.zg.dao.Zg002ByksDao;
import com.nis.zg.dao.Zg003YyzgDao;
import com.nis.zg.dao.Zg004YyxxDao;
import com.nis.zg.dao.Zg012Icd10Dao;
import com.nis.zg.entity.Zg002Byks;
import com.nis.zg.entity.Zg003Yyzg;
import com.nis.zg.entity.Zg004Yyxx;
import com.nis.zg.entity.Zg012Icd10;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JkSyncServiceImpl implements JkSyncService {
	private static final Logger logger = Logger.getLogger(PatientTaskController.class);
	@Autowired
	private JkDicDiseaseDao sU;
	@Autowired
	private JkDicDrugDao sV;
	@Autowired
	private JkDicOfficeDao sW;
	@Autowired
	private JkDicDoctorDao sX;
	@Autowired
	private JkPatientClinicDao sY;
	@Autowired
	private JkPatientVisitDao sZ;
	@Autowired
	private JkPatientDiagnoseDao ta;
	@Autowired
	private JkPatientZkmxDao tb;
	@Autowired
	private JkPatientYzDao tc;
	@Autowired
	private JkPatientBcDao td;
	@Autowired
	private JkPatientTempratureDao te;
	@Autowired
	private JkPatientRoutineDao tf;
	@Autowired
	private JkPatientYxDao tg;
	@Autowired
	private JkPatientOperationDao th;
	@Autowired
	private JkPatientLabItemsDao ti;
	@Autowired
	private JkPatientLabPathoDao tj;
	@Autowired
	private JkPatientLabAntiDao tk;
	@Autowired
	private Zg012Icd10Dao tl;
	@Autowired
	private Zg002ByksDao tm;
	@Autowired
	private Zg003YyzgDao tn;
	@Autowired
	private Zg004YyxxDao to;
	@Autowired
	private St020ClinicPatientsDao tp;
	@Autowired
	private St001JbxxbDao tq;
	@Autowired
	private St003CryxxbDao tr;
	@Autowired
	private St002ZdxxbDao ts;
	@Autowired
	private St004YzxxbDao tt;
	@Autowired
	private St012ZkjlDao tu;
	@Autowired
	private St005SsxxbDao tv;
	@Autowired
	private St005SsxxbService bO;
	@Autowired
	private St006TwxxDao tx;
	@Autowired
	private St021PatientRoutineDao ty;
	@Autowired
	private St008BcjlDao tz;
	@Autowired
	private St009SjbbDao tA;
	@Autowired
	private St010JcbytDao tB;
	@Autowired
	private St011SyjgbDao tC;
	@Autowired
	private St012KjywDao tD;
	@Autowired
	private St012KjywService bM;
	@Autowired
	private St014PacsDao tE;
	@Autowired
	private JkMessageService sQ;
	@Autowired
	private JkMessageDao sT;
	@Autowired
	private SysParamService j;
	private String hospId = null;
	private Date now = null;
	private String result = "1";
	private Long tF = new Long(0L);
	private static Map<String, JkSyncLog> tG = new HashMap();

	public List<JkSyncLog> by(String tables) {
		this.tF = new Long(this.j.findByParamCode(Param.NIS_JK_SYNC_DAY));
		this.bB(tables);
		return new ArrayList(tG.values());
	}

	public List<JkSyncLog> bz(String tables) {
		JkSyncLog jkSyncLog = null;
		Iterator arg3 = tG.keySet().iterator();

		while (arg3.hasNext()) {
			String table = (String) arg3.next();
			jkSyncLog = (JkSyncLog) tG.get(table);
			jkSyncLog.setCompleted(Integer.valueOf(jkSyncLog.getTotal().intValue() - this.bC(table).intValue()));
			tG.put(table, jkSyncLog);
		}

		return new ArrayList(tG.values());
	}

	private void bB(String tables) {
		tG = new HashMap();
		if (ab.isEmpty(tables)) {
			EnumSet tabless = EnumSet.allOf(com.nis.comm.enums.ab.class);
			Iterator arg3 = tabless.iterator();

			while (arg3.hasNext()) {
				com.nis.comm.enums.ab table = (com.nis.comm.enums.ab) arg3.next();
				tG.put(table.getCode(), new JkSyncLog(table, this.bC(table.getCode())));
			}
		} else {
			String[] arg6 = tables.split(",");
			String[] arg5 = arg6;
			int arg4 = arg6.length;

			for (int arg8 = 0; arg8 < arg4; ++arg8) {
				String arg7 = arg5[arg8];
				tG.put(arg7, new JkSyncLog(com.nis.comm.enums.ab.C(arg7), this.bC(arg7)));
			}
		}

	}

	private Integer bC(String table) {
		return Integer
				.valueOf(this.sT.getSyncTotalByTable(table, new Long(1L), f.a(f.getCurDate(), -this.tF.intValue())));
	}

	public String bx(String tables) {
      this.now = f.getCurDate();
      this.tF = new Long(this.j.findByParamCode(Param.NIS_JK_SYNC_DAY));
      this.result = "1";
      List zg004YyxxList = this.to.getAll();
      if(zg004YyxxList != null && !zg004YyxxList.isEmpty()) {
         this.hospId = ((Zg004Yyxx)zg004YyxxList.get(0)).getHospId();
      }

      if(StringUtils.isBlank(tables) || tables.toUpperCase().contains("JK_DIC_OFFICE")) {
         this.K();
      }

      if(StringUtils.isBlank(tables) || tables.toUpperCase().contains("JK_DIC_DOCTOR")) {
         this.L();
      }

      if(StringUtils.isBlank(tables) || tables.toUpperCase().contains("JK_DIC_DISEASE")) {
         this.M();
      }

      if(StringUtils.isBlank(tables) || tables.toUpperCase().contains("JK_DIC_DRUG")) {
         this.N();
      }

      if(StringUtils.isBlank(tables) || tables.toUpperCase().contains("JK_PATIENT_CLINIC")) {
         (new Thread(new 1(this))).start();
      }

      if(StringUtils.isBlank(tables) || tables.toUpperCase().contains("JK_PATIENT_VISIT")) {
         this.P();
      }

      if(StringUtils.isBlank(tables) || tables.toUpperCase().contains("JK_PATIENT_ZKMX")) {
         this.Q();
      }

      if(StringUtils.isBlank(tables) || tables.toUpperCase().contains("JK_PATIENT_DIAGNOSE")) {
         (new Thread(new 2(this))).start();
      }

      if(StringUtils.isBlank(tables) || tables.toUpperCase().contains("JK_PATIENT_TEMPRATURE")) {
         (new Thread(new 3(this))).start();
      }

      if(StringUtils.isBlank(tables) || tables.toUpperCase().contains("JK_PATIENT_ROUTINE")) {
         (new Thread(new 4(this))).start();
      }

      if(StringUtils.isBlank(tables) || tables.toUpperCase().contains("JK_PATIENT_YX")) {
         (new Thread(new 5(this))).start();
      }

      if(StringUtils.isBlank(tables) || tables.toUpperCase().contains("JK_PATIENT_OPERATION")) {
         (new Thread(new 6(this))).start();
      }

      if(StringUtils.isBlank(tables) || tables.toUpperCase().contains("JK_PATIENT_BC")) {
         (new Thread(new 7(this))).start();
      }

      if(StringUtils.isBlank(tables) || tables.toUpperCase().contains("JK_PATIENT_YZ")) {
         (new Thread(new 8(this))).start();
      }

      if(StringUtils.isBlank(tables) || tables.toUpperCase().contains("JK_PATIENT_LAB_ITEMS")) {
         (new Thread(new 9(this))).start();
      }

      if(StringUtils.isBlank(tables) || tables.toUpperCase().contains("JK_PATIENT_LAB_PATHO")) {
         (new Thread(new 10(this))).start();
      }

      if(StringUtils.isBlank(tables) || tables.toUpperCase().contains("JK_PATIENT_LAB_ANTI")) {
         (new Thread(new 11(this))).start();
      }

      return this.result;
   }

	private void K() {
		logger.info("开始科室信息同步到业务库。。。。。。。。。。。。。。。。。");
		Zg002Byks zg002Byks = null;
		boolean zg002Flag = false;
		JkDicOffice searchJkDicOffice = new JkDicOffice();
		searchJkDicOffice.setUpdFlag(new Long(1L));
		searchJkDicOffice.setUpdTime(f.a(f.getCurDate(), -this.tF.intValue()));
		List jkDicOfficeList = this.sW.findJkDicOffice(searchJkDicOffice);
		Iterator arg5 = jkDicOfficeList.iterator();

		while (arg5.hasNext()) {
			JkDicOffice jkDicOffice = (JkDicOffice) arg5.next();
			zg002Flag = false;

			try {
				if ("1".equals(this.a(com.nis.comm.enums.ab.hI))) {
					logger.info("暂停科室信息同步到业务库。。。。。。。。。。。。。。。。。");
					this.b(com.nis.comm.enums.ab.hI);
					break;
				}

				zg002Byks = this.tm.get(jkDicOffice.getId());
				if (zg002Byks == null) {
					zg002Flag = true;
					zg002Byks = new Zg002Byks();
					zg002Byks.setFlag(new Integer(1));
				}

				zg002Byks.setId(jkDicOffice.getId());
				zg002Byks.setDeptId(jkDicOffice.getDeptId());
				zg002Byks.setDeptName(jkDicOffice.getDeptName());
				zg002Byks.setDeptTypeid(jkDicOffice.getDepType());
				zg002Byks.setDeptTypename(jkDicOffice.getDepTypeName());
				zg002Byks.setHospId(this.hospId);
				zg002Byks.setSpCode(t.aE(jkDicOffice.getDepTypeName()));
				zg002Byks.setLastAt(f.getCurDate());
				if (zg002Flag) {
					this.tm.save(zg002Byks);
				} else {
					this.tm.update(zg002Byks);
				}

				jkDicOffice.setUpdFlag(new Long(0L));
				jkDicOffice.setUpdTime(this.now);
				this.sW.updateFlag(jkDicOffice);
			} catch (Exception arg7) {
				arg7.printStackTrace();
				this.result = f.g(this.now);
				this.sQ.b("医生信息同步到业务库异常：\n" + jkDicOffice.toString() + "\n" + arg7.getMessage(), this.now);
				logger.error("科室信息同步到业务库异常。。。。。。。。。。。。。。。。。");
				logger.error(arg7.getMessage());
			}
		}

		this.c(com.nis.comm.enums.ab.hI);
		logger.info("完成科室信息同步到业务库。。。。。。。。。。。。。。。。。");
	}

	private void L() {
		logger.info("开始医生信息同步到业务库。。。。。。。。。。。。。。。。。");
		Zg003Yyzg zg003Yyzg = null;
		boolean zg003Flag = false;
		JkDicDoctor searchJkDicDoctor = new JkDicDoctor();
		searchJkDicDoctor.setUpdFlag(new Long(1L));
		searchJkDicDoctor.setUpdTime(f.a(f.getCurDate(), -this.tF.intValue()));
		List jkDicDoctorList = this.sX.findJkDicDoctor(searchJkDicDoctor);
		Iterator arg5 = jkDicDoctorList.iterator();

		while (arg5.hasNext()) {
			JkDicDoctor jkDicDoctor = (JkDicDoctor) arg5.next();
			zg003Flag = false;

			try {
				if ("1".equals(this.a(com.nis.comm.enums.ab.hJ))) {
					logger.info("暂停医生信息同步到业务库。。。。。。。。。。。。。。。。。");
					this.b(com.nis.comm.enums.ab.hJ);
					break;
				}

				zg003Yyzg = this.tn.get(jkDicDoctor.getId());
				if (zg003Yyzg == null) {
					zg003Flag = true;
					zg003Yyzg = new Zg003Yyzg();
					zg003Yyzg.setFlag(new Integer(1));
					zg003Yyzg.setState("1");
				}

				zg003Yyzg.setId(jkDicDoctor.getId());
				zg003Yyzg.setHospId(this.hospId);
				zg003Yyzg.setEmployeeId(jkDicDoctor.getDrNo());
				zg003Yyzg.setEmployeeName(jkDicDoctor.getDrName());
				if (StringUtils.isNotBlank(jkDicDoctor.getDeptId())) {
					zg003Yyzg.setDeptId(jkDicDoctor.getDeptId());
				}

				zg003Yyzg.setSpCode(t.aE(jkDicDoctor.getDrName()));
				if (jkDicDoctor.getDocLine() != null) {
					zg003Yyzg.setDrLinetype("" + jkDicDoctor.getDocLine());
				}

				zg003Yyzg.setAuthCode(EncryptUtils.aj(this.j.findByParamCode(Param.NIS_DOC_DEFAULT_PWD)));
				zg003Yyzg.setCclass(jkDicDoctor.getDrType());
				zg003Yyzg.setRoleId("fedc0892ccfb4354b4210e655d8eab84");
				zg003Yyzg.setLastAt(f.getCurDate());
				if (zg003Flag) {
					this.tn.save(zg003Yyzg);
				} else {
					this.tn.update(zg003Yyzg);
				}

				jkDicDoctor.setUpdFlag(new Long(0L));
				jkDicDoctor.setUpdTime(this.now);
				this.sX.updateFlag(jkDicDoctor);
			} catch (Exception arg7) {
				arg7.printStackTrace();
				this.result = f.g(this.now);
				this.sQ.b("医生信息同步到业务库异常：\n" + jkDicDoctor.toString() + "\n" + arg7.getMessage(), this.now);
				logger.error("医生信息同步到业务库异常。。。。。。。。。。。。。。。。。");
				logger.error(arg7.getMessage());
			}
		}

		this.c(com.nis.comm.enums.ab.hJ);
		logger.info("完成医生信息同步到业务库。。。。。。。。。。。。。。。。。");
	}

	private void M() {
		logger.info("开始诊断字典信息同步到业务库。。。。。。。。。。。。。。。。。");
		Zg012Icd10 zg012Icd10 = null;
		boolean zg012Flag = false;
		JkDicDisease searchJkDicDisease = new JkDicDisease();
		searchJkDicDisease.setUpdFlag(new Long(1L));
		searchJkDicDisease.setUpdTime(f.a(f.getCurDate(), -this.tF.intValue()));
		List jkDicDiseaseList = this.sU.findJkDicDisease(searchJkDicDisease);
		Iterator arg5 = jkDicDiseaseList.iterator();

		while (arg5.hasNext()) {
			JkDicDisease jkDicDisease = (JkDicDisease) arg5.next();
			zg012Flag = false;

			try {
				if ("1".equals(this.a(com.nis.comm.enums.ab.hK))) {
					logger.info("暂停诊断字典信息同步到业务库。。。。。。。。。。。。。。。。。");
					this.b(com.nis.comm.enums.ab.hK);
					break;
				}

				zg012Icd10 = this.tl.get(jkDicDisease.getId());
				if (zg012Icd10 == null) {
					zg012Flag = true;
					zg012Icd10 = new Zg012Icd10();
					zg012Icd10.setFlag(new Long(1L));
				}

				zg012Icd10.setIcdId(jkDicDisease.getId());
				zg012Icd10.setIcdCode(jkDicDisease.getIcdCode());
				zg012Icd10.setIcdName(jkDicDisease.getDiseaseName());
				zg012Icd10.setSpCode(t.aE(jkDicDisease.getDiseaseName()));
				zg012Icd10.setLastAt(f.getCurDate());
				if (zg012Flag) {
					this.tl.save(zg012Icd10);
				} else {
					this.tl.update(zg012Icd10);
				}

				jkDicDisease.setUpdFlag(new Long(0L));
				jkDicDisease.setUpdTime(this.now);
				this.sU.updateFlag(zg012Icd10);
			} catch (Exception arg7) {
				arg7.printStackTrace();
				this.result = f.g(this.now);
				this.sQ.b("诊断字典信息同步到业务库异常：\n" + jkDicDisease.toString() + "\n" + arg7.getMessage(), this.now);
				logger.error("诊断字典信息同步到业务库异常。。。。。。。。。。。。。。。。。");
				logger.error(arg7.getMessage());
			}
		}

		this.c(com.nis.comm.enums.ab.hK);
		logger.info("完成诊断字典信息同步到业务库。。。。。。。。。。。。。。。。。");
	}

	private void N() {
		logger.info("开始抗菌药物信息同步到业务库。。。。。。。。。。。。。。。。。");
		St012Kjyw st012Kjyw = null;
		boolean zg010Flag = false;
		JkDicDrug searchJkDicDrug = new JkDicDrug();
		searchJkDicDrug.setUpdFlag(new Long(1L));
		searchJkDicDrug.setUpdTime(f.a(f.getCurDate(), -this.tF.intValue()));
		List jkDicDrugList = this.sV.findJkDicDrug(searchJkDicDrug);
		Iterator arg5 = jkDicDrugList.iterator();

		while (arg5.hasNext()) {
			JkDicDrug jkDicDrug = (JkDicDrug) arg5.next();
			zg010Flag = false;

			try {
				if ("1".equals(this.a(com.nis.comm.enums.ab.hL))) {
					logger.info("暂停抗菌药物信息同步到业务库。。。。。。。。。。。。。。。。。");
					this.b(com.nis.comm.enums.ab.hL);
					break;
				}

				st012Kjyw = this.tD.get(jkDicDrug.getId());
				if (st012Kjyw == null) {
					zg010Flag = true;
					st012Kjyw = new St012Kjyw();
				}

				st012Kjyw.setId(jkDicDrug.getId());
				st012Kjyw.setDrugId(jkDicDrug.getDrugCode());
				st012Kjyw.setSyDrugId(jkDicDrug.getDrugCode());
				st012Kjyw.setDrugName(jkDicDrug.getDrugName());
				if (st012Kjyw.getDrugLine() == null) {
					st012Kjyw.setDrugLine(jkDicDrug.getDrugLine());
				}

				st012Kjyw.setPycode(t.aE(jkDicDrug.getDrugName()));
				st012Kjyw.setLastAt(f.getCurDate());
				if (zg010Flag) {
					this.tD.save(st012Kjyw);
				} else {
					this.tD.update(st012Kjyw);
				}

				jkDicDrug.setUpdFlag(new Long(0L));
				jkDicDrug.setUpdTime(this.now);
				this.sV.updateFlag(jkDicDrug);
			} catch (Exception arg7) {
				arg7.printStackTrace();
				this.result = f.g(this.now);
				this.sQ.b("抗菌药物信息同步到业务库异常：\n" + jkDicDrug.toString() + "\n" + arg7.getMessage(), this.now);
				logger.error("抗菌药物信息同步到业务库异常。。。。。。。。。。。。。。。。。");
				logger.error(arg7.getMessage());
			}
		}

		this.c(com.nis.comm.enums.ab.hL);
		logger.info("完成抗菌药物信息同步到业务库。。。。。。。。。。。。。。。。。");
	}

	private void O() {
		logger.info("开始患者门诊就诊信息同步到业务库。。。。。。。。。。。。。。。。。");
		St020ClinicPatients st020ClinicPatients = null;
		Zg002Byks zg002Byks = null;
		Zg003Yyzg zg003Yyzg = null;
		boolean st020Flag = false;
		JkPatientClinic searchJkPatientClinic = new JkPatientClinic();
		searchJkPatientClinic.setUpdFlag(new Long(1L));
		searchJkPatientClinic.setUpdTime(f.a(f.getCurDate(), -this.tF.intValue()));
		List jkPatientVisitList = this.sY.findJkPatientClinic(searchJkPatientClinic);
		Iterator arg7 = jkPatientVisitList.iterator();

		while (arg7.hasNext()) {
			JkPatientClinic jkPatientClinic = (JkPatientClinic) arg7.next();
			st020Flag = false;

			try {
				if ("1".equals(this.a(com.nis.comm.enums.ab.hM))) {
					logger.info("暂停患者门诊就诊信息同步到业务库。。。。。。。。。。。。。。。。。");
					this.b(com.nis.comm.enums.ab.hM);
					break;
				}

				st020ClinicPatients = this.tp.get(jkPatientClinic.getId());
				if (st020ClinicPatients == null) {
					st020Flag = true;
					st020ClinicPatients = new St020ClinicPatients();
					st020ClinicPatients.setCreateAt(f.getCurDate());
				}

				st020ClinicPatients.setId(jkPatientClinic.getId());
				st020ClinicPatients.setMzid(jkPatientClinic.getMzid());
				st020ClinicPatients.setPatientId(jkPatientClinic.getPatientId());
				if (jkPatientClinic.getVisitId() == null) {
					st020ClinicPatients.setVisitId(new String("1"));
				} else {
					st020ClinicPatients.setVisitId("" + jkPatientClinic.getVisitId());
				}

				st020ClinicPatients.setPatientName(jkPatientClinic.getPatientName());
				st020ClinicPatients.setSex(jkPatientClinic.getSex());
				if ("1".equals(jkPatientClinic.getSex())) {
					st020ClinicPatients.setSex("男");
				}

				if ("2".equals(jkPatientClinic.getSex())) {
					st020ClinicPatients.setSex("女");
				}

				st020ClinicPatients.setIdnumber(jkPatientClinic.getIdnumber());
				st020ClinicPatients.setBirthday(jkPatientClinic.getBirthday());
				st020ClinicPatients.setAge(jkPatientClinic.getAge());
				st020ClinicPatients.setAgeUnit(jkPatientClinic.getAgeUnit());
				if (st020ClinicPatients.getAge() == null && StringUtils.isNotBlank(st020ClinicPatients.getIdnumber())) {
					st020ClinicPatients.setAge(Integer.valueOf(f.d((Date) null, st020ClinicPatients.getIdnumber())));
					st020ClinicPatients.setAgeUnit("岁");
				}

				if (StringUtils.isBlank(st020ClinicPatients.getSex())
						&& StringUtils.isNotBlank(st020ClinicPatients.getIdnumber())) {
					st020ClinicPatients.setSex(ab.bc(st020ClinicPatients.getIdnumber()));
				}

				st020ClinicPatients.setParentName(jkPatientClinic.getParentName());
				st020ClinicPatients.setTel(jkPatientClinic.getTel());
				st020ClinicPatients.setMarriage(jkPatientClinic.getMarriage());
				st020ClinicPatients.setNation(jkPatientClinic.getNation());
				st020ClinicPatients.setWorkUnit(jkPatientClinic.getWorkUnit());
				st020ClinicPatients.setEducation(jkPatientClinic.getEducation());
				st020ClinicPatients.setRegisteraddr(jkPatientClinic.getRegisteraddr());
				st020ClinicPatients.setPresentaddr(jkPatientClinic.getPresentaddr());
				st020ClinicPatients.setDiagnosisName(jkPatientClinic.getDiagnosisName());
				if (StringUtils.isBlank(jkPatientClinic.getDeptId())) {
					st020ClinicPatients.setDeptId(jkPatientClinic.getDeptId());
				}

				if (StringUtils.isNotBlank(jkPatientClinic.getDeptName())) {
					st020ClinicPatients.setDeptName(jkPatientClinic.getDeptName());
				} else if (StringUtils.isNotBlank(jkPatientClinic.getDeptId())) {
					zg002Byks = this.tm.get(jkPatientClinic.getDeptId());
					if (zg002Byks != null) {
						st020ClinicPatients.setDeptName(zg002Byks.getDeptName());
					}
				}

				if (StringUtils.isNotBlank(jkPatientClinic.getDoctorId())) {
					st020ClinicPatients.setDoctorId(jkPatientClinic.getDoctorId());
				}

				if (StringUtils.isNotBlank(jkPatientClinic.getDoctorName())) {
					st020ClinicPatients.setDoctorName(jkPatientClinic.getDoctorName());
				} else if (StringUtils.isNotBlank(jkPatientClinic.getDoctorId())) {
					zg003Yyzg = this.tn.get(jkPatientClinic.getDoctorId());
					if (zg003Yyzg != null) {
						st020ClinicPatients.setDoctorName(zg003Yyzg.getEmployeeName());
					}
				}

				st020ClinicPatients.setDiagnosisDt(jkPatientClinic.getVisitDt());
				st020ClinicPatients.setStartDt(jkPatientClinic.getStartDt());
				st020ClinicPatients.setDeathDt(jkPatientClinic.getDeathDt());
				st020ClinicPatients.setIsreturnvisit(jkPatientClinic.getIsreturnvisit());
				st020ClinicPatients.setHospId(this.hospId);
				st020ClinicPatients.setUpdDate(f.getCurDate());
				if (st020Flag) {
					this.tp.save(st020ClinicPatients);
				} else {
					this.tp.update(st020ClinicPatients);
				}

				jkPatientClinic.setUpdFlag(new Long(0L));
				jkPatientClinic.setUpdTime(this.now);
				this.sY.updateFlag(jkPatientClinic);
			} catch (Exception arg9) {
				arg9.printStackTrace();
				this.result = f.g(this.now);
				this.sQ.b("患者门诊就诊信息同步到业务库异常：\n" + jkPatientClinic.toString() + "\n" + arg9.getMessage(), this.now);
				logger.error("患者门诊就诊信息同步到业务库异常。。。。。。。。。。。。。。。。。");
				logger.error(arg9.getMessage());
			}
		}

		this.c(com.nis.comm.enums.ab.hM);
		logger.info("完成患者门诊就诊信息同步到业务库。。。。。。。。。。。。。。。。。");
	}

	private void P() {
		logger.info("开始患者住院就诊信息同步到业务库。。。。。。。。。。。。。。。。。");
		St003Cryxxb st003Cryxxb = null;
		Zg002Byks zg002Byks = null;
		Zg003Yyzg zg003Yyzg = null;
		boolean st003Flag = false;
		boolean st001Flag = false;
		JkPatientVisit searchJkPatientVisit = new JkPatientVisit();
		searchJkPatientVisit.setUpdFlag(new Long(1L));
		searchJkPatientVisit.setUpdTime(f.a(f.getCurDate(), -this.tF.intValue()));
		searchJkPatientVisit.setPage(Integer.valueOf(1));
		searchJkPatientVisit.setSize(Integer.valueOf(1000));
		int index = 0;

		int count;
		do {
			++index;
			count = this.sZ.findJkPatientVisitCount(searchJkPatientVisit);
			List jkPatientVisitList = this.sZ.findJkPatientVisit(searchJkPatientVisit);
			Iterator arg10 = jkPatientVisitList.iterator();

			while (arg10.hasNext()) {
				JkPatientVisit jkPatientVisit = (JkPatientVisit) arg10.next();
				st003Flag = false;
				st001Flag = false;

				try {
					if ("1".equals(this.a(com.nis.comm.enums.ab.hN))) {
						logger.info("暂停患者住院就诊信息同步到业务库。。。。。。。。。。。。。。。。。");
						this.b(com.nis.comm.enums.ab.hN);
						return;
					}

					st003Cryxxb = this.tr.get(jkPatientVisit.getZyid());
					if (st003Cryxxb == null) {
						st003Flag = true;
						st003Cryxxb = new St003Cryxxb();
					}

					st003Cryxxb.setZyid(jkPatientVisit.getZyid());
					st003Cryxxb.setId(jkPatientVisit.getZyid());
					st003Cryxxb.setPatientId(jkPatientVisit.getPatientId());
					if (jkPatientVisit.getVisitId() == null) {
						st003Cryxxb.setVisitId(new Integer(1));
					} else {
						st003Cryxxb.setVisitId(jkPatientVisit.getVisitId());
					}

					st003Cryxxb.setPatientName(jkPatientVisit.getPatientName());
					st003Cryxxb.setAge(jkPatientVisit.getAge());
					st003Cryxxb.setAgeUnit(jkPatientVisit.getAgeUnit());
					if (StringUtils.isBlank(st003Cryxxb.getAge())
							&& StringUtils.isNotBlank(jkPatientVisit.getIdCardId())) {
						st003Cryxxb.setAge("" + f.d((Date) null, jkPatientVisit.getIdCardId()));
						st003Cryxxb.setAgeUnit("岁");
					}

					st003Cryxxb.setSex(jkPatientVisit.getSex());
					if ("1".equals(jkPatientVisit.getSex())) {
						st003Cryxxb.setSex("男");
					}

					if ("2".equals(jkPatientVisit.getSex())) {
						st003Cryxxb.setSex("女");
					}

					if (StringUtils.isBlank(st003Cryxxb.getSex())
							&& StringUtils.isNotBlank(jkPatientVisit.getIdCardId())) {
						st003Cryxxb.setSex(ab.bc(jkPatientVisit.getIdCardId()));
					}

					st003Cryxxb.setInHospAt(jkPatientVisit.getInHospAt());
					if (StringUtils.isBlank(st003Cryxxb.getDeptCode())) {
						st003Cryxxb.setDeptCode(jkPatientVisit.getInDeptId());
					}

					if (StringUtils.isBlank(st003Cryxxb.getDeptName())) {
						if (StringUtils.isNotBlank(jkPatientVisit.getInDeptName())) {
							st003Cryxxb.setDeptName(jkPatientVisit.getInDeptName());
						} else if (StringUtils.isNotBlank(jkPatientVisit.getInDeptId())) {
							zg002Byks = this.tm.get(jkPatientVisit.getInDeptId());
							if (zg002Byks != null) {
								st003Cryxxb.setDeptName(zg002Byks.getDeptName());
							}
						}
					}

					if (StringUtils.isBlank(st003Cryxxb.getInDeptId())) {
						st003Cryxxb.setInDeptId(jkPatientVisit.getInDeptId());
					}

					if (StringUtils.isBlank(st003Cryxxb.getInDeptName())) {
						if (StringUtils.isNotBlank(jkPatientVisit.getInDeptName())) {
							st003Cryxxb.setInDeptName(jkPatientVisit.getInDeptName());
						} else if (StringUtils.isNotBlank(jkPatientVisit.getInDeptId())) {
							zg002Byks = this.tm.get(jkPatientVisit.getInDeptId());
							if (zg002Byks != null) {
								st003Cryxxb.setInDeptName(zg002Byks.getDeptName());
							}
						}
					}

					if (StringUtils.isBlank(st003Cryxxb.getOutDeptId())) {
						st003Cryxxb.setOutDeptId(jkPatientVisit.getOutDeptId());
					}

					if (StringUtils.isBlank(st003Cryxxb.getOutDeptName())) {
						if (StringUtils.isNotBlank(jkPatientVisit.getOutDeptName())) {
							st003Cryxxb.setOutDeptName(jkPatientVisit.getOutDeptName());
						} else if (StringUtils.isNotBlank(jkPatientVisit.getOutDeptId())) {
							zg002Byks = this.tm.get(jkPatientVisit.getOutDeptId());
							if (zg002Byks != null) {
								st003Cryxxb.setOutDeptName(zg002Byks.getDeptName());
							}
						}
					}

					st003Cryxxb.setOutAt(jkPatientVisit.getOutAt());
					if (jkPatientVisit.getInDays() != null) {
						st003Cryxxb.setInDays(jkPatientVisit.getInDays());
					}

					if (st003Cryxxb.getInDays() == null && st003Cryxxb.getInHospAt() != null
							&& st003Cryxxb.getOutAt() != null) {
						st003Cryxxb.setInDays(
								Integer.valueOf(f.a(f.i(st003Cryxxb.getOutAt()), f.i(st003Cryxxb.getInHospAt()))));
						if (st003Cryxxb.getInDays().intValue() == 0) {
							st003Cryxxb.setInDays(Integer.valueOf(1));
						}
					}

					if (StringUtils.isNotBlank(jkPatientVisit.getBedNo())) {
						st003Cryxxb.setBedNo(jkPatientVisit.getBedNo());
					}

					if (StringUtils.isNotBlank(jkPatientVisit.getChargeDrId())) {
						st003Cryxxb.setChargeDrId(jkPatientVisit.getChargeDrId());
					}

					if (StringUtils.isNotBlank(jkPatientVisit.getChargeDrName())) {
						st003Cryxxb.setChargeDrName(jkPatientVisit.getChargeDrName());
					} else if (StringUtils.isNotBlank(jkPatientVisit.getChargeDrId())) {
						zg003Yyzg = this.tn.get(jkPatientVisit.getChargeDrId());
						if (zg003Yyzg != null) {
							st003Cryxxb.setChargeDrName(zg003Yyzg.getEmployeeName());
						}
					}

					st003Cryxxb.setCost(jkPatientVisit.getCost());
					st003Cryxxb.setMemo(jkPatientVisit.getMemo());
					st003Cryxxb.setHospId(this.hospId);
					st003Cryxxb.setMarriage(jkPatientVisit.getMarriage());
					st003Cryxxb.setLxrName(jkPatientVisit.getLxrName());
					st003Cryxxb.setWorkAddr(jkPatientVisit.getWorkAddr());
					if (StringUtils.isNotBlank(jkPatientVisit.getChargeNrId())) {
						st003Cryxxb.setChargeNrId(jkPatientVisit.getChargeNrId());
					}

					if (StringUtils.isNotBlank(jkPatientVisit.getChargeNrName())) {
						st003Cryxxb.setChargeNrName(jkPatientVisit.getChargeNrName());
					} else if (StringUtils.isNotBlank(jkPatientVisit.getChargeNrId())) {
						zg003Yyzg = this.tn.get(jkPatientVisit.getChargeNrId());
						if (zg003Yyzg != null) {
							st003Cryxxb.setChargeNrName(zg003Yyzg.getEmployeeName());
						}
					}

					st003Cryxxb.setUpdDate(f.getCurDate());
					if (st003Flag) {
						this.tr.save(st003Cryxxb);
					} else {
						this.tr.update(st003Cryxxb);
					}

					if (ab.isNotEmpty(st003Cryxxb.getDeptCode())) {
						zg002Byks = this.tm.get(jkPatientVisit.getInDeptId());
						if (zg002Byks != null) {
							if (zg002Byks.getIfcaseoffice() == null) {
								zg002Byks.setIfcaseoffice(new Integer(1));
							}

							if (StringUtils.isNotBlank(zg002Byks.getDeptName())) {
								if (!zg002Byks.getDeptName().toUpperCase().contains("NICU")
										&& (!zg002Byks.getDeptName().toUpperCase().contains("新生儿")
												|| !zg002Byks.getDeptName().toUpperCase().contains("ICU"))
										&& (!zg002Byks.getDeptName().toUpperCase().contains("新生儿")
												|| !zg002Byks.getDeptName().toUpperCase().contains("重症"))) {
									if (zg002Byks.getDeptName().toUpperCase().contains("ICU")
											|| zg002Byks.getDeptName().toUpperCase().contains("重症")) {
										zg002Byks.setIficu(new Integer(1));
									}
								} else {
									zg002Byks.setIfchildoffice(new Integer(1));
								}
							}

							this.tm.update(zg002Byks);
						}
					}

					if (st003Cryxxb.getOutAt() != null) {
						List ex = this.tv.findListByZyid(st003Cryxxb.getZyid());
						Iterator arg13 = ex.iterator();

						while (arg13.hasNext()) {
							St005Ssxxb st005Ssxxb = (St005Ssxxb) arg13.next();
							st005Ssxxb.setOutAt(st003Cryxxb.getOutAt());
							st005Ssxxb.setOutDeptId(st003Cryxxb.getOutDeptId());
							st005Ssxxb.setOutDeptName(st003Cryxxb.getOutDeptName());
							this.tv.updateOutHosp(st005Ssxxb);
						}
					}

					St001Jbxxb arg15 = this.tq.get(jkPatientVisit.getPatientId());
					if (arg15 == null) {
						st001Flag = true;
						arg15 = new St001Jbxxb();
					}

					arg15.setId(jkPatientVisit.getPatientId());
					arg15.setPatientId(jkPatientVisit.getPatientId());
					arg15.setPatientName(jkPatientVisit.getPatientName());
					arg15.setIdCardId(jkPatientVisit.getIdCardId());
					arg15.setSex(jkPatientVisit.getSex());
					if ("1".equals(jkPatientVisit.getSex())) {
						arg15.setSex("男");
					}

					if ("2".equals(jkPatientVisit.getSex())) {
						arg15.setSex("女");
					}

					if (st003Cryxxb.getSex() == null && StringUtils.isNotBlank(arg15.getIdCardId())) {
						arg15.setSex(ab.bc(arg15.getIdCardId()));
					}

					arg15.setBirthDate(jkPatientVisit.getBirthDate());
					if (arg15.getBirthDate() == null && StringUtils.isNotBlank(arg15.getIdCardId())) {
						arg15.setBirthDate(f.ab(arg15.getIdCardId()));
					}

					arg15.setWeight(jkPatientVisit.getWeight());
					arg15.setAddress(jkPatientVisit.getAddress());
					arg15.setTel(jkPatientVisit.getTel());
					arg15.setNation(jkPatientVisit.getNation());
					arg15.setUpdDate(f.getCurDate());
					if (st001Flag) {
						this.tq.save(arg15);
					} else {
						this.tq.update(arg15);
					}

					jkPatientVisit.setUpdFlag(new Long(0L));
					jkPatientVisit.setUpdTime(this.now);
					this.sZ.updateFlag(jkPatientVisit);
				} catch (Exception arg14) {
					arg14.printStackTrace();
					this.result = f.g(this.now);
					this.sQ.b("患者住院就诊信息同步到业务库异常：\n" + jkPatientVisit.toString() + "\n" + arg14.getMessage(), this.now);
					logger.error("患者住院就诊信息同步到业务库异常。。。。。。。。。。。。。。。。。");
					logger.error(arg14.getMessage());
				}
			}
		} while (count >= 1000 && index <= 1000);

		this.c(com.nis.comm.enums.ab.hN);
		logger.info("完成患者住院就诊信息同步到业务库。。。。。。。。。。。。。。。。。");
	}

	private void Q() {
		logger.info("开始患者转科信息同步到业务库。。。。。。。。。。。。。。。。。");
		St012Zkjl st012Zkjl = null;
		St003Cryxxb st003Cryxxb = null;
		boolean st012Flag = false;
		JkPatientZkmx searchJkPatientZkmx = new JkPatientZkmx();
		searchJkPatientZkmx.setUpdFlag(new Long(1L));
		searchJkPatientZkmx.setUpdTime(f.a(f.getCurDate(), -this.tF.intValue()));
		searchJkPatientZkmx.setPage(Integer.valueOf(1));
		searchJkPatientZkmx.setSize(Integer.valueOf(1000));
		int index = 0;

		int count;
		do {
			++index;
			count = this.tb.findJkPatientZkmxCount(searchJkPatientZkmx);
			List jkPatientZkmxList = this.tb.findJkPatientZkmx(searchJkPatientZkmx);
			Iterator arg8 = jkPatientZkmxList.iterator();

			while (arg8.hasNext()) {
				JkPatientZkmx jkPatientZkmx = (JkPatientZkmx) arg8.next();
				st012Flag = false;

				try {
					if ("1".equals(this.a(com.nis.comm.enums.ab.hO))) {
						logger.info("暂停患者转科信息同步到业务库。。。。。。。。。。。。。。。。。");
						this.b(com.nis.comm.enums.ab.hO);
						return;
					}

					st012Zkjl = this.tu.get(jkPatientZkmx.getId());
					if (st012Zkjl == null) {
						st012Flag = true;
						st012Zkjl = new St012Zkjl();
					}

					st012Zkjl.setId(jkPatientZkmx.getId());
					st012Zkjl.setZyid(jkPatientZkmx.getZyid());
					st012Zkjl.setInDate(jkPatientZkmx.getInDeptAt());
					st012Zkjl.setInDeptId(jkPatientZkmx.getInDeptId());
					st012Zkjl.setOutDate(jkPatientZkmx.getOutDepAt());
					st012Zkjl.setOutDeptId(jkPatientZkmx.getOutDeptId());
					st012Zkjl.setBedNo(jkPatientZkmx.getBedNo());
					if (StringUtils.isNotBlank(st012Zkjl.getBedNo()) && StringUtils.isNotBlank(st012Zkjl.getZyid())) {
						st003Cryxxb = this.tr.get(st012Zkjl.getZyid());
						if (st003Cryxxb != null) {
							st003Cryxxb.setBedNo(st012Zkjl.getBedNo());
							this.tr.update(st003Cryxxb);
						}
					}

					st012Zkjl.setInDeptDays(jkPatientZkmx.getInDeptDays());
					st012Zkjl.setUpdTime(f.getCurDate());
					if (st012Flag) {
						this.tu.save(st012Zkjl);
					} else {
						this.tu.update(st012Zkjl);
					}

					jkPatientZkmx.setUpdFlag(new Long(0L));
					jkPatientZkmx.setUpdTime(this.now);
					this.tb.updateFlag(jkPatientZkmx);
				} catch (Exception arg10) {
					arg10.printStackTrace();
					this.result = f.g(this.now);
					this.sQ.b("患者转科信息同步到业务库异常：\n" + jkPatientZkmx.toString() + "\n" + arg10.getMessage(), this.now);
					logger.error("患者转科信息同步到业务库异常。。。。。。。。。。。。。。。。。");
					logger.error(arg10.getMessage());
				}
			}
		} while (count >= 1000 && index <= 1000);

		this.c(com.nis.comm.enums.ab.hO);
		logger.info("完成患者转科信息同步到业务库。。。。。。。。。。。。。。。。。");
	}

	private void R() {
		logger.info("开始患者诊断信息同步到业务库。。。。。。。。。。。。。。。。。");
		St002Zdxxb st002Zdxxb = null;
		St003Cryxxb st003Cryxxb = null;
		Zg003Yyzg zg003Yyzg = null;
		boolean st002Flag = false;
		JkPatientDiagnose searchJkPatientDiagnose = new JkPatientDiagnose();
		searchJkPatientDiagnose.setUpdFlag(new Long(1L));
		searchJkPatientDiagnose.setUpdTime(f.a(f.getCurDate(), -this.tF.intValue()));
		searchJkPatientDiagnose.setPage(Integer.valueOf(1));
		searchJkPatientDiagnose.setSize(Integer.valueOf(1000));
		int index = 0;

		int count;
		do {
			++index;
			count = this.ta.findJkPatientDiagnoseCount(searchJkPatientDiagnose);
			List jkPatientDiagnoseList = this.ta.findJkPatientDiagnose(searchJkPatientDiagnose);
			Iterator arg9 = jkPatientDiagnoseList.iterator();

			while (arg9.hasNext()) {
				JkPatientDiagnose jkPatientDiagnose = (JkPatientDiagnose) arg9.next();
				st002Flag = false;

				try {
					if ("1".equals(this.a(com.nis.comm.enums.ab.hP))) {
						logger.info("暂停患者诊断信息同步到业务库。。。。。。。。。。。。。。。。。");
						this.b(com.nis.comm.enums.ab.hP);
						return;
					}

					st002Zdxxb = this.ts.get(jkPatientDiagnose.getId());
					if (st002Zdxxb == null) {
						st002Flag = true;
						st002Zdxxb = new St002Zdxxb();
					}

					st002Zdxxb.setId(jkPatientDiagnose.getId());
					st002Zdxxb.setZyid(jkPatientDiagnose.getZyid());
					st002Zdxxb.setPatientId(jkPatientDiagnose.getPatientId());
					st002Zdxxb.setVisitId(jkPatientDiagnose.getVisitId());
					if (StringUtils.isNotBlank(jkPatientDiagnose.getZyid())) {
						st003Cryxxb = this.tr.get(jkPatientDiagnose.getZyid());
						if (st003Cryxxb != null) {
							st002Zdxxb.setPatientId(st003Cryxxb.getPatientId());
							st002Zdxxb.setVisitId(st003Cryxxb.getVisitId());
						}
					}

					if (StringUtils.isNotBlank(jkPatientDiagnose.getDocId())) {
						st002Zdxxb.setDocId(jkPatientDiagnose.getDocId());
					}

					if (StringUtils.isNotBlank(jkPatientDiagnose.getDocName())) {
						st002Zdxxb.setDocName(jkPatientDiagnose.getDocName());
					} else if (StringUtils.isNotBlank(jkPatientDiagnose.getDocId())) {
						zg003Yyzg = this.tn.get(jkPatientDiagnose.getDocId());
						if (zg003Yyzg != null) {
							st002Zdxxb.setDocName(zg003Yyzg.getEmployeeName());
						}
					}

					st002Zdxxb.setDiagnosisDate(jkPatientDiagnose.getDiagnosisDate());
					st002Zdxxb.setDiagnosisType(jkPatientDiagnose.getDiagnosisType());
					st002Zdxxb.setDiagnosisNo(jkPatientDiagnose.getDiagnosisNo());
					st002Zdxxb.setDiagnosisName(jkPatientDiagnose.getDiagnosisName());
					st002Zdxxb.setMzid(jkPatientDiagnose.getMzid());
					if (jkPatientDiagnose.getDiagnosisTypeMain() != null) {
						st002Zdxxb.setDiagnosisTypeMain("" + jkPatientDiagnose.getDiagnosisTypeMain());
					}

					st002Zdxxb.setUpdDate(f.getCurDate());
					if (st002Flag) {
						this.ts.save(st002Zdxxb);
					} else {
						this.ts.update(st002Zdxxb);
					}

					jkPatientDiagnose.setUpdFlag(new Long(0L));
					jkPatientDiagnose.setUpdTime(this.now);
					this.ta.updateFlag(jkPatientDiagnose);
				} catch (Exception arg11) {
					arg11.printStackTrace();
					this.result = f.g(this.now);
					this.sQ.b("患者诊断信息同步到业务库异常：\n" + jkPatientDiagnose.toString() + "\n" + arg11.getMessage(), this.now);
					logger.error("患者诊断信息同步到业务库异常。。。。。。。。。。。。。。。。。");
					logger.error(arg11.getMessage());
				}
			}
		} while (count >= 1000 && index <= 1000);

		this.c(com.nis.comm.enums.ab.hP);
		logger.info("完成患者诊断信息同步到业务库。。。。。。。。。。。。。。。。。");
	}

	private void S() {
      logger.info("开始患者病程信息同步到业务库。。。。。。。。。。。。。。。。。");
      JkPatientBc searchJkPatientBc = new JkPatientBc();
      searchJkPatientBc.setUpdFlag(new Long(1L));
      searchJkPatientBc.setUpdTime(f.a(f.getCurDate(), -this.tF.intValue()));
      searchJkPatientBc.setPage(Integer.valueOf(1));
      searchJkPatientBc.setSize(Integer.valueOf(500));
      int index = 0;
      List jkPatientBcList = null;

      int count;
      do {
         ++index;
         if("1".equals(this.a(com.nis.comm.enums.ab.hU))) {
            logger.info("暂停病程信息同步到业务库。。。。。。。。。。。。。。。。。");
            this.b(com.nis.comm.enums.ab.hU);
            break;
         }

         count = this.td.findJkPatientBcCount(searchJkPatientBc);
         jkPatientBcList = this.td.findJkPatientBc(searchJkPatientBc);
         List result = m.b(jkPatientBcList, 10);
         ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

         for(int e = 0; e < result.size(); ++e) {
            List zyidList = (List)result.get(e);
            fixedThreadPool.execute(new 12(this, zyidList));
         }

         fixedThreadPool.shutdown();

         while(!fixedThreadPool.isTerminated()) {
            try {
               Thread.sleep(1000L);
            } catch (InterruptedException arg8) {
               arg8.printStackTrace();
            }
         }
      } while(count >= 1000 && index <= 1000);

      this.c(com.nis.comm.enums.ab.hU);
      logger.info("完成患者病程信息同步到业务库。。。。。。。。。。。。。。。。。");
   }

	private void E(List<JkPatientBc> jkPatientBcList) {
		Iterator arg2 = jkPatientBcList.iterator();

		while (arg2.hasNext()) {
			JkPatientBc jkPatientBc = (JkPatientBc) arg2.next();
			boolean st008Flag = false;

			try {
				St008Bcjl ex = this.tz.get(jkPatientBc.getId());
				if (ex == null) {
					st008Flag = true;
					ex = new St008Bcjl();
				}

				ex.setId(jkPatientBc.getId());
				ex.setZyid(jkPatientBc.getZyid());
				ex.setPatientId(jkPatientBc.getPatientId());
				ex.setVisitId(jkPatientBc.getVisitId());
				if (StringUtils.isNotBlank(jkPatientBc.getZyid())) {
					St003Cryxxb st003Cryxxb = this.tr.get(jkPatientBc.getZyid());
					ex.setPatientId(st003Cryxxb.getPatientId());
					ex.setVisitId(st003Cryxxb.getVisitId());
				}

				ex.setCaseClass(jkPatientBc.getCaseClass());
				ex.setBcCode(jkPatientBc.getBcCode());
				ex.setBcName(jkPatientBc.getBcName());
				ex.setCourseContent(jkPatientBc.getCourseContent());
				ex.setCreateAt(jkPatientBc.getCreateAt());
				ex.setEnterDate(jkPatientBc.getEnterDate());
				ex.setUpdDate(f.getCurDate());
				ex.setAnalFlag(Long.valueOf(0L));
				if (st008Flag) {
					this.tz.save(ex);
				} else {
					this.tz.update(ex);
				}

				jkPatientBc.setUpdFlag(new Long(0L));
				jkPatientBc.setUpdTime(this.now);
				this.td.updateFlag(jkPatientBc);
			} catch (Exception arg6) {
				arg6.printStackTrace();
				this.result = f.g(this.now);
				this.sQ.b("患者病程信息同步到业务库异常：\n" + jkPatientBc.toString() + "\n" + arg6.getMessage(), this.now);
				logger.error("患者病程信息同步到业务库异常。。。。。。。。。。。。。。。。。");
				logger.error(arg6.getMessage());
			}
		}

	}

	private void T() {
      logger.info("开始患者体温信息同步到业务库。。。。。。。。。。。。。。。。。");
      JkPatientTemprature searchJkPatientTemprature = new JkPatientTemprature();
      searchJkPatientTemprature.setUpdFlag(new Long(1L));
      searchJkPatientTemprature.setUpdTime(f.a(f.getCurDate(), -this.tF.intValue()));
      searchJkPatientTemprature.setPage(Integer.valueOf(1));
      searchJkPatientTemprature.setSize(Integer.valueOf(1000));
      int index = 0;

      int count;
      do {
         ++index;
         if("1".equals(this.a(com.nis.comm.enums.ab.hQ))) {
            logger.info("暂停体温信息同步到业务库。。。。。。。。。。。。。。。。。");
            this.b(com.nis.comm.enums.ab.hQ);
            break;
         }

         count = this.te.findJkPatientTempratureCount(searchJkPatientTemprature);
         List jkPatientTempratureList = this.te.findJkPatientTemprature(searchJkPatientTemprature);
         List result = m.b(jkPatientTempratureList, 10);
         ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

         for(int e = 0; e < result.size(); ++e) {
            List zyidList = (List)result.get(e);
            fixedThreadPool.execute(new 13(this, zyidList));
         }

         fixedThreadPool.shutdown();

         while(!fixedThreadPool.isTerminated()) {
            try {
               Thread.sleep(1000L);
            } catch (InterruptedException arg8) {
               arg8.printStackTrace();
            }
         }
      } while(count >= 1000 && index <= 1000);

      this.c(com.nis.comm.enums.ab.hQ);
      logger.info("完成患者体温信息同步到业务库。。。。。。。。。。。。。。。。。");
   }

	private void F(List<JkPatientTemprature> jkPatientTempratureList) {
		Iterator arg2 = jkPatientTempratureList.iterator();

		while (arg2.hasNext()) {
			JkPatientTemprature jkPatientTemprature = (JkPatientTemprature) arg2.next();
			boolean st006Flag = false;

			try {
				St006Twxx ex = this.tx.get(jkPatientTemprature.getId());
				if (ex == null) {
					st006Flag = true;
					ex = new St006Twxx();
				}

				ex.setId(jkPatientTemprature.getId());
				ex.setZyid(jkPatientTemprature.getZyid());
				ex.setPatientId(jkPatientTemprature.getPatientId());
				if (StringUtils.isNotBlank(jkPatientTemprature.getZyid())) {
					St003Cryxxb st003Cryxxb = this.tr.get(jkPatientTemprature.getZyid());
					if (st003Cryxxb != null) {
						ex.setPatientId(st003Cryxxb.getPatientId());
					}
				}

				ex.setRecordingAt(jkPatientTemprature.getRecordingAt());
				ex.setTwValues(jkPatientTemprature.getTwValues());
				ex.setTempratureTypeName(jkPatientTemprature.getTempratureTypeName());
				ex.setUpdDate(f.getCurDate());
				if (st006Flag) {
					this.tx.save(ex);
				} else {
					this.tx.update(ex);
				}

				jkPatientTemprature.setUpdFlag(new Long(0L));
				jkPatientTemprature.setUpdTime(this.now);
				this.te.updateFlag(jkPatientTemprature);
			} catch (Exception arg6) {
				arg6.printStackTrace();
				this.result = f.g(this.now);
				this.sQ.b("患者体温信息同步到业务库异常：\n" + jkPatientTemprature.toString() + "\n" + arg6.getMessage(), this.now);
				logger.error("患者体温信息同步到业务库异常。。。。。。。。。。。。。。。。。");
				logger.error(arg6.getMessage());
			}
		}

	}

	private void U() {
		logger.info("开始患者大便常规信息同步到业务库。。。。。。。。。。。。。。。。。");
		St021PatientRoutine st021PatientRoutine = null;
		St003Cryxxb st003Cryxxb = null;
		boolean st021Flag = false;
		JkPatientRoutine searchJkPatientRoutine = new JkPatientRoutine();
		searchJkPatientRoutine.setUpdFlag(new Long(1L));
		searchJkPatientRoutine.setUpdTime(f.a(f.getCurDate(), -this.tF.intValue()));
		searchJkPatientRoutine.setPage(Integer.valueOf(1));
		searchJkPatientRoutine.setSize(Integer.valueOf(1000));
		int index = 0;

		int count;
		do {
			++index;
			count = this.tf.findJkPatientRoutineCount(searchJkPatientRoutine);
			List jkPatientRoutineList = this.tf.findJkPatientRoutine(searchJkPatientRoutine);
			Iterator arg8 = jkPatientRoutineList.iterator();

			while (arg8.hasNext()) {
				JkPatientRoutine jkPatientRoutine = (JkPatientRoutine) arg8.next();
				st021Flag = false;

				try {
					if ("1".equals(this.a(com.nis.comm.enums.ab.hR))) {
						logger.info("暂停大便常规信息同步到业务库。。。。。。。。。。。。。。。。。");
						this.b(com.nis.comm.enums.ab.hR);
						boolean arg11 = true;
						return;
					}

					st021PatientRoutine = this.ty.get(jkPatientRoutine.getId());
					if (st021PatientRoutine == null) {
						st021Flag = true;
						st021PatientRoutine = new St021PatientRoutine();
					}

					st021PatientRoutine.setId(jkPatientRoutine.getId());
					st021PatientRoutine.setZyid(jkPatientRoutine.getZyid());
					if (StringUtils.isNotBlank(jkPatientRoutine.getZyid())) {
						st003Cryxxb = this.tr.get(jkPatientRoutine.getZyid());
						if (st003Cryxxb != null) {
							st021PatientRoutine.setPatientId(st003Cryxxb.getPatientId());
							st021PatientRoutine.setVisitId(st003Cryxxb.getVisitId());
						}
					}

					st021PatientRoutine.setRecordingAt(jkPatientRoutine.getRecordingAt());
					st021PatientRoutine.setRoutineValues(jkPatientRoutine.getRoutineValues());
					st021PatientRoutine.setUpdTime(f.getCurDate());
					if (st021Flag) {
						this.ty.save(st021PatientRoutine);
					} else {
						this.ty.update(st021PatientRoutine);
					}

					jkPatientRoutine.setUpdFlag(new Long(0L));
					jkPatientRoutine.setUpdTime(this.now);
					this.tf.updateFlag(jkPatientRoutine);
				} catch (Exception arg10) {
					arg10.printStackTrace();
					this.result = f.g(this.now);
					this.sQ.b("患者大便常规信息同步到业务库异常：\n" + jkPatientRoutine.toString() + "\n" + arg10.getMessage(),
							this.now);
					logger.error("患者大便常规信息同步到业务库异常。。。。。。。。。。。。。。。。。");
					logger.error(arg10.getMessage());
				}
			}
		} while (count >= 1000 && index <= 1000);

		this.c(com.nis.comm.enums.ab.hR);
		logger.info("完成患者大便常规信息同步到业务库。。。。。。。。。。。。。。。。。");
	}

	private void V() {
		logger.info("开始患者影像信息同步到业务库。。。。。。。。。。。。。。。。。");
		St014Pacs st014Pacs = null;
		Zg002Byks zg002Byks = null;
		St003Cryxxb st003Cryxxb = null;
		boolean st014Flag = false;
		JkPatientYx searchJkPatientYx = new JkPatientYx();
		searchJkPatientYx.setUpdFlag(new Long(1L));
		searchJkPatientYx.setUpdTime(f.a(f.getCurDate(), -this.tF.intValue()));
		searchJkPatientYx.setPage(Integer.valueOf(1));
		searchJkPatientYx.setSize(Integer.valueOf(1000));
		int index = 0;

		int count;
		do {
			++index;
			count = this.tg.findJkPatientYxCount(searchJkPatientYx);
			List jkPatientYxList = this.tg.findJkPatientYx(searchJkPatientYx);
			Iterator arg9 = jkPatientYxList.iterator();

			while (arg9.hasNext()) {
				JkPatientYx jkPatientYx = (JkPatientYx) arg9.next();
				st014Flag = false;

				try {
					if ("1".equals(this.a(com.nis.comm.enums.ab.hS))) {
						logger.info("暂停影像标本信息同步到业务库。。。。。。。。。。。。。。。。。");
						this.b(com.nis.comm.enums.ab.hS);
						return;
					}

					st014Pacs = this.tE.get(jkPatientYx.getId());
					if (st014Pacs == null) {
						st014Flag = true;
						st014Pacs = new St014Pacs();
					}

					st014Pacs.setId(jkPatientYx.getId());
					st014Pacs.setZyid(jkPatientYx.getZyid());
					if (StringUtils.isNotBlank(jkPatientYx.getZyid())) {
						st003Cryxxb = this.tr.get(jkPatientYx.getZyid());
						if (st003Cryxxb != null) {
							st014Pacs.setPatientId(st003Cryxxb.getPatientId());
						}
					}

					st014Pacs.setMzid(st014Pacs.getMzid());
					st014Pacs.setCheckNo(jkPatientYx.getCheckNo());
					st014Pacs.setCheckAt(jkPatientYx.getCheckDate());
					st014Pacs.setReportAt(jkPatientYx.getReportDate());
					if (st014Pacs.getCheckAt() == null && st014Pacs.getReportAt() != null) {
						st014Pacs.setCheckAt(st014Pacs.getReportAt());
					}

					if (st014Pacs.getCheckAt() != null && st014Pacs.getReportAt() == null) {
						st014Pacs.setReportAt(st014Pacs.getCheckAt());
					}

					if (StringUtils.isNotBlank(jkPatientYx.getClinDiagnose())) {
						st014Pacs.setCliDiagnose(jkPatientYx.getClinDiagnose().getBytes("UTF-8"));
					}

					st014Pacs.setCheckDesc(jkPatientYx.getDescription());
					st014Pacs.setCheckImpr(jkPatientYx.getImpression());
					st014Pacs.setCheckDr(jkPatientYx.getCheckDoctor());
					st014Pacs.setReportDr(jkPatientYx.getReportDoctor());
					if (StringUtils.isNotBlank(jkPatientYx.getDeptId())) {
						st014Pacs.setDeptId(jkPatientYx.getDeptId());
					}

					if (StringUtils.isNotBlank(jkPatientYx.getDeptName())) {
						st014Pacs.setDeptName(jkPatientYx.getDeptName());
					} else if (StringUtils.isNotBlank(st014Pacs.getDeptId())) {
						zg002Byks = this.tm.get(st014Pacs.getDeptId());
						if (zg002Byks != null) {
							st014Pacs.setDeptName(zg002Byks.getDeptName());
						}
					}

					st014Pacs.setCreateAt(st014Pacs.getCheckAt());
					st014Pacs.setUpdDate(f.getCurDate());
					if (st014Flag) {
						this.tE.save(st014Pacs);
					} else {
						this.tE.update(st014Pacs);
					}

					jkPatientYx.setUpdFlag(new Long(0L));
					jkPatientYx.setUpdTime(this.now);
					this.tg.updateFlag(jkPatientYx);
				} catch (Exception arg11) {
					arg11.printStackTrace();
					this.result = f.g(this.now);
					this.sQ.b("患者影像信息同步到业务库异常：\n" + jkPatientYx.toString() + "\n" + arg11.getMessage(), this.now);
					logger.error("患者影像信息同步到业务库异常。。。。。。。。。。。。。。。。。");
					logger.error(arg11.getMessage());
				}
			}
		} while (count >= 1000 && index <= 1000);

		this.c(com.nis.comm.enums.ab.hS);
		logger.info("完成影像信息同步到业务库。。。。。。。。。。。。。。。。。");
	}

	private void W() {
		logger.info("开始患者手术信息同步到业务库。。。。。。。。。。。。。。。。。");
		St005Ssxxb st005Ssxxb = null;
		Zg002Byks zg002Byks = null;
		Zg003Yyzg zg003Yyzg = null;
		St003Cryxxb st003Cryxxb = null;
		boolean st005Flag = false;
		JkPatientOperation searchJkPatientOperation = new JkPatientOperation();
		searchJkPatientOperation.setUpdFlag(new Long(1L));
		searchJkPatientOperation.setUpdTime(f.a(f.getCurDate(), -this.tF.intValue()));
		searchJkPatientOperation.setPage(Integer.valueOf(1));
		searchJkPatientOperation.setSize(Integer.valueOf(1000));
		int index = 0;

		int count;
		do {
			++index;
			count = this.th.findJkPatientOperationCount(searchJkPatientOperation);
			List jkPatientOperationList = this.th.findJkPatientOperation(searchJkPatientOperation);
			Iterator arg10 = jkPatientOperationList.iterator();

			while (arg10.hasNext()) {
				JkPatientOperation jkPatientOperation = (JkPatientOperation) arg10.next();
				st005Flag = false;

				try {
					if ("1".equals(this.a(com.nis.comm.enums.ab.hT))) {
						logger.info("暂停手术标本信息同步到业务库。。。。。。。。。。。。。。。。。");
						this.b(com.nis.comm.enums.ab.hT);
						return;
					}

					st005Ssxxb = this.tv.get(jkPatientOperation.getId());
					if (st005Ssxxb == null) {
						st005Flag = true;
						st005Ssxxb = new St005Ssxxb();
						st005Ssxxb.setCreateDate(this.now);
					}

					if (st005Ssxxb.getStatus() == null || st005Ssxxb.getStatus().intValue() != 4) {
						st005Ssxxb.setId(jkPatientOperation.getId());
						st005Ssxxb.setRelid(jkPatientOperation.getId());
						st005Ssxxb.setZyid(jkPatientOperation.getZyid());
						st005Ssxxb.setPatientId(jkPatientOperation.getPatientId());
						st005Ssxxb.setVisitId(jkPatientOperation.getVisitId());
						if (StringUtils.isNotBlank(jkPatientOperation.getZyid())) {
							st003Cryxxb = this.tr.get(jkPatientOperation.getZyid());
							if (st003Cryxxb != null) {
								st005Ssxxb.setPatientId(st003Cryxxb.getPatientId());
								st005Ssxxb.setVisitId(st003Cryxxb.getVisitId());
								st005Ssxxb.setPatientName(st003Cryxxb.getPatientName());
								st005Ssxxb.setAge(st003Cryxxb.getAge());
								st005Ssxxb.setAgeUnit(st003Cryxxb.getAgeUnit());
								st005Ssxxb.setSex(st003Cryxxb.getSex());
								st005Ssxxb.setTel(st003Cryxxb.getTel());
								st005Ssxxb.setWeight(st003Cryxxb.getWeight());
								st005Ssxxb.setPatientAddress(st003Cryxxb.getAddress());
								st005Ssxxb.setBedNo(st003Cryxxb.getBedNo());
								st005Ssxxb.setInHospAt(st003Cryxxb.getInHospAt());
								st005Ssxxb.setInDeptId(st003Cryxxb.getInDeptId());
								st005Ssxxb.setInDeptName(st003Cryxxb.getInDeptName());
								st005Ssxxb.setOutAt(st003Cryxxb.getOutAt());
								st005Ssxxb.setOutDeptId(st003Cryxxb.getOutDeptName());
								st005Ssxxb.setOutDeptName(st003Cryxxb.getOutDeptName());
								st005Ssxxb.setInDays(st003Cryxxb.getInDays());
								st005Ssxxb.setChargeDrId(st003Cryxxb.getChargeDrId());
								st005Ssxxb.setChargeDrName(st003Cryxxb.getChargeDrName());
								st005Ssxxb.setCost(st003Cryxxb.getCost());
								st005Ssxxb.setMemo(st003Cryxxb.getMemo());
							}
						}

						if (StringUtils.isNotBlank(jkPatientOperation.getDeptId())) {
							st005Ssxxb.setDeptId(jkPatientOperation.getDeptId());
						}

						if (StringUtils.isNotBlank(jkPatientOperation.getDeptName())) {
							st005Ssxxb.setDeptName(jkPatientOperation.getDeptName());
						} else if (StringUtils.isNotBlank(st005Ssxxb.getDeptId())) {
							zg002Byks = this.tm.get(st005Ssxxb.getDeptId());
							if (zg002Byks != null) {
								st005Ssxxb.setDeptName(zg002Byks.getDeptName());
							}
						}

						st005Ssxxb.setOperAt(jkPatientOperation.getOperAt());
						if (st005Ssxxb.getOperAt() == null && jkPatientOperation.getOperStartTime() != null) {
							st005Ssxxb.setOperAt(jkPatientOperation.getOperStartTime());
						}

						if (st005Ssxxb.getOperAt() == null && jkPatientOperation.getOperEndTime() != null) {
							st005Ssxxb.setOperAt(jkPatientOperation.getOperEndTime());
						}

						st005Ssxxb.setOperAtStart(jkPatientOperation.getOperStartTime());
						if (jkPatientOperation.getOperAt() != null && jkPatientOperation.getOperStartTime() == null) {
							st005Ssxxb.setOperAtStart(jkPatientOperation.getOperAt());
						}

						st005Ssxxb.setOperAtEnd(jkPatientOperation.getOperEndTime());
						if (jkPatientOperation.getOperAt() != null && jkPatientOperation.getOperEndTime() == null) {
							st005Ssxxb.setOperAtEnd(jkPatientOperation.getOperAt());
						}

						st005Ssxxb.setOperRoom(jkPatientOperation.getOperRoom());
						st005Ssxxb.setOperName(jkPatientOperation.getOperName());
						st005Ssxxb.setOperId(jkPatientOperation.getOperId());
						if (StringUtils.isNotBlank(jkPatientOperation.getOpedocId())) {
							st005Ssxxb.setOpedocId(jkPatientOperation.getOpedocId());
						}

						if (StringUtils.isNotBlank(jkPatientOperation.getOpedocName())) {
							st005Ssxxb.setOpedocName(jkPatientOperation.getOpedocName());
						} else if (StringUtils.isNotBlank(jkPatientOperation.getOpedocId())) {
							zg003Yyzg = this.tn.get(jkPatientOperation.getOpedocId());
							if (zg003Yyzg != null) {
								st005Ssxxb.setOpedocName(zg003Yyzg.getEmployeeName());
							}
						}

						st005Ssxxb.setAnesDrName(jkPatientOperation.getAnesDrName());
						st005Ssxxb.setAsa(jkPatientOperation.getAsa());
						st005Ssxxb.setNarcKind(jkPatientOperation.getNarcKind());
						if (StringUtils.isNotBlank(jkPatientOperation.getOperLengTime())
								&& r.isNumber(jkPatientOperation.getOperLengTime())) {
							st005Ssxxb.setOperLengTime(new Integer(jkPatientOperation.getOperLengTime()));
						}

						if (jkPatientOperation.getIncisionGrade() != null) {
							if (!("" + jkPatientOperation.getIncisionGrade()).contains("1")
									&& !("" + jkPatientOperation.getIncisionGrade()).contains("I")) {
								if (!("" + jkPatientOperation.getIncisionGrade()).contains("2")
										&& !("" + jkPatientOperation.getIncisionGrade()).contains("II")) {
									if (!("" + jkPatientOperation.getIncisionGrade()).contains("3")
											&& !("" + jkPatientOperation.getIncisionGrade()).contains("III")) {
										if (!("" + jkPatientOperation.getIncisionGrade()).contains("4")
												&& !("" + jkPatientOperation.getIncisionGrade()).contains("IV类")) {
											st005Ssxxb.setIncisionGrade("" + jkPatientOperation.getIncisionGrade());
										} else {
											st005Ssxxb.setIncisionGrade("IV类");
										}
									} else {
										st005Ssxxb.setIncisionGrade("III类");
									}
								} else {
									st005Ssxxb.setIncisionGrade("II类");
								}
							} else {
								st005Ssxxb.setIncisionGrade("I类");
							}
						}

						st005Ssxxb.setUpdDate(f.getCurDate());
						if (st005Flag) {
							this.tv.save(st005Ssxxb);
						} else {
							this.tv.update(st005Ssxxb);
						}

						this.bO.a(st005Ssxxb, true);
						jkPatientOperation.setUpdFlag(new Long(0L));
						jkPatientOperation.setUpdTime(this.now);
						this.th.updateFlag(jkPatientOperation);
					}
				} catch (Exception arg12) {
					arg12.printStackTrace();
					this.result = f.g(this.now);
					this.sQ.b("患者手术信息同步到业务库异常：\n" + jkPatientOperation.toString() + "\n" + arg12.getMessage(),
							this.now);
					logger.error("患者手术信息同步到业务库异常。。。。。。。。。。。。。。。。。");
					logger.error(arg12.getMessage());
				}
			}
		} while (count >= 1000 && index <= 1000);

		this.c(com.nis.comm.enums.ab.hT);
		logger.info("完成手术信息同步到业务库。。。。。。。。。。。。。。。。。");
	}

	private void X() {
		logger.info("开始患者医嘱信息同步到业务库。。。。。。。。。。。。。。。。。");
		JkPatientYz searchJkPatientYz = new JkPatientYz();
		searchJkPatientYz.setUpdFlag(new Long(1L));
		searchJkPatientYz.setUpdTime(f.a(f.getCurDate(), -this.tF.intValue()));
		searchJkPatientYz.setPage(Integer.valueOf(1));
		searchJkPatientYz.setSize(Integer.valueOf(1000));
		int index = 0;

		int count;
		do {
			++index;
			if ("1".equals(this.a(com.nis.comm.enums.ab.hV))) {
				logger.info("暂停医嘱标本信息同步到业务库。。。。。。。。。。。。。。。。。");
				this.b(com.nis.comm.enums.ab.hV);
				break;
			}

			count = this.tc.findJkPatientYzCount(searchJkPatientYz);
			List jkPatientYzList = this.tc.findJkPatientYz(searchJkPatientYz);
			this.G(jkPatientYzList);
		} while (count >= 1000 && index <= 1000);

		this.c(com.nis.comm.enums.ab.hV);
		logger.info("完成医嘱信息同步到业务库。。。。。。。。。。。。。。。。。");
	}

	public void G(List<JkPatientYz> jkPatientYzList) {
		Iterator arg2 = jkPatientYzList.iterator();

		while (arg2.hasNext()) {
			JkPatientYz jkPatientYz = (JkPatientYz) arg2.next();
			boolean st014Flag = false;

			try {
				St004Yzxxb ex = this.tt.get(jkPatientYz.getId());
				if (ex == null) {
					st014Flag = true;
					ex = new St004Yzxxb();
				}

				ex.setId(jkPatientYz.getId());
				ex.setZyid(jkPatientYz.getZyid());
				ex.setPatientId(jkPatientYz.getPatientId());
				ex.setPatientName(jkPatientYz.getPatientName());
				ex.setVisitId(jkPatientYz.getVisitId());
				if (StringUtils.isNotBlank(jkPatientYz.getZyid())) {
					St003Cryxxb drugLine = this.tr.get(jkPatientYz.getZyid());
					if (drugLine != null) {
						ex.setPatientId(drugLine.getPatientId());
						ex.setVisitId(drugLine.getVisitId());
						ex.setPatientName(drugLine.getPatientName());
					}
				}

				if (this.tt.findFlagJrCount(jkPatientYz.getId()) > 0) {
					ex.setFlagJr(Integer.valueOf(1));
				}

				ex.setIsKjyw(new Integer(0));
				if (jkPatientYz.getIsKjyw() != null) {
					ex.setIsKjyw(jkPatientYz.getIsKjyw());
				} else {
					Integer drugLine1 = this.tt.findFlagKjywCount(jkPatientYz.getId());
					if (drugLine1 != null && drugLine1.intValue() > 0) {
						ex.setIsKjyw(Integer.valueOf(1));
						ex.setDrugLine(drugLine1);
					}
				}

				ex.setOrderType(jkPatientYz.getOrderType());
				ex.setOrderAt(jkPatientYz.getOrderAt());
				ex.setStopAt(jkPatientYz.getStopAt());
				ex.setExecuteTime(jkPatientYz.getExecuteTime());
				ex.setExecofficeCode(jkPatientYz.getExecofficeCode());
				ex.setExecofficeName(jkPatientYz.getExecofficeName());
				if ("0".equals("" + ex.getOrderType()) && jkPatientYz.getStopAt() == null) {
					ex.setStopAt(ex.getOrderAt());
				}

				ex.setBdocId(jkPatientYz.getBdocId());
				ex.setBdocName(jkPatientYz.getBdocName());
				ex.setBnrsId(jkPatientYz.getBnrsId());
				ex.setBnrsName(jkPatientYz.getBnrsName());
				ex.setEdocId(jkPatientYz.getEdocId());
				ex.setEdocName(jkPatientYz.getEdocName());
				ex.setUsedrugDays(jkPatientYz.getUsedrugDays());
				ex.setOrderClass(jkPatientYz.getOrderClass());
				ex.setOrderId(jkPatientYz.getOrderId());
				ex.setOrderName(jkPatientYz.getOrderName());
				ex.setDrugSpeci(jkPatientYz.getDrugSpeci());
				ex.setDosage(jkPatientYz.getDosage());
				ex.setDosageUnit(jkPatientYz.getDosageUnit());
				ex.setUseCount(jkPatientYz.getUseCount());
				ex.setSypc(jkPatientYz.getSypc());
				ex.setAdminRouteId(jkPatientYz.getAdminRouteId());
				ex.setAdminRouteName(jkPatientYz.getAdminRouteName());
				ex.setUsePurpose(jkPatientYz.getUsePurpose());
				ex.setYpdj(jkPatientYz.getYpdj());
				if (jkPatientYz.getFrequency() != null) {
					ex.setFrequency("" + jkPatientYz.getFrequency());
				}

				ex.setMemo(jkPatientYz.getMemo());
				ex.setUpdDate(f.getCurDate());
				if (st014Flag) {
					this.tt.save(ex);
				} else {
					this.tt.update(ex);
				}

				jkPatientYz.setUpdFlag(new Long(0L));
				jkPatientYz.setUpdTime(this.now);
				this.tc.updateFlag(jkPatientYz);
			} catch (Exception arg6) {
				arg6.printStackTrace();
				this.result = f.g(this.now);
				this.sQ.b("患者医嘱同步到业务库异常：\n" + jkPatientYz.toString() + "\n" + arg6.getMessage(), this.now);
				logger.error("患者医嘱信息同步到业务库异常。。。。。。。。。。。。。。。。。");
				logger.error(arg6.getMessage());
			}
		}

	}

	private void Y() {
      logger.info("开始患者送检标本信息同步到业务库。。。。。。。。。。。。。。。。。");
      JkPatientLabItems searchJkPatientLabItems = new JkPatientLabItems();
      searchJkPatientLabItems.setUpdFlag(new Long(1L));
      searchJkPatientLabItems.setUpdTime(f.a(f.getCurDate(), -this.tF.intValue()));
      searchJkPatientLabItems.setPage(Integer.valueOf(1));
      searchJkPatientLabItems.setSize(Integer.valueOf(1000));
      int index = 0;

      int count;
      do {
         ++index;
         if("1".equals(this.a(com.nis.comm.enums.ab.hW))) {
            logger.info("暂停送检标本信息同步到业务库。。。。。。。。。。。。。。。。。");
            this.b(com.nis.comm.enums.ab.hW);
            break;
         }

         count = this.ti.findJkPatientLabItemsCount(searchJkPatientLabItems);
         List jkPatientLabItemsList = this.ti.findJkPatientLabItems(searchJkPatientLabItems);
         List result = m.b(jkPatientLabItemsList, 10);
         ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

         for(int e = 0; e < result.size(); ++e) {
            List zyidList = (List)result.get(e);
            fixedThreadPool.execute(new 14(this, zyidList));
         }

         fixedThreadPool.shutdown();

         while(!fixedThreadPool.isTerminated()) {
            try {
               Thread.sleep(1000L);
            } catch (InterruptedException arg8) {
               arg8.printStackTrace();
            }
         }
      } while(count >= 1000 && index <= 1000);

      this.c(com.nis.comm.enums.ab.hW);
      logger.info("完成送检标本信息同步到业务库。。。。。。。。。。。。。。。。。");
   }

	private void H(List<JkPatientLabItems> jkPatientLabItemsList) {
		Iterator arg2 = jkPatientLabItemsList.iterator();

		while (arg2.hasNext()) {
			JkPatientLabItems jkPatientLabItems = (JkPatientLabItems) arg2.next();
			boolean st009Flag = false;

			try {
				St009Sjbb ex = this.tA.get(jkPatientLabItems.getId());
				if (ex == null) {
					st009Flag = true;
					ex = new St009Sjbb();
					ex.setCreateAt(f.getCurDate());
				}

				ex.setId(jkPatientLabItems.getId());
				ex.setHospId(this.hospId);
				ex.setMzid(jkPatientLabItems.getMzid());
				ex.setZyid(jkPatientLabItems.getZyid());
				ex.setPatientId(jkPatientLabItems.getPatientId());
				St003Cryxxb zg002Byks;
				if (StringUtils.isNotBlank(jkPatientLabItems.getZyid())) {
					zg002Byks = this.tr.get(jkPatientLabItems.getZyid());
					if (zg002Byks != null) {
						ex.setPatientId(zg002Byks.getPatientId());
						ex.setVisitId(zg002Byks.getVisitId());
						ex.setSex(zg002Byks.getSex());
						ex.setAge(zg002Byks.getAge());
						ex.setAgeUnit(zg002Byks.getAgeUnit());
					}
				}

				if (StringUtils.isBlank(ex.getZyid()) && StringUtils.isNotBlank(ex.getPatientId())) {
					zg002Byks = this.tr.getLastSt003CryxxbByPatientId(ex.getPatientId());
					if (zg002Byks != null) {
						ex.setZyid(zg002Byks.getZyid());
						ex.setPatientId(zg002Byks.getPatientId());
						ex.setVisitId(zg002Byks.getVisitId());
						ex.setSex(zg002Byks.getSex());
						ex.setAge(zg002Byks.getAge());
						ex.setAgeUnit(zg002Byks.getAgeUnit());
					}
				}

				if (StringUtils.isNotBlank(jkPatientLabItems.getItemType())
						&& r.isNumber(jkPatientLabItems.getItemType())) {
					ex.setItemType(new Integer(jkPatientLabItems.getItemType()));
				}

				ex.setItemTypeName(jkPatientLabItems.getItemTypeName());
				ex.setItemCode(jkPatientLabItems.getItemCode());
				ex.setItemName(jkPatientLabItems.getItemName());
				ex.setTestOrderNo(jkPatientLabItems.getTestOrderNo());
				if (StringUtils.isNotBlank(jkPatientLabItems.getDeptId())) {
					ex.setDeptId(jkPatientLabItems.getDeptId());
				}

				if (StringUtils.isNotBlank(jkPatientLabItems.getDeptName())) {
					ex.setDeptName(jkPatientLabItems.getDeptName());
				} else if (StringUtils.isNotBlank(jkPatientLabItems.getDeptId())) {
					Zg002Byks zg002Byks1 = this.tm.get(jkPatientLabItems.getDeptId());
					if (zg002Byks1 != null) {
						ex.setDeptName(zg002Byks1.getDeptName());
					}
				}

				ex.setSubmiAt(jkPatientLabItems.getSubmitAt());
				ex.setUpdDate(f.getCurDate());
				if (st009Flag) {
					this.tA.save(ex);
				} else {
					this.tA.update(ex);
				}

				jkPatientLabItems.setUpdFlag(new Long(0L));
				jkPatientLabItems.setUpdTime(this.now);
				this.ti.updateFlag(jkPatientLabItems);
			} catch (Exception arg6) {
				arg6.printStackTrace();
				this.result = f.g(this.now);
				this.sQ.b("患者送检标本信息同步到业务库异常：\n" + jkPatientLabItems.toString() + "\n" + arg6.getMessage(), this.now);
				logger.error("患者送检标本信息同步到业务库异常。。。。。。。。。。。。。。。。。");
				logger.error(arg6.getMessage());
			}
		}

	}

	private void Z() {
      logger.info("开始患者检出菌结果信息同步到业务库。。。。。。。。。。。。。。。。。");
      JkPatientLabPatho searchJkPatientLabPatho = new JkPatientLabPatho();
      searchJkPatientLabPatho.setUpdFlag(new Long(1L));
      searchJkPatientLabPatho.setUpdTime(f.a(f.getCurDate(), -this.tF.intValue()));
      searchJkPatientLabPatho.setPage(Integer.valueOf(1));
      searchJkPatientLabPatho.setSize(Integer.valueOf(1000));
      int index = 0;

      int count;
      do {
         ++index;
         if("1".equals(this.a(com.nis.comm.enums.ab.hY))) {
            logger.info("暂停检出菌信息同步到业务库。。。。。。。。。。。。。。。。。");
            this.b(com.nis.comm.enums.ab.hY);
            break;
         }

         count = this.tj.findJkPatientLabPathoCount(searchJkPatientLabPatho);
         List jkPatientLabPathoList = this.tj.findJkPatientLabPatho(searchJkPatientLabPatho);
         List result = m.b(jkPatientLabPathoList, 10);
         ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

         for(int e = 0; e < result.size(); ++e) {
            List zyidList = (List)result.get(e);
            fixedThreadPool.execute(new 15(this, zyidList));
         }

         fixedThreadPool.shutdown();

         while(!fixedThreadPool.isTerminated()) {
            try {
               Thread.sleep(1000L);
            } catch (InterruptedException arg8) {
               arg8.printStackTrace();
            }
         }
      } while(count >= 1000 && index <= 1000);

      this.c(com.nis.comm.enums.ab.hY);
      logger.info("完成检出菌信息同步到业务库。。。。。。。。。。。。。。。。。");
   }

	private void I(List<JkPatientLabPatho> jkPatientLabPathoList) {
		Iterator arg2 = jkPatientLabPathoList.iterator();

		while (arg2.hasNext()) {
			JkPatientLabPatho jkPatientLabPatho = (JkPatientLabPatho) arg2.next();
			boolean st010Flag = false;

			try {
				St010Jcbyt ex = this.tB.get(jkPatientLabPatho.getId());
				if (ex == null) {
					st010Flag = true;
					ex = new St010Jcbyt();
					ex.setCreateAt(f.getCurDate());
				}

				ex.setId(jkPatientLabPatho.getId());
				ex.setZyid(jkPatientLabPatho.getZyid());
				ex.setPatientId(jkPatientLabPatho.getPatientId());
				St003Cryxxb st003Cryxxb;
				if (StringUtils.isNotBlank(jkPatientLabPatho.getZyid())) {
					st003Cryxxb = this.tr.get(jkPatientLabPatho.getZyid());
					if (st003Cryxxb != null) {
						ex.setPatientId(st003Cryxxb.getPatientId());
					}
				}

				if (StringUtils.isBlank(ex.getZyid()) && StringUtils.isNotBlank(ex.getPatientId())) {
					st003Cryxxb = this.tr.getLastSt003CryxxbByPatientId(ex.getPatientId());
					if (st003Cryxxb != null) {
						ex.setZyid(st003Cryxxb.getZyid());
					}
				}

				ex.setTestOrderNo(jkPatientLabPatho.getTestOrderNo());
				ex.setTestDate(jkPatientLabPatho.getTestDate());
				if (ab.isNotEmpty(jkPatientLabPatho.getIsyang()) && r.isNumber(jkPatientLabPatho.getIsyang())) {
					ex.setIsyang(new Integer(jkPatientLabPatho.getIsyang()));
				}

				ex.setPathoCode(jkPatientLabPatho.getPathoCode());
				ex.setPathoName(jkPatientLabPatho.getPathoName());
				if (ab.isNotEmpty(jkPatientLabPatho.getIsyang()) && r.isNumber(jkPatientLabPatho.getIsyang())) {
					ex.setIsym(new Integer(jkPatientLabPatho.getIsym()));
				}

				ex.setYaominNo(jkPatientLabPatho.getYaominNo());
				ex.setPathogenSn(jkPatientLabPatho.getPathogenSn());
				ex.setMemo(jkPatientLabPatho.getMemo());
				ex.setUpdDate(f.getCurDate());
				if (st010Flag) {
					this.tB.save(ex);
				} else {
					this.tB.update(ex);
				}

				jkPatientLabPatho.setUpdFlag(new Long(0L));
				jkPatientLabPatho.setUpdTime(this.now);
				this.tj.updateFlag(jkPatientLabPatho);
			} catch (Exception arg6) {
				arg6.printStackTrace();
				this.result = f.g(this.now);
				this.sQ.b("患者检出菌信息同步到业务库异常：\n" + jkPatientLabPatho.toString() + "\n" + arg6.getMessage(), this.now);
				logger.error("患者检出菌信息同步到业务库异常。。。。。。。。。。。。。。。。。");
				logger.error(arg6.getMessage());
			}
		}

	}

	private void aa() {
      logger.info("开始患者检验、药敏结果信息同步到业务库。。。。。。。。。。。。。。。。。");
      JkPatientLabAnti searchJkPatientLabAnti = new JkPatientLabAnti();
      searchJkPatientLabAnti.setUpdFlag(new Long(1L));
      searchJkPatientLabAnti.setUpdTime(f.a(f.getCurDate(), -this.tF.intValue()));
      searchJkPatientLabAnti.setPage(Integer.valueOf(1));
      searchJkPatientLabAnti.setSize(Integer.valueOf(1000));
      int index = 0;

      int count;
      do {
         ++index;
         if("1".equals(this.a(com.nis.comm.enums.ab.hX))) {
            logger.info("暂停患者检验、药敏结果同步到业务库。。。。。。。。。。。。。。。。。");
            this.b(com.nis.comm.enums.ab.hX);
            return;
         }

         count = this.tk.findJkPatientLabAntiCount(searchJkPatientLabAnti);
         List jkPatientLabAntiList = this.tk.findJkPatientLabAnti(searchJkPatientLabAnti);
         List result = m.b(jkPatientLabAntiList, 10);
         ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

         for(int e = 0; e < result.size(); ++e) {
            List zyidList = (List)result.get(e);
            fixedThreadPool.execute(new 16(this, zyidList));
         }

         fixedThreadPool.shutdown();

         while(!fixedThreadPool.isTerminated()) {
            try {
               Thread.sleep(1000L);
            } catch (InterruptedException arg8) {
               arg8.printStackTrace();
            }
         }
      } while(count >= 1000 && index <= 1000);

      this.c(com.nis.comm.enums.ab.hX);
      logger.info("完成患者检验、药敏结果信息同步到业务库。。。。。。。。。。。。。。。。。");
   }

	private void J(List<JkPatientLabAnti> jkPatientLabAntiList) {
		Iterator arg2 = jkPatientLabAntiList.iterator();

		while (arg2.hasNext()) {
			JkPatientLabAnti jkPatientLabAnti = (JkPatientLabAnti) arg2.next();
			boolean st011Flag = false;

			try {
				St011Syjgb ex = this.tC.get(jkPatientLabAnti.getId());
				if (ex == null) {
					st011Flag = true;
					ex = new St011Syjgb();
					ex.setCreateAt(f.getCurDate());
				}

				ex.setId(jkPatientLabAnti.getId());
				ex.setMzid(jkPatientLabAnti.getMzid());
				ex.setZyid(jkPatientLabAnti.getZyid());
				ex.setPatientId(jkPatientLabAnti.getPatientId());
				ex.setVisitId(jkPatientLabAnti.getVisitId());
				St003Cryxxb st003Cryxxb;
				if (StringUtils.isNotBlank(jkPatientLabAnti.getZyid())) {
					st003Cryxxb = this.tr.get(jkPatientLabAnti.getZyid());
					if (st003Cryxxb != null) {
						ex.setPatientId(st003Cryxxb.getPatientId());
						ex.setVisitId(st003Cryxxb.getVisitId());
					}
				}

				if (StringUtils.isBlank(ex.getZyid()) && StringUtils.isNotBlank(ex.getPatientId())) {
					st003Cryxxb = this.tr.getLastSt003CryxxbByPatientId(ex.getPatientId());
					if (st003Cryxxb != null) {
						ex.setZyid(st003Cryxxb.getZyid());
					}
				}

				ex.setTestOrderNo(jkPatientLabAnti.getTestOrderNo());
				ex.setAntiCode(jkPatientLabAnti.getAntiCode());
				ex.setAntiName(jkPatientLabAnti.getAntiName());
				ex.setYaominResult(jkPatientLabAnti.getMicResult());
				ex.setTestResult(jkPatientLabAnti.getTestResult());
				ex.setUnit(jkPatientLabAnti.getUnit());
				ex.setReferRange(jkPatientLabAnti.getReferRange());
				ex.setRemark(jkPatientLabAnti.getRemark());
				ex.setPathogenSn(jkPatientLabAnti.getPathogenSn());
				if (ab.isNotEmpty(jkPatientLabAnti.getMicResult())) {
					ex.setIsym(Integer.valueOf(1));
				} else {
					ex.setIsym(Integer.valueOf(0));
				}

				if (jkPatientLabAnti.getResultDate() != null) {
					ex.setResultDate(f.g(jkPatientLabAnti.getResultDate()));
				}

				ex.setUpdDate(f.getCurDate());
				if (st011Flag) {
					this.tC.save(ex);
				} else {
					this.tC.update(ex);
				}

				jkPatientLabAnti.setUpdFlag(new Long(0L));
				jkPatientLabAnti.setUpdTime(this.now);
				this.tk.updateFlag(jkPatientLabAnti);
			} catch (Exception arg6) {
				arg6.printStackTrace();
				this.result = f.g(this.now);
				this.sQ.b("患者检验、药敏结果信息同步到业务库异常：\n" + jkPatientLabAnti.toString() + "\n" + arg6.getMessage(), this.now);
				logger.error("患者检验、药敏结果信息同步到业务库异常。。。。。。。。。。。。。。。。。");
				logger.error(arg6.getMessage());
			}
		}

	}

	public MyPage<JkSyncLog> a(JkSyncLog jkSyncLog) {
		int total = this.sT.findJkSyncLogCount(jkSyncLog);
		List data = null;
		if (total > 0) {
			data = this.sT.findJkSyncLog(jkSyncLog);
		}

		return new MyPage(jkSyncLog.getPage().intValue(), jkSyncLog.getSize().intValue(), total, data);
	}

	private String a(com.nis.comm.enums.ab jkTables) {
		return ((JkSyncLog) tG.get(jkTables.getCode())).getStop();
	}

	private void b(com.nis.comm.enums.ab jkTables) {
	}

	private void c(com.nis.comm.enums.ab jkTables) {
		JkSyncLog jkSyncLog = (JkSyncLog) tG.get(jkTables.getCode());
		jkSyncLog.setSyncStatus("1");
		tG.put(jkTables.getCode(), jkSyncLog);
	}

	public void bA(String table) {
		JkSyncLog jkSyncLog = (JkSyncLog) tG.get(table);
		if (!"1".equals(jkSyncLog.getSyncStatus())) {
			jkSyncLog.setStop("1");
			tG.put(table, jkSyncLog);
		}
	}
}