package com.nis.cdc.service.impl;

import com.nis.cdc.dao.CtgBk001CrbhbvcardDao;
import com.nis.cdc.entity.CtgBk001Crbhbvcard;
import com.nis.cdc.service.CtgBk001CrbhbvcardService;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CtgBk001CrbhbvcardServiceImpl implements CtgBk001CrbhbvcardService {
	@Autowired
	private CtgBk001CrbhbvcardDao dL;

	public void save(CtgBk001Crbhbvcard ctgBk001Crbhbvcard) {
		this.dL.save(ctgBk001Crbhbvcard);
	}

	public void delete(String subid) {
		this.dL.delete(subid);
	}

	public void update(CtgBk001Crbhbvcard ctgBk001Crbhbvcard) {
		this.dL.update(ctgBk001Crbhbvcard);
	}

	public CtgBk001Crbhbvcard get(String subid) {
		return this.dL.get(subid);
	}

	public MyPage<CtgBk001Crbhbvcard> a(CtgBk001Crbhbvcard ctgBk001Crbhbvcard) {
		int total = this.dL.findCtgBk001CrbhbvcardCount(ctgBk001Crbhbvcard);
		List data = null;
		if (total > 0) {
			data = this.dL.findCtgBk001Crbhbvcard(ctgBk001Crbhbvcard);
		}

		return new MyPage(ctgBk001Crbhbvcard.getPage().intValue(), ctgBk001Crbhbvcard.getSize().intValue(), total,
				data);
	}

	public List<CtgBk001Crbhbvcard> getAll() {
		return this.dL.getAll();
	}

	public boolean s(String masterid) {
		int exist = this.dL.isYGExist(masterid);
		return exist > 0;
	}

	public CtgBk001Crbhbvcard getByMasterId(String masterid) {
		return this.dL.getByMasterId(masterid);
	}
}