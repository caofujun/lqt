package com.nis.patient.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("resistantAnalysis")
public class ResistantAnalysis implements Serializable {
	private String zyid;
	private String patientId;
	private Integer visitId;
	private String patientName;
	private String testOrderNo;
	private String sex;
	private String age;
	private String ageUnit;
	private Date submiAt;
	private String itemTypeName;
	private String itemCode;
	private String itemName;
	private String deptId;
	private String deptName;
	private Integer isym;
	private Integer isyang;
	private String pathoCode;
	private String pathoName;
	private String pathogenSn;
	private Date testDate;
	private String pathogenId;
	private String pathogenName;
	private String bactGenusId;
	private String bactGenusIdName;
	private String surveyDeptId;
	private String surveyDeptName;
	private String id;
	private String isFungus;

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

	public Integer getIsyang() {
		return this.isyang;
	}

	public void setIsyang(Integer isyang) {
		this.isyang = isyang;
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

	public String getTestOrderNo() {
		return this.testOrderNo;
	}

	public void setTestOrderNo(String testOrderNo) {
		this.testOrderNo = testOrderNo;
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

	public Date getSubmiAt() {
		return this.submiAt;
	}

	public void setSubmiAt(Date submiAt) {
		this.submiAt = submiAt;
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

	public Integer getIsym() {
		return this.isym;
	}

	public void setIsym(Integer isym) {
		this.isym = isym;
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

	public Date getTestDate() {
		return this.testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public String getPathogenId() {
		return this.pathogenId;
	}

	public void setPathogenId(String pathogenId) {
		this.pathogenId = pathogenId;
	}

	public String getPathogenName() {
		return this.pathogenName;
	}

	public void setPathogenName(String pathogenName) {
		this.pathogenName = pathogenName;
	}

	public String getBactGenusId() {
		return this.bactGenusId;
	}

	public void setBactGenusId(String bactGenusId) {
		this.bactGenusId = bactGenusId;
	}

	public String getBactGenusIdName() {
		return this.bactGenusIdName;
	}

	public void setBactGenusIdName(String bactGenusIdName) {
		this.bactGenusIdName = bactGenusIdName;
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

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsFungus() {
		return this.isFungus;
	}

	public void setIsFungus(String isFungus) {
		this.isFungus = isFungus;
	}
}