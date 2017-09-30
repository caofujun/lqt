package com.nis.log.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.comm.enums.bg;
import com.nis.comm.utils.z;
import com.nis.log.dao.SysLoginLogDao;
import com.nis.log.entity.SysLoginLog;
import com.nis.log.service.SysLoginLogService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysLoginLogServiceImpl implements SysLoginLogService {
	@Autowired
	private SysLoginLogDao tO;

	public void save(SysLoginLog sysLoginLog) {
		sysLoginLog.setId(z.a(bg.mo));
		this.tO.save(sysLoginLog);
	}

	public void delete(String id) {
		this.tO.delete(id);
	}

	public void update(SysLoginLog sysLoginLog) {
		this.tO.update(sysLoginLog);
	}

	public SysLoginLog get(String id) {
		return this.tO.get(id);
	}

	public MyPage<SysLoginLog> a(SysLoginLog sysLoginLog) {
		int total = this.tO.findSysLoginLogCount(sysLoginLog);
		List data = null;
		if (total > 0) {
			data = this.tO.findSysLoginLog(sysLoginLog);
		}

		return new MyPage(sysLoginLog.getPage().intValue(), sysLoginLog.getSize().intValue(), total, data);
	}

	public List<SysLoginLog> getAll() {
		return this.tO.getAll();
	}

	public SysLoginLog getByMacAddress(String macAddress) {
		return this.tO.getByMacAddress(macAddress);
	}
}