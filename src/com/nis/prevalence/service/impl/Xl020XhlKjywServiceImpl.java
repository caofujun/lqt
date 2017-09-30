package com.nis.prevalence.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.prevalence.dao.Xl020XhlKjywDao;
import com.nis.prevalence.entity.Xl020XhlKjyw;
import com.nis.prevalence.service.Xl020XhlKjywService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Xl020XhlKjywServiceImpl implements Xl020XhlKjywService {
	@Autowired
	private Xl020XhlKjywDao xg;

	public void save(Xl020XhlKjyw xl020XhlKjyw) {
		this.xg.save(xl020XhlKjyw);
	}

	public void delete(String drugId) {
		this.xg.delete(drugId);
	}

	public void update(Xl020XhlKjyw xl020XhlKjyw) {
		this.xg.update(xl020XhlKjyw);
	}

	public Xl020XhlKjyw get(String drugId) {
		return this.xg.get(drugId);
	}

	public MyPage<Xl020XhlKjyw> a(Xl020XhlKjyw xl020XhlKjyw) {
		int total = this.xg.findXl020XhlKjywCount(xl020XhlKjyw);
		List data = null;
		if (total > 0) {
			data = this.xg.findXl020XhlKjyw(xl020XhlKjyw);
		}

		return new MyPage(xl020XhlKjyw.getPage().intValue(), xl020XhlKjyw.getSize().intValue(), total, data);
	}

	public List<Xl020XhlKjyw> getAll() {
		return this.xg.getAll();
	}

	public List<Xl020XhlKjyw> b(Xl020XhlKjyw xl020XhlKjyw) {
		return this.xg.findXl020XhlKjyw(xl020XhlKjyw);
	}
}