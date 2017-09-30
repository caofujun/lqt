package com.nis.hygiene.dao;

import com.nis.hygiene.entity.Hw008Xmsq;
import com.nis.hygiene.entity.Hw018RoleUser;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Hw018RoleUserDao {
	void save(Hw018RoleUser arg0);

	void delete(@Param("userId") String arg0, @Param("roleId") String arg1);

	void update(Hw018RoleUser arg0);

	Hw018RoleUser get(@Param("userId") String arg0, @Param("roleId") String arg1);

	List<Hw018RoleUser> findHw018RoleUser(Hw018RoleUser arg0);

	int findHw018RoleUserCount(Hw018RoleUser arg0);

	List<Hw018RoleUser> getAll();

	void delByRoleId(@Param("roleId") String arg0);

	void delByUserIdAndDeptId(@Param("userId") String arg0);

	void delHw018RoleUserNotIn(Hw008Xmsq arg0);
}