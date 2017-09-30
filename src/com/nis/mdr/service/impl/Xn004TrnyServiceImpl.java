package com.nis.mdr.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.mdr.dao.Xn004TrnyDao;
import com.nis.mdr.entity.Xn004Trny;
import com.nis.mdr.service.Xn004TrnyService;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Xn004TrnyServiceImpl implements Xn004TrnyService {
	@Autowired
	private Xn004TrnyDao tZ;

	public void save(Xn004Trny xn004Trny) {
		xn004Trny.setLastAt(new Date());
		this.tZ.save(xn004Trny);
	}

	public void delete(String pathogenId, String drugId) {
		this.tZ.delete(pathogenId, drugId);
	}

	public void update(Xn004Trny xn004Trny) {
		xn004Trny.setLastAt(new Date());
		this.tZ.update(xn004Trny);
	}

	public Xn004Trny get(String pathogenId, String drugId) {
		return this.tZ.get(pathogenId, drugId);
	}

	public MyPage<Xn004Trny> a(Xn004Trny xn004Trny) {
		int total = this.tZ.findXn004TrnyCount(xn004Trny);
		List data = null;
		if (total > 0) {
			data = this.tZ.findXn004Trny(xn004Trny);
		}

		return new MyPage(xn004Trny.getPage().intValue(), xn004Trny.getSize().intValue(), total, data);
	}

	public List<Xn004Trny> getAll() {
		return this.tZ.getAll();
	}

	public Set<String> findIdSet() {
		return this.tZ.findIdSet();
	}
}