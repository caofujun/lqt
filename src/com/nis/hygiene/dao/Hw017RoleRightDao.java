package com.nis.hygiene.dao;

import com.nis.hygiene.entity.Hw017RoleRight;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Hw017RoleRightDao {
	void save(Hw017RoleRight arg0);

	void delete(@Param("roleId") String arg0, @Param("classId") String arg1);

	void update(Hw017RoleRight arg0);

	Hw017RoleRight get(@Param("roleId") String arg0, @Param("classId") String arg1);

	List<Hw017RoleRight> findHw017RoleRight(Hw017RoleRight arg0);

	int findHw017RoleRightCount(Hw017RoleRight arg0);

	List<Hw017RoleRight> getAll();

	List<Hw017RoleRight> findList(Hw017RoleRight arg0);

	void delByRoleId(@Param("roleId") String arg0);
}