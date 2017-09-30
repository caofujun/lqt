package com.nis.patient.service;

import com.nis.access.entity.AcAccount;
import com.nis.comm.entity.DataWarning;
import com.nis.comm.entity.MyPage;
import com.nis.patient.entity.St003Cryxxb;
import com.nis.patient.entity.St020ClinicPatients;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface St003CryxxbService {
	void save(St003Cryxxb arg0);

	void delete(String arg0);

	void update(St003Cryxxb arg0);

	St003Cryxxb get(String arg0);

	MyPage<St003Cryxxb> l(St003Cryxxb arg0);

	List<St003Cryxxb> getAll();

	St003Cryxxb getPatientRecords(String arg0);

	List<St003Cryxxb> findCryxxByPatientId(String arg0);

	List<St003Cryxxb> findCryxxByFX();

	int findCountbyDate(St003Cryxxb arg0);

	St003Cryxxb getSt003Cryxxb(String arg0);

	MyPage<St003Cryxxb> a(String arg0, String arg1, String arg2, String arg3, Date arg4, Date arg5, Integer arg6,
			Integer arg7);

	MyPage<St003Cryxxb> b(String arg0, String arg1, String arg2, String arg3, Date arg4, Date arg5, Integer arg6,
			Integer arg7);

	List<St003Cryxxb> b(String arg0, String arg1, String arg2, String arg3, Date arg4, Date arg5);

	List<St003Cryxxb> m(St003Cryxxb arg0);

	MyPage<St003Cryxxb> n(St003Cryxxb arg0);

	List<St003Cryxxb> s(String arg0, String arg1, String arg2);

	List<St003Cryxxb> a(String arg0, String arg1, String arg2, String arg3, AcAccount arg4, String arg5, String arg6,
			String arg7, String arg8, String arg9, String arg10);

	MyPage<St003Cryxxb> o(St003Cryxxb arg0);

	MyPage<St003Cryxxb> a(St020ClinicPatients arg0);

	List<St003Cryxxb> h(String arg0, String arg1, String arg2, String arg3, String arg4);

	List<St003Cryxxb> a(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5, AcAccount arg6,
			String arg7, String arg8, String arg9, String arg10, String arg11);

	List<St003Cryxxb> findYjCalInfo(int arg0);

	MyPage<St003Cryxxb> b(St020ClinicPatients arg0);

	List<St003Cryxxb> findByPatientIdAndVisitId(String arg0, Integer arg1);

	Date cr(String arg0);

	Date cs(String arg0);

	List<DataWarning> findPatentCryxxbWarning(Date arg0, Date arg1);

	List<St003Cryxxb> ewListForInterface(St003Cryxxb arg0);
}