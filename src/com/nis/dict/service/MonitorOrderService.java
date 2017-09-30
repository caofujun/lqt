package com.nis.dict.service;

import com.nis.comm.entity.MyPage;
import com.nis.dict.entity.MonitorOrder;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface MonitorOrderService {
	void save(MonitorOrder arg0);

	void delete(String arg0);

	void update(MonitorOrder arg0);

	MonitorOrder get(String arg0);

	MyPage<MonitorOrder> a(MonitorOrder arg0);

	List<MonitorOrder> getAll();
}