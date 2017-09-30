package com.nis.mdr.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.serializer.a;
import com.nis.comm.serializer.b;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class ViewMdr extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 2575459418231015202L;
	private Date dt;
	private String surveyDeptId;
	private String surveyDeptName;
	private String orderno;
	private String zyid;
	private String patientId;
	private String visitId;
	private String patientName;
	private String sex;
	private String age;
	private String ageUnit;
	private String infectPartId;
	private String infectPartName;
	private String infectTypeId;
	private String infectTypeName;
	private String testOrderNo;
	private String itemType;
	private String itemTypeName;
	private String lisitemCode;
	private String lisitemName;
	private String itemCode;
	private String itemName;
	private Date submiAt;
	private String deptId;
	private String deptName;
	private Date affirmDt;
	private String pathoCode;
	private String pathoName;
	private String lispathoCode;
	private String lispathoName;
	private String bedNo;
	private String isAudited;
	private String resProp;
	private String resPropName;
	private String specDescribes;
	private String pathogenSn;
	private String normItemId;
	private String normOrderno;
	private String inteMode;
	private String bactGenusIdName;
	private String reportDrName;
	private String dateType;
	private String startDate;
	private String endDate;
	private String patientState;
	private String deptType;
	private String searchdeptId;
	private String jszd;
	private String byt;
	private String kjyw;
	private String esbl;
	private String qc;
	private String isolation;
	private String key;
	private String[] resPropList;
	private String[] specDescribesList;
	private String[] infectTypeIdList;
	private String[] esblList;
	private String date;
	private String specType;
	private String gl;
	private String rsId;
	private String gx;
	private List<String> deptIdIn;
	private String grbw;
	private String dnlx;
	private String grsj;
	private String authStatus;
	private Date glDate;
	private Date glCancelDate;
	private String authStatusMdr;
	private String authUser;
	private String authDate;

	@JsonSerialize(using = a.class)
	public Date getDt() {
		return this.dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

	public String getDnlx() {
		return this.dnlx;
	}

	public void setDnlx(String dnlx) {
		this.dnlx = dnlx;
	}

	public Date getGlDate() {
		return this.glDate;
	}

	public void setGlDate(Date glDate) {
		this.glDate = glDate;
	}

	public Date getGlCancelDate() {
		return this.glCancelDate;
	}

	public void setGlCancelDate(Date glCancelDate) {
		this.glCancelDate = glCancelDate;
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

	public String getOrderno() {
		return this.orderno;
	}

	public void setOrderno(String orderno) {
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

	public String getInfectTypeId() {
		return this.infectTypeId;
	}

	public void setInfectTypeId(String infectTypeId) {
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

	public String getItemType() {
		return this.itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemTypeName() {
		return this.itemTypeName;
	}

	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}

	public String getLisitemCode() {
		return this.lisitemCode;
	}

	public void setLisitemCode(String lisitemCode) {
		this.lisitemCode = lisitemCode;
	}

	public String getLisitemName() {
		return this.lisitemName;
	}

	public void setLisitemName(String lisitemName) {
		this.lisitemName = lisitemName;
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

	@JsonSerialize(using = b.class)
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

	public Date getAffirmDt() {
		return this.affirmDt;
	}

	public void setAffirmDt(Date affirmDt) {
		this.affirmDt = affirmDt;
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

	public String getLispathoCode() {
		return this.lispathoCode;
	}

	public void setLispathoCode(String lispathoCode) {
		this.lispathoCode = lispathoCode;
	}

	public String getLispathoName() {
		return this.lispathoName;
	}

	public void setLispathoName(String lispathoName) {
		this.lispathoName = lispathoName;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getBedNo() {
		return this.bedNo;
	}

	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}

	public String getIsAudited() {
		return this.isAudited;
	}

	public void setIsAudited(String isAudited) {
		this.isAudited = isAudited;
	}

	public String getResPropName() {
		return this.resPropName;
	}

	public void setResPropName(String resPropName) {
		this.resPropName = resPropName;
	}

	public String getResProp() {
		return this.resProp;
	}

	public void setResProp(String resProp) {
		this.resProp = resProp;
	}

	public String getSpecDescribes() {
		return this.specDescribes;
	}

	public void setSpecDescribes(String specDescribes) {
		this.specDescribes = specDescribes;
	}

	public String getPathogenSn() {
		return this.pathogenSn;
	}

	public void setPathogenSn(String pathogenSn) {
		this.pathogenSn = pathogenSn;
	}

	public String getNormItemId() {
		return this.normItemId;
	}

	public void setNormItemId(String normItemId) {
		this.normItemId = normItemId;
	}

	public String getNormOrderno() {
		return this.normOrderno;
	}

	public void setNormOrderno(String normOrderno) {
		this.normOrderno = normOrderno;
	}

	public String getInteMode() {
		return this.inteMode;
	}

	public void setInteMode(String inteMode) {
		this.inteMode = inteMode;
	}

	public String getDateType() {
		return this.dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public String getPatientState() {
		return this.patientState;
	}

	public void setPatientState(String patientState) {
		this.patientState = patientState;
	}

	public String getSearchdeptId() {
		return this.searchdeptId;
	}

	public void setSearchdeptId(String searchdeptId) {
		this.searchdeptId = searchdeptId;
	}

	public String getJszd() {
		return this.jszd;
	}

	public void setJszd(String jszd) {
		this.jszd = jszd;
	}

	public String getEsbl() {
		return this.esbl;
	}

	public void setEsbl(String esbl) {
		this.esbl = esbl;
	}

	public String getQc() {
		return this.qc;
	}

	public void setQc(String qc) {
		this.qc = qc;
	}

	public String getDeptType() {
		return this.deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	public String[] getResPropList() {
		return this.resPropList;
	}

	public void setResPropList(String[] resPropList) {
		this.resPropList = resPropList;
	}

	public String[] getSpecDescribesList() {
		return this.specDescribesList;
	}

	public void setSpecDescribesList(String[] specDescribesList) {
		this.specDescribesList = specDescribesList;
	}

	public String[] getInfectTypeIdList() {
		return this.infectTypeIdList;
	}

	public void setInfectTypeIdList(String[] infectTypeIdList) {
		this.infectTypeIdList = infectTypeIdList;
	}

	public String getBactGenusIdName() {
		return this.bactGenusIdName;
	}

	public void setBactGenusIdName(String bactGenusIdName) {
		this.bactGenusIdName = bactGenusIdName;
	}

	public String[] getEsblList() {
		return this.esblList;
	}

	public void setEsblList(String[] esblList) {
		this.esblList = esblList;
	}

	public String getByt() {
		return this.byt;
	}

	public void setByt(String byt) {
		this.byt = byt;
	}

	public String getKjyw() {
		return this.kjyw;
	}

	public void setKjyw(String kjyw) {
		this.kjyw = kjyw;
	}

	public String getIsolation() {
		return this.glCancelDate != null ? "1" : (this.glDate != null ? "2" : null);
	}

	public void setIsolation(String isolation) {
		this.isolation = isolation;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSpecType() {
		return this.specType;
	}

	public void setSpecType(String specType) {
		this.specType = specType;
	}

	public String getGl() {
		return this.gl;
	}

	public void setGl(String gl) {
		this.gl = gl;
	}

	public String getRsId() {
		return this.rsId;
	}

	public void setRsId(String rsId) {
		this.rsId = rsId;
	}

	public String getGx() {
		return this.gx;
	}

	public void setGx(String gx) {
		this.gx = gx;
	}

	public List<String> getDeptIdIn() {
		return this.deptIdIn;
	}

	public void setDeptIdIn(List<String> deptIdIn) {
		this.deptIdIn = deptIdIn;
	}

	public String getGrbw() {
		return this.grbw;
	}

	public void setGrbw(String grbw) {
		this.grbw = grbw;
	}

	public String getGrsj() {
		return this.grsj;
	}

	public void setGrsj(String grsj) {
		this.grsj = grsj;
	}

	public String getAuthStatus() {
		return this.authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	public String getReportDrName() {
		return this.reportDrName;
	}

	public void setReportDrName(String reportDrName) {
		this.reportDrName = reportDrName;
	}

	public String getAuthStatusMdr() {
		return this.authStatusMdr;
	}

	public void setAuthStatusMdr(String authStatusMdr) {
		this.authStatusMdr = authStatusMdr;
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
}