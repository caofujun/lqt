package com.nis.zg.service;

import com.nis.comm.entity.MyPage;
import com.nis.zg.entity.Zg010Kjyw;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Zg010KjywService {
	void save(Zg010Kjyw arg0);

	void delete(String arg0);

	void update(Zg010Kjyw arg0);

	Zg010Kjyw get(String arg0);

	MyPage<Zg010Kjyw> a(Zg010Kjyw arg0);

	List<Zg010Kjyw> getAll();
}