package com.nis.hand.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.hand.dao.Sw004YcxsjDao;
import com.nis.hand.entity.Sw004Ycxsj;
import com.nis.hand.service.Sw004YcxsjService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sw004YcxsjServiceImpl implements Sw004YcxsjService {
	@Autowired
	private Sw004YcxsjDao rg;

	public void save(Sw004Ycxsj sw004Ycxsj) {
		this.rg.save(sw004Ycxsj);
	}

	public void delete(String keyId) {
		this.rg.delete(keyId);
	}

	public void update(Sw004Ycxsj sw004Ycxsj) {
		this.rg.update(sw004Ycxsj);
	}

	public Sw004Ycxsj get(String keyId) {
		return this.rg.get(keyId);
	}

	public MyPage<Sw004Ycxsj> a(Sw004Ycxsj sw004Ycxsj) {
		int total = this.rg.findSw004YcxsjCount(sw004Ycxsj);
		List data = null;
		if (total > 0) {
			data = this.rg.findSw004Ycxsj(sw004Ycxsj);
		}

		return new MyPage(sw004Ycxsj.getPage().intValue(), sw004Ycxsj.getSize().intValue(), total, data);
	}

	public List<Sw004Ycxsj> getAll() {
		return this.rg.getAll();
	}

	public List<Sw004Ycxsj> getByDcid(String dcId) {
		return this.rg.getByDcid(dcId);
	}
}