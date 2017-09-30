package com.nis.analysis.service.impl;

import com.nis.analysis.dao.NyUnanalyzeDictDao;
import com.nis.analysis.entity.NyUnanalyzeDict;
import com.nis.analysis.service.NyUnanalyzeDictService;
import com.nis.comm.entity.MyPage;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NyUnanalyzeDictServiceImpl implements NyUnanalyzeDictService {
	@Autowired
	private NyUnanalyzeDictDao cA;

	public void save(NyUnanalyzeDict nyUnanalyzeDict) {
		this.cA.save(nyUnanalyzeDict);
	}

	public void delete(String id) {
		this.cA.delete(id);
	}

	public void update(NyUnanalyzeDict nyUnanalyzeDict) {
		this.cA.update(nyUnanalyzeDict);
	}

	public NyUnanalyzeDict get(String id) {
		return this.cA.get(id);
	}

	public MyPage<NyUnanalyzeDict> a(NyUnanalyzeDict nyUnanalyzeDict) {
		int total = this.cA.findNyUnanalyzeDictCount(nyUnanalyzeDict);
		List data = null;
		if (total > 0) {
			data = this.cA.findNyUnanalyzeDict(nyUnanalyzeDict);
		}

		return new MyPage(nyUnanalyzeDict.getPage().intValue(), nyUnanalyzeDict.getSize().intValue(), total, data);
	}

	public List<NyUnanalyzeDict> getAll() {
		return this.cA.getAll();
	}

	public Map<String, NyUnanalyzeDict> getUnDictMap() {
		HashMap map = new HashMap();
		List list = this.getAll();
		if (list != null) {
			Iterator arg3 = list.iterator();

			while (arg3.hasNext()) {
				NyUnanalyzeDict d = (NyUnanalyzeDict) arg3.next();
				map.put(d.getDcName(), d);
			}
		}

		return map;
	}
}