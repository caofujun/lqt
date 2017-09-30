package com.nis.patient.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.patient.dao.St021PatientRoutineDao;
import com.nis.patient.entity.St021PatientRoutine;
import com.nis.patient.service.St021PatientRoutineService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class St021PatientRoutineServiceImpl implements St021PatientRoutineService {
	@Autowired
	private St021PatientRoutineDao ty;

	public void save(St021PatientRoutine st021PatientRoutine) {
		this.ty.save(st021PatientRoutine);
	}

	public void delete(String id) {
		this.ty.delete(id);
	}

	public void update(St021PatientRoutine st021PatientRoutine) {
		this.ty.update(st021PatientRoutine);
	}

	public St021PatientRoutine get(String id) {
		return this.ty.get(id);
	}

	public MyPage<St021PatientRoutine> a(St021PatientRoutine st021PatientRoutine) {
		int total = this.ty.findSt021PatientRoutineCount(st021PatientRoutine);
		List data = null;
		if (total > 0) {
			data = this.ty.findSt021PatientRoutine(st021PatientRoutine);
		}

		return new MyPage(st021PatientRoutine.getPage().intValue(), st021PatientRoutine.getSize().intValue(), total,
				data);
	}

	public List<St021PatientRoutine> getAll() {
		return this.ty.getAll();
	}
}