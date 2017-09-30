package com.nis.dict.dao;

import com.nis.dict.entity.SysDict;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysDictDao {
	void save(SysDict arg0);

	void delete(@Param("id") String arg0);

	void deleteByTypeCode(@Param("dictTypeCode") String arg0);

	void update(SysDict arg0);

	void updateLastTimebyCode(@Param("dictTypeCode") String arg0, @Param("dictCode") String arg1,
			@Param("updateTime") Date arg2);

	void updateSort(@Param("id") String arg0, @Param("sequenceNumber") String arg1);

	SysDict get(@Param("id") String arg0);

	List<SysDict> findSysDict(SysDict arg0);

	int findSysDictCount(SysDict arg0);

	List<SysDict> getAll(SysDict arg0);

	List<SysDict> getAllTop(SysDict arg0);

	List<SysDict> findByDictTypeCode(@Param("dictTypeCode") String arg0, @Param("unitId") String arg1,
			@Param("depNo") String arg2);

	SysDict findByDictCode(@Param("dictCode") String arg0, @Param("dictTypeCode") String arg1,
			@Param("unitId") String arg2, @Param("depNo") String arg3);

	SysDict getMaxCodeAndSeq(@Param("dictTypeCode") String arg0, @Param("unitId") String arg1);

	List<SysDict> findByParentId(@Param("parentCode") String arg0, @Param("dictTypeCode") String arg1,
			@Param("unitId") String arg2);

	List<SysDict> findTop(@Param("dictTypeCode") String arg0, @Param("unitId") String arg1);

	List<SysDict> getAllSpecDescribes(SysDict arg0);

	int getMaxSpecDescribesNum();
}