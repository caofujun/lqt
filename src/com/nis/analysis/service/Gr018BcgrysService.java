package com.nis.analysis.service;

import com.nis.analysis.entity.Gr018Bcgrys;
import com.nis.comm.entity.MyPage;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Gr018BcgrysService {
	void save(Gr018Bcgrys arg0);

	void saveList(List<Gr018Bcgrys> arg0);

	void delete(String arg0, String arg1);

	void update(Gr018Bcgrys arg0);

	Gr018Bcgrys get(String arg0);

	MyPage<Gr018Bcgrys> a(Gr018Bcgrys arg0);

	List<Gr018Bcgrys> getAll();

	Date getMonitorPatientBcLastAt();

	int findGrysCount(String arg0, String arg1);
}