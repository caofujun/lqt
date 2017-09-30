package com.nis.comm.entity;

import java.io.Serializable;
import java.util.List;

public class XmlRequest implements Serializable {
	private String unitId;
	private List<?> listData;

	public List<?> getData() {
		return this.listData;
	}

	public void setData(List<?> data) {
		this.listData = data;
	}

	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
}