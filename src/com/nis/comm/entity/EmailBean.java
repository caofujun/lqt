package com.nis.comm.entity;

import java.io.Serializable;
import java.util.Map;

public class EmailBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String receiverAddress;
	private String emailTempName;
	private String emailTitle;
	private Map<String, String> emailTempParams;

	public String getReceiverAddress() {
		return this.receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getEmailTempName() {
		return this.emailTempName;
	}

	public void setEmailTempName(String emailTempName) {
		this.emailTempName = emailTempName;
	}

	public Map<String, String> getEmailTempParams() {
		return this.emailTempParams;
	}

	public void setEmailTempParams(Map<String, String> emailTempParams) {
		this.emailTempParams = emailTempParams;
	}

	public String getEmailTitle() {
		return this.emailTitle;
	}

	public void setEmailTitle(String emailTitle) {
		this.emailTitle = emailTitle;
	}

	public String toString() {
		return "EmailBean [receiverAddress=" + this.receiverAddress + ", emailTempName=" + this.emailTempName
				+ ", emailTitle=" + this.emailTitle + ", emailTempParams=" + this.emailTempParams + "]";
	}
}