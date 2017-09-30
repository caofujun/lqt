package com.nis.analysis.service.impl;

import com.nis.analysis.dao.Gr019YsgrmxTestDao;
import com.nis.analysis.entity.Gr019YsgrmxTest;
import com.nis.analysis.service.Gr019YsgrmxTestService;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Gr019YsgrmxTestServiceImpl implements Gr019YsgrmxTestService {
	@Autowired
	private Gr019YsgrmxTestDao cv;

	public void save(Gr019YsgrmxTest gr019YsgrmxTest) {
		this.cv.save(gr019YsgrmxTest);
	}

	public void delete(String zyid) {
		this.cv.delete(zyid);
	}

	public void update(Gr019YsgrmxTest gr019YsgrmxTest) {
		this.cv.update(gr019YsgrmxTest);
	}

	public Gr019YsgrmxTest get(String zyid) {
		return this.cv.get(zyid);
	}

	public MyPage<Gr019YsgrmxTest> a(Gr019YsgrmxTest gr019YsgrmxTest) {
		int total = this.cv.findGr019YsgrmxTestCount(gr019YsgrmxTest);
		List data = null;
		if (total > 0) {
			data = this.cv.findGr019YsgrmxTest(gr019YsgrmxTest);
		}

		return new MyPage(gr019YsgrmxTest.getPage().intValue(), gr019YsgrmxTest.getSize().intValue(), total, data);
	}

	public List<Gr019YsgrmxTest> getAll() {
		return this.cv.getAll();
	}

	public void saveList(List<Gr019YsgrmxTest> gr019List) {
		this.cv.saveList(gr019List);
	}

	public List<Gr019YsgrmxTest> getbyZyid(String zyid) {
		return this.cv.getbyZyid(zyid);
	}
}