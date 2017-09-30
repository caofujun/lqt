package com.nis.mdr.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;

public class Xn014Liskjyw extends BaseEntity implements Serializable {
	private String drugid;
	private String drugname;
	private String pycode;
	private String wbcode;
	private String bhcode;
	private String counterDrugid;
	private String drugFx;
	private String counterDrugName;

	public String getDrugid() {
		return this.drugid;
	}

	public void setDrugid(String drugid) {
		this.drugid = drugid;
	}

	public String getDrugname() {
		return this.drugname;
	}

	public void setDrugname(String drugname) {
		this.drugname = drugname;
	}

	public String getPycode() {
		return this.pycode;
	}

	public void setPycode(String pycode) {
		this.pycode = pycode;
	}

	public String getWbcode() {
		return this.wbcode;
	}

	public void setWbcode(String wbcode) {
		this.wbcode = wbcode;
	}

	public String getBhcode() {
		return this.bhcode;
	}

	public void setBhcode(String bhcode) {
		this.bhcode = bhcode;
	}

	public String getCounterDrugid() {
		return this.counterDrugid;
	}

	public void setCounterDrugid(String counterDrugid) {
		this.counterDrugid = counterDrugid;
	}

	public String getDrugFx() {
		return this.drugFx;
	}

	public void setDrugFx(String drugFx) {
		this.drugFx = drugFx;
	}

	public String getCounterDrugName() {
		return this.counterDrugName;
	}

	public void setCounterDrugName(String counterDrugName) {
		this.counterDrugName = counterDrugName;
	}
}