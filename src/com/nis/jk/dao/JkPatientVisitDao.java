package com.nis.jk.dao;

import com.nis.jk.entity.JkPatientVisit;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JkPatientVisitDao {
	void save(JkPatientVisit arg0);

	void delete(@Param("zyid") String arg0);

	void update(JkPatientVisit arg0);

	void updateFlag(JkPatientVisit arg0);

	JkPatientVisit get(@Param("zyid") String arg0);

	List<JkPatientVisit> findJkPatientVisit(JkPatientVisit arg0);

	int findJkPatientVisitCount(JkPatientVisit arg0);

	List<JkPatientVisit> getAll();
}