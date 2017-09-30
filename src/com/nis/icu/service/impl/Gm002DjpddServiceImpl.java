package com.nis.icu.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.icu.dao.Gm002DjpddDao;
import com.nis.icu.entity.Gm002Djpdd;
import com.nis.icu.service.Gm002DjpddService;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Gm002DjpddServiceImpl implements Gm002DjpddService {
	@Autowired
	private Gm002DjpddDao sm;

	public void save(Gm002Djpdd gm002Djpdd) {
		Gm002Djpdd djpdd = this.sm.get(gm002Djpdd.getDtYear(), gm002Djpdd.getDtMonth(), gm002Djpdd.getWeeknumber(),
				gm002Djpdd.getZyid());
		if (djpdd == null) {
			this.sm.save(gm002Djpdd);
		} else {
			this.sm.update(gm002Djpdd);
		}

	}

	public void update(Gm002Djpdd gm002Djpdd) {
		this.sm.update(gm002Djpdd);
	}

	public MyPage<Gm002Djpdd> a(Gm002Djpdd gm002Djpdd) {
		int total = this.sm.findGm002DjpddCount(gm002Djpdd);
		List data = null;
		if (total > 0) {
			data = this.sm.findGm002Djpdd(gm002Djpdd);
		}

		return new MyPage(gm002Djpdd.getPage().intValue(), gm002Djpdd.getSize().intValue(), total, data);
	}

	public List<Gm002Djpdd> getAll() {
		return this.sm.getAll();
	}

	public List<Gm002Djpdd> getByDateAndDeptId(String deptId, String strDate, Integer dtYear, Integer dtMonth,
			Integer weeknumber) {
		List djpddList = this.sm.getByDateAndDeptId(deptId, strDate, dtYear, dtMonth, weeknumber);
		Iterator arg7 = djpddList.iterator();

		while (arg7.hasNext()) {
			Gm002Djpdd djpdd = (Gm002Djpdd) arg7.next();
			djpdd.setDtYear(dtYear);
			djpdd.setDtMonth(dtMonth);
			djpdd.setWeeknumber(weeknumber);
		}

		return djpddList;
	}

	public void saveList(List<Gm002Djpdd> djpddList) {
		Iterator arg2 = djpddList.iterator();

		while (arg2.hasNext()) {
			Gm002Djpdd gm002Djpdd = (Gm002Djpdd) arg2.next();
			this.save(gm002Djpdd);
		}

	}

	public List<Gm002Djpdd> getBedByDateAndDeptId(String deptId, String strDate, Integer dtYear, Integer dtMonth,
			Integer weeknumber) {
		List djpddList = this.sm.getBedByDateAndDeptId(deptId, strDate, dtYear, dtMonth, weeknumber);
		Iterator arg7 = djpddList.iterator();

		while (arg7.hasNext()) {
			Gm002Djpdd djpdd = (Gm002Djpdd) arg7.next();
			djpdd.setDtYear(dtYear);
			djpdd.setDtMonth(dtMonth);
			djpdd.setWeeknumber(weeknumber);
		}

		return djpddList;
	}
}