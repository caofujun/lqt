package com.nis.dict.dao;

import com.nis.dict.entity.Icd10;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Icd10Dao {
	void save(Icd10 arg0);

	void delete(@Param("icdId") String arg0);

	void update(Icd10 arg0);

	Icd10 get(@Param("icdId") String arg0);

	List<Icd10> findIcd10(Icd10 arg0);

	int findIcd10Count(Icd10 arg0);

	List<Icd10> getAll();
}