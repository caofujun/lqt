package com.nis.comm.enums;

import com.nis.comm.entity.KvEntity;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public enum at {
	kZ("0", "不加密", "{}"), la("10", "md5(token)", "{token:\"sdfsdfsfsdf\",sign:\"encryptionParameters\"}"), lb("20",
			"md5(渠道+token)", "{channel:\"50\",token:\"sdfsdfsfsdf\",sign:\"encryptionParameters\"}"), lc("30",
					"md5(时间戳+token)",
					"{time:\"theCurrentTimestamp\",token:\"sdfsdfsfsdf\",sign:\"encryptionParameters\"}"), ld("40",
							"md5(渠道+时间戳+token)",
							"{channel:\"50\",time:\"theCurrentTimestamp\",token:\"sdfsdfsfsdf\",sign:\"encryptionParameters\"}");

	public static final String KEY = "project_sign";
	private String code;
	private String name;
	private String le;
	private static List<KvEntity> list = new ArrayList();
	private static Map<String, String> map = new HashMap();

	static {
		EnumSet set = EnumSet.allOf(at.class);
		Iterator arg1 = set.iterator();

		while (arg1.hasNext()) {
			at e = (at) arg1.next();
			map.put(e.getCode(), e.getName());
			list.add(new KvEntity(e.getCode().toString(), e.getName(), e.getParams()));
		}

	}

	private at(String code, String name, String params) {
		this.code = code;
		this.name = name;
		this.le = params;
	}

	public static String getText(String code) {
		return (String) map.get(code);
	}

	public static List<KvEntity> getList() {
		return list;
	}

	public String getCode() {
		return this.code;
	}

	public String getName() {
		return this.name;
	}

	public String getParams() {
		return this.le;
	}
}