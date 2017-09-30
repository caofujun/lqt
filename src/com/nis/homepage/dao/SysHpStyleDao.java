package com.nis.homepage.dao;

import com.nis.homepage.entity.SysHpStyle;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysHpStyleDao {
	void save(SysHpStyle arg0);

	void delete(@Param("id") String arg0);

	void update(SysHpStyle arg0);

	SysHpStyle get(@Param("id") String arg0);

	List<SysHpStyle> findSysHpStyle(SysHpStyle arg0);

	int findSysHpStyleCount(SysHpStyle arg0);

	List<SysHpStyle> getAll();

	List<SysHpStyle> findByUnitIdDepNoUserName(@Param("scopeLevel") Long arg0, @Param("unitId") String arg1,
			@Param("depNo") String arg2, @Param("userName") String arg3);
}