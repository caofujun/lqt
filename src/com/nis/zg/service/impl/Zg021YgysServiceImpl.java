package com.nis.zg.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.zg.dao.Zg021YgysDao;
import com.nis.zg.entity.Zg021Ygys;
import com.nis.zg.service.Zg021YgysService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Zg021YgysServiceImpl implements Zg021YgysService {
	@Autowired
	private Zg021YgysDao yA;

	public void save(Zg021Ygys zg021Ygys) {
		this.yA.save(zg021Ygys);
	}

	public void delete(String factorId) {
		this.yA.delete(factorId);
	}

	public void update(Zg021Ygys zg021Ygys) {
		this.yA.update(zg021Ygys);
	}

	public Zg021Ygys get(String factorId) {
		return this.yA.get(factorId);
	}

	public MyPage<Zg021Ygys> a(Zg021Ygys zg021Ygys) {
		int total = this.yA.findZg021YgysCount(zg021Ygys);
		List data = null;
		if (total > 0) {
			data = this.yA.findZg021Ygys(zg021Ygys);
		}

		return new MyPage(zg021Ygys.getPage().intValue(), zg021Ygys.getSize().intValue(), total, data);
	}

	public List<Zg021Ygys> getAll() {
		return this.yA.getAll();
	}
}