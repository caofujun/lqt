package com.nis.jk.dao;

import com.nis.jk.entity.JkDicDrug;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JkDicDrugDao {
	void save(JkDicDrug arg0);

	void delete(@Param("id") String arg0);

	void update(JkDicDrug arg0);

	JkDicDrug get(@Param("id") String arg0);

	List<JkDicDrug> findJkDicDrug(JkDicDrug arg0);

	int findJkDicDrugCount(JkDicDrug arg0);

	List<JkDicDrug> getAll();

	void updateFlag(JkDicDrug arg0);
}