package com.nis.hygiene.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.hygiene.dao.Hw007XjlbDao;
import com.nis.hygiene.entity.Hw007Xjlb;
import com.nis.hygiene.service.Hw007XjlbService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Hw007XjlbServiceImpl implements Hw007XjlbService {
	@Autowired
	private Hw007XjlbDao rP;

	public void save(Hw007Xjlb hw007Xjlb) {
		this.rP.save(hw007Xjlb);
	}

	public void delete(String pathoId) {
		this.rP.delete(pathoId);
	}

	public void update(Hw007Xjlb hw007Xjlb) {
		this.rP.update(hw007Xjlb);
	}

	public Hw007Xjlb get(String pathoId) {
		return this.rP.get(pathoId);
	}

	public MyPage<Hw007Xjlb> a(Hw007Xjlb hw007Xjlb) {
		int total = this.rP.findHw007XjlbCount(hw007Xjlb);
		List data = null;
		if (total > 0) {
			data = this.rP.findHw007Xjlb(hw007Xjlb);
		}

		return new MyPage(hw007Xjlb.getPage().intValue(), hw007Xjlb.getSize().intValue(), total, data);
	}

	public List<Hw007Xjlb> getAll() {
		return this.rP.getAll();
	}

	public List<Hw007Xjlb> query(Hw007Xjlb hw007Xjlb) {
		return this.rP.query(hw007Xjlb);
	}
}