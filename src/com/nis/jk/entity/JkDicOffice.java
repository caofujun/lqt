package com.nis.jk.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("jkDicOffice")
public class JkDicOffice extends BaseEntity implements Serializable {
	private String id;
	private String deptId;
	private String deptName;
	private String depType;
	private String depTypeName;
	private String depStatus;
	private String parentDepId;
	private String parentDepName;
	private Date updTime;
	private Long updFlag;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getDepType() {
		return this.depType;
	}

	public void setDepType(String depType) {
		this.depType = depType;
	}

	public String getDepTypeName() {
		return this.depTypeName;
	}

	public void setDepTypeName(String depTypeName) {
		this.depTypeName = depTypeName;
	}

	public String getDepStatus() {
		return this.depStatus;
	}

	public void setDepStatus(String depStatus) {
		this.depStatus = depStatus;
	}

	public String getParentDepId() {
		return this.parentDepId;
	}

	public void setParentDepId(String parentDepId) {
		this.parentDepId = parentDepId;
	}

	public String getParentDepName() {
		return this.parentDepName;
	}

	public void setParentDepName(String parentDepName) {
		this.parentDepName = parentDepName;
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