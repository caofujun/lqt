package com.nis.hygiene.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.enums.s;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("hw010Zzry")
public class Hw010Zzry extends BaseEntity implements Serializable {
	private String deptId;
	private String employeeId;
	private Integer source;
	private String employeeName;
	private String djDeptId;
	private String operateDeptId;
	private String operateDeptName;
	private String deptName;
	private String roleName;
	private String sourceName;

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getSource() {
		return this.source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getEmployeeName() {
		return this.employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDjDeptId() {
		return this.djDeptId;
	}

	public void setDjDeptId(String djDeptId) {
		this.djDeptId = djDeptId;
	}

	public String getOperateDeptName() {
		return this.operateDeptName;
	}

	public void setOperateDeptName(String operateDeptName) {
		this.operateDeptName = operateDeptName;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getOperateDeptId() {
		return this.operateDeptId;
	}

	public void setOperateDeptId(String operateDeptId) {
		this.operateDeptId = operateDeptId;
	}

	public String getSourceName() {
		return s.e(this.source).getName();
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
}