package com.nis.jk.dao;

import com.nis.jk.entity.JkPatientLabAnti;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JkPatientLabAntiDao {
	void save(JkPatientLabAnti arg0);

	void delete(@Param("id") String arg0);

	void update(JkPatientLabAnti arg0);

	JkPatientLabAnti get(@Param("id") String arg0);

	List<JkPatientLabAnti> findJkPatientLabAnti(JkPatientLabAnti arg0);

	int findJkPatientLabAntiCount(JkPatientLabAnti arg0);

	List<JkPatientLabAnti> getAll();

	void updateFlag(JkPatientLabAnti arg0);
}