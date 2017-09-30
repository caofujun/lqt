package com.nis.zg.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.zg.dao.Zg024ImpOpeDao;
import com.nis.zg.entity.Zg024ImpOpe;
import com.nis.zg.service.Zg024ImpOpeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Zg024ImpOpeServiceImpl implements Zg024ImpOpeService {
	@Autowired
	private Zg024ImpOpeDao yB;

	public void save(Zg024ImpOpe zg024ImpOpe) {
		this.yB.save(zg024ImpOpe);
	}

	public void delete(String impOpeId) {
		this.yB.delete(impOpeId);
	}

	public void update(Zg024ImpOpe zg024ImpOpe) {
		this.yB.update(zg024ImpOpe);
	}

	public Zg024ImpOpe get(String impOpeId) {
		return this.yB.get(impOpeId);
	}

	public MyPage<Zg024ImpOpe> a(Zg024ImpOpe zg024ImpOpe) {
		int total = this.yB.findZg024ImpOpeCount(zg024ImpOpe);
		List data = null;
		if (total > 0) {
			data = this.yB.findZg024ImpOpe(zg024ImpOpe);
		}

		return new MyPage(zg024ImpOpe.getPage().intValue(), zg024ImpOpe.getSize().intValue(), total, data);
	}

	public List<Zg024ImpOpe> getAll() {
		return this.yB.getAll();
	}
}