package com.nis.comm.enums;

public enum b {
	fu(Integer.valueOf(1), "菜单");

	private Integer fv;
	private String name;

	private b(Integer code, String name) {
		this.fv = code;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public Integer getCode() {
		return this.fv;
	}
}