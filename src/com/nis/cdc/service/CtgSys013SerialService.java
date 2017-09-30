package com.nis.cdc.service;

import com.nis.cdc.entity.CtgSys013Serial;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface CtgSys013SerialService {
	void save(CtgSys013Serial arg0);

	void delete(String arg0);

	void update(CtgSys013Serial arg0);

	CtgSys013Serial get(String arg0);

	MyPage<CtgSys013Serial> a(CtgSys013Serial arg0);

	List<CtgSys013Serial> getAll();

	CtgSys013Serial getAUnusedRecord();

	String x(String arg0);

	void updateUnusedRecord(String arg0, String arg1, String arg2);

	String isSNUsed(String arg0);
}