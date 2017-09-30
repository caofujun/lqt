package com.nis.jk.dao;

import com.nis.jk.entity.JkPatientTemprature;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JkPatientTempratureDao {
	void save(JkPatientTemprature arg0);

	void delete(@Param("id") String arg0);

	void update(JkPatientTemprature arg0);

	JkPatientTemprature get(@Param("id") String arg0);

	List<JkPatientTemprature> findJkPatientTemprature(JkPatientTemprature arg0);

	int findJkPatientTempratureCount(JkPatientTemprature arg0);

	List<JkPatientTemprature> getAll();

	void updateFlag(JkPatientTemprature arg0);
}