package com.nis.hygiene.service;

import com.nis.comm.entity.MyPage;
import com.nis.hygiene.entity.Hw010Zzry;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Hw010ZzryService {
	void save(Hw010Zzry arg0);

	void delete(String arg0);

	void update(Hw010Zzry arg0);

	Hw010Zzry get(String arg0);

	MyPage<Hw010Zzry> a(Hw010Zzry arg0);

	List<Hw010Zzry> getAll();

	List<Hw010Zzry> queryList(Hw010Zzry arg0);

	MyPage<Hw010Zzry> b(Hw010Zzry arg0);

	void x(String arg0, String arg1);
}