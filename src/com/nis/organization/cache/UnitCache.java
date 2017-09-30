package com.nis.organization.cache;

import com.nis.comm.enums.bg;
import com.nis.comm.utils.d;
import com.nis.organization.dao.UnitDao;
import com.nis.organization.entity.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnitCache {
	@Autowired
	private UnitDao vS;

	public Unit get(String id) {
		String key = d.a(bg.mG, id);
		Unit data = (Unit) d.get(key);
		if (data == null) {
			data = this.vS.get(id);
			d.set(key, data);
		}

		return data;
	}

	public void a(String id) {
		d.a(d.a(bg.mG, id));
	}
}