package com.nis.comm.enums;

public enum ah {
	iz("insert", "新增"), iA("update", "修改"), iB("delete", "删除"), iC("login", "登录"), iD("logout", "退出"), iE("apply",
			"申请"), iF("audit", "审核"), iG("error", "错误"), iH("yuyue", "复诊预约"), iI("sql", "SQL");

	private String value;
	private String name;

	private ah(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String getValue() {
		return this.value;
	}
}