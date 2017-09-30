package com.nis.hygiene.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.f;
import com.nis.dict.service.SysDictService;
import com.nis.hygiene.dao.Hw016RoleDao;
import com.nis.hygiene.dao.Hw017RoleRightDao;
import com.nis.hygiene.dao.Hw018RoleUserDao;
import com.nis.hygiene.entity.Hw016Role;
import com.nis.hygiene.service.Hw016RoleService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Hw016RoleServiceImpl implements Hw016RoleService {
	@Autowired
	private Hw016RoleDao rU;
	@Autowired
	private Hw017RoleRightDao rV;
	@Autowired
	private Hw018RoleUserDao rR;
	@Autowired
	private SysDictService p;

	public void save(Hw016Role hw016Role) {
		if (hw016Role.getLastAt() == null) {
			hw016Role.setLastAt(new Date());
		}

		if (hw016Role.getFlag() == null) {
			hw016Role.setFlag(Integer.valueOf(1));
		}

		this.rU.save(hw016Role);
		this.p.updateLastTimebyCode("data_init", "JCRYSQ", f.getCurDate());
	}

	public void delete(String roleId) {
		this.rU.delete(roleId);
		this.p.updateLastTimebyCode("data_init", "JCRYSQ", f.getCurDate());
	}

	public void update(Hw016Role hw016Role) {
		this.rU.update(hw016Role);
		this.p.updateLastTimebyCode("data_init", "JCRYSQ", f.getCurDate());
	}

	public Hw016Role get(String roleId) {
		return this.rU.get(roleId);
	}

	public MyPage<Hw016Role> a(Hw016Role hw016Role) {
		int total = this.rU.findHw016RoleCount(hw016Role);
		List data = null;
		if (total > 0) {
			data = this.rU.findHw016Role(hw016Role);
		}

		return new MyPage(hw016Role.getPage().intValue(), hw016Role.getSize().intValue(), total, data);
	}

	public List<Hw016Role> getAll() {
		return this.rU.getAll();
	}

	public List<Hw016Role> findList(Hw016Role hw016Role) {
		return this.rU.findList(hw016Role);
	}

	public void updHw016Role(Hw016Role hw016Role) {
		hw016Role.setLastAt(new Date());
		this.rU.updHw016Role(hw016Role);
		this.p.updateLastTimebyCode("data_init", "JCRYSQ", f.getCurDate());
	}

	@Transactional(rollbackFor = {Exception.class})
	public void bu(String roleId) {
		this.delete(roleId);
		this.rV.delByRoleId(roleId);
		this.rR.delByRoleId(roleId);
		this.p.updateLastTimebyCode("data_init", "JCRYSQ", f.getCurDate());
	}

	public List<Hw016Role> findListJoinHw018(String userId) {
		return this.rU.findListJoinHw018(userId);
	}
}