package com.nis.mdr.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.mdr.dao.Xn018TsnymxDao;
import com.nis.mdr.entity.Xn018Tsnymx;
import com.nis.mdr.service.Xn018TsnymxService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Xn018TsnymxServiceImpl implements Xn018TsnymxService {
	@Autowired
	private Xn018TsnymxDao ul;

	public void save(Xn018Tsnymx xn018Tsnymx) {
		this.ul.save(xn018Tsnymx);
	}

	public void update(Xn018Tsnymx xn018Tsnymx) {
		this.ul.update(xn018Tsnymx);
	}

	public MyPage<Xn018Tsnymx> a(Xn018Tsnymx xn018Tsnymx) {
		int total = this.ul.findXn018TsnymxCount(xn018Tsnymx);
		List data = null;
		if (total > 0) {
			data = this.ul.findXn018Tsnymx(xn018Tsnymx);
		}

		return new MyPage(xn018Tsnymx.getPage().intValue(), xn018Tsnymx.getSize().intValue(), total, data);
	}

	public List<Xn018Tsnymx> getAll() {
		return this.ul.getAll();
	}
}