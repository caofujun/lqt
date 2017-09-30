package com.nis.log.service;

import com.nis.comm.entity.MyPage;
import com.nis.log.entity.SysLoginLog;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface SysLoginLogService {
	void save(SysLoginLog arg0);

	void delete(String arg0);

	void update(SysLoginLog arg0);

	SysLoginLog get(String arg0);

	MyPage<SysLoginLog> a(SysLoginLog arg0);

	List<SysLoginLog> getAll();

	SysLoginLog getByMacAddress(String arg0);
}