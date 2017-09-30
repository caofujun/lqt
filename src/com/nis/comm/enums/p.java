package com.nis.comm.enums;

public enum p {
	gy("可用", "1"), gz("停用", "0");

	private String name;
	private String value;

	private p(String name, String value) {
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