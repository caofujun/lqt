package com.nis.user.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.nis.access.entity.AcAccount;
import com.nis.access.entity.AcAccountAndUnit;
import com.nis.access.entity.AcRole;
import com.nis.access.service.AcAccountService;
import com.nis.access.service.AcAccountroleService;
import com.nis.access.service.AcPwdFindService;
import com.nis.access.service.AcRoleService;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.Param;
import com.nis.comm.enums.a;
import com.nis.comm.enums.af;
import com.nis.comm.enums.ag;
import com.nis.comm.enums.ah;
import com.nis.comm.enums.d;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.t;
import com.nis.log.service.SysLogService;
import com.nis.organization.entity.Doctor;
import com.nis.organization.entity.Unit;
import com.nis.organization.service.DoctorService;
import com.nis.organization.service.UnitService;
import com.nis.param.service.SysParamService;
import com.nis.user.service.UserService;
import com.nis.zg.service.Zg003YyzgService;

class AsyncLogin
extends Thread
{
AcAccount xY = null;
UserServiceImpl ins;

public AsyncLogin(UserServiceImpl paramUserServiceImpl, AcAccount account)
{
  this.xY = account;
  this.ins = paramUserServiceImpl;
}

public void run()
{
	ins.getuJ().updateLoginInfo(this.xY.getUserId(), Long.valueOf(0L));
  
	ins.getaV().a(this.xY.getUnitId(), this.xY.getUsername(), 
		  ah.iC, af.iv, ag.ix, this.xY.getUserId(), new Object[] { this.xY });
}
}




@Component
public class UserServiceImpl implements UserService {
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
	@Autowired
	private AcAccountService uJ;
	@Autowired
	private UnitService se;
	@Autowired
	private DoctorService f;
	@Autowired
	private AcAccountroleService h;
	@Autowired
	private AcRoleService g;
	@Autowired
	private AcPwdFindService xX;
	@Autowired
	private SysParamService j;
	@Autowired
	private SysLogService aV;
	@Autowired
	private Zg003YyzgService yb;
	
	
	

	public SysLogService getaV() {
		return aV;
	}

	public AcAccountService getuJ() {
		return uJ;
	}

	public Result<AcAccount> a(String username, String password, boolean md5) {
		return this.a(username, password, md5, (String) null);
	}

	public Result<AcAccount> e(AcAccount account) {
		Result result = new Result();
		if (account != null && !ab.isEmpty(account.getEmail()) && !ab.isEmpty(account.getRealname())) {
			AcAccount ac = this.uJ.findAccountByRealnameNameAndEmail(account.getRealname(), account.getEmail());
			if (ac == null) {
				result.setResult("error");
				result.setMsg("帐号不存在!");
				return result;
			} else if (ac.getIsvalid().intValue() == com.nis.comm.enums.h.fQ.getCode().intValue()) {
				result.setResult("error");
				result.setMsg("您的账号未激活，请联系管理员!");
				return result;
			} else {
				result.setData(ac);
				result.setResult("success");
				return result;
			}
		} else {
			result.setResult("error");
			result.setMsg("帐号不存在!");
			return result;
		}
	}

	public Result<AcAccount> cG(String userId) {
		Result result = new Result();
		AcAccount account = this.uJ.get(userId);
		if (account == null) {
			result.setResult("error");
			result.setMsg("帐号不存在!");
		} else {
			result.setData(account);
			result.setResult("success");
		}

		return result;
	}

	@Transactional
	public boolean u(String userId, String passwd, String findId) {
		boolean result = false;
		if (userId != null && findId != null && !ab.isEmpty(passwd)) {
			try {
				this.uJ.updatePwdByUserId(userId, passwd);
				this.xX.updateState(findId, Long.valueOf(d.fB.getValue().longValue()));
				result = true;
			} catch (Exception arg5) {
				result = false;
				arg5.printStackTrace();
			}

			return result;
		} else {
			return false;
		}
	}

