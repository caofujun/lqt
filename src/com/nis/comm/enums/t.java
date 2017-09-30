package com.nis.comm.enums;

public enum t {
	gR("病人随访工作报告", "report_brsf"), gS("满意度调查分析报告", "report_myddc"), gT("按科室问卷统计", "report_kswj"), gU("问卷填写详情统计",
			"report_wjtx"), gV("分享问卷科室下拉框", "depNo_questShare"), gW("", (String) null);

	private String name;
	private String value;

	private t(String name, String value) {
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