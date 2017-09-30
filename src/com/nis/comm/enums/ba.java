package com.nis.comm.enums;

import java.util.EnumSet;
import java.util.Iterator;

public enum ba {
	lL("未登记", Integer.valueOf(0)), lM("已登记", Integer.valueOf(1)), lN("未登记", (Integer) null);

	private String name;
	private Integer value;

	private ba(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public Integer getValue() {
		return this.value;
	}

	public static ba m(Integer value) {
		if (value == null) {
			return lN;
		} else {
			EnumSet set = EnumSet.allOf(ba.class);
			Iterator arg2 = set.iterator();

			while (arg2.hasNext()) {
				ba e = (ba) arg2.next();
				if (value.equals(e.getValue())) {
					return e;
				}
			}

			return lN;
		}
	}
}