package com.nis.hygiene.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.f;
import com.nis.dict.service.SysDictService;
import com.nis.hygiene.dao.Hw009KssqDao;
import com.nis.hygiene.entity.Hw009Kssq;
import com.nis.hygiene.service.Hw009KssqService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Hw009KssqServiceImpl implements Hw009KssqService {
	@Autowired
	private Hw009KssqDao rS;
	@Autowired
	private SysDictService p;

	public void save(Hw009Kssq hw009Kssq) {
		this.rS.save(hw009Kssq);
		this.p.updateLastTimebyCode("data_init", "JCRYSQ", f.getCurDate());
	}

	public void delete(String userId, String deptId) {
		this.rS.delete(userId, deptId);
		this.p.updateLastTimebyCode("data_init", "JCRYSQ", f.getCurDate());
	}

	public void update(Hw009Kssq hw009Kssq) {
		this.rS.update(hw009Kssq);
		this.p.updateLastTimebyCode("data_init", "JCRYSQ", f.getCurDate());
	}

	public Hw009Kssq get(String userId, String deptId) {
		return this.rS.get(userId, deptId);
	}

	public MyPage<Hw009Kssq> a(Hw009Kssq hw009Kssq) {
		int total = this.rS.findHw009KssqCount(hw009Kssq);
		List data = null;
		if (total > 0) {
			data = this.rS.findHw009Kssq(hw009Kssq);
		}

		return new MyPage(hw009Kssq.getPage().intValue(), hw009Kssq.getSize().intValue(), total, data);
	}

	public List<Hw009Kssq> getAll() {
		return this.rS.getAll();
	}

	public List<Hw009Kssq> queryList(Hw009Kssq hw009Kssq) {
		return this.rS.queryList(hw009Kssq);
	}

	public List<Hw009Kssq> findAccreditDept(Hw009Kssq hw009Kssq) {
		return this.rS.findAccreditDept(hw009Kssq);
	}
}