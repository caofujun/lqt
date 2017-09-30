package com.nis.cdc.service.impl;

import com.nis.cdc.dao.CtgSys009YjDao;
import com.nis.cdc.entity.CtgSys009Yj;
import com.nis.cdc.service.CtgSys009YjService;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CtgSys009YjServiceImpl implements CtgSys009YjService {
	@Autowired
	private CtgSys009YjDao ek;

	public void save(CtgSys009Yj ctgSys009Yj) {
		this.ek.save(ctgSys009Yj);
	}

	public void delete(String masterid) {
		this.ek.delete(masterid);
	}

	public void update(CtgSys009Yj ctgSys009Yj) {
		this.ek.update(ctgSys009Yj);
	}

	public CtgSys009Yj get(String masterid) {
		return this.ek.get(masterid);
	}

	public MyPage<CtgSys009Yj> a(CtgSys009Yj ctgSys009Yj) {
		int total = this.ek.findCtgSys009YjCount(ctgSys009Yj);
		List data = null;
		if (total > 0) {
			data = this.ek.findCtgSys009Yj(ctgSys009Yj);
		}

		return new MyPage(ctgSys009Yj.getPage().intValue(), ctgSys009Yj.getSize().intValue(), total, data);
	}

	public List<CtgSys009Yj> getAll() {
		return this.ek.getAll();
	}

	public List<CtgSys009Yj> isExit(CtgSys009Yj ctgSys009Yj) {
		return this.ek.isExit(ctgSys009Yj);
	}
}