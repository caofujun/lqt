package com.nis.comm.enums;

public enum bi {
	of("标准模糊匹配LIS", Integer.valueOf(1)), og("LIS模糊匹配标准", Integer.valueOf(2));

	private String name;
	private Integer value;

	private bi(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public Integer getValue() {
		return this.value;
	}
}