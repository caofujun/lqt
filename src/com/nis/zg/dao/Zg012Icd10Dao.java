package com.nis.zg.dao;

import com.nis.zg.entity.Zg012Icd10;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Zg012Icd10Dao {
	void save(Zg012Icd10 arg0);

	void delete(@Param("icdId") String arg0);

	void update(Zg012Icd10 arg0);

	Zg012Icd10 get(@Param("icdId") String arg0);

	List<Zg012Icd10> findZg012Icd10(Zg012Icd10 arg0);

	int findZg012Icd10Count(Zg012Icd10 arg0);

	List<Zg012Icd10> getAll();
}