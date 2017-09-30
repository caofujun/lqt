package com.nis.homepage.dao;

import com.nis.homepage.entity.SysHpComponent;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysHpComponentDao {
	void save(SysHpComponent arg0);

	void delete(@Param("id") String arg0);

	void update(SysHpComponent arg0);

	SysHpComponent get(@Param("id") String arg0);

	List<SysHpComponent> findSysHpComponent(SysHpComponent arg0);

	int findSysHpComponentCount(SysHpComponent arg0);

	List<SysHpComponent> getAll();

	List<SysHpComponent> find(SysHpComponent arg0);

	void updateStatus(@Param("id") String arg0, @Param("status") String arg1);

	SysHpComponent getComponentCode(@Param("componentCode") String arg0);
}