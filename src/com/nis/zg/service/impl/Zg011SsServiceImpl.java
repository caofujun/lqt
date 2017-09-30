package com.nis.zg.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.zg.dao.Zg011SsDao;
import com.nis.zg.entity.Zg011Ss;
import com.nis.zg.service.Zg011SsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Zg011SsServiceImpl implements Zg011SsService {
	@Autowired
	private Zg011SsDao yv;

	public void save(Zg011Ss zg011Ss) {
		this.yv.save(zg011Ss);
	}

	public void delete(String icdId) {
		this.yv.delete(icdId);
	}

	public void update(Zg011Ss zg011Ss) {
		this.yv.update(zg011Ss);
	}

	public Zg011Ss get(String icdId) {
		return this.yv.get(icdId);
	}

	public MyPage<Zg011Ss> a(Zg011Ss zg011Ss) {
		int total = this.yv.findZg011SsCount(zg011Ss);
		List data = null;
		if (total > 0) {
			data = this.yv.findZg011Ss(zg011Ss);
		}

		return new MyPage(zg011Ss.getPage().intValue(), zg011Ss.getSize().intValue(), total, data);
	}

	public List<Zg011Ss> getAll() {
		return this.yv.getAll();
	}

	public Zg011Ss getByOperId(String operId) {
		return this.yv.getByOperId(operId);
	}

	public List<Zg011Ss> getOperaInfo(String operName) {
		return this.yv.getOperaInfo(operName);
	}

	public List<Zg011Ss> query(Zg011Ss zg011Ss) {
		return this.yv.query(zg011Ss);
	}
}