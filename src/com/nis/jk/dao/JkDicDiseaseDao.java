package com.nis.jk.dao;

import com.nis.jk.entity.JkDicDisease;
import com.nis.zg.entity.Zg012Icd10;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JkDicDiseaseDao {
	void save(JkDicDisease arg0);

	void delete(@Param("id") String arg0);

	void update(JkDicDisease arg0);

	JkDicDisease get(@Param("id") String arg0);

	List<JkDicDisease> findJkDicDisease(JkDicDisease arg0);

	int findJkDicDiseaseCount(JkDicDisease arg0);

	List<JkDicDisease> getAll();

	void updateFlag(Zg012Icd10 arg0);
}