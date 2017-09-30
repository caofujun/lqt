package com.nis.report.service;

import com.nis.comm.entity.MyPage;
import com.nis.report.entity.SysModuleExplain;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface SysModuleExplainService {
	void save(SysModuleExplain arg0);

	void delete(String arg0);

	void update(SysModuleExplain arg0);

	SysModuleExplain get(String arg0);

	MyPage<SysModuleExplain> a(SysModuleExplain arg0);

	List<SysModuleExplain> getAll();

	List<SysModuleExplain> findByName(String arg0);
}