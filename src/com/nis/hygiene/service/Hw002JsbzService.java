package com.nis.hygiene.service;

import com.nis.comm.entity.MyPage;
import com.nis.hygiene.entity.Hw002Jsbz;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Hw002JsbzService {
	void save(Hw002Jsbz arg0);

	void delete(String arg0);

	void update(Hw002Jsbz arg0);

	Hw002Jsbz get(String arg0);

	MyPage<Hw002Jsbz> a(Hw002Jsbz arg0);

	List<Hw002Jsbz> getAll();

	List<Hw002Jsbz> findListByClassId(String arg0);

	List<Hw002Jsbz> findList(Hw002Jsbz arg0);

	Hw002Jsbz getHw002Jsbz(String arg0);

	String findMaxItemId();
}