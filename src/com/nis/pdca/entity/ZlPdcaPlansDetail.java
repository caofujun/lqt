package com.nis.pdca.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class ZlPdcaPlansDetail extends BaseEntity implements Serializable {
	private String puid;
	private String pdSubid;
	private String stepNo;
	private String stepName;
	private String stepContent;
	private Long status;
	private Long xh;
	private Date wcDate;
	private String statusName;

	public String getPuid() {
		return this.puid;
	}

	public void setPuid(String puid) {
		this.puid = puid;
	}

	public String getPdSubid() {
		return this.pdSubid;
	}

	public void setPdSubid(String pdSubid) {
		this.pdSubid = pdSubid;
	}

	public String getStepNo() {
		return this.stepNo;
	}

	public void setStepNo(String stepNo) {
		this.stepNo = stepNo;
	}

	public String getStepName() {
		return this.stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public String getStepContent() {
		return this.stepContent;
	}

	public void setStepContent(String stepContent) {
		this.stepContent = stepContent;
	}

	public Long getStatus() {
		return this.status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getXh() {
		return this.xh;
	}

	public void setXh(Long xh) {
		this.xh = xh;
	}

	public String getStatusName() {
		return this.statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Date getWcDate() {
		return this.wcDate;
	}

	public void setWcDate(Date wcDate) {
		this.wcDate = wcDate;
	}
}