package com.nis.comm.utils;

import com.nis.comm.utils.DESS;
import com.nis.comm.utils.a;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;

public class EncryptUtils {
	public static String key = "HNCSLQTLmMGStGtOpF4xNyvYt54EQ==";

	public static String ad(String data) {
		return (new DESS()).W(data);
	}

	public static String ae(String data) {
		return (new DESS()).W(data);
	}

	public static String af(String data) {
		return (new DESS()).V(data);
	}

	public static String ag(String data) {
		return (new DESS()).V(data);
	}

	@Deprecated
	public static String ah(String str) {
		return encode(str, "MD5");
	}

	public static String ai(String str) {
		return encode(str, "SHA");
	}

	public static String aj(String str) {
		return a.encode(str.getBytes());
	}

	public static String ak(String str) throws IOException {
		return new String(a.decode(str));
	}

	private static String encode(String str, String method) {
		MessageDigest md = null;
		String dstr = null;

		try {
			md = MessageDigest.getInstance(method);
			md.update(str.getBytes());
			dstr = (new BigInteger(1, md.digest())).toString(16);
		} catch (NoSuchAlgorithmException arg4) {
			arg4.printStackTrace();
		}

		return dstr;
	}

	public static String V(String xmlStr) throws Exception {
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] encBuf = null;

		try {
			encBuf = decoder.decodeBuffer(xmlStr);
		} catch (IOException arg11) {
			arg11.printStackTrace();
		}

		byte[] key = new byte[8];
		byte[] iv = new byte[8];
		a(EncryptUtils.key, key, iv);
		SecretKeySpec deskey = new SecretKeySpec(key, "DES");
		IvParameterSpec ivParam = new IvParameterSpec(iv);
		byte[] temp = null;

		try {
			temp = d(encBuf, deskey, ivParam);
		} catch (Exception arg10) {
			arg10.printStackTrace();
		}

		byte[] md5Hash = null;

		try {
			md5Hash = a(temp, 16, temp.length - 16);
		} catch (Exception arg9) {
			arg9.printStackTrace();
		}

		for (int i = 0; i < md5Hash.length; ++i) {
			if (md5Hash[i] != temp[i]) {
				throw new Exception("MD5校验错误。");
			}
		}

		return new String(temp, 16, temp.length - 16, "utf-8");
	}

	public static byte[] a(byte[] sourceBuf, SecretKeySpec deskey, IvParameterSpec ivParam) throws Exception {
		Cipher encrypt = Cipher.getInstance("TripleDES/CBC/PKCS5Padding");
		encrypt.init(1, deskey, ivParam);
		byte[] cipherByte = encrypt.doFinal(sourceBuf, 0, sourceBuf.length);
		return cipherByte;
	}

	public static byte[] b(byte[] sourceBuf, SecretKeySpec deskey, IvParameterSpec ivParam) throws Exception {
		Cipher decrypt = Cipher.getInstance("TripleDES/CBC/PKCS5Padding");
		decrypt.init(2, deskey, ivParam);
		byte[] cipherByte = decrypt.doFinal(sourceBuf, 0, sourceBuf.length);
		return cipherByte;
	}

	public static byte[] c(byte[] sourceBuf, SecretKeySpec deskey, IvParameterSpec ivParam) throws Exception {
		Cipher encrypt = Cipher.getInstance("DES/CBC/PKCS5Padding");
		encrypt.init(1, deskey, ivParam);
		byte[] cipherByte = encrypt.doFinal(sourceBuf, 0, sourceBuf.length);
		return cipherByte;
	}

	public static byte[] d(byte[] sourceBuf, SecretKeySpec deskey, IvParameterSpec ivParam) throws Exception {
		Cipher decrypt = Cipher.getInstance("DES/CBC/PKCS5Padding");
		decrypt.init(2, deskey, ivParam);
		byte[] cipherByte = decrypt.doFinal(sourceBuf, 0, sourceBuf.length);
		return cipherByte;
	}

	public static byte[] a(byte[] buf, int offset, int length) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(buf, offset, length);
		return md.digest();
	}

	public static String a(byte[] inStr) {
		StringBuffer out = new StringBuffer(inStr.length * 2);

		for (int n = 0; n < inStr.length; ++n) {
			String stmp = Integer.toHexString(inStr[n] & 255);
			if (stmp.length() == 1) {
				out.append("0" + stmp);
			} else {
				out.append(stmp);
			}
		}

		return out.toString();
	}

	public static byte[] c(byte[] md5Byte, byte[] bodyByte) {
		int length = bodyByte.length + md5Byte.length;
		byte[] resutlByte = new byte[length];

		for (int i = 0; i < length; ++i) {
			if (i < md5Byte.length) {
				resutlByte[i] = md5Byte[i];
			} else {
				resutlByte[i] = bodyByte[i - md5Byte.length];
			}
		}

		return resutlByte;
	}

	public static void a(String encryptKey, byte[] key, byte[] iv) {
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] buf = null;

		try {
			buf = decoder.decodeBuffer(encryptKey);
		} catch (IOException arg5) {
			arg5.printStackTrace();
		}

		int i;
		for (i = 0; i < key.length; ++i) {
			key[i] = buf[i];
		}

		for (i = 0; i < iv.length; ++i) {
			iv[i] = buf[i + 8];
		}

	}

	public static void main(String[] args) throws IOException {
		try {
			System.out.println(V(
					"IECsBwPAi6/o9SxMAjeIF43DcVxU2wJ2QVJkq+Ye7B9oprZ7xJ0Cqgc35FW9ZB6n27Yf77wGUn5rYe11UJzZZvcG1TBKAgVfKCZ2LFxKwKg="));
			System.out.println("2017-12-30".compareTo(
					V("tyMoS/A1OL6OVB/H3/LLRe6Rhp7LNnbQOCWPsYqhnOuKU/9hsGgp0C4YI7Rq/lVKCXcbEVSKQ4A=").split("@@")[1]));
		} catch (Exception arg1) {
			arg1.printStackTrace();
		}

		System.out.println("dess加密" + ad("JY000244"));
		System.out.println("dess解密 " + af("B72546D7862366A1"));
		String base64Str = aj("hncslqt99");
		System.out.println("Base64加密 " + base64Str);
		System.out.println("Base64解密 " + ak("SE5jc2xxdDk5bmlzbGljZW5zZQ=="));
	}
}