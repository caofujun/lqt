package com.nis.analysis.dao;

import com.nis.analysis.entity.Zg006ZdmxEn;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Zg006ZdmxEnDao {
	void save(Zg006ZdmxEn arg0);

	void delete(@Param("mxId") String arg0);

	void update(Zg006ZdmxEn arg0);

	Zg006ZdmxEn get(@Param("mxId") String arg0);

	List<Zg006ZdmxEn> findZg006ZdmxEn(Zg006ZdmxEn arg0);

	int findZg006ZdmxEnCount(Zg006ZdmxEn arg0);

	List<Zg006ZdmxEn> getAll();
}