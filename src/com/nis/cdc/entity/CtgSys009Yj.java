package com.nis.cdc.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class CtgSys009Yj extends BaseEntity implements Serializable {
	private String masterid;
	private String patientType;
	private String patientId;
	private String mzzyid;
	private String patientName;
	private String yjcode;
	private String yjname;
	private Date yjdt;
	private String yjsource;
	private String yjcontent;
	private String sourceId;
	private String memo;

	public String getMasterid() {
		return this.masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}

	public String getPatientType() {
		return this.patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getMzzyid() {
		return this.mzzyid;
	}

	public void setMzzyid(String mzzyid) {
		this.mzzyid = mzzyid;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getYjcode() {
		return this.yjcode;
	}

	public void setYjcode(String yjcode) {
		this.yjcode = yjcode;
	}

	public String getYjname() {
		return this.yjname;
	}

	public void setYjname(String yjname) {
		this.yjname = yjname;
	}

	public Date getYjdt() {
		return this.yjdt;
	}

	public void setYjdt(Date yjdt) {
		this.yjdt = yjdt;
	}

	public String getYjsource() {
		return this.yjsource;
	}

	public void setYjsource(String yjsource) {
		this.yjsource = yjsource;
	}

	public String getYjcontent() {
		return this.yjcontent;
	}

	public void setYjcontent(String yjcontent) {
		this.yjcontent = yjcontent;
	}

	public String getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}