package com.nis.yj.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.yj.dao.Yj003StandardDao;
import com.nis.yj.entity.Yj003Standard;
import com.nis.yj.service.Yj003StandardService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Yj003StandardServiceImpl implements Yj003StandardService {
	@Autowired
	private Yj003StandardDao yh;

	public void save(Yj003Standard yj003Standard) {
		this.yh.save(yj003Standard);
	}

	public void delete(String id) {
		this.yh.delete(id);
	}

	public void update(Yj003Standard yj003Standard) {
		this.yh.update(yj003Standard);
	}

	public Yj003Standard get(String id) {
		return this.yh.get(id);
	}

	public MyPage<Yj003Standard> a(Yj003Standard yj003Standard) {
		int total = this.yh.findYj003StandardCount(yj003Standard);
		List data = null;
		if (total > 0) {
			data = this.yh.findYj003Standard(yj003Standard);
		}

		return new MyPage(yj003Standard.getPage().intValue(), yj003Standard.getSize().intValue(), total, data);
	}

	public List<Yj003Standard> getAll() {
		return this.yh.getAll();
	}
}