package com.nis.comm.enums;

import java.util.EnumSet;
import java.util.Iterator;

public enum am {
	kc("BW<=750g", "00", (Integer) null, Integer.valueOf(750)), kd("BW751g~1000g", "01", Integer.valueOf(751),
			Integer.valueOf(1000)), ke("BW1001g～1500g", "02", Integer.valueOf(1001), Integer.valueOf(1500)), kf(
					"BW1501g～2500g", "03", Integer.valueOf(1501), Integer.valueOf(2500)), kg("BW>2500g", "04",
							Integer.valueOf(2501),
							(Integer) null), kh("", (String) null, (Integer) null, (Integer) null);

	private String name;
	private String value;
	private Integer ki;
	private Integer kj;

	private am(String name, String value, Integer bwGt, Integer bwLt) {
		this.name = name;
		this.value = value;
		this.ki = bwGt;
		this.kj = bwLt;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getBwGt() {
		return this.ki;
	}

	public void setBwGt(Integer bwGt) {
		this.ki = bwGt;
	}

	public Integer getBwLt() {
		return this.kj;
	}

	public void setBwLt(Integer bwLt) {
		this.kj = bwLt;
	}

	public static am F(String value) {
		if (value == null) {
			return kh;
		} else {
			EnumSet set = EnumSet.allOf(am.class);
			Iterator arg2 = set.iterator();

			while (arg2.hasNext()) {
				am e = (am) arg2.next();
				if (value.equals(e.getValue())) {
					return e;
				}
			}

			return kh;
		}
	}

	public static am a(Double weight) {
		if (weight == null) {
			return kh;
		} else {
			EnumSet set = EnumSet.allOf(am.class);
			Iterator arg2 = set.iterator();

			while (arg2.hasNext()) {
				am e = (am) arg2.next();
				if (e.value != null) {
					if (e.value.equals("04")) {
						if (weight.intValue() >= e.getBwGt().intValue()) {
							return e;
						}
					} else if (e.value.equals("00")) {
						if (weight.intValue() <= e.getBwLt().intValue()) {
							return e;
						}
					} else if (weight.intValue() >= e.getBwGt().intValue()
							&& weight.intValue() <= e.getBwLt().intValue()) {
						return e;
					}
				}
			}

			return kh;
		}
	}
}