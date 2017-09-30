package com.nis.report.service;

import com.nis.comm.entity.MyPage;
import com.nis.report.entity.SysReportExplain;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface SysReportExplainService {
	void save(SysReportExplain arg0);

	void delete(String arg0);

	void update(SysReportExplain arg0);

	SysReportExplain get(String arg0);

	MyPage<SysReportExplain> a(SysReportExplain arg0);

	List<SysReportExplain> getAll();

	List<SysReportExplain> findByName(String arg0);
}