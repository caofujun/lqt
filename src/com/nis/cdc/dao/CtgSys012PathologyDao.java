package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgSys012Pathology;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CtgSys012PathologyDao {
	void save(CtgSys012Pathology arg0);

	void delete(@Param("pathologyno") String arg0);

	void update(CtgSys012Pathology arg0);

	CtgSys012Pathology get(@Param("pathologyno") String arg0);

	List<CtgSys012Pathology> findCtgSys012Pathology(CtgSys012Pathology arg0);

	int findCtgSys012PathologyCount(CtgSys012Pathology arg0);

	List<CtgSys012Pathology> getAll();

	List<CtgSys012Pathology> isNoExist(@Param("pathologyno") String arg0);
}