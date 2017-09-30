package com.nis.organization.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.enums.s;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("doctor")
public class Doctor extends BaseEntity implements Serializable {
	private String id;
	private String hospId;
	private String employeeId;
	private String employeeName;
	private String deptId;
	private String zjCode;
	private String bhCode;
	private String spCode;
	private String wbCode;
	private Double flag;
	private String operDoc;
	private String drLinetype;
	private Date lastAt;
	private String inTel;
	private String phoneNo;
	private String email;
	private String inGroupNo;
	private Double authMode;
	private String authCode;
	private String cclass;
	private String state;
	private String showDeptId;
	private String showCclass;
	private String pwd;
	private String employeeType;
	private String roleId;
	private Integer source;
	private String sourceName;
	private String deptName;
	private String drLinetypeName;

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

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return this.employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getZjCode() {
		return this.zjCode;
	}

	public void setZjCode(String zjCode) {
		this.zjCode = zjCode;
	}

	public String getBhCode() {
		return this.bhCode;
	}

	public void setBhCode(String bhCode) {
		this.bhCode = bhCode;
	}

	public String getSpCode() {
		return this.spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public String getWbCode() {
		return this.wbCode;
	}

	public void setWbCode(String wbCode) {
		this.wbCode = wbCode;
	}

	public Double getFlag() {
		return this.flag;
	}

	public void setFlag(Double flag) {
		this.flag = flag;
	}

	public String getOperDoc() {
		return this.operDoc;
	}

	public void setOperDoc(String operDoc) {
		this.operDoc = operDoc;
	}

	public String getDrLinetype() {
		return this.drLinetype;
	}

	public void setDrLinetype(String drLinetype) {
		this.drLinetype = drLinetype;
	}

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}

	public String getInTel() {
		return this.inTel;
	}

	public void setInTel(String inTel) {
		this.inTel = inTel;
	}

	public String getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInGroupNo() {
		return this.inGroupNo;
	}

	public void setInGroupNo(String inGroupNo) {
		this.inGroupNo = inGroupNo;
	}

	public Double getAuthMode() {
		return this.authMode;
	}

	public void setAuthMode(Double authMode) {
		this.authMode = authMode;
	}

	public String getAuthCode() {
		return this.authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getCclass() {
		return this.cclass;
	}

	public void setCclass(String cclass) {
		this.cclass = cclass;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getShowDeptId() {
		return this.showDeptId;
	}

	public void setShowDeptId(String showDeptId) {
		this.showDeptId = showDeptId;
	}

	public String getShowCclass() {
		return this.showCclass;
	}

	public void setShowCclass(String showCclass) {
		this.showCclass = showCclass;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Integer getSource() {
		return this.source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getSourceName() {
		return s.e(this.source).getName();
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getEmployeeType() {
		return this.employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String toString() {
		return "Doctor [id=" + this.id + ", hospId=" + this.hospId + ", employeeId=" + this.employeeId
				+ ", employeeName=" + this.employeeName + ", deptId=" + this.deptId + ", showDeptId=" + this.showDeptId
				+ ", zjCode=" + this.zjCode + ", bhCode=" + this.bhCode + ", spCode=" + this.spCode + ", wbCode="
				+ this.wbCode + ", flag=" + this.flag + ", operDoc=" + this.operDoc + ", drLinetype=" + this.drLinetype
				+ ", lastAt=" + this.lastAt + ", inTel=" + this.inTel + ", showCclass=" + this.showCclass + ", phoneNo="
				+ this.phoneNo + ", email=" + this.email + ", inGroupNo=" + this.inGroupNo + ", authMode="
				+ this.authMode + ", authCode=" + this.authCode + ", cclass=" + this.cclass + ", state=" + this.state
				+ "]";
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getDrLinetypeName() {
		return this.drLinetypeName;
	}

	public void setDrLinetypeName(String drLinetypeName) {
		this.drLinetypeName = drLinetypeName;
	}
}