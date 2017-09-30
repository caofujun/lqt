package com.nis.comm.enums;

public enum ai {
	iK("检查项目名称", "ITEM_TYPE_NAME"), iL("标本名称", "ITEM_NAME");

	private String name;
	private String value;

	private ai(String name, String value) {
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