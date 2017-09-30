package com.nis.access.service.impl;

import com.nis.access.dao.AcAccountroleDao;
import com.nis.access.entity.AcAccountrole;
import com.nis.access.entity.AcRole;
import com.nis.access.service.AcAccountroleService;
import com.nis.access.service.AcRoleService;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AcAccountroleServiceImpl implements AcAccountroleService {
	@Autowired
	private AcAccountroleDao r;
	@Autowired
	private AcRoleService g;

	public void a(String userId, List<String> roleids) {
		this.r.deleteByUserid(userId);
		Iterator arg3 = roleids.iterator();

		while (arg3.hasNext()) {
			String roleid = (String) arg3.next();
			AcAccountrole accRole = new AcAccountrole();
			accRole.setId(z.a(bg.mB));
			accRole.setRoleId(roleid);
			accRole.setUserId(userId);
			this.r.save(accRole);
		}

	}

	public void delete(String id) {
		this.r.delete(id);
	}

	public void update(AcAccountrole acAccountrole) {
		this.r.update(acAccountrole);
	}

	public AcAccountrole get(String id) {
		return this.r.get(id);
	}

	public MyPage<AcAccountrole> a(AcAccountrole acAccountrole) {
		int total = this.r.findAcAccountroleCount(acAccountrole);
		List data = null;
		if (total > 0) {
			data = this.r.findAcAccountrole(acAccountrole);
		}

		return new MyPage(acAccountrole.getPage().intValue(), acAccountrole.getSize().intValue(), total, data);
	}

	public List<AcAccountrole> getAll() {
		return this.r.getAll();
	}

	public List<AcRole> findByUserid(String userId) {
		ArrayList ars = new ArrayList();
		List list = this.r.findByUserid(userId);
		Iterator arg4 = list.iterator();

		while (arg4.hasNext()) {
			String roleId = (String) arg4.next();
			AcRole role = this.g.get(roleId);
			if (role != null) {
				ars.add(role);
			}
		}

		return ars;
	}

	public int getByRoleId(String roleId) {
		return this.r.getByRoleId(roleId);
	}
}