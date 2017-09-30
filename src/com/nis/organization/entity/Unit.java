package com.nis.organization.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("unit")
public class Unit extends BaseEntity implements Serializable {
	private String unitId;
	private String hospName;
	private String hospLevel;
	private Long beds;
	private String hospType;
	private String hospNature;
	private Long ifbranch;
	private Date createAt;
	private String bhCode;
	private Long flag;
	private Date lastAt;
	private String spCode;
	private String support;
	private String suppTel;
	private String licence;
	private String showHospLevel;
	private String showHospType;
	private String showHospNature;
	private String showFlag;
	private String showIfbranch;
	private String showCreateAt;
	private String showLastAt;

	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getHospName() {
		return this.hospName;
	}

	public void setHospName(String hospName) {
		this.hospName = hospName;
	}

	public String getHospLevel() {
		return this.hospLevel;
	}

	public void setHospLevel(String hospLevel) {
		this.hospLevel = hospLevel;
	}

	public Long getBeds() {
		return this.beds;
	}

	public void setBeds(Long beds) {
		this.beds = beds;
	}

	public String getHospType() {
		return this.hospType;
	}

	public void setHospType(String hospType) {
		this.hospType = hospType;
	}

	public String getHospNature() {
		return this.hospNature;
	}

	public void setHospNature(String hospNature) {
		this.hospNature = hospNature;
	}

	public Long getIfbranch() {
		return this.ifbranch;
	}

	public void setIfbranch(Long ifbranch) {
		this.ifbranch = ifbranch;
	}

	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getBhCode() {
		return this.bhCode;
	}

	public void setBhCode(String bhCode) {
		this.bhCode = bhCode;
	}

	public Long getFlag() {
		return this.flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}

	public String getSpCode() {
		return this.spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public String getSupport() {
		return this.support;
	}

	public void setSupport(String support) {
		this.support = support;
	}

	public String getSuppTel() {
		return this.suppTel;
	}

	public void setSuppTel(String suppTel) {
		this.suppTel = suppTel;
	}

	public String getLicence() {
		return this.licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	public String getShowHospLevel() {
		return this.showHospLevel;
	}

	public void setShowHospLevel(String showHospLevel) {
		this.showHospLevel = showHospLevel;
	}

	public String getShowHospType() {
		return this.showHospType;
	}

	public void setShowHospType(String showHospType) {
		this.showHospType = showHospType;
	}

	public String getShowHospNature() {
		return this.showHospNature;
	}

	public void setShowHospNature(String showHospNature) {
		this.showHospNature = showHospNature;
	}

	public String getShowFlag() {
		return this.showFlag;
	}

	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}

	public String getShowIfbranch() {
		return this.showIfbranch;
	}

	public void setShowIfbranch(String showIfbranch) {
		this.showIfbranch = showIfbranch;
	}

	public String getShowCreateAt() {
		return this.showCreateAt;
	}

	public void setShowCreateAt(String showCreateAt) {
		this.showCreateAt = showCreateAt;
	}

	public String getShowLastAt() {
		return this.showLastAt;
	}

	public void setShowLastAt(String showLastAt) {
		this.showLastAt = showLastAt;
	}

	public String toString() {
		return "Unit [unitId=" + this.unitId + ", hospName=" + this.hospName + ", hospLevel=" + this.hospLevel
				+ ", beds=" + this.beds + ", hospType=" + this.hospType + ", hospNature=" + this.hospNature
				+ ", ifbranch=" + this.ifbranch + ", createAt=" + this.createAt + ", bhCode=" + this.bhCode + ", flag="
				+ this.flag + ", lastAt=" + this.lastAt + ", spCode=" + this.spCode + ", support=" + this.support
				+ ", suppTel=" + this.suppTel + ", licence=" + this.licence + ", showHospLevel=" + this.showHospLevel
				+ ", showHospType=" + this.showHospType + ", showHospNature=" + this.showHospNature + ", showFlag="
				+ this.showFlag + ", showIfbranch=" + this.showIfbranch + ", showCreateAt=" + this.showCreateAt
				+ ", showLastAt=" + this.showLastAt + "]";
	}
}