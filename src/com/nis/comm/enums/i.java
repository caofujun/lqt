package com.nis.comm.enums;

import java.util.EnumSet;
import java.util.Iterator;

public enum i {
	fS("相对预警", Integer.valueOf(0)), fT("绝对预警", Integer.valueOf(1)), fU("", (Integer) null);

	private String name;
	private Integer value;

	private i(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public Integer getValue() {
		return this.value;
	}

	public static i c(Integer value) {
		if (value == null) {
			return fU;
		} else {
			EnumSet set = EnumSet.allOf(i.class);
			Iterator arg2 = set.iterator();

			while (arg2.hasNext()) {
				i e = (i) arg2.next();
				if (value.equals(e.getValue())) {
					return e;
				}
			}

			return fU;
		}
	}
}