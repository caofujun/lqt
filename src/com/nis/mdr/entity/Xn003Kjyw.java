package com.nis.mdr.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class Xn003Kjyw extends BaseEntity implements Serializable {
	private Long isShow;
	private String drugId;
	private String drugName;
	private String drugEnname;
	private String bhCode;
	private String spCode;
	private String wbCode;
	private Long ifCommon;
	private Long flag;
	private Long ifReport;
	private String zjCode;
	private Date lastAt;
	private String drugTypeId;
	private String subclassId;
	private String pathogenId;

	public Long getIsShow() {
		return this.isShow;
	}

	public void setIsShow(Long isShow) {
		this.isShow = isShow;
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

	public String getDrugEnname() {
		return this.drugEnname;
	}

	public void setDrugEnname(String drugEnname) {
		this.drugEnname = drugEnname;
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

	public Long getIfCommon() {
		return this.ifCommon;
	}

	public void setIfCommon(Long ifCommon) {
		this.ifCommon = ifCommon;
	}

	public Long getFlag() {
		return this.flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	public Long getIfReport() {
		return this.ifReport;
	}

	public void setIfReport(Long ifReport) {
		this.ifReport = ifReport;
	}

	public String getZjCode() {
		return this.zjCode;
	}

	public void setZjCode(String zjCode) {
		this.zjCode = zjCode;
	}

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}

	public String getDrugTypeId() {
		return this.drugTypeId;
	}

	public void setDrugTypeId(String drugTypeId) {
		this.drugTypeId = drugTypeId;
	}

	public String getSubclassId() {
		return this.subclassId;
	}

	public void setSubclassId(String subclassId) {
		this.subclassId = subclassId;
	}

	public String getPathogenId() {
		return this.pathogenId;
	}

	public void setPathogenId(String pathogenId) {
		this.pathogenId = pathogenId;
	}
}