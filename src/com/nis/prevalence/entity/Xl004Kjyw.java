package com.nis.prevalence.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.type.Alias;

@Alias("xl004Kjyw")
public class Xl004Kjyw extends BaseEntity implements Serializable {
	private String yjywid;
	private String brid;
	private String grid;
	private String bytid;
	private String drugId;
	private String drugName;
	private String status;
	private Date lastAt;
	private List<String> yjywidNotIn;

	public String getYjywid() {
		return this.yjywid;
	}

	public void setYjywid(String yjywid) {
		this.yjywid = yjywid;
	}

	public String getBrid() {
		return this.brid;
	}

	public void setBrid(String brid) {
		this.brid = brid;
	}

	public String getGrid() {
		return this.grid;
	}

	public void setGrid(String grid) {
		this.grid = grid;
	}

	public String getBytid() {
		return this.bytid;
	}

	public void setBytid(String bytid) {
		this.bytid = bytid;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}

	public List<String> getYjywidNotIn() {
		return this.yjywidNotIn;
	}

	public void setYjywidNotIn(List<String> yjywidNotIn) {
		this.yjywidNotIn = yjywidNotIn;
	}
}