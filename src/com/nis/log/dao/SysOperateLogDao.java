package com.nis.log.dao;

import com.nis.log.entity.SysOperateLog;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysOperateLogDao {
	void save(SysOperateLog arg0);

	void delete(@Param("id") String arg0);

	void update(SysOperateLog arg0);

	SysOperateLog get(@Param("id") String arg0);

	List<SysOperateLog> findSysOperateLog(SysOperateLog arg0);

	int findSysOperateLogCount(SysOperateLog arg0);

	List<SysOperateLog> getAll();
}