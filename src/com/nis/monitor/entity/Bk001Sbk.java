package com.nis.monitor.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.enums.y;
import com.nis.comm.enums.z;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.type.Alias;

@Alias("bk001Sbk")
public class Bk001Sbk extends BaseEntity implements Serializable {
	private String reportDeptName;
	private Integer isOk;
	private Integer isLb;
	private Integer isGr;
	private String cardReturnCause;
	private Integer isDel;
	private Integer isCb;
	private Double weight;
	private String bedNo;
	private String reportDrId;
	private String jbzdCode;
	private String jbzd;
	private Integer cardSource;
	private String delReason;
	private String gdFlag;
	private String gdUserid;
	private Date gdAt;
	private Integer bkType;
	private String lastoperName;
	private Date lastoperDate;
	private String bkTypename;
	private String infectDeptNameEdit;
	private String relid;
	private String refid;
	private String zyid;
	private String patientId;
	private Integer visitId;
	private Integer reportVisit;
	private String patientName;
	private Date inHospAt;
	private String ryzdCode;
	private String ryzd;
	private String sex;
	private String age;
	private String ageUnit;
	private Date reportAt;
	private String chargeDrId;
	private String reportDrName;
	private String reportDeptId;
	private String reportOrInfectDeptId;
	private String dateField;
	private Integer reportNum;
	private Integer infectTypeId;
	private String relation;
	private String infectDeptId;
	private String infectDeptName;
	private String infectDiagnId;
	private String infectDiagnName;
	private List<String> relationIn;
	private String isOkName;
	private String regId;
	private String chargeDrName;
	private Integer inDays;
	private String deptName;
	private Date outAt;
	private Date infectDate;
	private String opeName;
	private String memo;
	private String jbzg;
	private String ygys;
	private String sampleName;
	private String pathoName;
	private String antiName;
	private Date confirmDt;
	private String authUsername;
	private Date authAt;
	private String bk2Relid;
	private String specDescribes;
	private int ygysCount;
	private String ygysArr;
	private int zbjCount;
	private String zbjArr;
	private String infectTypeName;
	private Date startAt;
	private String infectName;
	private String unitId;

	public String getReportDeptName() {
		return this.reportDeptName;
	}

	public void setReportDeptName(String reportDeptName) {
		this.reportDeptName = reportDeptName;
	}

	public Integer getIsOk() {
		return this.isOk;
	}

	public void setIsOk(Integer isOk) {
		this.isOk = isOk;
	}

	public Integer getIsLb() {
		return this.isLb;
	}

	public void setIsLb(Integer isLb) {
		this.isLb = isLb;
	}

	public Integer getIsGr() {
		return this.isGr;
	}

	public void setIsGr(Integer isGr) {
		this.isGr = isGr;
	}

	public String getCardReturnCause() {
		return this.cardReturnCause;
	}

	public void setCardReturnCause(String cardReturnCause) {
		this.cardReturnCause = cardReturnCause;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Integer getIsCb() {
		return this.isCb;
	}

	public void setIsCb(Integer isCb) {
		this.isCb = isCb;
	}

	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getBedNo() {
		return this.bedNo;
	}

	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}

	public String getReportDrId() {
		return this.reportDrId;
	}

	public void setReportDrId(String reportDrId) {
		this.reportDrId = reportDrId;
	}

	public String getJbzdCode() {
		return this.jbzdCode;
	}

	public void setJbzdCode(String jbzdCode) {
		this.jbzdCode = jbzdCode;
	}

	public String getJbzd() {
		return this.jbzd;
	}

	public void setJbzd(String jbzd) {
		this.jbzd = jbzd;
	}

	public Integer getCardSource() {
		return this.cardSource;
	}

	public void setCardSource(Integer cardSource) {
		this.cardSource = cardSource;
	}

	public String getDelReason() {
		return this.delReason;
	}

	public void setDelReason(String delReason) {
		this.delReason = delReason;
	}

	public String getGdFlag() {
		return this.gdFlag;
	}

