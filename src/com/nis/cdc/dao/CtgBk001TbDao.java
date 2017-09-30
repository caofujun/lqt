package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgBk001Tb;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CtgBk001TbDao {
	void save(CtgBk001Tb arg0);

	void delete(@Param("masterid") String arg0);

	void update(CtgBk001Tb arg0);

	CtgBk001Tb get(@Param("masterid") String arg0);

	List<CtgBk001Tb> findCtgBk001Tb(CtgBk001Tb arg0);

	int findCtgBk001TbCount(CtgBk001Tb arg0);

	List<CtgBk001Tb> getAll();

	int isTBExist(@Param("masterid") String arg0);
}