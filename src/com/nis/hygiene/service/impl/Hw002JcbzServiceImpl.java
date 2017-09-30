package com.nis.hygiene.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.hygiene.dao.Hw002JcbzDao;
import com.nis.hygiene.entity.Hw002Jcbz;
import com.nis.hygiene.service.Hw002JcbzService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Hw002JcbzServiceImpl implements Hw002JcbzService {
	@Autowired
	private Hw002JcbzDao rJ;

	public void save(Hw002Jcbz hw002Jcbz) {
		this.rJ.save(hw002Jcbz);
	}

	public void delete(String itemId) {
		this.rJ.delete(itemId);
	}

	public void update(Hw002Jcbz hw002Jcbz) {
		this.rJ.update(hw002Jcbz);
	}

	public Hw002Jcbz get(String itemId) {
		return this.rJ.get(itemId);
	}

	public MyPage<Hw002Jcbz> a(Hw002Jcbz hw002Jcbz) {
		int total = this.rJ.findHw002JcbzCount(hw002Jcbz);
		List data = null;
		if (total > 0) {
			data = this.rJ.findHw002Jcbz(hw002Jcbz);
		}

		return new MyPage(hw002Jcbz.getPage().intValue(), hw002Jcbz.getSize().intValue(), total, data);
	}

	public List<Hw002Jcbz> getAll() {
		return this.rJ.getAll();
	}
}