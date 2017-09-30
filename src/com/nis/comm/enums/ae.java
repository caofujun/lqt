package com.nis.comm.enums;

import com.nis.comm.entity.KvEntity;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

public enum ae {
	ik("1", "抗菌药物分析", "analysis/kjywAnalysis.shtml"), il("2", "病程信息分析", "f_task/ysgrys.shtml"), im("3", "影像信息分析",
			"f_task/ysgrysPacs.shtml"), in("4", "标本信息分析", "analysis/bacteria.shtml"), io("5", "常规检验分析",
					"analysis/commCheck.shtml"), iq("6", "体温信息分析", "f_task/ysgrysTw.shtml"), ir("7", "多耐信息分析",
							"analysis/resisAnalysis.shtml"), is("8", "在院三管分析",
									"f_task/jcmx.shtml"), it("9", "预警模型分析", "f_task/ysgrMx.shtml");

	private String code;
	private String name;
	private String url;
	private static List<KvEntity> list = new ArrayList();

	private ae(String code, String name, String url) {
		this.code = code;
		this.name = name;
		this.url = url;
	}

	public static List<KvEntity> getList() {
		if (list.size() > 0) {
			return list;
		} else {
			EnumSet set = EnumSet.allOf(ae.class);
			Iterator arg1 = set.iterator();

			while (arg1.hasNext()) {
				ae p = (ae) arg1.next();
				list.add(new KvEntity(p.getCode(), p.getName()));
			}

			return list;
		}
	}

	public static ae D(String value) {
		if (value == null) {
			return null;
		} else {
			EnumSet set = EnumSet.allOf(ae.class);
			Iterator arg2 = set.iterator();

			while (arg2.hasNext()) {
				ae e = (ae) arg2.next();
				if (value.equals(e.getCode())) {
					return e;
				}
			}

			return null;
		}
	}

	public String getCode() {
		return this.code;
	}

	public String getName() {
		return this.name;
	}

	public String getUrl() {
		return this.url;
	}
}