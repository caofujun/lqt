package com.nis.comm.enums;

import java.util.EnumSet;
import java.util.Iterator;

public enum ak {
	iS("入院", "inHosp"), iT("出院", "outHosp"), iU("转科", "inTransfer"), iV("转科", "outTransfer"), iW("病历", "emr"), iX("手术",
			"operation"), iY("医嘱", "order"), iZ("影像", "pacs"), ja("检验", "test"), jb("体温", "nurse"), jc("三管分析",
					"tube"), jd("多耐分析", "mdr"), je("病程分析", "course"), jf("院感预警分析", "monitor"), jh("KETTLE中间库",
							"kettle_jk"), ji("KETTLE业务库库", "kettle_nis"), jj("", (String) null);

	private String name;
	private String code;

	private ak(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public String getCode() {
		return this.code;
	}

	public static ak E(String code) {
		if (code == null) {
			return jj;
		} else {
			EnumSet set = EnumSet.allOf(ak.class);
			Iterator arg2 = set.iterator();

			while (arg2.hasNext()) {
				ak e = (ak) arg2.next();
				if (code.equals(e.getCode())) {
					return e;
				}
			}

			return jj;
		}
	}
}