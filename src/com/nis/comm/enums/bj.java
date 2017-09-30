package com.nis.comm.enums;

public enum bj {
	oi("患者档案库", "huanzhe_dangan"), oj("患者分组管理", "huanzhe_fenzu"), ok("随访查询统计", "sf_chaxun"), ol("知识库管理",
			"zhishi_ku"), om("报表管理", "report"), on("下拉列表记忆", "depNo"), oo("", (String) null);

	private String name;
	private String value;

	private bj(String name, String value) {
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