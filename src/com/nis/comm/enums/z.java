package com.nis.comm.enums;

import java.util.EnumSet;
import java.util.Iterator;

public enum z {
	hz("未确认", Integer.valueOf(0)), hA("已确认", Integer.valueOf(1)), hB("退卡", Integer.valueOf(2)), hC("删卡",
			Integer.valueOf(3)), hD("未知", (Integer) null);

	private String name;
	private Integer value;

	private z(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public Integer getValue() {
		return this.value;
	}

	public static z i(Integer value) {
		if (value == null) {
			return hD;
		} else {
			EnumSet set = EnumSet.allOf(z.class);
			Iterator arg2 = set.iterator();

			while (arg2.hasNext()) {
				z e = (z) arg2.next();
				if (value.equals(e.getValue())) {
					return e;
				}
			}

			return hD;
		}
	}
}