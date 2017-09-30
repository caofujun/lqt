package com.nis.mdr.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("xn004Trny")
public class Xn004Trny extends BaseEntity implements Serializable {
	private String pathogenId;
	private String pathogenName;
	private String drugId;
	private String drugName;
	private Date lastAt;
	private String pathogenId2;
	private String drugId2;

	public String getPathogenId() {
		return this.pathogenId;
	}

	public void setPathogenId(String pathogenId) {
		this.pathogenId = pathogenId;
	}

	public String getPathogenName() {
		return this.pathogenName;
	}

	public void setPathogenName(String pathogenName) {
		this.pathogenName = pathogenName;
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

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}

	public String getPathogenId2() {
		return this.pathogenId2;
	}

	public void setPathogenId2(String pathogenId2) {
		this.pathogenId2 = pathogenId2;
	}

	public String getDrugId2() {
		return this.drugId2;
	}

	public void setDrugId2(String drugId2) {
		this.drugId2 = drugId2;
	}
}