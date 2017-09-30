package com.nis.comm.enums;

public enum as {
	kW("未引入", "0"), kX("引入", "1");

	private String name;
	private String value;

	private as(String name, String value) {
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