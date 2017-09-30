package com.nis.comm.enums;

public enum o {
	gp("住院", "1"), gq("门诊", "2"), gs("急诊", "3"), gt("留观", "4"), gu("医技", "5"), gv("", "");

	private String name;
	private String value;

	private o(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public String getValue() {
		return this.value;
	}

	public static o z(String value) {
		return gp.getValue().equals(value)
				? gp
				: (gq.getValue().equals(value)
						? gq
						: (gs.getValue().equals(value)
								? gs
								: (gt.getValue().equals(value) ? gt : (gu.getValue().equals(value) ? gu : gv))));
	}
}