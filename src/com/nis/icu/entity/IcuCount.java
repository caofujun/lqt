package com.nis.icu.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;

public class IcuCount extends BaseEntity implements Serializable {
	private String strDate;
	private String startDate;
	private String endDate;
	private String deptId;
	private String deptName;
	private Integer newCount;
	private Integer inCount;
	private Integer outCount;
	private Integer frCount;
	private Integer hxjCount;
	private Integer mndCount;
	private Integer zxjmCount;
	private Integer tsyCount;
	private Integer ywryksCount;
	private Integer ywryfxCount;
	private Integer ywryfrCount;
	private Integer bhksCount;
	private Integer bhfxCount;

	public Integer getYwryksCount() {
		return this.ywryksCount;
	}

	public void setYwryksCount(Integer ywryksCount) {
		this.ywryksCount = ywryksCount;
	}

	public Integer getYwryfxCount() {
		return this.ywryfxCount;
	}

	public void setYwryfxCount(Integer ywryfxCount) {
		this.ywryfxCount = ywryfxCount;
	}

	public Integer getBhksCount() {
		return this.bhksCount;
	}

	public void setBhksCount(Integer bhksCount) {
		this.bhksCount = bhksCount;
	}

	public Integer getBhfxCount() {
		return this.bhfxCount;
	}

	public void setBhfxCount(Integer bhfxCount) {
		this.bhfxCount = bhfxCount;
	}

	public Integer getNewCount() {
		return this.newCount;
	}

	public String getStrDate() {
		return this.strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	public void setNewCount(Integer newCount) {
		this.newCount = newCount;
	}

	public Integer getInCount() {
		return this.inCount;
	}

	public void setInCount(Integer inCount) {
		this.inCount = inCount;
	}

	public Integer getOutCount() {
		return this.outCount;
	}

	public void setOutCount(Integer outCount) {
		this.outCount = outCount;
	}

	public Integer getFrCount() {
		return this.frCount;
	}

	public void setFrCount(Integer frCount) {
		this.frCount = frCount;
	}

	public Integer getHxjCount() {
		return this.hxjCount;
	}

	public void setHxjCount(Integer hxjCount) {
		this.hxjCount = hxjCount;
	}

	public Integer getMndCount() {
		return this.mndCount;
	}

	public void setMndCount(Integer mndCount) {
		this.mndCount = mndCount;
	}

	public Integer getZxjmCount() {
		return this.zxjmCount;
	}

	public void setZxjmCount(Integer zxjmCount) {
		this.zxjmCount = zxjmCount;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
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

	public Integer getTsyCount() {
		return this.tsyCount;
	}

	public void setTsyCount(Integer tsyCount) {
		this.tsyCount = tsyCount;
	}

	public Integer getYwryfrCount() {
		return this.ywryfrCount;
	}

	public void setYwryfrCount(Integer ywryfrCount) {
		this.ywryfrCount = ywryfrCount;
	}
}