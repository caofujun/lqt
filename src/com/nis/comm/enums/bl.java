package com.nis.comm.enums;

public enum bl {
	ou("收录", "1"), ov("测试", "2"), ow("已开通", "3"), ox("待审核", "4"), oy("已删除", "5");

	private String name;
	private String code;

	private bl(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public String getCode() {
		return this.code;
	}
}