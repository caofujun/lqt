package com.nis.hygiene.dao;

import com.nis.hygiene.entity.Hw016Role;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Hw016RoleDao {
	void save(Hw016Role arg0);

	void delete(@Param("roleId") String arg0);

	void update(Hw016Role arg0);

	Hw016Role get(@Param("roleId") String arg0);

	List<Hw016Role> findHw016Role(Hw016Role arg0);

	int findHw016RoleCount(Hw016Role arg0);

	List<Hw016Role> getAll();

	List<Hw016Role> findList(Hw016Role arg0);

	void updHw016Role(Hw016Role arg0);

	List<Hw016Role> findListJoinHw018(@Param("userId") String arg0);
}