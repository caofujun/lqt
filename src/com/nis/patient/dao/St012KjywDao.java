package com.nis.patient.dao;

import com.nis.patient.entity.St012Kjyw;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.ibatis.annotations.Param;

public interface St012KjywDao {
	void save(St012Kjyw arg0);

	void delete(@Param("id") String arg0);

	void update(St012Kjyw arg0);

	St012Kjyw get(@Param("id") String arg0);

	List<St012Kjyw> findSt012Kjyw(St012Kjyw arg0);

	int findSt012KjywCount(St012Kjyw arg0);

	List<St012Kjyw> getAll();

	List<St012Kjyw> findKjywByDrugId(@Param("drugId") String arg0);

	Set<String> getByLevel(@Param("drugLine") Integer arg0);

	void updDrugLine(@Param("id") String arg0, @Param("drugLine") Integer arg1, @Param("updDate") Date arg2);

	Integer getDrugLine(@Param("drugName") String arg0);

	Map<String, String> queryMatched();

	void match();
}