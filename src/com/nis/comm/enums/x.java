package com.nis.comm.enums;

import java.util.EnumSet;
import java.util.Iterator;

public enum x {
	hp("普通感染", Integer.valueOf(1)), hq("ICU感染", Integer.valueOf(2)), hr("NICU感染", Integer.valueOf(3)), hs("普通感染",
			(Integer) null);

	private String name;
	private Integer value;

	private x(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public Integer getValue() {
		return this.value;
	}

	public static x g(Integer value) {
		if (value == null) {
			return hs;
		} else {
			EnumSet set = EnumSet.allOf(x.class);
			Iterator arg2 = set.iterator();

			while (arg2.hasNext()) {
				x e = (x) arg2.next();
				if (value.equals(e.getValue())) {
					return e;
				}
			}

			return hs;
		}
	}
}