	public void setGdFlag(String gdFlag) {
		this.gdFlag = gdFlag;
	}

	public String getGdUserid() {
		return this.gdUserid;
	}

	public void setGdUserid(String gdUserid) {
		this.gdUserid = gdUserid;
	}

	public Date getGdAt() {
		return this.gdAt;
	}

	public void setGdAt(Date gdAt) {
		this.gdAt = gdAt;
	}

	public Integer getBkType() {
		return this.bkType;
	}

	public void setBkType(Integer bkType) {
		this.bkType = bkType;
	}

	public String getLastoperName() {
		return this.lastoperName;
	}

	public void setLastoperName(String lastoperName) {
		this.lastoperName = lastoperName;
	}

	public Date getLastoperDate() {
		return this.lastoperDate;
	}

	public void setLastoperDate(Date lastoperDate) {
		this.lastoperDate = lastoperDate;
	}

	public String getBkTypename() {
		return this.bkTypename;
	}

	public void setBkTypename(String bkTypename) {
		this.bkTypename = bkTypename;
	}

	public String getInfectDeptNameEdit() {
		return this.infectDeptNameEdit;
	}

	public void setInfectDeptNameEdit(String infectDeptNameEdit) {
		this.infectDeptNameEdit = infectDeptNameEdit;
	}

	public String getRelid() {
		return this.relid;
	}

	public void setRelid(String relid) {
		this.relid = relid;
	}

	public String getZyid() {
		return this.zyid;
	}

