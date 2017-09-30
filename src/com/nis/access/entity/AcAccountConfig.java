package com.nis.access.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("acAccountConfig")
public class AcAccountConfig extends BaseEntity implements Serializable {
	private String id;
	private String userId;
	private String configKey;
	private String configValue;
	private Date updTime;

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

	public String getConfigKey() {
		return this.configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	public String getConfigValue() {
		return this.configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	public Date getUpdTime() {
		return this.updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public String toString() {
		return "AcAccountConfig [id=" + this.id + ", userId=" + this.userId + ", configKey=" + this.configKey
				+ ", configValue=" + this.configValue + ", updTime=" + this.updTime + "]";
	}
}