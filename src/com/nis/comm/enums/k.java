package com.nis.comm.enums;

public enum k {
	fZ("检验指标", "ANTI_NAME");

	private String name;
	private String value;

	private k(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public String getValue() {
		return this.value;
	}
}