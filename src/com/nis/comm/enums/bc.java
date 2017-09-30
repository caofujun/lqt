package com.nis.comm.enums;

import java.util.EnumSet;
import java.util.Iterator;

public enum bc {
	lU("医生上报", Integer.valueOf(1)), lV("院感上报", Integer.valueOf(2)), lW("系统预警", (Integer) null);

	private String name;
	private Integer value;

	private bc(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public Integer getValue() {
		return this.value;
	}

	public static bc n(Integer value) {
		if (value == null) {
			return lW;
		} else {
			EnumSet set = EnumSet.allOf(bc.class);
			Iterator arg2 = set.iterator();

			while (arg2.hasNext()) {
				bc e = (bc) arg2.next();
				if (value.equals(e.getValue())) {
					return e;
				}
			}

			return lW;
		}
	}
}