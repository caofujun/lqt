package com.nis.comm.enums;

public enum u {
	gY("未干预", "0"), gZ("已干预", "1");

	private String name;
	private String value;

	private u(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public String getValue() {
		return this.value;
	}
}