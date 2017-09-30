package com.nis.organization.service;

import com.nis.access.entity.AcAccount;
import com.nis.comm.entity.MyPage;
import com.nis.organization.entity.Doctor;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface DoctorService {
	void save(Doctor arg0);

	void delete(String arg0, String arg1, String arg2);

	void update(Doctor arg0);

	MyPage<Doctor> a(Doctor arg0);

	MyPage<Doctor> b(Doctor arg0);

	List<Doctor> findDoctorListByDept(String arg0, List<String> arg1, String arg2);

	List<Doctor> getAll(String arg0);

	Doctor getDoctorByHisDocNo(Doctor arg0);

	List<Doctor> findLike(String arg0, String arg1, String arg2);

	Doctor get(String arg0);

	AcAccount a(String arg0, String arg1, String arg2, boolean arg3);

	AcAccount c(Doctor arg0);

	void updatePwd(String arg0, String arg1, String arg2);

	List<Doctor> findExtis(Doctor arg0);

	List<Doctor> listDoctor(String arg0, List<String> arg1);

	MyPage<Doctor> a(Doctor arg0, String arg1);

	Doctor ck(String arg0);

	String q(String arg0, String arg1, String arg2);

	List<Doctor> queryToSelect(Doctor arg0);

	List<Doctor> queryToSelect1(Doctor arg0);

	void r(String arg0, String arg1, String arg2);
}