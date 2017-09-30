package com.nis.dict.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.h;
import com.nis.comm.utils.f;
import com.nis.comm.utils.t;
import com.nis.dict.dao.SopDao;
import com.nis.dict.entity.Sop;
import com.nis.dict.service.SopService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SopServiceImpl implements SopService {
	@Autowired
	private SopDao qJ;

	public void save(Sop sop) {
		sop.setFlag(String.valueOf(h.fP.getCode()));
		sop.setLastAt(f.getCurDate());
		if (StringUtils.isBlank(sop.getSpCode())) {
			sop.setSpCode(t.aE(sop.getFileTitle()));
		}

		if (StringUtils.isBlank(sop.getFlag())) {
			sop.setFlag("1");
		}

		this.qJ.save(sop);
	}

	public void delete(String id) {
		this.qJ.delete(id);
	}

	public void update(Sop sop) {
		sop.setLastAt(f.getCurDate());
		if (StringUtils.isBlank(sop.getFlag())) {
			sop.setFlag("1");
		}

		this.qJ.update(sop);
	}

	public Sop get(String id) {
		return this.qJ.get(id);
	}

	public MyPage<Sop> a(Sop sop) {
		int total = this.qJ.findSopCount(sop);
		List data = null;
		if (total > 0) {
			data = this.qJ.findSop(sop);
		}

		return new MyPage(sop.getPage().intValue(), sop.getSize().intValue(), total, data);
	}

	public List<Sop> getAll() {
		return this.qJ.getAll();
	}
}