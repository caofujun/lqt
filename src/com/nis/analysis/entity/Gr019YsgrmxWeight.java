package com.nis.analysis.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class Gr019YsgrmxWeight extends BaseEntity implements Serializable {
	private String zyid;
	private String infectCode;
	private Integer bcyxWeight;
	private Date bcyxDay;
	private Integer tyxWeight;
	private Date tyxDay;
	private Integer jyjcWeight;
	private Date jyjcDay;
	private Integer xjpyWeight;
	private Date xjpyDay;
	private Integer kjywWeight;
	private Date kjywDay;
	private Date grDate;
	private Date createTime;
	private Date monitorAt;

	public String getZyid() {
		return this.zyid;
	}

	public void setZyid(String zyid) {
		this.zyid = zyid;
	}

	public String getInfectCode() {
		return this.infectCode;
	}

	public void setInfectCode(String infectCode) {
		this.infectCode = infectCode;
	}

	public Integer getBcyxWeight() {
		return this.bcyxWeight;
	}

	public void setBcyxWeight(Integer bcyxWeight) {
		this.bcyxWeight = bcyxWeight;
	}

	public Integer getJyjcWeight() {
		return this.jyjcWeight;
	}

	public void setJyjcWeight(Integer jyjcWeight) {
		this.jyjcWeight = jyjcWeight;
	}

	public Integer getXjpyWeight() {
		return this.xjpyWeight;
	}

	public void setXjpyWeight(Integer xjpyWeight) {
		this.xjpyWeight = xjpyWeight;
	}

	public Integer getKjywWeight() {
		return this.kjywWeight;
	}

	public void setKjywWeight(Integer kjywWeight) {
		this.kjywWeight = kjywWeight;
	}

	public Date getGrDate() {
		return this.grDate;
	}

	public void setGrDate(Date grDate) {
		this.grDate = grDate;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getMonitorAt() {
		return this.monitorAt;
	}

	public void setMonitorAt(Date monitorAt) {
		this.monitorAt = monitorAt;
	}

	public Integer getTyxWeight() {
		return this.tyxWeight;
	}

	public void setTyxWeight(Integer tyxWeight) {
		this.tyxWeight = tyxWeight;
	}

	public Date getBcyxDay() {
		return this.bcyxDay;
	}

	public void setBcyxDay(Date bcyxDay) {
		this.bcyxDay = bcyxDay;
	}

	public Date getTyxDay() {
		return this.tyxDay;
	}

	public void setTyxDay(Date tyxDay) {
		this.tyxDay = tyxDay;
	}

	public Date getJyjcDay() {
		return this.jyjcDay;
	}

	public void setJyjcDay(Date jyjcDay) {
		this.jyjcDay = jyjcDay;
	}

	public Date getXjpyDay() {
		return this.xjpyDay;
	}

	public void setXjpyDay(Date xjpyDay) {
		this.xjpyDay = xjpyDay;
	}

	public Date getKjywDay() {
		return this.kjywDay;
	}

	public void setKjywDay(Date kjywDay) {
		this.kjywDay = kjywDay;
	}
}