package com.nis.zg.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.zg.dao.Zg007GrysDao;
import com.nis.zg.entity.Zg007Grys;
import com.nis.zg.service.Zg007GrysService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Zg007GrysServiceImpl implements Zg007GrysService {
	@Autowired
	private Zg007GrysDao ys;

	public void save(Zg007Grys zg007Grys) {
		this.ys.save(zg007Grys);
	}

	public void delete(String elementId) {
		this.ys.delete(elementId);
	}

	public void update(Zg007Grys zg007Grys) {
		this.ys.update(zg007Grys);
	}

	public Zg007Grys get(String elementId) {
		return this.ys.get(elementId);
	}

	public MyPage<Zg007Grys> a(Zg007Grys zg007Grys) {
		int total = this.ys.findZg007GrysCount(zg007Grys);
		List data = null;
		if (total > 0) {
			data = this.ys.findZg007Grys(zg007Grys);
		}

		return new MyPage(zg007Grys.getPage().intValue(), zg007Grys.getSize().intValue(), total, data);
	}

	public List<Zg007Grys> getAll() {
		return this.ys.getAll();
	}

	public List<Zg007Grys> findByElementIdLike(String elementId) {
		return this.ys.findByElementIdLike(elementId);
	}
}