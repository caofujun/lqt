package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgSys003Dictmaster;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CtgSys003DictmasterDao {
	void save(CtgSys003Dictmaster arg0);

	void delete(@Param("scopeid") String arg0);

	void update(CtgSys003Dictmaster arg0);

	CtgSys003Dictmaster get(@Param("scopeid") String arg0);

	List<CtgSys003Dictmaster> findCtgSys003Dictmaster(CtgSys003Dictmaster arg0);

	int findCtgSys003DictmasterCount(CtgSys003Dictmaster arg0);

	List<CtgSys003Dictmaster> getAll();
}