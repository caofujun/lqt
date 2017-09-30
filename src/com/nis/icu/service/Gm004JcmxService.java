package com.nis.icu.service;

import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.TreeEntity;
import com.nis.icu.entity.CgPg;
import com.nis.icu.entity.Gm004Jcmx;
import com.nis.icu.entity.IcuCount;
import com.nis.icu.entity.NicuCount;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public interface Gm004JcmxService {
	void save(Gm004Jcmx arg0);

	void delete(Date arg0);

	void update(Gm004Jcmx arg0);

	void updateSpecified(Gm004Jcmx arg0, List<String> arg1);

	Gm004Jcmx get(Date arg0);

	List<Map<String, Object>> getDeptPatient(String arg0, String arg1);

	String getPatientNum(String arg0, String arg1);

	MyPage<Gm004Jcmx> a(Gm004Jcmx arg0);

	List<Gm004Jcmx> getAll();

	List<Gm004Jcmx> findByZyid(String arg0, String arg1);

	HSSFWorkbook a(String arg0, String arg1, String arg2, String arg3, Date arg4, Date arg5);

	List<Map<String, Object>> findqrxczxgxgr(String arg0, String arg1, String arg2);

	Map<String, Object> a(String arg0, String arg1, List<String> arg2);

	List<NicuCount> findNicuCount(String arg0, String arg1, String arg2);

	HSSFWorkbook n(String arg0, String arg1, String arg2);

	Map<String, Object> A(String arg0, String arg1);

	Map<String, Object> a(Date arg0, Date arg1, String arg2);

	Date getMonitorPatientTubeLastAt();

	List<CgPg> findCgByZyid(String arg0, Date arg1, Date arg2, String arg3);

	List<TreeEntity> getDeptPatientMenuNode(String arg0, String arg1);

	List<Gm004Jcmx> getpatientList(String arg0, String arg1);

	List<Map<String, Object>> getDeptPatient2(String arg0, String arg1, String arg2);

	String getPatientNum2(String arg0, String arg1, String arg2);

	List<TreeEntity> getDeptPatientMenuNode2(String arg0, String arg1, String arg2);

	int getNumByTypeId(String arg0, String arg1, Date arg2, Date arg3);

	void o(String arg0, String arg1, String arg2);

	void deleteHxj(Gm004Jcmx arg0);

	List<IcuCount> i(String arg0, String arg1, String arg2, String arg3);
}