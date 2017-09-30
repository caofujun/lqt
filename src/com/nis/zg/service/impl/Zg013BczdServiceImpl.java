package com.nis.zg.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.zg.dao.Zg013BczdDao;
import com.nis.zg.entity.Zg013Bczd;
import com.nis.zg.service.Zg013BczdService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Zg013BczdServiceImpl implements Zg013BczdService {
	@Autowired
	private Zg013BczdDao yw;

	public void save(Zg013Bczd zg013Bczd) {
		this.yw.save(zg013Bczd);
	}

	public void delete(String itemName) {
		this.yw.delete(itemName);
	}

	public void update(Zg013Bczd zg013Bczd) {
		this.yw.update(zg013Bczd);
	}

	public Zg013Bczd get(String itemName) {
		return this.yw.get(itemName);
	}

	public MyPage<Zg013Bczd> a(Zg013Bczd zg013Bczd) {
		int total = this.yw.findZg013BczdCount(zg013Bczd);
		List data = null;
		if (total > 0) {
			data = this.yw.findZg013Bczd(zg013Bczd);
		}

		return new MyPage(zg013Bczd.getPage().intValue(), zg013Bczd.getSize().intValue(), total, data);
	}

	public List<Zg013Bczd> getAll() {
		return this.yw.getAll();
	}
}