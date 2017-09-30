package com.nis.comm.enums;

public enum ay {
	lt("短信问卷", "qs_type_sms"), lu("随访问卷", "qs_type_fo"), lv("网络问卷", "internet"), lw("医院服务", "code_service"), lx("服务",
			"1"), ly("教育", "2"), lz("操作", "3"), lA("住院", "4"), lB("急诊",
					"emergency"), lC("导诊", "guide"), lD("分诊", "triage"), lE("综合满意度问卷", "zh_satisfaction");

	private String name;
	private String value;

	private ay(String name, String value) {
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