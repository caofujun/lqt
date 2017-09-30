package com.nis.zg.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;

public class Zg027LisbbPp extends BaseEntity implements Serializable {
	private String bbid;
	private String bbmc;
	private String itemCode;
	private String itemName;
	private String bbppId;

	public String getBbid() {
		return this.bbid;
	}

	public void setBbid(String bbid) {
		this.bbid = bbid;
	}

	public String getBbmc() {
		return this.bbmc;
	}

	public void setBbmc(String bbmc) {
		this.bbmc = bbmc;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getBbppId() {
		return this.bbppId;
	}

	public void setBbppId(String bbppId) {
		this.bbppId = bbppId;
	}
}