package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgSys009Yj;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CtgSys009YjDao {
	void save(CtgSys009Yj arg0);

	void delete(@Param("masterid") String arg0);

	void update(CtgSys009Yj arg0);

	CtgSys009Yj get(@Param("masterid") String arg0);

	List<CtgSys009Yj> findCtgSys009Yj(CtgSys009Yj arg0);

	int findCtgSys009YjCount(CtgSys009Yj arg0);

	List<CtgSys009Yj> getAll();

	List<CtgSys009Yj> isExit(CtgSys009Yj arg0);
}