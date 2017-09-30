package com.nis.cdc.service.impl;

import com.nis.cdc.dao.CtgSys002ParamsDao;
import com.nis.cdc.entity.CtgSys002Params;
import com.nis.cdc.service.CtgSys002ParamsService;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CtgSys002ParamsServiceImpl implements CtgSys002ParamsService {
	@Autowired
	private CtgSys002ParamsDao eb;

	public void save(CtgSys002Params ctgSys002Params) {
		this.eb.save(ctgSys002Params);
	}

	public void delete(String paramid) {
		this.eb.delete(paramid);
	}

	public void update(CtgSys002Params ctgSys002Params) {
		this.eb.update(ctgSys002Params);
	}

	public CtgSys002Params get(String paramid) {
		return this.eb.get(paramid);
	}

	public MyPage<CtgSys002Params> a(CtgSys002Params ctgSys002Params) {
		int total = this.eb.findCtgSys002ParamsCount(ctgSys002Params);
		List data = null;
		if (total > 0) {
			data = this.eb.findCtgSys002Params(ctgSys002Params);
		}

		return new MyPage(ctgSys002Params.getPage().intValue(), ctgSys002Params.getSize().intValue(), total, data);
	}

	public List<CtgSys002Params> getAll() {
		return this.eb.getAll();
	}
}