package com.nis.report.dao;

import com.nis.report.entity.SysModuleExplain;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysModuleExplainDao {
	void save(SysModuleExplain arg0);

	void delete(@Param("mid") String arg0);

	void update(SysModuleExplain arg0);

	SysModuleExplain get(@Param("mid") String arg0);

	List<SysModuleExplain> findSysModuleExplain(SysModuleExplain arg0);

	int findSysModuleExplainCount(SysModuleExplain arg0);

	List<SysModuleExplain> getAll();

	List<SysModuleExplain> findByName(@Param("mkName") String arg0);
}