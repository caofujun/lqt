package com.nis.comm.enums;

import com.nis.comm.entity.KvEntity;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

public enum ar {
	kT("clinical", "临床科室"), kU("hospital", "院感科");

	private String code;
	private String name;
	private static List<KvEntity> list = new ArrayList();

	public static List<KvEntity> getList() {
		if (list.size() > 0) {
			return list;
		} else {
			EnumSet set = EnumSet.allOf(ar.class);
			Iterator arg1 = set.iterator();

			while (arg1.hasNext()) {
				ar p = (ar) arg1.next();
				list.add(new KvEntity(p.getCode(), p.getName()));
			}

			return list;
		}
	}

	private ar(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String getCode() {
		return this.code;
	}
}