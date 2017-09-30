package com.nis.comm.enums;

public enum bf {
	mj("未发送", Integer.valueOf(0)), mk("已发送", Integer.valueOf(1)), ml("收到应答", Integer.valueOf(2));

	private String name;
	private Integer value;

	private bf(String name, Integer value) {
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