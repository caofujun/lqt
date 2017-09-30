package com.nis.prevalence.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.prevalence.dao.Xl019XjnyqkDao;
import com.nis.prevalence.entity.Xl019Xjnyqk;
import com.nis.prevalence.service.Xl019XjnyqkService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Xl019XjnyqkServiceImpl implements Xl019XjnyqkService {
	@Autowired
	private Xl019XjnyqkDao xf;

	public void save(Xl019Xjnyqk xl019Xjnyqk) {
		this.xf.save(xl019Xjnyqk);
	}

	public void delete(String id) {
		this.xf.delete(id);
	}

	public void update(Xl019Xjnyqk xl019Xjnyqk) {
		this.xf.update(xl019Xjnyqk);
	}

	public Xl019Xjnyqk get(String id) {
		return this.xf.get(id);
	}

	public MyPage<Xl019Xjnyqk> a(Xl019Xjnyqk xl019Xjnyqk) {
		int total = this.xf.findXl019XjnyqkCount(xl019Xjnyqk);
		List data = null;
		if (total > 0) {
			data = this.xf.findXl019Xjnyqk(xl019Xjnyqk);
		}

		return new MyPage(xl019Xjnyqk.getPage().intValue(), xl019Xjnyqk.getSize().intValue(), total, data);
	}

	public List<Xl019Xjnyqk> getAll() {
		return this.xf.getAll();
	}
}