package com.nis.prevalence.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("xl005Kjywzl")
public class Xl005Kjywzl extends BaseEntity implements Serializable {
	private String ywzlid;
	private String brid;
	private String drugKindId;
	private String drugKindName;
	private Date lastAt;

	public String getYwzlid() {
		return this.ywzlid;
	}

	public void setYwzlid(String ywzlid) {
		this.ywzlid = ywzlid;
	}

	public String getBrid() {
		return this.brid;
	}

	public void setBrid(String brid) {
		this.brid = brid;
	}

	public String getDrugKindId() {
		return this.drugKindId;
	}

	public void setDrugKindId(String drugKindId) {
		this.drugKindId = drugKindId;
	}

	public String getDrugKindName() {
		return this.drugKindName;
	}

	public void setDrugKindName(String drugKindName) {
		this.drugKindName = drugKindName;
	}

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}
}