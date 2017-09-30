package com.nis.comm.interceptor;

import com.nis.access.entity.AcAccount;
import com.nis.access.service.AcPrivilegeService;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.utils.AppContextUtil;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.l;
import com.nis.organization.entity.Dep;
import com.nis.organization.service.DepService;
import com.nis.param.service.SysParamService;
import com.nis.user.service.UserService;
import com.nis.user.utils.a;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserSecurityViewInterceptor implements HandlerInterceptor {
	private static final Logger logger = Logger.getLogger(UserSecurityViewInterceptor.class);
	private String oN;

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = request.getParameter("token");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String unitId = request.getParameter("unitId");
		String ticket = request.getParameter("ticket");
		String employeeId = request.getParameter("employeeId");
		HttpSession session = request.getSession();
		String infoMsg = "";
		boolean errorFlag = false;
		if (session.getAttribute("user") != null && ab.isEmpty(token) && ab.isEmpty(ticket)) {
			return true;
		} else {
			UserService userService = (UserService) AppContextUtil.getInstance().getBean(UserService.class);
			String deptCode;
			String menus;
			if (ab.isNotEmpty(ticket)) {
				Result sysParamService = null;
				sysParamService = userService.P(employeeId, ticket);
				if (sysParamService.getMsg() != null && !"".equals(sysParamService.getMsg())) {
					infoMsg = sysParamService.getMsg();
				}

				if (sysParamService.getExtraValue() != null && !"".equals(sysParamService.getExtraValue())) {
					errorFlag = true;
				}

				AcAccount tokenCode = (AcAccount) sysParamService.getData();
				if (tokenCode != null) {
					AcPrivilegeService result1 = (AcPrivilegeService) AppContextUtil.getInstance()
							.getBean(AcPrivilegeService.class);
					SysParamService account1 = (SysParamService) AppContextUtil.getInstance()
							.getBean(SysParamService.class);
					DepService acPrivilegeService1 = (DepService) AppContextUtil.getInstance()
							.getBean(DepService.class);
					deptCode = tokenCode.getScopeInfo();
					menus = account1.findByParamCode(Param.NIS_SY_DEPT_QX);
					if (deptCode != null) {
						if (deptCode.indexOf(",") == -1) {
							tokenCode.setDepNo(deptCode);
							session.setAttribute("zg_dept", deptCode);
						} else if (deptCode.indexOf(",") > -1) {
							String menus1 = deptCode.split(",")[0];
							tokenCode.setDepNo(menus1);
							session.setAttribute("zg_dept", menus1);
						}
					}

					if (!"0".equals(menus)) {
						List depList;
						String scopeInfo;
						Dep dept;
						Iterator arg24;
						Dep menus3;
						if ("1".equals(menus)) {
							menus3 = new Dep();
							menus3.setPage(Integer.valueOf(1));
							menus3.setSize(Integer.valueOf(500));
							menus3.setDeptTypeId("1");
							depList = acPrivilegeService1.b(menus3).getRows();
							scopeInfo = "";

							for (arg24 = depList.iterator(); arg24
									.hasNext(); scopeInfo = scopeInfo + dept.getDeptId() + ",") {
								dept = (Dep) arg24.next();
							}

							tokenCode.setScopeInfo(scopeInfo);
						} else if ("2".equals(menus)) {
							menus3 = new Dep();
							menus3.setPage(Integer.valueOf(1));
							menus3.setSize(Integer.valueOf(500));
							menus3.setDeptTypeId("2");
							depList = acPrivilegeService1.b(menus3).getRows();
							scopeInfo = "";

							for (arg24 = depList.iterator(); arg24
									.hasNext(); scopeInfo = scopeInfo + dept.getDeptId() + ",") {
								dept = (Dep) arg24.next();
							}

							tokenCode.setScopeInfo(scopeInfo);
						} else if ("3".equals(menus)) {
							menus3 = new Dep();
							menus3.setPage(Integer.valueOf(1));
							menus3.setSize(Integer.valueOf(500));
							menus3.setIfcaseoffice(Long.valueOf(1L));
							depList = acPrivilegeService1.b(menus3).getRows();
							scopeInfo = "";

							for (arg24 = depList.iterator(); arg24
									.hasNext(); scopeInfo = scopeInfo + dept.getDeptId() + ",") {
								dept = (Dep) arg24.next();
							}

							tokenCode.setScopeInfo(scopeInfo);
						}
					}

					session.setAttribute("user_json", l.toString(a.d(tokenCode)));
					session.setAttribute("user", tokenCode);
					List menus4 = result1.findMenuByRoleid(tokenCode.getRoleCur().getRoleId(), tokenCode.getUserId());
					session.setAttribute("user_menus_json", l.toString(menus4));
					session.setAttribute("user_menus", menus4);
					return true;
				}
			}

			SysParamService sysParamService1 = (SysParamService) AppContextUtil.getInstance()
					.getBean(SysParamService.class);
			String tokenCode1 = sysParamService1.findByParamCode(Param.NIS_MSG_TOKEN);
			if (tokenCode1.equals(token)) {
				Result result = null;
				result = userService.a(username, password, true, unitId);
				AcAccount account = (AcAccount) result.getData();
				if (!"success".equals(result.getResult())) {
					result = userService.b(username, password, unitId, false);
					account = (AcAccount) result.getData();
				}

				if (account != null) {
					session.setAttribute("user_json", l.toString(a.d(account)));
					session.setAttribute("user", account);
					AcPrivilegeService acPrivilegeService = (AcPrivilegeService) AppContextUtil.getInstance()
							.getBean(AcPrivilegeService.class);
					deptCode = account.getScopeInfo();
					if (deptCode != null) {
						if (deptCode.indexOf(",") == -1) {
							account.setDepNo(deptCode);
							session.setAttribute("zg_dept", deptCode);
						} else if (deptCode.indexOf(",") > -1) {
							menus = deptCode.split(",")[0];
							account.setDepNo(menus);
							session.setAttribute("zg_dept", menus);
						}
					}

					List menus2 = acPrivilegeService.findMenuByRoleid(account.getRoleCur().getRoleId(),
							account.getUserId());
					session.setAttribute("user_menus_json", l.toString(menus2));
					session.setAttribute("user_menus", menus2);
					return true;
				}
			}

			if (logger.isInfoEnabled()) {
				logger.info("页面请求，用户未登录!");
			}

			if (errorFlag) {
				response.setCharacterEncoding("GBK");
				response.getWriter().print(infoMsg);
			} else {
				response.sendRedirect(request.getContextPath() + this.oN);
			}

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