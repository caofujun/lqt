package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgSys002Params;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CtgSys002ParamsDao {
	void save(CtgSys002Params arg0);

	void delete(@Param("paramid") String arg0);

	void update(CtgSys002Params arg0);

	CtgSys002Params get(@Param("paramid") String arg0);

	List<CtgSys002Params> findCtgSys002Params(CtgSys002Params arg0);

	int findCtgSys002ParamsCount(CtgSys002Params arg0);

	List<CtgSys002Params> getAll();
}