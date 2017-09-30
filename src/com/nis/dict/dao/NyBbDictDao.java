package com.nis.dict.dao;

import com.nis.dict.entity.NyBbDict;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NyBbDictDao {
	void save(NyBbDict arg0);

	void delete(@Param("bbid") String arg0);

	void update(NyBbDict arg0);

	NyBbDict get(@Param("bbid") String arg0);

	List<NyBbDict> findNyBbDict(NyBbDict arg0);

	int findNyBbDictCount(NyBbDict arg0);

	List<NyBbDict> getAll();
}