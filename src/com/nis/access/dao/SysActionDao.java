package com.nis.access.dao;

import com.nis.access.entity.SysAction;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysActionDao {
	void save(SysAction arg0);

	void delete(@Param("id") String arg0);

	void update(SysAction arg0);

	SysAction get(@Param("id") String arg0);

	SysAction findSysAction(SysAction arg0);

	List<SysAction> findSysActionList(SysAction arg0);

	SysAction findByParamCode(@Param("funcType") String arg0, @Param("sourceHospital") Long arg1,
			@Param("sourceDepno") String arg2, @Param("sourceUser") String arg3);
}