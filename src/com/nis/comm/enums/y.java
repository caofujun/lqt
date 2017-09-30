package com.nis.comm.enums;

import java.util.EnumSet;
import java.util.Iterator;

public enum y {
	hu("医院感染", "院感", Integer.valueOf(1)), hv("社区感染", "社感", Integer.valueOf(2)), hw("定植", "定植",
			Integer.valueOf(3)), hx("", "", (Integer) null);

	private String name;
	private String shortName;
	private Integer value;

	private y(String name, String shortName, Integer value) {
		this.name = name;
		this.shortName = shortName;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public String getShortName() {
		return this.shortName;
	}

	public Integer getValue() {
		return this.value;
	}

	public static y h(Integer value) {
		if (value == null) {
			return hx;
		} else {
			EnumSet set = EnumSet.allOf(y.class);
			Iterator arg2 = set.iterator();

			while (arg2.hasNext()) {
				y e = (y) arg2.next();
				if (value.equals(e.getValue())) {
					return e;
				}
			}

			return hx;
		}
	}
}