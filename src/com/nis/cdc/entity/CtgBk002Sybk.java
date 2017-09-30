package com.nis.cdc.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("ctgBk002Sybk")
public class CtgBk002Sybk extends BaseEntity implements Serializable {
	private String masterid;
	private String cardid;
	private String patientId;
	private String zyid;
	private String visitId;
	private String mzid;
	private String patientName;
	private Long age;
	private String ageunit;
	private String sexid;
	private String sexname;
	private String womantypeid;
	private String womantypename;
	private String id;
	private Date birtyday;
	private String nationid;
	private String nationname;
	private String marriageid;
	private String marriagevalue;
	private String professionid;
	private String professionname;
	private String educationid;
	private String educationname;
	private String regaddrtypeid;
	private String regaddrtypename;
	private String registrationaddrcode;
	private String registrationaddrcodevalue;
	private String registrationaddr;
	private String nowaddrtypeid;
	private String nowaddrtypename;
	private String nowaddrcode;
	private String nowaddrcodevalue;
	private String nowaddr;
	private String workplace;
	private Date deaddate;
	private String deadzoneid;
	private String deadzonevalue;
	private String folkname;
	private String folktel;
	private String folkaddr;
	private String acause;
	private String aicd10;
	private Long ascopetime;
	private String ascopetimeunit;
	private String bcause;
	private String bicd10;
	private Long bscopetime;
	private String bscopetimeunit;
	private String ccause;
	private String cicd10;
	private Long cscopetime;
	private String cscopetimeunit;
	private String dcause;
	private String dicd10;
	private Long dscopetime;
	private String dscopetimeunit;
	private String otherCause;
	private String otherIcd10;
	private String basecause;
	private String bsicd10;
	private String dorglevelid;
	private String dorglevelvalue;
	private String diagnoseby;
	private String diagnosebyvalue;
	private String doctorid;
	private String doctorname;
	private String officeid;
	private String officename;
	private String notes;
	private String reportdeptid;
	private String reportdeptname;
	private String reportdoctorid;
	private String reportdoctorname;
	private Date filldate;
	private String delreason;
	private Long flag;
	private Date auditdate;
	private String auditor;
	private String idtype;
	private String idtypevalue;
	private String livingHistory;
	private String surveyName;
	private String relationDead;
	private String contactphone;
	private String contactaddr;
	private String verbalAutopsy;
	private Date dsurvey;
	private String area;
	private Long isprint;
	private Long isemptycard;
	private String auditorname;
	private String serialnumber;

