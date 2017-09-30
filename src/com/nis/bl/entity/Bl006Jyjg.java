package com.nis.bl.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class Bl006Jyjg extends BaseEntity implements Serializable {
	private Long flag;
	private String blId;
	private Date jyTime;
	private String jyHm;
	private String jyJg;
	private String djMen;
	private Date djTime;
	private String jyDh;
	private String sfMemo;
	private String jyTimeStr;

	public Long getFlag() {
		return this.flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	public String getBlId() {
		return this.blId;
	}

	public void setBlId(String blId) {
		this.blId = blId;
	}

	public Date getJyTime() {
		return this.jyTime;
	}

	public void setJyTime(Date jyTime) {
		this.jyTime = jyTime;
	}

	public String getJyHm() {
		return this.jyHm;
	}

	public void setJyHm(String jyHm) {
		this.jyHm = jyHm;
	}

	public String getJyJg() {
		return this.jyJg;
	}

	public void setJyJg(String jyJg) {
		this.jyJg = jyJg;
	}

	public String getDjMen() {
		return this.djMen;
	}

	public void setDjMen(String djMen) {
		this.djMen = djMen;
	}

	public Date getDjTime() {
		return this.djTime;
	}

	public void setDjTime(Date djTime) {
		this.djTime = djTime;
	}

	public String getJyDh() {
		return this.jyDh;
	}

	public void setJyDh(String jyDh) {
		this.jyDh = jyDh;
	}

	public String getSfMemo() {
		return this.sfMemo;
	}

	public void setSfMemo(String sfMemo) {
		this.sfMemo = sfMemo;
	}

	public String getJyTimeStr() {
		return this.jyTimeStr;
	}

	public void setJyTimeStr(String jyTimeStr) {
		this.jyTimeStr = jyTimeStr;
	}
}