package com.nis.monitor.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.enums.y;
import com.nis.comm.serializer.c;
import com.nis.comm.serializer.d;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.type.Alias;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Alias("gr002YsgrMx")
public class Gr002YsgrMx extends BaseEntity implements Serializable {
	private String excludeName;
	private String yjInfectCode;
	private String infectDeptId;
	private String regId;
	private String zyid;
	private String infectCode;
	private String infectName;
	private String cause;
	private Long count;
	private Date startAt;
	private Date stopAt;
	private Long feverDays;
	private Long bloodTestUnusualCount;
	private Long bloodTestCount;
	private String relid;
	private Integer state;
	private Date inHospAt;
	private Date moniAt;
	private String operator;
	private Double suspectedDegree;
	private Integer reportType;
	private Integer infectTypeId;
	private String deptId;
	private Long isDc;
	private Date confDate;
	private Long standardNo;
	private String customInfectCode;
	private String customInfectName;
	private String remark;
	private Date lastoperDate;
	private String gr2Relid;
	private String infectTypeName;
	private String reportTypeName;
	private String itemName;
	List<Gr002YsgrMx> gr002YsgrMxList;
	private String dataForm;
	private String originalContent;
	private String dateRange;
	private Integer tiemsCount;
	private String elementName;
	private Integer isInHosp;
	private String deptName;
	private Integer reportNum;
	private Integer conformNum;
	private Integer veryLikelyNum;
	private Integer followNum;
	private String userId;
	private String bedNo;
	private String patientId;
	private String patientName;
	private Double suspectedDegreeGt;
	private Double suspectedDegreeLt;
	private String sex;
	private String age;
	private String reportDrId;
	private String reportDrName;
	private String reportDeptName;
	private Date reportAt;
	private String auditDrName;
	private String pcName;
	private Date auditAt;
	private String authUserName;
	private String infectDeptName;
	private String relation;
	private String opeName;
	private String memo;
	private String jbzg;
	private Date infectendDt;
	private Long bwId;
	private List<String> deptIdIn;
	private Integer inNum;
	private Integer outNum;
	private String sjId;
	private Date dataDate;
	private String testOrderNos;
	private String ageUnit;
	private String[] testOrderNo;
	private String returnReason;
	private String delReason;
	private String pcrid;
	private String pcr;
	private Date lod;
	private String rdn;
	private Date ra;
	private String au;
	private Date aa;
	private String clzrs;
	private String ygyqr;
	private String lwsg;
	private String dzj;
	private String pc;
	private String clrid;
	private String clrname;
	private String diagnosisName;
	private String tablename;
	private String showCrb;
	private String qzValue;
	private String isDel;
	private String isOk;

	public String getShowCrb() {
		return this.showCrb;
	}

	public void setShowCrb(String showCrb) {
		this.showCrb = showCrb;
	}

	public String getClrid() {
		return this.clrid;
	}

	public void setClrid(String clrid) {
		this.clrid = clrid;
	}

	public String getClrname() {
		return this.clrname;
	}

	public void setClrname(String clrname) {
		this.clrname = clrname;
	}

