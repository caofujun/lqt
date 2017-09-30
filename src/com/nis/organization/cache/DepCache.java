package com.nis.organization.cache;

import com.nis.comm.enums.bg;
import com.nis.comm.utils.d;
import com.nis.organization.dao.DepDao;
import com.nis.organization.entity.Dep;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepCache {
	@Autowired
	private DepDao vQ;

	public Dep F(String unitId, String id) {
		String key = d.a(bg.mJ, unitId + "_" + id);
		Dep data = (Dep) d.get(key);
		if (data == null) {
			List deps = this.vQ.getUnitIdDepId(unitId, id);
			data = deps.size() > 0 ? (Dep) deps.get(0) : null;
			d.set(key, data);
		}

		if (data == null) {
			data = new Dep();
		}

		return data;
	}

	public void s(String unitId, String id) {
		d.a(d.a(bg.mJ, unitId + "_" + id));
	}
}