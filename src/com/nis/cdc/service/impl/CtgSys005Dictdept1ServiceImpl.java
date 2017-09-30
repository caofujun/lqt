package com.nis.cdc.service.impl;

import com.nis.cdc.dao.CtgSys005Dictdept1Dao;
import com.nis.cdc.entity.CtgSys005Dictdept1;
import com.nis.cdc.service.CtgSys005Dictdept1Service;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CtgSys005Dictdept1ServiceImpl implements CtgSys005Dictdept1Service {
	@Autowired
	private CtgSys005Dictdept1Dao ef;

	public void save(CtgSys005Dictdept1 ctgSys005Dictdept1) {
		this.ef.save(ctgSys005Dictdept1);
	}

	public void delete(String deptid) {
		this.ef.delete(deptid);
	}

	public void update(CtgSys005Dictdept1 ctgSys005Dictdept1) {
		this.ef.update(ctgSys005Dictdept1);
	}

	public CtgSys005Dictdept1 get(String deptid) {
		return this.ef.get(deptid);
	}

	public MyPage<CtgSys005Dictdept1> a(CtgSys005Dictdept1 ctgSys005Dictdept1) {
		int total = this.ef.findCtgSys005Dictdept1Count(ctgSys005Dictdept1);
		List data = null;
		if (total > 0) {
			data = this.ef.findCtgSys005Dictdept1(ctgSys005Dictdept1);
		}

		return new MyPage(ctgSys005Dictdept1.getPage().intValue(), ctgSys005Dictdept1.getSize().intValue(), total,
				data);
	}

	public List<CtgSys005Dictdept1> getAll() {
		return this.ef.getAll();
	}
}