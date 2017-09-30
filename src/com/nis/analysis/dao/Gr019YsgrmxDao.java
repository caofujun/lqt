package com.nis.analysis.dao;

import com.nis.analysis.entity.Gr019Ysgrmx;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Gr019YsgrmxDao {
	void save(Gr019Ysgrmx arg0);

	void delete(@Param("zyid") String arg0, @Param("tablename") String arg1);

	void update(Gr019Ysgrmx arg0);

	Gr019Ysgrmx get(@Param("zyid") String arg0);

	List<Gr019Ysgrmx> findGr019Ysgrmx(Gr019Ysgrmx arg0);

	int findGr019YsgrmxCount(Gr019Ysgrmx arg0);

	List<Gr019Ysgrmx> getAll();

	Date getMonitorPatientGrLastAt();

	void saveList(@Param("gr019List") List<Gr019Ysgrmx> arg0, @Param("tablename") String arg1);

	List<Gr019Ysgrmx> getbyZyid(@Param("zyid") String arg0, @Param("tablename") String arg1);
}