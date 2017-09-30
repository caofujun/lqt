package com.nis.comm.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class q {
	private static final String cI = "NetworkTool";

	public static String a(String path, Map<String, String> params, String encoding, boolean isPathContainsParam)
			throws Exception {
		StringBuilder paramBuilder = new StringBuilder(path);
		if (isPathContainsParam) {
			paramBuilder.append('&');
		} else {
			paramBuilder.append('?');
		}

		Iterator conn = params.entrySet().iterator();

		while (conn.hasNext()) {
			Entry url = (Entry) conn.next();
			paramBuilder.append((String) url.getKey()).append('=');
			paramBuilder.append((String) url.getValue());
			paramBuilder.append('&');
		}

		paramBuilder.deleteCharAt(paramBuilder.length() - 1);
		URL url1 = new URL(paramBuilder.toString());
		System.out.println("NetworkTool:" + paramBuilder.toString());
		HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();
		conn1.setConnectTimeout(5000);
		conn1.setRequestMethod("GET");
		if (conn1.getResponseCode() == 200) {
			String result = b(conn1.getInputStream());
			System.out.println("result:" + result);
			return result;
		} else {
			return "";
		}
	}

	public static String a(String path, Map<String, String> params, String encoding) throws Exception {
		StringBuilder entityBuilder = new StringBuilder("");
		if (params != null && !params.isEmpty()) {
			Iterator url = params.entrySet().iterator();

			while (url.hasNext()) {
				Entry entity = (Entry) url.next();
				entityBuilder.append((String) entity.getKey()).append('=');
				entityBuilder.append(URLEncoder.encode((String) entity.getValue(), encoding));
				entityBuilder.append('&');
			}

			entityBuilder.deleteCharAt(entityBuilder.length() - 1);
		}

		byte[] entity1 = entityBuilder.toString().getBytes();
		URL url1 = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length", String.valueOf(entity1.length));
		OutputStream outStream = conn.getOutputStream();
		outStream.write(entity1);
		outStream.flush();
		outStream.close();
		if (conn.getResponseCode() == 200) {
			String result = b(conn.getInputStream());
			System.out.println("result:" + result);
			return result;
		} else {
			return "";
		}
	}

	public static boolean C() {
		try {
			String e = "http://csmsms.91160.cn/index.php";
			StringBuilder entityBuilder = new StringBuilder("");
			byte[] entity = entityBuilder.toString().getBytes();
			URL url = new URL(e);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", String.valueOf(entity.length));
			OutputStream outStream = conn.getOutputStream();
			outStream.write(entity);
			outStream.flush();
			outStream.close();
			return conn.getResponseCode() == 200;
		} catch (Exception arg5) {
			return false;
		}
	}

	public static byte[] a(InputStream inStream) throws Exception {
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		boolean len = false;

		int len1;
		while ((len1 = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len1);
		}

		outSteam.close();
		inStream.close();
		return outSteam.toByteArray();
	}

	public static String b(InputStream inStream) throws Exception {
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		boolean len = false;

		int len1;
		while ((len1 = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len1);
		}

		outSteam.close();
		inStream.close();
		return new String(outSteam.toByteArray());
	}
}