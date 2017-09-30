package com.nis.cdc.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class CtgBk009Sunstroke extends BaseEntity implements Serializable {
	private String masterid;
	private String reportType;
	private String cardId;
	private String patientId;
	private String zyid;
	private String mzid;
	private String visitId;
	private String patientName;
	private String idNo;
	private String sex;
	private Date birthday;
	private Long age;
	private String ageUnit;
	private String nowaddrareacode;
	private String nowaddr;
	private String nowaddrVillage;
	private String nowaddrGroup;
	private String sunstrokeDiagnosis;
	private String patientStatus;
	private Date sunstrokeDt;
	private String diagnosisUnit;
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
	private int isemptycard;
	private int isprint;

	public int getIsprint() {
		return this.isprint;
	}

	public void setIsprint(int isprint) {
		this.isprint = isprint;
	}

	public String getMasterid() {
		return this.masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}

	public String getReportType() {
		return this.reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
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

	public String getSunstrokeDiagnosis() {
		return this.sunstrokeDiagnosis;
	}

	public void setSunstrokeDiagnosis(String sunstrokeDiagnosis) {
		this.sunstrokeDiagnosis = sunstrokeDiagnosis;
	}

	public String getPatientStatus() {
		return this.patientStatus;
	}

	public void setPatientStatus(String patientStatus) {
		this.patientStatus = patientStatus;
	}

	public Date getSunstrokeDt() {
		return this.sunstrokeDt;
	}

	public void setSunstrokeDt(Date sunstrokeDt) {
		this.sunstrokeDt = sunstrokeDt;
	}

	public String getDiagnosisUnit() {
		return this.diagnosisUnit;
	}

	public void setDiagnosisUnit(String diagnosisUnit) {
		this.diagnosisUnit = diagnosisUnit;
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

	public int getIsemptycard() {
		return this.isemptycard;
	}

	public void setIsemptycard(int isemptycard) {
		this.isemptycard = isemptycard;
	}
}