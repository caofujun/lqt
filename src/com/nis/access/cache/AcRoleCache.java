package com.nis.access.cache;

import com.nis.access.dao.AcRoleDao;
import com.nis.access.entity.AcRole;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.d;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AcRoleCache {
	@Autowired
	private AcRoleDao a;

	public AcRole get(String id) {
		String key = d.a(bg.mA, id);
		AcRole data = (AcRole) d.get(key);
		if (data == null) {
			data = this.a.get(id);
			d.set(key, data);
		}

		return data;
	}

	public void a(String id) {
		d.a(d.a(bg.mA, id));
	}
}