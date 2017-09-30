package com.nis.comm.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum v {
	hb("手消毒液", Integer.valueOf(1), "ml/瓶", "瓶", "ml"), hc("普通洗手液", Integer.valueOf(2), "ml/瓶", "瓶", "ml"), hd("抗菌洗手液",
			Integer.valueOf(3), "ml/瓶", "瓶",
			"ml"), he("干手纸", Integer.valueOf(4), "抽/盒", "盒", "抽"), hf("", (Integer) null, "", "", "");

	private String name;
	private Integer value;
	private String specificaUnit;
	private String usedUnit;
	private String inventoryUnit;

	private v(String name, Integer value, String specificaUnit, String usedUnit, String inventoryUnit) {
		this.name = name;
		this.value = value;
		this.specificaUnit = specificaUnit;
		this.usedUnit = usedUnit;
		this.inventoryUnit = inventoryUnit;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return this.value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getSpecificaUnit() {
		return this.specificaUnit;
	}

	public void setSpecificaUnit(String specificaUnit) {
		this.specificaUnit = specificaUnit;
	}

	public String getUsedUnit() {
		return this.usedUnit;
	}

	public void setUsedUnit(String usedUnit) {
		this.usedUnit = usedUnit;
	}

	public String getInventoryUnit() {
		return this.inventoryUnit;
	}

	public void setInventoryUnit(String inventoryUnit) {
		this.inventoryUnit = inventoryUnit;
	}

	public static v f(Integer value) {
		if (value == null) {
			return hf;
		} else {
			EnumSet set = EnumSet.allOf(v.class);
			Iterator arg2 = set.iterator();

			while (arg2.hasNext()) {
				v e = (v) arg2.next();
				if (value.equals(e.getValue())) {
					return e;
				}
			}

			return hf;
		}
	}

	public static Map<Integer, Map<String, String>> s() {
		HashMap map = new HashMap();
		EnumSet set = EnumSet.allOf(v.class);
		Iterator arg2 = set.iterator();

		while (arg2.hasNext()) {
			v e = (v) arg2.next();
			if (e.getValue() != null) {
				HashMap m = new HashMap();
				m.put("specificaUnit", e.getSpecificaUnit());
				m.put("usedUnit", e.getUsedUnit());
				m.put("inventoryUnit", e.getInventoryUnit());
				map.put(e.getValue(), m);
			}
		}

		return map;
	}
}