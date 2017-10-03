package com.nis.user.controller;

import com.nis.access.entity.AcAccount;
import com.nis.access.entity.AcMenu;
import com.nis.access.entity.AcPwdFind;
import com.nis.access.entity.AcRole;
import com.nis.access.service.AcMenuService;
import com.nis.access.service.AcPrivilegeService;
import com.nis.access.service.AcPwdFindService;
import com.nis.comm.controller.BaseController;
import com.nis.comm.entity.LoginUser;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.af;
import com.nis.comm.enums.ag;
import com.nis.comm.enums.ah;
import com.nis.comm.enums.ar;
import com.nis.comm.enums.e;
import com.nis.comm.enums.h;
import com.nis.comm.enums.n;
import com.nis.comm.utils.EncryptUtils;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.d;
import com.nis.comm.utils.f;
import com.nis.comm.utils.i;
import com.nis.comm.utils.l;
import com.nis.comm.utils.x;
import com.nis.homepage.entity.SysHpStyle;
import com.nis.homepage.service.SysHpComponentService;
import com.nis.homepage.service.SysHpLayoutService;
import com.nis.homepage.service.SysHpStyleService;
import com.nis.log.entity.SysLoginLog;
import com.nis.log.module.a;
import com.nis.log.service.SysLogService;
import com.nis.log.service.SysLoginLogService;
import com.nis.msg.entity.NyMessageDetail;
import com.nis.msg.service.NyMessageDetailService;
import com.nis.organization.entity.Dep;
import com.nis.organization.entity.Doctor;
import com.nis.organization.service.DepService;
import com.nis.organization.service.DoctorService;
import com.nis.organization.service.UnitService;
import com.nis.param.service.SysParamService;
import com.nis.user.entity.Sms4RandomNum;
import com.nis.user.service.UserService;
import com.nis.zg.entity.Zg031Sqks;
import com.nis.zg.service.Zg003YyzgService;
import com.nis.zg.service.Zg031SqksService;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController extends BaseController {
	private static final Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	private UserService cU;
	@Autowired
	private AcPwdFindService xX;
	@Autowired
	private AcMenuService k;
	@Autowired
	private AcPrivilegeService i;
	@Autowired
	private SysParamService j;
	@Autowired
	private SysHpStyleService ri;
	@Autowired
	private SysHpComponentService rh;
	@Autowired
	private SysHpLayoutService rj;
	@Autowired
	private UnitService se;
	@Autowired
	private SysLogService aV;
	@Autowired
	private SysLoginLogService tI;
	@Autowired
	private Zg003YyzgService q;
	@Autowired
	private DoctorService f;
	@Autowired
	private Zg031SqksService dF;
	@Autowired
	private DepService e;
	@Autowired
	private NyMessageDetailService cV;

	@RequestMapping({"/user/json/login"})
	@ResponseBody
	public void m(HttpServletRequest request, HttpServletResponse response, String username, String password,
			String kaptcha, String unitId) throws Exception {
		a logCore = new a();
		String ip = x.l(request);
		Result result = null;
		if (!com.nis.comm.utils.i.i(request).booleanValue()) {
			result = new Result("error", "非浏览器请求!");
			result.setExpandValue("5");
			this.a(response, result);
			logger.warn("非浏览器请求！");
		} else {
			String loginErrorNumKey = "user_login_error_num_" + username;
			Long loginErrorNumValue = Long.valueOf(0L);
			if (this.b(request, loginErrorNumKey) == null) {
				loginErrorNumValue = Long.valueOf(0L);
			} else {
				try {
					loginErrorNumValue = (Long) this.b(request, loginErrorNumKey);
				} catch (NumberFormatException arg22) {
					loginErrorNumValue = Long.valueOf(0L);
				}
			}

			AcAccount ac;
			try {
				boolean acc = this.c(request, response, loginErrorNumKey, kaptcha, loginErrorNumValue);
				if (!acc) {
					return;
				}

				result = this.cU.a(username, password, false, (String) null);
				ac = (AcAccount) result.getData();
				if (!"success".equals(result.getResult())) {
					result = this.cU.b(username, password, (String) null, true);
					ac = (AcAccount) result.getData();
				}

				if (!"success".equals(result.getResult())) {
					SysLoginLog arg25 = new SysLoginLog();
					arg25.setUnitId(unitId);
					arg25.setUsername(username);
					arg25.setContent(result.getMsg());
					arg25.setOperateTime(new Date());
					arg25.setIp(x.l(request));
					arg25.setSqls(logCore.getSqlString());
					logCore.clear();
					this.tI.save(arg25);
				} else {
					this.c(request, loginErrorNumKey);
					if (logger.isInfoEnabled()) {
						logger.info(ac.getRealname() + " - 登录成功!");
					}

					String sysLoginLog = ac.getScopeInfo();
					String deptQx = this.j.findByParamCode(Param.NIS_SY_DEPT_QX);
					if (sysLoginLog != null) {
						if (sysLoginLog.indexOf(",") == -1) {
							ac.setDepNo(sysLoginLog);
							this.a(request, "zg_dept", sysLoginLog);
						} else if (sysLoginLog.indexOf(",") > -1) {
							String role = sysLoginLog.split(",")[0];
							ac.setDepNo(role);
							this.a(request, "zg_dept", role);
						}
					}

					List menus;
					Iterator sysLoginLog1;
					if (!"0".equals(deptQx)) {
						String single;
						Dep count;
						Dep arg26;
						if ("1".equals(deptQx)) {
							arg26 = new Dep();
							arg26.setPage(Integer.valueOf(1));
							arg26.setSize(Integer.valueOf(500));
							arg26.setDeptTypeId("1");
							menus = this.e.b(arg26).getRows();
							single = "";

							for (sysLoginLog1 = menus.iterator(); sysLoginLog1
									.hasNext(); single = single + count.getDeptId() + ",") {
								count = (Dep) sysLoginLog1.next();
							}

							ac.setScopeInfo(single);
						} else if ("2".equals(deptQx)) {
							arg26 = new Dep();
							arg26.setPage(Integer.valueOf(1));
							arg26.setSize(Integer.valueOf(500));
							arg26.setDeptTypeId("2");
							menus = this.e.b(arg26).getRows();
							single = "";

							for (sysLoginLog1 = menus.iterator(); sysLoginLog1
									.hasNext(); single = single + count.getDeptId() + ",") {
								count = (Dep) sysLoginLog1.next();
							}

							ac.setScopeInfo(single);
						} else if ("3".equals(deptQx)) {
							arg26 = new Dep();
							arg26.setPage(Integer.valueOf(1));
							arg26.setSize(Integer.valueOf(500));
							arg26.setIfcaseoffice(Long.valueOf(1L));
							menus = this.e.b(arg26).getRows();
							single = "";

							for (sysLoginLog1 = menus.iterator(); sysLoginLog1
									.hasNext(); single = single + count.getDeptId() + ",") {
								count = (Dep) sysLoginLog1.next();
							}

							ac.setScopeInfo(single);
						}
					}

					AcRole arg27 = ac.getRoleCur();
					if (arg27 != null) {
						ac.setRoleScope(arg27.getRoleScope());
					}

					this.a(request, ac);
					this.a(request, "user_json", l.toString(com.nis.user.utils.a.d(ac)));
					menus = this.i.findMenuByRoleid(ac.getRoleCur().getRoleId(), ac.getUserId());
					if (com.nis.comm.enums.e.fE.getValue().equals(ac.getRoleCur().getRoleType())) {
						ArrayList arg28 = new ArrayList();
						sysLoginLog1 = menus.iterator();

						while (sysLoginLog1.hasNext()) {
							AcMenu arg29 = (AcMenu) sysLoginLog1.next();
							if ("1".equals(arg29.getIsreport())) {
								arg28.add(arg29.getParentMenuNo());
							}
						}

						String[] arg31 = ab.f(arg28, ",").split(",");
						List arg33 = this.k.findMenuNos(arg31);
						Iterator arg21 = arg33.iterator();

						while (arg21.hasNext()) {
							AcMenu menu = (AcMenu) arg21.next();
							menu.setIsreport("1");
						}

						menus.addAll(arg33);
					}

					int arg30 = 0;
					int arg32 = 0;
					if (menus.size() > 0) {
						int arg34;
						for (arg34 = 0; arg34 < menus.size(); ++arg34) {
							if (((AcMenu) menus.get(arg34)).getDestUrl() == null
									&& !"K06".equals(((AcMenu) menus.get(arg34)).getParentMenuNo())) {
								++arg30;
								arg32 = arg34;
							}
						}

						if (arg30 == 1 && "K".equals(((AcMenu) menus.get(arg32)).getMenuNo())) {
							menus.remove(arg32);

							for (arg34 = 0; arg34 < menus.size(); ++arg34) {
								((AcMenu) menus.get(arg34)).setParentMenuNo((String) null);
							}
						}
					}

					this.a(request, "user_menus_json", l.toString(menus));
					this.a(request, "user_menus", menus);
					SysLoginLog arg35 = new SysLoginLog();
					arg35.setUnitId(ac.getUnitId());
					arg35.setUsername(ac.getRealname());
					arg35.setUserId(ac.getUserId());
					arg35.setContent("登录成功");
					arg35.setOperateTime(new Date());
					arg35.setIp(x.l(request));
					arg35.setSqls(logCore.getSqlString());
					logCore.clear();
					this.tI.save(arg35);
				}

				if (ac == null) {
					loginErrorNumValue = Long.valueOf(loginErrorNumValue.longValue() + 1L);
				} else {
					loginErrorNumValue = ac.getErrorTimes();
				}

				this.a(request, loginErrorNumKey, loginErrorNumValue);
				result.setExpandValue("" + loginErrorNumValue);
				if (loginErrorNumValue.longValue() > 3L) {
					this.c(request, "KAPTCHA_SESSION_KEY");
				}
			} catch (Exception arg23) {
				logger.error("获取信息异常!", arg23);
				this.c(request, "KAPTCHA_SESSION_KEY");
				result = new Result("error", "获取信息异常");
				result.setExpandValue("" + loginErrorNumValue);
			}

			AcAccount arg24 = (AcAccount) result.getData();
			if (arg24 != null) {
				ac = new AcAccount();
				ac.setDataScope(arg24.getDataScope());
				ac.setRoleCur(arg24.getRoleCur());
				result.setData(ac);
			}

			request.getSession().setAttribute("allowClinicPrint",
					this.j.findByParamCode(Param.NIS_CDC_IS_ALLOW_CLINIC_PRINT));
			this.a(response, result);
		}
	}

	@RequestMapping({"/user/f_json/chooseDep"})
	@ResponseBody
	public void aG(HttpServletRequest request, HttpServletResponse response, String deptId) {
		Result result = null;

		try {
			result = new Result();
			this.a(request, "zg_dept", deptId);
			AcAccount e = (AcAccount) this.b(request);
			e.setDepNo(deptId);
			this.a(request, e);
			this.a(request, "user_json", l.toString(com.nis.user.utils.a.d(e)));
			result.setResult("success");
		} catch (Exception arg5) {
			result = new Result("error", "获取信息异常");
		}

		this.a(response, result);
	}

	private boolean c(HttpServletRequest request, HttpServletResponse response, String loginErrorNumKey, String kaptcha,
			Long loginErrorNumValue) {
		this.c(request, loginErrorNumKey);
		return true;
	}

	@RequestMapping({"/user/f_view/logout"})
	public String r(HttpServletRequest request) {
		AcAccount account = (AcAccount) this.b(request);
		this.e(request);
		this.c(request, "user_menus");
		this.c(request, "user_json");
		this.aV.a((String) null, account.getUsername(), ah.iD, af.iv, ag.ix, account.getUserId(), (Object[]) null);
		return "redirect:/index.jsp";
	}

	@RequestMapping({"/user/f_view/logout_admin"})
	public String s(HttpServletRequest request) {
		AcAccount account = (AcAccount) this.b(request);
		this.e(request);
		this.c(request, "user_menus");
		this.c(request, "user_json");
		this.aV.a((String) null, account.getUsername(), ah.iD, af.iv, ag.ix, account.getUserId(),
				new Object[]{account});
		return "redirect:/login_admin.jsp";
	}

	@RequestMapping({"/user/f_view/main"})
	public String ah(HttpServletRequest request, ModelMap modelMap, String unitId) {
		AcAccount account = (AcAccount) this.b(request);
		if (account != null && n.gm.getValue().intValue() == account.getDataScope().intValue()) {
			account.setUnitId(unitId);
			account.setUnitName(this.se.getName((String) null));
			this.a(request, account);
			this.a(request, "user_json", l.toString(com.nis.user.utils.a.d(account)));
			AcMenu e = new AcMenu();
			e.setOwnership(ar.kU.getCode());
			e.setIsvalid(h.fP.getCode().toString());
			List count = this.k.findByOwnership(e);
			modelMap.put("user_menus", count);
			modelMap.put("isAdmin", h.fP.getCode());
		}

		if ("1".equals(this.j.findByParamCode(Param.NIS_IS_SHOW_MSG_BOX))) {
			try {
				NyMessageDetail e1 = new NyMessageDetail();
				if (!"hospital".equals(account.getAcType())) {
					e1.setUserId(account.getDocNo());
				} else {
					e1.setUserId(account.getUsername());
				}

				e1.setMsgType("7");
				e1.setIsRead("0");
				int count1 = this.cV.getMsgsCount(e1);
				if (count1 > 0) {
					modelMap.put("showEWIMsgs", "show");
				}
			} catch (Exception arg6) {
				arg6.printStackTrace();
			}

			modelMap.put("SHOWMSGBOX", this.j.findByParamCode(Param.NIS_IS_SHOW_MSG_BOX));
		}

		modelMap.put("showMsgAlert", this.j.findByParamCode(Param.NIS_IS_SHOW_INTERFACE_MSG_ALERT));
		modelMap.put("ISRBAENABLED", this.j.findByParamCode(Param.NIS_CDC_INTERFACE_IS_RBA_ENABLED));
		return "main";
	}

	@RequestMapping({"/user/f_view/main_admin"})
	public String ai(HttpServletRequest request, ModelMap modelMap, String deptId) {
		AcAccount account = (AcAccount) this.b(request);
		List sqksList = this.dF.getAll(account.getUserId());
		ArrayList deptIdList = new ArrayList();
		String deptId2;
		int arg10;
		if (ab.isNotEmpty(deptId)) {
			Doctor deptIds = this.f.get(account.getUserId());
			if (deptIds != null) {
				deptId2 = deptIds.getDeptId();
				String[] deptType = deptId.split(",");
				String[] arg12 = deptType;
				int arg11 = deptType.length;

				for (arg10 = 0; arg10 < arg11; ++arg10) {
					String depId = arg12[arg10];
					Dep dep = this.e.get(depId);
					if (dep != null) {
						deptIdList.add(depId);
					}

					if (deptId2.indexOf(depId) == -1) {
						deptId2 = deptId2 + "," + depId;
					}
				}

				deptIds.setDeptId(deptId2);
				this.f.update(deptIds);
				this.a(request, "zg_dept", deptType[0]);
			}
		}

		Iterator arg16 = sqksList.iterator();

		while (arg16.hasNext()) {
			Zg031Sqks arg14 = (Zg031Sqks) arg16.next();
			deptIdList.add(arg14.getAuthDeptId());
		}

		String arg15 = account.getScopeInfo();
		String arg19;
		if (ab.isNotEmpty(arg15)) {
			String[] arg17 = arg15.split(",");
			String[] arg21 = arg17;
			arg10 = arg17.length;

			for (int arg20 = 0; arg20 < arg10; ++arg20) {
				arg19 = arg21[arg20];
				deptIdList.add(arg19);
			}
		}

		if (deptIdList.size() > 0) {
			List arg18 = this.e.getByDeptIds(deptIdList);
			modelMap.put("deptList", arg18);
		}

		deptId2 = (String) this.b(request, "zg_dept");
		arg19 = this.e.getDeptType(deptId2);
		modelMap.put("deptType", arg19);
		modelMap.put("SHOWMSGBOX", this.j.findByParamCode(Param.NIS_IS_SHOW_MSG_BOX));
		modelMap.put("ISRBAENABLED", this.j.findByParamCode(Param.NIS_CDC_INTERFACE_IS_RBA_ENABLED));
		return "main_admin";
	}

	@RequestMapping({"/user/f_view/main_info"})
	public String aj(HttpServletRequest request, ModelMap modelMap, String isHttps) {
		LoginUser loginUser = this.d(request);
		SysHpStyle sysHpStyle = this.ri.a(loginUser.getDataScope().intValue(), (String) null, loginUser.getDepNo(),
				loginUser.getUsername());
		modelMap.put("sysHpStyle", sysHpStyle);
		modelMap.put("curMonthFirst", com.nis.comm.utils.f.q(com.nis.comm.utils.f.g(false)));
		modelMap.put("curMonthLast", com.nis.comm.utils.f.p(com.nis.comm.utils.f.h(false)));
		if ("true".equals(isHttps)) {
			request.getSession().setAttribute("isHttps", "true");
		} else {
			request.getSession().setAttribute("isHttps", "false");
		}

		return "main_info";
	}

	@RequestMapping({"/user/view/findPwd"})
	public String aw(HttpServletRequest request, ModelMap modelMap) {
		return "user/findPwd";
	}

	@RequestMapping({"/user/view/repeat"})
	public String ax(HttpServletRequest request, ModelMap modelMap) {
		return "user/repeat";
	}

	@RequestMapping({"/user/json/kaptcha"})
	@ResponseBody
	public void aH(HttpServletRequest request, HttpServletResponse response, String kaptcha) {
		Result result = null;
		if (ab.isEmpty(kaptcha)) {
			result = new Result("error", "验证码错误！");
			this.a(response, result);
		} else {
			String kaptchaExpected = (String) request.getSession().getAttribute("KAPTCHA_SESSION_KEY");
			if (ab.isEmpty(kaptchaExpected)) {
				result = new Result("error", "恶意请求！拒绝访问！");
				this.a(response, result);
			} else if (!kaptcha.equalsIgnoreCase(kaptchaExpected)) {
				result = new Result("error", "验证码错误！");
				this.a(response, result);
			} else {
				result = new Result("success", "验证码成功！");
				this.a(response, result);
			}
		}
	}

	@RequestMapping({"/user/view/emailRemind"})
	public String a(HttpServletRequest request, HttpServletResponse response, String cname, String email,
			ModelMap modelMap) {
		email = email.trim();
		email = email.replace(email.substring(3, email.indexOf("@")), "******");
		modelMap.put("email", email);
		return "user/emailReminder";
	}

	@RequestMapping({"/user/view/setPwd"})
	public String b(HttpServletRequest request, HttpServletResponse response, String uid, String dbTime,
			ModelMap modelMap) {
		if (!ab.isEmpty(uid) && !ab.isEmpty(dbTime)) {
			String applyTimeString = EncryptUtils.af(dbTime);
			String userIdString = EncryptUtils.af(uid);
			Date applyTime = null;

			try {
				applyTime = com.nis.comm.utils.f.l(applyTimeString, "yyyy-MM-dd HH:mm:ss");
			} catch (Exception arg18) {
				modelMap.put("msg", "页面找不到");
				return "redirect:/404.jsp";
			}

			int outTime = Integer.parseInt(this.j.findByParamCode(Param.NIS_PMS_USER_SETPWD_TIMEOUT, (String) null,
					(String) null, (String) null));
			long maxTime = applyTime.getTime() + (long) (outTime * 60 * 60 * 1000);
			long currentTime = (new Date()).getTime();
			if (currentTime > maxTime) {
				modelMap.put("msg", "链接已失效！");
				return "redirect:/404.jsp";
			} else {
				Result result = this.cU.cG(userIdString);
				if ("error".equals(result.getResult())) {
					modelMap.put("msg", "账户不存在！");
					return "redirect:/404.jsp";
				} else {
					AcAccount ac = (AcAccount) result.getData();
					AcPwdFind acPwd = this.xX.findAcPwdFindByUserNameEmailVDate(ac.getUsername(), ac.getEmail(),
							applyTime);
					if (acPwd == null) {
						return "redirect:/index.jsp";
					} else {
						String userId = EncryptUtils.ad(ac.getUserId());
						String findId = EncryptUtils.ad(acPwd.getId());
						modelMap.put("realname", ac.getRealname());
						modelMap.put("userId", userId);
						modelMap.put("findId", findId);
						return "user/setPwd";
					}
				}
			}
		} else {
			modelMap.put("msg", "页面找不到");
			return "redirect:/404.jsp";
		}
	}

	@RequestMapping({"/user/json/saveUserPwd"})
	@ResponseBody
	public void A(HttpServletRequest request, HttpServletResponse response, String userId, String passwd,
			String findId) {
		Result result = null;
		if (!ab.isEmpty(userId) && !ab.isEmpty(passwd) && !ab.isEmpty(findId)) {
			passwd = DigestUtils.md5Hex(passwd.trim());
			String trueUserId = EncryptUtils.af(userId);
			String trueFindId = EncryptUtils.af(findId);
			boolean isExistUser = false;

			try {
				isExistUser = this.cU.cK(trueUserId);
			} catch (NumberFormatException arg10) {
				logger.warn("csm 系统被修改密码恶意攻击！系统以拒绝其请求！");
				isExistUser = false;
			}

			if (!isExistUser) {
				result = new Result("error", "保存失败");
				this.a(response, result);
			} else {
				boolean success = this.cU.u(trueUserId, passwd, trueFindId);
				if (!success) {
					result = new Result("error", "保存失败");
					this.a(response, result);
				} else {
					result = new Result();
					result.setResult("success");
					this.a(response, result);
				}
			}
		} else {
			result = new Result("error", "保存失败");
			this.a(response, result);
		}
	}

	@RequestMapping({"/user/view/successPwd"})
	public String V(HttpServletRequest request, HttpServletResponse response) {
		return "user/successPwd";
	}

	@RequestMapping({"/user/view/unitRegister"})
	public String W(HttpServletRequest request, HttpServletResponse response) {
		return "user/register";
	}

	@RequestMapping({"/user/json/findUnitByUnitName"})
	@ResponseBody
	public void aI(HttpServletRequest request, HttpServletResponse response, String unitName) {
		Result result = null;
		if (ab.isEmpty(unitName)) {
			result = new Result("error", "医院名称为空");
			this.a(response, result);
		} else {
			Result resultUn = this.cU.cJ(unitName.trim());
			if ("error".equals(resultUn.getResult())) {
				result = new Result("error", "医院不存在");
				this.a(response, result);
			} else {
				this.a(response, resultUn);
			}
		}
	}

	@RequestMapping({"/user/json/findAccountByMobilenum"})
	@ResponseBody
	public void aJ(HttpServletRequest request, HttpServletResponse response, String mobilenum) {
		Result result = null;
		if (ab.isEmpty(mobilenum)) {
			result = new Result("error", "电话号码为空");
			this.a(response, result);
		} else {
			Result resultAc = this.cU.cH(mobilenum.trim());
			if ("error".equals(resultAc.getResult())) {
				result = new Result("error", "账户不存在");
				this.a(response, result);
			} else {
				result = new Result("success", "账户存在");
				this.a(response, result);
			}
		}
	}

	@RequestMapping({"/user/json/findAccountByEmail"})
	@ResponseBody
	public void aK(HttpServletRequest request, HttpServletResponse response, String email) {
		Result result = null;
		if (ab.isEmpty(email)) {
			result = new Result("error", "email为空");
			this.a(response, result);
		} else {
			Result resultAc = this.cU.cI(email.trim());
			if ("error".equals(resultAc.getResult())) {
				result = new Result("error", "email不存在");
				this.a(response, result);
			} else {
				result = new Result("success", "email存在");
				this.a(response, result);
			}
		}
	}

	@RequestMapping({"/user/view/unitRegisterSuccess"})
	public String X(HttpServletRequest request, HttpServletResponse response) {
		return "user/successReg";
	}

	@RequestMapping({"/user/json/regSmsVerify"})
	@ResponseBody
	public void Z(HttpServletRequest request, HttpServletResponse response, String mobilenum, String randomNum) {
		Result result = null;
		if (ab.isEmpty(randomNum)) {
			result = new Result("error", "验证验证码失败，参数为空");
			this.a(response, result);
		} else {
			String key = "reg" + mobilenum.trim();
			String cacheKey = "user_reg_sms_code_" + mobilenum.trim();
			Sms4RandomNum sms4RandomNum = (Sms4RandomNum) this.b(request, key);
			if (sms4RandomNum == null) {
				result = new Result("error", "验证验证码失败,无验证码");
				this.a(response, result);
			} else {
				boolean regErrorNumValue = false;
				int arg11;
				if (d.get(cacheKey) == null) {
					arg11 = 0;
				} else {
					try {
						arg11 = ((Integer) d.get(cacheKey)).intValue();
					} catch (NumberFormatException arg10) {
						arg11 = 5;
					}
				}

				if (arg11 > 5) {
					result = new Result("error", "验证失败！请1小时后重新注册!");
					this.a(response, result);
				} else if (!randomNum.trim().equals(sms4RandomNum.getRandomNum())) {
					++arg11;
					d.set(cacheKey, Integer.valueOf(arg11));
					result = new Result("error", "验证验证码失败，验证码错误");
					this.a(response, result);
				} else {
					d.a(cacheKey);
					result = new Result();
					result.setResult("success");
					this.a(response, result);
				}
			}
		}
	}

	private void a(HttpServletRequest request, String mobilenum, Sms4RandomNum sms4RandomNum) {
		String key = "reg" + mobilenum;
		this.a(request, key, sms4RandomNum);
	}

	private int getSms4RandomNumber() {
		double result = Math.random();
		result = result * 9000.0D + 1000.0D;
		return (int) result;
	}

	@RequestMapping({"/user/json/repeat"})
	@ResponseBody
	public void d(HttpServletRequest request, HttpServletResponse response, String unitId, String username,
			String password, String nusername, String kaptcha) {
		Result result = null;
		if (!com.nis.comm.utils.i.i(request).booleanValue()) {
			result = new Result("error", "非浏览器请求!");
			result.setExpandValue("5");
			this.a(response, result);
			logger.warn("非浏览器请求！");
		} else {
			String loginErrorNumKey = "user_login_repeat_error_num_" + username;
			Long loginErrorNumValue = Long.valueOf(0L);
			if (this.b(request, loginErrorNumKey) == null) {
				loginErrorNumValue = Long.valueOf(0L);
			} else {
				try {
					loginErrorNumValue = (Long) this.b(request, loginErrorNumKey);
				} catch (NumberFormatException arg13) {
					loginErrorNumValue = Long.valueOf(0L);
				}
			}

			AcAccount ac;
			try {
				boolean acc = this.c(request, response, loginErrorNumKey, kaptcha, loginErrorNumValue);
				if (!acc) {
					return;
				}

				if (unitId == null || ab.isEmpty(username) || ab.isEmpty(nusername) || ab.isEmpty(password)) {
					result = new Result("error", "医院不存在或参数缺失!");
					this.a(response, result);
					logger.warn("修改登陆名称部分参数不合法！");
					return;
				}

				result = this.cU.c((String) null, username, password, false);
				ac = (AcAccount) result.getData();
				if ("success".equals(result.getResult())) {
					this.c(request, loginErrorNumKey);
					if (logger.isInfoEnabled()) {
						logger.info(ac.getRealname() + " - 登录成功!");
					}

					ac.setUsername(nusername);
					this.cU.f(ac);
					this.a(request, ac);
					this.a(request, "user_json", l.toString(com.nis.user.utils.a.d(ac)));
					List menus = this.i.findMenuByRoleid(ac.getRoleCur().getRoleId(), ac.getUserId());
					this.a(request, "user_menus", menus);
					this.a(request, "user_menus_json", l.toString(menus));
				}

				if (ac == null) {
					loginErrorNumValue = Long.valueOf(loginErrorNumValue.longValue() + 1L);
				} else {
					loginErrorNumValue = ac.getErrorTimes();
				}

				this.a(request, loginErrorNumKey, loginErrorNumValue);
				result.setExpandValue("" + loginErrorNumValue);
				if (loginErrorNumValue.longValue() > 3L) {
					this.c(request, "KAPTCHA_SESSION_KEY");
				}
			} catch (Exception arg14) {
				logger.error("获取信息异常!", arg14);
				this.c(request, "KAPTCHA_SESSION_KEY");
				result = new Result("error", "获取信息异常");
				result.setExpandValue("" + loginErrorNumValue);
			}

			AcAccount acc1 = (AcAccount) result.getData();
			if (acc1 != null) {
				ac = new AcAccount();
				ac.setDataScope(acc1.getDataScope());
				result.setData(ac);
			}

			this.a(response, result);
		}
	}
	
	@RequestMapping({"/user/json/check"})
	@ResponseBody
	public void aL(HttpServletRequest request, HttpServletResponse response, String username) {
		Result result = null;
		if (!com.nis.comm.utils.i.i(request).booleanValue()) {
			result = new Result("error", "非浏览器请求!");
			result.setExpandValue("5");
			this.a(response, result);
			logger.warn("非浏览器请求！");
		} else {
			List list = this.cU.get(username);
			if (list != null && list.size() > 0) {
				result = new Result("error", "用户已存在!");
			} else {
				result = new Result("success", "用户不存在!");
			}

			this.a(response, result);
		}
	}

	@RequestMapping({"/nis/f_adapter/login"})
	@ResponseBody
	public void aa(HttpServletRequest request, HttpServletResponse response, String username, String password) {
		Result result = null;
		if (ab.isNotEmpty(username)) {
			result = this.cU.a(username, password, false);
			if (!"success".equals(result.getResult())) {
				result = this.cU.b(username, password, (String) null, true);
			}

			this.a(response, result);
		} else {
			result = new Result();
			result.setMsg("登录账号为空");
			result.setResult("error");
			this.a(response, result);
		}

	}
}