package com.nis.zg.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.zg.dao.Zg008BytDao;
import com.nis.zg.entity.Zg008Byt;
import com.nis.zg.service.Zg008BytService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Zg008BytServiceImpl implements Zg008BytService {
	@Autowired
	private Zg008BytDao yt;

	public void save(Zg008Byt zg008Byt) {
		this.yt.save(zg008Byt);
	}

	public void delete(String pathogenId) {
		this.yt.delete(pathogenId);
	}

	public void update(Zg008Byt zg008Byt) {
		this.yt.update(zg008Byt);
	}

	public Zg008Byt get(String pathogenId) {
		return this.yt.get(pathogenId);
	}

	public MyPage<Zg008Byt> a(Zg008Byt zg008Byt) {
		int total = this.yt.findZg008BytCount(zg008Byt);
		List data = null;
		if (total > 0) {
			data = this.yt.findZg008Byt(zg008Byt);
		}

		return new MyPage(zg008Byt.getPage().intValue(), zg008Byt.getSize().intValue(), total, data);
	}

	public List<Zg008Byt> getAll() {
		return this.yt.getAll();
	}
}