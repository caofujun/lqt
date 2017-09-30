package com.nis.yj.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.yj.dao.Yj003RegularLisDao;
import com.nis.yj.entity.Yj003RegularLis;
import com.nis.yj.service.Yj003RegularLisService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Yj003RegularLisServiceImpl implements Yj003RegularLisService {
	@Autowired
	private Yj003RegularLisDao yf;

	public void save(Yj003RegularLis yj003RegularLis) {
		this.yf.save(yj003RegularLis);
	}

	public void delete(String id) {
		this.yf.delete(id);
	}

	public void update(Yj003RegularLis yj003RegularLis) {
		this.yf.update(yj003RegularLis);
	}

	public Yj003RegularLis get(String id) {
		return this.yf.get(id);
	}

	public MyPage<Yj003RegularLis> a(Yj003RegularLis yj003RegularLis) {
		int total = this.yf.findYj003RegularLisCount(yj003RegularLis);
		List data = null;
		if (total > 0) {
			data = this.yf.findYj003RegularLis(yj003RegularLis);
		}

		return new MyPage(yj003RegularLis.getPage().intValue(), yj003RegularLis.getSize().intValue(), total, data);
	}

	public List<Yj003RegularLis> getAll() {
		return this.yf.getAll();
	}
}