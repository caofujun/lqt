package com.nis.comm.interceptor;

import com.nis.comm.entity.Result;
import com.nis.comm.sign.a;
import com.nis.comm.utils.f;
import com.nis.comm.utils.l;
import com.nis.comm.utils.x;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserSecurityAdapterInterceptor implements HandlerInterceptor {
	private static final Logger c = Logger.getLogger(UserSecurityAdapterInterceptor.class);

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.setHeader("connection", "close");
		if (c.isInfoEnabled()) {
			c.info("调用消息发送接口!");
		}

		Long time = Long.valueOf(request.getParameter("time"));
		Date date = new Date(time.longValue());
		Date curDate = new Date();
		if (f.b(curDate, date) > 3000) {
			response.getWriter().print(l.toString(new Result("408", "Request timeout!")));
			return false;
		} else {
			a signCore = new a(request);
			if (signCore.u()) {
				this.f(request);
				return true;
			} else {
				String outStr = l.toString(new Result("403", "You do not have privileges!"));
				if (c.isInfoEnabled()) {
					c.info("\n\n\n\n\n" + outStr + "\n\n\n\n\n");
				}

				this.f(request);
				response.getWriter().print(outStr);
				return false;
			}
		}
	}

	private void f(HttpServletRequest request) {
		StringBuffer logDescBuffer = new StringBuffer("request IP: ");

		String param;
		try {
			param = x.l(request);
			logDescBuffer.append(param);
		} catch (Exception arg3) {
			c.error("获取IP地址错误");
		}

		logDescBuffer.append("\trequest URL: ").append(request.getRequestURL().toString());
		param = l.toString(request.getParameterMap());
		logDescBuffer.append("\tparams: ").append(param);
		if (c.isInfoEnabled()) {
			c.info(logDescBuffer.toString());
		}

	}
}