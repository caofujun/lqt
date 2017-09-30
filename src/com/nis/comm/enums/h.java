package com.nis.comm.enums;

import com.nis.comm.entity.KvEntity;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public enum h {
	fP("是", Integer.valueOf(1), true), fQ("否", Integer.valueOf(0), false);

	private String name;
	private Integer fv;
	private boolean value;
	private static List<KvEntity> list = new ArrayList();
	private static Map<Integer, String> map = new HashMap();

	static {
		EnumSet set = EnumSet.allOf(h.class);
		Iterator arg1 = set.iterator();

		while (arg1.hasNext()) {
			h e = (h) arg1.next();
			map.put(e.getCode(), e.getName());
			list.add(new KvEntity(e.getCode().toString(), e.getName()));
		}

	}

	private h(String name, Integer code, boolean value) {
		this.name = name;
		this.fv = code;
		this.value = value;
	}

	public static String b(Integer code) {
		return (String) map.get(code);
	}

	public String getName() {
		return this.name;
	}

	public Integer getCode() {
		return this.fv;
	}

	public boolean r() {
		return this.value;
	}
}