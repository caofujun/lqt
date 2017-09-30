package com.nis.mdr.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.mdr.dao.Xn015TrlyjlDao;
import com.nis.mdr.entity.Xn015Trlyjl;
import com.nis.mdr.service.Xn015TrlyjlService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Xn015TrlyjlServiceImpl implements Xn015TrlyjlService {
	@Autowired
	private Xn015TrlyjlDao uj;

	public void save(Xn015Trlyjl xn015Trlyjl) {
		this.uj.save(xn015Trlyjl);
	}

	public void update(Xn015Trlyjl xn015Trlyjl) {
		this.uj.update(xn015Trlyjl);
	}

	public Xn015Trlyjl get(String testOrderNo, String pathoCode, String antiCode) {
		return this.uj.get(testOrderNo, pathoCode, antiCode);
	}

	public MyPage<Xn015Trlyjl> a(Xn015Trlyjl xn015Trlyjl) {
		int total = this.uj.findXn015TrlyjlCount(xn015Trlyjl);
		List data = null;
		if (total > 0) {
			data = this.uj.findXn015Trlyjl(xn015Trlyjl);
		}

		return new MyPage(xn015Trlyjl.getPage().intValue(), xn015Trlyjl.getSize().intValue(), total, data);
	}

	public List<Xn015Trlyjl> getAll() {
		return this.uj.getAll();
	}
}