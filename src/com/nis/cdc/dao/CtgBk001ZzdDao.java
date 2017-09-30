package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgBk001Zzd;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CtgBk001ZzdDao {
	void save(CtgBk001Zzd arg0);

	void delete(@Param("masterid") String arg0);

	void update(CtgBk001Zzd arg0);

	CtgBk001Zzd get(@Param("subid") String arg0);

	CtgBk001Zzd getByMasterid(@Param("masterid") String arg0);

	List<CtgBk001Zzd> findCtgBk001Zzd(CtgBk001Zzd arg0);

	int findCtgBk001ZzdCount(CtgBk001Zzd arg0);

	List<CtgBk001Zzd> getAll();

	int isFjhExist(@Param("masterid") String arg0);
}