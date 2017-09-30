package com.nis.comm.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum ap {
	kx(Integer.valueOf(0), "随访小结"), ky(Integer.valueOf(1), "新增患者"), kz(Integer.valueOf(2), "给患者发送短信"), kA(
			Integer.valueOf(3),
			"给患者打标签"), kB(Integer.valueOf(4), "将患者排除所在的分组"), kC(Integer.valueOf(5), "停止患者的计划"), kD(Integer.valueOf(6),
					"制订患者的计划"), kE(Integer.valueOf(7), "修改患者资料"), kF(Integer.valueOf(8), "患者加入分组"), kG(
							Integer.valueOf(9), "编辑患者采集单"), kH(Integer.valueOf(10), "编辑患者就诊记录"), kI(Integer.valueOf(11),
									"编辑患者分组"), kJ(Integer.valueOf(12), "删除患者分组"), kK(Integer.valueOf(13),
											"修改患者分组状态"), kL(Integer.valueOf(14), "患者分组绑定计划"), kM(Integer.valueOf(15),
													"删除患者采集单"), kN(Integer.valueOf(16), "上传患者的病历图片");

	private Integer value;
	private String name;
	private static Map<Integer, String> map = new HashMap();

	static {
		EnumSet set = EnumSet.allOf(ap.class);
		Iterator arg1 = set.iterator();

		while (arg1.hasNext()) {
			ap e = (ap) arg1.next();
			map.put(e.getValue(), e.getName());
		}

	}

	private ap(Integer value, String name) {
		this.value = value;
		this.name = name;
	}

	public static Map<Integer, String> getMap() {
		return map;
	}

	public static String b(Integer code) {
		return (String) map.get(code);
	}

	public String getName() {
		return this.name;
	}

	public Integer getValue() {
		return this.value;
	}
}