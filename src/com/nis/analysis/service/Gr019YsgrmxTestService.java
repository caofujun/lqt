package com.nis.analysis.service;

import com.nis.analysis.entity.Gr019YsgrmxTest;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Gr019YsgrmxTestService {
	void save(Gr019YsgrmxTest arg0);

	void saveList(List<Gr019YsgrmxTest> arg0);

	void delete(String arg0);

	void update(Gr019YsgrmxTest arg0);

	Gr019YsgrmxTest get(String arg0);

	MyPage<Gr019YsgrmxTest> a(Gr019YsgrmxTest arg0);

	List<Gr019YsgrmxTest> getAll();

	List<Gr019YsgrmxTest> getbyZyid(String arg0);
}