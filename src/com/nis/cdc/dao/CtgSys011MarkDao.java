package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgSys011Mark;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CtgSys011MarkDao {
	void save(CtgSys011Mark arg0);

	void delete(@Param("masterid") String arg0);

	void deleteByOtherField(@Param("mzzyid") String arg0, @Param("diseaseid") String arg1);

	void update(CtgSys011Mark arg0);

	CtgSys011Mark get(@Param("masterid") String arg0);

	List<CtgSys011Mark> findCtgSys011Mark(CtgSys011Mark arg0);

	int findCtgSys011MarkCount(CtgSys011Mark arg0);

	List<CtgSys011Mark> getAll();
}