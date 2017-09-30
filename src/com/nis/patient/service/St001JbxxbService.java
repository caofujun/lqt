package com.nis.patient.service;

import com.nis.comm.entity.MyPage;
import com.nis.patient.entity.PatientView;
import com.nis.patient.entity.St001Jbxxb;
import com.nis.patient.entity.St009Sjbb;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface St001JbxxbService {
	void save(St001Jbxxb arg0);

	void delete(String arg0);

	void update(St001Jbxxb arg0);

	St001Jbxxb get(String arg0);

	MyPage<St001Jbxxb> a(St001Jbxxb arg0);

	List<St001Jbxxb> getAll();

	List<PatientView> findbyZyid(String arg0, String arg1, String arg2, String arg3);

	void updWeightByZyid(String arg0, String arg1);

	List<St001Jbxxb> getByPatientId(String arg0);

	List<St009Sjbb> K(String arg0, String arg1);
}