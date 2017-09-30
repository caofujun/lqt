package com.nis.access.dao;

import com.nis.access.entity.AcMenu;
import com.nis.access.entity.AcPrivilege;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AcPrivilegeDao {
	void save(@Param("acPrivileges") List<AcPrivilege> arg0);

	void deleteRoleId(@Param("roleId") String arg0);

	void deleteReportAuthByRoleId(@Param("roleId") String arg0);

	void update(AcPrivilege arg0);

	AcPrivilege get(@Param("id") String arg0);

	List<AcPrivilege> findAcPrivilege(AcPrivilege arg0);

	int findAcPrivilegeCount(AcPrivilege arg0);

	List<AcPrivilege> getAll();

	List<AcPrivilege> findByRoleid(@Param("roleId") String arg0);

	List<AcMenu> findMenuByRoleid(@Param("roleId") String arg0, @Param("userId") String arg1);
}