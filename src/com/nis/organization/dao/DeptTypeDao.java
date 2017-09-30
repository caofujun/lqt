package com.nis.organization.dao;

import com.nis.organization.entity.DeptType;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeptTypeDao {
	void save(DeptType arg0);

	void delete(@Param("officekindid") String arg0);

	void update(DeptType arg0);

	DeptType get(@Param("officekindid") String arg0);

	List<DeptType> findDeptType(DeptType arg0);

	int findDeptTypeCount(DeptType arg0);

	List<DeptType> getAll();
}