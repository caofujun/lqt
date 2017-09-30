package com.nis.comm.utils;

import com.nis.comm.utils.f;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class p {
	public static String b(Map map, String key) {
		if (map == null) {
			return null;
		} else {
			Object value = map.get(key);
			return value == null ? null : value.toString();
		}
	}

	public static Integer c(Map map, String key) {
		if (map == null) {
			return null;
		} else {
			Object value = map.get(key);
			return value != null && !"".equals(value.toString()) ? Integer.valueOf(value.toString()) : null;
		}
	}

	public static Double d(Map map, String key) {
		if (map == null) {
			return null;
		} else {
			Object value = map.get(key);
			return value != null && !"".equals(value.toString()) ? Double.valueOf(value.toString()) : null;
		}
	}

	public static Double a(Map map, String key, Double defValue) {
		if (map == null) {
			return null;
		} else {
			Object value = map.get(key);
			return value != null && !"".equals(value.toString()) ? Double.valueOf(value.toString()) : defValue;
		}
	}

	public static Long e(Map map, String key) {
		if (map == null) {
			return null;
		} else {
			Object value = map.get(key);
			return value != null && !"".equals(value.toString()) ? Long.valueOf(value.toString()) : null;
		}
	}

	public static List<String> f(Map map, String key) {
		if (map == null) {
			return null;
		} else {
			Object value = map.get(key);
			return value == null ? null : (List) value;
		}
	}

	public static List<Map> g(Map map, String key) {
		if (map == null) {
			return null;
		} else {
			Object value = map.get(key);
			return value == null ? null : (List) value;
		}
	}

	public static Map h(Map map, String key) {
		if (map == null) {
			return null;
		} else {
			Object value = map.get(key);
			return value == null ? null : (Map) value;
		}
	}

	public static Date a(Map map, String key, String fmt) {
		if (map == null) {
			return null;
		} else {
			Object value = map.get(key);
			return value != null && !"".equals(value.toString()) ? f.l(value.toString(), fmt) : null;
		}
	}

	public static Float i(Map<String, Object> map, String key) {
		if (map == null) {
			return null;
		} else {
			Object value = map.get(key);
			return value != null && !"".equals(value.toString()) ? Float.valueOf(value.toString()) : null;
		}
	}

	public static Map<String, Object> c(Map<String, Object> map) {
		if (map == null) {
			return null;
		} else {
			HashMap newMap = new HashMap();
			Iterator arg2 = map.entrySet().iterator();

			while (arg2.hasNext()) {
				Entry entry = (Entry) arg2.next();
				if (entry.getValue() == null) {
					newMap.put((String) entry.getKey(), "");
				} else {
					newMap.put((String) entry.getKey(), entry.getValue());
				}
			}

			return newMap;
		}
	}
}