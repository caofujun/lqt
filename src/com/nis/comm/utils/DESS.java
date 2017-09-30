package com.nis.comm.utils;

import java.security.SecureRandom;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DESS {
	private static final String py = "DES";

	public static Throwable a(Throwable throwable, Throwable throwable1) {
		try {
			throwable.getClass().getMethod("initCause", new Class[]{Throwable.class}).invoke(throwable,
					new Object[]{throwable1});
		} catch (Exception arg2) {
			;
		}

		return throwable;
	}

	private String getStr() {
		try {
			byte exception = 0;
			boolean b = false;
			boolean c = false;
			boolean d = false;
			int b1 = (-(exception + ~exception) << 2) - (exception + ~exception);
			int c1 = -(exception + ~exception) << 2;
			int d1 = ~exception;
			int exception1 = exception - (exception + (~exception + ~exception >> 1));
			exception1 += "Hello World".charAt((-(exception1 + ~exception1) << 2)
					- (exception1 + ~exception1)) >> (-(exception1 + ~exception1) << 2) - (exception1 + ~exception1);
			String strKey = "" + exception1;
			return strKey;
		} catch (Exception arg5) {
			a(arg5.fillInStackTrace(), arg5.fillInStackTrace());
			return this.toString();
		}
	}

	private String v() {
		return "";
	}

	private String w() {
		return "";
	}

	public String V(String data) {
		String decStr = "";

		try {
			decStr = new String(this.b(this.b(data.getBytes("UTF-8")), this.x().getBytes()));
		} catch (Exception arg3) {
			decStr = "csm analysis error";
		}

		return decStr;
	}

	public String W(String massage) {
		String encStr = "";

		try {
			encStr = this.a(massage.getBytes("UTF-8"), this.x().getBytes());
		} catch (Exception arg3) {
			encStr = "encrypt false";
		}

		return encStr;
	}

	private String a(byte[] src, byte[] key) throws Exception {
		Object dencryptedData = null;
		String result = null;
		SecureRandom sr = new SecureRandom();
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(1, securekey, sr);
		byte[] dencryptedData1 = cipher.doFinal(src);
		result = this.a(dencryptedData1);
		return result;
	}

	private String b(byte[] src, byte[] key) throws Exception {
		Object dencryptedData = null;
		String result = null;
		SecureRandom sr = new SecureRandom();
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(2, securekey, sr);
		byte[] dencryptedData1 = cipher.doFinal(src);
		result = new String(dencryptedData1, "UTF-8");
		return result;
	}

	private String a(byte[] b) {
		String hs = "";
		String stmp = "";

		for (int n = 0; n < b.length; ++n) {
			stmp = Integer.toHexString(b[n] & 255);
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}

		return hs.toUpperCase();
	}

	private byte[] b(byte[] b) {
		if (b.length % 2 != 0) {
			throw new IllegalArgumentException("长度不是偶数");
		} else {
			byte[] b2 = new byte[b.length / 2];

			for (int n = 0; n < b.length; n += 2) {
				String item = new String(b, n, 2);
				b2[n / 2] = (byte) Integer.parseInt(item, 16);
			}

			return b2;
		}
	}

	public static void main(String[] args) {
		DESS des = new DESS();
		System.out.println(des.x());
		System.out.println("-----------");
	}

	private String x() {
		Random rand = new Random();
		int i = rand.nextInt(10000);
		boolean t = false;

		try {
			String exception = "";
			int t1 = ("abdade".charAt(-(i + ~i) << 2) >> (-(i + ~i) << 0)) + i + ~i;
			exception = exception + (char) t1;
			t1 = ("asja2e".charAt(-(i + ~i) << 1) >> (-(i + ~i) << 0)) + i + ~i;
			exception = exception + (char) t1;
			t1 = "adj34e".charAt(-(i + ~i) << 0) >> (-(i + ~i) << 0);
			exception = exception + (char) t1;
			t1 = ("rdy3we".charAt(-(i + ~i) << 0) >> (-(i + ~i) << 0)) + (-(i + ~i) << 2);
			exception = exception + (char) t1;
			exception = exception + "sd8erw4".charAt(-(i + ~i) << 1);
			exception = exception + "0csm";
			return exception;
		} catch (Exception arg4) {
			return this.toString();
		}
	}

	public String getKey() {
		String strKey = "nykj201099";
		return strKey;
	}
}