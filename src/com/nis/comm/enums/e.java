package com.nis.comm.enums;

public enum e {
	fD("医院端", "hospital"), fE("临床端", "clinical");

	private String name;
	private String value;

	private e(String name, String value) {
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