package com.nis.jk.dao;

import com.nis.jk.entity.JkPatientDiagnose;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JkPatientDiagnoseDao {
	void save(JkPatientDiagnose arg0);

	void delete(@Param("id") String arg0);

	void update(JkPatientDiagnose arg0);

	JkPatientDiagnose get(@Param("id") String arg0);

	List<JkPatientDiagnose> findJkPatientDiagnose(JkPatientDiagnose arg0);

	int findJkPatientDiagnoseCount(JkPatientDiagnose arg0);

	List<JkPatientDiagnose> getAll();

	void updateFlag(JkPatientDiagnose arg0);
}