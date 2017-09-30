package com.nis.log.dao;

import com.nis.comm.entity.DataWarning;
import com.nis.log.entity.SysLog;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysLogDao {
	void save(SysLog arg0);

	void delete(@Param("id") String arg0);

	void update(SysLog arg0);

	SysLog get(@Param("id") String arg0);

	List<SysLog> findSysLog(SysLog arg0);

	int findSysLogCount(SysLog arg0);

	List<SysLog> getAll();

	List<SysLog> findbyBusinessId(@Param("businessId") String arg0);

	List<DataWarning> findKettleWarning(@Param("queryStartDate") Date arg0, @Param("queryEndDate") Date arg1);

	List<DataWarning> findMonitorWarning(@Param("queryStartDate") Date arg0, @Param("queryEndDate") Date arg1);

	void deleteSqlLtDate(@Param("logType") String arg0, @Param("date") Date arg1);

	void delSqlLog();
}