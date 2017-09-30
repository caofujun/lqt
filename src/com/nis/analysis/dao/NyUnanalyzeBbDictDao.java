package com.nis.analysis.dao;

import com.nis.analysis.entity.NyUnanalyzeBbDict;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NyUnanalyzeBbDictDao {
	void save(NyUnanalyzeBbDict arg0);

	void delete(@Param("noDictName") String arg0);

	void update(NyUnanalyzeBbDict arg0);

	NyUnanalyzeBbDict get(@Param("noDictName") String arg0);

	List<NyUnanalyzeBbDict> findNyUnanalyzeBbDict(NyUnanalyzeBbDict arg0);

	int findNyUnanalyzeBbDictCount(NyUnanalyzeBbDict arg0);

	List<NyUnanalyzeBbDict> getAll();
}