package com.nis.log.service.impl;

import com.nis.comm.entity.DataWarning;
import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.af;
import com.nis.comm.enums.ag;
import com.nis.comm.enums.ah;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.l;
import com.nis.comm.utils.z;
import com.nis.log.dao.SysLogDao;
import com.nis.log.entity.SysLog;
import com.nis.log.service.SysLogService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysLogServiceImpl implements SysLogService {
	@Autowired
	private SysLogDao tN;

	public void save(SysLog sysLog) {
		sysLog.setId(z.a(bg.mn));
		this.tN.save(sysLog);
	}

	public void a(String unitId, String userName, ah logType, af logArea, ag logFun, Object businessId,
			Object... logContent) {
		SysLog sysLog = new SysLog();
		sysLog.setId(z.a(bg.mn));
		sysLog.setUnitId(unitId);
		sysLog.setUserName(userName);
		sysLog.setLogType(logType.getValue());
		sysLog.setLogArea(logArea.getValue());
		sysLog.setLogFun(logFun.getValue());
		sysLog.setBusinessId(businessId.toString());
		sysLog.setCreateTime(new Date());
		sysLog.setLogContent(l.toString(logContent));
		this.tN.save(sysLog);
	}

	public void delete(String id) {
		this.tN.delete(id);
	}

	public void update(SysLog sysLog) {
		this.tN.update(sysLog);
	}

	public SysLog get(String id) {
		return this.tN.get(id);
	}

	public MyPage<SysLog> a(SysLog sysLog) {
		int total = this.tN.findSysLogCount(sysLog);
		List data = null;
		if (total > 0) {
			data = this.tN.findSysLog(sysLog);
		}

		return new MyPage(sysLog.getPage().intValue(), sysLog.getSize().intValue(), total, data);
	}

	public List<SysLog> getAll() {
		return this.tN.getAll();
	}

	public List<SysLog> findSysLog(SysLog sysLog) {
		return this.tN.findSysLog(sysLog);
	}

	public List<DataWarning> findKettleWarning(Date queryStartDate, Date queryEndDate) {
		return this.tN.findKettleWarning(queryStartDate, queryEndDate);
	}

	public List<DataWarning> findMonitorWarning(Date queryStartDate, Date queryEndDate) {
		return this.tN.findMonitorWarning(queryStartDate, queryEndDate);
	}

	public void a(ah logType, String logFun, String businessId, String logContent) {
		SysLog sysLog = new SysLog();
		sysLog.setId(z.a(bg.mn));
		sysLog.setLogType(logType.getValue());
		sysLog.setLogFun(logFun);
		sysLog.setCreateTime(new Date());
		sysLog.setBusinessId(businessId);
		sysLog.setLogContent(l.toString(logContent));
		this.tN.save(sysLog);
	}

	public List<SysLog> findbyBusinessId(String businessId) {
		return this.tN.findbyBusinessId(businessId);
	}

	public void a(ah logType, String logFun, String businessId, String logContent, String userName) {
		SysLog sysLog = new SysLog();
		sysLog.setId(z.a(bg.mn));
		sysLog.setLogType(logType.getValue());
		sysLog.setLogFun(logFun);
		sysLog.setCreateTime(new Date());
		sysLog.setBusinessId(businessId);
		sysLog.setLogContent(logContent);
		sysLog.setUserName(userName);
		this.tN.save(sysLog);
	}

	public void b(ah logType, String userName, String logFun, String logContent) {
		try {
			SysLog e = new SysLog();
			e.setId(z.a(bg.mn));
			e.setLogType(logType.getValue());
			e.setLogFun(logFun);
			e.setCreateTime(new Date());
			e.setUserName(userName);
			if (logContent.length() > 3500) {
				e.setLogContent(logContent.substring(0, 3500));
				this.tN.save(e);
				e.setId(z.a(bg.mn));
				e.setLogContent(logContent.substring(3500));
			} else {
				e.setLogContent(logContent);
			}

			this.tN.save(e);
		} catch (Exception arg5) {
			arg5.printStackTrace();
		}

	}

	public void deleteSqlLtDate(String logType, Date date) {
		this.tN.deleteSqlLtDate(logType, date);
	}

	public void delSqlLog() {
		this.tN.delSqlLog();
	}
}