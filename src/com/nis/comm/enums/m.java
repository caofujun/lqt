package com.nis.comm.enums;

public enum m {
	ge("可用", "enable"), gf("停用", "disable");

	private String name;
	private String value;

	private m(String name, String value) {
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