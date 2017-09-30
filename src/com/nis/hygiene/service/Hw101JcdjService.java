package com.nis.hygiene.service;

import com.nis.access.entity.AcAccount;
import com.nis.comm.entity.MyPage;
import com.nis.hygiene.entity.Hw101Jcdj;
import com.nis.hygiene.entity.Hw102Jcdmx;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Hw101JcdjService {
	void save(Hw101Jcdj arg0);

	void delete(String arg0);

	void update(Hw101Jcdj arg0);

	void updateSpecified(Hw101Jcdj arg0, List<String> arg1);

	Hw101Jcdj get(String arg0);

	MyPage<Hw101Jcdj> a(Hw101Jcdj arg0);

	List<Hw101Jcdj> getAll();

	MyPage<Hw102Jcdmx> a(Hw102Jcdmx arg0);

	void a(Hw101Jcdj arg0, Hw102Jcdmx arg1);

	void a(Hw101Jcdj arg0, Hw102Jcdmx arg1, AcAccount arg2);

	void bv(String arg0);

	void b(Hw102Jcdmx arg0);

	void updatePrintFlag(String arg0);
}