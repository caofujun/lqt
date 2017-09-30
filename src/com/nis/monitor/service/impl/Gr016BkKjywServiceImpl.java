package com.nis.monitor.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.monitor.dao.Gr016BkKjywDao;
import com.nis.monitor.entity.Gr016BkKjyw;
import com.nis.monitor.service.Gr016BkKjywService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Gr016BkKjywServiceImpl implements Gr016BkKjywService {
	@Autowired
	private Gr016BkKjywDao uK;

	public void save(Gr016BkKjyw gr016BkKjyw) {
		gr016BkKjyw.setRelid(z.a(bg.nb));
		this.uK.save(gr016BkKjyw);
	}

	public void delete(String relid) {
		this.uK.delete(relid);
	}

	public void update(Gr016BkKjyw gr016BkKjyw) {
		this.uK.update(gr016BkKjyw);
	}

	public Gr016BkKjyw get(String relid) {
		return this.uK.get(relid);
	}

	public MyPage<Gr016BkKjyw> a(Gr016BkKjyw gr016BkKjyw) {
		int total = this.uK.findGr016BkKjywCount(gr016BkKjyw);
		List data = null;
		if (total > 0) {
			data = this.uK.findGr016BkKjyw(gr016BkKjyw);
		}

		return new MyPage(gr016BkKjyw.getPage().intValue(), gr016BkKjyw.getSize().intValue(), total, data);
	}

	public List<Gr016BkKjyw> getAll() {
		return this.uK.getAll();
	}

	public List<Gr016BkKjyw> query(Gr016BkKjyw gr016BkKjyw) {
		return this.uK.query(gr016BkKjyw);
	}

	public void deleteByRefid(String refid) {
		this.uK.deleteByRefid(refid);
	}
}