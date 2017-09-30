package com.nis.cdc.service.impl;

import com.nis.cdc.dao.CtgSys008MarkedcaseDao;
import com.nis.cdc.entity.CtgSys008Markedcase;
import com.nis.cdc.service.CtgSys008MarkedcaseService;
import com.nis.comm.entity.MyPage;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CtgSys008MarkedcaseServiceImpl implements CtgSys008MarkedcaseService {
	@Autowired
	private CtgSys008MarkedcaseDao ej;

	public void save(CtgSys008Markedcase ctgSys008Markedcase) {
		this.ej.save(ctgSys008Markedcase);
	}

	public void delete(String id) {
		this.ej.delete(id);
	}

	public void update(CtgSys008Markedcase ctgSys008Markedcase) {
		this.ej.update(ctgSys008Markedcase);
	}

	public CtgSys008Markedcase get(String id) {
		return this.ej.get(id);
	}

	public MyPage<CtgSys008Markedcase> a(CtgSys008Markedcase ctgSys008Markedcase) {
		int total = this.ej.findCtgSys008MarkedcaseCount(ctgSys008Markedcase);
		List data = null;
		if (total > 0) {
			data = this.ej.findCtgSys008Markedcase(ctgSys008Markedcase);
		}

		return new MyPage(ctgSys008Markedcase.getPage().intValue(), ctgSys008Markedcase.getSize().intValue(), total,
				data);
	}

	public List<CtgSys008Markedcase> getAll() {
		return this.ej.getAll();
	}

	public void u(List<CtgSys008Markedcase> mcList) {
		Iterator arg2 = mcList.iterator();

		while (arg2.hasNext()) {
			CtgSys008Markedcase mc = (CtgSys008Markedcase) arg2.next();
			CtgSys008Markedcase ctgSys008Markedcase = this.ej.get(mc.getId());
			if (ctgSys008Markedcase == null) {
				this.ej.save(mc);
			} else {
				this.ej.update(mc);
			}
		}

	}
}