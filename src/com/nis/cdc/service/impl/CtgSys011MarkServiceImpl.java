package com.nis.cdc.service.impl;

import com.nis.cdc.dao.CtgSys011MarkDao;
import com.nis.cdc.entity.CtgSys011Mark;
import com.nis.cdc.service.CtgSys011MarkService;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CtgSys011MarkServiceImpl implements CtgSys011MarkService {
	@Autowired
	private CtgSys011MarkDao el;

	public void save(CtgSys011Mark ctgSys011Mark) {
		this.el.save(ctgSys011Mark);
	}

	public void delete(String masterid) {
		this.el.delete(masterid);
	}

	public void update(CtgSys011Mark ctgSys011Mark) {
		this.el.update(ctgSys011Mark);
	}

	public CtgSys011Mark get(String masterid) {
		return this.el.get(masterid);
	}

	public MyPage<CtgSys011Mark> a(CtgSys011Mark ctgSys011Mark) {
		int total = this.el.findCtgSys011MarkCount(ctgSys011Mark);
		List data = null;
		if (total > 0) {
			data = this.el.findCtgSys011Mark(ctgSys011Mark);
		}

		return new MyPage(ctgSys011Mark.getPage().intValue(), ctgSys011Mark.getSize().intValue(), total, data);
	}

	public List<CtgSys011Mark> getAll() {
		return this.el.getAll();
	}

	public void deleteByOtherField(String mzzyid, String diseaseid) {
		this.el.deleteByOtherField(mzzyid, diseaseid);
	}
}