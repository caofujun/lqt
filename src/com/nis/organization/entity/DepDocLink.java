package com.nis.organization.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("depDocLink")
public class DepDocLink extends BaseEntity implements Serializable {
	private String id;
	private String doctorId;
	private String deptId;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String toString() {
		return "DepDocLink [id=" + this.id + ", doctorId=" + this.doctorId + ", deptId=" + this.deptId + "]";
	}
}