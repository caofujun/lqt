package com.nis.patient.service;

import com.nis.comm.entity.DataWarning;
import com.nis.comm.entity.MyPage;
import com.nis.patient.entity.St008Bcjl;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface St008BcjlService {
	void save(St008Bcjl arg0);

	void delete(String arg0);

	void update(St008Bcjl arg0);

	void updateAnalFlag(String arg0, String arg1);

	void updateAnalResultTest(St008Bcjl arg0);

	St008Bcjl get(String arg0);

	MyPage<St008Bcjl> a(St008Bcjl arg0);

	List<St008Bcjl> getAll();

	List<String> getBcZyidList(Date arg0, int arg1, String arg2);

	int getBcZyidCount(Date arg0, String arg1);

	List<St008Bcjl> getBcListTest(Date arg0, int arg1, String arg2);

	List<St008Bcjl> getBcListByZyid(List<String> arg0, Date arg1, String arg2);

	List<St008Bcjl> findDisAnalysisList(St008Bcjl arg0);

	Date getMonitorPatientBcjlLastAt();

	List<DataWarning> findPatentBcjlWarning(Date arg0, Date arg1);

	List<St008Bcjl> findByZyid(String arg0);

	void az();

	void updateCdcAnalFlag(St008Bcjl arg0);

	List<St008Bcjl> getBcListForCDC(int arg0);
}