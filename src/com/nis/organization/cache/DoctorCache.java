package com.nis.organization.cache;

import com.nis.comm.enums.bg;
import com.nis.comm.utils.d;
import com.nis.organization.dao.DoctorDao;
import com.nis.organization.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorCache {
	@Autowired
	private DoctorDao vR;

	public Doctor get(String id) {
		String key = d.a(bg.mH, id);
		Doctor data = (Doctor) d.get(key);
		if (data == null) {
			data = this.vR.get(id);
			d.set(key, data);
		}

		return data;
	}

	public void a(String id) {
		d.a(d.a(bg.mH, id));
	}
}