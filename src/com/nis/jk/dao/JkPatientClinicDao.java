package com.nis.jk.dao;

import com.nis.jk.entity.JkPatientClinic;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JkPatientClinicDao {
	void save(JkPatientClinic arg0);

	void delete(@Param("id") String arg0);

	void update(JkPatientClinic arg0);

	JkPatientClinic get(@Param("id") String arg0);

	List<JkPatientClinic> findJkPatientClinic(JkPatientClinic arg0);

	int findJkPatientClinicCount(JkPatientClinic arg0);

	List<JkPatientClinic> getAll();

	void updateFlag(JkPatientClinic arg0);
}