package com.nis.analysis.service.impl;

import com.nis.analysis.dao.Zg007DictEnDao;
import com.nis.analysis.entity.Zg007DictEn;
import com.nis.analysis.service.Zg007DictEnService;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Zg007DictEnServiceImpl implements Zg007DictEnService {
	@Autowired
	private Zg007DictEnDao cE;

	public void save(Zg007DictEn zg007DictEn) {
		this.cE.save(zg007DictEn);
	}

	public void delete(String keyid) {
		this.cE.delete(keyid);
	}

	public void update(Zg007DictEn zg007DictEn) {
		this.cE.update(zg007DictEn);
	}

	public Zg007DictEn get(String keyid) {
		return this.cE.get(keyid);
	}

	public MyPage<Zg007DictEn> a(Zg007DictEn zg007DictEn) {
		int total = this.cE.findZg007DictEnCount(zg007DictEn);
		List data = null;
		if (total > 0) {
			data = this.cE.findZg007DictEn(zg007DictEn);
		}

		return new MyPage(zg007DictEn.getPage().intValue(), zg007DictEn.getSize().intValue(), total, data);
	}

	public List<Zg007DictEn> getAll() {
		return this.cE.getAll();
	}
}