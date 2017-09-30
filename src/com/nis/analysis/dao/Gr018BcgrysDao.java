package com.nis.analysis.dao;

import com.nis.analysis.entity.Gr018Bcgrys;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Gr018BcgrysDao {
	void save(Gr018Bcgrys arg0);

	void saveList(@Param("gr018BcgrysList") List<Gr018Bcgrys> arg0, @Param("tablename") String arg1);

	void delete(@Param("zyid") String arg0, @Param("bcid") String arg1, @Param("tablename") String arg2);

	void update(Gr018Bcgrys arg0);

	Gr018Bcgrys get(@Param("zyid") String arg0);

	List<Gr018Bcgrys> findGr018Bcgrys(Gr018Bcgrys arg0);

	int findGr018BcgrysCount(Gr018Bcgrys arg0);

	int findGrysCount(@Param("zyid") String arg0, @Param("bcid") String arg1, @Param("tablename") String arg2);

	List<Gr018Bcgrys> getAll();

	Date getMonitorPatientBcLastAt();
}