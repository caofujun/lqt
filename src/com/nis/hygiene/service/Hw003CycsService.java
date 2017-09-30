package com.nis.hygiene.service;

import com.nis.comm.entity.MyPage;
import com.nis.hygiene.entity.Hw003Cycs;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Hw003CycsService {
	void save(Hw003Cycs arg0);

	void delete(String arg0);

	void update(Hw003Cycs arg0);

	Hw003Cycs get(String arg0);

	Hw003Cycs isExist(String arg0);

	MyPage<Hw003Cycs> a(Hw003Cycs arg0);

	List<Hw003Cycs> getAll();

	List<Hw003Cycs> queryList(Hw003Cycs arg0);

	List<Hw003Cycs> findList(Hw003Cycs arg0);

	String findMaxPlaceId();

	void updFlag(Hw003Cycs arg0);
}