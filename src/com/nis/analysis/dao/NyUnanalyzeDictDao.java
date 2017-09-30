package com.nis.analysis.dao;

import com.nis.analysis.entity.NyUnanalyzeDict;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NyUnanalyzeDictDao {
	void save(NyUnanalyzeDict arg0);

	void delete(@Param("dcName") String arg0);

	void update(NyUnanalyzeDict arg0);

	NyUnanalyzeDict get(@Param("dcName") String arg0);

	List<NyUnanalyzeDict> findNyUnanalyzeDict(NyUnanalyzeDict arg0);

	int findNyUnanalyzeDictCount(NyUnanalyzeDict arg0);

	List<NyUnanalyzeDict> getAll();
}