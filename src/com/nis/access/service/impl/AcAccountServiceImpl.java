package com.nis.access.service.impl;

import com.nis.access.dao.AcAccountDao;
import com.nis.access.entity.AcAccount;
import com.nis.access.entity.AcMenu;
import com.nis.access.entity.AcPrivilege;
import com.nis.access.service.AcAccountService;
import com.nis.access.service.AcAccountroleService;
import com.nis.access.service.AcMenuService;
import com.nis.access.service.AcPrivilegeService;
import com.nis.access.service.AcRoleService;
import com.nis.comm.constants.b;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.bg;
import com.nis.comm.enums.h;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.f;
import com.nis.comm.utils.z;
import com.nis.dict.service.SysDictService;
import com.nis.organization.service.DepService;
import com.nis.organization.service.DoctorService;
import com.nis.zg.service.Zg003YyzgService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AcAccountServiceImpl implements AcAccountService {
	private static final Logger logger = Logger.getLogger(AcAccountServiceImpl.class);
	@Autowired
	private AcAccountDao o;
	@Autowired
	private AcAccountroleService h;
	@Autowired
	private DepService e;
	@Autowired
	private DoctorService f;
	@Autowired
	private AcRoleService g;
	@Autowired
	private AcMenuService k;
	@Autowired
	private AcPrivilegeService i;
	@Autowired
	private SysDictService p;
	@Autowired
	private Zg003YyzgService q;

	public void save(AcAccount acAccount) {
		acAccount.setUserId(z.a(bg.mE));
		this.h.a(acAccount.getUserId(), acAccount.getExtRoleId());
		this.o.save(acAccount);
		this.p.updateLastTimebyCode("data_init", "GKKYH", com.nis.comm.utils.f.getCurDate());
	}

	@Transactional
	public void a(AcAccount acAccount, boolean md5) {
		acAccount.setUserId(acAccount.getPtUserId());
		acAccount.setPasswd(md5 ? acAccount.getPasswd() : DigestUtils.md5Hex(acAccount.getPasswd()));
		this.h.a(acAccount.getUserId(), acAccount.getExtRoleId());
		this.o.save(acAccount);
	}

	public String a(AcAccount acAccount) {
		acAccount.setUserId(z.a(bg.mE));
		if (this.get(acAccount.getUserId()) != null) {
			this.o.update(acAccount);
		} else {
			this.o.save(acAccount);
		}

		this.p.updateLastTimebyCode("data_init", "GKKYH", com.nis.comm.utils.f.getCurDate());
		return acAccount.getUserId();
	}

	public void delete(String userId) {
		this.o.delete(userId);
		this.p.updateLastTimebyCode("data_init", "GKKYH", com.nis.comm.utils.f.getCurDate());
	}

	public void update(AcAccount acAccount) {
		if (acAccount.getPtUserId() != null && !acAccount.getUserId().toString().equals(acAccount.getPtUserId())) {
			this.o.updateUserIdByUserId(acAccount.getUserId(), acAccount.getPtUserId());
			acAccount.setUserId(acAccount.getPtUserId());
		}

		if (acAccount.getExtRoleId() != null) {
			this.h.a(acAccount.getUserId(), acAccount.getExtRoleId());
		}

		this.o.update(acAccount);
		this.p.updateLastTimebyCode("data_init", "GKKYH", com.nis.comm.utils.f.getCurDate());
	}

	public void updateLoginInfo(String userId, Long errorTimes) {
		this.o.updateLoginInfo(userId, errorTimes);
	}

	public AcAccount get(String userId) {
		return this.o.get(userId);
	}

	public MyPage<AcAccount> b(AcAccount acAccount) {
		int total = this.o.findAcAccountCountByCondition(acAccount);
		List data = null;
		if (total > 0) {
			data = this.o.findAcAccountByCondition(acAccount);
			data = this.a(data);
		}

		return new MyPage(acAccount.getPage().intValue(), acAccount.getSize().intValue(), total, data);
	}

	private List<AcAccount> a(List<AcAccount> data) {
		Iterator arg2 = data.iterator();

		while (arg2.hasNext()) {
			AcAccount account = (AcAccount) arg2.next();
			String e;
			if (ab.isNotEmpty(account.getDepNo())) {
				e = this.e.H(account.getUnitId(), account.getDepNo());
				account.setShowDepName(e);
			}

			if (account.getUserId() != null) {
				try {
					e = this.g.getName(account.getUserId());
					account.setShowRoleName(e);
				} catch (Exception arg4) {
					logger.warn("查询帐号信息有异常：数据异常，帐号对应角色为空，请检查数据！");
					account.setShowRoleName("");
				}
			}
		}

		return data;
	}

	public AcAccount b(String username) {
		List list = this.o.getUsername(username, (String) null);
		return list.size() > 0 ? (AcAccount) list.get(0) : null;
	}

	public AcAccount findAccountByRealnameNameAndEmail(String realname, String email) {
		return this.o.findAccountByRealnameNameAndEmail(realname, email);
	}

	public AcAccount findAccountByMobilenum(String mobilenum) {
		return this.o.findAccountByMobilenum(mobilenum);
	}

	public AcAccount d(String email) {
		List accountList = this.o.findAccountByEmail(email);
		return accountList.size() > 0 ? (AcAccount) accountList.get(0) : null;
	}

	public void updatePwdByUserId(String userId, String passwd) {
		this.o.updatePwdByUserId(userId, passwd);
	}

	public Result<String> a(AcAccount acAccount, AcAccount acAccount1, String sourceType) {
		List acc = this.o.findAccExtis(acAccount);
		boolean stillInsert = true;
		Result result = new Result();
		if (acc != null && acc.size() > 0 && acAccount1 == null) {
			Iterator arg7 = acc.iterator();

			while (arg7.hasNext()) {
				AcAccount a = (AcAccount) arg7.next();
				if ("1".equals(a.getSource())) {
					this.q.delete(a.getId());
				} else {
					stillInsert = false;
					result.setResult("error_extis");
					result.setMsg("用户名、手机号、或邮箱已存在！");
				}
			}
		}

		if (stillInsert) {
			if (StringUtils.isBlank(acAccount.getUserId())) {
				if (ab.isEmpty(acAccount.getPasswd())) {
					acAccount.setPasswd(DigestUtils.md5Hex(acAccount.getUsername()));
				} else {
					acAccount.setPasswd(DigestUtils.md5Hex(acAccount.getPasswd()));
				}

				this.save(acAccount);
			} else {
				if (ab.isEmpty(acAccount.getPasswd())) {
					acAccount.setPasswd(acAccount1.getPasswd());
				} else {
					acAccount.setPasswd(DigestUtils.md5Hex(acAccount.getPasswd()));
				}

				this.update(acAccount);
			}

			result.setResult("success");
		}

		return result;
	}

	public List<AcAccount> c(AcAccount acAccount) {
		List acc = this.o.findAccExtis(acAccount);
		return acc;
	}

	public List<AcAccount> findRealname(String realname) {
		List acc = this.o.findRealname(realname);
		return acc;
	}

	public String getName(String username) {
		AcAccount acAccount = this.b(username);
		return acAccount == null ? null : acAccount.getRealname();
	}

	public List<AcAccount> findByUnitId(String unitId) {
		return this.o.findByUnitId(unitId);
	}

	public List<AcAccount> c(String loginUser) {
		return this.o.getUsername(loginUser.toLowerCase(), (String) null);
	}

	public List<AcAccount> findAccoutByCondition(AcAccount acAccount) {
		return this.o.findAccoutByCondition(acAccount);
	}

	public AcAccount a(String username, String unitId) {
		List list = this.o.getUsername(username.toLowerCase(), unitId);
		return list.size() > 0 ? (AcAccount) list.get(0) : null;
	}

	public void a(String userId, String havegrants, String delDepIds) {
		String scopeInfo = "";
		AcAccount acAccount = this.o.get(userId);
		if (StringUtils.isNotBlank(acAccount.getScopeInfo())) {
			scopeInfo = acAccount.getScopeInfo();
		}

		String[] delDepId;
		if (StringUtils.isNotBlank(havegrants)) {
			delDepId = havegrants.split(",");

			for (int depId = 0; depId < delDepId.length; ++depId) {
				if ((scopeInfo + ",").indexOf(delDepId[depId] + ",") < 0) {
					if (!scopeInfo.endsWith(",") && scopeInfo.length() != 0) {
						scopeInfo = scopeInfo + "," + delDepId[depId];
					} else {
						scopeInfo = scopeInfo + delDepId[depId];
					}
				}
			}
		}

		if (StringUtils.isNotBlank(scopeInfo) && StringUtils.isNotBlank(delDepIds)) {
			delDepId = delDepIds.split(",");
			if (delDepId != null && delDepId.length > 0) {
				scopeInfo = "," + scopeInfo + ",";
				String[] arg9 = delDepId;
				int arg8 = delDepId.length;

				for (int arg7 = 0; arg7 < arg8; ++arg7) {
					String arg10 = arg9[arg7];
					if (scopeInfo.indexOf("," + arg10 + ",") >= 0) {
						scopeInfo = scopeInfo.replace("," + arg10 + ",", ",");
					}
				}

				if (scopeInfo.length() > 1) {
					scopeInfo = scopeInfo.substring(scopeInfo.indexOf(",") + 1, scopeInfo.lastIndexOf(","));
				} else {
					scopeInfo = "";
				}
			}
		}

		acAccount.setScopeInfo(scopeInfo);
		this.o.update(acAccount);
	}

	public List<AcMenu> a(AcAccount account, String ownership) {
		AcMenu acMemu = new AcMenu();
		acMemu.setOwnership(ownership);
		acMemu.setIsvalid(com.nis.comm.enums.h.fP.getCode().toString());
		acMemu.setUnitId(account.getUnitId());
		List allList = this.k.d(acMemu);
		if (b.eB.equals(account.getUsername())) {
			return allList;
		} else {
			ArrayList list = new ArrayList();
			if (account.getRoleCur() != null) {
				List apList = this.i.findByRoleid(account.getUserId());
				Iterator arg7 = allList.iterator();

				while (arg7.hasNext()) {
					AcMenu menu = (AcMenu) arg7.next();
					Iterator arg9 = apList.iterator();

					while (arg9.hasNext()) {
						AcPrivilege ap = (AcPrivilege) arg9.next();
						if (com.nis.comm.enums.h.fP.getCode().toString().equals(ap.getHavegrant())
								&& ap.getAccessId().equals(menu.getMenuId())) {
							list.add(menu);
						}
					}
				}
			}

			return list;
		}
	}

	public int checkUniqueExtis(AcAccount acAccount) {
		return this.o.checkUniqueExtis(acAccount);
	}

	public List<AcAccount> findByDeptIds(List<String> deptIds) {
		return this.o.findByDeptIds(deptIds);
	}
}