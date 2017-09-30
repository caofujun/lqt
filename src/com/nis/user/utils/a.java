package com.nis.user.utils;

import com.nis.access.entity.AcAccount;
import com.nis.access.entity.AcMenu;
import com.nis.access.entity.AcRole;
import com.nis.comm.entity.LoginUser;
import java.util.Iterator;
import java.util.List;

public class a {
	public static LoginUser d(AcAccount account) {
		LoginUser loginUser = new LoginUser();
		loginUser.setUserId(String.valueOf(account.getUserId()));
		loginUser.setUsername(account.getUsername());
		loginUser.setUnitId(account.getUnitId());
		loginUser.setUnitName(account.getUnitName());
		loginUser.setDocNo(account.getDocNo());
		loginUser.setDepNo(account.getDepNo());
		loginUser.setRealname(account.getRealname());
		loginUser.setDataScope(account.getDataScope());
		loginUser.setScopeInfo(account.getScopeInfo());
		loginUser.setPhotoPath(account.getPhotoPath());
		AcRole role = account.getRoleCur();
		if (role != null) {
			loginUser.setRoleId(role.getRoleId());
			loginUser.setRoleName(role.getName());
			loginUser.setRoleScope(role.getRoleScope());
		}

		return loginUser;
	}

	public static boolean h(List<AcMenu> menus, String menuNo) {
		Iterator arg2 = menus.iterator();

		while (arg2.hasNext()) {
			AcMenu acMenu = (AcMenu) arg2.next();
			if (acMenu.getMenuNo().equals(menuNo)) {
				return true;
			}
		}

		return false;
	}
}