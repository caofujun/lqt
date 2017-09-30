package com.nis.comm.enums;

import java.util.EnumSet;
import java.util.Iterator;

public enum ao {
	kq("未维护", Integer.valueOf(0)), kr("已维护手术信息", Integer.valueOf(1)), ks("已维护用药信息", Integer.valueOf(2)), kt("已维护院外感染",
			Integer.valueOf(3)), ku("已归档", Integer.valueOf(4)), kv("", (Integer) null);

	private String name;
	private Integer value;

	private ao(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public Integer getValue() {
		return this.value;
	}

	public static ao j(Integer value) {
		if (value == null) {
			return kv;
		} else {
			EnumSet set = EnumSet.allOf(ao.class);
			Iterator arg2 = set.iterator();

			while (arg2.hasNext()) {
				ao e = (ao) arg2.next();
				if (value.equals(e.getValue())) {
					return e;
				}
			}

			return kv;
		}
	}
}