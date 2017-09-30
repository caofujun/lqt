package com.nis.zg.service;

import com.nis.comm.entity.MyPage;
import com.nis.zg.entity.Zg027LisbbPp;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public interface Zg027LisbbPpService {
	void save(Zg027LisbbPp arg0);

	MyPage<Zg027LisbbPp> a(Zg027LisbbPp arg0);

	List<Zg027LisbbPp> getAll();

	void match();

	Map<String, String> queryMatched();

	MyPage<Zg027LisbbPp> b(Zg027LisbbPp arg0);

	Zg027LisbbPp get(String arg0);

	void update(Zg027LisbbPp arg0);

	void aB();
}