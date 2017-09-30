package com.nis.comm.utils;

import com.nis.comm.utils.ab;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class aa {
	public static final Map<String, String> pN = new HashMap();
	public static final String pO = "{upname}";
	public static final String pP = "{dgsdoctor}";
	public static final String pQ = "{organization}";
	public static final String pR = "{exectime}";
	public static final String pS = "{dgstime}";
	public static final String pT = "{content}";
	public static final String pU = "{username}";
	public static final String pV = "{password}";
	public static final String pW = "{notifier}";
	public static final String pX = "{patientId}";

	static {
		pN.put("{upname}", "{患者姓名}");
		pN.put("{dgsdoctor}", "{就诊医生}");
		pN.put("{organization}", "{医院科室}");
		pN.put("{dgstime}", "{复诊时间}");
		pN.put("{exectime}", "{任务执行时间}");
		pN.put("{content}", "{任务内容}");
		pN.put("{username}", "{用户名}");
		pN.put("{password}", "{密码}");
		pN.put("{notifier}", "{通知人}");
		pN.put("{patientId}", "{患者编号}");
	}

	public static String getReg() {
		StringBuffer result = new StringBuffer("{");
		result.append("\'").append((String) pN.get("{username}")).append("\':\'用户登录时的用户名\',");
		result.append("\'").append((String) pN.get("{password}")).append("\':\'用户登录时的密码\'}");
		System.out.println(result.toString());
		return result.toString();
	}

	public static String getPlan() {
		StringBuffer result = new StringBuffer("{");
		result.append("\'").append((String) pN.get("{organization}")).append("\':\'患者就诊所在的科室\',");
		result.append("\'").append((String) pN.get("{dgstime}")).append("\':\'患者下次来复诊的时间\',");
		result.append("\'").append((String) pN.get("{exectime}")).append("\':\'患者执行任务的时间\',");
		result.append("\'").append((String) pN.get("{content}")).append("\':\'患者执行任务的内容\'}");
		return result.toString();
	}

	public static String getInform() {
		StringBuffer result = new StringBuffer("{");
		result.append("\'").append((String) pN.get("{upname}")).append("\':\'患者的姓名\'}");
		System.out.println(result.toString());
		return result.toString();
	}

	public static String getPatientId() {
		StringBuffer result = new StringBuffer("{");
		result.append("\'").append((String) pN.get("{patientId}")).append("\':\'患者的编号\'}");
		System.out.println(result.toString());
		return result.toString();
	}

	public static String getInforms() {
		StringBuffer result = new StringBuffer("{");
		result.append("\'").append((String) pN.get("{notifier}")).append("\':\'通知人\'}");
		System.out.println(result.toString());
		return result.toString();
	}

	private static String aL(String content) {
		Entry e;
		for (Iterator entryKeyIterator = pN.entrySet().iterator(); entryKeyIterator
				.hasNext(); content = content.replace((CharSequence) e.getValue(), (CharSequence) e.getKey())) {
			e = (Entry) entryKeyIterator.next();
		}

		return content;
	}

	public static String a(String content, Map<String, String> params) {
		content = aL(content);
		Iterator entryKeyIterator = params.entrySet().iterator();

		while (entryKeyIterator.hasNext()) {
			Entry e = (Entry) entryKeyIterator.next();
			if (ab.isNotEmpty((String) e.getValue())) {
				content = content.replace((CharSequence) e.getKey(), (CharSequence) e.getValue());
			} else {
				content = content.replace((CharSequence) e.getKey(), "");
			}
		}

		return content;
	}

	public static String b(String oldMsg, Map<String, String> replaceData) {
		Set keySet = replaceData.keySet();

		String key;
		for (Iterator ite = keySet.iterator(); ite
				.hasNext(); oldMsg = oldMsg.replaceAll("\\{" + key + "\\}", (String) replaceData.get(key))) {
			key = (String) ite.next();
		}

		return oldMsg;
	}

	public static void main(String[] args) {
		String oldMsg = "{unitName}将为您提供长期的健康管理服务，请登陆就医160查看您的健康方案； 用户名:{userName},密码:{userPwd} 网址:www.91160.com【就医160】 ";
		HashMap replaceData = new HashMap();
		replaceData.put("unitName", "qwe");
		replaceData.put("userName", "234");
		replaceData.put("userPwd", "123");
		oldMsg = b(oldMsg, replaceData);
		System.out.println(oldMsg);
	}
}