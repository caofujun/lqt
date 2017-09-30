package com.nis.hygiene.service;

import com.nis.comm.entity.MyPage;
import com.nis.hygiene.entity.Hw002Jcbz;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Hw002JcbzService {
	void save(Hw002Jcbz arg0);

	void delete(String arg0);

	void update(Hw002Jcbz arg0);

	Hw002Jcbz get(String arg0);

	MyPage<Hw002Jcbz> a(Hw002Jcbz arg0);

	List<Hw002Jcbz> getAll();
}