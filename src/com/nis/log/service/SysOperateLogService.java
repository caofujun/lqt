package com.nis.log.service;

import com.nis.comm.entity.MyPage;
import com.nis.log.entity.SysOperateLog;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface SysOperateLogService {
	void save(SysOperateLog arg0);

	void delete(String arg0);

	void update(SysOperateLog arg0);

	SysOperateLog get(String arg0);

	MyPage<SysOperateLog> a(SysOperateLog arg0);

	List<SysOperateLog> getAll();
}