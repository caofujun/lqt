package com.nis.zg.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class Zg021Ygys extends BaseEntity implements Serializable {
	private String factorId;
	private String factorName;
	private String factorKind;
	private String zjf;
	private Long flag;
	private Date lastAt;

	public String getFactorId() {
		return this.factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public String getFactorName() {
		return this.factorName;
	}

	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}

	public String getFactorKind() {
		return this.factorKind;
	}

	public void setFactorKind(String factorKind) {
		this.factorKind = factorKind;
	}

	public String getZjf() {
		return this.zjf;
	}

	public void setZjf(String zjf) {
		this.zjf = zjf;
	}

	public Long getFlag() {
		return this.flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}
}