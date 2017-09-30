package com.nis.cdc.service.impl;

import com.nis.cdc.dao.CtgSys005LisruleMasterDao;
import com.nis.cdc.entity.CtgSys005LisruleMaster;
import com.nis.cdc.service.CtgSys005LisruleMasterService;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CtgSys005LisruleMasterServiceImpl implements CtgSys005LisruleMasterService {
	@Autowired
	private CtgSys005LisruleMasterDao eg;

	public void save(CtgSys005LisruleMaster crbYjLisruleMaster) {
		this.eg.save(crbYjLisruleMaster);
	}

	public void delete(Long orderno) {
		this.eg.delete(orderno);
	}

	public void update(CtgSys005LisruleMaster crbYjLisruleMaster) {
		this.eg.update(crbYjLisruleMaster);
	}

	public CtgSys005LisruleMaster get(Long orderno) {
		return this.eg.get(orderno);
	}

	public MyPage<CtgSys005LisruleMaster> a(CtgSys005LisruleMaster crbYjLisruleMaster) {
		int total = this.eg.findCrbYjLisruleMasterCount(crbYjLisruleMaster);
		List data = null;
		if (total > 0) {
			data = this.eg.findCrbYjLisruleMaster(crbYjLisruleMaster);
		}

		return new MyPage(crbYjLisruleMaster.getPage().intValue(), crbYjLisruleMaster.getSize().intValue(), total,
				data);
	}

	public List<CtgSys005LisruleMaster> getAll() {
		return this.eg.getAll();
	}
}