	public void setZyid(String zyid) {
		this.zyid = zyid;
	}

	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public Integer getVisitId() {
		return this.visitId;
	}

	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}

	public Integer getReportVisit() {
		return this.reportVisit;
	}

	public void setReportVisit(Integer reportVisit) {
		this.reportVisit = reportVisit;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Date getInHospAt() {
		return this.inHospAt;
	}

	public void setInHospAt(Date inHospAt) {
		this.inHospAt = inHospAt;
	}

	public String getRyzdCode() {
		return this.ryzdCode;
	}

	public void setRyzdCode(String ryzdCode) {
		this.ryzdCode = ryzdCode;
	}

	public String getRyzd() {
		return this.ryzd;
	}

	public void setRyzd(String ryzd) {
		this.ryzd = ryzd;
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

	public String getAgeUnit() {
		return this.ageUnit;
	}

	public void setAgeUnit(String ageUnit) {
		this.ageUnit = ageUnit;
	}

	public Date getReportAt() {
		return this.reportAt;
	}

	public void setReportAt(Date reportAt) {
		this.reportAt = reportAt;
	}

	public String getChargeDrId() {
		return this.chargeDrId;
	}

	public void setChargeDrId(String chargeDrId) {
		this.chargeDrId = chargeDrId;
	}

	public String getReportDrName() {
		return this.reportDrName;
	}

	public void setReportDrName(String reportDrName) {
		this.reportDrName = reportDrName;
	}

	public String getReportDeptId() {
		return this.reportDeptId;
	}

	public void setReportDeptId(String reportDeptId) {
		this.reportDeptId = reportDeptId;
	}

	public Integer getReportNum() {
		return this.reportNum;
	}

	public void setReportNum(Integer reportNum) {
		this.reportNum = reportNum;
	}

	public Integer getInfectTypeId() {
		return this.infectTypeId;
	}

	public void setInfectTypeId(Integer infectTypeId) {
		this.infectTypeId = infectTypeId;
	}

	public String getRelation() {
		return this.relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getInfectDeptId() {
		return this.infectDeptId;
	}

	public void setInfectDeptId(String infectDeptId) {
		this.infectDeptId = infectDeptId;
	}

	public String getInfectDiagnId() {
		return this.infectDiagnId;
	}

	public void setInfectDiagnId(String infectDiagnId) {
		this.infectDiagnId = infectDiagnId;
	}

	public String getDateField() {
		return this.dateField;
	}

	public void setDateField(String dateField) {
		this.dateField = dateField;
	}

	public List<String> getRelationIn() {
		return this.relationIn;
	}

	public void setRelationIn(List<String> relationIn) {
		this.relationIn = relationIn;
	}

	public String getIsOkName() {
		return z.i(this.isOk).getName();
	}

	public void setIsOkName(String isOkName) {
		this.isOkName = isOkName;
	}

	public String getRegId() {
		return this.regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getChargeDrName() {
		return this.chargeDrName;
	}

	public void setChargeDrName(String chargeDrName) {
		this.chargeDrName = chargeDrName;
	}

	public String getInfectDeptName() {
		return this.infectDeptName;
	}

	public void setInfectDeptName(String infectDeptName) {
		this.infectDeptName = infectDeptName;
	}

	public String getInfectDiagnName() {
		return this.infectDiagnName;
	}

	public void setInfectDiagnName(String infectDiagnName) {
		this.infectDiagnName = infectDiagnName;
	}

	public Integer getInDays() {
		return this.inDays;
	}

	public void setInDays(Integer inDays) {
		this.inDays = inDays;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Date getOutAt() {
		return this.outAt;
	}

	public void setOutAt(Date outAt) {
		this.outAt = outAt;
	}

	public Date getInfectDate() {
		return this.infectDate;
	}

	public void setInfectDate(Date infectDate) {
		this.infectDate = infectDate;
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

	public String getYgys() {
		return this.ygys;
	}

	public void setYgys(String ygys) {
		this.ygys = ygys;
	}

	public String getSampleName() {
		return this.sampleName;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}

	public String getPathoName() {
		return this.pathoName;
	}

	public void setPathoName(String pathoName) {
		this.pathoName = pathoName;
	}

	public String getAntiName() {
		return this.antiName;
	}

	public void setAntiName(String antiName) {
		this.antiName = antiName;
	}

	public Date getConfirmDt() {
		return this.confirmDt;
	}

	public void setConfirmDt(Date confirmDt) {
		this.confirmDt = confirmDt;
	}

	public String getAuthUsername() {
		return this.authUsername;
	}

	public void setAuthUsername(String authUsername) {
		this.authUsername = authUsername;
	}

	public Date getAuthAt() {
		return this.authAt;
	}

	public void setAuthAt(Date authAt) {
		this.authAt = authAt;
	}

	public String getBk2Relid() {
		return this.bk2Relid;
	}

	public void setBk2Relid(String bk2Relid) {
		this.bk2Relid = bk2Relid;
	}

	public String getSpecDescribes() {
		return this.specDescribes;
	}

	public void setSpecDescribes(String specDescribes) {
		this.specDescribes = specDescribes;
	}

	public String getRefid() {
		return this.refid;
	}

	public void setRefid(String refid) {
		this.refid = refid;
	}

	public String getReportOrInfectDeptId() {
		return this.reportOrInfectDeptId;
	}

	public void setReportOrInfectDeptId(String reportOrInfectDeptId) {
		this.reportOrInfectDeptId = reportOrInfectDeptId;
	}

	public int getYgysCount() {
		return this.ygysCount;
	}

	public void setYgysCount(int ygysCount) {
		this.ygysCount = ygysCount;
	}

	public int getZbjCount() {
		return this.zbjCount;
	}

	public void setZbjCount(int zbjCount) {
		this.zbjCount = zbjCount;
	}

	public String getInfectTypeName() {
		return y.h(this.infectTypeId).getName();
	}

	public void setInfectTypeName(String infectTypeName) {
		this.infectTypeName = infectTypeName;
	}

	public Date getStartAt() {
		return this.startAt;
	}

	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}

	public String getInfectName() {
		return this.infectName;
	}

	public void setInfectName(String infectName) {
		this.infectName = infectName;
	}

	public String getYgysArr() {
		return this.ygysArr;
	}

	public void setYgysArr(String ygysArr) {
		this.ygysArr = ygysArr;
	}

	public String getZbjArr() {
		return this.zbjArr;
	}

	public void setZbjArr(String zbjArr) {
		this.zbjArr = zbjArr;
	}

	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
}