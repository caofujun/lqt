package com.nis.cdc.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class CtgSys011Mark extends BaseEntity implements Serializable {
	private String masterid;
	private String mzzyid;
	private String diseaseid;
	private Long flag;
	private String optId;
	private String optName;
	private Date optDate;

	public String getOptId() {
		return this.optId;
	}

	public void setOptId(String optId) {
		this.optId = optId;
	}

	public String getOptName() {
		return this.optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	public Date getOptDate() {
		return this.optDate;
	}

	public void setOptDate(Date optDate) {
		this.optDate = optDate;
	}

	public String getMasterid() {
		return this.masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}

	public String getMzzyid() {
		return this.mzzyid;
	}

	public void setMzzyid(String mzzyid) {
		this.mzzyid = mzzyid;
	}

	public String getDiseaseid() {
		return this.diseaseid;
	}

	public void setDiseaseid(String diseaseid) {
		this.diseaseid = diseaseid;
	}

	public Long getFlag() {
		return this.flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}
}