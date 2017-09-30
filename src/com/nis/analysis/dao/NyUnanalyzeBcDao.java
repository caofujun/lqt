package com.nis.analysis.dao;

import com.nis.analysis.entity.NyUnanalyzeBc;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NyUnanalyzeBcDao {
	void save(NyUnanalyzeBc arg0);

	void delete(@Param("id") String arg0);

	void update(NyUnanalyzeBc arg0);

	NyUnanalyzeBc get(@Param("id") String arg0);

	List<NyUnanalyzeBc> findNyUnanalyzeBc(NyUnanalyzeBc arg0);

	int findNyUnanalyzeBcCount(NyUnanalyzeBc arg0);

	List<NyUnanalyzeBc> getAll();
}