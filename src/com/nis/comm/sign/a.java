package com.nis.comm.sign;

import com.nis.comm.sign.rule.c;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class a {
	private static final Logger c = Logger.getLogger(a.class);
	private c pb;

	public a(HttpServletRequest request) {
		try {
			this.pb = (c) com.nis.comm.sign.rule.a.class.newInstance();
		} catch (InstantiationException arg2) {
			c.error("获取签名验证实例失败: " + arg2.getMessage());
		} catch (IllegalAccessException arg3) {
			c.error("获取签名验证实例失败: " + arg3.getMessage());
		}

		this.pb.g(request);
	}

	public boolean u() {
		return this.pb.u();
	}
}