package com.nis.comm.utils;

import com.nis.comm.utils.idcard.a;
import java.io.UnsupportedEncodingException;
import java.lang.Character.UnicodeBlock;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

public class ab {
	private static String pY = "http://127.0.0.1:8080";
	private static Random random = new Random();
	private static char[] pZ = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

	public static void setNisHttpUrl(String NIS_HTTP_URL) {
		pY = NIS_HTTP_URL;
	}

	public static String getNisHttpUrl() {
		return pY;
	}

	public static String w(List<?> strs) {
		strs.remove((Object) null);
		String str = ArrayUtils.toString(strs);
		return str.substring(1, str.length() - 1);
	}

	public static String f(List<?> list, String separator) {
		return StringUtils.join(list.toArray(), separator);
	}

	public static String a(Object[] strs) {
		ArrayUtils.removeElement(strs, (Object) null);
		String str = ArrayUtils.toString(strs);
		return str.substring(1, str.length() - 1);
	}

	public static boolean aM(String str) {
		return str == null || str.trim().length() == 0;
	}

	public static boolean d(String[] strArr) {
		return strArr == null || strArr.length == 0;
	}

	public static boolean d(Map<?, ?> map) {
		return map == null || map.isEmpty();
	}

	public static boolean x(List<?> list) {
		return list == null || list.isEmpty();
	}

	public static String aN(String string) {
		if (aM(string)) {
			return string;
		} else {
			String temp = string.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&#60;", "<")
					.replaceAll("&#62;", ">");
			Pattern pattern = Pattern.compile("<[^>]*>");
			Matcher matcher = pattern.matcher(temp);
			return matcher.replaceAll("");
		}
	}

	public static String aO(String string) {
		if (aM(string)) {
			return string;
		} else {
			String[] sqltags = new String[]{"and", "exec", "insert", "select", "delete", "update", "count", "%", "*",
					"chr", "mid", "master", "truncate", "char", "declare"};
			String temp = string.toLowerCase();

			for (int i = 0; i < sqltags.length; ++i) {
				if (temp.indexOf(sqltags[i]) != -1) {
					temp = temp.replaceAll(sqltags[i], "");
				}
			}

			return temp;
		}
	}

	public static String f(String str, String oldCharasetCode, String newCharasetCode) {
		String tempStr;
		try {
			tempStr = new String(str.getBytes(oldCharasetCode), newCharasetCode);
		} catch (UnsupportedEncodingException arg4) {
			tempStr = str;
		}

		return tempStr;
	}

	public static String aP(String str) {
		return str == null ? "" : str;
	}

	public static int a(String s, char c) {
		int no = 0;
		char[] cs = s.toCharArray();

		for (int i = 0; i < cs.length; ++i) {
			if (c == cs[i]) {
				++no;
			}
		}

		return no;
	}

