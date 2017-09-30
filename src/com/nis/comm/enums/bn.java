package com.nis.comm.enums;

public enum bn {
	oE("留置导尿管", "001"), oF("中心静脉插管", "002"), oG("使用呼吸机器", "003");

	private String name;
	private String value;

	private bn(String name, String value) {
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