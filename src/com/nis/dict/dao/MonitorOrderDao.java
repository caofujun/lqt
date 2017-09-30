package com.nis.dict.dao;

import com.nis.dict.entity.MonitorOrder;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MonitorOrderDao {
	void save(MonitorOrder arg0);

	void delete(@Param("orderCode") String arg0);

	void update(MonitorOrder arg0);

	MonitorOrder get(@Param("orderCode") String arg0);

	List<MonitorOrder> findMonitorOrder(MonitorOrder arg0);

	int findMonitorOrderCount(MonitorOrder arg0);

	List<MonitorOrder> getAll();
}