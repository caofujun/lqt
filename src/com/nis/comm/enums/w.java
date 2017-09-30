package com.nis.comm.enums;

import java.util.EnumSet;
import java.util.Iterator;

public enum w {
	hh("零类切口", "0"), hi("I类切口", "1"), hk("II类切口", "2"), hl("III类切口", "3"), hm("IV类切口", "4"), hn("", (String) null);

	private String name;
	private String value;

	private w(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public String getValue() {
		return this.value;
	}

	public static w A(String name) {
		if (name == null) {
			return hn;
		} else {
			EnumSet set = EnumSet.allOf(w.class);
			Iterator arg2 = set.iterator();

			while (arg2.hasNext()) {
				w e = (w) arg2.next();
				if (name.equals(e.getName())) {
					return e;
				}
			}

			return hn;
		}
	}

	public static w B(String value) {
		if (value == null) {
			return hn;
		} else {
			EnumSet set = EnumSet.allOf(w.class);
			Iterator arg2 = set.iterator();

			while (arg2.hasNext()) {
				w e = (w) arg2.next();
				if (value.equals(e.getValue())) {
					return e;
				}
			}

			return hn;
		}
	}
}