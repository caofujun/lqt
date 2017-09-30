package com.nis.mdr.service;

import com.nis.comm.entity.MyPage;
import com.nis.mdr.entity.Xn003Kjyw;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Xn003KjywService {
	void save(Xn003Kjyw arg0);

	void delete(String arg0);

	void update(Xn003Kjyw arg0);

	Xn003Kjyw get(String arg0);

	MyPage<Xn003Kjyw> a(Xn003Kjyw arg0);

	List<Xn003Kjyw> getAll();

	List<Xn003Kjyw> b(Xn003Kjyw arg0);
}