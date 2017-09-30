package com.nis.mdr.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.mdr.dao.Xn005JszdDao;
import com.nis.mdr.entity.Xn005Jszd;
import com.nis.mdr.service.Xn005JszdService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Xn005JszdServiceImpl implements Xn005JszdService {
	@Autowired
	private Xn005JszdDao ua;

	public void save(Xn005Jszd xn005Jszd) {
		this.ua.save(xn005Jszd);
	}

	public void delete(String bactGenusId) {
		this.ua.delete(bactGenusId);
	}

	public void update(Xn005Jszd xn005Jszd) {
		this.ua.update(xn005Jszd);
	}

	public Xn005Jszd get(String bactGenusId) {
		return this.ua.get(bactGenusId);
	}

	public MyPage<Xn005Jszd> a(Xn005Jszd xn005Jszd) {
		int total = this.ua.findXn005JszdCount(xn005Jszd);
		List data = null;
		if (total > 0) {
			data = this.ua.findXn005Jszd(xn005Jszd);
		}

		return new MyPage(xn005Jszd.getPage().intValue(), xn005Jszd.getSize().intValue(), total, data);
	}

	public List<Xn005Jszd> getAll() {
		return this.ua.getAll();
	}
}