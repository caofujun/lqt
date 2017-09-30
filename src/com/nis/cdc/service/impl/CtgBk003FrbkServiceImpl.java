package com.nis.cdc.service.impl;

import com.nis.cdc.dao.CtgBk003FrbkDao;
import com.nis.cdc.entity.CtgBk003Frbk;
import com.nis.cdc.service.CtgBk003FrbkService;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CtgBk003FrbkServiceImpl implements CtgBk003FrbkService {
	@Autowired
	private CtgBk003FrbkDao dS;

	public void save(CtgBk003Frbk ctgBk003Frbk) {
		this.dS.save(ctgBk003Frbk);
	}

	public void delete(String masterid) {
		this.dS.delete(masterid);
	}

	public void update(CtgBk003Frbk ctgBk003Frbk) {
		this.dS.update(ctgBk003Frbk);
	}

	public CtgBk003Frbk get(String masterid) {
		return this.dS.get(masterid);
	}

	public MyPage<CtgBk003Frbk> a(CtgBk003Frbk ctgBk003Frbk) {
		int total = this.dS.findCtgBk003FrbkCount(ctgBk003Frbk);
		List data = null;
		if (total > 0) {
			data = this.dS.findCtgBk003Frbk(ctgBk003Frbk);
		}

		return new MyPage(ctgBk003Frbk.getPage().intValue(), ctgBk003Frbk.getSize().intValue(), total, data);
	}

	public List<CtgBk003Frbk> getAll() {
		return this.dS.getAll();
	}
}