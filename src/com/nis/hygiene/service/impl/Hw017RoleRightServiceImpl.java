package com.nis.hygiene.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.f;
import com.nis.dict.service.SysDictService;
import com.nis.hygiene.dao.Hw017RoleRightDao;
import com.nis.hygiene.entity.Hw017RoleRight;
import com.nis.hygiene.service.Hw017RoleRightService;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Hw017RoleRightServiceImpl implements Hw017RoleRightService {
	@Autowired
	private Hw017RoleRightDao rV;
	@Autowired
	private SysDictService p;

	public void save(Hw017RoleRight hw017RoleRight) {
		if (hw017RoleRight.getSelfAdd() == null) {
			hw017RoleRight.setSelfAdd(Integer.valueOf(0));
		}

		if (hw017RoleRight.getSelfView() == null) {
			hw017RoleRight.setSelfView(Integer.valueOf(0));
		}

		if (hw017RoleRight.getSelfResult() == null) {
			hw017RoleRight.setSelfResult(Integer.valueOf(0));
		}

		if (hw017RoleRight.getOtherView() == null) {
			hw017RoleRight.setOtherView(Integer.valueOf(0));
		}

		if (hw017RoleRight.getOtherAdd() == null) {
			hw017RoleRight.setOtherAdd(Integer.valueOf(0));
		}

		if (hw017RoleRight.getOtherResult() == null) {
			hw017RoleRight.setOtherResult(Integer.valueOf(0));
		}

		this.rV.save(hw017RoleRight);
		this.p.updateLastTimebyCode("data_init", "JCRYSQ", f.getCurDate());
	}

	public void delete(String roleId, String classId) {
		this.rV.delete(roleId, classId);
		this.p.updateLastTimebyCode("data_init", "JCRYSQ", f.getCurDate());
	}

	public void update(Hw017RoleRight hw017RoleRight) {
		if (hw017RoleRight.getSelfAdd() == null) {
			hw017RoleRight.setSelfAdd(Integer.valueOf(0));
		}

		if (hw017RoleRight.getSelfView() == null) {
			hw017RoleRight.setSelfView(Integer.valueOf(0));
		}

		if (hw017RoleRight.getSelfResult() == null) {
			hw017RoleRight.setSelfResult(Integer.valueOf(0));
		}

		if (hw017RoleRight.getOtherView() == null) {
			hw017RoleRight.setOtherView(Integer.valueOf(0));
		}

		if (hw017RoleRight.getOtherAdd() == null) {
			hw017RoleRight.setOtherAdd(Integer.valueOf(0));
		}

		if (hw017RoleRight.getOtherResult() == null) {
			hw017RoleRight.setOtherResult(Integer.valueOf(0));
		}

		this.rV.update(hw017RoleRight);
		this.p.updateLastTimebyCode("data_init", "JCRYSQ", f.getCurDate());
	}

	public Hw017RoleRight get(String roleId, String classId) {
		return this.rV.get(roleId, classId);
	}

	public MyPage<Hw017RoleRight> a(Hw017RoleRight hw017RoleRight) {
		int total = this.rV.findHw017RoleRightCount(hw017RoleRight);
		List data = null;
		if (total > 0) {
			data = this.rV.findHw017RoleRight(hw017RoleRight);
		}

		return new MyPage(hw017RoleRight.getPage().intValue(), hw017RoleRight.getSize().intValue(), total, data);
	}

	public List<Hw017RoleRight> getAll() {
		return this.rV.getAll();
	}

	public List<Hw017RoleRight> findList(Hw017RoleRight hw017RoleRight) {
		return this.rV.findList(hw017RoleRight);
	}

	@Transactional(rollbackFor = {Exception.class})
	public void g(List<Hw017RoleRight> hw017List, String roleId) {
		Iterator arg3 = hw017List.iterator();

		while (arg3.hasNext()) {
			Hw017RoleRight hw017RoleRight = (Hw017RoleRight) arg3.next();
			Hw017RoleRight hw017 = this.get(hw017RoleRight.getRoleId(), hw017RoleRight.getClassId());
			if (hw017 != null) {
				this.update(hw017RoleRight);
			} else {
				hw017RoleRight.setRoleId(roleId);
				this.save(hw017RoleRight);
			}
		}

	}
}