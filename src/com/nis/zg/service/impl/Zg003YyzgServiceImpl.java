package com.nis.zg.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.zg.dao.Zg003YyzgDao;
import com.nis.zg.entity.Zg003Yyzg;
import com.nis.zg.service.Zg003YyzgService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Zg003YyzgServiceImpl implements Zg003YyzgService {
	@Autowired
	private Zg003YyzgDao tn;

	public void save(Zg003Yyzg zg003Yyzg) {
		this.tn.save(zg003Yyzg);
	}

	public void delete(String id) {
		this.tn.delete(id);
	}

	public void update(Zg003Yyzg zg003Yyzg) {
		this.tn.update(zg003Yyzg);
	}

	public Zg003Yyzg get(String id) {
		return this.tn.get(id);
	}

	public MyPage<Zg003Yyzg> a(Zg003Yyzg zg003Yyzg) {
		int total = this.tn.findZg003YyzgCount(zg003Yyzg);
		List data = null;
		if (total > 0) {
			data = this.tn.findZg003Yyzg(zg003Yyzg);
		}

		return new MyPage(zg003Yyzg.getPage().intValue(), zg003Yyzg.getSize().intValue(), total, data);
	}

	public List<Zg003Yyzg> getAll() {
		return this.tn.getAll();
	}

	public List<Zg003Yyzg> getByName(String employeeName, String deptId) {
		return this.tn.getByName(employeeName, deptId);
	}
}