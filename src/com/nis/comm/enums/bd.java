package com.nis.comm.enums;

import java.util.EnumSet;
import java.util.Iterator;

public enum bd {
	lY("敏感", Integer.valueOf(0)), lZ("MDR", Integer.valueOf(1)), ma("XDR", Integer.valueOf(2)), mb("PDR",
			Integer.valueOf(3)), mc("", (Integer) null);

	private String name;
	private Integer value;

	private bd(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public Integer getValue() {
		return this.value;
	}

	public static bd o(Integer value) {
		if (value == null) {
			return mc;
		} else {
			EnumSet set = EnumSet.allOf(bd.class);
			Iterator arg2 = set.iterator();

			while (arg2.hasNext()) {
				bd e = (bd) arg2.next();
				if (value.equals(e.getValue())) {
					return e;
				}
			}

			return mc;
		}
	}
}