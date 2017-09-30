package com.nis.zg.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.zg.dao.Zg016SopDao;
import com.nis.zg.entity.Zg016Sop;
import com.nis.zg.service.Zg016SopService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Zg016SopServiceImpl implements Zg016SopService {
	@Autowired
	private Zg016SopDao yx;

	public void save(Zg016Sop zg016Sop) {
		this.yx.save(zg016Sop);
	}

	public void delete(String fileId) {
		this.yx.delete(fileId);
	}

	public void update(Zg016Sop zg016Sop) {
		this.yx.update(zg016Sop);
	}

	public Zg016Sop get(String fileId) {
		return this.yx.get(fileId);
	}

	public MyPage<Zg016Sop> a(Zg016Sop zg016Sop) {
		int total = this.yx.findZg016SopCount(zg016Sop);
		List data = null;
		if (total > 0) {
			data = this.yx.findZg016Sop(zg016Sop);
		}

		return new MyPage(zg016Sop.getPage().intValue(), zg016Sop.getSize().intValue(), total, data);
	}

	public List<Zg016Sop> getAll() {
		return this.yx.getAll();
	}
}