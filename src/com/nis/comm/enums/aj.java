package com.nis.comm.enums;

public enum aj {
	iN("模糊匹配", "like"), iO("内容比较", "="), iP("上限比较", ">"), iQ("下限比较", "<");

	private String name;
	private String value;

	private aj(String name, String value) {
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