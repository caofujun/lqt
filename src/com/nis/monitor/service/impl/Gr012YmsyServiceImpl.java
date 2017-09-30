package com.nis.monitor.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.monitor.dao.Gr012YmsyDao;
import com.nis.monitor.entity.Gr012Ymsy;
import com.nis.monitor.service.Gr012YmsyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Gr012YmsyServiceImpl implements Gr012YmsyService {
	@Autowired
	private Gr012YmsyDao uI;

	public void save(Gr012Ymsy gr012Ymsy) {
		gr012Ymsy.setId(z.a(bg.na));
		this.uI.save(gr012Ymsy);
	}

	public void delete(Long id) {
		this.uI.delete(id);
	}

	public void update(Gr012Ymsy gr012Ymsy) {
		this.uI.update(gr012Ymsy);
	}

	public Gr012Ymsy get(Long id) {
		return this.uI.get(id);
	}

	public MyPage<Gr012Ymsy> a(Gr012Ymsy gr012Ymsy) {
		int total = this.uI.findGr012YmsyCount(gr012Ymsy);
		List data = null;
		if (total > 0) {
			data = this.uI.findGr012Ymsy(gr012Ymsy);
		}

		return new MyPage(gr012Ymsy.getPage().intValue(), gr012Ymsy.getSize().intValue(), total, data);
	}

	public List<Gr012Ymsy> getAll() {
		return this.uI.getAll();
	}
}