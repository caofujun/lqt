package com.nis.jk.dao;

import com.nis.jk.entity.JkPatientBc;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JkPatientBcDao {
	void save(JkPatientBc arg0);

	void delete(@Param("id") String arg0);

	void update(JkPatientBc arg0);

	JkPatientBc get(@Param("id") String arg0);

	List<JkPatientBc> findJkPatientBc(JkPatientBc arg0);

	int findJkPatientBcCount(JkPatientBc arg0);

	List<JkPatientBc> getAll();

	void updateFlag(JkPatientBc arg0);
}