package com.nis.mdr.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class Xn006Kjywfl extends BaseEntity implements Serializable {
	private String drugTypeId;
	private String drugTypeName;
	private String pycode;
	private String wbcode;
	private String bhcode;
	private Date lastAt;
	private Long iscustom;
	private Long flag;

	public String getDrugTypeId() {
		return this.drugTypeId;
	}

	public void setDrugTypeId(String drugTypeId) {
		this.drugTypeId = drugTypeId;
	}

	public String getDrugTypeName() {
		return this.drugTypeName;
	}

	public void setDrugTypeName(String drugTypeName) {
		this.drugTypeName = drugTypeName;
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

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}

	public Long getIscustom() {
		return this.iscustom;
	}

	public void setIscustom(Long iscustom) {
		this.iscustom = iscustom;
	}

	public Long getFlag() {
		return this.flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}
}