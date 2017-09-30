package com.nis.icu.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class CgPg extends BaseEntity implements Serializable {
	private Date dtime;
	private Integer mnd;
	private Integer zxjm;
	private Integer hxj;
	private Integer mndqs;
	private Integer zxjmqs;
	private Integer hxjqs;
	private Integer day;
	private String toDay;

	public Date getDtime() {
		return this.dtime;
	}

	public void setDtime(Date dtime) {
		this.dtime = dtime;
	}

	public Integer getMnd() {
		return this.mnd;
	}

	public void setMnd(Integer mnd) {
		this.mnd = mnd;
	}

	public Integer getZxjm() {
		return this.zxjm;
	}

	public void setZxjm(Integer zxjm) {
		this.zxjm = zxjm;
	}

	public Integer getHxj() {
		return this.hxj;
	}

	public void setHxj(Integer hxj) {
		this.hxj = hxj;
	}

	public Integer getDay() {
		return this.day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getMndqs() {
		return this.mndqs;
	}

	public void setMndqs(Integer mndqs) {
		this.mndqs = mndqs;
	}

	public Integer getZxjmqs() {
		return this.zxjmqs;
	}

	public void setZxjmqs(Integer zxjmqs) {
		this.zxjmqs = zxjmqs;
	}

	public Integer getHxjqs() {
		return this.hxjqs;
	}

	public void setHxjqs(Integer hxjqs) {
		this.hxjqs = hxjqs;
	}

	public String getToDay() {
		return this.toDay;
	}

	public void setToDay(String toDay) {
		this.toDay = toDay;
	}
}