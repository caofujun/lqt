package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgSys014Todolist;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CtgSys014TodolistDao {
	void save(CtgSys014Todolist arg0);

	void delete(@Param("keyid") String arg0);

	void update(CtgSys014Todolist arg0);

	CtgSys014Todolist get(@Param("keyid") String arg0);

	List<CtgSys014Todolist> findCtgSys014Todolist(CtgSys014Todolist arg0);

	int findCtgSys014TodolistCount(CtgSys014Todolist arg0);

	List<CtgSys014Todolist> getAll();

	List<CtgSys014Todolist> isExist(CtgSys014Todolist arg0);

	void removeByZyidPatientType(CtgSys014Todolist arg0);
}