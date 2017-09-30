package com.nis.dict.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.dict.dao.NyBbDictDao;
import com.nis.dict.entity.NyBbDict;
import com.nis.dict.service.NyBbDictService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NyBbDictServiceImpl implements NyBbDictService {
	@Autowired
	private NyBbDictDao qI;

	public void save(NyBbDict nyBbDict) {
		this.qI.save(nyBbDict);
	}

	public void delete(String bbid) {
		this.qI.delete(bbid);
	}

	public void update(NyBbDict nyBbDict) {
		this.qI.update(nyBbDict);
	}

	public NyBbDict get(String bbid) {
		return this.qI.get(bbid);
	}

	public MyPage<NyBbDict> a(NyBbDict nyBbDict) {
		int total = this.qI.findNyBbDictCount(nyBbDict);
		List data = null;
		if (total > 0) {
			data = this.qI.findNyBbDict(nyBbDict);
		}

		return new MyPage(nyBbDict.getPage().intValue(), nyBbDict.getSize().intValue(), total, data);
	}

	public List<NyBbDict> getAll() {
		return this.qI.getAll();
	}
}