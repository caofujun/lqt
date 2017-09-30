package com.nis.msg.service.impl;

import com.nis.comm.utils.i;
import java.util.HashMap;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class SendWebCat {
	private static final Logger c = Logger.getLogger(SendWebCat.class);

	public String e(String url, String appid, String userid, String title, String desc) {
		DefaultHttpClient httpclient = i.getHttpClient();
		HashMap params = new HashMap();
		String res = "";

		try {
			params.put("appid", appid);
			params.put("userid", userid);
			params.put("title", title);
			params.put("desc", desc);
			params.put("url", "");
			res = i.a(httpclient, params, url, "utf-8");
		} catch (Exception arg9) {
			c.error("-------------调用微信接口失败", arg9);
			arg9.printStackTrace();
		}

		return res;
	}
}