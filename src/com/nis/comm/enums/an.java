package com.nis.comm.enums;

public enum an {
	kl("网络异常", Integer.valueOf(-1)), km("系统异常", Integer.valueOf(-2)), kn("发送成功", Integer.valueOf(1)), ko("发送失败",
			Integer.valueOf(2));

	private String name;
	private Integer fv;

	private an(String name, Integer code) {
		this.name = name;
		this.fv = code;
	}

	public String getName() {
		return this.name;
	}

	public Integer getCode() {
		return this.fv;
	}
}