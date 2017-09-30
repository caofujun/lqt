package com.nis.analysis.dao;

import com.nis.analysis.entity.Gr019YsgrmxWeight;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Gr019YsgrmxWeightDao {
	void save(Gr019YsgrmxWeight arg0);

	void delete(@Param("zyid") String arg0);

	void update(Gr019YsgrmxWeight arg0);

	Gr019YsgrmxWeight get(@Param("zyid") String arg0, @Param("infectCode") String arg1);

	List<Gr019YsgrmxWeight> findGr019YsgrmxWeight(Gr019YsgrmxWeight arg0);

	int findGr019YsgrmxWeightCount(Gr019YsgrmxWeight arg0);

	List<Gr019YsgrmxWeight> getAll();
}