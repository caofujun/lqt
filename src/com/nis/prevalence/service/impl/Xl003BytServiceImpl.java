package com.nis.prevalence.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.prevalence.dao.Xl003BytDao;
import com.nis.prevalence.entity.Xl003Byt;
import com.nis.prevalence.service.Xl003BytService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Xl003BytServiceImpl implements Xl003BytService {
	@Autowired
	private Xl003BytDao wW;

	public void save(Xl003Byt xl003Byt) {
		xl003Byt.setBytid(z.a(bg.ni));
		this.wW.save(xl003Byt);
	}

	public void delete(String bytid) {
		this.wW.delete(bytid);
	}

	public void update(Xl003Byt xl003Byt) {
		this.wW.update(xl003Byt);
	}

	public Xl003Byt get(String bytid) {
		return this.wW.get(bytid);
	}

	public MyPage<Xl003Byt> a(Xl003Byt xl003Byt) {
		int total = this.wW.findXl003BytCount(xl003Byt);
		List data = null;
		if (total > 0) {
			data = this.wW.findXl003Byt(xl003Byt);
		}

		return new MyPage(xl003Byt.getPage().intValue(), xl003Byt.getSize().intValue(), total, data);
	}

	public List<Xl003Byt> getAll() {
		return this.wW.getAll();
	}

	public void delXl003Byt(List<String> bytidNotIn, String brid) {
		this.wW.delXl003Byt(bytidNotIn, brid);
	}

	public void deleteByBrid(String brid) {
		this.wW.deleteByBrid(brid);
	}
}