	public static boolean isEmpty(String str) {
		return "".equals(aQ(str));
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static String aQ(String a) {
		return ((String) a((Object) a, (Object) "")).trim();
	}

	public static String aR(String q) {
		for (q = ((String) a((Object) q, (Object) "")).trim(); q.startsWith("　"); q = q.substring(1, q.length())) {
			;
		}

		while (q.endsWith("　")) {
			q = q.substring(0, q.length() - 1);
		}

		return q;
	}

	public static <K> K a(K a, K b) {
		return a == null ? b : a;
	}

	public static boolean aS(String s) {
		if (isEmpty(s)) {
			return false;
		} else {
			char[] c = s.toCharArray();

			for (int i = 0; i < c.length; ++i) {
				if (c[i] < 48 || c[i] > 57) {
					return false;
				}
			}

			return true;
		}
	}

	public static String[] e(String[] obj) {
		String[] r = obj;
		int num = 0;

		int j;
		for (j = 0; j < r.length; ++j) {
			if (r[j] == null || r[j].trim().length() < 1) {
				++num;
			}
		}

		if (num > 0) {
			r = new String[r.length - num];
			j = 0;

			for (int i = 0; i < obj.length; ++i) {
				if (obj[i] != null && obj[i].trim().length() > 0) {
					r[j++] = obj[i].trim();
				}
			}
		}

		return r;
	}

	public static boolean contains(String source, String target) {
		if (source != null && source.length() > 0) {
			String[] all = source.split(",");
			String[] arg5 = all;
			int arg4 = all.length;

			for (int arg3 = 0; arg3 < arg4; ++arg3) {
				String str = arg5[arg3];
				if (str.trim().equals(target)) {
					return true;
				}
			}
		}

		return false;
	}

	public static boolean p(String source, String target) {
		if (source != null && source.length() > 0) {
			String[] all = source.split("@");
			String[] arg5 = all;
			int arg4 = all.length;

			for (int arg3 = 0; arg3 < arg4; ++arg3) {
				String str = arg5[arg3];
				if (str.trim().equals(target)) {
					return true;
				}
			}
		}

		return false;
	}

	public static String a(int length, String value) {
		if (aM(value)) {
			value = "0";
		}

		Integer newValue = Integer.valueOf(Integer.parseInt(value) + 1);
		String strValue = newValue.toString();
		length -= strValue.length();
		if (length > 0) {
			String first = "";

			for (int i = 0; i < length; ++i) {
				first = first + "0";
			}

			strValue = first + strValue;
		}

		return strValue;
	}

	public static String[] split(String msg, int num) {
		int len = msg.length();
		if (len <= num) {
			return new String[]{msg};
		} else {
			int count = len / (num - 1);
			count += len > (num - 1) * count ? 1 : 0;
			String[] result = new String[count];
			int pos = 0;
			int splitLen = num - 1;

			for (int i = 0; i < count; ++i) {
				if (i == count - 1) {
					splitLen = len - pos;
				}

				result[i] = msg.substring(pos, pos + splitLen);
				pos += splitLen;
			}

			return result;
		}
	}

	public static Set<String> aT(String sql) {
		HashSet params = new HashSet();
		int beginPos = sql.indexOf(":");
		boolean endPos = false;

		while (beginPos >= 0) {
			int endPos1 = sql.indexOf(" ", beginPos);
			if (endPos1 < 0) {
				endPos1 = sql.length();
			}

			String paramName = sql.substring(beginPos + 1, endPos1);
			beginPos = sql.indexOf(":", endPos1);
			params.add(paramName);
		}

		return params;
	}

	public static String aU(String original) {
		if (original == null) {
			return original;
		} else if (original.equals("")) {
			return original;
		} else {
			char[] chrs = original.toCharArray();
			chrs[0] = Character.toLowerCase(chrs[0]);
			return new String(chrs);
		}
	}

	public static String b(int length) {
		char[] r = new char[length];

		for (int i = 0; i < r.length; ++i) {
			r[i] = pZ[random.nextInt(pZ.length)];
		}

		return String.valueOf(r);
	}

	public static String q(String data, String regex) {
		return f(data.split(regex));
	}

	public static String aV(String data) {
		return f(data.split(","));
	}

	public static boolean aW(String phone) {
		if (isEmpty(phone)) {
			return false;
		} else {
			boolean result = true;
			String regExp = "^((1[0-9][0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
			Pattern p = Pattern.compile(regExp);
			Matcher m = p.matcher(phone);
			result = m.matches();
			return result;
		}
	}

	public static String f(String[] datas) {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < datas.length; ++i) {
			sb.append("\'");
			sb.append(datas[i]);
			sb.append("\'");
			if (i < datas.length - 1) {
				sb.append(",");
			}
		}

		return sb.toString();
	}

	public static String g(Object obj) {
		return obj == null ? "" : obj.toString();
	}

