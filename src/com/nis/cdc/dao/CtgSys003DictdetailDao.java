package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgSys003Dictdetail;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CtgSys003DictdetailDao {
	void save(CtgSys003Dictdetail arg0);

	void delete(@Param("scopeid") String arg0);

	void update(CtgSys003Dictdetail arg0);

	CtgSys003Dictdetail get(@Param("scopeid") String arg0);

	List<CtgSys003Dictdetail> findCtgSys003Dictdetail(CtgSys003Dictdetail arg0);

	int findCtgSys003DictdetailCount(CtgSys003Dictdetail arg0);

	List<CtgSys003Dictdetail> getAll();
}