package com.nis.monitor.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("xn020Gadc")
public class Xn020Gadc extends BaseEntity implements Serializable {
	private String id;
	private String zyid;
	private String itemCode;
	private String pathoCode;
	private String patientFrom;
	private String diagnosisName;
	private String isInfect;
	private String infectTypeName;
	private String infectPartName;
	private String instruInfect;
	private String infectDept;
	private Date infectDt;
	private String jbyh;
	private String gryh;
	private Date lastAt;
	private String lastBy;
	private String lastName;
	private String itemName;
	private String pathoName;
	private String resPropName;
	private String testOrderNo;
	private String specDescribes;
	private String surveyDeptId;
	private String surveyDeptName;
	private Date submiAt;
	private String refRowid;
	private Date dt;
	private String refId;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getZyid() {
		return this.zyid;
	}

	public void setZyid(String zyid) {
		this.zyid = zyid;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getPathoCode() {
		return this.pathoCode;
	}

	public void setPathoCode(String pathoCode) {
		this.pathoCode = pathoCode;
	}

	public String getPatientFrom() {
		return this.patientFrom;
	}

	public void setPatientFrom(String patientFrom) {
		this.patientFrom = patientFrom;
	}

	public String getDiagnosisName() {
		return this.diagnosisName;
	}

	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}

	public String getIsInfect() {
		return this.isInfect;
	}

	public void setIsInfect(String isInfect) {
		this.isInfect = isInfect;
	}

	public String getInfectTypeName() {
		return this.infectTypeName;
	}

	public void setInfectTypeName(String infectTypeName) {
		this.infectTypeName = infectTypeName;
	}

	public String getInfectPartName() {
		return this.infectPartName;
	}

	public void setInfectPartName(String infectPartName) {
		this.infectPartName = infectPartName;
	}

	public String getInstruInfect() {
		return this.instruInfect;
	}

	public void setInstruInfect(String instruInfect) {
		this.instruInfect = instruInfect;
	}

	public String getInfectDept() {
		return this.infectDept;
	}

	public void setInfectDept(String infectDept) {
		this.infectDept = infectDept;
	}

	public Date getInfectDt() {
		return this.infectDt;
	}

	public void setInfectDt(Date infectDt) {
		this.infectDt = infectDt;
	}

	public String getJbyh() {
		return this.jbyh;
	}

	public void setJbyh(String jbyh) {
		this.jbyh = jbyh;
	}

	public String getGryh() {
		return this.gryh;
	}

	public void setGryh(String gryh) {
		this.gryh = gryh;
	}

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}

	public String getLastBy() {
		return this.lastBy;
	}

	public void setLastBy(String lastBy) {
		this.lastBy = lastBy;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getPathoName() {
		return this.pathoName;
	}

	public void setPathoName(String pathoName) {
		this.pathoName = pathoName;
	}

	public String getResPropName() {
		return this.resPropName;
	}

	public void setResPropName(String resPropName) {
		this.resPropName = resPropName;
	}

	public String getTestOrderNo() {
		return this.testOrderNo;
	}

	public void setTestOrderNo(String testOrderNo) {
		this.testOrderNo = testOrderNo;
	}

	public String getSpecDescribes() {
		return this.specDescribes;
	}

	public void setSpecDescribes(String specDescribes) {
		this.specDescribes = specDescribes;
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

	public Date getSubmiAt() {
		return this.submiAt;
	}

	public void setSubmiAt(Date submiAt) {
		this.submiAt = submiAt;
	}

	public String getRefRowid() {
		return this.refRowid;
	}

	public void setRefRowid(String refRowid) {
		this.refRowid = refRowid;
	}

	public Date getDt() {
		return this.dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

	public String getRefId() {
		return this.refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}
}