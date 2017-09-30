package com.nis.dict.dao;

import com.nis.dict.entity.SysDictType;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysDictTypeDao {
	void save(SysDictType arg0);

	void delete(@Param("id") String arg0);

	void update(SysDictType arg0);

	SysDictType get(@Param("id") String arg0);

	List<SysDictType> findSysDictType(SysDictType arg0);

	int findSysDictTypeCount(SysDictType arg0);

	List<SysDictType> getAll(SysDictType arg0);
}