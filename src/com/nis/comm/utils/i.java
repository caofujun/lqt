package com.nis.comm.utils;

import com.nis.comm.utils.ab;
import com.nis.comm.utils.i.1;
import com.nis.comm.utils.i.2;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.log4j.Logger;

public final class i {
	private static final Logger logger = Logger.getLogger(i.class);
	private static final int pB = 200;
	private static final int TIME = 30;
	private static final int pC = 1000;
	private static final String pD = "utf-8";

	public static DefaultHttpClient getHttpClient() {
      BasicHttpParams params = new BasicHttpParams();
      HttpProtocolParams.setUserAgent(params, "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0)");
      HttpClientParams.setCookiePolicy(params, "compatibility");
      HttpConnectionParams.setConnectionTimeout(params, 30000);
      HttpConnectionParams.setSoTimeout(params, 30000);
      DefaultHttpClient httpclient = new DefaultHttpClient(params);
      httpclient.addRequestInterceptor(new 1());
      httpclient.addResponseInterceptor(new 2());
      return httpclient;
   }

	public static String a(DefaultHttpClient httpclient, String url, String encode) throws IOException {
		InputStream input = null;
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse res = httpclient.execute(get);
		StatusLine status = res.getStatusLine();
		if (status.getStatusCode() != 200) {
			a((StatusLine) status, (InputStream) input, encode, (HttpResponse) res);
			throw new RuntimeException("50001");
		} else if (res.getEntity() == null) {
			return "";
		} else {
			input = res.getEntity().getContent();
			InputStreamReader reader = new InputStreamReader(input, encode);
			BufferedReader bufReader = new BufferedReader(reader);
			String tmp = null;

			String html;
			for (html = ""; (tmp = bufReader.readLine()) != null; html = html + tmp) {
				;
			}

			if (bufReader != null) {
				bufReader.close();
				bufReader = null;
			}

			if (reader != null) {
				reader.close();
				reader = null;
			}

			if (input != null) {
				input.close();
				input = null;
			}

			get.releaseConnection();
			get = null;
			return html;
		}
	}

	public static String a(DefaultHttpClient httpclient, Map<String, Object> params, String url) throws IOException {
		return a(httpclient, params, url, "utf-8");
	}

	public static String a(DefaultHttpClient httpclient, Map<String, Object> params, String url, String encode)
			throws IOException {
		InputStream input = null;
		ArrayList paramspairs = new ArrayList();
		Entry entity;
		Iterator method;
		if (params != null && !params.isEmpty()) {
			method = params.entrySet().iterator();

			while (method.hasNext()) {
				entity = (Entry) method.next();
				paramspairs.add(new BasicNameValuePair((String) entity.getKey(), String.valueOf(entity.getValue())));
			}
		}

		if (StringUtils.isBlank(encode)) {
			encode = "utf-8";
		}

		UrlEncodedFormEntity entity1 = new UrlEncodedFormEntity(paramspairs, encode);
		HttpPost method1 = new HttpPost(url);
		method1.setEntity(entity1);
		CloseableHttpResponse response = httpclient.execute(method1);
		StatusLine status = response.getStatusLine();
		if (status.getStatusCode() != 200) {
			a((StatusLine) status, (InputStream) input, encode, (HttpResponse) response);
			throw new RuntimeException("50001");
		} else if (response.getEntity() == null) {
			return "";
		} else {
			input = response.getEntity().getContent();
			InputStreamReader reader = new InputStreamReader(input, encode);
			BufferedReader bufReader = new BufferedReader(reader);
			String tmp = null;

			String html;
			for (html = ""; (tmp = bufReader.readLine()) != null; html = html + tmp) {
				;
			}

			if (bufReader != null) {
				bufReader.close();
				bufReader = null;
			}

			if (reader != null) {
				reader.close();
				reader = null;
			}

			if (input != null) {
				input.close();
				input = null;
			}

			paramspairs = null;
			entity = null;
			method1.releaseConnection();
			method = null;
			return html;
		}
	}

	public static String a(DefaultHttpClient httpclient, String reqStr, String url, String encode) throws IOException {
		InputStream input = null;
		if (StringUtils.isBlank(encode)) {
			encode = "utf-8";
		}

		StringEntity entity = new StringEntity(reqStr, encode);
		entity.setContentEncoding(encode);
		entity.setContentType("application/json");
		HttpPost method = new HttpPost(url);
		method.setEntity(entity);
		CloseableHttpResponse response = httpclient.execute(method);
		StatusLine status = response.getStatusLine();
		if (status.getStatusCode() != 200) {
			a((StatusLine) status, (InputStream) input, encode, (HttpResponse) response);
			throw new RuntimeException("50001");
		} else if (response.getEntity() == null) {
			return "";
		} else {
			input = response.getEntity().getContent();
			InputStreamReader reader = new InputStreamReader(input, encode);
			BufferedReader bufReader = new BufferedReader(reader);
			String tmp = null;

			String html;
			for (html = ""; (tmp = bufReader.readLine()) != null; html = html + tmp) {
				;
			}

			if (bufReader != null) {
				bufReader.close();
				bufReader = null;
			}

			if (reader != null) {
				reader.close();
				reader = null;
			}

			if (input != null) {
				input.close();
				input = null;
			}

			entity = null;
			method.releaseConnection();
			method = null;
			return html;
		}
	}

