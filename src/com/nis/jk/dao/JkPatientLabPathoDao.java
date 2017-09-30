package com.nis.jk.dao;

import com.nis.jk.entity.JkPatientLabPatho;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JkPatientLabPathoDao {
	void save(JkPatientLabPatho arg0);

	void delete(@Param("id") String arg0);

	void update(JkPatientLabPatho arg0);

	JkPatientLabPatho get(@Param("id") String arg0);

	List<JkPatientLabPatho> findJkPatientLabPatho(JkPatientLabPatho arg0);

	int findJkPatientLabPathoCount(JkPatientLabPatho arg0);

	List<JkPatientLabPatho> getAll();

	void updateFlag(JkPatientLabPatho arg0);
}