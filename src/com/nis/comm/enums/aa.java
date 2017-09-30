package com.nis.comm.enums;

public enum aa {
	hF("已读", "1"), hG("未读", "0");

	private String name;
	private String value;

	private aa(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}