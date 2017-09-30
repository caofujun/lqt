package com.nis.zg.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.dict.service.SysDictService;
import com.nis.zg.dao.Zg010KjywDao;
import com.nis.zg.entity.Zg010Kjyw;
import com.nis.zg.service.Zg010KjywService;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Zg010KjywServiceImpl implements Zg010KjywService {
	@Autowired
	private Zg010KjywDao yu;
	@Autowired
	private SysDictService p;

	public void save(Zg010Kjyw zg010Kjyw) {
		this.yu.save(zg010Kjyw);
	}

	public void delete(String drugId) {
		this.yu.delete(drugId);
	}

	public void update(Zg010Kjyw zg010Kjyw) {
		this.yu.update(zg010Kjyw);
	}

	public Zg010Kjyw get(String drugId) {
		return this.yu.get(drugId);
	}

	public MyPage<Zg010Kjyw> a(Zg010Kjyw zg010Kjyw) {
		int total = this.yu.findZg010KjywCount(zg010Kjyw);
		List data = null;
		if (total > 0) {
			data = this.yu.findZg010Kjyw(zg010Kjyw);
			Iterator arg4 = data.iterator();

			while (arg4.hasNext()) {
				Zg010Kjyw kjyw = (Zg010Kjyw) arg4.next();
				kjyw.setDrugTypeName(this.p.k("antibiotic_type", kjyw.getDrugTypeid(), (String) null));
			}
		}

		return new MyPage(zg010Kjyw.getPage().intValue(), zg010Kjyw.getSize().intValue(), total, data);
	}

	public List<Zg010Kjyw> getAll() {
		return this.yu.getAll();
	}
}