package com.nis.jk.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("jkPatientBc")
public class JkPatientBc extends BaseEntity implements Serializable {
	private String id;
	private String zyid;
	private String patientId;
	private Integer visitId;
	private String deptId;
	private String deptName;
	private Date enterDate;
	private String caseClass;
	private String bcCode;
	private String bcName;
	private String courseContent;
	private Date createAt;
	private Date updTime;
	private Long updFlag;

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

	public Date getEnterDate() {
		return this.enterDate;
	}

	public void setEnterDate(Date enterDate) {
		this.enterDate = enterDate;
	}

	public String getCaseClass() {
		return this.caseClass;
	}

	public void setCaseClass(String caseClass) {
		this.caseClass = caseClass;
	}

	public String getBcCode() {
		return this.bcCode;
	}

	public void setBcCode(String bcCode) {
		this.bcCode = bcCode;
	}

	public String getBcName() {
		return this.bcName;
	}

	public void setBcName(String bcName) {
		this.bcName = bcName;
	}

	public String getCourseContent() {
		return this.courseContent;
	}

	public void setCourseContent(String courseContent) {
		this.courseContent = courseContent;
	}

	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdTime() {
		return this.updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public Long getUpdFlag() {
		return this.updFlag;
	}

	public void setUpdFlag(Long updFlag) {
		this.updFlag = updFlag;
	}
}