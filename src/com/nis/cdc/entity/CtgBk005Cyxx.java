package com.nis.cdc.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class CtgBk005Cyxx extends BaseEntity implements Serializable {
	private String subid;
	private String masterid;
	private Long orderno;
	private String specimennumber;
	private String specimentype;
	private String specimencount;
	private String numberofunits;
	private Date samplingdate;
	private String note;

	public String getSubid() {
		return this.subid;
	}

	public void setSubid(String subid) {
		this.subid = subid;
	}

	public String getMasterid() {
		return this.masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}

	public Long getOrderno() {
		return this.orderno;
	}

	public void setOrderno(Long orderno) {
		this.orderno = orderno;
	}

	public String getSpecimennumber() {
		return this.specimennumber;
	}

	public void setSpecimennumber(String specimennumber) {
		this.specimennumber = specimennumber;
	}

	public String getSpecimentype() {
		return this.specimentype;
	}

	public void setSpecimentype(String specimentype) {
		this.specimentype = specimentype;
	}

	public String getSpecimencount() {
		return this.specimencount;
	}

	public void setSpecimencount(String specimencount) {
		this.specimencount = specimencount;
	}

	public String getNumberofunits() {
		return this.numberofunits;
	}

	public void setNumberofunits(String numberofunits) {
		this.numberofunits = numberofunits;
	}

	public Date getSamplingdate() {
		return this.samplingdate;
	}

	public void setSamplingdate(Date samplingdate) {
		this.samplingdate = samplingdate;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}