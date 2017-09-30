package com.nis.comm.controller;


import com.nis.comm.entity.LoginUser;
import com.nis.comm.enums.ar;
import com.nis.comm.utils.l;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

public class BaseController {
	private static final Logger logger = Logger.getLogger(BaseController.class);
	public static final String er = "success";
	public static final String fj = "net_error";
	public static final String ERROR = "error";
	public static final String fk = "result";
	public static final String fl = "msg";
	public static final String fm = "data";

	@ExceptionHandler
	public String a(HttpServletRequest request, Exception e) {
		logger.error("异常：", e);
		return "forward:/error.jsp";
	}

	@InitBinder
	protected void a(WebDataBinder binder) {
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat yhmHmFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class,
				new MyCustomDateEditor(this, new DateFormat[]{dateTimeFormat, yhmHmFormat, dateFormat}, true));
	}

	public boolean y(String ownership) {
		return ar.kU.getCode().equals(ownership);
	}

	public void a(HttpServletResponse response, Object obj) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter e = response.getWriter();
			e.print(l.toString(obj));
			e.flush();
		} catch (IOException arg3) {
			logger.error(arg3.getMessage());
		}

	}

	public void b(HttpServletResponse response, Object obj) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			String e = null;
			if (obj != null && obj instanceof String) {
				e = obj.toString();
			} else {
				e = l.toString(obj);
			}

			e = e.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("javascript", "ｊａｖａｓｃｒｉｐｔ");
			response.getWriter().print(e);
		} catch (IOException arg3) {
			logger.error(arg3.getMessage());
		}

	}

	public <T> T b(HttpServletRequest request) {
		return (T)request.getSession().getAttribute("user");
	}

	public String c(HttpServletRequest request) {
		String string = (String) this.b(request, "user_json");
		LoginUser loginUser = (LoginUser) l.toObject(string, LoginUser.class);
		return loginUser.getUnitId();
	}

	public LoginUser d(HttpServletRequest request) {
		String string = (String) this.b(request, "user_json");
		LoginUser loginUser = (LoginUser) l.toObject(string, LoginUser.class);
		return loginUser;
	}

	public void a(HttpServletRequest request, Object account) {
		request.getSession().setAttribute("user", account);
	}

	public void e(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
	}

	public void a(HttpServletRequest request, String key, Object value) {
		request.getSession().setAttribute(key, value);
	}

	public Object b(HttpServletRequest request, String key) {
		return request.getSession().getAttribute(key);
	}

	public void b(HttpServletRequest request, Object zg) {
		request.getSession().setAttribute("user", zg);
	}

	public void c(HttpServletRequest request, String key) {
		request.getSession().removeAttribute(key);
	}
}