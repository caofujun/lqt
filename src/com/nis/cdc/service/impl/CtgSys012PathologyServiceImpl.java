package com.nis.cdc.service.impl;

import com.nis.cdc.dao.CtgSys012PathologyDao;
import com.nis.cdc.entity.CtgSys012Pathology;
import com.nis.cdc.service.CtgSys012PathologyService;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CtgSys012PathologyServiceImpl implements CtgSys012PathologyService {
	@Autowired
	private CtgSys012PathologyDao em;

	public void save(CtgSys012Pathology ctgSys012Pathology) {
		this.em.save(ctgSys012Pathology);
	}

	public void delete(String pathologyno) {
		this.em.delete(pathologyno);
	}

	public void update(CtgSys012Pathology ctgSys012Pathology) {
		this.em.update(ctgSys012Pathology);
	}

	public CtgSys012Pathology get(String pathologyno) {
		return this.em.get(pathologyno);
	}

	public MyPage<CtgSys012Pathology> a(CtgSys012Pathology ctgSys012Pathology) {
		int total = this.em.findCtgSys012PathologyCount(ctgSys012Pathology);
		List data = null;
		if (total > 0) {
			data = this.em.findCtgSys012Pathology(ctgSys012Pathology);
		}

		return new MyPage(ctgSys012Pathology.getPage().intValue(), ctgSys012Pathology.getSize().intValue(), total,
				data);
	}

	public List<CtgSys012Pathology> getAll() {
		return this.em.getAll();
	}

	public boolean w(String pathologyno) {
		List list = this.em.isNoExist(pathologyno);
		return list != null && list.size() > 0;
	}
}