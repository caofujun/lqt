package com.nis.analysis.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;

public class NyUnanalyzeBc extends BaseEntity implements Serializable {
	private String id;
	private String bcCode;
	private String bcName;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBcCode() {
		return this.bcCode;
	}

	public void setBcCode(String bcCode) {
		this.bcCode = bcCode;
	}

	public String getBcName() {
		return this.bcName;
	}

	public void setBcName(String bcName) {
		this.bcName = bcName;
	}
}