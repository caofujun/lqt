package com.nis.comm.enums;

public enum al {
	jl("普通消息", "0"), jm("干预消息", "1"), jn("病例消息", "2"), jo("职业暴露监测", "3"), jp("环境监测结果", "4"), jq("感染预警", "5"), jr("感染预警",
			"50"), js("复查提醒", "6"), jt("接口预警提醒", "7"), ju("传染病报卡上报消息", "8"), jv("死因报卡上报消息", "9"), jw("食源异常报卡上报消息",
					"10"), jx("食源监测报卡上报消息", "11"), jy("肿瘤报卡上报消息", "12"), jz("心脑血管报卡上报消息", "13"), jA("高温中暑报卡上报消息",
							"14"), jB("传染病报卡退回消息", "15"), jC("死因报卡退回消息", "16"), jD("食源异常报卡退回消息", "17"), jE("食源监测报卡退回消息",
									"18"), jF("肿瘤报卡退回消息", "19"), jG("心脑血管报卡退回消息", "20"), jH("高温中暑报卡退回消息", "21"), jI(
											"传染病报卡删卡消息",
											"22"), jJ("死因报卡删卡消息", "23"), jK("食源异常报卡删卡消息", "24"), jL("食源监测报卡删卡消息",
													"25"), jM("肿瘤报卡删卡消息", "26"), jN("心脑血管报卡删卡消息", "27"), jO(
															"高温中暑报卡删卡消息",
															"28"), jP("传染病报卡审核消息", "29"), jQ("死因报卡审核消息", "30"), jR(
																	"食源异常报卡审核消息",
																	"31"), jS("食源监测报卡审核消息", "32"), jT("肿瘤报卡审核消息",
																			"33"), jU("心脑血管报卡审核消息", "34"), jV(
																					"高温中暑报卡审核消息",
																					"35"), jW("多耐干预消息", "36"), jX(
																							"农药中毒报卡上报消息",
																							"100"), jY("农药中毒报卡退回消息",
																									"130"), jZ(
																											"农药中毒报卡删卡消息",
																											"160"), ka(
																													"农药中毒报卡审核消息",
																													"190");

	private String name;
	private String value;

	private al(String name, String value) {
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