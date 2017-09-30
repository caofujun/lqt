package com.nis.zg.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;

public class Zg031Sqks extends BaseEntity implements Serializable {
	private String id;
	private String authDeptId;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthDeptId() {
		return this.authDeptId;
	}

	public void setAuthDeptId(String authDeptId) {
		this.authDeptId = authDeptId;
	}

	public String toString() {
		return "Zg031Sqks [id=" + this.id + ", authDeptId=" + this.authDeptId + "]";
	}
}