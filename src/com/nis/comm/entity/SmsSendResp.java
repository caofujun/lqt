package com.nis.comm.entity;

public class SmsSendResp {
	private Integer state;
	private String msg;
	private String sslid;

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSslid() {
		return this.sslid;
	}

	public void setSslid(String sslid) {
		this.sslid = sslid;
	}
}