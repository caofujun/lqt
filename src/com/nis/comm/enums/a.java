package com.nis.comm.enums;

public enum a {
	fr("医务人员", Integer.valueOf(1)), fs("患者", Integer.valueOf(0));

	private String name;
	private Integer value;

	private a(String name, Integer value) {
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