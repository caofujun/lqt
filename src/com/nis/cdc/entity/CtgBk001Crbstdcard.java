package com.nis.cdc.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("ctgBk001Crbstdcard")
public class CtgBk001Crbstdcard extends BaseEntity implements Serializable {
	private String ctinfectionname;
	private String contactflag;
	private String subremark;
	private String reportdoctorid;
	private String reportdoctorname;
	private Date reportdate;
	private String masterid;
	private String subid;
	private String marriageid;
	private String marriagevalue;
	private String nationid;
	private String nationname;
	private String educationid;
	private String educationname;
	private String regaddrtypeid;
	private String regaddrtypename;
	private String regaddrcode;
	private String regaddrcodevalue;
	private String regvillage;
	private String reghousenumber;
	private String regaddr;
	private String contacthistoryid;
	private String contacthistoryname;
	private String contacthistoryother;
	private Long injectcount;
	private Long oppositesexcount;
	private Long urningcount;
	private String stdhistoryid;
	private String stdhistoryname;
	private String infectionid;
	private String infectionname;
	private String infectionother;
	private String samplesourceid;
	private String samplesourcename;
	private String samplesourceother;
	private String labconclusionid;
	private String labconclusionname;
	private Date affirmdate;
	private String affirmunit;
	private Date diagnosedt;
	private String ctinfectionid;
	private String business;
	private String sinfect;

	public String getRegvillage() {
		return this.regvillage;
	}

	public void setRegvillage(String regvillage) {
		this.regvillage = regvillage;
	}

	public String getReghousenumber() {
		return this.reghousenumber;
	}

	public void setReghousenumber(String reghousenumber) {
		this.reghousenumber = reghousenumber;
	}

	public String getBusiness() {
		return this.business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getSinfect() {
		return this.sinfect;
	}

	public void setSinfect(String sinfect) {
		this.sinfect = sinfect;
	}

	public String getCtinfectionname() {
		return this.ctinfectionname;
	}

	public void setCtinfectionname(String ctinfectionname) {
		this.ctinfectionname = ctinfectionname;
	}

	public String getContactflag() {
		return this.contactflag;
	}

	public void setContactflag(String contactflag) {
		this.contactflag = contactflag;
	}

	public String getSubremark() {
		return this.subremark;
	}

	public void setSubremark(String subremark) {
		this.subremark = subremark;
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

	public Date getReportdate() {
		return this.reportdate;
	}

	public void setReportdate(Date reportdate) {
		this.reportdate = reportdate;
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

	public String getRegaddrcode() {
		return this.regaddrcode;
	}

	public void setRegaddrcode(String regaddrcode) {
		this.regaddrcode = regaddrcode;
	}

	public String getRegaddrcodevalue() {
		return this.regaddrcodevalue;
	}

	public void setRegaddrcodevalue(String regaddrcodevalue) {
		this.regaddrcodevalue = regaddrcodevalue;
	}

	public String getRegaddr() {
		return this.regaddr;
	}

	public void setRegaddr(String regaddr) {
		this.regaddr = regaddr;
	}

	public String getContacthistoryid() {
		return this.contacthistoryid;
	}

	public void setContacthistoryid(String contacthistoryid) {
		this.contacthistoryid = contacthistoryid;
	}

	public String getContacthistoryname() {
		return this.contacthistoryname;
	}

	public void setContacthistoryname(String contacthistoryname) {
		this.contacthistoryname = contacthistoryname;
	}

	public String getContacthistoryother() {
		return this.contacthistoryother;
	}

	public void setContacthistoryother(String contacthistoryother) {
		this.contacthistoryother = contacthistoryother;
	}

	public Long getInjectcount() {
		return this.injectcount;
	}

	public void setInjectcount(Long injectcount) {
		this.injectcount = injectcount;
	}

	public Long getOppositesexcount() {
		return this.oppositesexcount;
	}

	public void setOppositesexcount(Long oppositesexcount) {
		this.oppositesexcount = oppositesexcount;
	}

	public Long getUrningcount() {
		return this.urningcount;
	}

	public void setUrningcount(Long urningcount) {
		this.urningcount = urningcount;
	}

	public String getStdhistoryid() {
		return this.stdhistoryid;
	}

	public void setStdhistoryid(String stdhistoryid) {
		this.stdhistoryid = stdhistoryid;
	}

	public String getStdhistoryname() {
		return this.stdhistoryname;
	}

	public void setStdhistoryname(String stdhistoryname) {
		this.stdhistoryname = stdhistoryname;
	}

	public String getInfectionid() {
		return this.infectionid;
	}

	public void setInfectionid(String infectionid) {
		this.infectionid = infectionid;
	}

	public String getInfectionname() {
		return this.infectionname;
	}

	public void setInfectionname(String infectionname) {
		this.infectionname = infectionname;
	}

	public String getInfectionother() {
		return this.infectionother;
	}

	public void setInfectionother(String infectionother) {
		this.infectionother = infectionother;
	}

	public String getSamplesourceid() {
		return this.samplesourceid;
	}

	public void setSamplesourceid(String samplesourceid) {
		this.samplesourceid = samplesourceid;
	}

	public String getSamplesourcename() {
		return this.samplesourcename;
	}

	public void setSamplesourcename(String samplesourcename) {
		this.samplesourcename = samplesourcename;
	}

	public String getSamplesourceother() {
		return this.samplesourceother;
	}

	public void setSamplesourceother(String samplesourceother) {
		this.samplesourceother = samplesourceother;
	}

	public String getLabconclusionid() {
		return this.labconclusionid;
	}

	public void setLabconclusionid(String labconclusionid) {
		this.labconclusionid = labconclusionid;
	}

	public String getLabconclusionname() {
		return this.labconclusionname;
	}

	public void setLabconclusionname(String labconclusionname) {
		this.labconclusionname = labconclusionname;
	}

	public Date getAffirmdate() {
		return this.affirmdate;
	}

	public void setAffirmdate(Date affirmdate) {
		this.affirmdate = affirmdate;
	}

	public String getAffirmunit() {
		return this.affirmunit;
	}

	public void setAffirmunit(String affirmunit) {
		this.affirmunit = affirmunit;
	}

	public Date getDiagnosedt() {
		return this.diagnosedt;
	}

	public void setDiagnosedt(Date diagnosedt) {
		this.diagnosedt = diagnosedt;
	}

	public String getCtinfectionid() {
		return this.ctinfectionid;
	}

	public void setCtinfectionid(String ctinfectionid) {
		this.ctinfectionid = ctinfectionid;
	}
}