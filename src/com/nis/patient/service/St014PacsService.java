package com.nis.patient.service;

import com.nis.comm.entity.MyPage;
import com.nis.patient.entity.St014Pacs;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface St014PacsService {
	void save(St014Pacs arg0);

	void delete(String arg0);

	void update(St014Pacs arg0);

	void updateAnalFlag(String arg0, String arg1);

	St014Pacs get(String arg0);

	MyPage<St014Pacs> a(St014Pacs arg0);

	List<St014Pacs> getAll();

	List<St014Pacs> findImageResultsList(St014Pacs arg0);

	void updateAnalResultTest(St014Pacs arg0);

	List<St014Pacs> d(int arg0);

	int getPacsCount();

	List<St014Pacs> getPacsListForCDC(int arg0);

	Date getMonitorPatientPacsLastAt();

	Date getMonitorPatientZkxxsLastAt();

	void updateAnalFlagAndDate(St014Pacs arg0);
}