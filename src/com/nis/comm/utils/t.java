package com.nis.comm.utils;

import com.nis.comm.utils.ab;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

public class t {
	private static HanyuPinyinOutputFormat pL = new HanyuPinyinOutputFormat();
	private static final String pM = "qwertyuioplkjhgfdsazxcvbnm1234567890_";

	static {
		pL.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		pL.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		pL.setVCharType(HanyuPinyinVCharType.WITH_V);
	}

	public static String aB(String str) {
		if (ab.isEmpty(str)) {
			return "";
		} else {
			StringBuilder strBuilder = new StringBuilder(str.length() * 5);

			try {
				for (int e = 0; e < str.length(); ++e) {
					char c = str.charAt(e);
					String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(c, pL);
					if (pinyins != null && pinyins.length > 0) {
						strBuilder.append(pinyins[0]);
					} else {
						strBuilder.append(c);
					}
				}
			} catch (Exception arg4) {
				arg4.printStackTrace();
			}

			return strBuilder.toString();
		}
	}

	public static String aC(String str) {
		if (ab.isEmpty(str)) {
			return "";
		} else {
			StringBuilder strBuilder = new StringBuilder(str.length() * 5);

			try {
				for (int e = 0; e < str.length(); ++e) {
					char c = str.charAt(e);
					String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(c, pL);
					if (pinyins != null && pinyins.length > 0) {
						strBuilder.append(pinyins[0].toUpperCase());
					} else {
						strBuilder.append(c);
					}

					strBuilder.append(" ");
				}
			} catch (Exception arg4) {
				arg4.printStackTrace();
			}

			return strBuilder.toString();
		}
	}

	public static String aD(String str) {
		if (ab.isEmpty(str)) {
			return "";
		} else {
			StringBuilder strBuilder = new StringBuilder(str.length() * 5);

			try {
				for (int e = 0; e < str.length(); ++e) {
					char c = str.charAt(e);
					String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(c, pL);
					if (pinyins != null && pinyins.length > 0) {
						if (e == 0) {
							strBuilder.append(pinyins[0]);
						} else {
							strBuilder.append(pinyins[0].charAt(0));
						}
					} else {
						strBuilder.append(c);
					}
				}
			} catch (Exception arg4) {
				arg4.printStackTrace();
			}

			return strBuilder.toString();
		}
	}

	public static String aE(String s) {
		if (ab.isEmpty(s)) {
			return "";
		} else {
			StringBuilder strBuilder = new StringBuilder(s.length() * 5);

			try {
				for (int e = 0; e < s.length(); ++e) {
					char c = s.charAt(e);
					String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(c, pL);
					if (pinyins != null && pinyins.length > 0) {
						strBuilder.append(pinyins[0].charAt(0));
					} else {
						strBuilder.append(c);
					}
				}
			} catch (Exception arg4) {
				arg4.printStackTrace();
			}

			return strBuilder.toString();
		}
	}

	private static String aF(String str) {
		StringBuffer strBuffer = new StringBuffer();
		String[] arg4;
		int arg3 = (arg4 = str.split("|")).length;

		for (int arg2 = 0; arg2 < arg3; ++arg2) {
			String s = arg4[arg2];
			if ("qwertyuioplkjhgfdsazxcvbnm1234567890_".contains(s)) {
				strBuffer.append(s);
			}
		}

		return strBuffer.toString();
	}

	public static String aG(String str) {
		if (ab.isEmpty(str)) {
			return null;
		} else {
			str = aD(str);
			return aF(str);
		}
	}

	public static String aH(String str) {
		if (ab.isEmpty(str)) {
			return null;
		} else {
			str = aE(str);
			return aF(str);
		}
	}

	public static void main(String[] args) {
		String s = "女人33是大师傅";
		System.out.println(aB(s));
		System.out.println(aC(s));
		System.out.println(aD(s));
		System.out.println(aE(s));
		System.out.println(aG(s));
	}
}