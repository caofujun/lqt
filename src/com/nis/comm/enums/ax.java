package com.nis.comm.enums;

public enum ax {
	lq("已发布", Integer.valueOf(1)), lr("未发布", Integer.valueOf(0));

	private String name;
	private int code;

	private ax(String name, Integer code) {
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