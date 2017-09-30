package com.nis.analysis.service.impl;

import com.nis.analysis.dao.Gr018BcgrysTestDao;
import com.nis.analysis.entity.Gr018BcgrysTest;
import com.nis.analysis.service.Gr018BcgrysTestService;
import com.nis.comm.entity.MyPage;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Gr018BcgrysTestServiceImpl implements Gr018BcgrysTestService {
	@Autowired
	private Gr018BcgrysTestDao ct;

	public void save(Gr018BcgrysTest gr018Bcgrys) {
		this.ct.save(gr018Bcgrys);
	}

	public void saveList(List<Gr018BcgrysTest> gr018BcgrysList) {
		this.ct.saveList(gr018BcgrysList);
	}

	public void delete(String zyid, String bcid) {
		this.ct.delete(zyid, bcid);
	}

	public void update(Gr018BcgrysTest gr018Bcgrys) {
		this.ct.update(gr018Bcgrys);
	}

	public Gr018BcgrysTest get(String zyid) {
		return this.ct.get(zyid);
	}

	public MyPage<Gr018BcgrysTest> a(Gr018BcgrysTest gr018Bcgrys) {
		int total = this.ct.findGr018BcgrysCount(gr018Bcgrys);
		List data = null;
		if (total > 0) {
			data = this.ct.findGr018Bcgrys(gr018Bcgrys);
		}

		return new MyPage(gr018Bcgrys.getPage().intValue(), gr018Bcgrys.getSize().intValue(), total, data);
	}

	public List<Gr018BcgrysTest> getAll() {
		return this.ct.getAll();
	}

	public Date getMonitorPatientBcLastAt() {
		return this.ct.getMonitorPatientBcLastAt();
	}
}