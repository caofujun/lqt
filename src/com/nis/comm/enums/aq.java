package com.nis.comm.enums;

import java.util.EnumSet;
import java.util.Iterator;

public enum aq {
	kP("临嘱", Integer.valueOf(0)), kQ("长嘱", Integer.valueOf(1)), kR("未知", (Integer) null);

	private String name;
	private Integer value;

	private aq(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public Integer getValue() {
		return this.value;
	}

	public static aq k(Integer value) {
		if (value == null) {
			return kR;
		} else {
			EnumSet set = EnumSet.allOf(aq.class);
			Iterator arg2 = set.iterator();

			while (arg2.hasNext()) {
				aq e = (aq) arg2.next();
				if (value.equals(e.getValue())) {
					return e;
				}
			}

			return kR;
		}
	}
}