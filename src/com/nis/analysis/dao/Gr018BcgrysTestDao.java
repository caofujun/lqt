package com.nis.analysis.dao;

import com.nis.analysis.entity.Gr018BcgrysTest;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Gr018BcgrysTestDao {
	void save(Gr018BcgrysTest arg0);

	void saveList(@Param("gr018BcgrysList") List<Gr018BcgrysTest> arg0);

	void delete(@Param("zyid") String arg0, @Param("bcid") String arg1);

	void update(Gr018BcgrysTest arg0);

	Gr018BcgrysTest get(@Param("zyid") String arg0);

	List<Gr018BcgrysTest> findGr018Bcgrys(Gr018BcgrysTest arg0);

	int findGr018BcgrysCount(Gr018BcgrysTest arg0);

	int findGrysCount(@Param("zyid") String arg0, @Param("bcid") String arg1);

	List<Gr018BcgrysTest> getAll();

	Date getMonitorPatientBcLastAt();
}