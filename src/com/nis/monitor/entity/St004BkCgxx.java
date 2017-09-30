package com.nis.monitor.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class St004BkCgxx extends BaseEntity implements Serializable {
	private String relid;
	private String refid;
	private Date createDate;
	private String yzId;
	private List<St004BkCgxx> st004List;
	private String[] yzIdList;
	private String[] relids;
	private String zyid;
	private String orderName;
	private Date orderAt;
	private Date stopAt;
	private String id;

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

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getYzId() {
		return this.yzId;
	}

	public void setYzId(String yzId) {
		this.yzId = yzId;
	}

	public List<St004BkCgxx> getSt004List() {
		return this.st004List;
	}

	public void setSt004List(List<St004BkCgxx> st004List) {
		this.st004List = st004List;
	}

	public String[] getRelids() {
		return this.relids;
	}

	public void setRelids(String[] relids) {
		this.relids = relids;
	}

	public String[] getYzIdList() {
		return this.yzIdList;
	}

	public void setYzIdList(String[] yzIdList) {
		this.yzIdList = yzIdList;
	}

	public String toString() {
		return "St004BkCgxx [relid=" + this.relid + ", refid=" + this.refid + ", createDate=" + this.createDate
				+ ", yzId=" + this.yzId + ", st004List=" + this.st004List + ", yzIdList="
				+ Arrays.toString(this.yzIdList) + ", relids=" + Arrays.toString(this.relids) + "]";
	}

	public String getZyid() {
		return this.zyid;
	}

	public void setZyid(String zyid) {
		this.zyid = zyid;
	}

	public String getOrderName() {
		return this.orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
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

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
}