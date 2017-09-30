package com.nis.mdr.service;

import com.nis.comm.entity.MyPage;
import com.nis.mdr.entity.Xn013Dbyw;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Xn013DbywService {
	void save(Xn013Dbyw arg0);

	void delete(Long arg0);

	void update(Xn013Dbyw arg0);

	Xn013Dbyw get(Long arg0);

	MyPage<Xn013Dbyw> a(Xn013Dbyw arg0);

	List<Xn013Dbyw> getAll();
}