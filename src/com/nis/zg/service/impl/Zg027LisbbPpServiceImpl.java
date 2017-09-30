package com.nis.zg.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.f;
import com.nis.comm.utils.z;
import com.nis.dict.service.SysDictService;
import com.nis.zg.dao.Zg027LisbbPpDao;
import com.nis.zg.entity.Zg027LisbbPp;
import com.nis.zg.service.Zg027LisbbPpService;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Zg027LisbbPpServiceImpl implements Zg027LisbbPpService {
	@Autowired
	private Zg027LisbbPpDao yC;
	@Autowired
	private SysDictService p;

	public void save(Zg027LisbbPp zg027LisbbPp) {
		zg027LisbbPp.setBbppId(z.a(bg.nS));
		this.yC.save(zg027LisbbPp);
		this.p.updateLastTimebyCode("data_init", "BB", f.getCurDate());
	}

	public MyPage<Zg027LisbbPp> a(Zg027LisbbPp zg027LisbbPp) {
		int total = this.yC.findZg027LisbbPpCount(zg027LisbbPp);
		List data = null;
		if (total > 0) {
			data = this.yC.findZg027LisbbPp(zg027LisbbPp);
		}

		return new MyPage(zg027LisbbPp.getPage().intValue(), zg027LisbbPp.getSize().intValue(), total, data);
	}

	public void match() {
		this.yC.match();
	}

	public Map<String, String> queryMatched() {
		List matchBefore = this.yC.matchBefore();
		if (matchBefore.size() > 0) {
			for (int i = 0; i < matchBefore.size(); ++i) {
				((Zg027LisbbPp) matchBefore.get(i)).setBbppId(z.a(bg.nS));
				this.yC.save((Zg027LisbbPp) matchBefore.get(i));
				this.p.updateLastTimebyCode("data_init", "BB", f.getCurDate());
			}
		}

		return this.yC.queryMatched();
	}

	public List<Zg027LisbbPp> getAll() {
		return this.yC.getAll();
	}

	public MyPage<Zg027LisbbPp> b(Zg027LisbbPp zg027LisbbPp) {
		int total = this.yC.findZg027LisbbPpListCount(zg027LisbbPp);
		List data = null;
		if (total > 0) {
			data = this.yC.findZg027LisbbPpList(zg027LisbbPp);
		}

		return new MyPage(zg027LisbbPp.getPage().intValue(), zg027LisbbPp.getSize().intValue(), total, data);
	}

	public Zg027LisbbPp get(String bbppId) {
		return this.yC.get(bbppId);
	}

	public void update(Zg027LisbbPp zg027LisbbPp) {
		this.yC.update(zg027LisbbPp);
		this.p.updateLastTimebyCode("data_init", "BB", f.getCurDate());
	}

	public void aB() {
		List lisbbPbList = this.yC.findbySt009();
		Iterator arg2 = lisbbPbList.iterator();

		while (arg2.hasNext()) {
			Zg027LisbbPp lisbbPP = (Zg027LisbbPp) arg2.next();
			this.save(lisbbPP);
		}

	}
}