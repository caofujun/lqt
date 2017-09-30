package com.nis.mdr.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("xn018Tsnymx")
public class Xn018Tsnymx extends BaseEntity implements Serializable {
	private String testOrderNo;
	private String pathogenSn;
	private String pathogenId;
	private String drugId;
	private String specDescribe;

	public String getTestOrderNo() {
		return this.testOrderNo;
	}

	public void setTestOrderNo(String testOrderNo) {
		this.testOrderNo = testOrderNo;
	}

	public String getPathogenSn() {
		return this.pathogenSn;
	}

	public void setPathogenSn(String pathogenSn) {
		this.pathogenSn = pathogenSn;
	}

	public String getPathogenId() {
		return this.pathogenId;
	}

	public void setPathogenId(String pathogenId) {
		this.pathogenId = pathogenId;
	}

	public String getDrugId() {
		return this.drugId;
	}

	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}

	public String getSpecDescribe() {
		return this.specDescribe;
	}

	public void setSpecDescribe(String specDescribe) {
		this.specDescribe = specDescribe;
	}
}