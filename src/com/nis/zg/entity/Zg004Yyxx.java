package com.nis.zg.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("zg004Yyxx")
public class Zg004Yyxx extends BaseEntity implements Serializable {
	private String hospId;
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

	public String getHospId() {
		return this.hospId;
	}

	public void setHospId(String hospId) {
		this.hospId = hospId;
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
}