package com.nis.zg.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("zg003Yyzg")
public class Zg003Yyzg extends BaseEntity implements Serializable {
	private String id;
	private String hospId;
	private String employeeId;
	private String employeeName;
	private String deptId;
	private String zjCode;
	private String bhCode;
	private String spCode;
	private String wbCode;
	private Integer flag;
	private String operDoc;
	private String drLinetype;
	private Date lastAt;
	private String inTel;
	private String phoneNo;
	private String email;
	private String inGroupNo;
	private Integer authMode;
	private String authCode;
	private String cclass;
	private String state;
	private String roleId;

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

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
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

	public Integer getAuthMode() {
		return this.authMode;
	}

	public void setAuthMode(Integer authMode) {
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

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}