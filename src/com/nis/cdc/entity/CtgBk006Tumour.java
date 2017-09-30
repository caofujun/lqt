package com.nis.cdc.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class CtgBk006Tumour extends BaseEntity implements Serializable {
	private String masterid;
	private String cardType;
	private String cardid;
	private String icd10Id;
	private String icd10Name;
	private String icd0Id;
	private String icd0Name;
	private String mzid;
	private String zyid;
	private String patientName;
	private String idNo;
	private String sex;
	private Date birthday;
	private Long age;
	private String ageUnit;
	private String nation;
	private String marriage;
	private String profession;
	private String education;
	private String tel;
	private String kinsfolkTel;
	private String unit;
	private String registerareacode;
	private String registerareaaddr;
	private String registeraddrVillage;
	private String registeraddrGroup;
	private String nowaddrareacode;
	private String nowaddr;
	private String nowaddrVillage;
	private String nowaddrGroup;
	private Date inHospitalDt;
	private Date startDt;
	private String cureWay;
	private String cureHospital;
	private Date operationDt;
	private String diseaseCheck;
	private String diagnosisPart;
	private String oldDiagnose;
	private Date oldDiagnosedDate;
	private String diagnose;
	private String pathologyName;
	private String pathologyId;
	private String timeT;
	private String timeN;
	private String timeM;
	private String clinicalStages;
	private Date diagnosedDate;
	private String diagnosedUnit;
	private String diagnosticBasis;
	private Date deathdate;
	private String deathCauses;
	private String deathAddr;
	private String reportdeptid;
	private String reportdeptname;
	private String reportdoctorid;
	private String reportdoctorname;
	private Date reportdt;
	private Long flag;
	private String validpersonid;
	private String validpersonname;
	private Date validdt;
	private Integer isemptycard;
	private String delreason;
	private String registerfulladdr;
	private String nowfulladdr;
	private Long isprint;

	public Long getIsprint() {
		return this.isprint;
	}

	public void setIsprint(Long isprint) {
		this.isprint = isprint;
	}

	public String getRegisterfulladdr() {
		return this.registerfulladdr;
	}

	public void setRegisterfulladdr(String registerfulladdr) {
		this.registerfulladdr = registerfulladdr;
	}

	public String getNowfulladdr() {
		return this.nowfulladdr;
	}

	public void setNowfulladdr(String nowfulladdr) {
		this.nowfulladdr = nowfulladdr;
	}

	public String getDelreason() {
		return this.delreason;
	}

	public void setDelreason(String delreason) {
		this.delreason = delreason;
	}

	public Integer getIsemptycard() {
		return this.isemptycard;
	}

	public void setIsemptycard(Integer isemptycard) {
		this.isemptycard = isemptycard;
	}

	public String getMasterid() {
		return this.masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}

	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardid() {
		return this.cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getIcd10Id() {
		return this.icd10Id;
	}

	public void setIcd10Id(String icd10Id) {
		this.icd10Id = icd10Id;
	}

	public String getIcd10Name() {
		return this.icd10Name;
	}

	public void setIcd10Name(String icd10Name) {
		this.icd10Name = icd10Name;
	}

	public String getIcd0Id() {
		return this.icd0Id;
	}

	public void setIcd0Id(String icd0Id) {
		this.icd0Id = icd0Id;
	}

	public String getIcd0Name() {
		return this.icd0Name;
	}

	public void setIcd0Name(String icd0Name) {
		this.icd0Name = icd0Name;
	}

	public String getMzid() {
		return this.mzid;
	}

	public void setMzid(String mzid) {
		this.mzid = mzid;
	}

	public String getZyid() {
		return this.zyid;
	}

	public void setZyid(String zyid) {
		this.zyid = zyid;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Long getAge() {
		return this.age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getAgeUnit() {
		return this.ageUnit;
	}

	public void setAgeUnit(String ageUnit) {
		this.ageUnit = ageUnit;
	}

	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getMarriage() {
		return this.marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public String getProfession() {
		return this.profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getKinsfolkTel() {
		return this.kinsfolkTel;
	}

	public void setKinsfolkTel(String kinsfolkTel) {
		this.kinsfolkTel = kinsfolkTel;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRegisterareacode() {
		return this.registerareacode;
	}

	public void setRegisterareacode(String registerareacode) {
		this.registerareacode = registerareacode;
	}

	public String getRegisterareaaddr() {
		return this.registerareaaddr;
	}

	public void setRegisterareaaddr(String registerareaaddr) {
		this.registerareaaddr = registerareaaddr;
	}

	public String getRegisteraddrVillage() {
		return this.registeraddrVillage;
	}

	public void setRegisteraddrVillage(String registeraddrVillage) {
		this.registeraddrVillage = registeraddrVillage;
	}

	public String getRegisteraddrGroup() {
		return this.registeraddrGroup;
	}

	public void setRegisteraddrGroup(String registeraddrGroup) {
		this.registeraddrGroup = registeraddrGroup;
	}

	public String getNowaddrareacode() {
		return this.nowaddrareacode;
	}

	public void setNowaddrareacode(String nowaddrareacode) {
		this.nowaddrareacode = nowaddrareacode;
	}

	public String getNowaddr() {
		return this.nowaddr;
	}

	public void setNowaddr(String nowaddr) {
		this.nowaddr = nowaddr;
	}

	public String getNowaddrVillage() {
		return this.nowaddrVillage;
	}

	public void setNowaddrVillage(String nowaddrVillage) {
		this.nowaddrVillage = nowaddrVillage;
	}

	public String getNowaddrGroup() {
		return this.nowaddrGroup;
	}

	public void setNowaddrGroup(String nowaddrGroup) {
		this.nowaddrGroup = nowaddrGroup;
	}

	public Date getInHospitalDt() {
		return this.inHospitalDt;
	}

	public void setInHospitalDt(Date inHospitalDt) {
		this.inHospitalDt = inHospitalDt;
	}

	public Date getStartDt() {
		return this.startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public String getCureWay() {
		return this.cureWay;
	}

	public void setCureWay(String cureWay) {
		this.cureWay = cureWay;
	}

	public String getCureHospital() {
		return this.cureHospital;
	}

	public void setCureHospital(String cureHospital) {
		this.cureHospital = cureHospital;
	}

	public Date getOperationDt() {
		return this.operationDt;
	}

	public void setOperationDt(Date operationDt) {
		this.operationDt = operationDt;
	}

	public String getDiseaseCheck() {
		return this.diseaseCheck;
	}

	public void setDiseaseCheck(String diseaseCheck) {
		this.diseaseCheck = diseaseCheck;
	}

	public String getDiagnosisPart() {
		return this.diagnosisPart;
	}

	public void setDiagnosisPart(String diagnosisPart) {
		this.diagnosisPart = diagnosisPart;
	}

	public String getOldDiagnose() {
		return this.oldDiagnose;
	}

	public void setOldDiagnose(String oldDiagnose) {
		this.oldDiagnose = oldDiagnose;
	}

	public Date getOldDiagnosedDate() {
		return this.oldDiagnosedDate;
	}

	public void setOldDiagnosedDate(Date oldDiagnosedDate) {
		this.oldDiagnosedDate = oldDiagnosedDate;
	}

	public String getDiagnose() {
		return this.diagnose;
	}

	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}

	public String getPathologyName() {
		return this.pathologyName;
	}

	public void setPathologyName(String pathologyName) {
		this.pathologyName = pathologyName;
	}

	public String getPathologyId() {
		return this.pathologyId;
	}

	public void setPathologyId(String pathologyId) {
		this.pathologyId = pathologyId;
	}

	public String getTimeT() {
		return this.timeT;
	}

	public void setTimeT(String timeT) {
		this.timeT = timeT;
	}

	public String getTimeN() {
		return this.timeN;
	}

	public void setTimeN(String timeN) {
		this.timeN = timeN;
	}

	public String getTimeM() {
		return this.timeM;
	}

	public void setTimeM(String timeM) {
		this.timeM = timeM;
	}

	public String getClinicalStages() {
		return this.clinicalStages;
	}

	public void setClinicalStages(String clinicalStages) {
		this.clinicalStages = clinicalStages;
	}

	public Date getDiagnosedDate() {
		return this.diagnosedDate;
	}

	public void setDiagnosedDate(Date diagnosedDate) {
		this.diagnosedDate = diagnosedDate;
	}

	public String getDiagnosedUnit() {
		return this.diagnosedUnit;
	}

	public void setDiagnosedUnit(String diagnosedUnit) {
		this.diagnosedUnit = diagnosedUnit;
	}

	public String getDiagnosticBasis() {
		return this.diagnosticBasis;
	}

	public void setDiagnosticBasis(String diagnosticBasis) {
		this.diagnosticBasis = diagnosticBasis;
	}

	public Date getDeathdate() {
		return this.deathdate;
	}

	public void setDeathdate(Date deathdate) {
		this.deathdate = deathdate;
	}

	public String getDeathCauses() {
		return this.deathCauses;
	}

	public void setDeathCauses(String deathCauses) {
		this.deathCauses = deathCauses;
	}

	public String getDeathAddr() {
		return this.deathAddr;
	}

	public void setDeathAddr(String deathAddr) {
		this.deathAddr = deathAddr;
	}

	public String getReportdeptid() {
		return this.reportdeptid;
	}

	public void setReportdeptid(String reportdeptid) {
		this.reportdeptid = reportdeptid;
	}

	public String getReportdeptname() {
		return this.reportdeptname;
	}

	public void setReportdeptname(String reportdeptname) {
		this.reportdeptname = reportdeptname;
	}

	public String getReportdoctorid() {
		return this.reportdoctorid;
	}

	public void setReportdoctorid(String reportdoctorid) {
		this.reportdoctorid = reportdoctorid;
	}

	public String getReportdoctorname() {
		return this.reportdoctorname;
	}

	public void setReportdoctorname(String reportdoctorname) {
		this.reportdoctorname = reportdoctorname;
	}

	public Date getReportdt() {
		return this.reportdt;
	}

	public void setReportdt(Date reportdt) {
		this.reportdt = reportdt;
	}

	public Long getFlag() {
		return this.flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	public String getValidpersonid() {
		return this.validpersonid;
	}

	public void setValidpersonid(String validpersonid) {
		this.validpersonid = validpersonid;
	}

	public String getValidpersonname() {
		return this.validpersonname;
	}

	public void setValidpersonname(String validpersonname) {
		this.validpersonname = validpersonname;
	}

	public Date getValiddt() {
		return this.validdt;
	}

	public void setValiddt(Date validdt) {
		this.validdt = validdt;
	}
}