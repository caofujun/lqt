package com.nis.monitor.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.monitor.dao.Xn020GadcDao;
import com.nis.monitor.entity.Xn020Gadc;
import com.nis.monitor.service.Xn020GadcService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Xn020GadcServiceImpl implements Xn020GadcService {
	@Autowired
	private Xn020GadcDao uQ;

	public void save(Xn020Gadc xn020Gadc) {
		xn020Gadc.setId(z.a(bg.nd));
		this.uQ.save(xn020Gadc);
	}

	public void delete(String id) {
		this.uQ.delete(id);
	}

	public void update(Xn020Gadc xn020Gadc) {
		this.uQ.update(xn020Gadc);
	}

	public Xn020Gadc get(String id) {
		return this.uQ.get(id);
	}

	public MyPage<Xn020Gadc> a(Xn020Gadc xn020Gadc) {
		int total = this.uQ.findXn020GadcCount(xn020Gadc);
		List data = null;
		if (total > 0) {
			data = this.uQ.findXn020Gadc(xn020Gadc);
		}

		return new MyPage(xn020Gadc.getPage().intValue(), xn020Gadc.getSize().intValue(), total, data);
	}

	public List<Xn020Gadc> getAll() {
		return this.uQ.getAll();
	}
}