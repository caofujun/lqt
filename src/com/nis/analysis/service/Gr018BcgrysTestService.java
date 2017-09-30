package com.nis.analysis.service;

import com.nis.analysis.entity.Gr018BcgrysTest;
import com.nis.comm.entity.MyPage;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Gr018BcgrysTestService {
	void save(Gr018BcgrysTest arg0);

	void saveList(List<Gr018BcgrysTest> arg0);

	void delete(String arg0, String arg1);

	void update(Gr018BcgrysTest arg0);

	Gr018BcgrysTest get(String arg0);

	MyPage<Gr018BcgrysTest> a(Gr018BcgrysTest arg0);

	List<Gr018BcgrysTest> getAll();

	Date getMonitorPatientBcLastAt();
}