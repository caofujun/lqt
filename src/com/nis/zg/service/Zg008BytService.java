package com.nis.zg.service;

import com.nis.comm.entity.MyPage;
import com.nis.zg.entity.Zg008Byt;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Zg008BytService {
	void save(Zg008Byt arg0);

	void delete(String arg0);

	void update(Zg008Byt arg0);

	Zg008Byt get(String arg0);

	MyPage<Zg008Byt> a(Zg008Byt arg0);

	List<Zg008Byt> getAll();
}