package com.nis.cdc.service.impl;

import com.nis.cdc.dao.CtgSys003DictdetailDao;
import com.nis.cdc.entity.CtgSys003Dictdetail;
import com.nis.cdc.service.CtgSys003DictdetailService;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CtgSys003DictdetailServiceImpl implements CtgSys003DictdetailService {
	@Autowired
	private CtgSys003DictdetailDao ec;

	public void save(CtgSys003Dictdetail ctgSys003Dictdetail) {
		this.ec.save(ctgSys003Dictdetail);
	}

	public void delete(String scopeid) {
		this.ec.delete(scopeid);
	}

	public void update(CtgSys003Dictdetail ctgSys003Dictdetail) {
		this.ec.update(ctgSys003Dictdetail);
	}

	public CtgSys003Dictdetail get(String scopeid) {
		return this.ec.get(scopeid);
	}

	public MyPage<CtgSys003Dictdetail> a(CtgSys003Dictdetail ctgSys003Dictdetail) {
		int total = this.ec.findCtgSys003DictdetailCount(ctgSys003Dictdetail);
		List data = null;
		if (total > 0) {
			data = this.ec.findCtgSys003Dictdetail(ctgSys003Dictdetail);
		}

		return new MyPage(ctgSys003Dictdetail.getPage().intValue(), ctgSys003Dictdetail.getSize().intValue(), total,
				data);
	}

	public List<CtgSys003Dictdetail> getAll() {
		return this.ec.getAll();
	}
}