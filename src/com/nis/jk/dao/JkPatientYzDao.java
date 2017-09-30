package com.nis.jk.dao;

import com.nis.jk.entity.JkPatientYz;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JkPatientYzDao {
	void save(JkPatientYz arg0);

	void delete(@Param("id") String arg0);

	void update(JkPatientYz arg0);

	JkPatientYz get(@Param("id") String arg0);

	List<JkPatientYz> findJkPatientYz(JkPatientYz arg0);

	int findJkPatientYzCount(JkPatientYz arg0);

	List<JkPatientYz> getAll();

	void updateFlag(JkPatientYz arg0);
}