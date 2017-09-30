package com.nis.analysis.service.impl;

import com.nis.analysis.dao.NyUnanalyzeBbBytDao;
import com.nis.analysis.entity.NyUnanalyzeBbByt;
import com.nis.analysis.service.NyUnanalyzeBbBytService;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NyUnanalyzeBbBytServiceImpl implements NyUnanalyzeBbBytService {
	@Autowired
	private NyUnanalyzeBbBytDao cx;

	public void save(NyUnanalyzeBbByt nyUnanalyzeBbByt) {
		nyUnanalyzeBbByt.setBytid(z.a(bg.nV));
		this.cx.save(nyUnanalyzeBbByt);
	}

	public void delete(String bytid) {
		this.cx.delete(bytid);
	}

	public void update(NyUnanalyzeBbByt nyUnanalyzeBbByt) {
		this.cx.update(nyUnanalyzeBbByt);
	}

	public NyUnanalyzeBbByt get(String bytid) {
		return this.cx.get(bytid);
	}

	public MyPage<NyUnanalyzeBbByt> a(NyUnanalyzeBbByt nyUnanalyzeBbByt) {
		int total = this.cx.findNyUnanalyzeBbBytCount(nyUnanalyzeBbByt);
		List data = null;
		if (total > 0) {
			data = this.cx.findNyUnanalyzeBbByt(nyUnanalyzeBbByt);
		}

		return new MyPage(nyUnanalyzeBbByt.getPage().intValue(), nyUnanalyzeBbByt.getSize().intValue(), total, data);
	}

	public List<NyUnanalyzeBbByt> getAll() {
		return this.cx.getAll();
	}
}