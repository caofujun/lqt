package com.nis.intervene.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;

public class FxPatientIndex extends BaseEntity implements Serializable {
	private String deptName;
	private String deptCode;
	private Integer fxCount;
	private Integer zbCount;
	private Integer ygyCount;
	private Integer wgyCount;
	private Integer ypgCount;
	private Integer wpgCount;
	private Integer wzxCount;
	private Integer yqxCount;

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getFxCount() {
		return this.fxCount;
	}

	public void setFxCount(Integer fxCount) {
		this.fxCount = fxCount;
	}

	public String getDeptCode() {
		return this.deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public Integer getZbCount() {
		return this.zbCount;
	}

	public void setZbCount(Integer zbCount) {
		this.zbCount = zbCount;
	}

	public Integer getYgyCount() {
		return this.ygyCount;
	}

	public void setYgyCount(Integer ygyCount) {
		this.ygyCount = ygyCount;
	}

	public Integer getWgyCount() {
		return this.wgyCount;
	}

	public void setWgyCount(Integer wgyCount) {
		this.wgyCount = wgyCount;
	}

	public Integer getYpgCount() {
		return this.ypgCount;
	}

	public void setYpgCount(Integer ypgCount) {
		this.ypgCount = ypgCount;
	}

	public Integer getWpgCount() {
		return this.wpgCount;
	}

	public void setWpgCount(Integer wpgCount) {
		this.wpgCount = wpgCount;
	}

	public Integer getWzxCount() {
		return this.wzxCount;
	}

	public void setWzxCount(Integer wzxCount) {
		this.wzxCount = wzxCount;
	}

	public Integer getYqxCount() {
		return this.yqxCount;
	}

	public void setYqxCount(Integer yqxCount) {
		this.yqxCount = yqxCount;
	}
}