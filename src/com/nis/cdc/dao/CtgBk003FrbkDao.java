package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgBk003Frbk;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CtgBk003FrbkDao {
	void save(CtgBk003Frbk arg0);

	void delete(@Param("masterid") String arg0);

	void update(CtgBk003Frbk arg0);

	CtgBk003Frbk get(@Param("masterid") String arg0);

	List<CtgBk003Frbk> findCtgBk003Frbk(CtgBk003Frbk arg0);

	int findCtgBk003FrbkCount(CtgBk003Frbk arg0);

	List<CtgBk003Frbk> getAll();
}