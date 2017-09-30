package com.nis.cdc.service.impl;

import com.nis.cdc.dao.CtgBk005BlxxDao;
import com.nis.cdc.entity.CtgBk005Blxx;
import com.nis.cdc.service.CtgBk005BlxxService;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CtgBk005BlxxServiceImpl implements CtgBk005BlxxService {
	@Autowired
	private CtgBk005BlxxDao dU;

	public void save(CtgBk005Blxx ctgBk005Blxx) {
		this.dU.save(ctgBk005Blxx);
	}

	public void delete(String subid) {
		this.dU.delete(subid);
	}

	public void update(CtgBk005Blxx ctgBk005Blxx) {
		this.dU.update(ctgBk005Blxx);
	}

	public CtgBk005Blxx get(String subid) {
		return this.dU.get(subid);
	}

	public MyPage<CtgBk005Blxx> a(CtgBk005Blxx ctgBk005Blxx) {
		int total = this.dU.findCtgBk005BlxxCount(ctgBk005Blxx);
		List data = null;
		if (total > 0) {
			data = this.dU.findCtgBk005Blxx(ctgBk005Blxx);
		}

		return new MyPage(ctgBk005Blxx.getPage().intValue(), ctgBk005Blxx.getSize().intValue(), total, data);
	}

	public List<CtgBk005Blxx> getAll() {
		return this.dU.getAll();
	}

	public List<CtgBk005Blxx> getByMastertid(String masterid) {
		return this.dU.getByMastertid(masterid);
	}
}