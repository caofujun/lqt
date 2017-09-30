package com.nis.analysis.dao;

import com.nis.analysis.entity.Zg007Dict;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Zg007DictDao {
	void save(Zg007Dict arg0);

	void delete(@Param("keyid") String arg0);

	void update(Zg007Dict arg0);

	Zg007Dict get(@Param("keyid") String arg0);

	Zg007Dict getByElementId(@Param("elementId") String arg0);

	List<Zg007Dict> findZg007Dict(Zg007Dict arg0);

	int findZg007DictCount(Zg007Dict arg0);

	List<Zg007Dict> getAll();

	List<Zg007Dict> getByClass(@Param("itemClass") String arg0, @Param("dictType") String arg1);
}