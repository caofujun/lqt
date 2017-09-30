package com.nis.comm.enums;

public enum au {
	lg("已发布", Integer.valueOf(1)), lh("未发布", Integer.valueOf(0));

	private String name;
	private int code;

	private au(String name, Integer code) {
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