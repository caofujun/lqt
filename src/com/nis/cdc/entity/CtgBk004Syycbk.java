package com.nis.cdc.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class CtgBk004Syycbk extends BaseEntity implements Serializable {
	private String masterid;
	private String cardid;
	private String patientId;
	private String zyid;
	private String visitId;
	private String mzid;
	private String patientName;
	private String sex;
	private Date birthday;
	private String idType;
	private String idTypeName;
	private String id;
	private String addr;
	private String addrcode;
	private String registeraddr;
	private String registerareacode;
	private String telephone;
	private String mobilePhone;
	private Date startDate;
	private Date diagnoseDate;
	private String symptoms;
	private String symptomsOther;
	private String digestives;
	private String digestiveOther;
	private String respiratorys;
	private String respiratoryOther;
	private String cardiovasculars;
	private String cardiovascularOther;
	private String urinarys;
	private String urinaryOther;
	private String nervous;
	private String nervouOther;
	private String skins;
	private String skinOther;
	private String otherSymptom;
	private String sign;
	private String labResult;
	private String assistResult;
	private String mainDiagnosis;
	private String pathogenesis;
	private String foods;
	private String reportReason;
	private Date reportdt;
	private String reportdoctorid;
	private String reportdoctorname;
	private String reportdeptid;
	private String reportdeptname;
	private Long flag;
	private Date validdt;
	private String validpersonid;
	private String validpersonname;
	private String rejectReason;
	private String pathogenesisOther;
	private Integer age;
	private String ageUnit;
	private Integer isemptycard;
	private Long isprint;

	public Long getIsprint() {
		return this.isprint;
	}

	public void setIsprint(Long isprint) {
		this.isprint = isprint;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAgeUnit() {
		return this.ageUnit;
	}

	public void setAgeUnit(String ageUnit) {
		this.ageUnit = ageUnit;
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

	public String getCardid() {
		return this.cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
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

	public String getVisitId() {
		return this.visitId;
	}

	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}

	public String getMzid() {
		return this.mzid;
	}

	public void setMzid(String mzid) {
		this.mzid = mzid;
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

	public String getIdType() {
		return this.idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdTypeName() {
		return this.idTypeName;
	}

	public void setIdTypeName(String idTypeName) {
		this.idTypeName = idTypeName;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAddrcode() {
		return this.addrcode;
	}

	public void setAddrcode(String addrcode) {
		this.addrcode = addrcode;
	}

	public String getRegisteraddr() {
		return this.registeraddr;
	}

	public void setRegisteraddr(String registeraddr) {
		this.registeraddr = registeraddr;
	}

	public String getRegisterareacode() {
		return this.registerareacode;
	}

	public void setRegisterareacode(String registerareacode) {
		this.registerareacode = registerareacode;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getDiagnoseDate() {
		return this.diagnoseDate;
	}

	public void setDiagnoseDate(Date diagnoseDate) {
		this.diagnoseDate = diagnoseDate;
	}

	public String getSymptoms() {
		return this.symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public String getSymptomsOther() {
		return this.symptomsOther;
	}

	public void setSymptomsOther(String symptomsOther) {
		this.symptomsOther = symptomsOther;
	}

	public String getDigestives() {
		return this.digestives;
	}

	public void setDigestives(String digestives) {
		this.digestives = digestives;
	}

	public String getDigestiveOther() {
		return this.digestiveOther;
	}

	public void setDigestiveOther(String digestiveOther) {
		this.digestiveOther = digestiveOther;
	}

	public String getRespiratorys() {
		return this.respiratorys;
	}

	public void setRespiratorys(String respiratorys) {
		this.respiratorys = respiratorys;
	}

	public String getRespiratoryOther() {
		return this.respiratoryOther;
	}

	public void setRespiratoryOther(String respiratoryOther) {
		this.respiratoryOther = respiratoryOther;
	}

	public String getCardiovasculars() {
		return this.cardiovasculars;
	}

	public void setCardiovasculars(String cardiovasculars) {
		this.cardiovasculars = cardiovasculars;
	}

	public String getCardiovascularOther() {
		return this.cardiovascularOther;
	}

	public void setCardiovascularOther(String cardiovascularOther) {
		this.cardiovascularOther = cardiovascularOther;
	}

	public String getUrinarys() {
		return this.urinarys;
	}

	public void setUrinarys(String urinarys) {
		this.urinarys = urinarys;
	}

	public String getUrinaryOther() {
		return this.urinaryOther;
	}

	public void setUrinaryOther(String urinaryOther) {
		this.urinaryOther = urinaryOther;
	}

	public String getNervous() {
		return this.nervous;
	}

	public void setNervous(String nervous) {
		this.nervous = nervous;
	}

	public String getNervouOther() {
		return this.nervouOther;
	}

	public void setNervouOther(String nervouOther) {
		this.nervouOther = nervouOther;
	}

	public String getSkins() {
		return this.skins;
	}

	public void setSkins(String skins) {
		this.skins = skins;
	}

	public String getSkinOther() {
		return this.skinOther;
	}

	public void setSkinOther(String skinOther) {
		this.skinOther = skinOther;
	}

	public String getOtherSymptom() {
		return this.otherSymptom;
	}

	public void setOtherSymptom(String otherSymptom) {
		this.otherSymptom = otherSymptom;
	}

	public String getSign() {
		return this.sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getLabResult() {
		return this.labResult;
	}

	public void setLabResult(String labResult) {
		this.labResult = labResult;
	}

	public String getAssistResult() {
		return this.assistResult;
	}

	public void setAssistResult(String assistResult) {
		this.assistResult = assistResult;
	}

	public String getMainDiagnosis() {
		return this.mainDiagnosis;
	}

	public void setMainDiagnosis(String mainDiagnosis) {
		this.mainDiagnosis = mainDiagnosis;
	}

	public String getPathogenesis() {
		return this.pathogenesis;
	}

	public void setPathogenesis(String pathogenesis) {
		this.pathogenesis = pathogenesis;
	}

	public String getFoods() {
		return this.foods;
	}

	public void setFoods(String foods) {
		this.foods = foods;
	}

	public String getReportReason() {
		return this.reportReason;
	}

	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
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

	public String getPathogenesisOther() {
		return this.pathogenesisOther;
	}

	public void setPathogenesisOther(String pathogenesisOther) {
		this.pathogenesisOther = pathogenesisOther;
	}
}