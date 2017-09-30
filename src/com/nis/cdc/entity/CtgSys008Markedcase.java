package com.nis.cdc.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class CtgSys008Markedcase extends BaseEntity implements Serializable {
	private String id;
	private String dataTypeid;
	private Long remark;
	private String userId;
	private String userName;
	private Date remarkTime;
	private String hospId;

	public String getHospId() {
		return this.hospId;
	}

	public void setHospId(String hospId) {
		this.hospId = hospId;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDataTypeid() {
		return this.dataTypeid;
	}

	public void setDataTypeid(String dataTypeid) {
		this.dataTypeid = dataTypeid;
	}

	public Long getRemark() {
		return this.remark;
	}

	public void setRemark(Long remark) {
		this.remark = remark;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getRemarkTime() {
		return this.remarkTime;
	}

	public void setRemarkTime(Date remarkTime) {
		this.remarkTime = remarkTime;
	}
}