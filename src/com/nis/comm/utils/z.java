package com.nis.comm.utils;

import com.nis.comm.enums.bg;
import com.nis.comm.service.SequenceService;
import com.nis.comm.utils.AppContextUtil;
import com.nis.comm.utils.af;

public class z {
	public static String a(bg seqTable) {
		return af.getUUID32();
	}

	public static String aJ(String tableName) {
		return af.getUUID32();
	}

	public static String b(bg seqTable) {
		return af.getUUID32();
	}

	public static Long aK(String tableName) {
		SequenceService sequenceService = (SequenceService) AppContextUtil.getInstance().getBean(SequenceService.class);
		return sequenceService.a(tableName, Integer.valueOf(1), Long.valueOf(10000000L));
	}

	public static Long a(bg seqTable, Long value) {
		if (value.longValue() < 1000L) {
			value = Long.valueOf(value.longValue() + 9000L);
		}

		String valueString = Long.toString(value.longValue());
		valueString = valueString.substring(valueString.length() - 4, valueString.length());
		Integer prefixNum = Integer.valueOf(Integer.parseInt(valueString));
		SequenceService sequenceService = (SequenceService) AppContextUtil.getInstance().getBean(SequenceService.class);
		return sequenceService.a(seqTable.getTable(), prefixNum);
	}
}