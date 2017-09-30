package com.nis.intervene.service;

import com.nis.comm.entity.MyPage;
import com.nis.intervene.entity.FxPatientZb;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface FxPatientZbService {
	void save(FxPatientZb arg0);

	List<FxPatientZb> a(FxPatientZb arg0);

	void delete(String arg0);

	void update(FxPatientZb arg0);

	FxPatientZb get(String arg0);

	MyPage<FxPatientZb> b(FxPatientZb arg0);

	List<FxPatientZb> getAll();

	List<FxPatientZb> findByzyid(String arg0);

	void saveList(List<FxPatientZb> arg0);
}