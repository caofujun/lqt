package com.nis.cdc.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("ctgBk001Crbhbvcard")
public class CtgBk001Crbhbvcard extends BaseEntity implements Serializable {
	private String masterid;
	private String subid;
	private String hbsag;
	private Long firstYear;
	private Long firstMonth;
	private Long firstUnknown;
	private String alt;
	private String hbc;
	private String liverPuncture;
	private String decubation;

	public String getMasterid() {
		return this.masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}

	public String getSubid() {
		return this.subid;
	}

	public void setSubid(String subid) {
		this.subid = subid;
	}

	public String getHbsag() {
		return this.hbsag;
	}

	public void setHbsag(String hbsag) {
		this.hbsag = hbsag;
	}

	public Long getFirstYear() {
		return this.firstYear;
	}

	public void setFirstYear(Long firstYear) {
		this.firstYear = firstYear;
	}

	public Long getFirstMonth() {
		return this.firstMonth;
	}

	public void setFirstMonth(Long firstMonth) {
		this.firstMonth = firstMonth;
	}

	public Long getFirstUnknown() {
		return this.firstUnknown;
	}

	public void setFirstUnknown(Long firstUnknown) {
		this.firstUnknown = firstUnknown;
	}

	public String getAlt() {
		return this.alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getHbc() {
		return this.hbc;
	}

	public void setHbc(String hbc) {
		this.hbc = hbc;
	}

	public String getLiverPuncture() {
		return this.liverPuncture;
	}

	public void setLiverPuncture(String liverPuncture) {
		this.liverPuncture = liverPuncture;
	}

	public String getDecubation() {
		return this.decubation;
	}

	public void setDecubation(String decubation) {
		this.decubation = decubation;
	}
}