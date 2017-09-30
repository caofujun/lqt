package com.nis.analysis.dao;

import com.nis.analysis.entity.NyUnanalyzeBbByt;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NyUnanalyzeBbBytDao {
	void save(NyUnanalyzeBbByt arg0);

	void delete(@Param("bytid") String arg0);

	void update(NyUnanalyzeBbByt arg0);

	NyUnanalyzeBbByt get(@Param("bytid") String arg0);

	List<NyUnanalyzeBbByt> findNyUnanalyzeBbByt(NyUnanalyzeBbByt arg0);

	int findNyUnanalyzeBbBytCount(NyUnanalyzeBbByt arg0);

	List<NyUnanalyzeBbByt> getAll();
}