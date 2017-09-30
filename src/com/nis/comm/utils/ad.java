package com.nis.comm.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ad {
	private static final String qe = "&#x";
	private static final String qf = ";";

	public static String bd(String sTemp) {
		StringBuffer sb = new StringBuffer();
		if (sTemp != null && !sTemp.equals("")) {
			String s = be(sTemp);

			for (int i = 0; i < s.length(); ++i) {
				char cChar = s.charAt(i);
				if (e(cChar)) {
					sb.append("&#x");
					sb.append(Integer.toHexString(cChar));
					sb.append(";");
				} else {
					switch (cChar) {
						case ' ' :
							sb.append("&#32;");
							break;
						case '\"' :
							sb.append("&quot;");
							break;
						case '&' :
							sb.append("&amp;");
							break;
						case '<' :
							sb.append("&lt;");
							break;
						case '>' :
							sb.append("&gt;");
							break;
						default :
							sb.append(cChar);
					}
				}
			}

			return sb.toString();
		} else {
			return "";
		}
	}

	public static String be(String str) {
		try {
			String ex = getEncoding(str);
			String temp = new String(str.getBytes(ex), "GB2312");
			return temp;
		} catch (IOException arg2) {
			return null;
		}
	}

	public static String bf(String str) {
		try {
			String ex = getEncoding(str);
			String temp = new String(str.getBytes(ex), "UTF-8");
			return temp;
		} catch (IOException arg2) {
			return null;
		}
	}

	public static boolean e(char c) {
		Character ch = new Character(c);
		String sCh = ch.toString();

		try {
			byte[] ex = sCh.getBytes("gb2312");
			return ex.length > 1;
		} catch (UnsupportedEncodingException arg3) {
			return false;
		}
	}

	public static String getEncoding(String str) {
		String encode = "GB2312";

		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				return encode;
			}
		} catch (Exception arg5) {
			;
		}

		encode = "ISO-8859-1";

		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				return encode;
			}
		} catch (Exception arg4) {
			;
		}

		encode = "UTF-8";

		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				return encode;
			}
		} catch (Exception arg3) {
			;
		}

		encode = "GBK";

		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				return encode;
			}
		} catch (Exception arg2) {
			;
		}

		return "";
	}
}