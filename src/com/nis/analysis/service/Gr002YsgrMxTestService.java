package com.nis.analysis.service;

import com.nis.analysis.entity.Gr002YsgrMxTest;
import com.nis.comm.entity.MyPage;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Gr002YsgrMxTestService {
	void save(Gr002YsgrMxTest arg0);

	void delete(String arg0);

	void update(Gr002YsgrMxTest arg0);

	Gr002YsgrMxTest get(String arg0);

	MyPage<Gr002YsgrMxTest> a(Gr002YsgrMxTest arg0);

	List<Gr002YsgrMxTest> getAll();

	void deleteByZyidState(String arg0);

	List<Gr002YsgrMxTest> findByZyidInfectCode(String arg0, String arg1);
}