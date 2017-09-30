package com.nis.analysis.service.impl;

import com.nis.analysis.dao.Gr019YsgrmxWeightDao;
import com.nis.analysis.entity.Gr019YsgrmxWeight;
import com.nis.analysis.service.Gr019YsgrmxWeightService;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Gr019YsgrmxWeightServiceImpl implements Gr019YsgrmxWeightService {
	@Autowired
	private Gr019YsgrmxWeightDao cw;

	public void save(Gr019YsgrmxWeight gr019YsgrmxWeight) {
		this.cw.save(gr019YsgrmxWeight);
	}

	public void delete(String zyid) {
		this.cw.delete(zyid);
	}

	public void update(Gr019YsgrmxWeight gr019YsgrmxWeight) {
		this.cw.update(gr019YsgrmxWeight);
	}

	public MyPage<Gr019YsgrmxWeight> b(Gr019YsgrmxWeight gr019YsgrmxWeight) {
		int total = this.cw.findGr019YsgrmxWeightCount(gr019YsgrmxWeight);
		List data = null;
		if (total > 0) {
			data = this.cw.findGr019YsgrmxWeight(gr019YsgrmxWeight);
		}

		return new MyPage(gr019YsgrmxWeight.getPage().intValue(), gr019YsgrmxWeight.getSize().intValue(), total, data);
	}

	public List<Gr019YsgrmxWeight> getAll() {
		return this.cw.getAll();
	}

	public void a(Gr019YsgrmxWeight gr019YsgrmxWeight) {
		Gr019YsgrmxWeight ysgrmxWeight = this.cw.get(gr019YsgrmxWeight.getZyid(), gr019YsgrmxWeight.getInfectCode());
		if (ysgrmxWeight != null) {
			this.update(gr019YsgrmxWeight);
		} else {
			this.save(gr019YsgrmxWeight);
		}

	}
}