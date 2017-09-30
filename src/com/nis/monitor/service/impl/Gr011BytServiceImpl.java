package com.nis.monitor.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.monitor.dao.Gr011BytDao;
import com.nis.monitor.entity.Gr011Byt;
import com.nis.monitor.service.Gr011BytService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Gr011BytServiceImpl implements Gr011BytService {
	@Autowired
	private Gr011BytDao uH;

	public void save(Gr011Byt gr011Byt) {
		gr011Byt.setId(z.a(bg.mZ));
		this.uH.save(gr011Byt);
	}

	public void delete(Long id) {
		this.uH.delete(id);
	}

	public void update(Gr011Byt gr011Byt) {
		this.uH.update(gr011Byt);
	}

	public Gr011Byt get(Long id) {
		return this.uH.get(id);
	}

	public MyPage<Gr011Byt> a(Gr011Byt gr011Byt) {
		int total = this.uH.findGr011BytCount(gr011Byt);
		List data = null;
		if (total > 0) {
			data = this.uH.findGr011Byt(gr011Byt);
		}

		return new MyPage(gr011Byt.getPage().intValue(), gr011Byt.getSize().intValue(), total, data);
	}

	public List<Gr011Byt> getAll() {
		return this.uH.getAll();
	}
}