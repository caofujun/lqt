package com.nis.log.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.ap;
import com.nis.log.dao.SysOperateLogDao;
import com.nis.log.entity.SysOperateLog;
import com.nis.log.service.SysOperateLogService;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysOperateLogServiceImpl implements SysOperateLogService {
	@Autowired
	private SysOperateLogDao tP;

	public void save(SysOperateLog sysOperateLog) {
		this.tP.save(sysOperateLog);
	}

	public void delete(String id) {
		this.tP.delete(id);
	}

	public void update(SysOperateLog sysOperateLog) {
		this.tP.update(sysOperateLog);
	}

	public SysOperateLog get(String id) {
		return this.tP.get(id);
	}

	public MyPage<SysOperateLog> a(SysOperateLog sysOperateLog) {
		int total = this.tP.findSysOperateLogCount(sysOperateLog);
		List data = null;
		if (total > 0) {
			data = this.tP.findSysOperateLog(sysOperateLog);
			Iterator arg4 = data.iterator();

			while (arg4.hasNext()) {
				SysOperateLog sol = (SysOperateLog) arg4.next();
				sol.setActionName(ap.b(sol.getAction()));
			}
		}

		return new MyPage(sysOperateLog.getPage().intValue(), sysOperateLog.getSize().intValue(), total, data);
	}

	public List<SysOperateLog> getAll() {
		return this.tP.getAll();
	}
}