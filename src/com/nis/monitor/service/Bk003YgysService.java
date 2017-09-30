package com.nis.monitor.service;

import com.nis.comm.entity.MyPage;
import com.nis.monitor.entity.Bk002Grzd;
import com.nis.monitor.entity.Bk003Ygys;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Bk003YgysService {
	void save(Bk003Ygys arg0);

	void delete(String arg0);

	void update(Bk003Ygys arg0);

	Bk003Ygys get(String arg0);

	MyPage<Bk003Ygys> a(Bk003Ygys arg0);

	List<Bk003Ygys> findBk003Ygys(Bk003Ygys arg0);

	List<Bk003Ygys> findBk003YgysList(Bk003Ygys arg0);

	int findBk003YgysCount(Bk003Ygys arg0);

	List<Bk003Ygys> getAll();

	List<Bk003Ygys> findSusceptFactors(Bk003Ygys arg0);

	void b(Bk003Ygys arg0);

	void delSusceptFactors(Bk003Ygys arg0);

	void a(Bk002Grzd arg0, String[] arg1);

	void delByRefid(String arg0);

	void b(Bk002Grzd arg0, String[] arg1);
}