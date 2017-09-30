package com.nis.comm.constants;

import java.util.UUID;

public class a {
	public static final String UTF_8 = "utf-8";
	public static final String ep = "post";
	public static final String eq = "get";
	public static final String er = "success";
	public static final String ERROR = "error";
	public static String es = null;
	public static String et = "yyyy-MM-dd HH:mm";
	public static final Integer eu = Integer.valueOf(2048);
	public static final Integer ev = Integer.valueOf(5120);
	public static final String ew = "user";
	public static final String ex = "main";
	public static final String ey = "taskJobLogClean";
	private static String ez = null;

	public static String q() {
		if (ez == null) {
			ez = UUID.randomUUID().toString();
		}

		return ez;
	}
}