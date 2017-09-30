package com.nis.comm.remote;

import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.utils.AppContextUtil;
import com.nis.comm.utils.i;
import com.nis.comm.utils.l;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import com.nis.param.service.SysParamService;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

public class a {
	private static final Logger logger = Logger.getLogger(a.class);
	private String url;
	private String oO;
	private String token;
	private SysParamService j;
	private Map<String, Object> oP = new LinkedHashMap();

	public a(String url, String cid, String token) {
		this.url = url;
		this.oO = cid;
		this.token = token;
	}

	public a(String url, String unitId) {
		String prefixUrl = this.getSysParamService().findByParamCode(Param.NIS_HTTP_URL);
		this.url = prefixUrl + url;
		SysDictService sysDictService = (SysDictService) AppContextUtil.getInstance().getBean(SysDictService.class);
		SysDict dict = sysDictService.j("req_way", unitId.toString(), (String) null);
		this.oO = unitId.toString();
		this.token = dict.getExtParam1();
	}

	private SysParamService getSysParamService() {
		if (this.j == null) {
			this.j = (SysParamService) AppContextUtil.getInstance().getBean(SysParamService.class);
		}

		return this.j;
	}

	public void a(String key, Object value) {
		this.oP.put(key, value);
	}

	public void clear() {
		this.oP.clear();
	}

	private String getSign() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(this.oP.get("cid"));
		buffer.append(this.oP.get("time"));
		buffer.append(this.token);
		return DigestUtils.md5Hex(buffer.toString());
	}

	public Result<String> getResult() {
		this.oP.put("cid", this.oO);
		this.oP.put("time", Long.valueOf(System.currentTimeMillis()));
		this.oP.put("sign", this.getSign());
		Result result = new Result();
		DefaultHttpClient httpclient = i.getHttpClient();

		try {
			logger.info("执行接口请求操作地址=" + this.url + "\t请求参数" + l.toString(this.oP));
			String e = i.a(httpclient, this.oP, this.url, "utf-8");
			result.setData(e);
			result.setResult("success");
		} catch (IOException arg3) {
			result.setResult("error");
			result.setMsg(arg3.getMessage());
		}

		return result;
	}
}