package com.nis.comm.enums;

public enum bk {
	oq("单选题", Integer.valueOf(1)), or("多选题", Integer.valueOf(2)), os("填空题", Integer.valueOf(3));

	private String name;
	private Integer value;

	private bk(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public Integer getValue() {
		return this.value;
	}

	public static bk q(Integer value) {
		return oq.getValue().equals(value)
				? oq
				: (or.getValue().equals(value) ? or : (os.getValue().equals(value) ? os : null));
	}
}