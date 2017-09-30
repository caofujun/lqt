package com.nis.log.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("sysOperateLog")
public class SysOperateLog extends BaseEntity implements Serializable {
	private String id;
	private String unitId;
	private String username;
	private Integer action;
	private String content;
	private String sqls;
	private String ip;
	private Date operateTime;
	private String actionName;

	public SysOperateLog() {
	}

	public SysOperateLog(String unitId, String username, Integer action, String content, String sqls, String ip) {
		this.unitId = unitId;
		this.username = username;
		this.action = action;
		this.content = content;
		this.sqls = sqls;
		this.ip = ip;
		this.operateTime = new Date();
	}

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

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAction() {
		return this.action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSqls() {
		return this.sqls;
	}

	public void setSqls(String sqls) {
		this.sqls = sqls;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getActionName() {
		return this.actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
}