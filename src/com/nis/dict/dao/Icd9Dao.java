package com.nis.dict.dao;

import com.nis.dict.entity.Icd9;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Icd9Dao {
	void save(Icd9 arg0);

	void delete(@Param("icdId") String arg0);

	void update(Icd9 arg0);

	Icd9 get(@Param("icdId") String arg0);

	List<Icd9> findIcd9(Icd9 arg0);

	int findIcd9Count(Icd9 arg0);

	List<Icd9> getAll();
}