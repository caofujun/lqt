package com.nis.comm.enums;

import java.util.EnumSet;
import java.util.Iterator;

public enum bb {
	lP("中心静脉插管", "01"), lQ("导尿管插管", "02"), lR("呼吸机", "03"), lS("", (String) null);

	private String name;
	private String value;

	private bb(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public String getValue() {
		return this.value;
	}

	public static bb G(String name) {
		if (name == null) {
			return lS;
		} else {
			EnumSet set = EnumSet.allOf(bb.class);
			Iterator arg2 = set.iterator();

			while (arg2.hasNext()) {
				bb e = (bb) arg2.next();
				if (name.equals(e.getName())) {
					return e;
				}
			}

			return lS;
		}
	}

	public static bb H(String value) {
		if (value == null) {
			return lS;
		} else {
			EnumSet set = EnumSet.allOf(bb.class);
			Iterator arg2 = set.iterator();

			while (arg2.hasNext()) {
				bb e = (bb) arg2.next();
				if (value.equals(e.getValue())) {
					return e;
				}
			}

			return lS;
		}
	}
}