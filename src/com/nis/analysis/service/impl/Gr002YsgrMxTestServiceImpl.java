package com.nis.analysis.service.impl;

import com.nis.analysis.dao.Gr002YsgrMxTestDao;
import com.nis.analysis.entity.Gr002YsgrMxTest;
import com.nis.analysis.service.Gr002YsgrMxTestService;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Gr002YsgrMxTestServiceImpl implements Gr002YsgrMxTestService {
	@Autowired
	private Gr002YsgrMxTestDao cq;

	public void save(Gr002YsgrMxTest gr002YsgrMxTest) {
		gr002YsgrMxTest.setRegId(z.a(bg.mY));
		this.cq.save(gr002YsgrMxTest);
	}

	public void delete(String regId) {
		this.cq.delete(regId);
	}

	public void update(Gr002YsgrMxTest gr002YsgrMxTest) {
		this.cq.update(gr002YsgrMxTest);
	}

	public Gr002YsgrMxTest get(String regId) {
		return this.cq.get(regId);
	}

	public MyPage<Gr002YsgrMxTest> a(Gr002YsgrMxTest gr002YsgrMxTest) {
		int total = this.cq.findGr002YsgrMxTestCount(gr002YsgrMxTest);
		List data = null;
		if (total > 0) {
			data = this.cq.findGr002YsgrMxTest(gr002YsgrMxTest);
		}

		return new MyPage(gr002YsgrMxTest.getPage().intValue(), gr002YsgrMxTest.getSize().intValue(), total, data);
	}

	public List<Gr002YsgrMxTest> getAll() {
		return this.cq.getAll();
	}

	public void deleteByZyidState(String zyid) {
		this.cq.deleteByZyidState(zyid);
	}

	public List<Gr002YsgrMxTest> findByZyidInfectCode(String zyid, String infectCode) {
		return this.cq.findByZyidInfectCode(zyid, infectCode);
	}
}