package com.nis.mdr.service;

import com.nis.comm.entity.MyPage;
import com.nis.mdr.entity.Xn014Liskjyw;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public interface Xn014LiskjywService {
	void save(Xn014Liskjyw arg0);

	void delete(String arg0);

	void update(Xn014Liskjyw arg0);

	void match();

	Map<String, String> queryMatched();

	Xn014Liskjyw get(String arg0);

	MyPage<Xn014Liskjyw> a(Xn014Liskjyw arg0);

	List<Xn014Liskjyw> getAll();

	MyPage<Xn014Liskjyw> b(Xn014Liskjyw arg0);
}