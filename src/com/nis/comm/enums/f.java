package com.nis.comm.enums;

public enum f {
	fG("根据用户获取消息", "getMsgByUser"), fH("获取参数", "getParam"), fI("发送新消息", "sendMsg");

	private String name;
	private String value;

	private f(String name, String value) {
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