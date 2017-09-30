package com.nis.mdr.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.f;
import com.nis.comm.utils.t;
import com.nis.dict.service.SysDictService;
import com.nis.mdr.dao.Xn013LisbytDao;
import com.nis.mdr.entity.Xn013Lisbyt;
import com.nis.mdr.service.Xn013LisbytService;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Xn013LisbytServiceImpl implements Xn013LisbytService {
	@Autowired
	private Xn013LisbytDao uh;
	@Autowired
	private SysDictService p;

	public void save(Xn013Lisbyt xn013Lisbyt) {
		this.uh.save(xn013Lisbyt);
		this.p.updateLastTimebyCode("data_init", "XJ", f.getCurDate());
	}

	public void delete(String lisBytid) {
		this.uh.delete(lisBytid);
		this.p.updateLastTimebyCode("data_init", "XJ", f.getCurDate());
	}

	public void update(Xn013Lisbyt xn013Lisbyt) {
		this.uh.update(xn013Lisbyt);
		this.p.updateLastTimebyCode("data_init", "XJ", f.getCurDate());
	}

	public void match() {
		this.uh.match();
	}

	public Map<String, String> queryMatched() {
		List matchBefore = this.uh.matchBefore();
		if (matchBefore.size() > 0) {
			for (int i = 0; i < matchBefore.size(); ++i) {
				this.uh.save((Xn013Lisbyt) matchBefore.get(i));
				this.p.updateLastTimebyCode("data_init", "XJ", f.getCurDate());
			}
		}

		return this.uh.queryMatched();
	}

	public Xn013Lisbyt get(String lisBytid) {
		return this.uh.get(lisBytid);
	}

	public MyPage<Xn013Lisbyt> a(Xn013Lisbyt xn013Lisbyt) {
		int total = this.uh.findXn013LisbytCount(xn013Lisbyt);
		List data = null;
		if (total > 0) {
			data = this.uh.findXn013Lisbyt(xn013Lisbyt);
		}

		return new MyPage(xn013Lisbyt.getPage().intValue(), xn013Lisbyt.getSize().intValue(), total, data);
	}

	public List<Xn013Lisbyt> getAll() {
		return this.uh.getAll();
	}

	public MyPage<Xn013Lisbyt> b(Xn013Lisbyt xn013Lisbyt) {
		int total = this.uh.findXn013LisbytListCount(xn013Lisbyt);
		List data = null;
		if (total > 0) {
			data = this.uh.findXn013LisbytList(xn013Lisbyt);
		}

		return new MyPage(xn013Lisbyt.getPage().intValue(), xn013Lisbyt.getSize().intValue(), total, data);
	}

	public void ab() {
		List lisbytList = this.uh.autoSaveList();
		Iterator arg2 = lisbytList.iterator();

		while (arg2.hasNext()) {
			Xn013Lisbyt lisbyt = (Xn013Lisbyt) arg2.next();
			lisbyt.setPycode(t.aC(lisbyt.getLisBytname()));
			this.save(lisbyt);
		}

	}
}