	public static String h(Object obj) {
		return obj == null
				? null
				: ("男".equalsIgnoreCase(obj.toString()) ? "0" : ("女".equalsIgnoreCase(obj.toString()) ? "1" : null));
	}

	public static String aX(String targer) {
		StringBuilder sb = new StringBuilder();
		String[] temps = targer.split("_");
		if (temps.length > 1) {
			for (int i = 0; i < temps.length; ++i) {
				String value = temps[i].toLowerCase();
				if (i == 0) {
					sb.append(value);
				} else {
					sb.append(Character.toUpperCase(value.charAt(0))).append(value.substring(1));
				}
			}

			return sb.toString();
		} else {
			return targer.toLowerCase();
		}
	}

	public static String g(String[] array) {
		if (d(array)) {
			return null;
		} else {
			StringBuffer sb = new StringBuffer();
			String[] arg4 = array;
			int arg3 = array.length;

			for (int arg2 = 0; arg2 < arg3; ++arg2) {
				String str = arg4[arg2];
				sb.append(str).append(",");
			}

			return sb.substring(0, sb.lastIndexOf(",")).toString();
		}
	}

	public static boolean aY(String str) {
		if (isEmpty(str)) {
			return false;
		} else {
			Pattern pattern = Pattern.compile("[0-9]*(\\.?)[0-9]*");
			return pattern.matcher(str).matches();
		}
	}

	public static String aZ(String ctxPath) {
		ctxPath = StringUtils.trim(ctxPath);
		if (StringUtils.countMatches(ctxPath, "/") > 1 && ctxPath.lastIndexOf("/") == ctxPath.length() - 1) {
			ctxPath = ctxPath.substring(0, ctxPath.lastIndexOf("/"));
		}

		if (StringUtils.countMatches(ctxPath, "\\") > 1 && ctxPath.lastIndexOf("\\") == ctxPath.length() - 1) {
			ctxPath = ctxPath.substring(0, ctxPath.lastIndexOf("\\"));
		}

		return ctxPath;
	}

	private static boolean d(char c) {
		UnicodeBlock ub = UnicodeBlock.of(c);
		return ub == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
				|| ub == UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
				|| ub == UnicodeBlock.GENERAL_PUNCTUATION;
	}

	public static boolean ba(String strName) {
		char[] ch = strName.toCharArray();

		for (int i = 0; i < ch.length; ++i) {
			char c = ch[i];
			if (d(c)) {
				return true;
			}
		}

		return false;
	}

	public static boolean bb(String str) {
      if(str == null) {
         return false;
      } else {
         Pattern pattern = Pattern.compile("[\\一-\\龿]+");
         return pattern.matcher(str.trim()).find();
      }
   }

	public static String a(String fileName, HttpServletRequest request) throws UnsupportedEncodingException {
		if (isEmpty(fileName)) {
			return null;
		} else {
			String userAgent = request.getHeader("USER-AGENT");
			if (userAgent.indexOf("MSIE") > -1) {
				fileName = URLEncoder.encode(fileName, "UTF-8");
			} else if (userAgent.indexOf("Chrome") > -1) {
				fileName = new String(fileName.getBytes(), "ISO8859-1");
			} else if (userAgent.indexOf("Firefox") > -1) {
				fileName = new String(fileName.getBytes(), "ISO8859-1");
			} else {
				fileName = URLEncoder.encode(fileName, "UTF-8");
			}

			return fileName;
		}
	}

	public static String[] h(String[] a) {
		LinkedList list = new LinkedList();

		for (int i = 0; i < a.length; ++i) {
			if (!list.contains(a[i])) {
				list.add(a[i]);
			}
		}

		return (String[]) list.toArray(new String[list.size()]);
	}

	public static String c(String str, int index) {
		if (isNotEmpty(str) && str.length() >= index) {
			str = str.substring(0, index);
		}

		return str;
	}

	public static String bc(String cardNo) {
		a idcardInfo = new a(cardNo);
		return idcardInfo.getGender();
	}
}