package com.nis.comm.enums;

import java.util.EnumSet;
import java.util.Iterator;

public enum r {
	gH("非", Integer.valueOf(0)), gI("一线", Integer.valueOf(1)), gJ("二线", Integer.valueOf(2)), gK("三线",
			Integer.valueOf(3)), gL("", (Integer) null);

	private String name;
	private Integer value;

	private r(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public Integer getValue() {
		return this.value;
	}

	public static r d(Integer value) {
		if (value == null) {
			return gL;
		} else {
			EnumSet set = EnumSet.allOf(r.class);
			Iterator arg2 = set.iterator();

			while (arg2.hasNext()) {
				r e = (r) arg2.next();
				if (value.equals(e.getValue())) {
					return e;
				}
			}

			return gL;
		}
	}
}