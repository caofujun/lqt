package com.nis.comm.enums;

import com.nis.comm.entity.KvEntity;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public enum ac {
	ia(Integer.valueOf(0), "正常"), ib(Integer.valueOf(50), "获取异常");

	public static final String KEY = "job_log_status";
	private Integer fv;
	private String name;
	private static List<KvEntity> list = new ArrayList();
	private static Map<Integer, String> map = new HashMap();

	static {
		EnumSet set = EnumSet.allOf(ac.class);
		Iterator arg1 = set.iterator();

		while (arg1.hasNext()) {
			ac e = (ac) arg1.next();
			map.put(e.getCode(), e.getName());
			list.add(new KvEntity(e.getCode().toString(), e.getName()));
		}

	}

	private ac(Integer code, String name) {
		this.fv = code;
		this.name = name;
	}

	public static String b(Integer code) {
		return (String) map.get(code);
	}

	public static List<KvEntity> getList() {
		return list;
	}

	public Integer getCode() {
		return this.fv;
	}

	public String getName() {
		return this.name;
	}
}