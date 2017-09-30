package com.nis.patient.service;

import com.nis.comm.entity.MyPage;
import com.nis.patient.entity.St021PatientRoutine;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface St021PatientRoutineService {
	void save(St021PatientRoutine arg0);

	void delete(String arg0);

	void update(St021PatientRoutine arg0);

	St021PatientRoutine get(String arg0);

	MyPage<St021PatientRoutine> a(St021PatientRoutine arg0);

	List<St021PatientRoutine> getAll();
}