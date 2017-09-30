package com.nis.cdc.service.impl;

import com.nis.cdc.dao.CtgBk001CrbafpcardDao;
import com.nis.cdc.entity.CtgBk001Crbafpcard;
import com.nis.cdc.service.CtgBk001CrbafpcardService;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CtgBk001CrbafpcardServiceImpl implements CtgBk001CrbafpcardService {
	@Autowired
	private CtgBk001CrbafpcardDao dJ;

	public void save(CtgBk001Crbafpcard ctgBk001Crbafpcard) {
		this.dJ.save(ctgBk001Crbafpcard);
	}

	public void delete(String masterid) {
		this.dJ.delete(masterid);
	}

	public void update(CtgBk001Crbafpcard ctgBk001Crbafpcard) {
		this.dJ.update(ctgBk001Crbafpcard);
	}

	public CtgBk001Crbafpcard get(String masterid) {
		return this.dJ.get(masterid);
	}

	public MyPage<CtgBk001Crbafpcard> a(CtgBk001Crbafpcard ctgBk001Crbafpcard) {
		int total = this.dJ.findCtgBk001CrbafpcardCount(ctgBk001Crbafpcard);
		List data = null;
		if (total > 0) {
			data = this.dJ.findCtgBk001Crbafpcard(ctgBk001Crbafpcard);
		}

		return new MyPage(ctgBk001Crbafpcard.getPage().intValue(), ctgBk001Crbafpcard.getSize().intValue(), total,
				data);
	}

	public List<CtgBk001Crbafpcard> getAll() {
		return this.dJ.getAll();
	}
}