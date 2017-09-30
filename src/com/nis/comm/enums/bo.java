package com.nis.comm.enums;

public enum bo {
	oI("诊断", "0"), oJ("医嘱", "1"), oK("检验", "2"), oL("手术", "3");

	private String name;
	private String value;

	private bo(String name, String value) {
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