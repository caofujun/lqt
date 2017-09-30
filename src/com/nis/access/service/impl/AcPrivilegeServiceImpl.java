package com.nis.access.service.impl;

import com.nis.access.dao.AcPrivilegeDao;
import com.nis.access.entity.AcMenu;
import com.nis.access.entity.AcPrivilege;
import com.nis.access.service.AcPrivilegeService;
import com.nis.comm.enums.b;
import com.nis.comm.enums.bg;
import com.nis.comm.enums.c;
import com.nis.comm.enums.h;
import com.nis.comm.utils.ab;
import com.nis.comm.utils.z;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AcPrivilegeServiceImpl implements AcPrivilegeService {
	@Autowired
	private AcPrivilegeDao t;

	@Transactional(rollbackFor = {Exception.class})
	public void a(String roleId, String[] fungrants, String[] havegrants) {
		this.t.deleteRoleId(roleId);
		ArrayList allMids = new ArrayList();
		ArrayList funList = new ArrayList();
		if (fungrants != null && fungrants.length > 0) {
			funList.addAll(Arrays.asList(fungrants));
		}

		allMids.addAll(funList);
		ArrayList haveList = new ArrayList();
		if (havegrants != null && havegrants.length > 0) {
			haveList.addAll(Arrays.asList(havegrants));
			String[] arg9 = havegrants;
			int menuId = havegrants.length;

			for (int acPrivilege = 0; acPrivilege < menuId; ++acPrivilege) {
				String acPrivileges = arg9[acPrivilege];
				if (!allMids.contains(acPrivileges)) {
					allMids.add(acPrivileges);
				}
			}
		}

		if (allMids.size() > 0) {
			ArrayList arg10 = new ArrayList();
			AcPrivilege arg11 = null;

			for (Iterator arg13 = allMids.iterator(); arg13.hasNext(); arg10.add(arg11)) {
				String arg12 = (String) arg13.next();
				arg11 = new AcPrivilege();
				arg11.setId(z.a(bg.mD));
				arg11.setRoleId(roleId);
				arg11.setAccessType(b.fu.getCode().toString());
				arg11.setAccessId(arg12);
				if (funList.contains(arg12)) {
					arg11.setOperation(c.fx.getCode().toString());
				} else {
					arg11.setOperation(c.fy.getCode().toString());
				}

				if (haveList.contains(arg12)) {
					arg11.setHavegrant(h.fP.getCode().toString());
				} else {
					arg11.setHavegrant(h.fQ.getCode().toString());
				}
			}

			if (arg10.size() > 0) {
				this.t.save(arg10);
			}
		}

	}

	public List<AcPrivilege> findByRoleid(String roleId) {
		return this.t.findByRoleid(roleId);
	}

	public List<AcMenu> findMenuByRoleid(String roleId, String userId) {
		return this.t.findMenuByRoleid(roleId, userId);
	}

	public void save(List<AcPrivilege> acPrivileges) {
		this.t.save(acPrivileges);
	}

	public void saveList(List<AcPrivilege> acPrivileges, String roleId) {
		if (ab.isNotEmpty(roleId)) {
			this.t.deleteReportAuthByRoleId(roleId);
			if (acPrivileges != null && !acPrivileges.isEmpty()) {
				this.t.save(acPrivileges);
			}
		}

	}
}