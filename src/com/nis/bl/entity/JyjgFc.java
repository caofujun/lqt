package com.nis.bl.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;

public class JyjgFc extends BaseEntity implements Serializable {
	private String djName;
	private String backTime;
	private String nowTime;
	private String blId;

	public String getDjName() {
		return this.djName;
	}

	public void setDjName(String djName) {
		this.djName = djName;
	}

	public String getBackTime() {
		return this.backTime;
	}

	public void setBackTime(String backTime) {
		this.backTime = backTime;
	}

	public String getNowTime() {
		return this.nowTime;
	}

	public void setNowTime(String nowTime) {
		this.nowTime = nowTime;
	}

	public String getBlId() {
		return this.blId;
	}

	public void setBlId(String blId) {
		this.blId = blId;
	}
}