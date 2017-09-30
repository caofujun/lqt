package com.nis.icu.dao;

import com.nis.icu.entity.By001Bfgz;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface By001BfgzDao {
	void save(By001Bfgz arg0);

	void delete(@Param("outbreakTypeId") String arg0);

	void update(By001Bfgz arg0);

	By001Bfgz get(@Param("outbreakTypeId") String arg0);

	List<By001Bfgz> findBy001Bfgz(By001Bfgz arg0);

	int findBy001BfgzCount(By001Bfgz arg0);

	List<By001Bfgz> getAll();
}