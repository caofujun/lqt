package com.nis.comm.sign.rule;

import com.nis.comm.sign.model.a;
import com.nis.comm.utils.AppContextUtil;
import com.nis.dict.entity.SysDict;
import com.nis.dict.service.SysDictService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;

public abstract class c {
	private String url;
	private HttpServletRequest pf;
	private String pg;
	private List<String> ph = new ArrayList();

	public void g(HttpServletRequest request) {
		this.pf = request;
		this.url = request.getRequestURI();
		this.pg = request.getParameter("sign");
		String cid = request.getParameter("cid");
		String token = null;
		SysDictService dictService = (SysDictService) AppContextUtil.getInstance().getBean(SysDictService.class);
		SysDict dict = dictService.j("req_way", cid, (String) null);
		if (dict != null) {
			token = dict.getExtParam1();
		}

		this.ph.add(cid);
		this.ph.add(request.getParameter("time"));
		this.ph.add(token);
	}

	protected String getUrl() {
		return this.url;
	}

	protected String getValue(String key) {
		return this.pf.getParameter(key);
	}

	protected void R(String param) {
		this.ph.add(param);
	}

	protected boolean v(List<a> suffixs) {
		Iterator param = suffixs.iterator();

		while (true) {
			a buffer;
			do {
				if (!param.hasNext()) {
					StringBuffer buffer1 = new StringBuffer();
					Iterator param3 = this.ph.iterator();

					while (param3.hasNext()) {
						String param2 = (String) param3.next();
						buffer1.append(param2);
					}

					if (!DigestUtils.md5Hex(buffer1.toString()).equals(this.pg)) {
						return false;
					}

					return true;
				}

				buffer = (a) param.next();
			} while (!this.getUrl().endsWith(buffer.getSuffix()));

			Iterator arg4 = buffer.getSignParams().iterator();

			while (arg4.hasNext()) {
				String param1 = (String) arg4.next();
				this.R(this.getValue(param1));
			}
		}
	}

	public abstract boolean u();
}