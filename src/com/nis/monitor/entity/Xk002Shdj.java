package com.nis.monitor.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class Xk002Shdj extends BaseEntity implements Serializable {
	private String shNo;
	private String shType;
	private String scqyNo;
	private String scqyName;
	private String jyqyName;
	private String jyqyNo;
	private Date scjyDate;
	private Date jygrDate;
	private Date scNd;
	private Date jyJydate;
	private String depCg;
	private String cgRy;
	private Date shDate;
	private String shName;
	private String shJg;
	private String useFlag;
	private String remark;
	private String orgShNo;

	public String getOrgShNo() {
		return this.orgShNo;
	}

	public void setOrgShNo(String orgShNo) {
		this.orgShNo = orgShNo;
	}

	public String getShNo() {
		return this.shNo;
	}

	public void setShNo(String shNo) {
		this.shNo = shNo;
	}

	public String getShType() {
		return this.shType;
	}

	public void setShType(String shType) {
		this.shType = shType;
	}

	public String getScqyNo() {
		return this.scqyNo;
	}

	public void setScqyNo(String scqyNo) {
		this.scqyNo = scqyNo;
	}

	public String getScqyName() {
		return this.scqyName;
	}

	public void setScqyName(String scqyName) {
		this.scqyName = scqyName;
	}

	public String getJyqyName() {
		return this.jyqyName;
	}

	public void setJyqyName(String jyqyName) {
		this.jyqyName = jyqyName;
	}

	public String getJyqyNo() {
		return this.jyqyNo;
	}

	public void setJyqyNo(String jyqyNo) {
		this.jyqyNo = jyqyNo;
	}

	public Date getScjyDate() {
		return this.scjyDate;
	}

	public void setScjyDate(Date scjyDate) {
		this.scjyDate = scjyDate;
	}

	public Date getJygrDate() {
		return this.jygrDate;
	}

	public void setJygrDate(Date jygrDate) {
		this.jygrDate = jygrDate;
	}

	public Date getScNd() {
		return this.scNd;
	}

	public void setScNd(Date scNd) {
		this.scNd = scNd;
	}

	public Date getJyJydate() {
		return this.jyJydate;
	}

	public void setJyJydate(Date jyJydate) {
		this.jyJydate = jyJydate;
	}

	public String getDepCg() {
		return this.depCg;
	}

	public void setDepCg(String depCg) {
		this.depCg = depCg;
	}

	public String getCgRy() {
		return this.cgRy;
	}

	public void setCgRy(String cgRy) {
		this.cgRy = cgRy;
	}

	public Date getShDate() {
		return this.shDate;
	}

	public void setShDate(Date shDate) {
		this.shDate = shDate;
	}

	public String getShName() {
		return this.shName;
	}

	public void setShName(String shName) {
		this.shName = shName;
	}

	public String getShJg() {
		return this.shJg;
	}

	public void setShJg(String shJg) {
		this.shJg = shJg;
	}

	public String getUseFlag() {
		return this.useFlag;
	}

	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}