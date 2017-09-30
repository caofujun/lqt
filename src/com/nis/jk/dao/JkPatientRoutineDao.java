package com.nis.jk.dao;

import com.nis.jk.entity.JkPatientRoutine;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JkPatientRoutineDao {
	void save(JkPatientRoutine arg0);

	void delete(@Param("id") String arg0);

	void update(JkPatientRoutine arg0);

	JkPatientRoutine get(@Param("id") String arg0);

	List<JkPatientRoutine> findJkPatientRoutine(JkPatientRoutine arg0);

	int findJkPatientRoutineCount(JkPatientRoutine arg0);

	List<JkPatientRoutine> getAll();

	void updateFlag(JkPatientRoutine arg0);
}