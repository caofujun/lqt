package com.nis.cdc.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class CtgBk010Pesticide extends BaseEntity implements Serializable {
	private String masterid;
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
	private String tel;
	private String nowaddrareacode;
	private String nowaddr;
	private String nowaddrVillage;
	private String nowaddrGroup;
	private String poisoningNum;
	private String pesticideList;
	private String pesticideKind;
	private String kindOther;
	private String poisoningReason;
	private String train;
	private String drugWay;
	private String riskBehavior;
	private String outcome;
	private String outcomeOther;
	private Date diagnoseDt;
	private Date deadDt;
	private String diagnoseUnit;
	private String unitLeader;
	private Date reportdt;
	private String reportdoctorid;
	private String reportdoctorname;
	private String reportdeptid;
	private String reportdeptname;
	private String reportTel;
	private Long flag;
	private Date validdt;
	private String validpersonid;
	private String validpersonname;
	private String rejectReason;
	private Integer isemptycard;
	private Integer isprint;

	public Integer getIsemptycard() {
		return this.isemptycard;
	}

	public void setIsemptycard(Integer isemptycard) {
		this.isemptycard = isemptycard;
	}

	public Integer getIsprint() {
		return this.isprint;
	}

	public void setIsprint(Integer isprint) {
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

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
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

	public String getPoisoningNum() {
		return this.poisoningNum;
	}

	public void setPoisoningNum(String poisoningNum) {
		this.poisoningNum = poisoningNum;
	}

	public String getPesticideList() {
		return this.pesticideList;
	}

	public void setPesticideList(String pesticideList) {
		this.pesticideList = pesticideList;
	}

	public String getPesticideKind() {
		return this.pesticideKind;
	}

	public void setPesticideKind(String pesticideKind) {
		this.pesticideKind = pesticideKind;
	}

	public String getKindOther() {
		return this.kindOther;
	}

	public void setKindOther(String kindOther) {
		this.kindOther = kindOther;
	}

	public String getPoisoningReason() {
		return this.poisoningReason;
	}

	public void setPoisoningReason(String poisoningReason) {
		this.poisoningReason = poisoningReason;
	}

	public String getTrain() {
		return this.train;
	}

	public void setTrain(String train) {
		this.train = train;
	}

	public String getDrugWay() {
		return this.drugWay;
	}

	public void setDrugWay(String drugWay) {
		this.drugWay = drugWay;
	}

	public String getRiskBehavior() {
		return this.riskBehavior;
	}

	public void setRiskBehavior(String riskBehavior) {
		this.riskBehavior = riskBehavior;
	}

	public String getOutcome() {
		return this.outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public String getOutcomeOther() {
		return this.outcomeOther;
	}

	public void setOutcomeOther(String outcomeOther) {
		this.outcomeOther = outcomeOther;
	}

	public Date getDiagnoseDt() {
		return this.diagnoseDt;
	}

	public void setDiagnoseDt(Date diagnoseDt) {
		this.diagnoseDt = diagnoseDt;
	}

	public Date getDeadDt() {
		return this.deadDt;
	}

	public void setDeadDt(Date deadDt) {
		this.deadDt = deadDt;
	}

	public String getDiagnoseUnit() {
		return this.diagnoseUnit;
	}

	public void setDiagnoseUnit(String diagnoseUnit) {
		this.diagnoseUnit = diagnoseUnit;
	}

	public String getUnitLeader() {
		return this.unitLeader;
	}

	public void setUnitLeader(String unitLeader) {
		this.unitLeader = unitLeader;
	}

	public Date getReportdt() {
		return this.reportdt;
	}

	public void setReportdt(Date reportdt) {
		this.reportdt = reportdt;
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

	public String getReportTel() {
		return this.reportTel;
	}

	public void setReportTel(String reportTel) {
		this.reportTel = reportTel;
	}

	public Long getFlag() {
		return this.flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	public Date getValiddt() {
		return this.validdt;
	}

	public void setValiddt(Date validdt) {
		this.validdt = validdt;
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

	public String getRejectReason() {
		return this.rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
}