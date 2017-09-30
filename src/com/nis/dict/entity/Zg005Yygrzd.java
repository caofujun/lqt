package com.nis.dict.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Zg005Yygrzd extends BaseEntity implements Serializable {
	private String id;
	private String text;
	private String state;
	private String indiagid;
	private String infectCode;
	private String infectName;
	private String pInfectCode;
	private String bhCode;
	private String spCode;
	private String wbCode;
	private Integer flag;
	private Integer orderIndex;
	private String memo;
	private Date lastAt;
	private Double isLastLv;
	private Double type;
	private List<Zg005Yygrzd> cldList;

	public String getIndiagid() {
		return this.indiagid;
	}

	public void setIndiagid(String indiagid) {
		this.indiagid = indiagid;
	}

	public String getInfectCode() {
		return this.infectCode;
	}

	public void setInfectCode(String infectCode) {
		this.infectCode = infectCode;
		this.setId(infectCode);
	}

	public String getInfectName() {
		return this.infectName;
	}

	public void setInfectName(String infectName) {
		this.infectName = infectName;
		this.setText(infectName);
	}

	public String getPInfectCode() {
		return this.pInfectCode;
	}

	public void setPInfectCode(String pInfectCode) {
		this.pInfectCode = pInfectCode;
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

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getOrderIndex() {
		return this.orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}

	public Double getIsLastLv() {
		return this.isLastLv;
	}

	public void setIsLastLv(Double isLastLv) {
		this.isLastLv = isLastLv;
	}

	public Double getType() {
		return this.type;
	}

	public void setType(Double type) {
		this.type = type;
	}

	public List<Zg005Yygrzd> getCldList() {
		return this.cldList;
	}

	public void setCldList(List<Zg005Yygrzd> cldList) {
		this.cldList = cldList;
	}

	public void addCldList(Zg005Yygrzd zg005Yygrzd) {
		if (this.cldList == null) {
			this.cldList = new ArrayList();
		}

		this.cldList.add(zg005Yygrzd);
	}

	public String getpInfectCode() {
		return this.pInfectCode;
	}

	public void setpInfectCode(String pInfectCode) {
		this.pInfectCode = pInfectCode;
	}

	public List<Zg005Yygrzd> getChildren() {
		return this.getCldList();
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String toString() {
		return "Zg005Yygrzd [indiagid=" + this.indiagid + ", infectCode=" + this.infectCode + ", infectName="
				+ this.infectName + ", pInfectCode=" + this.pInfectCode + ", bhCode=" + this.bhCode + ", spCode="
				+ this.spCode + ", wbCode=" + this.wbCode + ", flag=" + this.flag + ", orderIndex=" + this.orderIndex
				+ ", memo=" + this.memo + ", lastAt=" + this.lastAt + ", isLastLv=" + this.isLastLv + ", type="
				+ this.type + ", cldList=" + this.cldList + "]";
	}
}