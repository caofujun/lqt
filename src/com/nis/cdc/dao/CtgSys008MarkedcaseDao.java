package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgSys008Markedcase;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CtgSys008MarkedcaseDao {
	void save(CtgSys008Markedcase arg0);

	void delete(@Param("id") String arg0);

	void update(CtgSys008Markedcase arg0);

	CtgSys008Markedcase get(@Param("id") String arg0);

	List<CtgSys008Markedcase> findCtgSys008Markedcase(CtgSys008Markedcase arg0);

	int findCtgSys008MarkedcaseCount(CtgSys008Markedcase arg0);

	List<CtgSys008Markedcase> getAll();
}