package com.nis.hygiene.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.hygiene.dao.Hw004CybbDao;
import com.nis.hygiene.entity.Hw004Cybb;
import com.nis.hygiene.service.Hw004CybbService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Hw004CybbServiceImpl implements Hw004CybbService {
	@Autowired
	private Hw004CybbDao rM;

	public void save(Hw004Cybb hw004Cybb) {
		if (hw004Cybb.getFlag() == null) {
			hw004Cybb.setFlag(Integer.valueOf(1));
		}

		if (hw004Cybb.getLastAt() == null) {
			hw004Cybb.setLastAt(new Date());
		}

		this.rM.save(hw004Cybb);
	}

	public void delete(String sampleId) {
		this.rM.delete(sampleId);
	}

	public void update(Hw004Cybb hw004Cybb) {
		if (hw004Cybb.getFlag() == null) {
			hw004Cybb.setFlag(Integer.valueOf(0));
		}

		if (hw004Cybb.getLastAt() == null) {
			hw004Cybb.setLastAt(new Date());
		}

		this.rM.update(hw004Cybb);
	}

	public Hw004Cybb get(String sampleId) {
		return this.rM.get(sampleId);
	}

	public MyPage<Hw004Cybb> a(Hw004Cybb hw004Cybb) {
		int total = this.rM.findHw004CybbCount(hw004Cybb);
		List data = null;
		if (total > 0) {
			data = this.rM.findHw004Cybb(hw004Cybb);
		}

		return new MyPage(hw004Cybb.getPage().intValue(), hw004Cybb.getSize().intValue(), total, data);
	}

	public List<Hw004Cybb> getAll() {
		return this.rM.getAll();
	}

	public List<Hw004Cybb> queryList(Hw004Cybb hw004Cybb) {
		return this.rM.queryList(hw004Cybb);
	}

	public List<Hw004Cybb> findList(Hw004Cybb hw004Cybb) {
		return this.rM.findList(hw004Cybb);
	}

	public String findMaxSampleId() {
		return this.rM.findMaxSampleId();
	}

	public void updFlag(Hw004Cybb hw004Cybb) {
		hw004Cybb.setLastAt(new Date());
		this.rM.updFlag(hw004Cybb);
	}

	public Hw004Cybb getHw004Cybb(String sampleId) {
		return this.rM.getHw004Cybb(sampleId);
	}

	public Hw004Cybb isExist(String sampleId) {
		return this.rM.isExist(sampleId);
	}
}