	public String getDiagnosisName() {
		return this.diagnosisName;
	}

	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}

	public String getClzrs() {
		return this.clzrs;
	}

	public void setClzrs(String clzrs) {
		this.clzrs = clzrs;
	}

	public String getYgyqr() {
		return this.ygyqr;
	}

	public void setYgyqr(String ygyqr) {
		this.ygyqr = ygyqr;
	}

	public String getLwsg() {
		return this.lwsg;
	}

	public void setLwsg(String lwsg) {
		this.lwsg = lwsg;
	}

	public String getDzj() {
		return this.dzj;
	}

	public void setDzj(String dzj) {
		this.dzj = dzj;
	}

	public String getPc() {
		return this.pc;
	}

	public void setPc(String pc) {
		this.pc = pc;
	}

	public String getPcrid() {
		return this.pcrid;
	}

	public void setPcrid(String pcrid) {
		this.pcrid = pcrid;
	}

	public String getPcr() {
		return this.pcr;
	}

	public void setPcr(String pcr) {
		this.pcr = pcr;
	}

	public Date getLod() {
		return this.lod;
	}

	public void setLod(Date lod) {
		this.lod = lod;
	}

	public String getRdn() {
		return this.rdn;
	}

	public void setRdn(String rdn) {
		this.rdn = rdn;
	}

	public Date getRa() {
		return this.ra;
	}

	public void setRa(Date ra) {
		this.ra = ra;
	}

	public String getAu() {
		return this.au;
	}

	public void setAu(String au) {
		this.au = au;
	}

	public Date getAa() {
		return this.aa;
	}

	public void setAa(Date aa) {
		this.aa = aa;
	}

	public String getExcludeName() {
		return this.excludeName;
	}

	public void setExcludeName(String excludeName) {
		this.excludeName = excludeName;
	}

	public String getYjInfectCode() {
		return this.yjInfectCode;
	}

	public void setYjInfectCode(String yjInfectCode) {
		this.yjInfectCode = yjInfectCode;
	}

	public String getInfectDeptId() {
		return this.infectDeptId;
	}

	public void setInfectDeptId(String infectDeptId) {
		this.infectDeptId = infectDeptId;
	}

	public String getRegId() {
		return this.regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getReportDrId() {
		return this.reportDrId;
	}

	public void setReportDrId(String reportDrId) {
		this.reportDrId = reportDrId;
	}

	public String getZyid() {
		return this.zyid;
	}

	public void setZyid(String zyid) {
		this.zyid = zyid;
	}

	public String getInfectCode() {
		return this.infectCode;
	}

	public void setInfectCode(String infectCode) {
		this.infectCode = infectCode;
	}

	public String getInfectName() {
		return this.infectName;
	}

	public void setInfectName(String infectName) {
		this.infectName = infectName;
	}

	public String getCause() {
		return this.cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public Long getCount() {
		return this.count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@JsonSerialize(using = d.class)
	public Date getStartAt() {
		return this.startAt;
	}

	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}

	public Date getStopAt() {
		return this.stopAt;
	}

	public void setStopAt(Date stopAt) {
		this.stopAt = stopAt;
	}

	public Long getFeverDays() {
		return this.feverDays;
	}

	public void setFeverDays(Long feverDays) {
		this.feverDays = feverDays;
	}

	public Long getBloodTestUnusualCount() {
		return this.bloodTestUnusualCount;
	}

	public void setBloodTestUnusualCount(Long bloodTestUnusualCount) {
		this.bloodTestUnusualCount = bloodTestUnusualCount;
	}

	public Long getBloodTestCount() {
		return this.bloodTestCount;
	}

	public void setBloodTestCount(Long bloodTestCount) {
		this.bloodTestCount = bloodTestCount;
	}

	public String getRelid() {
		return this.relid;
	}

	public void setRelid(String relid) {
		this.relid = relid;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@JsonSerialize(using = d.class)
	public Date getMoniAt() {
		return this.moniAt;
	}

	public void setMoniAt(Date moniAt) {
		this.moniAt = moniAt;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Double getSuspectedDegree() {
		return this.suspectedDegree;
	}

	public void setSuspectedDegree(Double suspectedDegree) {
		this.suspectedDegree = suspectedDegree;
	}

	public Integer getReportType() {
		return this.reportType;
	}

	public void setReportType(Integer reportType) {
		this.reportType = reportType;
	}

	public Integer getInfectTypeId() {
		return this.infectTypeId;
	}

	public void setInfectTypeId(Integer infectTypeId) {
		this.infectTypeId = infectTypeId;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public Long getIsDc() {
		return this.isDc;
	}

	public void setIsDc(Long isDc) {
		this.isDc = isDc;
	}

	public Date getConfDate() {
		return this.confDate;
	}

	public void setConfDate(Date confDate) {
		this.confDate = confDate;
	}

	public Long getStandardNo() {
		return this.standardNo;
	}

	public void setStandardNo(Long standardNo) {
		this.standardNo = standardNo;
	}

	public String getCustomInfectCode() {
		return this.customInfectCode;
	}

	public void setCustomInfectCode(String customInfectCode) {
		this.customInfectCode = customInfectCode;
	}

	public String getCustomInfectName() {
		return this.customInfectName;
	}

	public void setCustomInfectName(String customInfectName) {
		this.customInfectName = customInfectName;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@JsonSerialize(using = d.class)
	public Date getLastoperDate() {
		return this.lastoperDate;
	}

	public void setLastoperDate(Date lastoperDate) {
		this.lastoperDate = lastoperDate;
	}

	public String getGr2Relid() {
		return this.gr2Relid;
	}

	public void setGr2Relid(String gr2Relid) {
		this.gr2Relid = gr2Relid;
	}

	public String getInfectTypeName() {
		return y.h(this.infectTypeId).getName();
	}

	public void setInfectTypeName(String infectTypeName) {
		this.infectTypeName = infectTypeName;
	}

	public String getReportTypeName() {
		return this.reportTypeName;
	}

	public void setReportTypeName(String reportTypeName) {
		this.reportTypeName = reportTypeName;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public List<Gr002YsgrMx> getGr002YsgrMxList() {
		return this.gr002YsgrMxList;
	}

	public void setGr002YsgrMxList(List<Gr002YsgrMx> gr002YsgrMxList) {
		this.gr002YsgrMxList = gr002YsgrMxList;
	}

	public String getDataForm() {
		return this.dataForm;
	}

	public void setDataForm(String dataForm) {
		this.dataForm = dataForm;
	}

	public String getOriginalContent() {
		return this.originalContent;
	}

	public void setOriginalContent(String originalContent) {
		this.originalContent = originalContent;
	}

	public String getDateRange() {
		return this.dateRange;
	}

	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
	}

	public Integer getTiemsCount() {
		return this.tiemsCount;
	}

	public void setTiemsCount(Integer tiemsCount) {
		this.tiemsCount = tiemsCount;
	}

	public String getElementName() {
		return this.elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public Integer getIsInHosp() {
		return this.isInHosp;
	}

	public void setIsInHosp(Integer isInHosp) {
		this.isInHosp = isInHosp;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getReportNum() {
		return this.reportNum;
	}

	public void setReportNum(Integer reportNum) {
		this.reportNum = reportNum;
	}

	public Integer getConformNum() {
		return this.conformNum;
	}

	public void setConformNum(Integer conformNum) {
		this.conformNum = conformNum;
	}

	public Integer getVeryLikelyNum() {
		return this.veryLikelyNum;
	}

	public void setVeryLikelyNum(Integer veryLikelyNum) {
		this.veryLikelyNum = veryLikelyNum;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBedNo() {
		return this.bedNo;
	}

	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}

	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Double getSuspectedDegreeGt() {
		return this.suspectedDegreeGt;
	}

	public void setSuspectedDegreeGt(Double suspectedDegreeGt) {
		this.suspectedDegreeGt = suspectedDegreeGt;
	}

	public Double getSuspectedDegreeLt() {
		return this.suspectedDegreeLt;
	}

	public void setSuspectedDegreeLt(Double suspectedDegreeLt) {
		this.suspectedDegreeLt = suspectedDegreeLt;
	}

	@JsonSerialize(using = d.class)
	public Date getInHospAt() {
		return this.inHospAt;
	}

	public void setInHospAt(Date inHospAt) {
		this.inHospAt = inHospAt;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getReportDrName() {
		return this.reportDrName;
	}

	public void setReportDrName(String reportDrName) {
		this.reportDrName = reportDrName;
	}

	@JsonSerialize(using = c.class)
	public Date getReportAt() {
		return this.reportAt;
	}

	public void setReportAt(Date reportAt) {
		this.reportAt = reportAt;
	}

	public String getAuditDrName() {
		return this.auditDrName;
	}

	public void setAuditDrName(String auditDrName) {
		this.auditDrName = auditDrName;
	}

	@JsonSerialize(using = c.class)
	public Date getAuditAt() {
		return this.auditAt;
	}

	public void setAuditAt(Date auditAt) {
		this.auditAt = auditAt;
	}

	public String getInfectDeptName() {
		return this.infectDeptName;
	}

	public void setInfectDeptName(String infectDeptName) {
		this.infectDeptName = infectDeptName;
	}

	public String getRelation() {
		return this.relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getOpeName() {
		return this.opeName;
	}

	public void setOpeName(String opeName) {
		this.opeName = opeName;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getJbzg() {
		return this.jbzg;
	}

	public void setJbzg(String jbzg) {
		this.jbzg = jbzg;
	}

	@JsonSerialize(using = c.class)
	public Date getInfectendDt() {
		return this.infectendDt;
	}

	public void setInfectendDt(Date infectendDt) {
		this.infectendDt = infectendDt;
	}

	public Long getBwId() {
		return this.bwId;
	}

	public void setBwId(Long bwId) {
		this.bwId = bwId;
	}

	public List<String> getDeptIdIn() {
		return this.deptIdIn;
	}

	public void setDeptIdIn(List<String> deptIdIn) {
		this.deptIdIn = deptIdIn;
	}

	public Integer getFollowNum() {
		return this.followNum;
	}

	public void setFollowNum(Integer followNum) {
		this.followNum = followNum;
	}

	public Integer getInNum() {
		return this.inNum;
	}

	public void setInNum(Integer inNum) {
		this.inNum = inNum;
	}

	public Integer getOutNum() {
		return this.outNum;
	}

	public void setOutNum(Integer outNum) {
		this.outNum = outNum;
	}

	public String getSjId() {
		return this.sjId;
	}

	public void setSjId(String sjId) {
		this.sjId = sjId;
	}

	public Date getDataDate() {
		return this.dataDate;
	}

	public void setDataDate(Date dataDate) {
		this.dataDate = dataDate;
	}

	public String getPcName() {
		return this.pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

	public String getTestOrderNos() {
		return this.testOrderNos;
	}

	public void setTestOrderNos(String testOrderNos) {
		this.testOrderNos = testOrderNos;
	}

	public String[] getTestOrderNo() {
		return this.testOrderNo;
	}

	public void setTestOrderNo(String[] testOrderNo) {
		this.testOrderNo = testOrderNo;
	}

	public String getTablename() {
		return this.tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getAgeUnit() {
		return this.ageUnit;
	}

	public void setAgeUnit(String ageUnit) {
		this.ageUnit = ageUnit;
	}

	public String getQzValue() {
		return this.qzValue;
	}

	public void setQzValue(String qzValue) {
		this.qzValue = qzValue;
	}

	public String getIsDel() {
		return this.isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getIsOk() {
		return this.isOk;
	}

	public void setIsOk(String isOk) {
		this.isOk = isOk;
	}

	public String getReturnReason() {
		return this.returnReason;
	}

	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}

	public String getDelReason() {
		return this.delReason;
	}

	public void setDelReason(String delReason) {
		this.delReason = delReason;
	}

	public String getReportDeptName() {
		return this.reportDeptName;
	}

	public void setReportDeptName(String reportDeptName) {
		this.reportDeptName = reportDeptName;
	}

	public String getAuthUserName() {
		return this.authUserName;
	}

	public void setAuthUserName(String authUserName) {
		this.authUserName = authUserName;
	}
}