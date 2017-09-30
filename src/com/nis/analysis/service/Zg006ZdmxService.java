package com.nis.analysis.service;

import com.nis.analysis.entity.Zg006Zdmx;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Zg006ZdmxService {
	void save(Zg006Zdmx arg0);

	void delete(String arg0);

	void update(Zg006Zdmx arg0);

	Zg006Zdmx get(String arg0);

	MyPage<Zg006Zdmx> a(Zg006Zdmx arg0);

	List<Zg006Zdmx> getAll();

	boolean g();

	Zg006Zdmx e(String arg0, String arg1);

	List<Zg006Zdmx> findbyElementId(String arg0);

	List<Zg006Zdmx> findMustList();

	List<Zg006Zdmx> findNeedCountList();
}