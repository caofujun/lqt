package com.nis.cdc.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class CtgBk008Ccvd extends BaseEntity implements Serializable {
	private String masterid;
	private String cardId;
	private String patientId;
	private String zyid;
	private String mzid;
	private String visitId;
	private String patientName;
	private String idno;
	private String sex;
	private Date birthday;
	private Long age;
	private String ageunit;
	private String nation;
	private String profession;
	private String unit;
	private String contacts;
	private String tel;
	private String registerareacode;
	private String registerareaaddr;
	private String registeraddrVillage;
	private String registeraddrGroup;
	private String registeraddrDetail;
	private String nowaddrareacode;
	private String nowaddr;
	private String nowaddrVillage;
	private String nowaddrGroup;
	private String nowaddrDetail;
	private String icdcode;
	private String diagnosisList;
	private String diagnosisGist;
	private Date startDt;
	private Date diagnosisDt;
	private Long onsetTime;
	private Date firstDiagnosisDt;
	private String highestUnit;
	private String theresult;
	private String deadzone;
	private String deadReason;
	private Date deathDt;
	private String deadReportName;
	private String reportUnit;
	private String reportdeptid;
	private String reportdeptname;
	private String reportdoctorid;
	private String reportdoctorname;
	private Date reportdt;
	private Long flag;
	private String validpersonid;
	private String validpersonname;
	private Date validdt;
	private String delreason;
	private Long isemptycard;
	private String marriage;
	private String education;
	private Long isprint;

	public String getRegisteraddrDetail() {
		return this.registeraddrDetail;
	}

	public void setRegisteraddrDetail(String registeraddrDetail) {
		this.registeraddrDetail = registeraddrDetail;
	}

	public String getNowaddrDetail() {
		return this.nowaddrDetail;
	}

	public void setNowaddrDetail(String nowaddrDetail) {
		this.nowaddrDetail = nowaddrDetail;
	}

	public Long getIsprint() {
		return this.isprint;
	}

	public void setIsprint(Long isprint) {
		this.isprint = isprint;
	}

	public String getMasterid() {
		return this.masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}

	public String getCardId() {
		return this.cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getZyid() {
		return this.zyid;
	}

	public void setZyid(String zyid) {
		this.zyid = zyid;
	}

	public String getMzid() {
		return this.mzid;
	}

	public void setMzid(String mzid) {
		this.mzid = mzid;
	}

	public String getVisitId() {
		return this.visitId;
	}

	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
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

	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getProfession() {
		return this.profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getContacts() {
		return this.contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
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

	public String getIdno() {
		return this.idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getAgeunit() {
		return this.ageunit;
	}

	public void setAgeunit(String ageunit) {
		this.ageunit = ageunit;
	}

	public String getIcdcode() {
		return this.icdcode;
	}

	public void setIcdcode(String icdcode) {
		this.icdcode = icdcode;
	}

	public String getTheresult() {
		return this.theresult;
	}

	public void setTheresult(String theresult) {
		this.theresult = theresult;
	}

	public String getDiagnosisList() {
		return this.diagnosisList;
	}

	public void setDiagnosisList(String diagnosisList) {
		this.diagnosisList = diagnosisList;
	}

	public String getDiagnosisGist() {
		return this.diagnosisGist;
	}

	public void setDiagnosisGist(String diagnosisGist) {
		this.diagnosisGist = diagnosisGist;
	}

	public Date getStartDt() {
		return this.startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public Date getDiagnosisDt() {
		return this.diagnosisDt;
	}

	public void setDiagnosisDt(Date diagnosisDt) {
		this.diagnosisDt = diagnosisDt;
	}

	public Long getOnsetTime() {
		return this.onsetTime;
	}

	public void setOnsetTime(Long onsetTime) {
		this.onsetTime = onsetTime;
	}

	public Date getFirstDiagnosisDt() {
		return this.firstDiagnosisDt;
	}

	public void setFirstDiagnosisDt(Date firstDiagnosisDt) {
		this.firstDiagnosisDt = firstDiagnosisDt;
	}

	public String getHighestUnit() {
		return this.highestUnit;
	}

	public void setHighestUnit(String highestUnit) {
		this.highestUnit = highestUnit;
	}

	public String getDeadzone() {
		return this.deadzone;
	}

	public void setDeadzone(String deadzone) {
		this.deadzone = deadzone;
	}

	public String getDeadReason() {
		return this.deadReason;
	}

	public void setDeadReason(String deadReason) {
		this.deadReason = deadReason;
	}

	public Date getDeathDt() {
		return this.deathDt;
	}

	public void setDeathDt(Date deathDt) {
		this.deathDt = deathDt;
	}

	public String getDeadReportName() {
		return this.deadReportName;
	}

	public void setDeadReportName(String deadReportName) {
		this.deadReportName = deadReportName;
	}

	public String getReportUnit() {
		return this.reportUnit;
	}

	public void setReportUnit(String reportUnit) {
		this.reportUnit = reportUnit;
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

	public String getDelreason() {
		return this.delreason;
	}

	public void setDelreason(String delreason) {
		this.delreason = delreason;
	}

	public Long getIsemptycard() {
		return this.isemptycard;
	}

	public void setIsemptycard(Long isemptycard) {
		this.isemptycard = isemptycard;
	}

	public String getMarriage() {
		return this.marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}
}