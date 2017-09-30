package com.nis.cdc.dao;

import com.nis.cdc.entity.CtgBk001Crbmaster;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface CtgBk001CrbmasterDao {
	void save(CtgBk001Crbmaster arg0);

	void delete(@Param("masterid") String arg0);

	void update(CtgBk001Crbmaster arg0);

	CtgBk001Crbmaster get(@Param("masterid") String arg0);

	List<CtgBk001Crbmaster> getZYBK(CtgBk001Crbmaster arg0);

	List<CtgBk001Crbmaster> getAllBK(@Param("id") String arg0, @Param("cardType") String arg1);

	List<CtgBk001Crbmaster> getMZBK(CtgBk001Crbmaster arg0);

	List<CtgBk001Crbmaster> findCtgBk001Crbmaster(CtgBk001Crbmaster arg0);

	int findCtgBk001CrbmasterCount(CtgBk001Crbmaster arg0);

	List<CtgBk001Crbmaster> getAll();

	List<CtgBk001Crbmaster> findCardsForAdmin(CtgBk001Crbmaster arg0);

	void updateDelReason(CtgBk001Crbmaster arg0);

	void updatePrintFlag(@Param("masterid") String arg0);

	int reportHistoryQueryCount(CtgBk001Crbmaster arg0);

	List<CtgBk001Crbmaster> reportHistoryQuery(CtgBk001Crbmaster arg0);

	List<Map<String, Object>> classesDataForChart(@Param("currentDate") String arg0);

	List<Map<String, Object>> unAuditCards();

	List<Map<String, Object>> curDayYJBL();

	List<Map<String, Object>> diseaseTypeDataForChart(@Param("currentDate") String arg0);

	List<Map<String, Object>> areaDataForChart(@Param("currentDate") String arg0);

	List<Map<String, Object>> reportDataForChart(@Param("currentDate") String arg0);

	List<Map<String, Object>> yearDataForChart(@Param("startMonth") String arg0, @Param("endMonth") String arg1);

	List<CtgBk001Crbmaster> findExportCards(CtgBk001Crbmaster arg0);
}