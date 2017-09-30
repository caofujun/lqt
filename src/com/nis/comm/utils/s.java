package com.nis.comm.utils;

import com.nis.comm.utils.ab;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

public class s {
	public static String b(HttpServletRequest request, String paramName, String defaultValue) {
		String value = request.getParameter(paramName);
		if (value == null) {
			value = defaultValue;
		}

		return value;
	}

	public static String o(String paramStr, String name) {
		String r = "";
		if (paramStr != null && !ab.isEmpty(name) && paramStr.indexOf(name) >= 0) {
			int beginPos = paramStr.indexOf(name + "=");
			if (beginPos > 0) {
				beginPos = paramStr.indexOf("&" + name + "=");
				if (beginPos < 0) {
					return r;
				}

				++beginPos;
			}

			int endPos = paramStr.indexOf("&", beginPos + 1);
			if (endPos < 0) {
				endPos = paramStr.length();
			}

			r = paramStr.substring(beginPos + name.length() + 1, endPos);
			return r;
		} else {
			return r;
		}
	}

	public static String j(Map<String, Object> params, String enc) {
		return a((Map) params, (String[]) null, enc, (Set) null);
	}

	public static String a(Map<String, Object> params, String[] keyset, String enc) {
		return a((Map) params, keyset, enc, (Set) null);
	}

	public static String a(Map<String, Object> params, String[] keyset, String enc, Set<String> excludeKeySet) {
		if (params != null && params.size() != 0) {
			if (enc == null) {
				enc = "UTF-8";
			}

			StringBuffer sb = new StringBuffer(params.size() * 20);
			if (keyset == null) {
				keyset = (String[]) params.keySet().toArray(new String[params.size()]);
			}

			int i = 0;

			try {
				String[] arg8 = keyset;
				int arg7 = keyset.length;

				for (int arg6 = 0; arg6 < arg7; ++arg6) {
					String e = arg8[arg6];
					if (excludeKeySet == null || !excludeKeySet.contains(e)) {
						if (i != 0) {
							sb.append("&");
						}

						sb.append(e).append("=").append(URLEncoder.encode(params.get(e).toString(), enc));
						++i;
					}
				}
			} catch (Exception arg9) {
				arg9.printStackTrace();
			}

			return sb.toString();
		} else {
			return "";
		}
	}

	public static String j(HttpServletRequest request) {
		return a((HttpServletRequest) request, (String[]) null, (String) null, (Set) null);
	}

	public static String a(HttpServletRequest request, String[] keyset, String enc, Set<String> excludeKeySet) {
		if (request == null) {
			return "";
		} else {
			if (enc == null) {
				enc = "UTF-8";
			}

			if (keyset == null) {
				Enumeration sb = request.getParameterNames();
				ArrayList i = new ArrayList();

				while (sb.hasMoreElements()) {
					i.add((String) sb.nextElement());
				}

				keyset = (String[]) i.toArray(new String[i.size()]);
			}

			StringBuffer arg10 = new StringBuffer(keyset.length * 20);
			int arg11 = 0;

			try {
				String[] arg8 = keyset;
				int arg7 = keyset.length;

				for (int arg6 = 0; arg6 < arg7; ++arg6) {
					String e = arg8[arg6];
					if (excludeKeySet == null || !excludeKeySet.contains(e)) {
						if (arg11 != 0) {
							arg10.append("&");
						}

						arg10.append(e).append("=").append(URLEncoder.encode(request.getParameter(e), enc));
						++arg11;
					}
				}
			} catch (Exception arg9) {
				arg9.printStackTrace();
			}

			return arg10.toString();
		}
	}

	public static String a(Map<String, Object> params, boolean sorted, String splitChar) {
		return a(params, sorted, splitChar, false, (String) null);
	}

	public static String a(Map<String, Object> params, boolean sorted, String splitChar, boolean urlEncode,
			String enc) {
		if (params != null && params.size() != 0) {
			if (splitChar == null) {
				splitChar = "&";
			}

			StringBuffer sb = new StringBuffer(params.size() * 20);
			String[] keyset = (String[]) params.keySet().toArray(new String[params.size()]);
			Arrays.sort(keyset);
			int i = 0;

			try {
				String e = "";
				String[] arg11 = keyset;
				int arg10 = keyset.length;

				for (int arg9 = 0; arg9 < arg10; ++arg9) {
					String key = arg11[arg9];
					if (i != 0) {
						sb.append(splitChar);
					}

					e = params.get(key).toString();
					if (urlEncode) {
						e = URLEncoder.encode(e, enc);
					}

					sb.append(key).append("=").append(e);
					++i;
				}
			} catch (Exception arg12) {
				arg12.printStackTrace();
			}

			return sb.toString();
		} else {
			return "";
		}
	}

	public static <T> T b(String param, Class<T> clazz) {
		return clazz.getName().equals("java.lang.Integer")
				? (T)az(param)
				: ("java.lang.Double".equals(clazz.getName())
						? (T)ay(param)
						: ("java.sql.Timestamp".equals(clazz.getName())
								? (T)aw(param)
								: ("java.util.Date".equals(clazz.getName())
										? (T)aw(param)
										: ("java.lang.Long".equals(clazz.getName()) ? (T)aA(param) : (T)param))));
	}

	public static Timestamp aw(String ct) {
		if (ct != null && ct.length() >= 1) {
			Timestamp r = null;
			ct = ct.trim();
			if (ct.indexOf(32) < 0) {
				ct = ct + " 00:00:00.000";
			}

			int length = ct.length();
			if (length == 13) {
				ct = ct + ":00:00.000";
			} else if (length == 16) {
				ct = ct + ":00.000";
			}

			r = Timestamp.valueOf(ct);
			return r;
		} else {
			return null;
		}
	}

	public static Float ax(String id) {
		return id == null ? Float.valueOf(0.0F) : Float.valueOf(Float.parseFloat("0" + id));
	}

	public static Double ay(String id) {
		return id == null ? Double.valueOf(0.0D) : Double.valueOf(Double.parseDouble("0" + id));
	}

	public static Integer az(String id) {
		return b(id, 0);
	}

	public static Integer b(String id, int defaultValue) {
		int tmpid = defaultValue;
		if (id != null) {
			try {
				tmpid = Integer.parseInt(id);
			} catch (Exception arg3) {
				;
			}
		}

		return Integer.valueOf(tmpid);
	}

	public static int[] b(String[] id) {
		if (id != null && id.length > 0) {
			int[] iID = new int[id.length];

			for (int i = 0; i < id.length; ++i) {
				iID[i] = az(id[i]).intValue();
			}

			return iID;
		} else {
			return new int[0];
		}
	}

	public static Long aA(String id) {
		long tmpid = 0L;
		if (id != null) {
			try {
				tmpid = Long.parseLong(id);
			} catch (Exception arg3) {
				;
			}
		}

		return Long.valueOf(tmpid);
	}

	public static Long[] c(String[] id) {
		if (id != null && id.length > 0) {
			Long[] iID = new Long[id.length];

			for (int i = 0; i < id.length; ++i) {
				iID[i] = aA(id[i]);
			}

			return iID;
		} else {
			return new Long[0];
		}
	}

	public static Map<String, String> k(HttpServletRequest request) {
		HashMap map = new HashMap();
		Enumeration formNames = request.getParameterNames();
		Enumeration e = formNames;

		while (e.hasMoreElements()) {
			String thisName = ((String) e.nextElement()).toString();
			map.put(thisName, request.getParameter(thisName));
		}

		return map;
	}
}