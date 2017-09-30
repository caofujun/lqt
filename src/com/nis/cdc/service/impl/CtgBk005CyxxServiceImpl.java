package com.nis.cdc.service.impl;

import com.nis.cdc.dao.CtgBk005CyxxDao;
import com.nis.cdc.entity.CtgBk005Cyxx;
import com.nis.cdc.service.CtgBk005CyxxService;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CtgBk005CyxxServiceImpl implements CtgBk005CyxxService {
	@Autowired
	private CtgBk005CyxxDao dV;

	public void save(CtgBk005Cyxx ctgBk005Cyxx) {
		this.dV.save(ctgBk005Cyxx);
	}

	public void delete(String subid) {
		this.dV.delete(subid);
	}

	public void update(CtgBk005Cyxx ctgBk005Cyxx) {
		this.dV.update(ctgBk005Cyxx);
	}

	public CtgBk005Cyxx get(String subid) {
		return this.dV.get(subid);
	}

	public MyPage<CtgBk005Cyxx> a(CtgBk005Cyxx ctgBk005Cyxx) {
		int total = this.dV.findCtgBk005CyxxCount(ctgBk005Cyxx);
		List data = null;
		if (total > 0) {
			data = this.dV.findCtgBk005Cyxx(ctgBk005Cyxx);
		}

		return new MyPage(ctgBk005Cyxx.getPage().intValue(), ctgBk005Cyxx.getSize().intValue(), total, data);
	}

	public List<CtgBk005Cyxx> getAll() {
		return this.dV.getAll();
	}

	public List<CtgBk005Cyxx> getByMastertid(String masterid) {
		return this.dV.getByMastertid(masterid);
	}
}