	public Result<AcAccount> cH(String mobilenum) {
		Result result = new Result();
		if (ab.isEmpty(mobilenum)) {
			result.setResult("error");
			result.setMsg("参数为空!");
			return result;
		} else {
			AcAccount ac = this.uJ.findAccountByMobilenum(mobilenum);
			if (ac == null) {
				result.setResult("error");
				result.setMsg("帐号不存在!");
				return result;
			} else {
				result.setData(ac);
				result.setResult("success");
				return result;
			}
		}
	}

	public Result<AcAccount> cI(String email) {
		Result result = new Result();
		if (ab.isEmpty(email)) {
			result.setResult("error");
			result.setMsg("参数为空!");
			return result;
		} else {
			AcAccount ac = this.uJ.d(email);
			if (ac == null) {
				result.setResult("error");
				result.setMsg("帐号不存在!");
				return result;
			} else {
				result.setData(ac);
				result.setResult("success");
				return result;
			}
		}
	}

	public Result<Unit> cJ(String unitName) {
		Result result = new Result();
		if (ab.isEmpty(unitName)) {
			result.setResult("error");
			result.setMsg("参数为空!");
			return result;
		} else {
			Unit unit = this.se.findUnitByUnitName(unitName);
			if (unit == null) {
				result.setResult("error");
				result.setMsg("医院不存在!");
				return result;
			} else {
				result.setData(unit);
				result.setResult("success");
				return result;
			}
		}
	}

	private void a(AcAccountAndUnit acAccountAndUnit) throws IllegalAccessError, IllegalAccessException {
		AcAccount ac = new AcAccount();
		ac.setUnitId(acAccountAndUnit.getUnitId());
		acAccountAndUnit.setMobilenum(acAccountAndUnit.getMobilenum().trim());
		if (!ab.isEmpty(acAccountAndUnit.getRealname()) && !ab.isEmpty(acAccountAndUnit.getMobilenum())) {
			String realName = t.aB(acAccountAndUnit.getRealname().trim());
			realName = realName + acAccountAndUnit.getMobilenum()
					.substring(acAccountAndUnit.getMobilenum().length() - 4, acAccountAndUnit.getMobilenum().length());
			ac.setUsername(realName);
		}

		ac.setRealname(acAccountAndUnit.getRealname());
		if (ab.isEmpty(acAccountAndUnit.getPasswd())) {
			logger.error("注册医院和账户信息异常，密码为空！");
			throw new IllegalAccessException("密码为空");
		} else {
			ac.setPasswd(DigestUtils.md5Hex(acAccountAndUnit.getPasswd().trim()));
			ac.setEmail(acAccountAndUnit.getEmail().trim());
			ac.setMobilenum(acAccountAndUnit.getMobilenum());
			ac.setIsvalid(Long.valueOf(com.nis.comm.enums.h.fQ.getCode().longValue()));
			ac.setUserType(Long.valueOf(a.fr.getValue().longValue()));
			this.uJ.a(ac);
		}
	}

	public void Q(String userId, String newpasswd) {
		String passwd = DigestUtils.md5Hex(newpasswd);
		this.uJ.updatePwdByUserId(userId, passwd);
	}

	public boolean cK(String userId) {
		AcAccount ac = this.uJ.get(userId);
		return ac != null;
	}

	public List<AcAccount> get(String username) {
		return this.uJ.c(username);
	}

