package com.nis.cdc.service.impl;

import com.nis.cdc.dao.CtgBk001TbDao;
import com.nis.cdc.entity.CtgBk001Tb;
import com.nis.cdc.service.CtgBk001TbService;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CtgBk001TbServiceImpl implements CtgBk001TbService {
	@Autowired
	private CtgBk001TbDao dQ;

	public void save(CtgBk001Tb ctgBk001Tb) {
		this.dQ.save(ctgBk001Tb);
	}

	public void delete(String masterid) {
		this.dQ.delete(masterid);
	}

	public void update(CtgBk001Tb ctgBk001Tb) {
		this.dQ.update(ctgBk001Tb);
	}

	public CtgBk001Tb get(String masterid) {
		return this.dQ.get(masterid);
	}

	public MyPage<CtgBk001Tb> a(CtgBk001Tb ctgBk001Tb) {
		int total = this.dQ.findCtgBk001TbCount(ctgBk001Tb);
		List data = null;
		if (total > 0) {
			data = this.dQ.findCtgBk001Tb(ctgBk001Tb);
		}

		return new MyPage(ctgBk001Tb.getPage().intValue(), ctgBk001Tb.getSize().intValue(), total, data);
	}

	public List<CtgBk001Tb> getAll() {
		return this.dQ.getAll();
	}

	public boolean u(String masterid) {
		int exist = this.dQ.isTBExist(masterid);
		return exist > 0;
	}
}