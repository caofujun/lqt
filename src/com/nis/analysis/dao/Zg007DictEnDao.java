package com.nis.analysis.dao;

import com.nis.analysis.entity.Zg007DictEn;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Zg007DictEnDao {
	void save(Zg007DictEn arg0);

	void delete(@Param("keyid") String arg0);

	void update(Zg007DictEn arg0);

	Zg007DictEn get(@Param("keyid") String arg0);

	List<Zg007DictEn> findZg007DictEn(Zg007DictEn arg0);

	int findZg007DictEnCount(Zg007DictEn arg0);

	List<Zg007DictEn> getAll();
}