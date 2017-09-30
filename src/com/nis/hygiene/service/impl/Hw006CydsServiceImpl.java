package com.nis.hygiene.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.hygiene.dao.Hw006CydsDao;
import com.nis.hygiene.entity.Hw006Cyds;
import com.nis.hygiene.service.Hw006CydsService;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Hw006CydsServiceImpl implements Hw006CydsService {
	@Autowired
	private Hw006CydsDao rO;

	public void save(Hw006Cyds hw006Cyds) {
		if (hw006Cyds.getFlag() == null) {
			hw006Cyds.setFlag(Integer.valueOf(1));
		}

		if (hw006Cyds.getLastAt() == null) {
			hw006Cyds.setLastAt(new Date());
		}

		if (StringUtils.isBlank(hw006Cyds.getClassId())) {
			hw006Cyds.setClassId("00");
		}

		this.rO.save(hw006Cyds);
	}

	public void delete(String posId) {
		this.rO.delete(posId);
	}

	public void update(Hw006Cyds hw006Cyds) {
		if (hw006Cyds.getFlag() == null) {
			hw006Cyds.setFlag(Integer.valueOf(0));
		}

		if (hw006Cyds.getLastAt() == null) {
			hw006Cyds.setLastAt(new Date());
		}

		if (StringUtils.isBlank(hw006Cyds.getClassId())) {
			hw006Cyds.setClassId("00");
		}

		this.rO.update(hw006Cyds);
	}

	public Hw006Cyds get(String posId) {
		return this.rO.get(posId);
	}

	public MyPage<Hw006Cyds> a(Hw006Cyds hw006Cyds) {
		int total = this.rO.findHw006CydsCount(hw006Cyds);
		List data = null;
		if (total > 0) {
			data = this.rO.findHw006Cyds(hw006Cyds);
		}

		return new MyPage(hw006Cyds.getPage().intValue(), hw006Cyds.getSize().intValue(), total, data);
	}

	public List<Hw006Cyds> getAll() {
		return this.rO.getAll();
	}

	public List<Hw006Cyds> queryList(Hw006Cyds hw006Cyds) {
		return this.rO.queryList(hw006Cyds);
	}

	public List<Hw006Cyds> findList(Hw006Cyds hw006Cyds) {
		return this.rO.findList(hw006Cyds);
	}

	public String findMaxPosId() {
		return this.rO.findMaxPosId();
	}

	public void updFlag(Hw006Cyds hw006Cyds) {
		this.rO.updFlag(hw006Cyds);
	}
}