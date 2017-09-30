package com.nis.monitor.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.type.Alias;

@Alias("gr016SsbwKjyw")
public class Gr016SsbwKjyw extends BaseEntity implements Serializable {
	private String dateSection;
	private String memo;
	private String yzId;
	private String relid;
	private String refid;
	private Integer operTypeId;
	private Integer isselect;
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
	private List<Gr016SsbwKjyw> gr16List1;
	private List<Gr016SsbwKjyw> gr16List2;
	private List<Gr016SsbwKjyw> gr16List3;
	private String preYymd;
	private String szyzjyyewzj;

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

	public String getRelid() {
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

	public Integer getOperTypeId() {
		return this.operTypeId;
	}

	public void setOperTypeId(Integer operTypeId) {
		this.operTypeId = operTypeId;
	}

	public Integer getIsselect() {
		return this.isselect;
	}

	public void setIsselect(Integer isselect) {
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

	public List<Gr016SsbwKjyw> getGr16List1() {
		return this.gr16List1;
	}

	public void setGr16List1(List<Gr016SsbwKjyw> gr16List1) {
		this.gr16List1 = gr16List1;
	}

	public List<Gr016SsbwKjyw> getGr16List2() {
		return this.gr16List2;
	}

	public void setGr16List2(List<Gr016SsbwKjyw> gr16List2) {
		this.gr16List2 = gr16List2;
	}

	public List<Gr016SsbwKjyw> getGr16List3() {
		return this.gr16List3;
	}

	public void setGr16List3(List<Gr016SsbwKjyw> gr16List3) {
		this.gr16List3 = gr16List3;
	}

	public String getPreYymd() {
		return this.preYymd;
	}

	public void setPreYymd(String preYymd) {
		this.preYymd = preYymd;
	}

	public String getSzyzjyyewzj() {
		return this.szyzjyyewzj;
	}

	public void setSzyzjyyewzj(String szyzjyyewzj) {
		this.szyzjyyewzj = szyzjyyewzj;
	}

	public String toString() {
		return "Gr016SsbwKjyw [dateSection=" + this.dateSection + ", memo=" + this.memo + ", yzId=" + this.yzId
				+ ", relid=" + this.relid + ", refid=" + this.refid + ", operTypeId=" + this.operTypeId + ", isselect="
				+ this.isselect + ", orderType=" + this.orderType + ", orderAt=" + this.orderAt + ", stopAt="
				+ this.stopAt + ", orderName=" + this.orderName + ", dose=" + this.dose + ", dosageUnit="
				+ this.dosageUnit + ", qty=" + this.qty + ", frequency=" + this.frequency + ", dailyTimes="
				+ this.dailyTimes + ", adminRouteName=" + this.adminRouteName + ", executeName=" + this.executeName
				+ ", executeTime=" + this.executeTime + ", gr16List1=" + this.gr16List1 + ", gr16List2="
				+ this.gr16List2 + ", gr16List3=" + this.gr16List3 + ", preYymd=" + this.preYymd + ", szyzjyyewzj="
				+ this.szyzjyyewzj + "]";
	}
}