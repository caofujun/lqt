package com.nis.cdc.entity;

import com.nis.cdc.entity.CtgBk005Blxx;
import com.nis.cdc.entity.CtgBk005Cyxx;
import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CtgBk005Syjc extends BaseEntity implements Serializable {
	private String masterid;
	private String cardid;
	private String patientId;
	private String zyid;
	private String visitId;
	private String mzid;
	private String isinhospital;
	private String patientName;
	private String sexid;
	private String sexname;
	private String parentName;
	private String idType;
	private String idTypeName;
	private String id;
	private Date birthday;
	private Long age;
	private String ageUnit;
	private String unit;
	private String telp;
	private String telh;
	private String areatypeId;
	private String areatypeName;
	private String addr;
	private String addrcode;
	private String addrcodevalue;
	private String registerareatypeId;
	private String registerareatypeName;
	private String registerarea;
	private String registerareacode;
	private String registerareavalue;
	private String profession;
	private Date startDate;
	private Date diagnoseDate;
	private Date deaddate;
	private String symptoms;
	private String symptomsFr;
	private String symptomsOther;
	private String digestives;
	private String digestiveOt;
	private String digestiveFx;
	private String digestiveFxxz;
	private String digestiveOther;
	private String respiratorys;
	private String respiratoryOther;
	private String cardiovasculars;
	private String cardiovascularOther;
	private String urinarys;
	private String urinaryOther;
	private String nervous;
	private String nervouYc;
	private String nervouOther;
	private String skins;
	private String skinOther;
	private String isusedantibiotic;
	private String antibiotic;
	private String initdiagnosis;
	private String initdiagnosisOther;
	private String previoushistory;
	private String previoushistoryOther;
	private String isfoodcaused;
	private String isbiologicalsamples;
	private Date reportdt;
	private Date savedt;
	private String reportdoctorid;
	private String reportdoctorname;
	private Long flag;
	private Date validDate;
	private String doctorid;
	private String doctorname;
	private String deptid;
	private String deptname;
	private String rejectReason;
	private String reportdeptid;
	private String reportdeptname;
	private String validperson;
	private String validpersonname;
	private String notes;
	private int isemptycard;
	private Integer isreturnvisit;
	private Long isprint;
	private List<CtgBk005Blxx> ctgBk005Blxx;
	private List<CtgBk005Cyxx> ctgBk005Cyxx;

	public Long getIsprint() {
		return this.isprint;
	}

	public void setIsprint(Long isprint) {
		this.isprint = isprint;
	}

	public Integer getIsreturnvisit() {
		return this.isreturnvisit;
	}

	public void setIsreturnvisit(Integer isreturnvisit) {
		this.isreturnvisit = isreturnvisit;
	}

	public Integer getIsemptycard() {
		return Integer.valueOf(this.isemptycard);
	}

	public void setIsemptycard(Integer isemptycard) {
		this.isemptycard = isemptycard.intValue();
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getValidpersonname() {
		return this.validpersonname;
	}

	public void setValidpersonname(String validpersonname) {
		this.validpersonname = validpersonname;
	}

	public String getAntibiotic() {
		return this.antibiotic;
	}

	public void setAntibiotic(String antibiotic) {
		this.antibiotic = antibiotic;
	}

	public List<CtgBk005Blxx> getCtgBk005Blxx() {
		return this.ctgBk005Blxx;
	}

	public void setCtgBk005Blxx(List<CtgBk005Blxx> ctgBk005Blxx) {
		this.ctgBk005Blxx = ctgBk005Blxx;
	}

	public List<CtgBk005Cyxx> getCtgBk005Cyxx() {
		return this.ctgBk005Cyxx;
	}

	public void setCtgBk005Cyxx(List<CtgBk005Cyxx> ctgBk005Cyxx) {
		this.ctgBk005Cyxx = ctgBk005Cyxx;
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

	public String getIsinhospital() {
		return this.isinhospital;
	}

	public void setIsinhospital(String isinhospital) {
		this.isinhospital = isinhospital;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getSexid() {
		return this.sexid;
	}

	public void setSexid(String sexid) {
		this.sexid = sexid;
	}

	public String getSexname() {
		return this.sexname;
	}

	public void setSexname(String sexname) {
		this.sexname = sexname;
	}

	public String getParentName() {
		return this.parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
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

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getTelp() {
		return this.telp;
	}

	public void setTelp(String telp) {
		this.telp = telp;
	}

	public String getTelh() {
		return this.telh;
	}

	public void setTelh(String telh) {
		this.telh = telh;
	}

	public String getAreatypeId() {
		return this.areatypeId;
	}

	public void setAreatypeId(String areatypeId) {
		this.areatypeId = areatypeId;
	}

	public String getAreatypeName() {
		return this.areatypeName;
	}

	public void setAreatypeName(String areatypeName) {
		this.areatypeName = areatypeName;
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

	public String getAddrcodevalue() {
		return this.addrcodevalue;
	}

	public void setAddrcodevalue(String addrcodevalue) {
		this.addrcodevalue = addrcodevalue;
	}

	public String getRegisterareatypeId() {
		return this.registerareatypeId;
	}

	public void setRegisterareatypeId(String registerareatypeId) {
		this.registerareatypeId = registerareatypeId;
	}

	public String getRegisterareatypeName() {
		return this.registerareatypeName;
	}

	public void setRegisterareatypeName(String registerareatypeName) {
		this.registerareatypeName = registerareatypeName;
	}

	public String getRegisterarea() {
		return this.registerarea;
	}

	public void setRegisterarea(String registerarea) {
		this.registerarea = registerarea;
	}

	public String getRegisterareacode() {
		return this.registerareacode;
	}

	public void setRegisterareacode(String registerareacode) {
		this.registerareacode = registerareacode;
	}

	public String getRegisterareavalue() {
		return this.registerareavalue;
	}

	public void setRegisterareavalue(String registerareavalue) {
		this.registerareavalue = registerareavalue;
	}

	public String getProfession() {
		return this.profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
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

	public Date getDeaddate() {
		return this.deaddate;
	}

	public void setDeaddate(Date deaddate) {
		this.deaddate = deaddate;
	}

	public String getSymptoms() {
		return this.symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public String getSymptomsFr() {
		return this.symptomsFr;
	}

	public void setSymptomsFr(String symptomsFr) {
		this.symptomsFr = symptomsFr;
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

	public String getDigestiveOt() {
		return this.digestiveOt;
	}

	public void setDigestiveOt(String digestiveOt) {
		this.digestiveOt = digestiveOt;
	}

	public String getDigestiveFx() {
		return this.digestiveFx;
	}

	public void setDigestiveFx(String digestiveFx) {
		this.digestiveFx = digestiveFx;
	}

	public String getDigestiveFxxz() {
		return this.digestiveFxxz;
	}

	public void setDigestiveFxxz(String digestiveFxxz) {
		this.digestiveFxxz = digestiveFxxz;
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

	public String getNervouYc() {
		return this.nervouYc;
	}

	public void setNervouYc(String nervouYc) {
		this.nervouYc = nervouYc;
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

	public String getIsusedantibiotic() {
		return this.isusedantibiotic;
	}

	public void setIsusedantibiotic(String isusedantibiotic) {
		this.isusedantibiotic = isusedantibiotic;
	}

	public String getInitdiagnosis() {
		return this.initdiagnosis;
	}

	public void setInitdiagnosis(String initdiagnosis) {
		this.initdiagnosis = initdiagnosis;
	}

	public String getInitdiagnosisOther() {
		return this.initdiagnosisOther;
	}

	public void setInitdiagnosisOther(String initdiagnosisOther) {
		this.initdiagnosisOther = initdiagnosisOther;
	}

	public String getPrevioushistory() {
		return this.previoushistory;
	}

	public void setPrevioushistory(String previoushistory) {
		this.previoushistory = previoushistory;
	}

	public String getPrevioushistoryOther() {
		return this.previoushistoryOther;
	}

	public void setPrevioushistoryOther(String previoushistoryOther) {
		this.previoushistoryOther = previoushistoryOther;
	}

	public String getIsfoodcaused() {
		return this.isfoodcaused;
	}

	public void setIsfoodcaused(String isfoodcaused) {
		this.isfoodcaused = isfoodcaused;
	}

	public String getIsbiologicalsamples() {
		return this.isbiologicalsamples;
	}

	public void setIsbiologicalsamples(String isbiologicalsamples) {
		this.isbiologicalsamples = isbiologicalsamples;
	}

	public Date getReportdt() {
		return this.reportdt;
	}

	public void setReportdt(Date reportdt) {
		this.reportdt = reportdt;
	}

	public Date getSavedt() {
		return this.savedt;
	}

	public void setSavedt(Date savedt) {
		this.savedt = savedt;
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

	public Long getFlag() {
		return this.flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	public Date getValidDate() {
		return this.validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public String getDoctorid() {
		return this.doctorid;
	}

	public void setDoctorid(String doctorid) {
		this.doctorid = doctorid;
	}

	public String getDoctorname() {
		return this.doctorname;
	}

	public void setDoctorname(String doctorname) {
		this.doctorname = doctorname;
	}

	public String getDeptid() {
		return this.deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getDeptname() {
		return this.deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getRejectReason() {
		return this.rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
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

	public String getValidperson() {
		return this.validperson;
	}

	public void setValidperson(String validperson) {
		this.validperson = validperson;
	}
}