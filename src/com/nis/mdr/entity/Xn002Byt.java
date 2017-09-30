package com.nis.mdr.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class Xn002Byt extends BaseEntity implements Serializable {
	private String pathogenId;
	private String pathogenName;
	private String pathogenEnName;
	private String bhCode;
	private String spCode;
	private String wbCode;
	private String kindId;
	private String rsId;
	private String bactGenusId;
	private String cnId;
	private Long flag;
	private Long ifCommon;
	private Date lastAt;
	private Long isFungus;
	private String startDate;
	private String endDate;
	private String itemName;
	private String jsName;
	private String jszd;
	private String isByt;

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

	public String getPathogenEnName() {
		return this.pathogenEnName;
	}

	public void setPathogenEnName(String pathogenEnName) {
		this.pathogenEnName = pathogenEnName;
	}

	public String getBhCode() {
		return this.bhCode;
	}

	public void setBhCode(String bhCode) {
		this.bhCode = bhCode;
	}

	public String getSpCode() {
		return this.spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public String getWbCode() {
		return this.wbCode;
	}

	public void setWbCode(String wbCode) {
		this.wbCode = wbCode;
	}

	public String getKindId() {
		return this.kindId;
	}

	public void setKindId(String kindId) {
		this.kindId = kindId;
	}

	public String getRsId() {
		return this.rsId;
	}

	public void setRsId(String rsId) {
		this.rsId = rsId;
	}

	public String getBactGenusId() {
		return this.bactGenusId;
	}

	public void setBactGenusId(String bactGenusId) {
		this.bactGenusId = bactGenusId;
	}

	public String getCnId() {
		return this.cnId;
	}

	public void setCnId(String cnId) {
		this.cnId = cnId;
	}

	public Long getFlag() {
		return this.flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	public Long getIfCommon() {
		return this.ifCommon;
	}

	public void setIfCommon(Long ifCommon) {
		this.ifCommon = ifCommon;
	}

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}

	public Long getIsFungus() {
		return this.isFungus;
	}

	public void setIsFungus(Long isFungus) {
		this.isFungus = isFungus;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getIsByt() {
		return this.isByt;
	}

	public void setIsByt(String isByt) {
		this.isByt = isByt;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getJsName() {
		return this.jsName;
	}

	public void setJsName(String jsName) {
		this.jsName = jsName;
	}

	public String getJszd() {
		return this.jszd;
	}

	public void setJszd(String jszd) {
		this.jszd = jszd;
	}

	public Object get(String pathogenId2) {
		return null;
	}
}