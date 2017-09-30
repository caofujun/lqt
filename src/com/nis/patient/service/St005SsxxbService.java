package com.nis.patient.service;

import com.nis.comm.entity.DataWarning;
import com.nis.comm.entity.MyPage;
import com.nis.patient.entity.St005Ssxxb;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public interface St005SsxxbService {
	void save(St005Ssxxb arg0);

	void delete(String arg0);

	void update(St005Ssxxb arg0);

	void updateSpecified(St005Ssxxb arg0, List<String> arg1);

	St005Ssxxb get(String arg0);

	MyPage<St005Ssxxb> a(St005Ssxxb arg0);

	List<St005Ssxxb> getAll();

	List<St005Ssxxb> findListByZyid(String arg0);

	List<St005Ssxxb> findListByPatientId(String arg0, Date arg1);

	List<St005Ssxxb> findListByName(String[] arg0);

	Integer findGradeType(String arg0);

	MyPage<St005Ssxxb> b(St005Ssxxb arg0);

	List<St005Ssxxb> findSurgeryDayList(St005Ssxxb arg0);

	HSSFWorkbook c(St005Ssxxb arg0);

	HSSFWorkbook d(St005Ssxxb arg0);

	void e(St005Ssxxb arg0);

	void f(St005Ssxxb arg0);

	void M(List<St005Ssxxb> arg0);

	void a(St005Ssxxb arg0, boolean arg1);

	Map<String, Object> g(St005Ssxxb arg0);

	Date getMonitorPatientSsxxLastAt(String arg0);

	List<DataWarning> findPatentSsxxbWarning(Date arg0, Date arg1);

	Date getNumBefore(String arg0, Date arg1, int arg2);

	Integer getNnisPer(Integer arg0, String arg1);

	St005Ssxxb getByRelid(String arg0);

	void L(String arg0, String arg1);

	Date getRecentOperAt(String arg0, Date arg1);
}