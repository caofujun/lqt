package com.nis.comm.utils;

import com.nis.comm.utils.ab;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

public class r {
	public static boolean a(BigDecimal val1, BigDecimal val2) {
		return val1.compareTo(val2) == 0;
	}

	public static int b(BigDecimal val1, BigDecimal val2) {
		return val1.compareTo(val2) < 0 ? -1 : (val1.compareTo(val2) == 0 ? 0 : 1);
	}

	public static String b(Double number) {
		DecimalFormat df = new DecimalFormat("0.000");
		return df.format(number);
	}

	public static String c(Double number) {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(number);
	}

	public static String d(Double number) {
		DecimalFormat df = new DecimalFormat("0");
		return df.format(number);
	}

	public static String au(String str) throws ParseException {
		DecimalFormat df = new DecimalFormat("0.000");
		return df.format(df.parse(str).doubleValue());
	}

	public static String a(Number number, String format) throws ParseException {
		DecimalFormat df = new DecimalFormat(format);
		return df.format(number);
	}

	public static Number n(String number, String format) throws ParseException {
		DecimalFormat df = new DecimalFormat(format);
		return df.parse(number);
	}

	public static String e(Double number) {
		DecimalFormat df = new DecimalFormat("0.0");
		return df.format(number);
	}

	public static Long av(String str) {
		return ab.aS(str) ? Long.valueOf(Long.parseLong(str, 10)) : null;
	}

	public static Long c(Object obj) {
		return ab.aS(ab.g(obj)) ? Long.valueOf(Long.parseLong(ab.g(obj), 10)) : Long.valueOf(0L);
	}

	public static int d(Object obj) {
		return ab.aS(ab.g(obj)) ? Integer.parseInt(ab.g(obj)) : 0;
	}

	public static Double e(Object obj) {
		return ab.aY(ab.g(obj)) ? Double.valueOf(Double.parseDouble(ab.g(obj))) : Double.valueOf(0.0D);
	}

	public static Long f(Object obj) {
		return ab.aS(ab.g(obj)) ? Long.valueOf(Long.parseLong(ab.g(obj), 10)) : null;
	}

	public static boolean isNumber(String number) {
		try {
			new Double(number);
			return true;
		} catch (Exception arg1) {
			return false;
		}
	}
}