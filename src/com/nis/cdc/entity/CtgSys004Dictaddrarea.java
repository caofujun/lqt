package com.nis.cdc.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("ctgSys004Dictaddrarea")
public class CtgSys004Dictaddrarea extends BaseEntity implements Serializable {
	private String areacode;
	private String areacodevalue;
	private String areafullvalue;
	private String cityRural;
	private String aspect;
	private String areaRankings;
	private Long frontier;
	private Long minority;
	private String areaParent;

	public String getAreacode() {
		return this.areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getAreacodevalue() {
		return this.areacodevalue;
	}

	public void setAreacodevalue(String areacodevalue) {
		this.areacodevalue = areacodevalue;
	}

	public String getAreafullvalue() {
		return this.areafullvalue;
	}

	public void setAreafullvalue(String areafullvalue) {
		this.areafullvalue = areafullvalue;
	}

	public String getCityRural() {
		return this.cityRural;
	}

	public void setCityRural(String cityRural) {
		this.cityRural = cityRural;
	}

	public String getAspect() {
		return this.aspect;
	}

	public void setAspect(String aspect) {
		this.aspect = aspect;
	}

	public String getAreaRankings() {
		return this.areaRankings;
	}

	public void setAreaRankings(String areaRankings) {
		this.areaRankings = areaRankings;
	}

	public Long getFrontier() {
		return this.frontier;
	}

	public void setFrontier(Long frontier) {
		this.frontier = frontier;
	}

	public Long getMinority() {
		return this.minority;
	}

	public void setMinority(Long minority) {
		this.minority = minority;
	}

	public String getAreaParent() {
		return this.areaParent;
	}

	public void setAreaParent(String areaParent) {
		this.areaParent = areaParent;
	}
}