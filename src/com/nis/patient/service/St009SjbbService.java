package com.nis.patient.service;

import com.nis.comm.entity.DataWarning;
import com.nis.comm.entity.MyPage;
import com.nis.patient.entity.ResistantAnalysis;
import com.nis.patient.entity.St009Sjbb;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public interface St009SjbbService {
	void save(St009Sjbb arg0);

	void delete(String arg0);

	void update(St009Sjbb arg0);

	void batchUpdAnalFlag(List<St009Sjbb> arg0);

	St009Sjbb get(String arg0);

	MyPage<St009Sjbb> a(St009Sjbb arg0);

	List<St009Sjbb> getAll();

	List<St009Sjbb> findSjbbForInfectList(St009Sjbb arg0);

	List<St009Sjbb> findSjbbListBytestNo(String arg0);

	List<St009Sjbb> findBacteriaList(St009Sjbb arg0);

	St009Sjbb getCheckResultTitle(St009Sjbb arg0);

	Map<String, String> ct(String arg0);

	List<St009Sjbb> getUnAnalysisRecord(int arg0);

	String masterCdts(String arg0, String arg1, String arg2);

	int masterCdtsNew(String arg0, String arg1, String arg2);

	Map<String, Date> getMonitorPatientTestLastAt(String arg0);

	Map<String, Date> getMonitorPatientTestsLastAt(String arg0);

	void updateFlagByMzzyid(St009Sjbb arg0);

	void updateFlagByTestOrderNo(St009Sjbb arg0);

	List<DataWarning> findPatentSjbbWarning(Date arg0, Date arg1);

	List<St009Sjbb> a(Date arg0, Integer arg1);

	List<St009Sjbb> b(Date arg0, Integer arg1);

	int w(Date arg0);

	int x(Date arg0);

	List<St009Sjbb> c(Date arg0, Integer arg1);

	List<St009Sjbb> d(Date arg0, Integer arg1);

	int y(Date arg0);

	int z(Date arg0);

	int findWaitAnaResisCount();

	List<ResistantAnalysis> findWaitAnaResis(Integer arg0, Date arg1, Date arg2, String arg3, Date arg4);

	List<St009Sjbb> b(St009Sjbb arg0);
}