package com.nis.mdr.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class Xn005Jszd extends BaseEntity implements Serializable {
	private String bactGenusId;
	private String bactGenusIdName;
	private String pycode;
	private String wbcode;
	private String bhcode;
	private String describe;
	private Date lastAt;
	private Long flag;

	public String getBactGenusId() {
		return this.bactGenusId;
	}

	public void setBactGenusId(String bactGenusId) {
		this.bactGenusId = bactGenusId;
	}

	public String getBactGenusIdName() {
		return this.bactGenusIdName;
	}

	public void setBactGenusIdName(String bactGenusIdName) {
		this.bactGenusIdName = bactGenusIdName;
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

	public String getDescribe() {
		return this.describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}

	public Long getFlag() {
		return this.flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}
}