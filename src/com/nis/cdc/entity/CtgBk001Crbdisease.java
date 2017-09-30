package com.nis.cdc.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("ctgBk001Crbdisease")
public class CtgBk001Crbdisease extends BaseEntity implements Serializable {
	private Date diagnosedate;
	private String casetypeid;
	private String casetypename;
	private String casetypeid2;
	private String casetypename2;
	private String contactflag;
	private String diseasenotes;
	private Date acquisitiondate;
	private String senddoctorname;
	private String sampleplaces;
	private String treatmentprocess;
	private String clinicaldiagnosis1;
	private String clinicaldiagnosis2;
	private String memo;
	private String reportdeptid;
	private String reportdeptname;
	private String reportdoctorid;
	private String reportdoctorname;
	private Date filldate;
	private Long flag;
	private Date auditdate;
	private String auditor;
	private String auditorname;
	private String labresultno;
	private String labresult;
	private String masterid;
	private String subid;
	private String diseaseid;
	private String diseasename;
	private Date startdate;
	private String rpr;
	private String trust;

	public String getTrust() {
		return this.trust;
	}

	public void setTrust(String trust) {
		this.trust = trust;
	}

	public String getRpr() {
		return this.rpr;
	}

	public void setRpr(String rpr) {
		this.rpr = rpr;
	}

	public String getAuditorname() {
		return this.auditorname;
	}

	public void setAuditorname(String auditorname) {
		this.auditorname = auditorname;
	}

	public Date getDiagnosedate() {
		return this.diagnosedate;
	}

	public void setDiagnosedate(Date diagnosedate) {
		this.diagnosedate = diagnosedate;
	}

	public String getCasetypeid() {
		return this.casetypeid;
	}

	public void setCasetypeid(String casetypeid) {
		this.casetypeid = casetypeid;
	}

	public String getCasetypename() {
		return this.casetypename;
	}

	public void setCasetypename(String casetypename) {
		this.casetypename = casetypename;
	}

	public String getCasetypeid2() {
		return this.casetypeid2;
	}

	public void setCasetypeid2(String casetypeid2) {
		this.casetypeid2 = casetypeid2;
	}

	public String getCasetypename2() {
		return this.casetypename2;
	}

	public void setCasetypename2(String casetypename2) {
		this.casetypename2 = casetypename2;
	}

	public String getContactflag() {
		return this.contactflag;
	}

	public void setContactflag(String contactflag) {
		this.contactflag = contactflag;
	}

	public String getDiseasenotes() {
		return this.diseasenotes;
	}

	public void setDiseasenotes(String diseasenotes) {
		this.diseasenotes = diseasenotes;
	}

	public Date getAcquisitiondate() {
		return this.acquisitiondate;
	}

	public void setAcquisitiondate(Date acquisitiondate) {
		this.acquisitiondate = acquisitiondate;
	}

	public String getSenddoctorname() {
		return this.senddoctorname;
	}

	public void setSenddoctorname(String senddoctorname) {
		this.senddoctorname = senddoctorname;
	}

	public String getSampleplaces() {
		return this.sampleplaces;
	}

	public void setSampleplaces(String sampleplaces) {
		this.sampleplaces = sampleplaces;
	}

	public String getTreatmentprocess() {
		return this.treatmentprocess;
	}

	public void setTreatmentprocess(String treatmentprocess) {
		this.treatmentprocess = treatmentprocess;
	}

	public String getClinicaldiagnosis1() {
		return this.clinicaldiagnosis1;
	}

	public void setClinicaldiagnosis1(String clinicaldiagnosis1) {
		this.clinicaldiagnosis1 = clinicaldiagnosis1;
	}

	public String getClinicaldiagnosis2() {
		return this.clinicaldiagnosis2;
	}

	public void setClinicaldiagnosis2(String clinicaldiagnosis2) {
		this.clinicaldiagnosis2 = clinicaldiagnosis2;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public String getLabresultno() {
		return this.labresultno;
	}

	public void setLabresultno(String labresultno) {
		this.labresultno = labresultno;
	}

	public String getLabresult() {
		return this.labresult;
	}

	public void setLabresult(String labresult) {
		this.labresult = labresult;
	}

	public String getMasterid() {
		return this.masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}

	public String getSubid() {
		return this.subid;
	}

	public void setSubid(String subid) {
		this.subid = subid;
	}

	public String getDiseaseid() {
		return this.diseaseid;
	}

	public void setDiseaseid(String diseaseid) {
		this.diseaseid = diseaseid;
	}

	public String getDiseasename() {
		return this.diseasename;
	}

	public void setDiseasename(String diseasename) {
		this.diseasename = diseasename;
	}

	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
}