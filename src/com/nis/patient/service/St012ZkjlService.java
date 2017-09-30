package com.nis.patient.service;

import com.nis.comm.entity.MyPage;
import com.nis.patient.entity.St012Zkjl;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface St012ZkjlService {
	void save(St012Zkjl arg0);

	void delete(String arg0);

	void update(St012Zkjl arg0);

	St012Zkjl get(String arg0);

	MyPage<St012Zkjl> a(St012Zkjl arg0);

	List<St012Zkjl> getAll();

	List<St012Zkjl> findInAndOutDepList(String arg0);

	Date getMonitorPatientInZkxxLastAt(String arg0);

	Date getMonitorPatientOutZkxxLastAt(String arg0);
}