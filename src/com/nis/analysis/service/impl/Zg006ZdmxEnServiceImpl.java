package com.nis.analysis.service.impl;

import com.nis.analysis.dao.Zg006ZdmxEnDao;
import com.nis.analysis.entity.Zg006ZdmxEn;
import com.nis.analysis.service.Zg006ZdmxEnService;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Zg006ZdmxEnServiceImpl implements Zg006ZdmxEnService {
	@Autowired
	private Zg006ZdmxEnDao cC;

	public void save(Zg006ZdmxEn zg006ZdmxEn) {
		this.cC.save(zg006ZdmxEn);
	}

	public void delete(String mxId) {
		this.cC.delete(mxId);
	}

	public void update(Zg006ZdmxEn zg006ZdmxEn) {
		this.cC.update(zg006ZdmxEn);
	}

	public Zg006ZdmxEn get(String mxId) {
		return this.cC.get(mxId);
	}

	public MyPage<Zg006ZdmxEn> a(Zg006ZdmxEn zg006ZdmxEn) {
		int total = this.cC.findZg006ZdmxEnCount(zg006ZdmxEn);
		List data = null;
		if (total > 0) {
			data = this.cC.findZg006ZdmxEn(zg006ZdmxEn);
		}

		return new MyPage(zg006ZdmxEn.getPage().intValue(), zg006ZdmxEn.getSize().intValue(), total, data);
	}

	public List<Zg006ZdmxEn> getAll() {
		return this.cC.getAll();
	}
}