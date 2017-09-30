package com.nis.access.dao;

import com.nis.access.entity.AcAccountrole;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AcAccountroleDao {
	void save(AcAccountrole arg0);

	void delete(@Param("id") String arg0);

	void update(AcAccountrole arg0);

	AcAccountrole get(@Param("id") String arg0);

	List<AcAccountrole> findAcAccountrole(AcAccountrole arg0);

	int findAcAccountroleCount(AcAccountrole arg0);

	List<AcAccountrole> getAll();

	List<String> findByUserid(@Param("userId") String arg0);

	void deleteByUserid(@Param("userId") String arg0);

	int getByRoleId(@Param("roleId") String arg0);
}