	public static String a(DefaultHttpClient httpclient, Map<String, Object> params, File file, String url,
			String encode, String fileFlag) throws ClientProtocolException, IOException {
		InputStream input = null;
		HttpPost httpPost = new HttpPost(url);
		FileBody fileBody = new FileBody(file);
		MultipartEntity reqEntity = new MultipartEntity();
		if (StringUtils.isEmpty(fileFlag)) {
			reqEntity.addPart("file", fileBody);
		} else {
			reqEntity.addPart(fileFlag, fileBody);
		}

		if (params != null && !params.isEmpty()) {
			Iterator status = params.entrySet().iterator();

			while (status.hasNext()) {
				Entry response = (Entry) status.next();
				StringBody reader = new StringBody(String.valueOf(response.getValue()));
				reqEntity.addPart((String) response.getKey(), reader);
			}
		}

		if (StringUtils.isBlank(encode)) {
			encode = "utf-8";
		}

		httpPost.setEntity(reqEntity);
		CloseableHttpResponse response1 = httpclient.execute(httpPost);
		StatusLine status1 = response1.getStatusLine();
		if (status1.getStatusCode() != 200) {
			a((StatusLine) status1, (InputStream) input, encode, (HttpResponse) response1);
			throw new RuntimeException("50001");
		} else if (response1.getEntity() == null) {
			return "";
		} else {
			input = response1.getEntity().getContent();
			InputStreamReader reader1 = new InputStreamReader(input, encode);
			BufferedReader bufReader = new BufferedReader(reader1);
			String tmp = null;

			String html;
			for (html = ""; (tmp = bufReader.readLine()) != null; html = html + tmp) {
				;
			}

			if (input != null) {
				input.close();
			}

			return html;
		}
	}

	private static void a(StatusLine status, InputStream input, String encode, HttpResponse response)
			throws IOException, UnsupportedEncodingException {
		input = response.getEntity().getContent();
		InputStreamReader reader = new InputStreamReader(input, encode);
		BufferedReader bufReader = new BufferedReader(reader);
		String tmp = null;

		String html;
		for (html = ""; (tmp = bufReader.readLine()) != null; html = html + tmp) {
			;
		}

		if (input != null) {
			input.close();
		}

		logger.error("请求响应：" + status.getStatusCode() + " " + status.getReasonPhrase() + "请求响应信息：" + html);
	}

	public static DefaultHttpClient a(String proxyHost, int proxyPort, String username, String password) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		httpClient.getCredentialsProvider().setCredentials(new AuthScope(proxyHost, proxyPort),
				new UsernamePasswordCredentials(username, password));
		HttpHost proxy = new HttpHost(proxyHost, proxyPort);
		httpClient.getParams().setParameter("http.route.default-proxy", proxy);
		return httpClient;
	}

	public static String h(HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		String webPath = url.delete(url.length() - request.getRequestURI().length(), url.length())
				.append(request.getContextPath()).append("/").toString();
		return webPath;
	}

	public static Boolean i(HttpServletRequest request) {
		String head = request.getHeader("user-agent");
		if (ab.isEmpty(head)) {
			return Boolean.valueOf(false);
		} else {
			head = head.toLowerCase();
			return head.indexOf("msie") <= 0 && head.indexOf("gecko") <= 0 && head.indexOf("firefox") <= 0
					&& head.indexOf("chrome") <= 0 ? Boolean.valueOf(false) : Boolean.valueOf(true);
		}
	}

	public static void q(HttpServletRequest request, HttpServletResponse response, String path) {
		BufferedInputStream bis = null;
		ServletOutputStream outPutStream = null;
		HttpURLConnection httpUrl = null;
		URL url = null;
		short BUFFER_SIZE = 1024;
		byte[] buf = new byte[BUFFER_SIZE];
		boolean size = false;

		try {
			url = new URL(path);
			httpUrl = (HttpURLConnection) url.openConnection();
			httpUrl.connect();
			bis = new BufferedInputStream(httpUrl.getInputStream());
			outPutStream = response.getOutputStream();

			int size1;
			while ((size1 = bis.read(buf)) != -1) {
				outPutStream.write(buf, 0, size1);
			}

			request.setCharacterEncoding("UTF-8");
			response.setContentType("image/jpeg");
			outPutStream.flush();
			bis.close();
			outPutStream.close();
			httpUrl.connect();
		} catch (MalformedURLException arg20) {
			arg20.printStackTrace();
		} catch (IOException arg21) {
			arg21.printStackTrace();
		} finally {
			try {
				bis.close();
				outPutStream.close();
				httpUrl.connect();
			} catch (IOException arg19) {
				arg19.printStackTrace();
			}

		}

	}
}