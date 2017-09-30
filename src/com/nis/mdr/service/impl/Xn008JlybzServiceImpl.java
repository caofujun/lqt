package com.nis.mdr.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.mdr.dao.Xn008JlybzDao;
import com.nis.mdr.entity.Xn008Jlybz;
import com.nis.mdr.service.Xn008JlybzService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Xn008JlybzServiceImpl implements Xn008JlybzService {
	@Autowired
	private Xn008JlybzDao uc;

	public void save(Xn008Jlybz xn008Jlybz) {
		this.uc.save(xn008Jlybz);
	}

	public void update(Xn008Jlybz xn008Jlybz) {
		this.uc.update(xn008Jlybz);
	}

	public MyPage<Xn008Jlybz> a(Xn008Jlybz xn008Jlybz) {
		int total = this.uc.findXn008JlybzCount(xn008Jlybz);
		List data = null;
		if (total > 0) {
			data = this.uc.findXn008Jlybz(xn008Jlybz);
		}

		return new MyPage(xn008Jlybz.getPage().intValue(), xn008Jlybz.getSize().intValue(), total, data);
	}

	public List<Xn008Jlybz> getAll() {
		return this.uc.getAll();
	}

	public List<Xn008Jlybz> findJlybzInfo() {
		return this.uc.findJlybzInfo();
	}
}