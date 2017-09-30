package com.nis.comm.enums;

public enum aw {
	ln("正常", Integer.valueOf(1)), lo("删除", Integer.valueOf(0));

	private String name;
	private int code;

	private aw(String name, Integer code) {
		this.name = name;
		this.code = code.intValue();
	}

	public String getName() {
		return this.name;
	}

	public Integer getCode() {
		return Integer.valueOf(this.code);
	}
}