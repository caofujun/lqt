package com.nis.hygiene.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.hygiene.dao.Hw003CycsDao;
import com.nis.hygiene.entity.Hw003Cycs;
import com.nis.hygiene.service.Hw003CycsService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Hw003CycsServiceImpl implements Hw003CycsService {
	@Autowired
	private Hw003CycsDao rL;

	public void save(Hw003Cycs hw003Cycs) {
		if (hw003Cycs.getFlag() == null) {
			hw003Cycs.setFlag(Integer.valueOf(1));
		}

		if (hw003Cycs.getLastAt() == null) {
			hw003Cycs.setLastAt(new Date());
		}

		this.rL.save(hw003Cycs);
	}

	public void delete(String placeId) {
		this.rL.delete(placeId);
	}

	public void update(Hw003Cycs hw003Cycs) {
		if (hw003Cycs.getFlag() == null) {
			hw003Cycs.setFlag(Integer.valueOf(0));
		}

		if (hw003Cycs.getLastAt() == null) {
			hw003Cycs.setLastAt(new Date());
		}

		this.rL.update(hw003Cycs);
	}

	public Hw003Cycs get(String placeId) {
		return this.rL.get(placeId);
	}

	public MyPage<Hw003Cycs> a(Hw003Cycs hw003Cycs) {
		int total = this.rL.findHw003CycsCount(hw003Cycs);
		List data = null;
		if (total > 0) {
			data = this.rL.findHw003Cycs(hw003Cycs);
		}

		return new MyPage(hw003Cycs.getPage().intValue(), hw003Cycs.getSize().intValue(), total, data);
	}

	public List<Hw003Cycs> getAll() {
		return this.rL.getAll();
	}

	public List<Hw003Cycs> queryList(Hw003Cycs hw003Cycs) {
		return this.rL.queryList(hw003Cycs);
	}

	public List<Hw003Cycs> findList(Hw003Cycs hw003Cycs) {
		return this.rL.findList(hw003Cycs);
	}

	public String findMaxPlaceId() {
		return this.rL.findMaxPlaceId();
	}

	public void updFlag(Hw003Cycs hw003Cycs) {
		hw003Cycs.setLastAt(new Date());
		this.rL.updFlag(hw003Cycs);
	}

	public Hw003Cycs isExist(String placeId) {
		return this.rL.isExist(placeId);
	}
}