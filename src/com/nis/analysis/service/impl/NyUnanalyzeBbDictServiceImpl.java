package com.nis.analysis.service.impl;

import com.nis.analysis.dao.NyUnanalyzeBbDictDao;
import com.nis.analysis.entity.NyUnanalyzeBbDict;
import com.nis.analysis.service.NyUnanalyzeBbDictService;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NyUnanalyzeBbDictServiceImpl implements NyUnanalyzeBbDictService {
	@Autowired
	private NyUnanalyzeBbDictDao cy;

	public void save(NyUnanalyzeBbDict nyUnanalyzeBbDict) {
		this.cy.save(nyUnanalyzeBbDict);
	}

	public void delete(String noDictName) {
		this.cy.delete(noDictName);
	}

	public void update(NyUnanalyzeBbDict nyUnanalyzeBbDict) {
		this.cy.update(nyUnanalyzeBbDict);
	}

	public NyUnanalyzeBbDict get(String noDictName) {
		return this.cy.get(noDictName);
	}

	public MyPage<NyUnanalyzeBbDict> a(NyUnanalyzeBbDict nyUnanalyzeBbDict) {
		int total = this.cy.findNyUnanalyzeBbDictCount(nyUnanalyzeBbDict);
		List data = null;
		if (total > 0) {
			data = this.cy.findNyUnanalyzeBbDict(nyUnanalyzeBbDict);
		}

		return new MyPage(nyUnanalyzeBbDict.getPage().intValue(), nyUnanalyzeBbDict.getSize().intValue(), total, data);
	}

	public List<NyUnanalyzeBbDict> getAll() {
		return this.cy.getAll();
	}
}