package com.nis.log.service;

import com.nis.comm.entity.DataWarning;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.af;
import com.nis.comm.enums.ag;
import com.nis.comm.enums.ah;
import com.nis.log.entity.SysLog;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface SysLogService {
	void save(SysLog arg0);

	void a(String arg0, String arg1, ah arg2, af arg3, ag arg4, Object arg5, Object... arg6);

	void delete(String arg0);

	void update(SysLog arg0);

	SysLog get(String arg0);

	MyPage<SysLog> a(SysLog arg0);

	List<SysLog> getAll();

	List<SysLog> findSysLog(SysLog arg0);

	List<DataWarning> findKettleWarning(Date arg0, Date arg1);

	List<DataWarning> findMonitorWarning(Date arg0, Date arg1);

	void a(ah arg0, String arg1, String arg2, String arg3);

	List<SysLog> findbyBusinessId(String arg0);

	void a(ah arg0, String arg1, String arg2, String arg3, String arg4);

	void b(ah arg0, String arg1, String arg2, String arg3);

	void deleteSqlLtDate(String arg0, Date arg1);

	void delSqlLog();
}