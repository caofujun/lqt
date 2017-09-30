package com.nis.prevalence.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.prevalence.dao.Xl012DicTrocheDao;
import com.nis.prevalence.entity.Xl012DicTroche;
import com.nis.prevalence.service.Xl012DicTrocheService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Xl012DicTrocheServiceImpl implements Xl012DicTrocheService {
	@Autowired
	private Xl012DicTrocheDao xc;

	public void save(Xl012DicTroche xl012DicTroche) {
		this.xc.save(xl012DicTroche);
	}

	public void delete(String trocheid) {
		this.xc.delete(trocheid);
	}

	public void update(Xl012DicTroche xl012DicTroche) {
		this.xc.update(xl012DicTroche);
	}

	public Xl012DicTroche get(String trocheid) {
		return this.xc.get(trocheid);
	}

	public MyPage<Xl012DicTroche> a(Xl012DicTroche xl012DicTroche) {
		int total = this.xc.findXl012DicTrocheCount(xl012DicTroche);
		List data = null;
		if (total > 0) {
			data = this.xc.findXl012DicTroche(xl012DicTroche);
		}

		return new MyPage(xl012DicTroche.getPage().intValue(), xl012DicTroche.getSize().intValue(), total, data);
	}

	public List<Xl012DicTroche> getAll() {
		return this.xc.getAll();
	}
}