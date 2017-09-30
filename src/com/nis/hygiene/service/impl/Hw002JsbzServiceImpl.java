package com.nis.hygiene.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.hygiene.dao.Hw002JsbzDao;
import com.nis.hygiene.entity.Hw002Jsbz;
import com.nis.hygiene.service.Hw002JsbzService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Hw002JsbzServiceImpl implements Hw002JsbzService {
	@Autowired
	private Hw002JsbzDao rK;

	public void save(Hw002Jsbz hw002Jsbz) {
		if (hw002Jsbz.getLastAt() == null) {
			hw002Jsbz.setLastAt(new Date());
		}

		if (hw002Jsbz.getFlag() == null) {
			hw002Jsbz.setFlag(Integer.valueOf(0));
		}

		this.rK.save(hw002Jsbz);
	}

	public void delete(String itemId) {
		this.rK.delete(itemId);
	}

	public void update(Hw002Jsbz hw002Jsbz) {
		if (hw002Jsbz.getLastAt() == null) {
			hw002Jsbz.setLastAt(new Date());
		}

		if (hw002Jsbz.getFlag() == null) {
			hw002Jsbz.setFlag(Integer.valueOf(0));
		}

		this.rK.update(hw002Jsbz);
	}

	public Hw002Jsbz get(String itemId) {
		return this.rK.get(itemId);
	}

	public MyPage<Hw002Jsbz> a(Hw002Jsbz hw002Jsbz) {
		int total = this.rK.findHw002JsbzCount(hw002Jsbz);
		List data = null;
		if (total > 0) {
			data = this.rK.findHw002Jsbz(hw002Jsbz);
		}

		return new MyPage(hw002Jsbz.getPage().intValue(), hw002Jsbz.getSize().intValue(), total, data);
	}

	public List<Hw002Jsbz> getAll() {
		return this.rK.getAll();
	}

	public List<Hw002Jsbz> findListByClassId(String classId) {
		return this.rK.findListByClassId(classId);
	}

	public List<Hw002Jsbz> findList(Hw002Jsbz hw002Jsbz) {
		return this.rK.findList(hw002Jsbz);
	}

	public Hw002Jsbz getHw002Jsbz(String itemId) {
		return this.rK.getHw002Jsbz(itemId);
	}

	public String findMaxItemId() {
		return this.rK.findMaxItemId();
	}
}