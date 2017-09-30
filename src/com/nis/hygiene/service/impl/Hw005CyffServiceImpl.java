package com.nis.hygiene.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.hygiene.dao.Hw005CyffDao;
import com.nis.hygiene.entity.Hw005Cyff;
import com.nis.hygiene.service.Hw005CyffService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Hw005CyffServiceImpl implements Hw005CyffService {
	@Autowired
	private Hw005CyffDao rN;

	public void save(Hw005Cyff hw005Cyff) {
		if (hw005Cyff.getFlag() == null) {
			hw005Cyff.setFlag(Integer.valueOf(1));
		}

		if (hw005Cyff.getLastAt() == null) {
			hw005Cyff.setLastAt(new Date());
		}

		this.rN.save(hw005Cyff);
	}

	public void delete(String takeModeId) {
		this.rN.delete(takeModeId);
	}

	public void update(Hw005Cyff hw005Cyff) {
		if (hw005Cyff.getFlag() == null) {
			hw005Cyff.setFlag(Integer.valueOf(0));
		}

		if (hw005Cyff.getLastAt() == null) {
			hw005Cyff.setLastAt(new Date());
		}

		this.rN.update(hw005Cyff);
	}

	public Hw005Cyff get(String takeModeId) {
		return this.rN.get(takeModeId);
	}

	public MyPage<Hw005Cyff> a(Hw005Cyff hw005Cyff) {
		int total = this.rN.findHw005CyffCount(hw005Cyff);
		List data = null;
		if (total > 0) {
			data = this.rN.findHw005Cyff(hw005Cyff);
		}

		return new MyPage(hw005Cyff.getPage().intValue(), hw005Cyff.getSize().intValue(), total, data);
	}

	public List<Hw005Cyff> getAll() {
		return this.rN.getAll();
	}

	public List<Hw005Cyff> queryList(Hw005Cyff hw005Cyff) {
		return this.rN.queryList(hw005Cyff);
	}

	public List<Hw005Cyff> findList(Hw005Cyff hw005Cyff) {
		return this.rN.findList(hw005Cyff);
	}

	public String findMaxTakeModeId() {
		return this.rN.findMaxTakeModeId();
	}

	public void updFlag(Hw005Cyff hw005Cyff) {
		hw005Cyff.setLastAt(new Date());
		this.rN.updFlag(hw005Cyff);
	}
}