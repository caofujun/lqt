package com.nis.zg.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.zg.dao.Zg006GrzdmxDao;
import com.nis.zg.entity.Zg006Grzdmx;
import com.nis.zg.service.Zg006GrzdmxService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Zg006GrzdmxServiceImpl implements Zg006GrzdmxService {
	@Autowired
	private Zg006GrzdmxDao yq;

	public void save(Zg006Grzdmx zg006Grzdmx) {
		this.yq.save(zg006Grzdmx);
	}

	public void delete(Double mxId) {
		this.yq.delete(mxId);
	}

	public void update(Zg006Grzdmx zg006Grzdmx) {
		this.yq.update(zg006Grzdmx);
	}

	public Zg006Grzdmx get(Double mxId) {
		return this.yq.get(mxId);
	}

	public MyPage<Zg006Grzdmx> a(Zg006Grzdmx zg006Grzdmx) {
		int total = this.yq.findZg006GrzdmxCount(zg006Grzdmx);
		List data = null;
		if (total > 0) {
			data = this.yq.findZg006Grzdmx(zg006Grzdmx);
		}

		return new MyPage(zg006Grzdmx.getPage().intValue(), zg006Grzdmx.getSize().intValue(), total, data);
	}

	public List<Zg006Grzdmx> getAll() {
		return this.yq.getAll();
	}
}