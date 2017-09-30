package com.nis.outbreak.service;

import com.nis.comm.entity.MyPage;
import com.nis.outbreak.entity.By007GraphData;
import com.nis.outbreak.entity.By007Show;
import com.nis.patient.entity.St003Cryxxb;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public interface By007ShowService {
	void save(By007Show arg0);

	void delete(String arg0);

	void update(By007Show arg0);

	By007Show get(String arg0);

	MyPage<By007Show> a(By007Show arg0);

	List<By007Show> getAll();

	List<Map<String, Object>> cm(String arg0);

	List<Map<String, Object>> cn(String arg0);

	List<By007GraphData> o(String arg0, String arg1, String arg2, String arg3);

	List<Map<String, Object>> co(String arg0);

	List<Map<String, Object>> cp(String arg0);

	List<By007GraphData> p(String arg0, String arg1, String arg2, String arg3);

	List<By007Show> findShowFields(Integer arg0);

	List<St003Cryxxb> findPatientInfo(By007Show arg0);

	List<St003Cryxxb> findBloodCulturePatient(By007Show arg0);

	List<Map<String, Object>> b(By007Show arg0);

	HSSFWorkbook c(By007Show arg0);

	HSSFWorkbook d(By007Show arg0);

	HSSFWorkbook e(By007Show arg0);

	HSSFWorkbook J(String arg0, String arg1);

	List<St003Cryxxb> findPathogenPatient(By007Show arg0);

	List<St003Cryxxb> findInfectionSiteList(By007Show arg0);
}