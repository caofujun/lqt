package com.nis.comm.enums;

public enum c {
	fx(Integer.valueOf(1), "所有"), fy(Integer.valueOf(2), "不可见");

	private Integer fv;
	private String name;

	private c(Integer code, String name) {
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