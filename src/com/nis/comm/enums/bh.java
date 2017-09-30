package com.nis.comm.enums;

public enum bh {
	ob("未分析", Integer.valueOf(0)), oc("已分析", Integer.valueOf(1)), od("LIS标本未映射", Integer.valueOf(2));

	private String name;
	private Integer value;

	private bh(String name, Integer value) {
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