	public String getSerialnumber() {
		return this.serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	public String getAuditorname() {
		return this.auditorname;
	}

	public void setAuditorname(String auditorname) {
		this.auditorname = auditorname;
	}

	public Long getIsemptycard() {
		return this.isemptycard;
	}

	public void setIsemptycard(Long isemptycard) {
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

	public Long getAge() {
		return this.age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getAgeunit() {
		return this.ageunit;
	}

	public void setAgeunit(String ageunit) {
		this.ageunit = ageunit;
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

	public String getWomantypeid() {
		return this.womantypeid;
	}

	public void setWomantypeid(String womantypeid) {
		this.womantypeid = womantypeid;
	}

	public String getWomantypename() {
		return this.womantypename;
	}

	public void setWomantypename(String womantypename) {
		this.womantypename = womantypename;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getBirtyday() {
		return this.birtyday;
	}

	public void setBirtyday(Date birtyday) {
		this.birtyday = birtyday;
	}

	public String getNationid() {
		return this.nationid;
	}

	public void setNationid(String nationid) {
		this.nationid = nationid;
	}

	public String getNationname() {
		return this.nationname;
	}

	public void setNationname(String nationname) {
		this.nationname = nationname;
	}

	public String getMarriageid() {
		return this.marriageid;
	}

	public void setMarriageid(String marriageid) {
		this.marriageid = marriageid;
	}

	public String getMarriagevalue() {
		return this.marriagevalue;
	}

	public void setMarriagevalue(String marriagevalue) {
		this.marriagevalue = marriagevalue;
	}

	public String getProfessionid() {
		return this.professionid;
	}

	public void setProfessionid(String professionid) {
		this.professionid = professionid;
	}

	public String getProfessionname() {
		return this.professionname;
	}

	public void setProfessionname(String professionname) {
		this.professionname = professionname;
	}

	public String getEducationid() {
		return this.educationid;
	}

	public void setEducationid(String educationid) {
		this.educationid = educationid;
	}

	public String getEducationname() {
		return this.educationname;
	}

	public void setEducationname(String educationname) {
		this.educationname = educationname;
	}

	public String getRegaddrtypeid() {
		return this.regaddrtypeid;
	}

	public void setRegaddrtypeid(String regaddrtypeid) {
		this.regaddrtypeid = regaddrtypeid;
	}

	public String getRegaddrtypename() {
		return this.regaddrtypename;
	}

	public void setRegaddrtypename(String regaddrtypename) {
		this.regaddrtypename = regaddrtypename;
	}

	public String getRegistrationaddrcode() {
		return this.registrationaddrcode;
	}

	public void setRegistrationaddrcode(String registrationaddrcode) {
		this.registrationaddrcode = registrationaddrcode;
	}

	public String getRegistrationaddrcodevalue() {
		return this.registrationaddrcodevalue;
	}

	public void setRegistrationaddrcodevalue(String registrationaddrcodevalue) {
		this.registrationaddrcodevalue = registrationaddrcodevalue;
	}

	public String getRegistrationaddr() {
		return this.registrationaddr;
	}

	public void setRegistrationaddr(String registrationaddr) {
		this.registrationaddr = registrationaddr;
	}

	public String getNowaddrtypeid() {
		return this.nowaddrtypeid;
	}

	public void setNowaddrtypeid(String nowaddrtypeid) {
		this.nowaddrtypeid = nowaddrtypeid;
	}

	public String getNowaddrtypename() {
		return this.nowaddrtypename;
	}

	public void setNowaddrtypename(String nowaddrtypename) {
		this.nowaddrtypename = nowaddrtypename;
	}

	public String getNowaddrcode() {
		return this.nowaddrcode;
	}

	public void setNowaddrcode(String nowaddrcode) {
		this.nowaddrcode = nowaddrcode;
	}

	public String getNowaddrcodevalue() {
		return this.nowaddrcodevalue;
	}

	public void setNowaddrcodevalue(String nowaddrcodevalue) {
		this.nowaddrcodevalue = nowaddrcodevalue;
	}

	public String getNowaddr() {
		return this.nowaddr;
	}

	public void setNowaddr(String nowaddr) {
		this.nowaddr = nowaddr;
	}

	public String getWorkplace() {
		return this.workplace;
	}

	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}

	public Date getDeaddate() {
		return this.deaddate;
	}

	public void setDeaddate(Date deaddate) {
		this.deaddate = deaddate;
	}

	public String getDeadzoneid() {
		return this.deadzoneid;
	}

	public void setDeadzoneid(String deadzoneid) {
		this.deadzoneid = deadzoneid;
	}

	public String getDeadzonevalue() {
		return this.deadzonevalue;
	}

	public void setDeadzonevalue(String deadzonevalue) {
		this.deadzonevalue = deadzonevalue;
	}

	public String getFolkname() {
		return this.folkname;
	}

	public void setFolkname(String folkname) {
		this.folkname = folkname;
	}

	public String getFolktel() {
		return this.folktel;
	}

	public void setFolktel(String folktel) {
		this.folktel = folktel;
	}

	public String getFolkaddr() {
		return this.folkaddr;
	}

	public void setFolkaddr(String folkaddr) {
		this.folkaddr = folkaddr;
	}

	public String getAcause() {
		return this.acause;
	}

	public void setAcause(String acause) {
		this.acause = acause;
	}

	public String getAicd10() {
		return this.aicd10;
	}

	public void setAicd10(String aicd10) {
		this.aicd10 = aicd10;
	}

	public Long getAscopetime() {
		return this.ascopetime;
	}

	public void setAscopetime(Long ascopetime) {
		this.ascopetime = ascopetime;
	}

	public String getAscopetimeunit() {
		return this.ascopetimeunit;
	}

	public void setAscopetimeunit(String ascopetimeunit) {
		this.ascopetimeunit = ascopetimeunit;
	}

	public String getBcause() {
		return this.bcause;
	}

	public void setBcause(String bcause) {
		this.bcause = bcause;
	}

	public String getBicd10() {
		return this.bicd10;
	}

	public void setBicd10(String bicd10) {
		this.bicd10 = bicd10;
	}

	public Long getBscopetime() {
		return this.bscopetime;
	}

	public void setBscopetime(Long bscopetime) {
		this.bscopetime = bscopetime;
	}

	public String getBscopetimeunit() {
		return this.bscopetimeunit;
	}

	public void setBscopetimeunit(String bscopetimeunit) {
		this.bscopetimeunit = bscopetimeunit;
	}

	public String getCcause() {
		return this.ccause;
	}

	public void setCcause(String ccause) {
		this.ccause = ccause;
	}

	public String getCicd10() {
		return this.cicd10;
	}

	public void setCicd10(String cicd10) {
		this.cicd10 = cicd10;
	}

	public Long getCscopetime() {
		return this.cscopetime;
	}

	public void setCscopetime(Long cscopetime) {
		this.cscopetime = cscopetime;
	}

	public String getCscopetimeunit() {
		return this.cscopetimeunit;
	}

	public void setCscopetimeunit(String cscopetimeunit) {
		this.cscopetimeunit = cscopetimeunit;
	}

	public String getDcause() {
		return this.dcause;
	}

	public void setDcause(String dcause) {
		this.dcause = dcause;
	}

	public String getDicd10() {
		return this.dicd10;
	}

	public void setDicd10(String dicd10) {
		this.dicd10 = dicd10;
	}

	public Long getDscopetime() {
		return this.dscopetime;
	}

	public void setDscopetime(Long dscopetime) {
		this.dscopetime = dscopetime;
	}

	public String getDscopetimeunit() {
		return this.dscopetimeunit;
	}

	public void setDscopetimeunit(String dscopetimeunit) {
		this.dscopetimeunit = dscopetimeunit;
	}

	public String getOtherCause() {
		return this.otherCause;
	}

	public void setOtherCause(String otherCause) {
		this.otherCause = otherCause;
	}

	public String getOtherIcd10() {
		return this.otherIcd10;
	}

	public void setOtherIcd10(String otherIcd10) {
		this.otherIcd10 = otherIcd10;
	}

	public String getBasecause() {
		return this.basecause;
	}

	public void setBasecause(String basecause) {
		this.basecause = basecause;
	}

	public String getBsicd10() {
		return this.bsicd10;
	}

	public void setBsicd10(String bsicd10) {
		this.bsicd10 = bsicd10;
	}

	public String getDorglevelid() {
		return this.dorglevelid;
	}

	public void setDorglevelid(String dorglevelid) {
		this.dorglevelid = dorglevelid;
	}

	public String getDorglevelvalue() {
		return this.dorglevelvalue;
	}

	public void setDorglevelvalue(String dorglevelvalue) {
		this.dorglevelvalue = dorglevelvalue;
	}

	public String getDiagnoseby() {
		return this.diagnoseby;
	}

	public void setDiagnoseby(String diagnoseby) {
		this.diagnoseby = diagnoseby;
	}

	public String getDiagnosebyvalue() {
		return this.diagnosebyvalue;
	}

	public void setDiagnosebyvalue(String diagnosebyvalue) {
		this.diagnosebyvalue = diagnosebyvalue;
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

	public String getOfficeid() {
		return this.officeid;
	}

	public void setOfficeid(String officeid) {
		this.officeid = officeid;
	}

	public String getOfficename() {
		return this.officename;
	}

	public void setOfficename(String officename) {
		this.officename = officename;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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

	public Date getFilldate() {
		return this.filldate;
	}

	public void setFilldate(Date filldate) {
		this.filldate = filldate;
	}

	public String getDelreason() {
		return this.delreason;
	}

	public void setDelreason(String delreason) {
		this.delreason = delreason;
	}

	public Long getFlag() {
		return this.flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	public Date getAuditdate() {
		return this.auditdate;
	}

	public void setAuditdate(Date auditdate) {
		this.auditdate = auditdate;
	}

	public String getAuditor() {
		return this.auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getIdtype() {
		return this.idtype;
	}

	public void setIdtype(String idtype) {
		this.idtype = idtype;
	}

	public String getIdtypevalue() {
		return this.idtypevalue;
	}

	public void setIdtypevalue(String idtypevalue) {
		this.idtypevalue = idtypevalue;
	}

	public String getLivingHistory() {
		return this.livingHistory;
	}

	public void setLivingHistory(String livingHistory) {
		this.livingHistory = livingHistory;
	}

	public String getSurveyName() {
		return this.surveyName;
	}

	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}

	public String getRelationDead() {
		return this.relationDead;
	}

	public void setRelationDead(String relationDead) {
		this.relationDead = relationDead;
	}

	public String getContactphone() {
		return this.contactphone;
	}

	public void setContactphone(String contactphone) {
		this.contactphone = contactphone;
	}

	public String getContactaddr() {
		return this.contactaddr;
	}

	public void setContactaddr(String contactaddr) {
		this.contactaddr = contactaddr;
	}

	public String getVerbalAutopsy() {
		return this.verbalAutopsy;
	}

	public void setVerbalAutopsy(String verbalAutopsy) {
		this.verbalAutopsy = verbalAutopsy;
	}

	public Date getDsurvey() {
		return this.dsurvey;
	}

	public void setDsurvey(Date dsurvey) {
		this.dsurvey = dsurvey;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Long getIsprint() {
		return this.isprint;
	}

	public void setIsprint(Long isprint) {
		this.isprint = isprint;
	}
}