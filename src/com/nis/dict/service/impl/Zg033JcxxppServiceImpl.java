package com.nis.dict.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.dict.dao.Zg033JcxxppDao;
import com.nis.dict.entity.Zg033Jcxxpp;
import com.nis.dict.service.Zg033JcxxppService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Zg033JcxxppServiceImpl implements Zg033JcxxppService {
	@Autowired
	private Zg033JcxxppDao qP;

	public void save(Zg033Jcxxpp zg033Jcxxpp) {
		zg033Jcxxpp.setId(z.a(bg.nU));
		this.qP.save(zg033Jcxxpp);
	}

	public void delete(String id) {
		this.qP.delete(id);
	}

	public void update(Zg033Jcxxpp zg033Jcxxpp) {
		this.qP.update(zg033Jcxxpp);
	}

	public Zg033Jcxxpp get(String id) {
		return this.qP.get(id);
	}

	public MyPage<Zg033Jcxxpp> a(Zg033Jcxxpp zg033Jcxxpp) {
		int total = this.qP.findZg033JcxxppCount(zg033Jcxxpp);
		List data = null;
		if (total > 0) {
			data = this.qP.findZg033Jcxxpp(zg033Jcxxpp);
		}

		return new MyPage(zg033Jcxxpp.getPage().intValue(), zg033Jcxxpp.getSize().intValue(), total, data);
	}

	public List<Zg033Jcxxpp> getAll() {
		return this.qP.getAll();
	}

	public List<Zg033Jcxxpp> findBySjId(String sjId) {
		return this.qP.findBySjId(sjId);
	}
}