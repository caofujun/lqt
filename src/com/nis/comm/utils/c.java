package com.nis.comm.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class c {
	private static final Pattern pu = Pattern.compile("^([bpmfdtnlgkhjqxrzcsyw]{0,2})([aeiouv][a-z]*)$");
	private static HanyuPinyinOutputFormat pv = new HanyuPinyinOutputFormat();
	private static Properties pw;
	private static Properties px;

	static {
		pv.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		pv.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		pv.setVCharType(HanyuPinyinVCharType.WITH_V);
		pw = new Properties();

		try {
			pw.load(new BufferedInputStream(c.class.getResourceAsStream("/bm_wb86.properties")));
		} catch (IOException arg1) {
			arg1.printStackTrace();
		}

		px = new Properties();

		try {
			px.load(new BufferedInputStream(c.class.getResourceAsStream("/bm_sp.properties")));
		} catch (IOException arg0) {
			arg0.printStackTrace();
		}

	}

	public static String[] a(char c) {
		if ((c < 19968 || c > '龥') && c != 12295) {
			return null;
		} else {
			try {
				return PinyinHelper.toHanyuPinyinStringArray(c, pv);
			} catch (BadHanyuPinyinOutputFormatCombination arg1) {
				return null;
			}
		}
	}

	public static String[] b(char c) {
		String[] array = a(c);
		if (array == null) {
			return array;
		} else {
			String[] result = new String[array.length];

			for (int i = 0; i < array.length; ++i) {
				String s = array[i];
				Matcher m = pu.matcher(s);
				if (m.matches()) {
					String sm = m.group(1);
					String smdz = px.getProperty(sm);
					String ym = m.group(2);
					String ymdz = px.getProperty(ym);
					String r = "";
					if (smdz != null) {
						r = smdz;
					}

					if (ymdz != null) {
						r = r + ymdz;
					}

					result[i] = r;
				} else {
					System.err.println("分解" + c + "拼音的拼音时发生错误!无法拆分出声母和韵母.");
				}
			}

			return result;
		}
	}

	public static String S(String s) {
		if (s != null && s.length() != 0) {
			StringBuilder sb = new StringBuilder();
			char[] arg4;
			int arg3 = (arg4 = s.toCharArray()).length;

			for (int arg2 = 0; arg2 < arg3; ++arg2) {
				char c = arg4[arg2];
				String[] r = a(c);
				if (r == null) {
					sb.append(c);
				} else {
					String py = r[0];
					if (py.length() > 0) {
						sb.append(py.charAt(0));
					}
				}
			}

			return sb.toString();
		} else {
			return null;
		}
	}

	public static String T(String s) {
		if (s != null && s.length() != 0) {
			StringBuilder sb = new StringBuilder();
			char[] arg4;
			int arg3 = (arg4 = s.toCharArray()).length;

			for (int arg2 = 0; arg2 < arg3; ++arg2) {
				char c = arg4[arg2];
				String[] r = a(c);
				if (r == null) {
					sb.append(c);
				} else {
					String py = r[0];
					if (py.length() > 0) {
						char f = Character.toUpperCase(py.charAt(0));
						py = f + py.substring(1);
					}

					sb.append(py);
				}
			}

			return sb.toString();
		} else {
			return null;
		}
	}

	public static String U(String s) {
		if (s != null && s.length() != 0) {
			StringBuilder sb = new StringBuilder();
			char[] arg4;
			int arg3 = (arg4 = s.toCharArray()).length;

			for (int arg2 = 0; arg2 < arg3; ++arg2) {
				char c = arg4[arg2];
				String[] r = b(c);
				if (r == null) {
					sb.append(c);
				} else {
					String py = r[0];
					if (py.length() > 0) {
						char f = Character.toUpperCase(py.charAt(0));
						py = f + py.substring(1);
					}

					sb.append(py);
				}
			}

			return sb.toString();
		} else {
			return null;
		}
	}

	public static String[] c(char c) {
		if (c >= 19968 && c <= '龥') {
			String result = pw.getProperty(Integer.toHexString(c).toUpperCase());
			return result == null ? null : (result.contains(",") ? result.split(",") : new String[]{result});
		} else {
			return null;
		}
	}

	public static void main(String[] args) {
		System.out.println(T("中华人民共和国--中联部"));
		System.out.println(U("中华人民共和国--中联部"));
		System.out.println(S("中华人民共和国--中联部"));
		System.out.println(U("壹仟贰佰叁拾肆亿伍仟陆佰柒拾捌万玖仟零壹拾贰元叁角肆分"));
		System.out.println(Arrays.deepToString(c('啊')));
		System.out.println(Arrays.deepToString(c('A')));
		System.out.println(Arrays.deepToString(c('工')));
	}
}