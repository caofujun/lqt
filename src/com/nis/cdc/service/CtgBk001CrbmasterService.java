package com.nis.cdc.service;

import com.nis.access.entity.AcAccount;
import com.nis.cdc.entity.CtgBk001Crbmaster;
import com.nis.comm.entity.MyPage;
import com.nis.comm.entity.Result;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public interface CtgBk001CrbmasterService {
	void save(CtgBk001Crbmaster arg0);

	void delete(String arg0);

	void update(CtgBk001Crbmaster arg0);

	CtgBk001Crbmaster get(String arg0);

	List<CtgBk001Crbmaster> getMZBK(CtgBk001Crbmaster arg0);

	List<CtgBk001Crbmaster> getZYBK(CtgBk001Crbmaster arg0);

	List<CtgBk001Crbmaster> getAllBK(String arg0, String arg1);

	MyPage<CtgBk001Crbmaster> b(CtgBk001Crbmaster arg0);

	List<CtgBk001Crbmaster> getAll();

	Result<String> a(CtgBk001Crbmaster arg0, AcAccount arg1);

	List<CtgBk001Crbmaster> findCardsForAdmin(CtgBk001Crbmaster arg0);

	void a(HttpServletResponse arg0, CtgBk001Crbmaster arg1);

	void updateDelReason(CtgBk001Crbmaster arg0);

	void updatePrintFlag(String arg0);

	MyPage<CtgBk001Crbmaster> c(CtgBk001Crbmaster arg0);

	Result<String> c(String arg0, String arg1, String arg2);

	Result<String> d(String arg0, String arg1, String arg2);

	List<Map<String, Object>> classesDataForChart(String arg0);

	List<Map<String, Object>> unAuditCards();

	List<Map<String, Object>> curDayYJBL();

	List<Map<String, Object>> diseaseTypeDataForChart(String arg0);

	List<Map<String, Object>> areaDataForChart(String arg0);

	List<Map<String, Object>> reportDataForChart(String arg0);

	List<Map<String, Object>> yearDataForChart(String arg0, String arg1);
}