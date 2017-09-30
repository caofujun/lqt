package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgBk001Crbafpcard;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CtgBk001CrbafpcardDao {
	void save(CtgBk001Crbafpcard arg0);

	void delete(@Param("masterid") String arg0);

	void update(CtgBk001Crbafpcard arg0);

	CtgBk001Crbafpcard get(@Param("masterid") String arg0);

	List<CtgBk001Crbafpcard> findCtgBk001Crbafpcard(CtgBk001Crbafpcard arg0);

	int findCtgBk001CrbafpcardCount(CtgBk001Crbafpcard arg0);

	List<CtgBk001Crbafpcard> getAll();
}