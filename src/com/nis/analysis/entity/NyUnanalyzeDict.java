package com.nis.analysis.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;

public class NyUnanalyzeDict extends BaseEntity implements Serializable {
	private String dcName;

	public String getDcName() {
		return this.dcName;
	}

	public void setDcName(String dcName) {
		this.dcName = dcName;
	}
}