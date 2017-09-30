package com.nis.jk.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("jkTranDatelog")
public class JkTranDatelog extends BaseEntity implements Serializable {
	private String syncCode;
	private String syncName;
	private Date paramBegintime;
	private Date paramEndtime;
	private String lastId;
	private String lastStatus;
	private Date lastSyncTime;
	private String lastLog;

	public String getSyncCode() {
		return this.syncCode;
	}

	public void setSyncCode(String syncCode) {
		this.syncCode = syncCode;
	}

	public String getSyncName() {
		return this.syncName;
	}

	public void setSyncName(String syncName) {
		this.syncName = syncName;
	}

	public Date getParamBegintime() {
		return this.paramBegintime;
	}

	public void setParamBegintime(Date paramBegintime) {
		this.paramBegintime = paramBegintime;
	}

	public Date getParamEndtime() {
		return this.paramEndtime;
	}

	public void setParamEndtime(Date paramEndtime) {
		this.paramEndtime = paramEndtime;
	}

	public String getLastId() {
		return this.lastId;
	}

	public void setLastId(String lastId) {
		this.lastId = lastId;
	}

	public String getLastStatus() {
		return this.lastStatus;
	}

	public void setLastStatus(String lastStatus) {
		this.lastStatus = lastStatus;
	}

	public Date getLastSyncTime() {
		return this.lastSyncTime;
	}

	public void setLastSyncTime(Date lastSyncTime) {
		this.lastSyncTime = lastSyncTime;
	}

	public String getLastLog() {
		return this.lastLog;
	}

	public void setLastLog(String lastLog) {
		this.lastLog = lastLog;
	}
}