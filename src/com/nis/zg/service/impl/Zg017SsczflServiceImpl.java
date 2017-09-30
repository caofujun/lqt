package com.nis.zg.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.zg.dao.Zg017SsczflDao;
import com.nis.zg.entity.Zg017Ssczfl;
import com.nis.zg.service.Zg017SsczflService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Zg017SsczflServiceImpl implements Zg017SsczflService {
	@Autowired
	private Zg017SsczflDao yy;

	public void save(Zg017Ssczfl zg017Ssczfl) {
		this.yy.save(zg017Ssczfl);
	}

	public void delete(String opepartkindid) {
		this.yy.delete(opepartkindid);
	}

	public void update(Zg017Ssczfl zg017Ssczfl) {
		this.yy.update(zg017Ssczfl);
	}

	public Zg017Ssczfl get(String opepartkindid) {
		return this.yy.get(opepartkindid);
	}

	public MyPage<Zg017Ssczfl> a(Zg017Ssczfl zg017Ssczfl) {
		int total = this.yy.findZg017SsczflCount(zg017Ssczfl);
		List data = null;
		if (total > 0) {
			data = this.yy.findZg017Ssczfl(zg017Ssczfl);
		}

		return new MyPage(zg017Ssczfl.getPage().intValue(), zg017Ssczfl.getSize().intValue(), total, data);
	}

	public List<Zg017Ssczfl> getAll() {
		return this.yy.getAll();
	}
}