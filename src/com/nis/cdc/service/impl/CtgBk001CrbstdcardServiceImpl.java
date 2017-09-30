package com.nis.cdc.service.impl;

import com.nis.cdc.dao.CtgBk001CrbstdcardDao;
import com.nis.cdc.entity.CtgBk001Crbstdcard;
import com.nis.cdc.service.CtgBk001CrbstdcardService;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CtgBk001CrbstdcardServiceImpl implements CtgBk001CrbstdcardService {
	@Autowired
	private CtgBk001CrbstdcardDao dN;

	public void save(CtgBk001Crbstdcard ctgBk001Crbstdcard) {
		this.dN.save(ctgBk001Crbstdcard);
	}

	public void delete(String masterid) {
		this.dN.delete(masterid);
	}

	public void update(CtgBk001Crbstdcard ctgBk001Crbstdcard) {
		this.dN.update(ctgBk001Crbstdcard);
	}

	public CtgBk001Crbstdcard get(String masterid) {
		return this.dN.get(masterid);
	}

	public MyPage<CtgBk001Crbstdcard> a(CtgBk001Crbstdcard ctgBk001Crbstdcard) {
		int total = this.dN.findCtgBk001CrbstdcardCount(ctgBk001Crbstdcard);
		List data = null;
		if (total > 0) {
			data = this.dN.findCtgBk001Crbstdcard(ctgBk001Crbstdcard);
		}

		return new MyPage(ctgBk001Crbstdcard.getPage().intValue(), ctgBk001Crbstdcard.getSize().intValue(), total,
				data);
	}

	public List<CtgBk001Crbstdcard> getAll() {
		return this.dN.getAll();
	}

	public boolean t(String masterid) {
		int exist = this.dN.isHIVExist(masterid);
		return exist > 0;
	}
}