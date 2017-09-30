package com.nis.access.service.impl;

import com.nis.access.cache.AcRoleCache;
import com.nis.access.dao.AcRoleDao;
import com.nis.access.entity.AcAccount;
import com.nis.access.entity.AcMenu;
import com.nis.access.entity.AcPrivilege;
import com.nis.access.entity.AcRole;
import com.nis.access.service.AcAccountroleService;
import com.nis.access.service.AcMenuService;
import com.nis.access.service.AcPrivilegeService;
import com.nis.access.service.AcRoleService;
import com.nis.comm.constants.b;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import com.nis.comm.enums.bg;
import com.nis.comm.enums.h;
import com.nis.comm.utils.z;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AcRoleServiceImpl implements AcRoleService {
	@Autowired
	private AcRoleDao a;
	@Autowired
	private AcRoleCache v;
	@Autowired
	private AcMenuService k;
	@Autowired
	private AcPrivilegeService i;
	@Autowired
	private AcAccountroleService h;

	public void save(AcRole acRole) {
		acRole.setRoleId(z.a(bg.mA));
		acRole.setCreateTime(new Date());
		this.a.save(acRole);
	}

	public Result<String> i(String roleId) {
		Result result = new Result();
		int num = this.h.getByRoleId(roleId);
		if (num > 0) {
			result.setResult("error_exist");
			result.setMsg("该角色已经有用户使用，不能删除");
		} else {
			this.a.delete(roleId);
			this.v.a(roleId);
			result.setResult("success");
		}

		return result;
	}

	public void update(AcRole acRole) {
		this.a.update(acRole);
		this.v.a(acRole.getRoleId());
	}

	public AcRole get(String roleId) {
		return this.v.get(roleId);
	}

	public MyPage<AcRole> a(AcRole acRole) {
		int total = this.a.findAcRoleCount(acRole);
		List data = null;
		if (total > 0) {
			data = this.a.findAcRole(acRole);
		}

		return new MyPage(acRole.getPage().intValue(), acRole.getSize().intValue(), total, data);
	}

	public List<AcRole> getAll() {
		return this.a.getAll();
	}

	public List<AcRole> getRoleByUnitId(String unitId, String acType) {
		return this.a.getRoleByUnitId(unitId, acType);
	}

	public String getName(String userId) {
		List roles = this.h.findByUserid(userId);
		String roleNames = null;
		if (roles != null && roles.size() > 0) {
			StringBuffer sb = new StringBuffer("");
			Iterator arg5 = roles.iterator();

			while (arg5.hasNext()) {
				AcRole acRole = (AcRole) arg5.next();
				sb.append(acRole.getName()).append(",");
			}

			if (sb.length() > 0) {
				sb.deleteCharAt(sb.length() - 1);
			}

			roleNames = sb.toString();
		}

		return roleNames;
	}

	public List<AcMenu> a(AcAccount account, String ownership) {
		AcMenu acMemu = new AcMenu();
		acMemu.setOwnership(ownership);
		acMemu.setIsvalid(com.nis.comm.enums.h.fP.getCode().toString());
		acMemu.setUnitId(account.getUnitId());
		List allList = this.k.d(acMemu);
		if (!b.eB.equals(account.getUsername()) && !b.eC.equals(account.getUsername())) {
			ArrayList list = new ArrayList();
			if (account.getRoleCur() != null) {
				List apList = this.i.findByRoleid(account.getRoleCur().getRoleId());
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
		} else {
			return allList;
		}
	}
}