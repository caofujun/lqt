package com.nis.param.dao;

import com.nis.param.entity.SysParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysParamDao {
	void save(SysParam arg0);

	void delete(@Param("id") String arg0);

	void update(SysParam arg0);

	SysParam get(@Param("id") String arg0);

	List<SysParam> findSysParam(SysParam arg0);

	int findSysParamCount(SysParam arg0);

	List<SysParam> getAll();

	List<SysParam> findSysParamList(SysParam arg0);

	SysParam findByParamCode(@Param("paramCode") String arg0, @Param("unitId") String arg1, @Param("depNo") String arg2,
			@Param("userName") String arg3);
}