package com.nis.comm.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class ag {
	public static String getLocalIP() {
		try {
			return E() ? InetAddress.getLocalHost().getHostAddress() : getLinuxLocalIp();
		} catch (Exception arg0) {
			return "127.0.0.1";
		}
	}

	public static boolean E() {
		boolean isWindowsOS = false;
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().indexOf("windows") > -1) {
			isWindowsOS = true;
		}

		return isWindowsOS;
	}

	public static String getLocalHostName() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostName();
	}

	private static String getLinuxLocalIp() throws SocketException {
		String ip = "";

		try {
			Enumeration ex = NetworkInterface.getNetworkInterfaces();

			label41 : while (true) {
				NetworkInterface intf;
				String name;
				do {
					do {
						if (!ex.hasMoreElements()) {
							break label41;
						}

						intf = (NetworkInterface) ex.nextElement();
						name = intf.getName();
					} while (name.contains("docker"));
				} while (name.contains("lo"));

				Enumeration enumIpAddr = intf.getInetAddresses();

				while (enumIpAddr.hasMoreElements()) {
					InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						String ipaddress = inetAddress.getHostAddress().toString();
						if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
							ip = ipaddress;
							System.out.println(ipaddress);
						}
					}
				}
			}
		} catch (SocketException arg6) {
			System.out.println("获取ip地址异常");
			ip = "127.0.0.1";
			arg6.printStackTrace();
		}

		System.out.println("IP:" + ip);
		return ip;
	}

	public static String m(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}

	public static void a(String pathUrl, String name, String pwd, String phone, String content) {
		try {
			URL ex = new URL(pathUrl);
			HttpURLConnection httpConn = (HttpURLConnection) ex.openConnection();
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setUseCaches(false);
			httpConn.setRequestMethod("POST");
			String requestString = "客服端要以以流方式发送到服务端的数据...";
			byte[] requestStringBytes = requestString.getBytes("utf-8");
			httpConn.setRequestProperty("Content-length", "" + requestStringBytes.length);
			httpConn.setRequestProperty("Content-Type", "   application/x-www-form-urlencoded");
			httpConn.setRequestProperty("Connection", "Keep-Alive");
			httpConn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httpConn.setRequestProperty("Accept-Encoding", "gzip, deflate");
			httpConn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
			httpConn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0");
			httpConn.setRequestProperty("Upgrade-Insecure-Requests", "1");
			httpConn.setRequestProperty("account", name);
			httpConn.setRequestProperty("passwd", pwd);
			httpConn.setRequestProperty("phone", phone);
			httpConn.setRequestProperty("content", content);
			OutputStream outputStream = httpConn.getOutputStream();
			outputStream.write(requestStringBytes);
			outputStream.close();
			int responseCode = httpConn.getResponseCode();
			if (200 == responseCode) {
				StringBuffer sb = new StringBuffer();
				BufferedReader responseReader = new BufferedReader(
						new InputStreamReader(httpConn.getInputStream(), "utf-8"));

				String readLine;
				while ((readLine = responseReader.readLine()) != null) {
					sb.append(readLine).append("\n");
				}

				responseReader.close();
			}
		} catch (Exception arg13) {
			arg13.printStackTrace();
		}

	}

	public static void b(String url, String name, String pwd, String phone, String content) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		ArrayList formparams = new ArrayList();
		formparams.add(new BasicNameValuePair("account", name));
		formparams.add(new BasicNameValuePair("passwd", pwd));
		formparams.add(new BasicNameValuePair("phone", phone));
		formparams.add(new BasicNameValuePair("content", content));

		try {
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			System.out.println("executing request " + httppost.getURI());
			CloseableHttpResponse e = httpclient.execute(httppost);

			try {
				HttpEntity entity = e.getEntity();
				if (entity != null) {
					System.out.println("--------------------------------------");
					System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
					System.out.println("--------------------------------------");
				}
			} finally {
				e.close();
			}
		} catch (Exception arg25) {
			arg25.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException arg23) {
				arg23.printStackTrace();
			}

		}

	}
}