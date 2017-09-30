package com.nis.follow.service;

import com.nis.comm.entity.MyPage;
import com.nis.follow.entity.FoPatient;
import com.nis.patient.entity.St003Cryxxb;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface FoPatientService {
	void save(FoPatient arg0);

	void delete(String arg0);

	void deleteByPatientId(String arg0, String arg1);

	void update(FoPatient arg0);

	FoPatient get(String arg0);

	MyPage<FoPatient> a(FoPatient arg0);

	List<FoPatient> getAll();

	List<FoPatient> getByPatientId(String arg0);

	List<St003Cryxxb> findByDeptIdDetail(FoPatient arg0);
}