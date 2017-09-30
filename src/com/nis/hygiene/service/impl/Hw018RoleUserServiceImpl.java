package com.nis.hygiene.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.f;
import com.nis.dict.service.SysDictService;
import com.nis.hygiene.dao.Hw018RoleUserDao;
import com.nis.hygiene.entity.Hw018RoleUser;
import com.nis.hygiene.service.Hw018RoleUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Hw018RoleUserServiceImpl implements Hw018RoleUserService {
	@Autowired
	private Hw018RoleUserDao rR;
	@Autowired
	private SysDictService p;

	public void save(Hw018RoleUser hw018RoleUser) {
		this.rR.save(hw018RoleUser);
		this.p.updateLastTimebyCode("data_init", "JCRYSQ", f.getCurDate());
	}

	public void delete(String userId, String roleId) {
		this.rR.delete(userId, roleId);
		this.p.updateLastTimebyCode("data_init", "JCRYSQ", f.getCurDate());
	}

	public void update(Hw018RoleUser hw018RoleUser) {
		this.rR.update(hw018RoleUser);
		this.p.updateLastTimebyCode("data_init", "JCRYSQ", f.getCurDate());
	}

	public Hw018RoleUser get(String userId, String roleId) {
		return this.rR.get(userId, roleId);
	}

	public MyPage<Hw018RoleUser> a(Hw018RoleUser hw018RoleUser) {
		int total = this.rR.findHw018RoleUserCount(hw018RoleUser);
		List data = null;
		if (total > 0) {
			data = this.rR.findHw018RoleUser(hw018RoleUser);
		}

		return new MyPage(hw018RoleUser.getPage().intValue(), hw018RoleUser.getSize().intValue(), total, data);
	}

	public List<Hw018RoleUser> getAll() {
		return this.rR.getAll();
	}
}