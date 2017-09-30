package com.nis.mdr.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.mdr.dao.Xn013DbywDao;
import com.nis.mdr.entity.Xn013Dbyw;
import com.nis.mdr.service.Xn013DbywService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Xn013DbywServiceImpl implements Xn013DbywService {
	@Autowired
	private Xn013DbywDao ug;

	public void save(Xn013Dbyw xn013Dbyw) {
		this.ug.save(xn013Dbyw);
	}

	public void delete(Long itemId) {
		this.ug.delete(itemId);
	}

	public void update(Xn013Dbyw xn013Dbyw) {
		this.ug.update(xn013Dbyw);
	}

	public Xn013Dbyw get(Long itemId) {
		return this.ug.get(itemId);
	}

	public MyPage<Xn013Dbyw> a(Xn013Dbyw xn013Dbyw) {
		int total = this.ug.findXn013DbywCount(xn013Dbyw);
		List data = null;
		if (total > 0) {
			data = this.ug.findXn013Dbyw(xn013Dbyw);
		}

		return new MyPage(xn013Dbyw.getPage().intValue(), xn013Dbyw.getSize().intValue(), total, data);
	}

	public List<Xn013Dbyw> getAll() {
		return this.ug.getAll();
	}
}