package com.nis.comm.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class h {
	public static String a(String[] cmd) {
		String result = "";
		String line = "";

		try {
			Process e = Runtime.getRuntime().exec(cmd);
			InputStreamReader is = new InputStreamReader(e.getInputStream());

			for (BufferedReader br = new BufferedReader(is); (line = br.readLine()) != null; result = result + line) {
				;
			}
		} catch (Exception arg5) {
			arg5.printStackTrace();
		}

		return result;
	}

	public static String a(String[] cmd, String[] another) {
		String result = "";
		String line = "";

		try {
			Runtime e = Runtime.getRuntime();
			Process proc = e.exec(cmd);
			proc.waitFor();
			proc = e.exec(another);
			InputStreamReader is = new InputStreamReader(proc.getInputStream());

			for (BufferedReader br = new BufferedReader(is); (line = br.readLine()) != null; result = result + line) {
				;
			}
		} catch (Exception arg7) {
			arg7.printStackTrace();
		}

		return result;
	}

	public static String e(String ip, String sourceString, String macSeparator) {
		String result = "";
		String regExp = "((([0-9,A-F,a-f]{1,2}" + macSeparator + "){1,5})[0-9,A-F,a-f]{1,2})";
		Pattern pattern = Pattern.compile(regExp);
		Matcher matcher = pattern.matcher(sourceString);

		while (matcher.find()) {
			result = matcher.group(1);
			if (sourceString.indexOf(ip) <= sourceString.lastIndexOf(matcher.group(1))) {
				break;
			}
		}

		return result;
	}

	public static String am(String ip) {
		String result = "";
		String[] cmd = new String[]{"cmd", "/c", "ping " + ip};
		String[] another = new String[]{"cmd", "/c", "arp -a"};
		String cmdResult = a(cmd, another);
		result = e(ip, cmdResult, "-");
		return result;
	}

	public static String an(String ip) {
		String result = "";
		String[] cmd = new String[]{"/bin/sh", "-c", "ping " + ip + " -c 2 && arp -a"};
		String cmdResult = a(cmd);
		result = e(ip, cmdResult, ":");
		return result;
	}

	public static String ao(String ip) {
		String macAddress = "";
		macAddress = am(ip).trim();
		if (macAddress == null || "".equals(macAddress)) {
			macAddress = an(ip).trim();
		}

		return macAddress;
	}

	public static void main(String[] args) {
		System.out.println(ao("192.168.1.48"));
	}
}