package com.nis.patient.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("st020ClinicPatients")
public class St020ClinicPatients extends BaseEntity implements Serializable {
	private String id;
	private String hospId;
	private String mzid;
	private String patientId;
	private String visitId;
	private String patientName;
	private String sex;
	private String idnumber;
	private Date birthday;
	private Integer age;
	private String ageUnit;
	private String parentName;
	private String tel;
	private String marriage;
	private String nation;
	private String workUnit;
	private String education;
	private String registeraddr;
	private String presentaddr;
	private String diagnosisName;
	private String deptId;
	private String deptName;
	private String doctorId;
	private String doctorName;
	private Date diagnosisDt;
	private Date startDt;
	private Date deathDt;
	private Long isreturnvisit;
	private Date createAt;
	private Date updDate;
	private String patientType;
	private String deptType;
	private String imageQueryStr;
	private String JYZBConditions;
	private String JYZB;
	private String dateType;
	private String unReadMsg;
	private String hideExclude;
	private String startTime;
	private String endTime;
	private String userId;
	private String isMarked;
	private int cardCount;
	private int isemptycard;
	private String showEWP;
	private String showURP;
	private String mzzyid;
	private String yjdisease;
	private String yjdiseaseName;
	private String yjsource;
	private String yjcontent;
	private String scopetime;
	private String repeatcycle;
	private String inhospat;
	private String indeptname;
	private String indiagnosis;
	private Date outAt;
	private String outdeptname;
	private String outdiagnosis;
	private String diseaseid;
	private Date filldate;
	private String bkjg;
	private String iscb;
	private String islb;
	private String flag;
	private String showCrb;
	private Integer cdcanalflag;
	private Date cdcanaldt;
	private String optId;
	private String optName;
	private Date optDate;
	private String isdb;
	private String patientRange;
	private Integer crbCount;
	private Integer syCount;
	private Integer jcCount;
	private Integer tmCount;
	private Integer ycCount;
	private Integer xnCount;
	private Integer zsCount;
	private Integer nyCount;

	public String getPatientRange() {
		return this.patientRange;
	}

	public void setPatientRange(String patientRange) {
		this.patientRange = patientRange;
	}

	public String getIsdb() {
		return this.isdb;
	}

	public void setIsdb(String isdb) {
		this.isdb = isdb;
	}

	public String getOptId() {
		return this.optId;
	}

	public void setOptId(String optId) {
		this.optId = optId;
	}

