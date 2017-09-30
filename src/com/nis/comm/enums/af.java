package com.nis.comm.enums;

public enum af {
	iv("pms{access、organization、user}", "权限{授权、机构、用户}");

	private String value;
	private String name;

	private af(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String getValue() {
		return this.value;
	}
}