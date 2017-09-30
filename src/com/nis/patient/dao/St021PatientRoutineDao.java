package com.nis.patient.dao;

import com.nis.patient.entity.St021PatientRoutine;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface St021PatientRoutineDao {
	void save(St021PatientRoutine arg0);

	void delete(@Param("id") String arg0);

	void update(St021PatientRoutine arg0);

	St021PatientRoutine get(@Param("id") String arg0);

	List<St021PatientRoutine> findSt021PatientRoutine(St021PatientRoutine arg0);

	int findSt021PatientRoutineCount(St021PatientRoutine arg0);

	List<St021PatientRoutine> getAll();
}