package com.nis.comm.enums;

import java.util.EnumSet;
import java.util.Iterator;

public enum az {
	lG("未读", Integer.valueOf(0)), lH("已读", Integer.valueOf(1)), lI("已删除", Integer.valueOf(2)), lJ("", (Integer) null);

	private String name;
	private Integer value;

	private az(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public Integer getValue() {
		return this.value;
	}

	public static az l(Integer value) {
		if (value == null) {
			return lJ;
		} else {
			EnumSet set = EnumSet.allOf(az.class);
			Iterator arg2 = set.iterator();

			while (arg2.hasNext()) {
				az e = (az) arg2.next();
				if (value.equals(e.getValue())) {
					return e;
				}
			}

			return lJ;
		}
	}
}