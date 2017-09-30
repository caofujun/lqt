package com.nis.comm.service.impl;

import com.nis.comm.entity.Result;
import com.nis.comm.service.SyncService;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.i;
import com.nis.comm.utils.l;
import com.nis.comm.utils.p;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class SyncServiceImpl implements SyncService {
	private static final Logger logger = Logger.getLogger(SyncServiceImpl.class);
	private static final String pa = "[同步服务]";

	public Result<String> b(Map<String, Object> params) {
		Result result = new Result();
		DefaultHttpClient httpclient = i.getHttpClient();
		HashMap syncData = new HashMap();
		syncData.put("cid", params.get("cid"));
		syncData.put("token", params.get("token"));
		syncData.put("card_type", params.get("card_type"));
		syncData.put("card", params.get("card"));
		syncData.put("truename", params.get("truename"));
		syncData.put("sex", params.get("sex"));
		syncData.put("birth", params.get("birth"));
		syncData.put("phone", params.get("phone"));
		syncData.put("password", params.get("password"));
		if (ab.isNotEmpty("" + params.get("unit_name"))) {
			params.put("unit_name", params.get("unit_name"));
		}

		if (ab.isNotEmpty("" + params.get("email"))) {
			params.put("email", params.get("email"));
		}

		try {
			logger.info("[同步服务]同步患者开始：【参数】" + l.toString(params));
			String e = i.a(httpclient, params, "" + params.get("url"), "utf-8");
			logger.info("[同步服务]同步患者：【91160返回结果】" + e);
			if (ab.isEmpty(e)) {
				logger.warn("[同步服务]同步患者失败：未返回任何结果!");
				result.setResult("error");
				result.setMsg("同步患者失败：未返回任何结果!");
			} else {
				Map backResul = (Map) l.ar(e);
				int state = p.c(backResul, "state").intValue();
				if (state > 0) {
					result.setResult("success");
					result.setMsg(String.valueOf(state));
				} else {
					logger.warn("[同步服务]同步患者失败：911160提示同步失败!");
					result.setResult("error");
					result.setMsg("同步患者失败：911160提示同步失败!");
				}
			}
		} catch (IOException arg7) {
			logger.error("[同步服务]同步患者失败：同步出现异常!异常信息：" + arg7.getMessage());
			result.setResult("error");
			result.setMsg("同步患者失败：同步出现异常!");
		}

		logger.info("[同步服务]同步患者结束：");
		return result;
	}
}