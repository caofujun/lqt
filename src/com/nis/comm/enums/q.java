package com.nis.comm.enums;

public enum q {
	gB("题库的题型", "topic_type"), gC("题目分类", "topic_classify"), gD("问卷分类", "qs_type"), gE("是否", "boolean"), gF("菜单类型",
			"menu_type");

	private String name;
	private String value;

	private q(String name, String value) {
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