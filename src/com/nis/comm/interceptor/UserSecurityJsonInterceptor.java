package com.nis.comm.interceptor;

import com.nis.access.entity.AcAccount;
import com.nis.access.service.AcPrivilegeService;
import com.nis.comm.entity.Result;
import com.nis.comm.utils.AppContextUtil;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.l;
import com.nis.user.service.UserService;
import com.nis.user.utils.a;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserSecurityJsonInterceptor implements HandlerInterceptor {
	private static final Logger logger = Logger.getLogger(UserSecurityJsonInterceptor.class);
	private String oN;

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.setHeader("connection", "close");
		HttpSession session = request.getSession();
		String ticket = request.getParameter("ticket");
		String employeeId = request.getParameter("employeeId");
		if (session.getAttribute("user") != null && ab.isEmpty(ticket)) {
			return true;
		} else {
			UserService result = (UserService) AppContextUtil.getInstance().getBean(UserService.class);
			if (ab.isNotEmpty(ticket)) {
				Result result1 = null;
				result1 = result.P(employeeId, ticket);
				AcAccount account = (AcAccount) result1.getData();
				if (account != null) {
					AcPrivilegeService acPrivilegeService = (AcPrivilegeService) AppContextUtil.getInstance()
							.getBean(AcPrivilegeService.class);
					session.setAttribute("user_json", l.toString(a.d(account)));
					session.setAttribute("user", account);
					String deptCode = account.getScopeInfo();
					if (deptCode != null) {
						if (deptCode.indexOf(",") == -1) {
							account.setDepNo(deptCode);
							session.setAttribute("zg_dept", deptCode);
						} else if (deptCode.indexOf(",") > -1) {
							String menus = deptCode.split(",")[0];
							account.setDepNo(menus);
							session.setAttribute("zg_dept", menus);
						}
					}

					List menus1 = acPrivilegeService.findMenuByRoleid(account.getRoleCur().getRoleId(),
							account.getUserId());
					session.setAttribute("user_menus_json", l.toString(menus1));
					session.setAttribute("user_menus", menus1);
					return true;
				}
			}

			if (logger.isInfoEnabled()) {
				logger.info("ajax请求，用户未登录!");
			}

			Result result2 = new Result("error_not_login", "Not logged in to the request!");
			response.getWriter().print(l.toString(result2));
			return false;
		}
	}

	public String getLoginUrl() {
		return this.oN;
	}

	public void setLoginUrl(String loginUrl) {
		this.oN = loginUrl;
	}
}