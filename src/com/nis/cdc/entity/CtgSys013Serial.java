package com.nis.cdc.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class CtgSys013Serial extends BaseEntity implements Serializable {
	private String idnumber;
	private String masterid;
	private String serialnumber;
	private Date recyDt;
	private String recyPersonid;
	private String recyPersonname;
	private Long isReuse;
	private String reMasterid;

	public String getIdnumber() {
		return this.idnumber;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	public String getMasterid() {
		return this.masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}

	public String getSerialnumber() {
		return this.serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	public Date getRecyDt() {
		return this.recyDt;
	}

	public void setRecyDt(Date recyDt) {
		this.recyDt = recyDt;
	}

	public String getRecyPersonid() {
		return this.recyPersonid;
	}

	public void setRecyPersonid(String recyPersonid) {
		this.recyPersonid = recyPersonid;
	}

	public String getRecyPersonname() {
		return this.recyPersonname;
	}

	public void setRecyPersonname(String recyPersonname) {
		this.recyPersonname = recyPersonname;
	}

	public Long getIsReuse() {
		return this.isReuse;
	}

	public void setIsReuse(Long isReuse) {
		this.isReuse = isReuse;
	}

	public String getReMasterid() {
		return this.reMasterid;
	}

	public void setReMasterid(String reMasterid) {
		this.reMasterid = reMasterid;
	}
}