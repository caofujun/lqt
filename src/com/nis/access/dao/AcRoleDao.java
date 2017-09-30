package com.nis.access.dao;

import com.nis.access.entity.AcRole;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AcRoleDao {
	void save(AcRole arg0);

	void delete(@Param("roleId") String arg0);

	void update(AcRole arg0);

	AcRole get(@Param("roleId") String arg0);

	List<AcRole> findAcRole(AcRole arg0);

	int findAcRoleCount(AcRole arg0);

	List<AcRole> getAll();

	List<AcRole> getRoleByUnitId(@Param("unitId") String arg0, @Param("acType") String arg1);
}