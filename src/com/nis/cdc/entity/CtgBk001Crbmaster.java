package com.nis.cdc.entity;

import com.nis.cdc.entity.CtgBk001Crbafpcard;
import com.nis.cdc.entity.CtgBk001Crbdisease;
import com.nis.cdc.entity.CtgBk001Crbhbvcard;
import com.nis.cdc.entity.CtgBk001Crbstdcard;
import com.nis.cdc.entity.CtgBk001Zzd;
import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.type.Alias;

@Alias("ctgBk001Crbmaster")
public class CtgBk001Crbmaster extends BaseEntity implements Serializable {
	private String masterid;
	private String cardid;
	private String patientId;
	private String zyid;
	private String visitId;
	private String mzid;
	private String bedNo;
	private String patientName;
	private String parentName;
	private String sexid;
	private String sexname;
	private Date birtyday;
	private Long age;
	private String ageunit;
	private String idcard;
	private String telp;
	private String unit;
	private String professionid;
	private String professionname;
	private String areatypeid;
	private String areatypename;
	private String addrcode;
	private String addrcodevalue;
	private String addr;
	private String village;
	private String housenumber;
	private String doctorid;
	private String doctorname;
	private String officeid;
	private String officename;
	private Date deaddate;
	private String reporttypeid;
	private String reporttypename;
	private String reportdoctorid;
	private String reportdoctorname;
	private String reportdeptid;
	private String reportdeptname;
	private Date filldate;
	private Long isprint;
	private String idtype;
	private String idtypevalue;
	private String delreason;
	private int isemptycard;
	private String auditorname;
	private String diseaseId;
	private String diseaseName;
	private String cardType;
	private String cardName;
	private String cardUrl;
	private String cardStates;
	private String dateType;
	private String unReadMsg;
	private String casetypename;
	private String diseasenotes;
	private String auditor;
	private Date auditdate;
	private String subid;
	private String userid;
	private List<CtgBk001Crbdisease> diseaseList;
	private CtgBk001Crbhbvcard yg;
	private CtgBk001Crbafpcard afp;
	private CtgBk001Crbstdcard hiv;
	private CtgBk001Zzd fjh;
	private String casetypeid;
	private String casetypeName;
	private String casetypeid2;
	private String casetypename2;
	private Date startdate;
	private Date diagnosedate;
	private String labresult;
	private String contactflag;
	private String hbsAgTime;
	private String hbsAgTimeName;
	private String hbvDateTime;
	private String alt;
	private String hbc;
	private String hbcName;
	private String hbv;
	private String hbvName;
	private String hbv7Code;
	private String hbv7CodeName;
	private String marriageid;
	private String marriageName;
	private String nationId;
	private String nationName;
	private String educationId;
	private String educationName;
	private String regaddrCode;
	private String regaddrtypeId;
	private String regaddr;
	private String intouchhis;
	private String intouchhisName;
	private String urningcount;
	private String injectcount;
	private String nonWebCount;
	private String business;
	private String contacthistoryother;
	private String stdhistoryId;
	private String stdhistoryName;
	private String infectionId;
	private String infectionName;
	private String infectionother;
	private String samplesourceId;
	private String samplesourceName;
	private String srcothers;
	private String labconclusionId;
	private String labconclusionName;
	private Date affirmdate;
	private String affirmunit;
	private Date diagnosedt;
	private String notes;
	private String sinfect;
	private String nidn;
	private Integer zbflag;
	private String dzbm;

	public String getDzbm() {
		return this.dzbm;
	}

	public void setDzbm(String dzbm) {
		this.dzbm = dzbm;
	}

	public Integer getZbflag() {
		return this.zbflag;
	}

	public void setZbflag(Integer zbflag) {
		this.zbflag = zbflag;
	}

	public String getNidn() {
		return this.nidn;
	}

	public void setNidn(String nidn) {
		this.nidn = nidn;
	}

	public String getCardUrl() {
		return this.cardUrl;
	}

	public void setCardUrl(String cardUrl) {
		this.cardUrl = cardUrl;
	}

