package com.nis.mdr.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.mdr.dao.Xn006KjywflDao;
import com.nis.mdr.entity.Xn006Kjywfl;
import com.nis.mdr.service.Xn006KjywflService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Xn006KjywflServiceImpl implements Xn006KjywflService {
	@Autowired
	private Xn006KjywflDao ub;

	public void save(Xn006Kjywfl xn006Kjywfl) {
		this.ub.save(xn006Kjywfl);
	}

	public void delete(String drugTypeId) {
		this.ub.delete(drugTypeId);
	}

	public void update(Xn006Kjywfl xn006Kjywfl) {
		this.ub.update(xn006Kjywfl);
	}

	public Xn006Kjywfl get(String drugTypeId) {
		return this.ub.get(drugTypeId);
	}

	public MyPage<Xn006Kjywfl> a(Xn006Kjywfl xn006Kjywfl) {
		int total = this.ub.findXn006KjywflCount(xn006Kjywfl);
		List data = null;
		if (total > 0) {
			data = this.ub.findXn006Kjywfl(xn006Kjywfl);
		}

		return new MyPage(xn006Kjywfl.getPage().intValue(), xn006Kjywfl.getSize().intValue(), total, data);
	}

	public List<Xn006Kjywfl> getAll() {
		return this.ub.getAll();
	}
}