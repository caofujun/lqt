package com.nis.prevalence.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.prevalence.dao.Xl010DicStatkindDao;
import com.nis.prevalence.entity.Xl010DicStatkind;
import com.nis.prevalence.service.Xl010DicStatkindService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Xl010DicStatkindServiceImpl implements Xl010DicStatkindService {
	@Autowired
	private Xl010DicStatkindDao xa;

	public void save(Xl010DicStatkind xl010DicStatkind) {
		this.xa.save(xl010DicStatkind);
	}

	public void delete(String statid) {
		this.xa.delete(statid);
	}

	public void update(Xl010DicStatkind xl010DicStatkind) {
		this.xa.update(xl010DicStatkind);
	}

	public Xl010DicStatkind get(String statid) {
		return this.xa.get(statid);
	}

	public MyPage<Xl010DicStatkind> a(Xl010DicStatkind xl010DicStatkind) {
		int total = this.xa.findXl010DicStatkindCount(xl010DicStatkind);
		List data = null;
		if (total > 0) {
			data = this.xa.findXl010DicStatkind(xl010DicStatkind);
		}

		return new MyPage(xl010DicStatkind.getPage().intValue(), xl010DicStatkind.getSize().intValue(), total, data);
	}

	public List<Xl010DicStatkind> getAll() {
		return this.xa.getAll();
	}

	public List<Xl010DicStatkind> b(Xl010DicStatkind xl010DicStatkind) {
		return this.xa.findXl010DicStatkind(xl010DicStatkind);
	}
}