package com.nis.icu.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class By001Bfgz extends BaseEntity implements Serializable {
	private String qid;
	private String outbreakTypeId;
	private String outbreakTypeName;
	private Double outbreakCycle;
	private Double minCaseNum;
	private Double totalDataType;
	private String bhCode;
	private String wbCode;
	private String spCode;
	private Date lastAt;
	private Long isoutbreak;
	private Long typeId;
	private Double outbreakRate;
	private Long compCycle;
	private Long compMode;

	public String getQid() {
		return this.qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getOutbreakTypeId() {
		return this.outbreakTypeId;
	}

	public void setOutbreakTypeId(String outbreakTypeId) {
		this.outbreakTypeId = outbreakTypeId;
	}

	public String getOutbreakTypeName() {
		return this.outbreakTypeName;
	}

	public void setOutbreakTypeName(String outbreakTypeName) {
		this.outbreakTypeName = outbreakTypeName;
	}

	public Double getOutbreakCycle() {
		return this.outbreakCycle;
	}

	public void setOutbreakCycle(Double outbreakCycle) {
		this.outbreakCycle = outbreakCycle;
	}

	public Double getMinCaseNum() {
		return this.minCaseNum;
	}

	public void setMinCaseNum(Double minCaseNum) {
		this.minCaseNum = minCaseNum;
	}

	public Double getTotalDataType() {
		return this.totalDataType;
	}

	public void setTotalDataType(Double totalDataType) {
		this.totalDataType = totalDataType;
	}

	public String getBhCode() {
		return this.bhCode;
	}

	public void setBhCode(String bhCode) {
		this.bhCode = bhCode;
	}

	public String getWbCode() {
		return this.wbCode;
	}

	public void setWbCode(String wbCode) {
		this.wbCode = wbCode;
	}

	public String getSpCode() {
		return this.spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}

	public Long getIsoutbreak() {
		return this.isoutbreak;
	}

	public void setIsoutbreak(Long isoutbreak) {
		this.isoutbreak = isoutbreak;
	}

	public Long getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Double getOutbreakRate() {
		return this.outbreakRate;
	}

	public void setOutbreakRate(Double outbreakRate) {
		this.outbreakRate = outbreakRate;
	}

	public Long getCompCycle() {
		return this.compCycle;
	}

	public void setCompCycle(Long compCycle) {
		this.compCycle = compCycle;
	}

	public Long getCompMode() {
		return this.compMode;
	}

	public void setCompMode(Long compMode) {
		this.compMode = compMode;
	}
}