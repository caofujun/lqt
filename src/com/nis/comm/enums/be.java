package com.nis.comm.enums;

import java.util.EnumSet;
import java.util.Iterator;

public enum be {
	me("待处理", Integer.valueOf(-1)), mf("不合格", Integer.valueOf(1)), mg("合格", Integer.valueOf(0)), mh("待处理",
			(Integer) null);

	private String name;
	private Integer value;

	private be(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public Integer getValue() {
		return this.value;
	}

	public static be p(Integer value) {
		if (value == null) {
			return mh;
		} else {
			EnumSet set = EnumSet.allOf(be.class);
			Iterator arg2 = set.iterator();

			while (arg2.hasNext()) {
				be e = (be) arg2.next();
				if (value.equals(e.getValue())) {
					return e;
				}
			}

			return mh;
		}
	}
}