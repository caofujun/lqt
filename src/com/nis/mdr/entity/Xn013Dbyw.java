package com.nis.mdr.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("xn013Dbyw")
public class Xn013Dbyw extends BaseEntity implements Serializable {
	private Integer itemId;
	private Integer orderno;
	private String drugTypeId;
	private String drugId;
	private String drugName;

	public Integer getItemId() {
		return this.itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getOrderno() {
		return this.orderno;
	}

	public void setOrderno(Integer orderno) {
		this.orderno = orderno;
	}

	public String getDrugTypeId() {
		return this.drugTypeId;
	}

	public void setDrugTypeId(String drugTypeId) {
		this.drugTypeId = drugTypeId;
	}

	public String getDrugId() {
		return this.drugId;
	}

	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}

	public String getDrugName() {
		return this.drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
}