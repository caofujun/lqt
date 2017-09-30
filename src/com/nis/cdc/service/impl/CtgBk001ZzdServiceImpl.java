package com.nis.cdc.service.impl;

import com.nis.cdc.dao.CtgBk001ZzdDao;
import com.nis.cdc.entity.CtgBk001Zzd;
import com.nis.cdc.service.CtgBk001ZzdService;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CtgBk001ZzdServiceImpl implements CtgBk001ZzdService {
	@Autowired
	private CtgBk001ZzdDao dP;

	public void save(CtgBk001Zzd ctgBk001Zzd) {
		this.dP.save(ctgBk001Zzd);
	}

	public void delete(String masterid) {
		this.dP.delete(masterid);
	}

	public void update(CtgBk001Zzd ctgBk001Zzd) {
		this.dP.update(ctgBk001Zzd);
	}

	public CtgBk001Zzd get(String subid) {
		return this.dP.get(subid);
	}

	public MyPage<CtgBk001Zzd> a(CtgBk001Zzd ctgBk001Zzd) {
		int total = this.dP.findCtgBk001ZzdCount(ctgBk001Zzd);
		List data = null;
		if (total > 0) {
			data = this.dP.findCtgBk001Zzd(ctgBk001Zzd);
		}

		return new MyPage(ctgBk001Zzd.getPage().intValue(), ctgBk001Zzd.getSize().intValue(), total, data);
	}

	public List<CtgBk001Zzd> getAll() {
		return this.dP.getAll();
	}

	public boolean v(String masterid) {
		int exist = this.dP.isFjhExist(masterid);
		return exist > 0;
	}

	public CtgBk001Zzd getByMasterid(String masterid) {
		return this.dP.getByMasterid(masterid);
	}
}