package com.nis.patient.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.patient.dao.St012ZkjlDao;
import com.nis.patient.entity.St012Zkjl;
import com.nis.patient.service.St012ZkjlService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class St012ZkjlServiceImpl implements St012ZkjlService {
	@Autowired
	private St012ZkjlDao tu;

	public void save(St012Zkjl st012Zkjl) {
		this.tu.save(st012Zkjl);
	}

	public void delete(String id) {
		this.tu.delete(id);
	}

	public void update(St012Zkjl st012Zkjl) {
		this.tu.update(st012Zkjl);
	}

	public St012Zkjl get(String id) {
		return this.tu.get(id);
	}

	public MyPage<St012Zkjl> a(St012Zkjl st012Zkjl) {
		int total = this.tu.findSt012ZkjlCount(st012Zkjl);
		List data = null;
		if (total > 0) {
			data = this.tu.findSt012Zkjl(st012Zkjl);
		}

		return new MyPage(st012Zkjl.getPage().intValue(), st012Zkjl.getSize().intValue(), total, data);
	}

	public List<St012Zkjl> getAll() {
		return this.tu.getAll();
	}

	public List<St012Zkjl> findInAndOutDepList(String zyid) {
		return this.tu.findInAndOutDepList(zyid);
	}

	public Date getMonitorPatientInZkxxLastAt(String deptId) {
		return this.tu.getMonitorPatientInZkxxLastAt(deptId);
	}

	public Date getMonitorPatientOutZkxxLastAt(String deptId) {
		return this.tu.getMonitorPatientOutZkxxLastAt(deptId);
	}
}