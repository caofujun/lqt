package com.nis.comm.enums;

import java.util.EnumSet;
import java.util.Iterator;

public enum ab {
	hI("科室信息表", "JK_DIC_OFFICE", new Integer(1)), hJ("医生信息表", "JK_DIC_DOCTOR", new Integer(2)), hK("诊断字典表",
			"JK_DIC_DISEASE", new Integer(3)), hL("抗菌药物字典表", "JK_DIC_DRUG", new Integer(4)), hM("患者门诊信息表",
					"JK_PATIENT_CLINIC",
					new Integer(5)), hN("患者入出院信息表", "JK_PATIENT_VISIT", new Integer(6)), hO("患者转科表", "JK_PATIENT_ZKMX",
							new Integer(7)), hP("患者诊断信息表", "JK_PATIENT_DIAGNOSE", new Integer(8)), hQ("患者体温信息表",
									"JK_PATIENT_TEMPRATURE", new Integer(9)), hR("患者大便记录表", "JK_PATIENT_ROUTINE",
											new Integer(10)), hS("患者影像信息表", "JK_PATIENT_YX",
													new Integer(11)), hT("患者手术信息表", "JK_PATIENT_OPERATION", new Integer(
															12)), hU("患者病程信息表", "JK_PATIENT_BC", new Integer(13)), hV(
																	"患者医嘱信息表", "JK_PATIENT_YZ", new Integer(14)), hW(
																			"患者检验记录表", "JK_PATIENT_LAB_ITEMS",
																			new Integer(15)), hX("患者检验结果表",
																					"JK_PATIENT_LAB_ANTI",
																					new Integer(16)), hY("患者检验病原体表",
																							"JK_PATIENT_LAB_PATHO",
																							new Integer(17));

	private String name;
	private String code;
	private Integer sortNo;

	private ab(String name, String code, Integer sortNo) {
		this.name = name;
		this.code = code;
		this.sortNo = sortNo;
	}

	public String getName() {
		return this.name;
	}

	public String getCode() {
		return this.code;
	}

	public Integer getSortNo() {
		return this.sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public static ab C(String code) {
		if (code == null) {
			return null;
		} else {
			EnumSet set = EnumSet.allOf(ab.class);
			Iterator arg2 = set.iterator();

			while (arg2.hasNext()) {
				ab e = (ab) arg2.next();
				if (code.equals(e.getCode())) {
					return e;
				}
			}

			return null;
		}
	}

	public static String getString() {
		String tables = "";
		EnumSet set = EnumSet.allOf(ab.class);

		ab e;
		for (Iterator arg2 = set.iterator(); arg2.hasNext(); tables = tables + e.code + ",") {
			e = (ab) arg2.next();
		}

		return tables;
	}
}