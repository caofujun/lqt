package com.nis.monitor.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.monitor.entity.Gr016SsbwKjyw;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Gr016BkKjyw extends BaseEntity implements Serializable {
	private String relid;
	private String refid;
	private int isselect;
	private String orderType;
	private Date orderAt;
	private Date stopAt;
	private String orderName;
	private Double dose;
	private String dosageUnit;
	private Double qty;
	private String frequency;
	private Integer dailyTimes;
	private String adminRouteName;
	private String executeName;
	private Date executeTime;
	private String dateSection;
	private String memo;
	private String yzId;
	private String preYymd;
	private String operTypeId;
	private List<Gr016SsbwKjyw> gr16List;
	private String sypc;
	private String yzIdStr;
	private String[] yzIds;
	private String[] preYymds;
	private String[] relids;
	private String[] isselects;
	private String days;

	public String relid() {
		return this.relid;
	}

	public void setRelid(String relid) {
		this.relid = relid;
	}

	public String getRefid() {
		return this.refid;
	}

	public void setRefid(String refid) {
		this.refid = refid;
	}

	public int getIsselect() {
		return this.isselect;
	}

	public void setIsselect(int isselect) {
		this.isselect = isselect;
	}

	public String getOrderType() {
		return this.orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Date getOrderAt() {
		return this.orderAt;
	}

	public void setOrderAt(Date orderAt) {
		this.orderAt = orderAt;
	}

	public Date getStopAt() {
		return this.stopAt;
	}

	public void setStopAt(Date stopAt) {
		this.stopAt = stopAt;
	}

	public String getOrderName() {
		return this.orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public Double getDose() {
		return this.dose;
	}

	public void setDose(Double dose) {
		this.dose = dose;
	}

	public String getDosageUnit() {
		return this.dosageUnit;
	}

	public void setDosageUnit(String dosageUnit) {
		this.dosageUnit = dosageUnit;
	}

	public Double getQty() {
		return this.qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}

	public String getFrequency() {
		return this.frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public Integer getDailyTimes() {
		return this.dailyTimes;
	}

	public void setDailyTimes(Integer dailyTimes) {
		this.dailyTimes = dailyTimes;
	}

	public String getAdminRouteName() {
		return this.adminRouteName;
	}

	public void setAdminRouteName(String adminRouteName) {
		this.adminRouteName = adminRouteName;
	}

	public String getExecuteName() {
		return this.executeName;
	}

	public void setExecuteName(String executeName) {
		this.executeName = executeName;
	}

	public Date getExecuteTime() {
		return this.executeTime;
	}

	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}

	public String getDateSection() {
		return this.dateSection;
	}

	public void setDateSection(String dateSection) {
		this.dateSection = dateSection;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getYzId() {
		return this.yzId;
	}

	public void setYzId(String yzId) {
		this.yzId = yzId;
	}

	public String getPreYymd() {
		return this.preYymd;
	}

	public void setPreYymd(String preYymd) {
		this.preYymd = preYymd;
	}

	public String getOperTypeId() {
		return this.operTypeId;
	}

	public void setOperTypeId(String operTypeId) {
		this.operTypeId = operTypeId;
	}

	public List<Gr016SsbwKjyw> getGr16List() {
		return this.gr16List;
	}

	public void setGr16List(List<Gr016SsbwKjyw> gr16List) {
		this.gr16List = gr16List;
	}

	public String getSypc() {
		return this.sypc;
	}

	public void setSypc(String sypc) {
		this.sypc = sypc;
	}

	public String getYzIdStr() {
		return this.yzIdStr;
	}

	public void setYzIdStr(String yzIdStr) {
		this.yzIdStr = yzIdStr;
	}

	public String getDays() {
		return this.days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String[] getYzIds() {
		return this.yzIds;
	}

	public void setYzIds(String[] yzIds) {
		this.yzIds = yzIds;
	}

	public String[] getPreYymds() {
		return this.preYymds;
	}

	public void setPreYymds(String[] preYymds) {
		this.preYymds = preYymds;
	}

	public String[] getRelids() {
		return this.relids;
	}

	public void setRelids(String[] relids) {
		this.relids = relids;
	}

	public String[] getIsselects() {
		return this.isselects;
	}

	public void setIsselects(String[] isselects) {
		this.isselects = isselects;
	}
}