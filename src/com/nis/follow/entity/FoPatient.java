package com.nis.follow.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class FoPatient extends BaseEntity implements Serializable {
	private String foId;
	private String patientId;
	private String followName;
	private Date followTime;
	private String deptId;

	public String getFoId() {
		return this.foId;
	}

	public void setFoId(String foId) {
		this.foId = foId;
	}

	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getFollowName() {
		return this.followName;
	}

	public void setFollowName(String followName) {
		this.followName = followName;
	}

	public Date getFollowTime() {
		return this.followTime;
	}

	public void setFollowTime(Date followTime) {
		this.followTime = followTime;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
}