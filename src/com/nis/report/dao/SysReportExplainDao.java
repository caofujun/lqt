package com.nis.report.dao;

import com.nis.report.entity.SysReportExplain;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysReportExplainDao {
	void save(SysReportExplain arg0);

	void delete(@Param("reportId") String arg0);

	void update(SysReportExplain arg0);

	SysReportExplain get(@Param("reportId") String arg0);

	List<SysReportExplain> findSysReportExplain(SysReportExplain arg0);

	int findSysReportExplainCount(SysReportExplain arg0);

	List<SysReportExplain> getAll();

	List<SysReportExplain> findByName(@Param("reportName") String arg0);
}