package com.nis.comm.utils;

import com.nis.comm.utils.ac.1;
import com.nis.comm.utils.ac.2;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.log4j.Logger;

public class ac implements Serializable {
	private static final Logger c = Logger.getLogger(ac.class);
	private static final String qa = "UTF-8";
	private static final int qb = 120000;
	private String url;
	private Map<String, Object> oP = new HashMap();

	public ac(String url) {
		this.url = url;
	}

	public void r(String key, String value) {
		this.oP.put(key, value);
	}

	public String D() {
		String result = null;
		InputStream input = null;
		ArrayList paramspairs = new ArrayList();
		if (this.oP != null && !this.oP.isEmpty()) {
			Iterator method = this.oP.entrySet().iterator();

			while (method.hasNext()) {
				Entry e = (Entry) method.next();
				paramspairs.add(new BasicNameValuePair((String) e.getKey(), String.valueOf(e.getValue())));
			}
		}

		try {
			UrlEncodedFormEntity e1 = new UrlEncodedFormEntity(paramspairs, "UTF-8");
			HttpPost method1 = new HttpPost(this.url);
			method1.setEntity(e1);
			DefaultHttpClient httpclient = this.getHttpClient();
			CloseableHttpResponse response = httpclient.execute(method1);
			if (response.getEntity() == null) {
				return "没有返回内容";
			}

			input = response.getEntity().getContent();
			InputStreamReader reader = new InputStreamReader(input, "UTF-8");
			BufferedReader bufReader = new BufferedReader(reader);
			StringBuffer buffer = new StringBuffer();

			String temp;
			while ((temp = bufReader.readLine()) != null) {
				buffer.append(temp);
			}

			result = buffer.toString().trim();
			if (input != null) {
				input.close();
			}
		} catch (Exception arg11) {
			c.error("HTTP请求返回数据异常: " + arg11.getMessage());
		}

		return result;
	}

	private DefaultHttpClient getHttpClient() {
      boolean time = true;
      boolean thousand = true;
      BasicHttpParams params = new BasicHttpParams();
      HttpProtocolParams.setUserAgent(params, "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0)");
      HttpClientParams.setCookiePolicy(params, "compatibility");
      HttpConnectionParams.setConnectionTimeout(params, 120000);
      HttpConnectionParams.setSoTimeout(params, 120000);
      DefaultHttpClient httpclient = new DefaultHttpClient(params);
      httpclient.addRequestInterceptor(new 1(this));
      httpclient.addResponseInterceptor(new 2(this));
      return httpclient;
   }
}