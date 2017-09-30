package com.nis.cdc.service.impl;

import com.nis.cdc.dao.CtgSys004DictaddrareaDao;
import com.nis.cdc.entity.CtgSys004Dictaddrarea;
import com.nis.cdc.service.CtgSys004DictaddrareaService;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CtgSys004DictaddrareaServiceImpl implements CtgSys004DictaddrareaService {
	@Autowired
	private CtgSys004DictaddrareaDao ee;

	public void save(CtgSys004Dictaddrarea ctgSys004Dictaddrarea) {
		this.ee.save(ctgSys004Dictaddrarea);
	}

	public void delete(String areacode) {
		this.ee.delete(areacode);
	}

	public void update(CtgSys004Dictaddrarea ctgSys004Dictaddrarea) {
		this.ee.update(ctgSys004Dictaddrarea);
	}

	public CtgSys004Dictaddrarea get(String areacode) {
		return this.ee.get(areacode);
	}

	public MyPage<CtgSys004Dictaddrarea> a(CtgSys004Dictaddrarea ctgSys004Dictaddrarea) {
		int total = this.ee.findCtgSys004DictaddrareaCount(ctgSys004Dictaddrarea);
		List data = null;
		if (total > 0) {
			data = this.ee.findCtgSys004Dictaddrarea(ctgSys004Dictaddrarea);
		}

		return new MyPage(ctgSys004Dictaddrarea.getPage().intValue(), ctgSys004Dictaddrarea.getSize().intValue(), total,
				data);
	}

	public List<CtgSys004Dictaddrarea> getAll() {
		return this.ee.getAll();
	}

	public List<CtgSys004Dictaddrarea> getSheng() {
		return this.ee.getSheng();
	}

	public List<CtgSys004Dictaddrarea> getOther(String areaCode) {
		return this.ee.getOther(areaCode);
	}
}