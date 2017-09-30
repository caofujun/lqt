package com.nis.intervene.service;

import com.nis.comm.entity.MyPage;
import com.nis.intervene.entity.FxPatient;
import com.nis.intervene.entity.FxPatientIndex;
import com.nis.patient.entity.St003Cryxxb;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public interface FxPatientService {
	void save(FxPatient arg0);

	void delete(String arg0);

	void update(FxPatient arg0);

	FxPatient get(String arg0);

	MyPage<FxPatient> a(FxPatient arg0);

	List<FxPatient> getAll();

	void F();

	MyPage<FxPatientIndex> b(FxPatient arg0);

	List<FxPatient> findFxPatientList(FxPatient arg0);

	List<Map<String, Object>> a(Date arg0, Date arg1, String arg2, String arg3, String arg4);

	HSSFWorkbook D(List<FxPatientIndex> arg0);

	List<FxPatientIndex> c(FxPatient arg0);

	List<FxPatient> findFxPatientByList(List<St003Cryxxb> arg0);
}