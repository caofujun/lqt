package com.nis.dict.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("icd10")
public class Icd10 extends BaseEntity implements Serializable {
	private String icdId;
	private String icdCode;
	private String icdName;
	private String bhCode;
	private String spCode;
	private String wbCode;
	private Long flag;
	private String zjCode;
	private Date lastAt;
	private String scode;

	public String getIcdId() {
		return this.icdId;
	}

	public void setIcdId(String icdId) {
		this.icdId = icdId;
	}

	public String getIcdCode() {
		return this.icdCode;
	}

	public void setIcdCode(String icdCode) {
		this.icdCode = icdCode;
	}

	public String getIcdName() {
		return this.icdName;
	}

	public void setIcdName(String icdName) {
		this.icdName = icdName;
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

	public Long getFlag() {
		return this.flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
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

	public String getScode() {
		return this.scode;
	}

	public void setScode(String scode) {
		this.scode = scode;
	}

	public String toString() {
		return "Icd10 [icdId=" + this.icdId + ", icdCode=" + this.icdCode + ", icdName=" + this.icdName + ", bhCode="
				+ this.bhCode + ", spCode=" + this.spCode + ", wbCode=" + this.wbCode + ", flag=" + this.flag
				+ ", zjCode=" + this.zjCode + ", lastAt=" + this.lastAt + ", scode=" + this.scode + "]";
	}
}