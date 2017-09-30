package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgBk001Crbstdcard;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CtgBk001CrbstdcardDao {
	void save(CtgBk001Crbstdcard arg0);

	void delete(@Param("masterid") String arg0);

	int isHIVExist(@Param("masterid") String arg0);

	void update(CtgBk001Crbstdcard arg0);

	CtgBk001Crbstdcard get(@Param("masterid") String arg0);

	List<CtgBk001Crbstdcard> findCtgBk001Crbstdcard(CtgBk001Crbstdcard arg0);

	int findCtgBk001CrbstdcardCount(CtgBk001Crbstdcard arg0);

	List<CtgBk001Crbstdcard> getAll();
}