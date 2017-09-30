package com.nis.prevalence.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.prevalence.dao.Xl013DicPathotrocheDao;
import com.nis.prevalence.entity.Xl013DicPathotroche;
import com.nis.prevalence.service.Xl013DicPathotrocheService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Xl013DicPathotrocheServiceImpl implements Xl013DicPathotrocheService {
	@Autowired
	private Xl013DicPathotrocheDao xd;

	public void save(Xl013DicPathotroche xl013DicPathotroche) {
		this.xd.save(xl013DicPathotroche);
	}

	public void delete(String pathotrocheid) {
		this.xd.delete(pathotrocheid);
	}

	public void update(Xl013DicPathotroche xl013DicPathotroche) {
		this.xd.update(xl013DicPathotroche);
	}

	public Xl013DicPathotroche get(String pathotrocheid) {
		return this.xd.get(pathotrocheid);
	}

	public MyPage<Xl013DicPathotroche> a(Xl013DicPathotroche xl013DicPathotroche) {
		int total = this.xd.findXl013DicPathotrocheCount(xl013DicPathotroche);
		List data = null;
		if (total > 0) {
			data = this.xd.findXl013DicPathotroche(xl013DicPathotroche);
		}

		return new MyPage(xl013DicPathotroche.getPage().intValue(), xl013DicPathotroche.getSize().intValue(), total,
				data);
	}

	public List<Xl013DicPathotroche> getAll() {
		return this.xd.getAll();
	}
}