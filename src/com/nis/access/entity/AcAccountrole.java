package com.nis.access.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("acAccountrole")
public class AcAccountrole extends BaseEntity implements Serializable {
	private String id;
	private String userId;
	private String roleId;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}