package com.nis.comm.enums;

public enum d {
	fA("正常", Integer.valueOf(0)), fB("删除", Integer.valueOf(-1));

	private String name;
	private Integer value;

	private d(String name, Integer value) {
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