package com.nis.mdr.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.mdr.dao.Xn003KjywDao;
import com.nis.mdr.entity.Xn003Kjyw;
import com.nis.mdr.service.Xn003KjywService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Xn003KjywServiceImpl implements Xn003KjywService {
	@Autowired
	private Xn003KjywDao tY;

	public void save(Xn003Kjyw xn003Kjyw) {
		this.tY.save(xn003Kjyw);
	}

	public void delete(String drugId) {
		this.tY.delete(drugId);
	}

	public void update(Xn003Kjyw xn003Kjyw) {
		this.tY.update(xn003Kjyw);
	}

	public Xn003Kjyw get(String drugId) {
		return this.tY.get(drugId);
	}

	public MyPage<Xn003Kjyw> a(Xn003Kjyw xn003Kjyw) {
		int total = this.tY.findXn003KjywCount(xn003Kjyw);
		List data = null;
		if (total > 0) {
			data = this.tY.findXn003Kjyw(xn003Kjyw);
		}

		return new MyPage(xn003Kjyw.getPage().intValue(), xn003Kjyw.getSize().intValue(), total, data);
	}

	public List<Xn003Kjyw> b(Xn003Kjyw xn003Kjyw) {
		return this.tY.findXn003KjywByByt(xn003Kjyw);
	}

	public List<Xn003Kjyw> getAll() {
		return this.tY.getAll();
	}
}