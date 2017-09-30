package com.nis.analysis.dao;

import com.nis.analysis.entity.Gr019YsgrmxTest;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Gr019YsgrmxTestDao {
	void save(Gr019YsgrmxTest arg0);

	void saveList(@Param("gr019List") List<Gr019YsgrmxTest> arg0);

	void delete(@Param("zyid") String arg0);

	void update(Gr019YsgrmxTest arg0);

	Gr019YsgrmxTest get(@Param("zyid") String arg0);

	List<Gr019YsgrmxTest> findGr019YsgrmxTest(Gr019YsgrmxTest arg0);

	int findGr019YsgrmxTestCount(Gr019YsgrmxTest arg0);

	List<Gr019YsgrmxTest> getAll();

	List<Gr019YsgrmxTest> getbyZyid(@Param("zyid") String arg0);
}