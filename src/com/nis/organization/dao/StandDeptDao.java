package com.nis.organization.dao;

import com.nis.organization.entity.StandDept;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StandDeptDao {
	void save(StandDept arg0);

	void delete(@Param("deptId") String arg0);

	void update(StandDept arg0);

	StandDept get(@Param("deptId") String arg0);

	List<StandDept> findStandDept(StandDept arg0);

	int findStandDeptCount(StandDept arg0);

	List<StandDept> getAll();

	List<StandDept> getStandDept(StandDept arg0);
}