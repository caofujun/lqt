package com.nis.dict.dao;

import com.nis.dict.entity.AntibiosisDrug;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AntibiosisDrugDao {
	void save(AntibiosisDrug arg0);

	void delete(@Param("drugId") String arg0);

	void update(AntibiosisDrug arg0);

	AntibiosisDrug get(@Param("drugId") String arg0);

	List<AntibiosisDrug> findAntibiosisDrug(AntibiosisDrug arg0);

	int findAntibiosisDrugCount(AntibiosisDrug arg0);

	List<AntibiosisDrug> getAll();

	List<HashMap<String, String>> findKjywyl(AntibiosisDrug arg0);
}