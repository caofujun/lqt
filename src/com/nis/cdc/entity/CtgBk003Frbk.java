package com.nis.cdc.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("ctgBk003Frbk")
public class CtgBk003Frbk extends BaseEntity implements Serializable {
	private String nowaddrtypename;
	private String nowaddrcode;
	private String nowaddrcodevalue;
	private String nowaddr;
	private String cardinalsymptoms;
	private Date startdate;
	private Date diagnosedate;
	private Long firstvisit;
	private String primarydiagnosis;
	private Date sampledate;
	private Date senddate;
	private String lisitemid;
	private String lisitemvalue;
	private String listresult;
	private String doctorid;
	private String doctorname;
	private String officeid;
	private String officename;
	private Long flag;
	private Date filldate;
	private String reportdeptid;
	private String reportdeptname;
	private String reportdoctorid;
	private String reportdoctorname;
	private String delreason;
	private Date auditdate;
	private String auditor;
	private Long isprint;
	private String masterid;
	private String cardid;
	private String patientId;
	private String mzid;
	private String zyid;
	private String visitId;
	private String patientName;
	private String parentName;
	private String sexid;
	private String sexname;
	private Date birtyday;
	private Long age;
	private String ageunit;
	private String id;
	private String professionid;
	private String professionname;
	private String telp;
	private String workunit;
	private String nowaddrtypeid;

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

	public String getCardinalsymptoms() {
		return this.cardinalsymptoms;
	}

	public void setCardinalsymptoms(String cardinalsymptoms) {
		this.cardinalsymptoms = cardinalsymptoms;
	}

	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getDiagnosedate() {
		return this.diagnosedate;
	}

	public void setDiagnosedate(Date diagnosedate) {
		this.diagnosedate = diagnosedate;
	}

	public Long getFirstvisit() {
		return this.firstvisit;
	}

	public void setFirstvisit(Long firstvisit) {
		this.firstvisit = firstvisit;
	}

	public String getPrimarydiagnosis() {
		return this.primarydiagnosis;
	}

	public void setPrimarydiagnosis(String primarydiagnosis) {
		this.primarydiagnosis = primarydiagnosis;
	}

	public Date getSampledate() {
		return this.sampledate;
	}

	public void setSampledate(Date sampledate) {
		this.sampledate = sampledate;
	}

	public Date getSenddate() {
		return this.senddate;
	}

	public void setSenddate(Date senddate) {
		this.senddate = senddate;
	}

	public String getLisitemid() {
		return this.lisitemid;
	}

	public void setLisitemid(String lisitemid) {
		this.lisitemid = lisitemid;
	}

	public String getLisitemvalue() {
		return this.lisitemvalue;
	}

	public void setLisitemvalue(String lisitemvalue) {
		this.lisitemvalue = lisitemvalue;
	}

	public String getListresult() {
		return this.listresult;
	}

	public void setListresult(String listresult) {
		this.listresult = listresult;
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

	public Long getFlag() {
		return this.flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	public Date getFilldate() {
		return this.filldate;
	}

	public void setFilldate(Date filldate) {
		this.filldate = filldate;
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

	public String getDelreason() {
		return this.delreason;
	}

	public void setDelreason(String delreason) {
		this.delreason = delreason;
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

	public String getParentName() {
		return this.parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
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

	public Date getBirtyday() {
		return this.birtyday;
	}

	public void setBirtyday(Date birtyday) {
		this.birtyday = birtyday;
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

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getTelp() {
		return this.telp;
	}

	public void setTelp(String telp) {
		this.telp = telp;
	}

	public String getWorkunit() {
		return this.workunit;
	}

	public void setWorkunit(String workunit) {
		this.workunit = workunit;
	}

	public String getNowaddrtypeid() {
		return this.nowaddrtypeid;
	}

	public void setNowaddrtypeid(String nowaddrtypeid) {
		this.nowaddrtypeid = nowaddrtypeid;
	}
}