package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgSys005Dictdept1;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CtgSys005Dictdept1Dao {
	void save(CtgSys005Dictdept1 arg0);

	void delete(@Param("deptid") String arg0);

	void update(CtgSys005Dictdept1 arg0);

	CtgSys005Dictdept1 get(@Param("deptid") String arg0);

	List<CtgSys005Dictdept1> findCtgSys005Dictdept1(CtgSys005Dictdept1 arg0);

	int findCtgSys005Dictdept1Count(CtgSys005Dictdept1 arg0);

	List<CtgSys005Dictdept1> getAll();
}