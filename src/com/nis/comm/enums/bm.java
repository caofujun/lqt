package com.nis.comm.enums;

public enum bm {
	oA("正常", Integer.valueOf(0)), oB("删除", Integer.valueOf(-1));

	private String name;
	private Integer value;

	private bm(String name, Integer value) {
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