package com.nis.mdr.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.mdr.dao.Xn009LybzmxDao;
import com.nis.mdr.entity.Xn009Lybzmx;
import com.nis.mdr.service.Xn009LybzmxService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Xn009LybzmxServiceImpl implements Xn009LybzmxService {
	@Autowired
	private Xn009LybzmxDao ud;

	public void save(Xn009Lybzmx xn009Lybzmx) {
		this.ud.save(xn009Lybzmx);
	}

	public void update(Xn009Lybzmx xn009Lybzmx) {
		this.ud.update(xn009Lybzmx);
	}

	public MyPage<Xn009Lybzmx> a(Xn009Lybzmx xn009Lybzmx) {
		int total = this.ud.findXn009LybzmxCount(xn009Lybzmx);
		List data = null;
		if (total > 0) {
			data = this.ud.findXn009Lybzmx(xn009Lybzmx);
		}

		return new MyPage(xn009Lybzmx.getPage().intValue(), xn009Lybzmx.getSize().intValue(), total, data);
	}

	public List<Xn009Lybzmx> getAll() {
		return this.ud.getAll();
	}
}