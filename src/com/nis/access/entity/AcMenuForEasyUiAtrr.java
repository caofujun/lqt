package com.nis.access.entity;

import java.io.Serializable;

public class AcMenuForEasyUiAtrr implements Serializable {
	private static final long serialVersionUID = 1L;
	private String destUrl;

	public String getDestUrl() {
		return this.destUrl;
	}

	public void setDestUrl(String destUrl) {
		this.destUrl = destUrl;
	}
}