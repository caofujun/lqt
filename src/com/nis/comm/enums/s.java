package com.nis.comm.enums;

import java.util.EnumSet;
import java.util.Iterator;

public enum s {
	gN("用户表", Integer.valueOf(0)), gO("职工表", Integer.valueOf(1)), gP("未知", (Integer) null);

	private String name;
	private Integer value;

	private s(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public Integer getValue() {
		return this.value;
	}

	public static s e(Integer value) {
		if (value == null) {
			return gP;
		} else {
			EnumSet set = EnumSet.allOf(s.class);
			Iterator arg2 = set.iterator();

			while (arg2.hasNext()) {
				s e = (s) arg2.next();
				if (value.equals(e.getValue())) {
					return e;
				}
			}

			return gP;
		}
	}
}