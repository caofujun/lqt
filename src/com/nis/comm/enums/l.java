package com.nis.comm.enums;

public enum l {
	gb("检验结果", "TEST_RESULT"), gc("备注", "REMARK");

	private String name;
	private String value;

	private l(String name, String value) {
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