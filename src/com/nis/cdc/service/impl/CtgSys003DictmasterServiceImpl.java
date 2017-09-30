package com.nis.cdc.service.impl;

import com.nis.cdc.dao.CtgSys003DictmasterDao;
import com.nis.cdc.entity.CtgSys003Dictmaster;
import com.nis.cdc.service.CtgSys003DictmasterService;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CtgSys003DictmasterServiceImpl implements CtgSys003DictmasterService {
	@Autowired
	private CtgSys003DictmasterDao ed;

	public void save(CtgSys003Dictmaster ctgSys003Dictmaster) {
		this.ed.save(ctgSys003Dictmaster);
	}

	public void delete(String scopeid) {
		this.ed.delete(scopeid);
	}

	public void update(CtgSys003Dictmaster ctgSys003Dictmaster) {
		this.ed.update(ctgSys003Dictmaster);
	}

	public CtgSys003Dictmaster get(String scopeid) {
		return this.ed.get(scopeid);
	}

	public MyPage<CtgSys003Dictmaster> a(CtgSys003Dictmaster ctgSys003Dictmaster) {
		int total = this.ed.findCtgSys003DictmasterCount(ctgSys003Dictmaster);
		List data = null;
		if (total > 0) {
			data = this.ed.findCtgSys003Dictmaster(ctgSys003Dictmaster);
		}

		return new MyPage(ctgSys003Dictmaster.getPage().intValue(), ctgSys003Dictmaster.getSize().intValue(), total,
				data);
	}

	public List<CtgSys003Dictmaster> getAll() {
		return this.ed.getAll();
	}
}