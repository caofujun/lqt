package com.nis.log.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("sysLog")
public class SysLog extends BaseEntity implements Serializable {
	private String id;
	private String unitId;
	private String userName;
	private String logArea;
	private String logFun;
	private String logType;
	private String businessId;
	private Date createTime;
	private String logContent;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLogArea() {
		return this.logArea;
	}

	public void setLogArea(String logArea) {
		this.logArea = logArea;
	}

	public String getLogFun() {
		return this.logFun;
	}

	public void setLogFun(String logFun) {
		this.logFun = logFun;
	}

	public String getLogType() {
		return this.logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getBusinessId() {
		return this.businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLogContent() {
		return this.logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}
}