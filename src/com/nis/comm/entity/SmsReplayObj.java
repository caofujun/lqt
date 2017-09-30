package com.nis.comm.entity;

public class SmsReplayObj {
	private String SSLID;
	private String REVALUE;
	private String RETIME;
	private String USID;

	public String getSSLID() {
		return this.SSLID;
	}

	public void setSSLID(String sslid) {
		this.SSLID = sslid;
	}

	public String getREVALUE() {
		return this.REVALUE;
	}

	public void setREVALUE(String revalue) {
		this.REVALUE = revalue;
	}

	public String getRETIME() {
		return this.RETIME;
	}

	public void setRETIME(String retime) {
		this.RETIME = retime;
	}

	public String getUSID() {
		return this.USID;
	}

	public void setUSID(String usid) {
		this.USID = usid;
	}

	public String toString() {
		return "SmsReplayObj [SSLID=" + this.SSLID + ", REVALUE=" + this.REVALUE + ", RETIME=" + this.RETIME + ", USID="
				+ this.USID + "]";
	}
}