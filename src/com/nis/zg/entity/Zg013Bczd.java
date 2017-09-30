package com.nis.zg.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class Zg013Bczd extends BaseEntity implements Serializable {
	private String itemName;
	private Double itemClass;
	private String spCode;
	private Double flag;
	private Double exType;
	private Date lastAt;

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getItemClass() {
		return this.itemClass;
	}

	public void setItemClass(Double itemClass) {
		this.itemClass = itemClass;
	}

	public String getSpCode() {
		return this.spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public Double getFlag() {
		return this.flag;
	}

	public void setFlag(Double flag) {
		this.flag = flag;
	}

	public Double getExType() {
		return this.exType;
	}

	public void setExType(Double exType) {
		this.exType = exType;
	}

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}
}