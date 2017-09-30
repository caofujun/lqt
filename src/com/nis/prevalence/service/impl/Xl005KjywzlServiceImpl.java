package com.nis.prevalence.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.prevalence.dao.Xl005KjywzlDao;
import com.nis.prevalence.entity.Xl005Kjywzl;
import com.nis.prevalence.service.Xl005KjywzlService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Xl005KjywzlServiceImpl implements Xl005KjywzlService {
	@Autowired
	private Xl005KjywzlDao wY;

	public void save(Xl005Kjywzl xl005Kjywzl) {
		this.wY.save(xl005Kjywzl);
	}

	public void delete(String ywzlid) {
		this.wY.delete(ywzlid);
	}

	public void update(Xl005Kjywzl xl005Kjywzl) {
		this.wY.update(xl005Kjywzl);
	}

	public Xl005Kjywzl get(String ywzlid) {
		return this.wY.get(ywzlid);
	}

	public MyPage<Xl005Kjywzl> a(Xl005Kjywzl xl005Kjywzl) {
		int total = this.wY.findXl005KjywzlCount(xl005Kjywzl);
		List data = null;
		if (total > 0) {
			data = this.wY.findXl005Kjywzl(xl005Kjywzl);
		}

		return new MyPage(xl005Kjywzl.getPage().intValue(), xl005Kjywzl.getSize().intValue(), total, data);
	}

	public List<Xl005Kjywzl> getAll() {
		return this.wY.getAll();
	}
}