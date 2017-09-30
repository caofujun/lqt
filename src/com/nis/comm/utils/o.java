package com.nis.comm.utils;

import java.security.MessageDigest;

public class o {
	private static o pJ;
	private static final String[] pK = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C",
			"D", "E", "F"};

	public static synchronized o getInstance() {
		if (pJ == null) {
			pJ = new o();
		}

		return pJ;
	}

	private String e(byte[] b) {
		StringBuffer resultSb = new StringBuffer();

		for (int i = 0; i < b.length; ++i) {
			resultSb.append(this.byteToHexString(b[i]));
		}

		return resultSb.toString().toUpperCase();
	}

	private String byteToHexString(byte b) {
		int n = b;
		if (b < 0) {
			n = b + 256;
		}

		int d1 = n / 16;
		int d2 = n % 16;
		return pK[d1] + pK[d2];
	}

	private byte[] md5Digest(byte[] src) throws Exception {
		MessageDigest alg = MessageDigest.getInstance("MD5");
		return alg.digest(src);
	}

	public String at(String strMing) {
		return this.m(strMing, (String) null);
	}

	public String m(String strMing, String expStr) {
		String resultString = null;
		if (expStr == null) {
			resultString = new String(strMing);
		} else {
			resultString = new String(strMing + expStr);
		}

		try {
			resultString = this.e(this.md5Digest(resultString.getBytes()));
		} catch (Exception arg4) {
			;
		}

		return resultString;
	}

	public static void main(String[] args) {
		String str1 = "admin";
		String str2 = getInstance().m(str1, "admin");
		System.out.println("明文:" + str1);
		System.out.println("密文:" + str2);
	}
}