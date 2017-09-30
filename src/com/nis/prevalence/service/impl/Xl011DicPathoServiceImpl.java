package com.nis.prevalence.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.prevalence.dao.Xl011DicPathoDao;
import com.nis.prevalence.entity.Xl011DicPatho;
import com.nis.prevalence.service.Xl011DicPathoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Xl011DicPathoServiceImpl implements Xl011DicPathoService {
	@Autowired
	private Xl011DicPathoDao xb;

	public void save(Xl011DicPatho xl011DicPatho) {
		this.xb.save(xl011DicPatho);
	}

	public void delete(String pathoid) {
		this.xb.delete(pathoid);
	}

	public void update(Xl011DicPatho xl011DicPatho) {
		this.xb.update(xl011DicPatho);
	}

	public Xl011DicPatho get(String pathoid) {
		return this.xb.get(pathoid);
	}

	public MyPage<Xl011DicPatho> a(Xl011DicPatho xl011DicPatho) {
		int total = this.xb.findXl011DicPathoCount(xl011DicPatho);
		List data = null;
		if (total > 0) {
			data = this.xb.findXl011DicPatho(xl011DicPatho);
		}

		return new MyPage(xl011DicPatho.getPage().intValue(), xl011DicPatho.getSize().intValue(), total, data);
	}

	public List<Xl011DicPatho> getAll() {
		return this.xb.getAll();
	}

	public List<Xl011DicPatho> b(Xl011DicPatho xl011DicPatho) {
		return this.xb.findXl011DicPatho(xl011DicPatho);
	}
}