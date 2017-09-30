package com.nis.mdr.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.mdr.dao.Xn002BytDao;
import com.nis.mdr.entity.Xn002Byt;
import com.nis.mdr.service.Xn002BytService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Xn002BytServiceImpl implements Xn002BytService {
	@Autowired
	private Xn002BytDao tX;

	public void save(Xn002Byt xn002Byt) {
		this.tX.save(xn002Byt);
	}

	public void delete(String pathogenId) {
		this.tX.delete(pathogenId);
	}

	public void update(Xn002Byt xn002Byt) {
		this.tX.update(xn002Byt);
	}

	public Xn002Byt get(String pathogenId) {
		return this.tX.get(pathogenId);
	}

	public MyPage<Xn002Byt> a(Xn002Byt xn002Byt) {
		int total = this.tX.findXn002BytCount(xn002Byt);
		List data = null;
		if (total > 0) {
			data = this.tX.findXn002Byt(xn002Byt);
		}

		return new MyPage(xn002Byt.getPage().intValue(), xn002Byt.getSize().intValue(), total, data);
	}

	public List<Xn002Byt> getAll() {
		return this.tX.getAll();
	}

	public MyPage<Xn002Byt> b(Xn002Byt xn002Byt) {
		int total = this.tX.findXn002BytCountTemp(xn002Byt);
		List data = null;
		if (total > 0) {
			data = this.tX.findXn002BytTemp(xn002Byt);
		}

		return new MyPage(xn002Byt.getPage().intValue(), xn002Byt.getSize().intValue(), total, data);
	}

	public MyPage<Xn002Byt> c(Xn002Byt xn002Byt) {
		int total = this.tX.findXn002BytIndexCount(xn002Byt);
		List data = null;
		if (total > 0) {
			data = this.tX.findXn002BytIndex(xn002Byt);
		}

		return new MyPage(xn002Byt.getPage().intValue(), xn002Byt.getSize().intValue(), total, data);
	}

	public List<HashMap<String, Object>> getGlsfl() {
		return this.tX.getGlsfl();
	}

	public Xn002Byt findXn002BytEdit(Xn002Byt xn002Byt) {
		return this.tX.findXn002BytEdit(xn002Byt);
	}
}