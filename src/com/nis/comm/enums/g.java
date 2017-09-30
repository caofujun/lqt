package com.nis.comm.enums;

import java.util.EnumSet;
import java.util.Iterator;

public enum g {
	fK("正常", Integer.valueOf(0)), fL("迟报", Integer.valueOf(1)), fM("漏报", Integer.valueOf(2)), fN("", (Integer) null);

	private String name;
	private Integer value;

	private g(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public Integer getValue() {
		return this.value;
	}

	public static g a(Integer value) {
		if (value == null) {
			return fN;
		} else {
			EnumSet set = EnumSet.allOf(g.class);
			Iterator arg2 = set.iterator();

			while (arg2.hasNext()) {
				g e = (g) arg2.next();
				if (value.equals(e.getValue())) {
					return e;
				}
			}

			return fN;
		}
	}
}