	public Result<AcAccount> c(String unitId, String username, String password, boolean md5) {
		Result result = new Result();
		AcAccount acAccount = new AcAccount();
		acAccount.setUnitId(unitId);
		acAccount.setUsername(username);
		acAccount.setPasswd(md5 ? password : DigestUtils.md5Hex(password));
		List accounts = this.uJ.findAccoutByCondition(acAccount);
		AcAccount account = accounts.size() > 0 ? (AcAccount) accounts.get(0) : null;
		Long errorTimes = Long.valueOf(0L);
		if (account == null) {
			logger.info("登录：帐号不存在!");
			result.setResult("error");
			result.setMsg("用户名或密码错误!");
		} else if (account.getIsvalid().intValue() == com.nis.comm.enums.h.fQ.getCode().intValue()) {
			logger.info("登录：您的账号未激活，请联系管理员!");
			result.setResult("error_p");
			result.setMsg("您的账号未激活，请联系管理员!");
			errorTimes = Long.valueOf(0L);
			account.setErrorTimes(errorTimes);
			result.setData(account);
		} else {
			List roles = this.h.findByUserid(account.getUserId());
			if (roles.size() > 0) {
				account.setRoles(roles);
				account.setRoleCur((AcRole) roles.get(0));
			}

			if (account.getUnitId() != null && !"0".equals(account.getUnitId())) {
				account.setUnitName(this.se.getName(account.getUnitId()));
			}

			account.setErrorTimes(errorTimes);
			result.setData(account);
			result.setResult("success");
			this.uJ.updateLoginInfo(account.getUserId(), Long.valueOf(0L));
			this.aV.a(account.getUnitId(), account.getUsername(), ah.iC, af.iv, ag.ix, account.getUserId(),
					new Object[]{account});
		}

		return result;
	}

	public void f(AcAccount account) {
		this.uJ.update(account);
	}

	public Result<AcAccount> a(String username, String password, boolean md5, String unitId) {
		Result result = new Result();
		AcAccount account = null;
		if (unitId == null) {
			List errorTimes = this.uJ.c(username);
			if (errorTimes != null && errorTimes.size() > 1) {
				logger.info("登录：用户名称重复");
				result.setResult("repeat");
				result.setMsg("登录账号重复");
				result.setExtraValue(username);
				return result;
			}

			if (errorTimes.size() > 0 && ab.bb(username)) {
				logger.info("登录：登陆名称不能为中文");
				result.setResult("repeat");
				result.setMsg("登录账号中文");
				result.setExtraValue(username);
				return result;
			}

			account = errorTimes.size() > 0 ? (AcAccount) errorTimes.get(0) : null;
		} else {
			account = this.uJ.a(username, unitId);
		}

		Long errorTimes1 = Long.valueOf(0L);
		if (account == null) {
			logger.info("登录：帐号不存在!");
			result.setResult("error");
			result.setMsg("用户名或密码错误!");
		} else if (account.getIsvalid() != null && account.getIsvalid().intValue() == com.nis.comm.enums.h.fQ.getCode().intValue()) {
			logger.info("登录：您的账号未激活，请联系管理员!");
			result.setResult("error_p");
			result.setMsg("您的账号未激活，请联系管理员!");
			errorTimes1 = Long.valueOf(0L);
			account.setErrorTimes(errorTimes1);
			result.setData(account);
		} else if (!account.getPasswd().equals(md5 ? password : DigestUtils.md5Hex(password))) {
			logger.info("登录：密码输入错误!");
			result.setResult("error");
			result.setMsg("用户名或密码错误!");
			errorTimes1 = Long.valueOf(account.getErrorTimes() == null ? 1L : account.getErrorTimes().longValue() + 1L);
			account.setErrorTimes(errorTimes1);
			result.setData(account);
			this.uJ.updateLoginInfo(account.getUserId(), errorTimes1);
		} else {
			if (account.getValidDate() == null) {
				logger.info("登录：帐号未设置有效期!");
				result.setResult("error");
				result.setMsg("此帐号未设置有效期!");
				errorTimes1 = Long
						.valueOf(account.getErrorTimes() == null ? 1L : account.getErrorTimes().longValue() + 1L);
				account.setErrorTimes(errorTimes1);
				result.setData(account);
				return result;
			}

			long roles = account.getValidDate().getTime();
			long now = (new Date()).getTime();
			if (roles <= now) {
				logger.info("登录：帐号已过期!");
				result.setResult("error");
				result.setMsg("此帐号已到使用期限，已停用!");
				errorTimes1 = Long
						.valueOf(account.getErrorTimes() == null ? 1L : account.getErrorTimes().longValue() + 1L);
				account.setErrorTimes(errorTimes1);
				result.setData(account);
				return result;
			}

			List roles1 = this.h.findByUserid(account.getUserId());
			if (roles1.size() > 0) {
				account.setRoles(roles1);
				account.setRoleCur((AcRole) roles1.get(0));
			}

			if (account.getUnitId() != null && !"0".equals(account.getUnitId())) {
				account.setUnitName(this.se.getName(account.getUnitId()));
			}

			account.setErrorTimes(errorTimes1);
			result.setData(account);
			result.setResult("success");
			AsyncLogin t = new AsyncLogin(this, account);
			t.start();
		}

		return result;
	}

