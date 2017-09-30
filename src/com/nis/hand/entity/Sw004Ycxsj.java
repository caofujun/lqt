package com.nis.hand.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;

public class Sw004Ycxsj extends BaseEntity implements Serializable {
	private String keyId;
	private String dcId;
	private String sjId;
	private String ryType;
	private String ryName;
	private String sjList;
	private String swsList;
	private Long isRight;
	private String errReason;
	private Long orderBy;

	public Long getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(Long orderBy) {
		this.orderBy = orderBy;
	}

	public String getKeyId() {
		return this.keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public String getDcId() {
		return this.dcId;
	}

	public void setDcId(String dcId) {
		this.dcId = dcId;
	}

	public String getSjId() {
		return this.sjId;
	}

	public void setSjId(String sjId) {
		this.sjId = sjId;
	}

	public String getRyType() {
		return this.ryType;
	}

	public void setRyType(String ryType) {
		this.ryType = ryType;
	}

	public String getRyName() {
		return this.ryName;
	}

	public void setRyName(String ryName) {
		this.ryName = ryName;
	}

	public String getSjList() {
		return this.sjList;
	}

	public void setSjList(String sjList) {
		this.sjList = sjList;
	}

	public String getSwsList() {
		return this.swsList;
	}

	public void setSwsList(String swsList) {
		this.swsList = swsList;
	}

	public Long getIsRight() {
		return this.isRight;
	}

	public void setIsRight(Long isRight) {
		this.isRight = isRight;
	}

	public String getErrReason() {
		return this.errReason;
	}

	public void setErrReason(String errReason) {
		this.errReason = errReason;
	}
}