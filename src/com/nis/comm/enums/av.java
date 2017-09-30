package com.nis.comm.enums;

public enum av {
	lj("未干预", "0"), lk("已干预", "2"), ll("不可干预", "1");

	private String name;
	private String value;

	private av(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}