package com.nis.comm.enums;

public enum j {
	fW("业务缓存的名称", "businessCache"), fX("session缓存的名称", "sessionCache");

	private String name;
	private String value;

	private j(String name, String value) {
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