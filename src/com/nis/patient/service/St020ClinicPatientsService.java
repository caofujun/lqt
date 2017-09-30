package com.nis.patient.service;

import com.nis.comm.entity.DataWarning;
import com.nis.comm.entity.MyPage;
import com.nis.patient.entity.St020ClinicPatients;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface St020ClinicPatientsService {
	void save(St020ClinicPatients arg0);

	void delete(String arg0);

	void update(St020ClinicPatients arg0);

	St020ClinicPatients get(String arg0);

	St020ClinicPatients getByMzid(String arg0, String arg1);

	MyPage<St020ClinicPatients> c(St020ClinicPatients arg0);

	List<St020ClinicPatients> getAll();

	MyPage<St020ClinicPatients> d(St020ClinicPatients arg0);

	MyPage<St020ClinicPatients> e(St020ClinicPatients arg0);

	MyPage<St020ClinicPatients> f(St020ClinicPatients arg0);

	MyPage<St020ClinicPatients> g(St020ClinicPatients arg0);

	List<St020ClinicPatients> getByPatientId(String arg0);

	List<St020ClinicPatients> findByPatientIdAndVisitId(String arg0, Integer arg1);

	List<St020ClinicPatients> findMzZdList(int arg0);

	List<DataWarning> findPatentClinicWarning(Date arg0, Date arg1);
}