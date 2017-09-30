package com.nis.zg.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.zg.dao.Zg004YyxxDao;
import com.nis.zg.entity.Zg004Yyxx;
import com.nis.zg.service.Zg004YyxxService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Zg004YyxxServiceImpl implements Zg004YyxxService {
	@Autowired
	private Zg004YyxxDao to;

	public void save(Zg004Yyxx zg004Yyxx) {
		this.to.save(zg004Yyxx);
	}

	public void delete(String hospId) {
		this.to.delete(hospId);
	}

	public void update(Zg004Yyxx zg004Yyxx) {
		this.to.update(zg004Yyxx);
	}

	public Zg004Yyxx get(String hospId) {
		return this.to.get(hospId);
	}

	public MyPage<Zg004Yyxx> a(Zg004Yyxx zg004Yyxx) {
		int total = this.to.findZg004YyxxCount(zg004Yyxx);
		List data = null;
		if (total > 0) {
			data = this.to.findZg004Yyxx(zg004Yyxx);
		}

		return new MyPage(zg004Yyxx.getPage().intValue(), zg004Yyxx.getSize().intValue(), total, data);
	}

	public List<Zg004Yyxx> getAll() {
		return this.to.getAll();
	}
}