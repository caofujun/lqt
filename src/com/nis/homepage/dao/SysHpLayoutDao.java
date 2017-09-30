package com.nis.homepage.dao;

import com.nis.homepage.entity.SysHpLayout;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysHpLayoutDao {
	void save(SysHpLayout arg0);

	void delete(@Param("id") String arg0);

	void update(SysHpLayout arg0);

	SysHpLayout get(@Param("id") String arg0);

	List<SysHpLayout> findSysHpLayout(SysHpLayout arg0);

	int findSysHpLayoutCount(SysHpLayout arg0);

	List<SysHpLayout> getAll();

	List<SysHpLayout> find(SysHpLayout arg0);

	void updateStatus(@Param("id") String arg0, @Param("status") String arg1);

	SysHpLayout getLayoutCode(@Param("layoutCode") String arg0);
}