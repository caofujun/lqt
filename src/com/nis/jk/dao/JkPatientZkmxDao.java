package com.nis.jk.dao;

import com.nis.jk.entity.JkPatientZkmx;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JkPatientZkmxDao {
	void save(JkPatientZkmx arg0);

	void delete(@Param("id") String arg0);

	void update(JkPatientZkmx arg0);

	JkPatientZkmx get(@Param("id") String arg0);

	List<JkPatientZkmx> findJkPatientZkmx(JkPatientZkmx arg0);

	int findJkPatientZkmxCount(JkPatientZkmx arg0);

	List<JkPatientZkmx> getAll();

	void updateFlag(JkPatientZkmx arg0);
}