	public String getOptName() {
		return this.optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	public Date getOptDate() {
		return this.optDate;
	}

	public void setOptDate(Date optDate) {
		this.optDate = optDate;
	}

	public Integer getCdcanalflag() {
		return this.cdcanalflag;
	}

	public void setCdcanalflag(Integer cdcanalflag) {
		this.cdcanalflag = cdcanalflag;
	}

	public Date getCdcanaldt() {
		return this.cdcanaldt;
	}

	public void setCdcanaldt(Date cdcanaldt) {
		this.cdcanaldt = cdcanaldt;
	}

	public String getInhospat() {
		return this.inhospat;
	}

	public void setInhospat(String inhospat) {
		this.inhospat = inhospat;
	}

	public String getIndeptname() {
		return this.indeptname;
	}

	public void setIndeptname(String indeptname) {
		this.indeptname = indeptname;
	}

	public String getIndiagnosis() {
		return this.indiagnosis;
	}

	public void setIndiagnosis(String indiagnosis) {
		this.indiagnosis = indiagnosis;
	}

	public Date getOutAt() {
		return this.outAt;
	}

	public void setOutAt(Date outAt) {
		this.outAt = outAt;
	}

	public String getOutdeptname() {
		return this.outdeptname;
	}

	public void setOutdeptname(String outdeptname) {
		this.outdeptname = outdeptname;
	}

	public String getOutdiagnosis() {
		return this.outdiagnosis;
	}

	public void setOutdiagnosis(String outdiagnosis) {
		this.outdiagnosis = outdiagnosis;
	}

	public String getYjsource() {
		return this.yjsource;
	}

	public void setYjsource(String yjsource) {
		this.yjsource = yjsource;
	}

	public String getYjcontent() {
		return this.yjcontent;
	}

	public void setYjcontent(String yjcontent) {
		this.yjcontent = yjcontent;
	}

	public String getShowEWP() {
		return this.showEWP;
	}

	public void setShowEWP(String showEWP) {
		this.showEWP = showEWP;
	}

	public String getShowURP() {
		return this.showURP;
	}

	public void setShowURP(String showURP) {
		this.showURP = showURP;
	}

	public String getMzzyid() {
		return this.mzzyid;
	}

	public void setMzzyid(String mzzyid) {
		this.mzzyid = mzzyid;
	}

	public String getYjdisease() {
		return this.yjdisease;
	}

	public void setYjdisease(String yjdisease) {
		this.yjdisease = yjdisease;
	}

	public String getYjdiseaseName() {
		return this.yjdiseaseName;
	}

	public void setYjdiseaseName(String yjdiseaseName) {
		this.yjdiseaseName = yjdiseaseName;
	}

	public String getScopetime() {
		return this.scopetime;
	}

	public void setScopetime(String scopetime) {
		this.scopetime = scopetime;
	}

	public String getRepeatcycle() {
		return this.repeatcycle;
	}

	public void setRepeatcycle(String repeatcycle) {
		this.repeatcycle = repeatcycle;
	}

	public String getDiseaseid() {
		return this.diseaseid;
	}

	public void setDiseaseid(String diseaseid) {
		this.diseaseid = diseaseid;
	}

	public Date getFilldate() {
		return this.filldate;
	}

	public void setFilldate(Date filldate) {
		this.filldate = filldate;
	}

	public String getBkjg() {
		return this.bkjg;
	}

	public void setBkjg(String bkjg) {
		this.bkjg = bkjg;
	}

	public String getIscb() {
		return this.iscb;
	}

	public void setIscb(String iscb) {
		this.iscb = iscb;
	}

	public String getIslb() {
		return this.islb;
	}

	public void setIslb(String islb) {
		this.islb = islb;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getNyCount() {
		return this.nyCount;
	}

	public void setNyCount(Integer nyCount) {
		this.nyCount = nyCount;
	}

	public Integer getXnCount() {
		return this.xnCount;
	}

	public void setXnCount(Integer xnCount) {
		this.xnCount = xnCount;
	}

	public Integer getZsCount() {
		return this.zsCount;
	}

	public void setZsCount(Integer zsCount) {
		this.zsCount = zsCount;
	}

	public Integer getYcCount() {
		return this.ycCount;
	}

	public void setYcCount(Integer ycCount) {
		this.ycCount = ycCount;
	}

	public Integer getTmCount() {
		return this.tmCount;
	}

	public void setTmCount(Integer tmCount) {
		this.tmCount = tmCount;
	}

	public int getIsemptycard() {
		return this.isemptycard;
	}

	public void setIsemptycard(int isemptycard) {
		this.isemptycard = isemptycard;
	}

	public int getCardCount() {
		return this.cardCount;
	}

	public void setCardCount(int cardCount) {
		this.cardCount = cardCount;
	}

	public String getIsMarked() {
		return this.isMarked;
	}

	public void setIsMarked(String isMarked) {
		this.isMarked = isMarked;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPatientType() {
		return this.patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	public String getDeptType() {
		return this.deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	public String getJYZBConditions() {
		return this.JYZBConditions;
	}

	public void setJYZBConditions(String jYZBConditions) {
		this.JYZBConditions = jYZBConditions;
	}

	public String getJYZB() {
		return this.JYZB;
	}

	public void setJYZB(String jYZB) {
		this.JYZB = jYZB;
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

	public String getHideExclude() {
		return this.hideExclude;
	}

	public void setHideExclude(String hideExclude) {
		this.hideExclude = hideExclude;
	}

	public String getImageQueryStr() {
		return this.imageQueryStr;
	}

	public void setImageQueryStr(String imageQueryStr) {
		this.imageQueryStr = imageQueryStr;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHospId() {
		return this.hospId;
	}

	public void setHospId(String hospId) {
		this.hospId = hospId;
	}

	public String getMzid() {
		return this.mzid;
	}

	public void setMzid(String mzid) {
		this.mzid = mzid;
	}

	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
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

	public String getIdnumber() {
		return this.idnumber;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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

	public String getParentName() {
		return this.parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMarriage() {
		return this.marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getWorkUnit() {
		return this.workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getRegisteraddr() {
		return this.registeraddr;
	}

	public void setRegisteraddr(String registeraddr) {
		this.registeraddr = registeraddr;
	}

	public String getPresentaddr() {
		return this.presentaddr;
	}

	public void setPresentaddr(String presentaddr) {
		this.presentaddr = presentaddr;
	}

	public String getDiagnosisName() {
		return this.diagnosisName;
	}

	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return this.doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public Date getDiagnosisDt() {
		return this.diagnosisDt;
	}

	public void setDiagnosisDt(Date diagnosisDt) {
		this.diagnosisDt = diagnosisDt;
	}

	public Date getStartDt() {
		return this.startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public Date getDeathDt() {
		return this.deathDt;
	}

	public void setDeathDt(Date deathDt) {
		this.deathDt = deathDt;
	}

	public Long getIsreturnvisit() {
		return this.isreturnvisit;
	}

	public void setIsreturnvisit(Long isreturnvisit) {
		this.isreturnvisit = isreturnvisit;
	}

	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public Integer getCrbCount() {
		return this.crbCount;
	}

	public void setCrbCount(Integer crbCount) {
		this.crbCount = crbCount;
	}

	public Integer getSyCount() {
		return this.syCount;
	}

	public void setSyCount(Integer syCount) {
		this.syCount = syCount;
	}

	public Integer getJcCount() {
		return this.jcCount;
	}

	public void setJcCount(Integer jcCount) {
		this.jcCount = jcCount;
	}

	public String getShowCrb() {
		return this.showCrb;
	}

	public void setShowCrb(String showCrb) {
		this.showCrb = showCrb;
	}
}