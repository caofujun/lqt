package com.nis.analysis.dao;

import com.nis.analysis.entity.Gr002YsgrMxTest;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Gr002YsgrMxTestDao {
	void save(Gr002YsgrMxTest arg0);

	void delete(@Param("regId") String arg0);

	void update(Gr002YsgrMxTest arg0);

	Gr002YsgrMxTest get(@Param("regId") String arg0);

	List<Gr002YsgrMxTest> findGr002YsgrMxTest(Gr002YsgrMxTest arg0);

	int findGr002YsgrMxTestCount(Gr002YsgrMxTest arg0);

	List<Gr002YsgrMxTest> getAll();

	void deleteByZyidState(@Param("zyid") String arg0);

	List<Gr002YsgrMxTest> findByZyidInfectCode(@Param("zyid") String arg0, @Param("infectCode") String arg1);
}