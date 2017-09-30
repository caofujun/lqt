package com.nis.comm.enums;

public enum ag {
	ix("pms{access、organization、user}", "权限{授权、机构、用户}");

	private String value;
	private String name;

	private ag(String value, String name) {
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