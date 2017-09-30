package com.nis.log.dao;

import com.nis.log.entity.SysLoginLog;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysLoginLogDao {
	void save(SysLoginLog arg0);

	void delete(@Param("id") String arg0);

	void update(SysLoginLog arg0);

	SysLoginLog get(@Param("id") String arg0);

	List<SysLoginLog> findSysLoginLog(SysLoginLog arg0);

	int findSysLoginLogCount(SysLoginLog arg0);

	List<SysLoginLog> getAll();

	SysLoginLog getByMacAddress(@Param("macAddress") String arg0);
}