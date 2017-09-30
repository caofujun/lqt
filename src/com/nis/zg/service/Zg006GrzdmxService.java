package com.nis.zg.service;

import com.nis.comm.entity.MyPage;
import com.nis.zg.entity.Zg006Grzdmx;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Zg006GrzdmxService {
	void save(Zg006Grzdmx arg0);

	void delete(Double arg0);

	void update(Zg006Grzdmx arg0);

	Zg006Grzdmx get(Double arg0);

	MyPage<Zg006Grzdmx> a(Zg006Grzdmx arg0);

	List<Zg006Grzdmx> getAll();
}