	public Result<AcAccount> a(String username, String password, boolean md5, String unitId, String userId) {
		Result result = new Result();
		if (userId == null) {
			logger.info("登录：帐号不存在!");
			result.setResult("error");
			result.setMsg("用户id为空!");
			return result;
		} else {
			AcAccount account = this.uJ.get(userId);
			if (account == null) {
				logger.info("登录：帐号不存在!");
				result.setResult("error");
				result.setMsg("用户名或密码错误!");
			} else {
				List roles = this.h.findByUserid(account.getUserId());
				if (roles.size() > 0) {
					account.setRoles(roles);
					account.setRoleCur((AcRole) roles.get(0));
				}

				result.setResult("success");
			}

			this.aV.a(account.getUnitId(), account.getUsername(), ah.iC, af.iv, ag.ix, account.getUserId(),
					new Object[]{account});
			result.setData(account);
			return result;
		}
	}

	public Result<AcAccount> b(String username, String password, String unitId, boolean base64) {
		Result result = new Result();
		if (username == null) {
			logger.info("登录：帐号不存在!");
			result.setResult("error");
			result.setMsg("用户id为空!");
			return result;
		} else {
			AcAccount acAccount = this.f.a(unitId, username, password, base64);
			if (acAccount == null) {
				logger.info("登录：帐号不存在!");
				result.setResult("error");
				result.setMsg("用户名或密码错误!");
				return result;
			} else {
				AcRole role = acAccount.getRoleCur();
				acAccount.setRoleCur(role);
				result.setData(acAccount);
				return result;
			}
		}
	}

	public Result<AcAccount> P(String username, String ticket) {
		Result result = new Result();
		if (username == null) {
			logger.info("登录：帐号不存在!");
			result.setResult("error");
			result.setMsg("用户id为空!");
			return result;
		} else {
			String ticketCode = this.j.findByParamCode(Param.NIS_INFO_TICKET);
			if (!ticketCode.equals(ticket)) {
				logger.info("ticket验证失败!");
				result.setResult("error");
				result.setMsg("ticket验证失败!");
				return result;
			} else {
				AcAccount account = this.uJ.b(username);
				if (account != null) {
					List doctor1 = this.h.findByUserid(account.getUserId());
					if (doctor1.size() > 0) {
						account.setRoleCur((AcRole) doctor1.get(0));
					}

					result.setData(account);
					return result;
				} else {
					Doctor doctor = this.f.get(username);
					AcAccount acAccount = this.f.c(doctor);
					if (acAccount == null) {
						logger.info("用户无效，请联系管理员！");
						result.setResult("error");
						result.setMsg("用户无效，请联系管理员！");
						result.setExtraValue("0008");
						return result;
					} else {
						AcRole role = acAccount.getRoleCur();
						acAccount.setRoleCur(role);
						result.setData(acAccount);
						return result;
					}
				}
			}
		}
	}
}