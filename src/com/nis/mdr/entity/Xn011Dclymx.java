package com.nis.mdr.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("xn011Dclymx")
public class Xn011Dclymx extends BaseEntity implements Serializable {
	private Date dt;
	private String surveyDeptId;
	private String surveyDeptName;
	private Long orderno;
	private String zyid;
	private String patientId;
	private Integer visitId;
	private String patientName;
	private String sex;
	private String age;
	private String ageUnit;
	private String infectPartId;
	private String infectPartName;
	private Integer infectTypeId;
	private String infectTypeName;
	private String testOrderNo;
	private Integer itemType;
	private String itemTypeName;
	private String itemCode;
	private String itemName;
	private Date submiAt;
	private String deptId;
	private String deptName;
	private String pathoCode;
	private String pathoName;
	private String pathogenSn;
	private Integer resProp;
	private String resPropName;
	private Integer isym;
	private Integer manualResProp;
	private String changeUserid;
	private Date changeDt;
	private Integer audited;
	private Integer inteFlag;
	private String inteMode;
	private Date inteAt;
	private Integer normItemId;
	private Integer normOrderno;
	private Date affirmDt;
	private String affirmUserid;
	private String specDescribes;
	private String esbl;
	private String validationStr;
	private Integer drugFastCount;
	private Integer bbAnalTag;
	private Date bbAnalDt;
	private String id;
	private String bedNo;
	private String tablename;
	private String jcbytId;
	private String authStatus;
	private String authUser;
	private String authDate;

	public String getBedNo() {
		return this.bedNo;
	}

	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}

	public Date getDt() {
		return this.dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

	public String getSurveyDeptId() {
		return this.surveyDeptId;
	}

	public void setSurveyDeptId(String surveyDeptId) {
		this.surveyDeptId = surveyDeptId;
	}

	public String getSurveyDeptName() {
		return this.surveyDeptName;
	}

	public void setSurveyDeptName(String surveyDeptName) {
		this.surveyDeptName = surveyDeptName;
	}

	public Long getOrderno() {
		return this.orderno;
	}

	public void setOrderno(Long orderno) {
		this.orderno = orderno;
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

	public String getInfectPartId() {
		return this.infectPartId;
	}

	public void setInfectPartId(String infectPartId) {
		this.infectPartId = infectPartId;
	}

	public String getInfectPartName() {
		return this.infectPartName;
	}

	public void setInfectPartName(String infectPartName) {
		this.infectPartName = infectPartName;
	}

	public Integer getInfectTypeId() {
		return this.infectTypeId;
	}

	public void setInfectTypeId(Integer infectTypeId) {
		this.infectTypeId = infectTypeId;
	}

	public String getInfectTypeName() {
		return this.infectTypeName;
	}

	public void setInfectTypeName(String infectTypeName) {
		this.infectTypeName = infectTypeName;
	}

	public String getTestOrderNo() {
		return this.testOrderNo;
	}

	public void setTestOrderNo(String testOrderNo) {
		this.testOrderNo = testOrderNo;
	}

	public Integer getItemType() {
		return this.itemType;
	}

	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	public String getItemTypeName() {
		return this.itemTypeName;
	}

	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Date getSubmiAt() {
		return this.submiAt;
	}

	public void setSubmiAt(Date submiAt) {
		this.submiAt = submiAt;
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

	public String getPathoCode() {
		return this.pathoCode;
	}

	public void setPathoCode(String pathoCode) {
		this.pathoCode = pathoCode;
	}

	public String getPathoName() {
		return this.pathoName;
	}

	public void setPathoName(String pathoName) {
		this.pathoName = pathoName;
	}

	public String getPathogenSn() {
		return this.pathogenSn;
	}

	public void setPathogenSn(String pathogenSn) {
		this.pathogenSn = pathogenSn;
	}

	public Integer getResProp() {
		return this.resProp;
	}

	public void setResProp(Integer resProp) {
		this.resProp = resProp;
	}

	public Integer getIsym() {
		return this.isym;
	}

	public void setIsym(Integer isym) {
		this.isym = isym;
	}

	public Integer getManualResProp() {
		return this.manualResProp;
	}

	public void setManualResProp(Integer manualResProp) {
		this.manualResProp = manualResProp;
	}

	public String getChangeUserid() {
		return this.changeUserid;
	}

	public void setChangeUserid(String changeUserid) {
		this.changeUserid = changeUserid;
	}

	public Date getChangeDt() {
		return this.changeDt;
	}

	public void setChangeDt(Date changeDt) {
		this.changeDt = changeDt;
	}

	public Integer getAudited() {
		return this.audited;
	}

	public void setAudited(Integer audited) {
		this.audited = audited;
	}

	public Integer getInteFlag() {
		return this.inteFlag;
	}

	public void setInteFlag(Integer inteFlag) {
		this.inteFlag = inteFlag;
	}

	public String getInteMode() {
		return this.inteMode;
	}

	public void setInteMode(String inteMode) {
		this.inteMode = inteMode;
	}

	public Date getInteAt() {
		return this.inteAt;
	}

	public void setInteAt(Date inteAt) {
		this.inteAt = inteAt;
	}

	public Integer getNormItemId() {
		return this.normItemId;
	}

	public void setNormItemId(Integer normItemId) {
		this.normItemId = normItemId;
	}

	public Integer getNormOrderno() {
		return this.normOrderno;
	}

	public void setNormOrderno(Integer normOrderno) {
		this.normOrderno = normOrderno;
	}

	public Date getAffirmDt() {
		return this.affirmDt;
	}

	public void setAffirmDt(Date affirmDt) {
		this.affirmDt = affirmDt;
	}

	public String getAffirmUserid() {
		return this.affirmUserid;
	}

	public void setAffirmUserid(String affirmUserid) {
		this.affirmUserid = affirmUserid;
	}

	public String getSpecDescribes() {
		return this.specDescribes;
	}

	public void setSpecDescribes(String specDescribes) {
		this.specDescribes = specDescribes;
	}

	public String getEsbl() {
		return this.esbl;
	}

	public void setEsbl(String esbl) {
		this.esbl = esbl;
	}

	public String getValidationStr() {
		return this.validationStr;
	}

	public void setValidationStr(String validationStr) {
		this.validationStr = validationStr;
	}

	public Integer getDrugFastCount() {
		return this.drugFastCount;
	}

	public void setDrugFastCount(Integer drugFastCount) {
		this.drugFastCount = drugFastCount;
	}

	public Integer getBbAnalTag() {
		return this.bbAnalTag;
	}

	public void setBbAnalTag(Integer bbAnalTag) {
		this.bbAnalTag = bbAnalTag;
	}

	public Date getBbAnalDt() {
		return this.bbAnalDt;
	}

	public void setBbAnalDt(Date bbAnalDt) {
		this.bbAnalDt = bbAnalDt;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTablename() {
		return this.tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getJcbytId() {
		return this.jcbytId;
	}

	public void setJcbytId(String jcbytId) {
		this.jcbytId = jcbytId;
	}

	public String getAuthStatus() {
		return this.authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	public String getAuthUser() {
		return this.authUser;
	}

	public void setAuthUser(String authUser) {
		this.authUser = authUser;
	}

	public String getAuthDate() {
		return this.authDate;
	}

	public void setAuthDate(String authDate) {
		this.authDate = authDate;
	}

	public String getResPropName() {
		return this.resPropName;
	}

	public void setResPropName(String resPropName) {
		this.resPropName = resPropName;
	}
}