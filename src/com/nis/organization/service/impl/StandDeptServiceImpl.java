package com.nis.organization.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.organization.dao.StandDeptDao;
import com.nis.organization.entity.StandDept;
import com.nis.organization.service.StandDeptService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StandDeptServiceImpl implements StandDeptService {
	@Autowired
	private StandDeptDao wc;

	public void save(StandDept standDept) {
		this.wc.save(standDept);
	}

	public void delete(String deptId) {
		this.wc.delete(deptId);
	}

	public void update(StandDept standDept) {
		this.wc.update(standDept);
	}

	public StandDept get(String deptId) {
		return this.wc.get(deptId);
	}

	public MyPage<StandDept> a(StandDept standDept) {
		int total = this.wc.findStandDeptCount(standDept);
		List data = null;
		if (total > 0) {
			data = this.wc.findStandDept(standDept);
		}

		return new MyPage(standDept.getPage().intValue(), standDept.getSize().intValue(), total, data);
	}

	public List<StandDept> getAll() {
		return this.wc.getAll();
	}

	public List<StandDept> getStandDept(StandDept standDept) {
		return this.wc.getStandDept(standDept);
	}
}