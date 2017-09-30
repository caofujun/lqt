package com.nis.mdr.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.utils.f;
import com.nis.dict.service.SysDictService;
import com.nis.mdr.dao.Xn014LiskjywDao;
import com.nis.mdr.entity.Xn014Liskjyw;
import com.nis.mdr.service.Xn014LiskjywService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Xn014LiskjywServiceImpl implements Xn014LiskjywService {
	@Autowired
	private Xn014LiskjywDao ui;
	@Autowired
	private SysDictService p;

	public void save(Xn014Liskjyw xn014Liskjyw) {
		this.ui.save(xn014Liskjyw);
		this.p.updateLastTimebyCode("data_init", "YMYW", f.getCurDate());
	}

	public void delete(String drugid) {
		this.ui.delete(drugid);
		this.p.updateLastTimebyCode("data_init", "YMYW", f.getCurDate());
	}

	public void update(Xn014Liskjyw xn014Liskjyw) {
		this.ui.update(xn014Liskjyw);
		this.p.updateLastTimebyCode("data_init", "YMYW", f.getCurDate());
	}

	public void match() {
		this.ui.match();
	}

	public Map<String, String> queryMatched() {
		List matchBefore = this.ui.matchBefore();
		if (matchBefore.size() > 0) {
			for (int i = 0; i < matchBefore.size(); ++i) {
				this.ui.save((Xn014Liskjyw) matchBefore.get(i));
				this.p.updateLastTimebyCode("data_init", "YMYW", f.getCurDate());
			}
		}

		return this.ui.queryMatched();
	}

	public Xn014Liskjyw get(String drugid) {
		return this.ui.get(drugid);
	}

	public MyPage<Xn014Liskjyw> a(Xn014Liskjyw xn014Liskjyw) {
		int total = this.ui.findXn014LiskjywCount(xn014Liskjyw);
		List data = null;
		if (total > 0) {
			data = this.ui.findXn014Liskjyw(xn014Liskjyw);
		}

		return new MyPage(xn014Liskjyw.getPage().intValue(), xn014Liskjyw.getSize().intValue(), total, data);
	}

	public List<Xn014Liskjyw> getAll() {
		return this.ui.getAll();
	}

	public MyPage<Xn014Liskjyw> b(Xn014Liskjyw xn014Liskjyw) {
		int total = this.ui.findXn014LiskjywListCount(xn014Liskjyw);
		List data = null;
		if (total > 0) {
			data = this.ui.findXn014LiskjywList(xn014Liskjyw);
		}

		return new MyPage(xn014Liskjyw.getPage().intValue(), xn014Liskjyw.getSize().intValue(), total, data);
	}
}