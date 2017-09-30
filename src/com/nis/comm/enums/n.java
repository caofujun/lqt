package com.nis.comm.enums;

public enum n {
	gh("本人", Integer.valueOf(0)), gi("本科室", Integer.valueOf(3)), gj("多科室", Integer.valueOf(4)), gk("本院",
			Integer.valueOf(6)), gm("平台", Integer.valueOf(9));

	private String name;
	private Integer value;

	private n(String name, Integer value) {
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