package com.nis.zg.service;

import com.nis.comm.entity.MyPage;
import com.nis.zg.entity.Zg021Ygys;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Zg021YgysService {
	void save(Zg021Ygys arg0);

	void delete(String arg0);

	void update(Zg021Ygys arg0);

	Zg021Ygys get(String arg0);

	MyPage<Zg021Ygys> a(Zg021Ygys arg0);

	List<Zg021Ygys> getAll();
}