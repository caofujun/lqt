package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgBk001Crbhbvcard;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CtgBk001CrbhbvcardDao {
	void save(CtgBk001Crbhbvcard arg0);

	void delete(@Param("masterid") String arg0);

	void update(CtgBk001Crbhbvcard arg0);

	CtgBk001Crbhbvcard get(@Param("subid") String arg0);

	CtgBk001Crbhbvcard getByMasterId(@Param("masterid") String arg0);

	List<CtgBk001Crbhbvcard> findCtgBk001Crbhbvcard(CtgBk001Crbhbvcard arg0);

	int findCtgBk001CrbhbvcardCount(CtgBk001Crbhbvcard arg0);

	List<CtgBk001Crbhbvcard> getAll();

	int isYGExist(@Param("masterid") String arg0);
}