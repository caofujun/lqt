package com.nis.report.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class SysModuleExplain extends BaseEntity implements Serializable {
	private String mid;
	private String mkName;
	private String mkExplain;
	private Date updateTime;
	private String createUser;
	private String createName;

	public String getMid() {
		return this.mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMkName() {
		return this.mkName;
	}

	public void setMkName(String mkName) {
		this.mkName = mkName;
	}

	public String getMkExplain() {
		return this.mkExplain;
	}

	public void setMkExplain(String mkExplain) {
		this.mkExplain = mkExplain;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateName() {
		return this.createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}
}