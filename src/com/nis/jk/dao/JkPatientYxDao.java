package com.nis.jk.dao;

import com.nis.jk.entity.JkPatientYx;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JkPatientYxDao {
	void save(JkPatientYx arg0);

	void delete(@Param("id") String arg0);

	void update(JkPatientYx arg0);

	JkPatientYx get(@Param("id") String arg0);

	List<JkPatientYx> findJkPatientYx(JkPatientYx arg0);

	int findJkPatientYxCount(JkPatientYx arg0);

	List<JkPatientYx> getAll();

	void updateFlag(JkPatientYx arg0);
}