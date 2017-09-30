package com.nis.analysis.service.impl;

import com.nis.analysis.dao.NyUnanalyzeBcDao;
import com.nis.analysis.entity.NyUnanalyzeBc;
import com.nis.analysis.service.NyUnanalyzeBcService;
import com.nis.comm.entity.MyPage;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NyUnanalyzeBcServiceImpl implements NyUnanalyzeBcService {
	@Autowired
	private NyUnanalyzeBcDao cz;

	public void save(NyUnanalyzeBc nyUnanalyzeBc) {
		this.cz.save(nyUnanalyzeBc);
	}

	public void delete(String dcname) {
		this.cz.delete(dcname);
	}

	public void update(NyUnanalyzeBc nyUnanalyzeBc) {
		this.cz.update(nyUnanalyzeBc);
	}

	public NyUnanalyzeBc get(String dcname) {
		return this.cz.get(dcname);
	}

	public MyPage<NyUnanalyzeBc> a(NyUnanalyzeBc nyUnanalyzeBc) {
		int total = this.cz.findNyUnanalyzeBcCount(nyUnanalyzeBc);
		List data = null;
		if (total > 0) {
			data = this.cz.findNyUnanalyzeBc(nyUnanalyzeBc);
		}

		return new MyPage(nyUnanalyzeBc.getPage().intValue(), nyUnanalyzeBc.getSize().intValue(), total, data);
	}

	public List<NyUnanalyzeBc> getAll() {
		return this.cz.getAll();
	}

	public Map<String, NyUnanalyzeBc> getUnBcMap() {
		HashMap map = new HashMap();
		List list = this.getAll();
		if (list != null) {
			Iterator arg3 = list.iterator();

			while (arg3.hasNext()) {
				NyUnanalyzeBc bc = (NyUnanalyzeBc) arg3.next();
				if (bc.getBcName() != null && !"".equals(bc.getBcName())) {
					map.put(bc.getBcName(), bc);
				}
			}
		}

		return map;
	}
}