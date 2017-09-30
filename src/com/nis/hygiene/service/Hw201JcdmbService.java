package com.nis.hygiene.service;

import com.nis.access.entity.AcAccount;
import com.nis.comm.entity.MyPage;
import com.nis.hygiene.entity.Hw101Jcdj;
import com.nis.hygiene.entity.Hw201Jcdmb;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Hw201JcdmbService {
	void save(Hw201Jcdmb arg0);

	void delete(String arg0);

	void update(Hw201Jcdmb arg0);

	Hw201Jcdmb get(String arg0);

	MyPage<Hw201Jcdmb> a(Hw201Jcdmb arg0);

	List<Hw201Jcdmb> getAll();

	List<Hw201Jcdmb> findTempletList(Hw201Jcdmb arg0);

	List<Hw201Jcdmb> findSampleList(Hw201Jcdmb arg0);

	void i(String[] arg0);

	int findByTempletName(Hw201Jcdmb arg0);

	void delByTempletName(Hw201Jcdmb arg0);

	void batchInsert(List<Hw201Jcdmb> arg0);

	void b(Hw201Jcdmb arg0);

	void a(Hw101Jcdj arg0, Hw201Jcdmb arg1, AcAccount arg2);
}