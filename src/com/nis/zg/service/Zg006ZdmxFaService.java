package com.nis.zg.service;

import com.nis.comm.entity.MyPage;
import com.nis.zg.entity.Zg006ZdmxFa;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Zg006ZdmxFaService {
	void save(Zg006ZdmxFa arg0);

	void delete(String arg0);

	void update(Zg006ZdmxFa arg0);

	Zg006ZdmxFa get(String arg0);

	MyPage<Zg006ZdmxFa> a(Zg006ZdmxFa arg0);

	List<Zg006ZdmxFa> getAll();

	void start(String arg0);

	void a(String arg0, Integer arg1, String arg2, String arg3);

	void v(String arg0, String arg1, String arg2);

	Zg006ZdmxFa getState();
}