	public String getCardName() {
		return this.cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getDiseaseId() {
		return this.diseaseId;
	}

	public void setDiseaseId(String diseaseId) {
		this.diseaseId = diseaseId;
	}

	public String getAuditorname() {
		return this.auditorname;
	}

	public void setAuditorname(String auditorname) {
		this.auditorname = auditorname;
	}

	public int getIsemptycard() {
		return this.isemptycard;
	}

	public void setIsemptycard(int isemptycard) {
		this.isemptycard = isemptycard;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSubid() {
		return this.subid;
	}

	public void setSubid(String subid) {
		this.subid = subid;
	}

	public String getAuditor() {
		return this.auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public Date getAuditdate() {
		return this.auditdate;
	}

	public void setAuditdate(Date auditdate) {
		this.auditdate = auditdate;
	}

	public String getCasetypename() {
		return this.casetypename;
	}

	public void setCasetypename(String casetypename) {
		this.casetypename = casetypename;
	}

	public String getDiseasenotes() {
		return this.diseasenotes;
	}

	public void setDiseasenotes(String diseasenotes) {
		this.diseasenotes = diseasenotes;
	}

	public String getDelreason() {
		return this.delreason;
	}

	public void setDelreason(String delreason) {
		this.delreason = delreason;
	}

	public String getDateType() {
		return this.dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public String getUnReadMsg() {
		return this.unReadMsg;
	}

	public void setUnReadMsg(String unReadMsg) {
		this.unReadMsg = unReadMsg;
	}

	public CtgBk001Crbhbvcard getYg() {
		return this.yg;
	}

	public void setYg(CtgBk001Crbhbvcard yg) {
		this.yg = yg;
	}

	public CtgBk001Crbafpcard getAfp() {
		return this.afp;
	}

	public void setAfp(CtgBk001Crbafpcard afp) {
		this.afp = afp;
	}

	public CtgBk001Crbstdcard getHiv() {
		return this.hiv;
	}

	public void setHiv(CtgBk001Crbstdcard hiv) {
		this.hiv = hiv;
	}

	public List<CtgBk001Crbdisease> getDiseaseList() {
		return this.diseaseList;
	}

	public void setDiseaseList(List<CtgBk001Crbdisease> diseaseList) {
		this.diseaseList = diseaseList;
	}

	public String getDiseaseName() {
		return this.diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardStates() {
		return this.cardStates;
	}

	public void setCardStates(String cardStates) {
		this.cardStates = cardStates;
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

	public String getBedNo() {
		return this.bedNo;
	}

	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
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

	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getTelp() {
		return this.telp;
	}

	public void setTelp(String telp) {
		this.telp = telp;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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

	public String getAreatypeid() {
		return this.areatypeid;
	}

	public void setAreatypeid(String areatypeid) {
		this.areatypeid = areatypeid;
	}

	public String getAreatypename() {
		return this.areatypename;
	}

	public void setAreatypename(String areatypename) {
		this.areatypename = areatypename;
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

	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getVillage() {
		return this.village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getHousenumber() {
		return this.housenumber;
	}

	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
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

	public Date getDeaddate() {
		return this.deaddate;
	}

	public void setDeaddate(Date deaddate) {
		this.deaddate = deaddate;
	}

	public String getReporttypeid() {
		return this.reporttypeid;
	}

	public void setReporttypeid(String reporttypeid) {
		this.reporttypeid = reporttypeid;
	}

	public String getReporttypename() {
		return this.reporttypename;
	}

	public void setReporttypename(String reporttypename) {
		this.reporttypename = reporttypename;
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

	public Date getFilldate() {
		return this.filldate;
	}

	public void setFilldate(Date filldate) {
		this.filldate = filldate;
	}

	public Long getIsprint() {
		return this.isprint;
	}

	public void setIsprint(Long isprint) {
		this.isprint = isprint;
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

	public String getCasetypeid() {
		return this.casetypeid;
	}

	public void setCasetypeid(String casetypeid) {
		this.casetypeid = casetypeid;
	}

	public String getCasetypeName() {
		return this.casetypeName;
	}

	public void setCasetypeName(String casetypeName) {
		this.casetypeName = casetypeName;
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

	public String getLabresult() {
		return this.labresult;
	}

	public void setLabresult(String labresult) {
		this.labresult = labresult;
	}

	public String getContactflag() {
		return this.contactflag;
	}

	public void setContactflag(String contactflag) {
		this.contactflag = contactflag;
	}

	public String getHbsAgTime() {
		return this.hbsAgTime;
	}

	public void setHbsAgTime(String hbsAgTime) {
		this.hbsAgTime = hbsAgTime;
	}

	public String getHbsAgTimeName() {
		return this.hbsAgTimeName;
	}

	public void setHbsAgTimeName(String hbsAgTimeName) {
		this.hbsAgTimeName = hbsAgTimeName;
	}

	public String getHbvDateTime() {
		return this.hbvDateTime;
	}

	public void setHbvDateTime(String hbvDateTime) {
		this.hbvDateTime = hbvDateTime;
	}

	public String getAlt() {
		return this.alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getHbc() {
		return this.hbc;
	}

	public void setHbc(String hbc) {
		this.hbc = hbc;
	}

	public String getHbcName() {
		return this.hbcName;
	}

	public void setHbcName(String hbcName) {
		this.hbcName = hbcName;
	}

	public String getHbv() {
		return this.hbv;
	}

	public void setHbv(String hbv) {
		this.hbv = hbv;
	}

	public String getHbvName() {
		return this.hbvName;
	}

	public void setHbvName(String hbvName) {
		this.hbvName = hbvName;
	}

	public String getHbv7Code() {
		return this.hbv7Code;
	}

	public void setHbv7Code(String hbv7Code) {
		this.hbv7Code = hbv7Code;
	}

	public String getHbv7CodeName() {
		return this.hbv7CodeName;
	}

	public void setHbv7CodeName(String hbv7CodeName) {
		this.hbv7CodeName = hbv7CodeName;
	}

	public String getMarriageid() {
		return this.marriageid;
	}

	public void setMarriageid(String marriageid) {
		this.marriageid = marriageid;
	}

	public String getMarriageName() {
		return this.marriageName;
	}

	public void setMarriageName(String marriageName) {
		this.marriageName = marriageName;
	}

	public String getNationId() {
		return this.nationId;
	}

	public void setNationId(String nationId) {
		this.nationId = nationId;
	}

	public String getNationName() {
		return this.nationName;
	}

	public void setNationName(String nationName) {
		this.nationName = nationName;
	}

	public String getEducationId() {
		return this.educationId;
	}

	public void setEducationId(String educationId) {
		this.educationId = educationId;
	}

	public String getEducationName() {
		return this.educationName;
	}

	public void setEducationName(String educationName) {
		this.educationName = educationName;
	}

	public String getIntouchhis() {
		return this.intouchhis;
	}

	public void setIntouchhis(String intouchhis) {
		this.intouchhis = intouchhis;
	}

	public String getIntouchhisName() {
		return this.intouchhisName;
	}

	public void setIntouchhisName(String intouchhisName) {
		this.intouchhisName = intouchhisName;
	}

	public String getUrningcount() {
		return this.urningcount;
	}

	public void setUrningcount(String urningcount) {
		this.urningcount = urningcount;
	}

	public String getInjectcount() {
		return this.injectcount;
	}

	public void setInjectcount(String injectcount) {
		this.injectcount = injectcount;
	}

	public String getNonWebCount() {
		return this.nonWebCount;
	}

	public void setNonWebCount(String nonWebCount) {
		this.nonWebCount = nonWebCount;
	}

	public String getBusiness() {
		return this.business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getContacthistoryother() {
		return this.contacthistoryother;
	}

	public void setContacthistoryother(String contacthistoryother) {
		this.contacthistoryother = contacthistoryother;
	}

	public String getStdhistoryId() {
		return this.stdhistoryId;
	}

	public void setStdhistoryId(String stdhistoryId) {
		this.stdhistoryId = stdhistoryId;
	}

	public String getStdhistoryName() {
		return this.stdhistoryName;
	}

	public void setStdhistoryName(String stdhistoryName) {
		this.stdhistoryName = stdhistoryName;
	}

	public String getInfectionId() {
		return this.infectionId;
	}

	public void setInfectionId(String infectionId) {
		this.infectionId = infectionId;
	}

	public String getInfectionName() {
		return this.infectionName;
	}

	public void setInfectionName(String infectionName) {
		this.infectionName = infectionName;
	}

	public String getInfectionother() {
		return this.infectionother;
	}

	public void setInfectionother(String infectionother) {
		this.infectionother = infectionother;
	}

	public String getSamplesourceId() {
		return this.samplesourceId;
	}

	public void setSamplesourceId(String samplesourceId) {
		this.samplesourceId = samplesourceId;
	}

	public String getSamplesourceName() {
		return this.samplesourceName;
	}

	public void setSamplesourceName(String samplesourceName) {
		this.samplesourceName = samplesourceName;
	}

	public String getSrcothers() {
		return this.srcothers;
	}

	public void setSrcothers(String srcothers) {
		this.srcothers = srcothers;
	}

	public String getLabconclusionId() {
		return this.labconclusionId;
	}

	public void setLabconclusionId(String labconclusionId) {
		this.labconclusionId = labconclusionId;
	}

	public String getLabconclusionName() {
		return this.labconclusionName;
	}

	public void setLabconclusionName(String labconclusionName) {
		this.labconclusionName = labconclusionName;
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

	public String getRegaddrCode() {
		return this.regaddrCode;
	}

	public void setRegaddrCode(String regaddrCode) {
		this.regaddrCode = regaddrCode;
	}

	public String getRegaddrtypeId() {
		return this.regaddrtypeId;
	}

	public void setRegaddrtypeId(String regaddrtypeId) {
		this.regaddrtypeId = regaddrtypeId;
	}

	public String getRegaddr() {
		return this.regaddr;
	}

	public void setRegaddr(String regaddr) {
		this.regaddr = regaddr;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getSinfect() {
		return this.sinfect;
	}

	public void setSinfect(String sinfect) {
		this.sinfect = sinfect;
	}

	public CtgBk001Zzd getFjh() {
		return this.fjh;
	}

	public void setFjh(CtgBk001Zzd fjh) {
		this.fjh = fjh;
	}
}