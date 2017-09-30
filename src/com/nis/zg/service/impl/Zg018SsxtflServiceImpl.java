package com.nis.zg.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.zg.dao.Zg018SsxtflDao;
import com.nis.zg.entity.Zg018Ssxtfl;
import com.nis.zg.service.Zg018SsxtflService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Zg018SsxtflServiceImpl implements Zg018SsxtflService {
	@Autowired
	private Zg018SsxtflDao yz;

	public void save(Zg018Ssxtfl zg018Ssxtfl) {
		this.yz.save(zg018Ssxtfl);
	}

	public void delete(String opesysid) {
		this.yz.delete(opesysid);
	}

	public void update(Zg018Ssxtfl zg018Ssxtfl) {
		this.yz.update(zg018Ssxtfl);
	}

	public Zg018Ssxtfl get(String opesysid) {
		return this.yz.get(opesysid);
	}

	public MyPage<Zg018Ssxtfl> a(Zg018Ssxtfl zg018Ssxtfl) {
		int total = this.yz.findZg018SsxtflCount(zg018Ssxtfl);
		List data = null;
		if (total > 0) {
			data = this.yz.findZg018Ssxtfl(zg018Ssxtfl);
		}

		return new MyPage(zg018Ssxtfl.getPage().intValue(), zg018Ssxtfl.getSize().intValue(), total, data);
	}

	public List<Zg018Ssxtfl> getAll() {
		return this.yz.getAll();
	}
}