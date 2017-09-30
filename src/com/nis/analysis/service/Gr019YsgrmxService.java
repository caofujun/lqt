package com.nis.analysis.service;

import com.nis.analysis.entity.Gr019Ysgrmx;
import com.nis.comm.entity.MyPage;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface Gr019YsgrmxService {
	void save(Gr019Ysgrmx arg0);

	void saveList(List<Gr019Ysgrmx> arg0);

	void delete(String arg0);

	void update(Gr019Ysgrmx arg0);

	Gr019Ysgrmx get(String arg0);

	MyPage<Gr019Ysgrmx> a(Gr019Ysgrmx arg0);

	List<Gr019Ysgrmx> getAll();

	Date getMonitorPatientGrLastAt();

	List<Gr019Ysgrmx> getbyZyid(String arg0);
}